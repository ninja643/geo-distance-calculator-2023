package rs.ac.ni.pmf.rwa.geodistance.data.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import rs.ac.ni.pmf.rwa.geodistance.data.entity.UserEntity;
import rs.ac.ni.pmf.rwa.geodistance.shared.Gender;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

public interface UserDao extends JpaRepository<UserEntity, Integer>
{
	List<UserEntity> findByLastNameIgnoreCase(String lastName);
	List<UserEntity> findByFirstNameOrLastName(String firstName, String lastName);

	List<UserEntity> findByFirstNameLike(String namePart);
	List<UserEntity> findByFirstNameStartsWith(String namePart);

	List<UserEntity> findByLastNameAndGender(String lastName, Gender gender);

	List<UserEntity> findByDobBetween(LocalDate start, LocalDate end);

//	List<UserEntity> findByFirstNameStartsWithAndDobBeforeAndEmailContainingIgnoreCase();


}
