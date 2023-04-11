package rs.ac.ni.pmf.rwa.geodistance.rest;

import java.util.Comparator;
import java.util.stream.Stream;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;
import rs.ac.ni.pmf.rwa.geodistance.core.GeoDistanceService;
import rs.ac.ni.pmf.rwa.geodistance.core.LocationService;
import rs.ac.ni.pmf.rwa.geodistance.core.model.GeoDistanceResult;
import rs.ac.ni.pmf.rwa.geodistance.rest.dto.GeoDistanceResultDTO;
import rs.ac.ni.pmf.rwa.geodistance.rest.dto.LocationDTO;
import rs.ac.ni.pmf.rwa.geodistance.rest.mapper.GeoDistanceResultMapper;
import rs.ac.ni.pmf.rwa.geodistance.rest.mapper.LocationMapper;
import rs.ac.ni.pmf.rwa.geodistance.shared.DistanceUnit;

@RestController
@RequiredArgsConstructor
public class GeoDistanceRestController
{
	private final GeoDistanceService _geoDistanceService;
	private final LocationService _locationService;

	private final GeoDistanceResultMapper _resultMapper;
	private final LocationMapper _locationMapper;

	@GetMapping("/geo-distance")
	public GeoDistanceResultDTO getGeoDistance(
		@RequestParam(name = "postal-code-1") String postalCode1,
		@RequestParam(name = "postal-code-2") String postalCode2,
		@RequestParam(name = "unit", required = false, defaultValue = "KILOMETERS") DistanceUnit distanceUnit)
	{
		final GeoDistanceResult result = _geoDistanceService.distance(postalCode1, postalCode2, distanceUnit);
		return _resultMapper.toDto(result);
	}

	@GetMapping("/postal-codes")
	public Stream<LocationDTO> getLocations()
	{
		return _locationService.getLocations()
			.map(_locationMapper::toDto)
			.sorted(Comparator.comparing(LocationDTO::getPostalCode));
	}
}
