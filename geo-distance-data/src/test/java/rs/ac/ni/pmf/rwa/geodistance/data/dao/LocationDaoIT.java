package rs.ac.ni.pmf.rwa.geodistance.data.dao;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;
import rs.ac.ni.pmf.rwa.geodistance.data.TestConfiguration;
import rs.ac.ni.pmf.rwa.geodistance.data.entity.LocationEntity;
import rs.ac.ni.pmf.rwa.geodistance.data.entity.UserEntity;
import rs.ac.ni.pmf.rwa.geodistance.shared.Gender;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@DataJpaTest
@ContextConfiguration(classes = TestConfiguration.class)
public class LocationDaoIT
{
	@Autowired
	private UserDao userDao;

	@Autowired
	private LocationDao locationDao;

	@BeforeEach
	public void initData()
	{
		final UserEntity user = userDao.save(
				UserEntity.builder()
						.firstName("Petar")
						.lastName("Petrovic")
						.email("pera@gmail.com")
						.dob(LocalDate.of(1980, Month.FEBRUARY, 10))
						.gender(Gender.MALE)
						.build());

		locationDao.save(
				LocationEntity.builder()
						.postalCode("AB10 1XG")
						.latitude(57.144156)
						.longitude(-2.114864)
						.editedBy(user)
						.build());

		locationDao.save(
				LocationEntity.builder()
						.postalCode("AB10 6RN")
						.latitude(57.13787)
						.longitude(-2.121487)
						.editedBy(user)
						.build());
	}

	@Test
	public void shouldGetLocations()
	{
		List<LocationEntity> locations = locationDao.findAll();
		assertThat(locations).hasSize(2);

		locations.forEach(System.out::println);

		LocationEntity location = locations.get(0);
		System.out.println(location.getEditedBy().getEmail());
	}
}
