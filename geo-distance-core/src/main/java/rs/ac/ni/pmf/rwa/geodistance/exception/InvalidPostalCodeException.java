package rs.ac.ni.pmf.rwa.geodistance.exception;

public class InvalidPostalCodeException extends RuntimeException
{
	public InvalidPostalCodeException(final String postalCode)
	{
		super("Postal code '" + postalCode + "' is not valid");
	}
}
