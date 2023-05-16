package rs.ac.ni.pmf.rwa.geodistance.data.mapper;

import rs.ac.ni.pmf.rwa.geodistance.core.model.Location;
import rs.ac.ni.pmf.rwa.geodistance.data.entity.LocationEntity;

public class LocationEntityMapper
{
	public static Location fromEntity(final LocationEntity entity)
	{
		return new Location(entity.getPostalCode(), entity.getLatitude(), entity.getLongitude());
	}

	public static LocationEntity toEntity(final Location location)
	{
		return new LocationEntity(
				location.getPostalCode(),
				location.getLatitude(),
				location.getLongitude());
	}
}
