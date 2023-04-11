package rs.ac.ni.pmf.rwa.geodistance.core;

import rs.ac.ni.pmf.rwa.geodistance.core.model.Location;

public class BadGeoDistanceCalculator implements GeoDistanceCalculator
{
	@Override
	public double distance(Location location1, Location location2)
	{
		return Math.abs(location1.getLatitude() - location2.getLatitude())
				+ Math.abs(location1.getLongitude() - location2.getLongitude());
	}
}
