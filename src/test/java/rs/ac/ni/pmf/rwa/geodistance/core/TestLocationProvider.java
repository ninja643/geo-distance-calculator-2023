package rs.ac.ni.pmf.rwa.geodistance.core;

import java.util.Map;
import java.util.stream.Stream;
import rs.ac.ni.pmf.rwa.geodistance.core.model.Location;

public class TestLocationProvider implements LocationProvider
{
	private final Map<String, Location> locations = Map.of(
		"AB10 1XG", new Location("AB10 1XG", 57.144156, -2.114864),
		"AB10 6RN", new Location("AB10 6RN", 57.137871, -2.121487)
	);

	@Override
	public Location getLocation(String postalCode)
	{
		return locations.get(postalCode);
	}

	@Override
	public Stream<Location> getLocations()
	{
		return locations.values().stream();
	}
}
