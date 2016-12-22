package org.mycompany.myapp.dao;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import javax.inject.Inject;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mycompany.domain.UserVO;
import org.mycompany.dto.LoginDTO;
import org.mycompany.persistence.UserDAO;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(
		locations ={"file:src/main/webapp/WEB-INF/spring/**/*.xml"})
public class UserDAOTest {

	@Inject
	private UserDAO dao;
	
	private UserVO user1;

	@Before
	public void setUp() {
		user1 = new UserVO();
		user1.setUid("testUser1");
		user1.setUpw("testPw1");
		user1.setUname("tester1");	
	}

	@Test
	public void loginTest() throws Exception {
		LoginDTO dto = new LoginDTO();
		dto.setUid("user01");
		dto.setUpw("user01");

		UserVO vo = dao.login(dto);
		assertThat(vo.getUid(), is("user01"));
	}
	
	@Test
	public void addNewUser() throws Exception {
		dao.deleteUser(user1);
		
		dao.addUser(user1);
		
		LoginDTO dto = new LoginDTO();
		dto.setUid(user1.getUid());
		dto.setUpw(user1.getUpw());
		UserVO addedUser = dao.login(dto);
		
		assertThat(user1.getUid(), is(addedUser.getUid()));
		assertThat(user1.getUpw(), is(addedUser.getUpw()));
	}
	
}
