package com.calogardev.pizzarella.service;

import java.util.List;

import com.calogardev.pizzarella.dto.UserDto;
import com.calogardev.pizzarella.exception.CustomValidationException;

/**
 * Main interface to handle the User entity. Contains the bussiness logic.
 * 
 * @author calogar
 *
 */
public interface UserService extends GenericService {

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
	 * Deletes a User by it's unique DNI
	 * 
	 * @param dni
	 */
	public void deleteByDni(String dni);

	/**
	 * Saves a User by its CreateUserDto
	 * 
	 * @param createUserDto
	 *            Dto with the needed data
	 * @throws CustomValidationException
	 */
	public void save(UserDto userDto) throws CustomValidationException;
}
