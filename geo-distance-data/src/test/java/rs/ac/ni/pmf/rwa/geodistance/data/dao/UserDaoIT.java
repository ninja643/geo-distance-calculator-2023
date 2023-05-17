package rs.ac.ni.pmf.rwa.geodistance.data.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;
import rs.ac.ni.pmf.rwa.geodistance.data.TestConfiguration;
import rs.ac.ni.pmf.rwa.geodistance.data.dao.UserDao;

@DataJpaTest
@ContextConfiguration(classes = TestConfiguration.class)
class UserDaoIT
{
	@Autowired
	private UserDao userDao;

	@Test
	public void shouldGetUsers()
	{
		userDao.findAll().forEach(System.out::println);
	}
}
