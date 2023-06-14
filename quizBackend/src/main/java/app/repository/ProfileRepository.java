package app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import app.model.Profile;

/**
 * @implNote Repository interface to manage Profile
 * @author usardar
 *
 */
@Repository
public interface ProfileRepository extends JpaRepository<Profile, Integer> {

//	@Query(value = "SELECT * FROM users as u WHERE u.uName = :userName and u.uPassword = :userPassword")
//	public Profile findByEmailAndPassword(@Param("userName") String email, @Param("userPassword") String password);

	//Check Username exists in database
	@Query(value = "SELECT uId FROM user as u WHERE u.uName = :userName")
	public Integer authByUserName(@Param("userName") String userName);

	//check if username exists then retrieve user information
	@Query(value = "SELECT * FROM user as u WHERE uId = :uId and u.uPassword = :userPassword", nativeQuery = true)
	public Profile authByPassword(@Param("uId") int uId, @Param("userPassword") String userPassword);
}
