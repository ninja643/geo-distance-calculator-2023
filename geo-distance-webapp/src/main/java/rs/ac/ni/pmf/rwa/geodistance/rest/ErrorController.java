package rs.ac.ni.pmf.rwa.geodistance.rest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import rs.ac.ni.pmf.rwa.geodistance.exception.DuplicatePostalCodeException;
import rs.ac.ni.pmf.rwa.geodistance.exception.UnknownLocationException;
import rs.ac.ni.pmf.rwa.geodistance.rest.dto.ErrorCode;
import rs.ac.ni.pmf.rwa.geodistance.rest.dto.ErrorDTO;

@Slf4j
@ControllerAdvice
@ResponseBody
public class ErrorController
{
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

	@ExceptionHandler(DuplicatePostalCodeException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ErrorDTO handleDuplicateLocation(final DuplicatePostalCodeException e)
	{
		return ErrorDTO.builder()
				.code(ErrorCode.DUPLICATE_LOCATION)
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
