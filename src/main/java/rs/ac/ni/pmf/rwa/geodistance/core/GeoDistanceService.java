package rs.ac.ni.pmf.rwa.geodistance.core;

import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import rs.ac.ni.pmf.rwa.geodistance.core.model.GeoDistanceResult;
import rs.ac.ni.pmf.rwa.geodistance.core.model.Location;
import rs.ac.ni.pmf.rwa.geodistance.core.util.GeoDistanceCalculator;
import rs.ac.ni.pmf.rwa.geodistance.shared.DistanceUnit;

@Service
@RequiredArgsConstructor
public class GeoDistanceService
{
	private final LocationProvider locationProvider;

	public GeoDistanceResult distance(final String postalCode1, final String postalCode2, final DistanceUnit unit)
	{
		final Location location1 = locationProvider.getLocation(postalCode1);
		final Location location2 = locationProvider.getLocation(postalCode2);
		final double distanceKm = GeoDistanceCalculator.distance(location1, location2);

		final double d = (unit == DistanceUnit.KILOMETERS) ? distanceKm : distanceKm / 1.6;

		return new GeoDistanceResult(location1, location2, unit, d);
	}
}
