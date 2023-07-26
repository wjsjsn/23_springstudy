package ex09_db;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository("dao")
public class DAO {
	
	// 실제 사용할 클래스 : SqlSessionTemplate 
	// jsp에서 사용한 SqlSession과 사용법이 같음
	@Autowired
	private SqlSessionTemplate sqlsessionTemplate;
	
	public SqlSessionTemplate getSqlsessionTemplate() {
		return sqlsessionTemplate;
	}

	public void setSqlsessionTemplate(SqlSessionTemplate sqlsessionTemplate) {
		this.sqlsessionTemplate = sqlsessionTemplate;
	}

	public List<VO> getList(){
		List<VO> list = sqlsessionTemplate.selectList("members.list");
		return list;
	}
}
