package rs.ac.ni.pmf.rwa.geodistance;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rs.ac.ni.pmf.rwa.geodistance.data.dao.LocationDao;
import rs.ac.ni.pmf.rwa.geodistance.data.dao.UserDao;
import rs.ac.ni.pmf.rwa.geodistance.data.entity.LocationEntity;
import rs.ac.ni.pmf.rwa.geodistance.data.entity.UserEntity;

@Service
@RequiredArgsConstructor
public class SomeComponent
{
	private final UserDao userDao;
	private final LocationDao locationDao;

	@Transactional
	public void viewData()
	{
		final UserEntity user = userDao.findById(1).orElseThrow();
		System.out.println(user);

//		System.out.println(user.getFullName());
//		for (final LocationEntity location : user.getEditedLocations())
//		{
//			System.out.println(
//					location.getPostalCode() + " - " + location.getLatitude() + " : " + location.getLongitude());
//		}

//		System.out.println(
//				"User " + user.getFullName() + " has edited " + user.getEditedLocations().size() + " locations");
//		System.out.println(user.getFullName());

//		final LocationEntity locationEntity = locationDao.findById("AB10 1XG").orElseThrow();
//		System.out.println(locationEntity.getPostalCode());
//		System.out.println(locationEntity.getLatitude());
//		System.out.println(locationEntity.getLongitude());
//
//		System.out.println(locationEntity.getEditedBy().getFullName());
	}

	@Transactional
	public void initializeData2()
	{
		final LocationEntity location = LocationEntity.builder()
				.postalCode("AB10 1XG")
				.latitude(57.144156)
				.longitude(-2.114864)
				.editedBy(UserEntity.builder().firstName("Marko").lastName("Milosevic").build())
				.build();

		locationDao.save(location);
	}

	@Transactional
	public void initializeData3()
	{
		final UserEntity user = userDao.save(UserEntity.builder().firstName("Mitar").lastName("Mitrovic").build());

//		System.out.println(user);

		locationDao.save(LocationEntity.builder()
								 .postalCode("AB10 1XG")
								 .latitude(57.144156)
								 .longitude(-2.114864)
								 .editedBy(user)
								 .build());

		locationDao.save(LocationEntity.builder()
								 .postalCode("AB10 6RN")
								 .latitude(57.13787)
								 .longitude(-2.121487)
								 .editedBy(user)
								 .build());
	}

	public void showUsers()
	{
		System.out.println("List of users");
		userDao.findAll().forEach(System.out::println);
	}

	public void initializeData()
	{
		userDao.save(UserEntity.builder().firstName("Pera").lastName("Peric").build());

		final UserEntity user = userDao.findById(1).orElseThrow();
//		System.out.println(user);
		locationDao.save(LocationEntity.builder()
								 .postalCode("AB10 1XG")
								 .latitude(57.144156)
								 .longitude(-2.114864)
								 .editedBy(user)
								 .build());
		locationDao.save(LocationEntity.builder()
								 .postalCode("AB10 6RN")
								 .latitude(57.13787)
								 .longitude(-2.121487)
								 .editedBy(user)
								 .build());
	}
}
