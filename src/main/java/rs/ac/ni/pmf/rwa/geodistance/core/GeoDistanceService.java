package rs.ac.ni.pmf.rwa.geodistance.core;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import rs.ac.ni.pmf.rwa.geodistance.core.model.GeoDistanceResult;
import rs.ac.ni.pmf.rwa.geodistance.core.model.Location;
import rs.ac.ni.pmf.rwa.geodistance.shared.DistanceUnit;

@Service
@RequiredArgsConstructor
public class GeoDistanceService
{
    // radius in kilometers
    private final static double EARTH_RADIUS = 6371;

    private final LocationProvider locationProvider;

    public GeoDistanceResult distance(
            final String postalCode1,
            final String postalCode2,
            final DistanceUnit unit)
    {
        final Location location1 = locationProvider.getLocation(postalCode1);
        final Location location2 = locationProvider.getLocation(postalCode2);
        final double distanceKm = distance(location1, location2);

        final double d = (unit == DistanceUnit.KILOMETERS) ? distanceKm : distanceKm / 1.6;

        return new GeoDistanceResult(location1, location2, unit, d);
    }

    private double distance(
            final Location location1,
            final Location location2)
    {
        // Using Haversine formula! See Wikipedia;
        double longitude1Radians = Math.toRadians(location1.getLongitude());
        double longitude2Radians = Math.toRadians(location2.getLongitude());
        double latitude1Radians = Math.toRadians(location1.getLatitude());
        double latitude2Radians = Math.toRadians(location2.getLatitude());
        double a = haversine(latitude1Radians, latitude2Radians)
                + Math.cos(latitude1Radians)
                * Math.cos(latitude2Radians)
                * haversine(longitude1Radians, longitude2Radians);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return (EARTH_RADIUS * c);
    }

    private static double haversine(final double deg1, final double deg2)
    {
        return square(Math.sin((deg1 - deg2) / 2.0));
    }

    private static double square(final double x)
    {
        return x * x;
    }
}
