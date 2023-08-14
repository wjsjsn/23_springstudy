package com.ict.board.model.service;

import java.util.List;
import java.util.Map;

import com.ict.board.model.vo.BoardVO;

public interface BoardService {

	
	// 페이지 처리
	int getCount();
	
	// 조회수 업데이트
	int getBoardHit(String idx);
	
	// board 전체 보기
	List<BoardVO> getBoardList(int offset, int limit);
	
	// 글 작성
	int BoardInsertOk(BoardVO bovo);
	
	// 상세보기
	BoardVO BoardOneList(String idx);
	
	int getLevUpdate(Map<String, Integer> map);
	
	int BoardAnsInsert(BoardVO bovo);
	
	int getBoardUpdateOk(BoardVO bovo);
	
	int getBoardDeleteOk(String idx);
}
