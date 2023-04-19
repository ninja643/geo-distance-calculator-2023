package rs.ac.ni.pmf.rwa.geodistance.rest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.*;
import rs.ac.ni.pmf.rwa.geodistance.core.GeoDistanceService;
import rs.ac.ni.pmf.rwa.geodistance.core.model.GeoDistanceResult;
import rs.ac.ni.pmf.rwa.geodistance.exception.UnknownLocationException;
import rs.ac.ni.pmf.rwa.geodistance.rest.dto.ErrorCode;
import rs.ac.ni.pmf.rwa.geodistance.rest.dto.ErrorDTO;
import rs.ac.ni.pmf.rwa.geodistance.rest.dto.GeoDistanceResultDTO;
import rs.ac.ni.pmf.rwa.geodistance.rest.mapper.GeoDistanceResultMapper;
import rs.ac.ni.pmf.rwa.geodistance.shared.DistanceUnit;

@RestController
@RequiredArgsConstructor
@Slf4j
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

	@ExceptionHandler(UnknownLocationException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ErrorDTO handleLocationNotFound(final UnknownLocationException e)
	{
		log.error("Unknown location requested for postal code: {}", e.getPostalCode());
		return ErrorDTO.builder()
				.code(ErrorCode.UNKNOWN_LOCATION)
				.details(e.getMessage())
				.build();
	}

	@ExceptionHandler(MissingServletRequestParameterException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ErrorDTO handleMissingParameter(final MissingServletRequestParameterException e)
	{
		return ErrorDTO.builder()
				.code(ErrorCode.GENERAL_REQUEST_ERROR)
				.details(e.getMessage())
				.build();
	}

	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ErrorDTO handleGeneralError(final Exception e)
	{
		return ErrorDTO.builder()
				.code(ErrorCode.GENERAL_REQUEST_ERROR)
				.details(e.getMessage())
				.build();
	}
}
