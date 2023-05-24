package rs.ac.ni.pmf.rwa.geodistance.data.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "locations")
@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class LocationEntity
{
	@Id
	String postalCode;
	double latitude;
	double longitude;

	@ManyToOne(fetch = FetchType.LAZY)
	UserEntity editedBy;
}
