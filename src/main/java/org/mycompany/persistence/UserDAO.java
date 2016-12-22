package org.mycompany.persistence;

import java.util.Date;

import org.mycompany.domain.UserVO;
import org.mycompany.dto.LoginDTO;

public interface UserDAO {
	
	public UserVO login(LoginDTO dto) throws Exception;
	
	public void keepLogin(String uid, String sessionId, Date next);
	
	public UserVO checkUserWithSessionKey(String value);

	public void addUser(UserVO vo);

	public void deleteUser(UserVO vo);

}
