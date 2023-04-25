package rs.ac.ni.pmf.rwa.geodistance.core;

import rs.ac.ni.pmf.rwa.geodistance.core.model.Location;

import java.util.List;
import java.util.Optional;

public interface LocationProvider
{
	Optional<Location> getLocation(final String postalCode);

	List<Location> getLocations();

	void saveLocation(final Location location);

	void removeLocation(final String postalCode);
}
