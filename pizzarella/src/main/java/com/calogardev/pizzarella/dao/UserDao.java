package com.calogardev.pizzarella.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.calogardev.pizzarella.model.User;

@Repository
public interface UserDao extends CrudRepository<User, Long> {

	@Override
	@Query("SELECT u FROM User u WHERE u.status = 'ACTIVE'")
	public List<User> findAll();

	@Query("SELECT u FROM User u WHERE u.status = 'ACTIVE' AND u.dni = :dni")
	public User findByDni(@Param("dni") String dni);

	@Query("SELECT u FROM User u WHERE u.status = 'ACTIVE' AND u.nickname = :nickname")
	public User findByNickname(@Param("nickname") String nickname);

	// TODO: Current policy: we can't create a user with dni = x if a deleted
	// one with that dni exists
	public Boolean existsByDni(String dni);

	public Boolean existsByNickname(String nickname);
}
