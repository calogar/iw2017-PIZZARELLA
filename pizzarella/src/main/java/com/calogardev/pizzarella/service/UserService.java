package com.calogardev.pizzarella.service;

import java.util.List;

import com.calogardev.pizzarella.dto.CreateUserDto;
import com.calogardev.pizzarella.dto.UserDto;
import com.calogardev.pizzarella.exception.DuplicatedUniqueAttributeException;
import com.calogardev.pizzarella.exception.EmptyAttributeException;
import com.calogardev.pizzarella.exception.ShortAttributeException;
import com.calogardev.pizzarella.model.User;

public interface UserService extends Service {

	/**
	 * Gets a User by id
	 * 
	 * @param id
	 * @return UserDto
	 */
	public UserDto findOne(Long id);

	/**
	 * Gets all active Users
	 * 
	 * @return List<UserDto>
	 */
	public List<UserDto> findAll();

	/**
	 * Transforms a UserDto into a User
	 * 
	 * @param userDto
	 * @return User
	 */
	public User transform(UserDto userDto);

	/**
	 * Transforms a User into a UserDto
	 * 
	 * @param user
	 * @return UserDto
	 */
	public UserDto transform(User user);

	/**
	 * Deletes a User by it's unique DNI
	 * 
	 * @param dni
	 */
	public void deleteByDni(String dni);

	/**
	 * Saves a User by its UserDto and password
	 * 
	 * @param userDto
	 * @throws EmptyAttributeException
	 * @throws ShortAttributeException
	 * @throws DuplicatedUniqueAttributeException
	 */
	public void save(CreateUserDto userDto)
			throws EmptyAttributeException, ShortAttributeException, DuplicatedUniqueAttributeException;
}
