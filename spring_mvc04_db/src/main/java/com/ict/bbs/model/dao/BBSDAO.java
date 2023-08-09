package com.ict.bbs.model.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ict.bbs.model.vo.BBSVO;
import com.ict.bbs.model.vo.CommentVO;

@Repository
public class BBSDAO {
	
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	// 페이지 처리
	public int getCount() {
		return sqlSessionTemplate.selectOne("bbs.count");
	}
	
//	public List<BBSVO> getBBSList(String page){
//		int nowPage = Integer.parseInt(page);
//		int limit = 5;
//		int offset = limit * (nowPage - 1);
//		
//		Map<String, Integer> map = new HashMap<String, Integer>();
//		map.put("limit", limit);
//		map.put("offset", offset);
//		
//		return sqlSessionTemplate.selectList("bbs.list", map);
//	}
	
	public List<BBSVO> getBBSList(int offset, int limit){
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("limit", limit);
		map.put("offset", offset);
		
		return sqlSessionTemplate.selectList("bbs.list", map);
	}
	
	public int getInsert(BBSVO bvo) {
		return sqlSessionTemplate.insert("bbs.insert", bvo);
	}
	
	public BBSVO getBBSOnelist(String b_idx) {
		return sqlSessionTemplate.selectOne("bbs.onelist", b_idx);
	}
	
	public int getHitUpdate(String b_idx) {
		return sqlSessionTemplate.update("bbs.hit", b_idx);
	}
	
	public int getBBSUpdateOk(BBSVO bvo) {
		return sqlSessionTemplate.update("bbs.update", bvo);
	}
	
	public int getBBSDelete(String b_idx) {
		return sqlSessionTemplate.update("bbs.delete", b_idx);
	}
	
	public List<CommentVO> getCommList(String b_idx){
		return sqlSessionTemplate.selectList("bbs.commlist", b_idx);
	}
	
	public int getCommInsert(CommentVO cvo) {
		return sqlSessionTemplate.insert("bbs.commInsert", cvo);
	}
	
	public int getCommDelete(String c_idx) {
		return sqlSessionTemplate.delete("bbs.commDelete", c_idx);
	}
	
	public int getCommCount(String b_idx) {
		return sqlSessionTemplate.selectOne("bbs.commCount", b_idx);
	}
}
