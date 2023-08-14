package com.ict.board.model.service;

import java.util.List;
import java.util.Map;

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
	
	@Override
	public int getLevUpdate(Map<String, Integer> map) {
		return boardDAO.getLevUpdate(map);
	}
	
	@Override
	public int BoardAnsInsert(BoardVO bovo) {
		return boardDAO.BoardAnsInsert(bovo);
	}

	@Override
	public int getBoardUpdateOk(BoardVO bovo) {
		return boardDAO.getBoardUpdateOk(bovo);
	}

	@Override
	public int getBoardDeleteOk(String idx) {
		return boardDAO.getBoardDeleteOk(idx);
	}
}
