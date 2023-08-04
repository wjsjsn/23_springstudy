package com.ict.model.service;
 
import java.util.List;

import com.ict.model.vo.Guestbook2VO;

public interface Guestbook2Service {

	// 전체보기
	List<Guestbook2VO> guestbookList();
	
	// 글쓰기
	int WriteAdd(Guestbook2VO gvo);
	
	// 상세보기
	Guestbook2VO oneList(String idx);
	
	// 글 수정
	int UpdateOk(Guestbook2VO gvo);
	
	// 글 삭제
	int DeleteOk(String idx);
}
