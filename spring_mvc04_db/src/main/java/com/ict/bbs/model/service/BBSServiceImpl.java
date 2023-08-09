package com.ict.bbs.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ict.bbs.model.dao.BBSDAO;
import com.ict.bbs.model.vo.BBSVO;
import com.ict.bbs.model.vo.CommentVO;

@Service
public class BBSServiceImpl implements BBSService{
		@Autowired
		private BBSDAO bbsdao;
	
//		@Override
//		public List<BBSVO> getBBSList(String page) {
//			return bbsdao.getBBSList(page);
//		}
		
		@Override
		public List<BBSVO> getBBSList(int offset, int limit) {
			return bbsdao.getBBSList(offset, limit);
		}
		
		@Override
		public int getInsert(BBSVO bvo) {
			return bbsdao.getInsert(bvo);
		}

		@Override
		public BBSVO getBBSOnelist(String b_idx) {
			return bbsdao.getBBSOnelist(b_idx);
		}

		@Override
		public int getHitUpdate(String b_idx) {
			return bbsdao.getHitUpdate(b_idx);
		}

		@Override
		public int getCount() {
			return bbsdao.getCount();
		}

		@Override
		public int getBBSUpdateOk(BBSVO bvo) {
			return bbsdao.getBBSUpdateOk(bvo);
		}

		@Override
		public int getBBSDeleteOk(String b_idx) {
			return bbsdao.getBBSDelete(b_idx);
		}

		@Override
		public List<CommentVO> getCommList(String b_idx) {
			return bbsdao.getCommList(b_idx);
		}

		@Override
		public int getCommInsert(CommentVO cvo) {
			return bbsdao.getCommInsert(cvo);
		}
		
		@Override
		public int getCommDelete(String c_idx) {
		return bbsdao.getCommDelete(c_idx);
		}
		
		@Override
		public int getCommCount(String b_idx) {
		return bbsdao.getCommCount(b_idx);
		}
}
