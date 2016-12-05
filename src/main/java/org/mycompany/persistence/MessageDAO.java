package org.mycompany.persistence;

import org.mycompany.domain.MessageVO;

public interface MessageDAO {
	
	public void creat(MessageVO vo) throws Exception;
	
	public MessageVO readMessage(Integer mid) throws Exception;
	
	public void updateState(Integer mid) throws Exception;

}
