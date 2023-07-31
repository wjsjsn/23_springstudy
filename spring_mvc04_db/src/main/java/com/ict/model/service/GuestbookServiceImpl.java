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

	public GuestbookDAO getGuestbookDAO() {
		return guestbookDAO;
	}

	public void setGuestbookDAO(GuestbookDAO guestbookDAO) {
		this.guestbookDAO = guestbookDAO;
	}
	
	@Override
	public List<GuestbookVO> guestbookList() {
		List<GuestbookVO> list = guestbookDAO.guestbooklist();
		return list;
	}
	
	@Override
	public int WriteAdd(GuestbookVO gvo) {
		int result = guestbookDAO.WriteAdd(gvo);
		return result;
	}
	
	@Override
	public GuestbookVO oneList(String idx) {
		GuestbookVO gvo = guestbookDAO.oneList(idx);
		return gvo;
	}
	
	@Override
	public int UpdateOk(GuestbookVO gvo) {
		int result = guestbookDAO.UpdateOk(gvo);
		return result;
	}
	
	@Override
	public int DeleteOk(String idx) {
		int result = guestbookDAO.DeleteOk(idx);
		return result;
	}
}
