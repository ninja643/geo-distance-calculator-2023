package rs.ac.ni.pmf.rwa.geodistance.core;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import rs.ac.ni.pmf.rwa.geodistance.core.model.Location;

import java.util.List;

@RequiredArgsConstructor
@Service
public class LocationService
{
	private final LocationProvider locationProvider;

	public List<Location> getLocations()
	{
		return locationProvider.getLocations();
	}
}
