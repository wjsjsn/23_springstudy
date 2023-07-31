package com.ict.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ict.model.service.GuestbookService;
import com.ict.model.vo.GuestbookVO;

@Controller
public class GuestbookController {
	@Autowired
	private GuestbookService guestbookService;

	public GuestbookService getGuestbookService() {
		return guestbookService;
	}

	public void setGuestbookService(GuestbookService guestbookService) {
		this.guestbookService = guestbookService;
	}
	
	@GetMapping("/guestbook_list.do")
	public ModelAndView getGuestList() {
		ModelAndView mv = new ModelAndView("guestbook/list");
		List<GuestbookVO> list = guestbookService.guestbookList();
		mv.addObject("list", list);
		return mv;
	}
	
	@GetMapping("/guestbook_write.do")
	public ModelAndView Write() {
		return new ModelAndView("guestbook/write");
	}
	
	@PostMapping("/guestbook_writeOk.do")
	public ModelAndView WriteOk(GuestbookVO gvo) {
		ModelAndView mv = new ModelAndView("redirect:/guestbook_list.do");
		int result = guestbookService.WriteAdd(gvo);
		mv.addObject("result", result);
		return mv;
	}
	
	@GetMapping("/guestbook_onelist.do")
		public ModelAndView oneList(@RequestParam("idx")String idx) {
		ModelAndView mv = new ModelAndView("guestbook/onelist");
		GuestbookVO gvo = guestbookService.oneList(idx);
		mv.addObject("gvo", gvo);
		return mv;
		}
	
	@PostMapping("/guestbook_update.do")
	public ModelAndView UpdateAdd(@RequestParam("idx")String idx) {
		System.out.println(idx);
		ModelAndView mv = new ModelAndView("guestbook/update");
		GuestbookVO gvo = guestbookService.oneList(idx);
		mv.addObject("gvo", gvo);
		return mv;
	}
	
	@PostMapping("/guestbook_updateOk.do")
	public ModelAndView UpdateOk(GuestbookVO gvo) {
		ModelAndView mv = new ModelAndView("redirect:/guestbook_list.do");
		int result = guestbookService.UpdateOk(gvo);
		mv.addObject("result", result);
		return mv;
	}
	
	@PostMapping("/guestbook_delete.do")
	public ModelAndView delete(@RequestParam("idx")String idx) {
		ModelAndView mv = new ModelAndView("guestbook/delete");
		GuestbookVO gvo = guestbookService.oneList(idx);
		mv.addObject("gvo", gvo);
		return mv;
	}
	
	@PostMapping("/guestbook_deleteOk.do")
	public ModelAndView DeleteOk(@RequestParam("idx")String idx) {
		System.out.println(idx);
		ModelAndView mv = new ModelAndView("redirect:/guestbook_list.do");
		int result = guestbookService.DeleteOk(idx);
		mv.addObject("result", result);
		return mv;
	}
}
