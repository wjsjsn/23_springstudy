package com.ict.board.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ict.board.model.dao.BoardDAO;
import com.ict.board.model.vo.BoardVO;

@Service
public class BoardServiceImpl implements BoardService{

	@Autowired
	private BoardDAO boardDAO;

	@Override
	public List<BoardVO> getBoardList(int offset, int limit) {
		return boardDAO.getBoardList(offset, limit);
	}

	@Override
	public int getCount() {
		return boardDAO.getCount();
	}
	
	@Override
	public int BoardInsertOk(BoardVO bovo) {
		return boardDAO.BoardInsertOk(bovo);
	}
	
	@Override
	public BoardVO BoardOneList(String idx) {
		return boardDAO.BoardOneList(idx);
	}
	
	@Override
	public int getBoardHit(String idx) {
		return boardDAO.getBoardHit(idx);
	}
}
