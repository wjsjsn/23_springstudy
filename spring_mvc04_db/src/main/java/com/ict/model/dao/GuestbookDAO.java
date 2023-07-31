package com.ict.model.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ict.model.vo.GuestbookVO;

@Repository
public class GuestbookDAO {
	
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;

	public SqlSessionTemplate getSqlSessionTemplate() {
		return sqlSessionTemplate;
	}

	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		this.sqlSessionTemplate = sqlSessionTemplate;
	}
	
	// guestbook 리스트
	public List<GuestbookVO> guestbooklist(){
		List<GuestbookVO> list = sqlSessionTemplate.selectList("guestbook.list");
		return list;
	}
	
	// 글쓰기
	public int WriteAdd(GuestbookVO gvo) {
		int result = sqlSessionTemplate.insert("guestbook.insert", gvo);
		return result;
	}
	
	// 글 상세보기
	public GuestbookVO oneList(String idx) {
		GuestbookVO gvo = sqlSessionTemplate.selectOne("guestbook.onelist", idx);
		return gvo;
	}
	
	// 글 수정
	public int UpdateOk(GuestbookVO gvo) {
		int result = sqlSessionTemplate.update("guestbook.update", gvo);
		return result;
	}
	
	// 글 삭제
	public int DeleteOk(String idx) {
		int result = sqlSessionTemplate.delete("guestbook.delete", idx);
		return result;
	}
}
