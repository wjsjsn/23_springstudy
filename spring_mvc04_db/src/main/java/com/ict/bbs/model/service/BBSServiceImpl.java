package com.ict.bbs.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ict.bbs.model.dao.BBSDAO;
import com.ict.bbs.model.vo.BBSVO;

@Service
public class BBSServiceImpl implements BBSService{
		@Autowired
		private BBSDAO bbsdao;

		@Override
		public List<BBSVO> getBBSList(String page) {
			return bbsdao.getBBSList(page);
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
		
}
