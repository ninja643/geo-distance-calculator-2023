package rs.ac.ni.pmf.rwa.geodistance.core;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import rs.ac.ni.pmf.rwa.geodistance.core.model.Location;
import rs.ac.ni.pmf.rwa.geodistance.exception.DuplicatePostalCodeException;
import rs.ac.ni.pmf.rwa.geodistance.exception.InvalidPostalCodeException;
import rs.ac.ni.pmf.rwa.geodistance.exception.UnknownLocationException;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LocationServiceTest
{
	@Mock
	private LocationProvider locationProvider;

	@InjectMocks
	private LocationService locationService;

	@Test
	public void shouldGetAllLocations()
	{
		final List<Location> expectedLocations = mock(List.class);

		when(locationProvider.getLocations())
				.thenReturn(expectedLocations);

		final List<Location> actualLocations = locationService.getLocations();

		assertThat(actualLocations).isEqualTo(expectedLocations);
	}

	@Test
	public void shouldGetLocationIfExists()
	{
		final Location expectedLocation = mock(Location.class);

		when(locationProvider.getLocation("X"))
				.thenReturn(Optional.of(expectedLocation));

		final Location actualLocation =
				locationService.getLocation("X");

		assertThat(actualLocation).isEqualTo(expectedLocation);
	}

	@Test
	public void shouldThrowWhenNoLocation()
	{
		when(locationProvider.getLocation("X"))
				.thenReturn(Optional.empty());

		assertThatThrownBy(() -> locationService.getLocation("X"))
				.isInstanceOf(UnknownLocationException.class)
				.hasMessage("Unknown location for postal code 'X'");
	}

	@Test
	public void shouldThrowWhenBadPostalCode()
	{
		final Location location = mock(Location.class);
		when(location.getPostalCode()).thenReturn(null);

		assertThatThrownBy(() -> locationService.createLocation(location))
				.isInstanceOf(InvalidPostalCodeException.class);

		final Location location2 = mock(Location.class);
		when(location2.getPostalCode()).thenReturn("   ");

		assertThatThrownBy(() -> locationService.createLocation(location2))
				.isInstanceOf(InvalidPostalCodeException.class);
	}

	@Test
	public void shouldThrowWhenPostalCodeAlreadyExists()
	{
		final String postalCode = "ABC";

		final Location location = mock(Location.class);
		when(location.getPostalCode()).thenReturn(postalCode);

		final Location existingLocation = mock(Location.class);

		when(locationProvider.getLocation(postalCode))
				.thenReturn(Optional.of(existingLocation));

		assertThatThrownBy(() -> locationService.createLocation(location))
				.isInstanceOf(DuplicatePostalCodeException.class);
	}

	@Test
	public void shouldCreateLocation()
	{
		final String postalCode = "ABC";

		final Location location = mock(Location.class);
		when(location.getPostalCode()).thenReturn(postalCode);

		when(locationProvider.getLocation(postalCode))
				.thenReturn(Optional.empty());

		locationService.createLocation(location);

		verify(locationProvider).saveLocation(location);
	}
}










