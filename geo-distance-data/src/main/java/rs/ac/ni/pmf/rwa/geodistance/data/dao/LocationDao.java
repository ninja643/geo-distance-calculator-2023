package rs.ac.ni.pmf.rwa.geodistance.data.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import rs.ac.ni.pmf.rwa.geodistance.data.entity.LocationEntity;

public interface LocationDao extends JpaRepository<LocationEntity, String>
{
}
