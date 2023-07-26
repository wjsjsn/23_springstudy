package ex10_guestbook_db;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository("guestdao")
public class GuestDAO {

	// 실제 사용할 클래스 : SqlSessionTemplate 
	// jsp에서 사용한 SqlSession과 사용법이 같음
	@Autowired
	private SqlSessionTemplate sqlsessionTemplate;
	
	public List<VO> getList(){
		List<VO> list = sqlsessionTemplate.selectList("guestbook.list");
		return list;
	}
	
	// 정보 DB에 저장(insert, update, delete는 결과가 int)
		public int getInsert(VO vo) {
			int res = sqlsessionTemplate.insert("guestbook.insert", vo);
			return res;
		}
		
		// primary key를 이용해 하나의 상세 정보 가져오기
		public VO getOneList(String idx) {
			VO vo = sqlsessionTemplate.selectOne("guestbook.onelist", idx);
			return vo;
		}
		
		// 업데이트
		public int getUpdate(VO vo) {
			int result = sqlsessionTemplate.update("guestbook.update", vo);
			return result;
		}
		
		// 삭제
		public int getDelete(String idx) {
			int result = sqlsessionTemplate.delete("guestbook.delete", idx);
			return result;
		}
}
