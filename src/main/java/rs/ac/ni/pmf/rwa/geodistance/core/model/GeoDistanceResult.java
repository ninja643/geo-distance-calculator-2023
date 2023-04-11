package rs.ac.ni.pmf.rwa.geodistance.core.model;

import lombok.Value;
import rs.ac.ni.pmf.rwa.geodistance.shared.DistanceUnit;

@Value
public class GeoDistanceResult
{
	Location location1;
	Location location2;
	DistanceUnit unit;
	double distance;
}
