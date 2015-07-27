package x.y.z.service;

import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring/root-context.xml"})
public class UserDAOTest {

	@Resource(name="userDAO")
	UserDAO userDAO;

	@Test
	public void test() {
		Map rs = userDAO.getUser("user01");
		System.out.println(rs);
	}

}
