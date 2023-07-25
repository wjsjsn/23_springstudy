package ex05_Annotation;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
public class MySQLDAO implements DAO{
	public MySQLDAO() {
		System.out.println("MySQL 생성자");
	}
	
	@Override
	public void prn() {
		System.out.println("MySQL 메서드");
	}
}
