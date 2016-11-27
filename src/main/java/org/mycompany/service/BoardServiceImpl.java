package org.mycompany.service;

import java.util.List;

import javax.inject.Inject;

import org.mycompany.domain.BoardVO;
import org.mycompany.domain.Criteria;
import org.mycompany.persistence.BoardDAO;
import org.springframework.stereotype.Service;

@Service //스프링 빈으로 인식되기 위함
public class BoardServiceImpl implements BoardService {
	@Inject
	private BoardDAO dao;

	@Override
	public void regist(BoardVO board) throws Exception {
		dao.create(board);

	}

	@Override
	public BoardVO read(Integer bno) throws Exception {
		return dao.read(bno);
	}

	@Override
	public void modify(BoardVO board) throws Exception {
		dao.update(board);
	}

	@Override
	public void remove(Integer bno) throws Exception {
		dao.delete(bno);
	}

	@Override
	public List<BoardVO> listAll() throws Exception {
		return dao.listAll();
	}

	@Override
	public List<BoardVO> listCriteria(Criteria cri) throws Exception {
		return dao.listCriteria(cri);
	}

}
