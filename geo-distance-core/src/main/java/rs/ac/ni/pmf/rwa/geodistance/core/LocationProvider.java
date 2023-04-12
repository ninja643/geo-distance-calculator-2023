package rs.ac.ni.pmf.rwa.geodistance.core;

import rs.ac.ni.pmf.rwa.geodistance.core.model.Location;

import java.util.List;

public interface LocationProvider
{
	Location getLocation(final String postalCode);

	List<Location> getLocations();
}
