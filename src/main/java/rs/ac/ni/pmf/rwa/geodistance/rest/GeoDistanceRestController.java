package rs.ac.ni.pmf.rwa.geodistance.rest;

import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;
import rs.ac.ni.pmf.rwa.geodistance.core.GeoDistanceService;
import rs.ac.ni.pmf.rwa.geodistance.core.model.GeoDistanceResult;
import rs.ac.ni.pmf.rwa.geodistance.rest.dto.GeoDistanceResultDTO;
import rs.ac.ni.pmf.rwa.geodistance.rest.dto.LocationDTO;
import rs.ac.ni.pmf.rwa.geodistance.rest.mapper.GeoDistanceResultMapper;
import rs.ac.ni.pmf.rwa.geodistance.shared.DistanceUnit;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class GeoDistanceRestController
{
	private final GeoDistanceService geoDistanceService;
	private final GeoDistanceResultMapper resultMapper;

	@GetMapping("/geo-distance")
	public GeoDistanceResultDTO getGeoDistance(
		@RequestParam(name = "postal-code-1") String postalCode1,
		@RequestParam(name = "postal-code-2") String postalCode2,
		@RequestParam(name = "unit", required = false, defaultValue = "KILOMETERS") DistanceUnit distanceUnit)
	{
		final GeoDistanceResult result = geoDistanceService.distance(postalCode1, postalCode2, distanceUnit);
		return resultMapper.toDto(result);
	}
}
