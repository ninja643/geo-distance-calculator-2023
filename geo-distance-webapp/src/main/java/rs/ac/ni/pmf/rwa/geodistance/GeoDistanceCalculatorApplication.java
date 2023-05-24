package rs.ac.ni.pmf.rwa.geodistance;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import rs.ac.ni.pmf.rwa.geodistance.data.dao.LocationDao;
import rs.ac.ni.pmf.rwa.geodistance.data.dao.UserDao;
import rs.ac.ni.pmf.rwa.geodistance.data.entity.LocationEntity;
import rs.ac.ni.pmf.rwa.geodistance.data.entity.UserEntity;


@SpringBootApplication(exclude = {
//		DataSourceAutoConfiguration.class
})
@EnableTransactionManagement
public class GeoDistanceCalculatorApplication
{

	public static void main(String[] args)
	{
		SpringApplication.run(GeoDistanceCalculatorApplication.class, args);
	}

	@Bean
	public CommandLineRunner afterInit(final UserDao userDao, final LocationDao locationDao)
	{
		return args -> {
			initializeData(userDao, locationDao);

//			viewData(userDao);
		};
	}
//
//	@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
//	public void viewData(final UserDao userDao)
//	{
//		final UserEntity user = userDao.findById(1).orElseThrow();
//		System.out.println(user.getFullName());
//	}

	public void initializeData(final UserDao userDao, final LocationDao locationDao)
	{
		userDao.save(UserEntity.builder().firstName("Pera").lastName("Peric").build());

		final UserEntity user = userDao.findById(1).orElseThrow();
//		System.out.println(user);
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
}
