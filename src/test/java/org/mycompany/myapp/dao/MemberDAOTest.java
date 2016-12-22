package org.mycompany.myapp.dao;

import static org.junit.Assert.*;

import static org.hamcrest.Matchers.*;

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
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");

		Date now = new Date();
		Date dbTime = format.parse(dao.getTime());

		long diffMills = now.getTime() - dbTime.getTime();
		long diffMin = diffMills / 60000;
		long diff = Math.abs(diffMin);
		
		assertThat((Long)diff, lessThan(Long.valueOf(10)));
	}
	
	@Test
	public void numberOfMembers() {
		int number = dao.numberOfMembers();
		System.out.println(number);
	}
//	@Test(expected=DataAccessException.class)
//	//@Test
//	public void testInsertMember()  {
//		try {
//			MemberVO vo = new MemberVO();
//			vo.setUserid("user000");
//			vo.setUserpw("user000");
//			vo.setUsername("USER000");
//			vo.setEmail("user000@aaa.com");
//			
//			dao.insertMember(vo);	
//
//		} catch (Exception e) {
//			if (e.getMessage().contains("permission")) {
//				fail(e.getMessage());
//			}
//			throw e;
//		}
//		
//	}
}

