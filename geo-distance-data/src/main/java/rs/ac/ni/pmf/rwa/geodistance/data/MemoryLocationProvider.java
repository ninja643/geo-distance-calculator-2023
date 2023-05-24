package rs.ac.ni.pmf.rwa.geodistance.data;

import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import rs.ac.ni.pmf.rwa.geodistance.core.LocationProvider;
import rs.ac.ni.pmf.rwa.geodistance.core.model.Location;
import rs.ac.ni.pmf.rwa.geodistance.data.dao.LocationDao;
import rs.ac.ni.pmf.rwa.geodistance.data.dao.UserDao;
import rs.ac.ni.pmf.rwa.geodistance.data.entity.LocationEntity;
import rs.ac.ni.pmf.rwa.geodistance.data.entity.UserEntity;
import rs.ac.ni.pmf.rwa.geodistance.data.mapper.LocationEntityMapper;
import rs.ac.ni.pmf.rwa.geodistance.shared.Gender;

import java.time.LocalDate;
import java.time.Month;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
//@RequiredArgsConstructor
public class MemoryLocationProvider implements LocationProvider
{
	private final LocationDao locationDao;

	private final Map<String, Location> locations = new HashMap<>(Map.of(
			"AB10 1XG", new Location("AB10 1XG", 57.144156, -2.114864),
			"AB10 6RN", new Location("AB10 6RN", 57.137871, -2.121487)
	));

	public MemoryLocationProvider(final LocationDao locationDao)
	{
		this.locationDao = locationDao;

//		for (Location location : locations.values())
//		{
//			saveLocation(location);
//		}
	}

	@Override
	@Transactional
	public Optional<Location> getLocation(final String postalCode)
	{
		final LocationEntity locationEntity = locationDao.findById(postalCode).orElseThrow();

		System.out.println(locationEntity.getEditedBy().getFullName());

//		final Location location =
//				.map(LocationEntityMapper::fromEntity)
//				.orElse(null);

		return Optional.ofNullable(LocationEntityMapper.fromEntity(locationEntity));
	}

	@Override
	public List<Location> getLocations()
	{
		return locationDao.findAll().stream()
				.map(LocationEntityMapper::fromEntity)
				.collect(Collectors.toList());
	}

	@Override
	public void saveLocation(final Location location)
	{
		log.debug("Saving location {}", location);
		locationDao.save(LocationEntityMapper.toEntity(location));
	}

	@Override
	public void removeLocation(String postalCode)
	{
		locationDao.deleteById(postalCode);
	}
}
