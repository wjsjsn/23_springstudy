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

	// 서비스에서 DB 처리하는 메서드를 모두 받아줘야 함
	// guestbook 리스트
	public List<GuestbookVO> guestbooklist() {
		return sqlSessionTemplate.selectList("guestbook.list");
	}

	// 글쓰기
	public int WriteAdd(GuestbookVO gvo) {
		return sqlSessionTemplate.insert("guestbook.insert", gvo);
	}

	// 글 상세보기
	public GuestbookVO oneList(String idx) {
		return sqlSessionTemplate.selectOne("guestbook.onelist", idx);
	}

	// 글 수정
	public int UpdateOk(GuestbookVO gvo) {
		return sqlSessionTemplate.update("guestbook.update", gvo);
	}

	// 글 삭제
	public int DeleteOk(String idx) {
		return sqlSessionTemplate.delete("guestbook.delete", idx);
	}
}
