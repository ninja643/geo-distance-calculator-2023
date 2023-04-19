package rs.ac.ni.pmf.rwa.geodistance.rest.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class ErrorDTO
{
	ErrorCode code;
	String details;
}
