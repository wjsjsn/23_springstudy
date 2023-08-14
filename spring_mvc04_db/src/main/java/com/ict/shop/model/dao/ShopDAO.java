package com.ict.shop.model.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ict.shop.model.vo.CartVO;
import com.ict.shop.model.vo.ShopVO;

@Repository
public class ShopDAO {
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	public List<ShopVO> getShopList(String category) throws Exception {
		return sqlSessionTemplate.selectList("shop.s_list", category);
	}

	public ShopVO getShopOneList(String idx) throws Exception {
		return sqlSessionTemplate.selectOne("shop.s_onelist", idx);
	}

	public List<CartVO> getCartList(String m_id) throws Exception {
		return sqlSessionTemplate.selectList("shop.c_list", m_id);
	}

	public ShopVO getProductOneList(String idx) throws Exception {
		return sqlSessionTemplate.selectOne("shop.s_onelist", idx);
	}

	public CartVO getCartOneList(String m_id, String p_num) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		map.put("m_id", m_id);
		map.put("p_num", p_num);
		return sqlSessionTemplate.selectOne("shop.c_onelist", map);
	}

	public int getCartInsert(CartVO cavo) throws Exception {
		return sqlSessionTemplate.insert("shop.c_insert", cavo);
	}

	public int getCartUpdate(CartVO cavo) throws Exception {
		return sqlSessionTemplate.update("shop.c_update", cavo);
	}

	public int getCartEdit(CartVO cavo) throws Exception {
		return sqlSessionTemplate.update("shop.c_edit", cavo);
	}

	public int getCartDelete(String idx) throws Exception {
		return sqlSessionTemplate.delete("shop.c_delete", idx);
	}

	public int getProductInsert(ShopVO svo) throws Exception {
		return sqlSessionTemplate.insert("shop.p_insert", svo);
	}
}
