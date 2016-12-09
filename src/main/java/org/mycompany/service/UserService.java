package org.mycompany.service;

import java.util.Date;

import org.mycompany.domain.UserVO;
import org.mycompany.dto.LoginDTO;

public interface UserService {
	
	public UserVO login(LoginDTO dto) throws Exception;
	
	public void keepLogin(String uid, String sessionId, Date next) throws Exception;
	
	public UserVO checkLoginBefore(String value);

}
