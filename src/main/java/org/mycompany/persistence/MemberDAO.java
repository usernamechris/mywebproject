package org.mycompany.persistence;

import org.mycompany.domain.MemberVO;

//인터페이스 정의. DB가 바뀌어도 DAO만 변경해서 처리하기 위해

public interface MemberDAO {

	public String getTime();
	
	public void insertMember(MemberVO vo);
	
	public int numberOfMembers();
	
	public MemberVO readMember(String userid) throws Exception;

	public MemberVO readWithPW(String userid, String userpw) throws Exception;

}
