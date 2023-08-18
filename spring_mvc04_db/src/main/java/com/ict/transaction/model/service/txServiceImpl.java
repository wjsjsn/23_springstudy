package com.ict.transaction.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ict.transaction.model.vo.txVO;

@Service
public class txServiceImpl {
	@Autowired
	private com.ict.transaction.model.dao.txDAO txDAO;
	
	public int getInsert(txVO txvo) throws Exception{
		return txDAO.getInsert(txvo);
	}
	
}
