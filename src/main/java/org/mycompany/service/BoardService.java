package org.mycompany.service;

import java.util.List;

import org.mycompany.domain.BoardVO;
import org.mycompany.domain.Criteria;
import org.mycompany.domain.SearchCriteria;

public interface BoardService {

	public void regist(BoardVO board) throws Exception;

	public BoardVO read(Integer bno) throws Exception;

	public void modify(BoardVO board) throws Exception;

	public void remove(Integer bno) throws Exception;

	public List<BoardVO> listAll() throws Exception;
	
	public List<BoardVO> listCriteria(Criteria cri) throws Exception;
	
	public int listSearchCount(SearchCriteria cri) throws Exception;

	public List<BoardVO> listSearchCriteria(SearchCriteria cri) throws Exception;
}
