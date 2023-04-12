package rs.ac.ni.pmf.rwa.geodistance.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import rs.ac.ni.pmf.rwa.geodistance.core.LocationService;
import rs.ac.ni.pmf.rwa.geodistance.rest.dto.LocationDTO;
import rs.ac.ni.pmf.rwa.geodistance.rest.mapper.LocationMapper;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class LocationRestController
{
	private final LocationService locationService;
	private final LocationMapper locationMapper;

	@GetMapping("/locations")
	public List<LocationDTO> getLocations()
	{
		return locationService.getLocations().stream()
				.map(locationMapper::toDto)
				.collect(Collectors.toList());
	}
}
