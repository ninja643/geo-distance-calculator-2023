package rs.ac.ni.pmf.rwa.geodistance.exception;

public class DuplicatePostalCodeException extends RuntimeException
{
	public DuplicatePostalCodeException(final String postalCode)
	{
		super("Postal code '" + postalCode + "' already exists");
	}
}
