package com.ict.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ict.model.dao.Guestbook2DAO;
import com.ict.model.dao.GuestbookDAO;
import com.ict.model.vo.Guestbook2VO;
import com.ict.model.vo.GuestbookVO;

@Service
public class Guestbook2ServiceImpl implements Guestbook2Service{
	// dao 호출
	@Autowired
	private Guestbook2DAO guestbook2DAO;
	
	// 리스트
	@Override
	public List<Guestbook2VO> guestbookList() {
		return guestbook2DAO.guestbooklist();
	}
	
	// 글쓰기
	@Override
	public int WriteAdd(Guestbook2VO gvo) {
		return guestbook2DAO.WriteAdd(gvo);
	}
	
	
	// 상세보기
	@Override
	public Guestbook2VO oneList(String idx) {
		return guestbook2DAO.oneList(idx);
	}
	
	
	// 수정
	@Override
	public int UpdateOk(Guestbook2VO gvo) {
		return guestbook2DAO.UpdateOk(gvo);
	}
	
	
	// 삭제
	@Override
	public int DeleteOk(String idx) {
		return guestbook2DAO.DeleteOk(idx);
	}
}
