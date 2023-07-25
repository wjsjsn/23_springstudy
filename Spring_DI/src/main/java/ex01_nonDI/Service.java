package ex01_nonDI;

public class Service {
	// 방법 1
	/*
	OracleDAO oracleDAO = new OracleDAO();
	MySQLDAO mySQLDAO = new MySQLDAO();
	
	// 실제 실행하고자 하는 메서드
	public void biz() {
		oracleDAO.prn();
//		mySQLDAO.prn();
	}
	*/
	
	// 방법 2
	// 클래스를 자료형으로 활용
	private DAO dao;
	public Service() {}
	
	// 생성자 이용
	public Service(DAO dao) {
		this.dao = dao;
	}
	
	// setter 이용	
	public void setDao(DAO dao) {
		this.dao = dao;
	}
	
	public DAO getDao() {
		return dao;
	}
	
	// 실제 실행하고자 하는 메서드
		public void biz() {
			dao.prn();
		} 
}




















