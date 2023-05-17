package rs.ac.ni.pmf.rwa.geodistance.data.entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import rs.ac.ni.pmf.rwa.geodistance.shared.Gender;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
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
}