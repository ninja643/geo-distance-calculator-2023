package rs.ac.ni.pmf.rwa.geodistance.core;

import lombok.RequiredArgsConstructor;
import rs.ac.ni.pmf.rwa.geodistance.core.model.Location;
import rs.ac.ni.pmf.rwa.geodistance.exception.DuplicatePostalCodeException;
import rs.ac.ni.pmf.rwa.geodistance.exception.InvalidPostalCodeException;
import rs.ac.ni.pmf.rwa.geodistance.exception.UnknownLocationException;

import java.util.List;

@RequiredArgsConstructor
public class LocationService
{
	private final LocationProvider locationProvider;

	public List<Location> getLocations()
	{
		return locationProvider.getLocations();
	}

	public Location getLocation(final String postalCode)
	{
		return locationProvider.getLocation(postalCode)
				.orElseThrow(() -> new UnknownLocationException(postalCode));
	}

	public void createLocation(final Location location)
	{
		final String postalCode = location.getPostalCode();

		validatePostalCode(postalCode);

		if (locationProvider.getLocation(postalCode).isPresent())
		{
			throw new DuplicatePostalCodeException(postalCode);
		}

		locationProvider.saveLocation(location);
	}

	private void validatePostalCode(final String postalCode)
	{
		if (postalCode == null || postalCode.trim().isEmpty())
		{
			throw new InvalidPostalCodeException(postalCode);
		}
	}

	public void updateLocation(final String postalCode, final Location location)
	{
		locationProvider.getLocation(postalCode)
				.orElseThrow(() -> new UnknownLocationException(postalCode));
		final String newPostalCode = location.getPostalCode();

		if (!postalCode.equals(newPostalCode))
		{
			validatePostalCode(newPostalCode);

			if (locationProvider.getLocation(newPostalCode).isPresent())
			{
				throw new DuplicatePostalCodeException(newPostalCode);
			}

			locationProvider.removeLocation(postalCode);
		}

		locationProvider.saveLocation(location);
	}

	public void deleteLocation(final String postalCode)
	{
		if (locationProvider.getLocation(postalCode).isEmpty())
		{
			throw new UnknownLocationException(postalCode);
		}
		locationProvider.removeLocation(postalCode);
	}
}
