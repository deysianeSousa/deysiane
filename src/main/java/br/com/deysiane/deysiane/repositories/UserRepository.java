package br.com.deysiane.deysiane.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.deysiane.deysiane.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	@Query("select case when count(u) > 0 then true else false end from User u where u.email = :email")
	boolean isEmailUserExist(@Param("email") String email);
	
	@Query("select u from User u where u.email = ?1")
	User findByEmailAddress(@Param("email") String email);

}
