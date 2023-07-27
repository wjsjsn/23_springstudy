package com.ict.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

// 어노테이션이 아닌 컨트롤러는 반드시 Controller를 상속받아야 함
public class indexController implements Controller{
	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("result");
		
		// 일처리 = DB 처리, 비지니스로직 처리
		// 일처리 후 데이터 저장
		mv.addObject("name", "홍길동");
		request.setAttribute("age", 17);
		request.getSession().setAttribute("addr", "서울시 마포구 신수동");
		
		return mv;
	}
}
