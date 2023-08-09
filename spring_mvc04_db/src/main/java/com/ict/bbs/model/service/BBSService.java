package com.ict.bbs.model.service;

import java.util.List;

import com.ict.bbs.model.vo.BBSVO;
import com.ict.bbs.model.vo.CommentVO;

public interface BBSService {
	
	// 페이지 처리
	int getCount();
	
	// 전체보기
//	List<BBSVO> getBBSList(String page);
	List<BBSVO> getBBSList(int offset, int limit);
	
	// 삽입
	int getInsert(BBSVO bvo);
	
	// 상세보기
	BBSVO getBBSOnelist(String b_idx);
	
	// 조회수 업데이트
	int getHitUpdate(String b_idx);
		
	// 글 업데이트
	int getBBSUpdateOk(BBSVO bvo);
	
	// 글 삭제
	int getBBSDeleteOk(String b_idx);
	
	// 댓글 가져오기
	List<CommentVO> getCommList(String b_idx);
	
	// 댓글 쓰기
	int getCommInsert(CommentVO cvo);
	
	// 댓글 삭제
	int getCommDelete(String c_idx);
	
	// 댓글 수 보이기
	int getCommCount(String b_idx);
}
