# Geo Distance Calculator

## Useful code

```java
public class GeoDistanceCalculator
{
    //...
    private double distance(
            final Location location1,
            final Location location2)
    {
        // Using Haversine formula! See Wikipedia;
        double longitude1Radians = Math.toRadians(location1.getLongitude());
        double longitude2Radians = Math.toRadians(location2.getLongitude());
        double latitude1Radians = Math.toRadians(location1.getLatitude());
        double latitude2Radians = Math.toRadians(location2.getLatitude());
        double a = haversine(latitude1Radians, latitude2Radians)
                + Math.cos(latitude1Radians)
                * Math.cos(latitude2Radians)
                * haversine(longitude1Radians, longitude2Radians);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return (EARTH_RADIUS * c);
    }

    private static double haversine(final double deg1, final double deg2)
    {
        return square(Math.sin((deg1 - deg2) / 2.0));
    }

    private static double square(final double x)
    {
        return x * x;
    }
}
```

