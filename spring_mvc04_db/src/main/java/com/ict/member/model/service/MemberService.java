package com.ict.member.model.service;

import com.ict.member.model.vo.MemberVO;

public interface MemberService {
	
	// 회원가입
	int getMemberAdd(MemberVO mvo2);
	
	// 로그인
	// 1. id로 pw가 맞는지 검사
	String getMemberPw(String m_id); 
	
	// 아이디 찾기
	
	// 비밀번호 찾기
}
