package rs.ac.ni.pmf.rwa.geodistance.core;

import rs.ac.ni.pmf.rwa.geodistance.core.model.Location;

public interface GeoDistanceCalculator
{
	double distance(Location location1, Location location2);
}
