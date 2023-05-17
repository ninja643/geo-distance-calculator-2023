package rs.ac.ni.pmf.rwa.geodistance.data.entity;

import lombok.*;
import rs.ac.ni.pmf.rwa.geodistance.shared.Gender;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class UserEntity
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	Integer id;

	String firstName;
	String lastName;
	String email;
	LocalDate dob;

	@Enumerated(EnumType.STRING)
	Gender gender;

	@Transient
	String fullName;

	@Transient
	Integer age;

	@PostLoad
	protected void computeTransientValues()
	{
		System.out.println("Calling post load");
		fullName = firstName + " " + lastName;
		age = 1;
	}
}
