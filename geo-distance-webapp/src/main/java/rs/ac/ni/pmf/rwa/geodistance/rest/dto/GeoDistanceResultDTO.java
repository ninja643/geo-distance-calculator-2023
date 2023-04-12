package rs.ac.ni.pmf.rwa.geodistance.rest.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class GeoDistanceResultDTO
{
	double latitude1;
	double longitude1;
	double latitude2;
	double longitude2;
	String unit;
	double distance;
}
