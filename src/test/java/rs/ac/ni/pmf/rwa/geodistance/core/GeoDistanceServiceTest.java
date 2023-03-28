package rs.ac.ni.pmf.rwa.geodistance.core;

import org.assertj.core.data.Offset;
import org.junit.jupiter.api.Test;
import rs.ac.ni.pmf.rwa.geodistance.core.model.GeoDistanceResult;
import rs.ac.ni.pmf.rwa.geodistance.shared.DistanceUnit;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class GeoDistanceServiceTest
{

    @Test
    public void shouldComputeDistance()
    {
        final GeoDistanceService service = new GeoDistanceService(new TestLocationProvider());

        final GeoDistanceResult distanceResult =
                service.distance("AB10 1XG", "AB10 6RN", DistanceUnit.KILOMETERS);

        final double expectedDistance = 0.805;

        assertThat(distanceResult.getDistance()).isCloseTo(expectedDistance, Offset.offset(0.001));

    }
}