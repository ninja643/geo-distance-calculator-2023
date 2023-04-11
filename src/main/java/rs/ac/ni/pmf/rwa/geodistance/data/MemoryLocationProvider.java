package rs.ac.ni.pmf.rwa.geodistance.data;

import java.util.Map;
import org.springframework.stereotype.Component;
import rs.ac.ni.pmf.rwa.geodistance.core.LocationProvider;
import rs.ac.ni.pmf.rwa.geodistance.core.model.Location;

@Component
public class MemoryLocationProvider implements LocationProvider
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
}
