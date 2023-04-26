package rs.ac.ni.pmf.rwa.geodistance.rest.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class LocationDTO
{
	@Schema(description = "Postal code of the location", example = "AX 1B")
	String postalCode;
	double latitude;
	double longitude;
}
