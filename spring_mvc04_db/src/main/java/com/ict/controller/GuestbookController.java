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
	// 일처리(DB)가 있으면 서비스로 가기
	@Autowired
	private GuestbookService guestbookService;

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
	
	// idx는 onelist.jsp에도 사용하기 때문에 넘겨야함
	@GetMapping("/guestbook_onelist.do")
		public ModelAndView oneList(@ModelAttribute("idx")String idx) {
		ModelAndView mv = new ModelAndView("guestbook/onelist");
		GuestbookVO gvo = guestbookService.oneList(idx);
		mv.addObject("gvo", gvo);
		return mv;
		}
	
	@PostMapping("/guestbook_update.do")
	public ModelAndView UpdateAdd(String idx) {
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
	public ModelAndView delete(@ModelAttribute("idx")String idx) {
		ModelAndView mv = new ModelAndView("guestbook/delete");
		// jsp에서 실제 삭제할 때 비밀번호를 검사하기 위해서 oneList() 실행
		GuestbookVO gvo = guestbookService.oneList(idx);
		mv.addObject("gvo", gvo);
		return mv;
	}
	
	@PostMapping("/guestbook_deleteOk.do")
	public ModelAndView DeleteOk(String idx) {
		ModelAndView mv = new ModelAndView("redirect:/guestbook_list.do");
		int result = guestbookService.DeleteOk(idx);
		mv.addObject("result", result);
		return mv;
	}
}
