package rs.ac.ni.pmf.rwa.geodistance.core;

import java.util.stream.Stream;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import rs.ac.ni.pmf.rwa.geodistance.core.model.Location;

@Service
@RequiredArgsConstructor
public class LocationService
{
	private final LocationProvider locationProvider;

	public Stream<Location> getLocations()
	{
		return locationProvider.getLocations();
	}
}
