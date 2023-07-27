package com.ict.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class examController implements Controller {
	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mv = new ModelAndView("result");
		
		String [] dogName = {"초복이", "중복이", "말복이", "바둑이"};
		mv.addObject("dogName", dogName);
		
		// List
		List<String> list = new ArrayList<String>();
		list.add("해리포터");
		list.add("헤르미온느");
		list.add("론");
		list.add("덤블도어");
		mv.addObject("list", list);
		
		return mv;
	}
}
