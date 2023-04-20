package rs.ac.ni.pmf.rwa.geodistance.core;

import lombok.RequiredArgsConstructor;
import rs.ac.ni.pmf.rwa.geodistance.core.model.Location;
import rs.ac.ni.pmf.rwa.geodistance.exception.UnknownLocationException;

import java.util.List;

@RequiredArgsConstructor
public class LocationService
{
	private final LocationProvider locationProvider;

	public List<Location> getLocations()
	{
		return locationProvider.getLocations();
	}

	public Location getLocation(final String postalCode)
	{
		return locationProvider.getLocation(postalCode)
				.orElseThrow(() -> new UnknownLocationException(postalCode));
	}
}
