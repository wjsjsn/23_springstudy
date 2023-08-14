package com.ict.shop.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ict.common.paging;
import com.ict.member.model.vo.MemberVO;
import com.ict.shop.model.service.ShopService;
import com.ict.shop.model.vo.CartVO;
import com.ict.shop.model.vo.ShopVO;

@Controller
public class ShopController {
	@Autowired
	private ShopService shopService;
	
	@Autowired
	paging paging;
	
	@GetMapping("/shop_list.do")
	public ModelAndView getShopList(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("shop/product_list");
		String category = request.getParameter("category");
		
		// 리스트에 처음 오면 category가 null임
		// 다음부터는 list에 올 때 category를 지정
		if(category == null || category == "") {
			category = "ele002";
		}
	
		try {
			List<ShopVO> list = shopService.getShopList(category);
			mv.addObject("list", list);
			return mv;
		} catch (Exception e) {
			e.printStackTrace();
			return new ModelAndView("shop/error");
		}
	}
	
	@GetMapping("/shop_onelist.do")
	public ModelAndView getShopOneList(@RequestParam("idx") String idx) {
		ModelAndView mv = new ModelAndView("shop/product_content");
		try {
			ShopVO svo = shopService.getShopOneList(idx);
			mv.addObject("svo", svo);
			return mv;
		} catch (Exception e) {
			e.printStackTrace();
			return new ModelAndView("shop/error");
		}
	}
	
	@GetMapping("/shop_addcart.do")
	public ModelAndView getShopAddCart(@ModelAttribute("idx") String idx, HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("redirect:/shop_onelist.do");
		try {
			// 제품 정보와 로그인한 정보를 가지고 cart에 있는지 확인
			// 로그인 정보 가져오기
			String m_id = (String) request.getSession().getAttribute("m_id");
			
			// idx를 이용해 제품 정보 가져오기
			ShopVO svo = shopService.getProductOneList(idx);
			
			// 제품 정보와 로그인 정보를 가지고 cart 정보를 구함
			CartVO cavo = shopService.getCartOneList(m_id, svo.getP_num());
			
			if(cavo == null) {
				// 카트 정보에 제품 정보가 없으면 카트에 추가
				CartVO cavo2 = new CartVO();
				cavo2.setP_num(svo.getP_num());
				cavo2.setP_name(svo.getP_name());
				cavo2.setP_price(String.valueOf(svo.getP_price())); 
				cavo2.setP_saleprice(String.valueOf(svo.getP_saleprice()));
				cavo2.setM_id(m_id);
				shopService.getCartInsert(cavo2);
			}else {
				// 카트 정보에 제품 정보가 있으면 제품 개수 1 추가
				shopService.getCartUpdate(cavo);
			}
			return mv;
		} catch (Exception e) {
			e.printStackTrace();
			return new ModelAndView("shop/error");
		}
	}
	
	@GetMapping("/shop_showcart.do")
	public ModelAndView getShopShowCart(@ModelAttribute("idx") String idx, HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("shop/cartList");
		try {
			// 로그인 정보 가져오기
			String m_id = (String) request.getSession().getAttribute("m_id");
			List<CartVO> list = shopService.getCartList(m_id);
		
			mv.addObject("list", list);
			return mv;
		} catch (Exception e) {
			e.printStackTrace();
			return new ModelAndView("shop/error");
		}
	}
	
	@PostMapping("/shop_edit.do")
	public ModelAndView getCartEdit(CartVO cavo) {
		try {
			shopService.getCartEdit(cavo);
			return new ModelAndView("redirect:/shop_showcart.do");
		} catch (Exception e) {
			e.printStackTrace();
			return new ModelAndView("shop/error");
		}
	}
	
	@GetMapping("/shop_delete.do")
	public ModelAndView getCartDelete(@RequestParam("idx") String idx) {
		try {
			shopService.getCartDelete(idx);
			return new ModelAndView("redirect:/shop_showcart.do");
		} catch (Exception e) {
			e.printStackTrace();
			return new ModelAndView("shop/error");
		}
	}
	
	@GetMapping("/shop_product_insertForm.do")
	public ModelAndView getInsertForm() {
		return new ModelAndView("shop/admin");
	}
	
}
