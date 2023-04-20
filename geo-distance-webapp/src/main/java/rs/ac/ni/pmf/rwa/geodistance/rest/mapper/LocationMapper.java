package rs.ac.ni.pmf.rwa.geodistance.rest.mapper;

import org.springframework.stereotype.Component;
import rs.ac.ni.pmf.rwa.geodistance.core.model.Location;
import rs.ac.ni.pmf.rwa.geodistance.rest.dto.LocationDTO;

@Component
public class LocationMapper
{
	public LocationDTO toDto(final Location location)
	{
		return LocationDTO.builder()
				.postalCode(location.getPostalCode())
				.latitude(location.getLatitude())
				.longitude(location.getLongitude())
				.build();
	}

	public Location fromDto(final LocationDTO dto)
	{
		return new Location(dto.getPostalCode(), dto.getLatitude(), dto.getLongitude());
	}
}
