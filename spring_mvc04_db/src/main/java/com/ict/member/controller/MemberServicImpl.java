package com.ict.member.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ict.member.model.dao.MemberDAO;
import com.ict.member.model.service.MemberService;
import com.ict.member.model.vo.MemberVO;

@Service
public class MemberServicImpl implements MemberService {
	// dao 호출
	@Autowired
	private MemberDAO memberDAO;
	
	// 회원가입
	@Override
	public int getMemberAdd(MemberVO mvo2) {
		return memberDAO.getMemberAdd(mvo2);
	}
	
	// pw 검사
	@Override
	public String getMemberPw(String m_id) {
		return memberDAO.getMemberPw(m_id);
	}
}
