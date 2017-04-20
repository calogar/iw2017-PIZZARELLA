package com.calogardev.pizzarella.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.calogardev.pizzarella.dao.UserDao;
import com.calogardev.pizzarella.dto.UserDto;
import com.calogardev.pizzarella.dto.Dto;
import com.calogardev.pizzarella.dto.UserDto;
import com.calogardev.pizzarella.enums.Status;
import com.calogardev.pizzarella.exception.CustomValidationException;
import com.calogardev.pizzarella.model.User;

/**
 * UserService implementation.
 * 
 * @author calogar
 *
 */
@Service
public class UserServiceImpl implements UserService {

	private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
	private UserDao userDao;

	@Autowired
	UtilsService utilsService;

	@Override
	@Transactional(readOnly = true)
	public UserDto findOne(Long id) {
		final User user = userDao.findOne(id);
		return utilsService.transform(user, UserDto.class);
	}

	@Override
	@Transactional(readOnly = true)
	public List<UserDto> findAll() {
		log.info("Finding all active users");
		List<User> users = userDao.findAll();
		return utilsService.transform(users, UserDto.class);
	}

	@Override
	public void deleteByDni(String dni) {
		User user = userDao.findByDni(dni);
		user.setStatus(Status.DELETED);
		userDao.save(user);
		log.debug("Deleted User with dni: " + dni);
	}

	// TODO: Check if some fields contain numbers
	@Override
	public void save(UserDto u) throws CustomValidationException {
		log.info(u.toString());
		if (isEmpty(u.getName()) || isEmpty(u.getSurnames()) || isEmpty(u.getDni()) || isEmpty(u.getNickname())
				|| isEmpty(u.getPassword())) {
			throw new CustomValidationException("Some attributes are empty.");
		} else if (u.getPassword().length() < 6) {
			throw new CustomValidationException("Password must be at least 6 characters long.");
		} else if (u.getDni().length() != 9) {
			throw new CustomValidationException("DNI must have 9 characters.");
		} else if (userDao.existsByDni(u.getDni()) == null) {
			throw new CustomValidationException("That DNI is already registered.");
		} else if (userDao.existsByNickname(u.getNickname()) == null) {
			throw new CustomValidationException("That Nickname is already registered");
		}

		User user = utilsService.transform(u, User.class);
		user.setStatus(Status.ACTIVE);
		userDao.save(user);
		log.info("Saved User: " + user.toString());
	}

	/**
	 * Compatibility method. Allows to implement a generic Service for saving
	 * Dtos.
	 * 
	 * @param dto
	 *            A generic Dto that can be casted to CreateUserDto
	 */
	@Override
	public void save(Dto dto) throws CustomValidationException {
		save((UserDto) dto);
	}

	/**
	 * Checks if string is null or "" (blank)
	 * 
	 * @param str
	 *            The string to check
	 * @return The response
	 */
	private Boolean isEmpty(String str) {
		return StringUtils.isEmpty(str);
	}
}
