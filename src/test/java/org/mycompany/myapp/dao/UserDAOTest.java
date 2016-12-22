package org.mycompany.myapp.dao;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import javax.inject.Inject;

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
		UserVO newUser = new UserVO();
		newUser.setUid("testUser1");
		newUser.setUpw("testPw1");
		newUser.setUname("tester1");
		
		dao.addUser(newUser);
		
		LoginDTO dto = new LoginDTO();
		dto.setUid(newUser.getUid());
		dto.setUpw(newUser.getUpw());
		UserVO addedUser = dao.login(dto);
		
		assertThat(newUser.getUid(), is(addedUser.getUid()));
		assertThat(newUser.getUpw(), is(addedUser.getUpw()));
	}
	
}
