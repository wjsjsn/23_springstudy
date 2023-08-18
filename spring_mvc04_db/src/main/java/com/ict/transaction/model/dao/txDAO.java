package com.ict.transaction.model.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.ict.transaction.model.vo.txVO;

@Repository
public class txDAO {
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	@Autowired
	private DataSourceTransactionManager transactionManager;
	
	public int getInsert(txVO txvo) throws Exception{
		// 트랜잭션 이전
//		int result = 0;
//		result += sqlSessionTemplate.insert("card.c_insert", txvo);
//		result += sqlSessionTemplate.insert("card.t_insert", txvo);
//		return result;
		
		// 트랜잭션 처리
		int result = 0;
		TransactionDefinition def = new DefaultTransactionDefinition();
		TransactionStatus status = transactionManager.getTransaction(def);
		try {
			result += sqlSessionTemplate.insert("card.c_insert", txvo);
			result += sqlSessionTemplate.insert("card.t_insert", txvo);
			transactionManager.commit(status);
			System.out.println("결제 성공, 발권 성공");
		} catch (Exception e) {
			transactionManager.rollback(status);
			System.out.println("오류 발생, 결제 취소, 발권 취소");
		}
		return result;
	}
}
