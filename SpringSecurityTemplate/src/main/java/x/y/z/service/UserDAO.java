package x.y.z.service;

import java.util.Map;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

@Repository("userDAO")
public class UserDAO {

	@Resource(name="sqlSession")
	private SqlSessionTemplate sqlSession;


	public Map getUser(String username) {
		System.out.println("username:" +username);
		return sqlSession.selectOne("User.getUser", username);
	}
	
	
	
}
