package com.ict.board.model.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ict.board.model.vo.BoardVO;

@Repository
public class BoardDAO {

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	// 페이지 처리
	public int getCount() {
		return sqlSessionTemplate.selectOne("board.count");
	}
	
	// 조회수 업데이트
	public int getBoardHit(String idx) {
		return sqlSessionTemplate.update("board.hitUpdate", idx);
	}
	
	// board 전체 보기
	public List<BoardVO> getBoardList(int offset, int limit){
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("limit", limit);
		map.put("offset", offset);
		
		return sqlSessionTemplate.selectList("board.list", map);
	}
	
	// 글쓰기
	public int BoardInsertOk(BoardVO bovo) {
		return sqlSessionTemplate.insert("board.insert", bovo);
	}
	
	// 상세보기
	public BoardVO BoardOneList(String idx) {
		return sqlSessionTemplate.selectOne("board.onelist", idx);
	}
	
	public int getLevUpdate(Map<String, Integer> map) {
		return sqlSessionTemplate.update("board.levUpdate", map);
	}
	
	public int BoardAnsInsert(BoardVO bovo) {
		return sqlSessionTemplate.insert("board.ansInsert", bovo);
	}
	
	public int getBoardUpdateOk(BoardVO bovo) {
		return sqlSessionTemplate.update("board.update", bovo);
	}
	
	public int getBoardDeleteOk(String idx) {
		return sqlSessionTemplate.update("board.delete", idx);
	}
}
