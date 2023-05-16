package rs.ac.ni.pmf.rwa.geodistance;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import rs.ac.ni.pmf.rwa.geodistance.data.dao.UserDao;
import rs.ac.ni.pmf.rwa.geodistance.data.entity.UserEntity;
import rs.ac.ni.pmf.rwa.geodistance.shared.Gender;

import java.time.LocalDate;
import java.time.Month;

@SpringBootApplication(exclude = {
//		DataSourceAutoConfiguration.class
})
public class GeoDistanceCalculatorApplication
{

	public static void main(String[] args)
	{
		SpringApplication.run(GeoDistanceCalculatorApplication.class, args);
	}

	@Bean
	public CommandLineRunner startUp(final UserDao userDao)
	{
		return args -> {
			System.out.println("Adding users to database");

			userDao.save(new UserEntity(
					null,
					"Petar",
					"Petrovic",
					"pera@gmail.com",
					LocalDate.of(1980, Month.FEBRUARY, 10),
					Gender.MALE));

			userDao.save(new UserEntity(
					null,
					"Mira",
					"Petrovic",
					"mira@gmail.com",
					LocalDate.of(1990, Month.APRIL, 15),
					Gender.FEMALE));

			userDao.save(new UserEntity(
					null,
					"Zika",
					"Petrovic",
					"zika@gmail.com",
					LocalDate.of(1986, Month.FEBRUARY, 10),
					Gender.MALE));

			userDao.save(new UserEntity(
					null,
					"Mira",
					"Mitrovic",
					"mira2@gmail.com",
					LocalDate.of(1991, Month.APRIL, 15),
					Gender.FEMALE));

//			System.out.println("Total number of users: " + userDao.count());
//			UserEntity user = userDao.findById(1)
//					.orElseThrow();
//			System.out.println(user);
//
//			userDao.findAllById(List.of(1, 2, 4, 6, 9))
//					.forEach(System.out::println);

//			final List<UserEntity> users = userDao.findByLastNameIgnoreCase("petrovic");
//
//			users.forEach(System.out::println);

//			userDao.findByFirstNameOrLastName("Mira", "Petrovic")
//					.forEach(System.out::println);

//			userDao.findByFirstNameStartsWith("Mi")
//					.forEach(System.out::println);
//			userDao.findByLastNameAndGender("Petrovic", Gender.MALE)
//					.forEach(System.out::println);

			userDao.findByDobBetween(LocalDate.of(1985, Month.JANUARY, 1), LocalDate.of(1990, Month.APRIL, 15))
					.forEach(System.out::println);
		};
	}
}
