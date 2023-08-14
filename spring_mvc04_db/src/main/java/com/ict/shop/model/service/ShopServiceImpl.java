package com.ict.shop.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ict.shop.model.dao.ShopDAO;
import com.ict.shop.model.vo.CartVO;
import com.ict.shop.model.vo.ShopVO;

@Service
public class ShopServiceImpl implements ShopService{
	@Autowired
	private ShopDAO shopDAO;
	
	@Override
	public List<ShopVO> getShopList(String category) throws Exception {
		return shopDAO.getShopList(category);
	}

	@Override
	public ShopVO getShopOneList(String idx) throws Exception {
		return shopDAO.getShopOneList(idx);
	}

	@Override
	public List<CartVO> getCartList(String m_id) throws Exception {
		return shopDAO.getCartList(m_id);
	}

	@Override
	public ShopVO getProductOneList(String idx) throws Exception {
		return shopDAO.getProductOneList(idx);
	}

	@Override
	public CartVO getCartOneList(String m_id, String p_num) throws Exception {
		return shopDAO.getCartOneList(m_id, p_num);
	}

	@Override
	public int getCartInsert(CartVO cavo) throws Exception {
		return shopDAO.getCartInsert(cavo);
	}

	@Override
	public int getCartUpdate(CartVO cavo) throws Exception {
		return shopDAO.getCartUpdate(cavo);
	}

	@Override
	public int getCartEdit(CartVO cavo) throws Exception {
		return shopDAO.getCartEdit(cavo);
	}

	@Override
	public int getCartDelete(String idx) throws Exception {
		return shopDAO.getCartDelete(idx);
	}

	@Override
	public int getProductInsert(ShopVO svo) throws Exception {
		return shopDAO.getProductInsert(svo);
	}
}
