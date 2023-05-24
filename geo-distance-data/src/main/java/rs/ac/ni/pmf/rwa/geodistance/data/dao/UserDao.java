package rs.ac.ni.pmf.rwa.geodistance.data.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import rs.ac.ni.pmf.rwa.geodistance.data.entity.UserEntity;
import rs.ac.ni.pmf.rwa.geodistance.data.entity.projection.IUserLite;
import rs.ac.ni.pmf.rwa.geodistance.data.entity.projection.UserLite;
import rs.ac.ni.pmf.rwa.geodistance.shared.Gender;

import java.time.LocalDate;
import java.util.List;

public interface UserDao extends JpaRepository<UserEntity, Integer>
{
	List<UserEntity> findByLastNameIgnoreCase(String lastName);

	List<UserEntity> findByFirstNameOrLastName(String firstName, String lastName);

	List<UserEntity> findByFirstNameLike(String namePart);

	List<UserEntity> findByFirstNameStartsWith(String namePart);

	List<UserEntity> findByLastNameAndGender(String lastName, Gender gender);

	List<UserEntity> findByDobBetween(LocalDate start, LocalDate end);

	@Query("select u from UserEntity as u where u.firstName = :nameParam")
	List<UserEntity> myFindUsers(@Param("nameParam") String name);

	@Query("select u.lastName from UserEntity as u where u.firstName = :nameParam")
	List<String> getLastNamesByFirstName(@Param("nameParam") String name);

	@Query("select u.firstName, u.lastName, u.dob from UserEntity as u")
	List<Object[]> getUsersLite();

	@Query("select new rs.ac.ni.pmf.rwa.geodistance.data.entity.projection.UserLite(u.firstName, u.lastName, u.email) " +
			"from UserEntity as u")
	List<UserLite> getUsersLite2();

	@Query("select u.firstName as first, u.lastName as last, u.email as email from UserEntity u")
	List<IUserLite> getUsersLite3();
}
