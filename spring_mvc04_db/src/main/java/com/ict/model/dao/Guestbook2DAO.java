package com.ict.model.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ict.model.vo.Guestbook2VO;

@Repository 
public class Guestbook2DAO {

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;

	// 서비스에서 DB 처리하는 메서드를 모두 받아줘야 함
	// guestbook 리스트
	public List<Guestbook2VO> guestbooklist() {
		return sqlSessionTemplate.selectList("guestbook2.list");
	}

	// 글쓰기
	public int WriteAdd(Guestbook2VO gvo) {
		return sqlSessionTemplate.insert("guestbook2.insert", gvo);
	}

	// 글 상세보기
	public Guestbook2VO oneList(String idx) {
		return sqlSessionTemplate.selectOne("guestbook2.onelist", idx);
	}

	// 글 수정
	public int UpdateOk(Guestbook2VO gvo) {
		return sqlSessionTemplate.update("guestbook2.update", gvo);
	}

	// 글 삭제
	public int DeleteOk(String idx) {
		return sqlSessionTemplate.delete("guestbook2.delete", idx);
	}
}
