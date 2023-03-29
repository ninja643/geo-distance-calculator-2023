package rs.ac.ni.pmf.rwa.geodistance.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import rs.ac.ni.pmf.rwa.geodistance.core.GeoDistanceService;
import rs.ac.ni.pmf.rwa.geodistance.core.model.GeoDistanceResult;
import rs.ac.ni.pmf.rwa.geodistance.rest.dto.GeoDistanceResultDTO;
import rs.ac.ni.pmf.rwa.geodistance.rest.mapper.GeoDistanceResultMapper;
import rs.ac.ni.pmf.rwa.geodistance.shared.DistanceUnit;

@RestController
@RequiredArgsConstructor
public class GeoDistanceRestController
{
    private final GeoDistanceService geoDistanceService;
    private final GeoDistanceResultMapper resultMapper;

    @GetMapping("/geo-distance")
    public GeoDistanceResultDTO getGeoDistance(
            @RequestParam(name = "postal-code-1") String postalCode1,
            @RequestParam(name = "postal-code-2") String postalCode2,
            @RequestParam(name = "unit", required = false, defaultValue = "KILOMETERS") DistanceUnit distanceUnit)
    {
        final GeoDistanceResult result = geoDistanceService.distance(postalCode1, postalCode2, distanceUnit);
        return resultMapper.toDto(result);
    }
}
