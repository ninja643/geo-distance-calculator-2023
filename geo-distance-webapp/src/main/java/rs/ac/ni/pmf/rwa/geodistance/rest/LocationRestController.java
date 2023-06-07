package rs.ac.ni.pmf.rwa.geodistance.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import rs.ac.ni.pmf.rwa.geodistance.core.LocationService;
import rs.ac.ni.pmf.rwa.geodistance.core.model.Location;
import rs.ac.ni.pmf.rwa.geodistance.rest.dto.LocationDTO;
import rs.ac.ni.pmf.rwa.geodistance.rest.mapper.LocationMapper;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Locations API", description = "CRUD operations for locations")
@SecurityRequirement(name = "GeoDistanceAPI")
public class LocationRestController
{
	private final LocationService locationService;
	private final LocationMapper locationMapper;

	@GetMapping("/locations")
	@Operation(
			description = "Get the list of all available locations",
			summary = "List of all locations")
	public List<LocationDTO> getLocations()
	{
		return locationService.getLocations().stream()
				.map(locationMapper::toDto)
				.collect(Collectors.toList());
	}

	@GetMapping("/locations/{postal-code}")
	public LocationDTO findLocation(@PathVariable(name = "postal-code") final String postalCode)
	{
		final Location location = locationService.getLocation(postalCode);
		return locationMapper.toDto(location);
	}

	@PostMapping("/locations")
	@ResponseStatus(HttpStatus.CREATED)
	public void createLocation(@RequestBody final LocationDTO location)
	{
		locationService.createLocation(locationMapper.fromDto(location));
	}

	@PutMapping("/locations/{postal-code}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void updateLocation(
			@PathVariable(name = "postal-code") final String postalCode,
			@RequestBody final LocationDTO location)
	{
		locationService.updateLocation(postalCode, locationMapper.fromDto(location));
	}

	@DeleteMapping("/locations/{postal-code}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteLocation(@PathVariable(name = "postal-code") final String postalCode)
	{
		locationService.deleteLocation(postalCode);
	}
}
