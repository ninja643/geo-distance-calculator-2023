package rs.ac.ni.pmf.rwa.geodistance.exception;

import lombok.Getter;

public class UnknownLocationException extends RuntimeException
{
	@Getter
	private final String postalCode;

	public UnknownLocationException(final String postalCode)
	{
		super("Unknown location for postal code '" + postalCode + "'");
		this.postalCode = postalCode;
	}
}
