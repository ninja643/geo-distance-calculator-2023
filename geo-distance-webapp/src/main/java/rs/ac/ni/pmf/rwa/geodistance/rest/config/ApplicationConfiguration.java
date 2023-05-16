package rs.ac.ni.pmf.rwa.geodistance.rest.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import rs.ac.ni.pmf.rwa.geodistance.core.*;
import rs.ac.ni.pmf.rwa.geodistance.data.MemoryLocationProvider;
import rs.ac.ni.pmf.rwa.geodistance.data.dao.LocationDao;
import rs.ac.ni.pmf.rwa.geodistance.data.dao.UserDao;

@Configuration
public class ApplicationConfiguration
{
	@Bean
	public LocationProvider getLocationProvider(final LocationDao locationDao)
	{
		return new MemoryLocationProvider(locationDao);
	}

	@Bean
	public GeoDistanceCalculator getGeoDistanceCalculator()
	{
//		return new BadGeoDistanceCalculator();
		return new StandardGeoDistanceCalculator();
	}

	@Bean
	public GeoDistanceService getGeoDistanceService(
			final LocationProvider locationProvider,
			final GeoDistanceCalculator geoDistanceCalculator)
	{
		return new GeoDistanceService(locationProvider, geoDistanceCalculator);
	}

	@Bean
	public LocationService getLocationService(final LocationProvider locationProvider)
	{
		return new LocationService(locationProvider);
	}
}
