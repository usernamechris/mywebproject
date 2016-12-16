package org.mycompany.myapp.dao;

import static org.junit.Assert.*;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mycompany.domain.MemberVO;
import org.mycompany.persistence.MemberDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;


@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(
		locations ={"file:src/main/webapp/WEB-INF/spring/**/*.xml"})
public class MemberDAOTest {

	private static final Logger logger =
			LoggerFactory.getLogger(MemberDAOTest.class);
	
	@Inject
	private MemberDAO dao;
	
	@Test
	public void testTime() throws Exception {
		Date now = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");

		System.out.println(dao.getTime());
		
		assertTrue(dao.getTime().startsWith(format.format(now)));
	}
	
	@Test(expected=DataAccessException.class)
	public void testInsertMember() throws Exception {
		MemberVO vo = new MemberVO();
		vo.setUserid("user000");
		vo.setUserpw("user000");
		vo.setUsername("USER000");
		vo.setEmail("user000@aaa.com");
		
		dao.insertMember(vo);
	}
}

