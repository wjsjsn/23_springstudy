package com.ict.member.model.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ict.member.model.vo.MemberVO;

@Repository
public class MemberDAO {
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	// 회원가입
	public int getMemberAdd(MemberVO mvo2) {
		return sqlSessionTemplate.insert("member.join", mvo2);
	}
	// 로그인
	// 1. pw 검사
	public String getMemberPw(String m_id) {
		return sqlSessionTemplate.selectOne("member.getPw", m_id);
	}
	
	// 아이디 찾기
	
	// 비밀번호 찾기
}
