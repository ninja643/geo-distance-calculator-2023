package rs.ac.ni.pmf.rwa.geodistance.data;

import lombok.extern.slf4j.Slf4j;
import rs.ac.ni.pmf.rwa.geodistance.core.LocationProvider;
import rs.ac.ni.pmf.rwa.geodistance.core.model.Location;

import java.util.*;

@Slf4j
public class MemoryLocationProvider implements LocationProvider
{
	private final Map<String, Location> locations = new HashMap<>(Map.of(
			"AB10 1XG", new Location("AB10 1XG", 57.144156, -2.114864),
			"AB10 6RN", new Location("AB10 6RN", 57.137871, -2.121487)
	));

	@Override
	public Optional<Location> getLocation(String postalCode)
	{
		return Optional.ofNullable(locations.get(postalCode));
	}

	@Override
	public List<Location> getLocations()
	{
		return new ArrayList<>(locations.values());
	}

	@Override
	public void saveLocation(Location location)
	{
		log.debug("Saving location {}", location);
		locations.put(location.getPostalCode(), location);
	}
}
