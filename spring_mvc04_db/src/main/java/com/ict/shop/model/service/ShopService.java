package com.ict.shop.model.service;

import java.util.List;

import com.ict.shop.model.vo.CartVO;
import com.ict.shop.model.vo.ShopVO;

public interface ShopService {
	
	// 전체보기
	public List<ShopVO> getShopList(String category) throws Exception;
	
	// 상세보기
	public ShopVO getShopOneList(String idx) throws Exception;
	
	// 장바구니 보기
	public List<CartVO> getCartList(String m_id) throws Exception;
	
	// 물건 상세보기
	public ShopVO getProductOneList(String idx) throws Exception;
	
	// 장바구니 상세보기
	public CartVO getCartOneList(String m_id, String p_num) throws Exception;
	
	// 장바구니 넣기
	public int getCartInsert(CartVO cavo) throws Exception;
	
	// 장바구니 업데이트
	public int getCartUpdate(CartVO cavo) throws Exception;
	
	// 장바구니 물건 개수 업데이트
	public int getCartEdit(CartVO cavo) throws Exception;
	
	// 장바구니 삭제
	public int getCartDelete(String idx) throws Exception;
	
	// 물건 넣기
	public int getProductInsert(ShopVO svo) throws Exception;
}
