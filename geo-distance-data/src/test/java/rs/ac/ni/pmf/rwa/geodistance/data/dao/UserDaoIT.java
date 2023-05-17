package rs.ac.ni.pmf.rwa.geodistance.data.dao;

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

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ContextConfiguration(classes = TestConfiguration.class)
class UserDaoIT
{
	@Autowired
	private UserDao userDao;

	private static final List<UserEntity> DEFAULT_USERS = Arrays.asList(
			UserEntity.builder()
					.firstName("Petar")
					.lastName("Petrovic")
					.email("pera@gmail.com")
					.dob(LocalDate.of(1980, Month.FEBRUARY, 10))
					.gender(Gender.MALE)
					.build(),
			UserEntity.builder()
					.firstName("Mira")
					.lastName("Petrovic")
					.email("mira@gmail.com")
					.dob(LocalDate.of(1990, Month.APRIL, 15))
					.gender(Gender.FEMALE)
					.build(),
			UserEntity.builder()
					.firstName("Zika")
					.lastName("Petrovic")
					.email("zika@gmail.com")
					.dob(LocalDate.of(1986, Month.FEBRUARY, 10))
					.gender(Gender.MALE)
					.build(),
			UserEntity.builder()
					.firstName("Mika")
					.lastName("Mikic")
					.email("mika@gmail.com")
					.dob(LocalDate.of(1980, Month.FEBRUARY, 10))
					.gender(Gender.MALE)
					.build(),
			UserEntity.builder()
					.firstName("Mira")
					.lastName("Mitrovic")
					.email("mira2@gmail.com")
					.dob(LocalDate.of(1991, Month.APRIL, 15))
					.gender(Gender.FEMALE)
					.build());


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

	@Test
	public void shouldSearchUsers()
	{
		List<UserEntity> users = userDao.findByFirstNameStartsWith("Mi");
		assertThat(users).hasSize(3);
	}

	@Test
	public void shouldGetUserById()
	{
		final UserEntity user = userDao.findById(2).orElseThrow();
		System.out.println();
		System.out.println(user);
		System.out.println();
	}
}
