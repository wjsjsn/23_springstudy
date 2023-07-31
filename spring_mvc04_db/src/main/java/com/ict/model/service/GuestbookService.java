package com.ict.model.service;

import java.util.List;

import com.ict.model.vo.GuestbookVO;

public interface GuestbookService {

	// 전체보기
	List<GuestbookVO> guestbookList();
	
	// 글쓰기
	int WriteAdd(GuestbookVO gvo);
	
	// 상세보기
	GuestbookVO oneList(String idx);
	
	// 글 수정
	int UpdateOk(GuestbookVO gvo);
	
	// 글 삭제
	int DeleteOk(String idx);
}
