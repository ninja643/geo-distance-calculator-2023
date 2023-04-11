package rs.ac.ni.pmf.rwa.geodistance.core;

import rs.ac.ni.pmf.rwa.geodistance.core.model.Location;

public interface LocationProvider
{
	Location getLocation(final String postalCode);
}
