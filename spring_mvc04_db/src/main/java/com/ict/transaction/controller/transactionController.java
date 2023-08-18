package com.ict.transaction.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ict.transaction.model.service.txServiceImpl;
import com.ict.transaction.model.vo.txVO;

@Controller
public class transactionController {
	@Autowired
	private txServiceImpl txServiceImpl;
	
	@RequestMapping("/transaction.do")
	public ModelAndView getTransactionForm() {
		return new ModelAndView("transactionForm");
	}
	
	@RequestMapping("/result4.do")
	public ModelAndView getTransactionOk(@ModelAttribute("txvo") txVO txvo) {
		ModelAndView mv = new ModelAndView("result4");
		try {
			int result = txServiceImpl.getInsert(txvo);
			mv.addObject("res", result);
			return mv;
		} catch (Exception e) {
			e.printStackTrace();
			return new ModelAndView("error");
		}
	}
}
