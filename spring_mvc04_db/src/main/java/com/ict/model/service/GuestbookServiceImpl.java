package com.ict.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ict.model.dao.GuestbookDAO;
import com.ict.model.vo.GuestbookVO;

@Service
public class GuestbookServiceImpl implements GuestbookService{
	// dao 호출
	@Autowired
	private GuestbookDAO guestbookDAO;

	@Override
	public List<GuestbookVO> guestbookList() {
		return guestbookDAO.guestbooklist();
	}
	
	@Override
	public int WriteAdd(GuestbookVO gvo) {
		return guestbookDAO.WriteAdd(gvo);
	}
	
	@Override
	public GuestbookVO oneList(String idx) {
		return guestbookDAO.oneList(idx);
	}
	
	@Override
	public int UpdateOk(GuestbookVO gvo) {
		return guestbookDAO.UpdateOk(gvo);
	}
	
	@Override
	public int DeleteOk(String idx) {
		return guestbookDAO.DeleteOk(idx);
	}
}
