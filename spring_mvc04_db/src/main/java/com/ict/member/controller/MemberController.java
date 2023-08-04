package com.ict.member.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ict.member.model.service.MemberService;
import com.ict.member.model.vo.MemberVO;

@Controller
public class MemberController {
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@GetMapping("/member_reg.do")
	public ModelAndView getMemberJoinForm() {
		return new ModelAndView("members/addForm");
	}
	
	@PostMapping("/member_add.do")
	public ModelAndView getMemberAdd(MemberVO mvo2) {
		ModelAndView mv = new ModelAndView("redirect:/");
		
		// 패스워드 암호화
		mvo2.setM_pw(passwordEncoder.encode(mvo2.getM_pw()));
		memberService.getMemberAdd(mvo2);
		return mv;
	}
	
	@PostMapping("/member_login.do")
	public ModelAndView getMemberLogin(MemberVO mvo2, HttpSession session) {
		ModelAndView mv = new ModelAndView("redirect:/");
		
		// 입력한 id의 pw를 db에서 가져와서 일치한지 확인
		// id로 pw 가져오기
		String pw = memberService.getMemberPw(mvo2.getM_id());
		
		if(!passwordEncoder.matches(mvo2.getM_pw(), pw)) {
			session.setAttribute("loginChk", "fail");
			return mv;
		}else {			
			session.setAttribute("loginChk", "ok");
			session.setAttribute("m_id", mvo2.getM_id());
			return mv;
		}
	}
}
