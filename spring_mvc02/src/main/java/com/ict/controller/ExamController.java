package com.ict.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ExamController {
	
	// a링크는 get방식
	@GetMapping("/exam01.do")
	public ModelAndView exam01(HttpServletRequest reqeust, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView("output");
		mv.addObject("name", "오천사");
		mv.addObject("age", 1004);
		mv.addObject("addr", "천국");
		
		return mv;
	}
	@GetMapping("/exam02.do")
	public ModelAndView exam02(HttpServletRequest reqeust, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView("admin/output");
		List<String> dogName = new ArrayList<String>();
		dogName.add("바둑이");
		dogName.add("일땡이");
		dogName.add("이땡이");
		dogName.add("삼땡이");
		dogName.add("사땡이");
		mv.addObject("dogName", dogName);
		
		return mv;	
	}
}
