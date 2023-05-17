package rs.ac.ni.pmf.rwa.geodistance.data.dao;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;
import rs.ac.ni.pmf.rwa.geodistance.data.TestConfiguration;
import rs.ac.ni.pmf.rwa.geodistance.data.entity.UserEntity;
import rs.ac.ni.pmf.rwa.geodistance.shared.Gender;

import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

@DataJpaTest
@ContextConfiguration(classes = TestConfiguration.class)
class UserDaoIT
{
	@Autowired
	private UserDao userDao;

	private static final List<UserEntity> DEFAULT_USERS = Arrays.asList(
			new UserEntity(null, "Petar", "Petrovic", "pera@gmail.com", LocalDate.of(1980, Month.FEBRUARY, 10),
						   Gender.MALE),

			new UserEntity(null, "Mira", "Petrovic", "mira@gmail.com", LocalDate.of(1990, Month.APRIL, 15),
						   Gender.FEMALE),

			new UserEntity(null, "Zika", "Petrovic", "zika@gmail.com", LocalDate.of(1986, Month.FEBRUARY, 10),
						   Gender.MALE),

			new UserEntity(null, "Mira", "Mitrovic", "mira2@gmail.com", LocalDate.of(1991, Month.APRIL, 15),
						   Gender.FEMALE));

	@BeforeEach
	public void initializeDatabase()
	{
		userDao.saveAll(DEFAULT_USERS);
	}

	@Test
	public void shouldGetUsers()
	{
		final List<UserEntity> users = userDao.findAll();
		assertThat(users).hasSize(DEFAULT_USERS.size());
	}
}
