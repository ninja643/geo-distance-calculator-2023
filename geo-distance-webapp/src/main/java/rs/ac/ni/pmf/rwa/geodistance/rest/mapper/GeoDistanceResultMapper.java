package rs.ac.ni.pmf.rwa.geodistance.rest.mapper;

import org.springframework.stereotype.Component;
import rs.ac.ni.pmf.rwa.geodistance.core.model.GeoDistanceResult;
import rs.ac.ni.pmf.rwa.geodistance.rest.dto.GeoDistanceResultDTO;

@Component
public class GeoDistanceResultMapper
{
	public GeoDistanceResultDTO toDto(final GeoDistanceResult data)
	{
		return GeoDistanceResultDTO.builder()
				.longitude1(data.getLocation1().getLongitude())
				.latitude1(data.getLocation1().getLatitude())
				.longitude2(data.getLocation2().getLongitude())
				.latitude2(data.getLocation2().getLatitude())
				.unit(data.getUnit().name())
				.distance(data.getDistance())
				.build();
	}
}
