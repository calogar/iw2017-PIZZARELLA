package com.calogardev.pizzarella.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.dozer.DozerBeanMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.calogardev.pizzarella.dao.UserDao;
import com.calogardev.pizzarella.dto.CreateUserDto;
import com.calogardev.pizzarella.dto.Dto;
import com.calogardev.pizzarella.dto.UserDto;
import com.calogardev.pizzarella.enums.UserStatus;
import com.calogardev.pizzarella.exception.DuplicatedUniqueAttributeException;
import com.calogardev.pizzarella.exception.EmptyAttributeException;
import com.calogardev.pizzarella.exception.ShortAttributeException;
import com.calogardev.pizzarella.model.User;

@Service
public class UserServiceImpl implements UserService {

	private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
	private UserDao userDao;

	@Autowired
	private DozerBeanMapper dozer;

	@Override
	public UserDto findOne(Long id) {
		final User user = userDao.findOne(id);
		return transform(user);
	}

	@Override
	public List<UserDto> findAll() {

		log.info("### Getting all users ###");
		List<UserDto> userDtos = new ArrayList<>();
		for (User user : userDao.findAll()) {
			userDtos.add(transform(user));
			log.info(transform(user).toString());
		}
		return userDtos;
	}

	@Override
	public User transform(UserDto userDto) {
		return dozer.map(userDto, User.class);
	}

	@Override
	public UserDto transform(User user) {
		return dozer.map(user, UserDto.class);
	}

	public User transform(CreateUserDto dto) {
		return dozer.map(dto, User.class);
	}

	@Override
	public void deleteByDni(String dni) {
		User user = userDao.findByDni(dni);
		user.setStatus(UserStatus.DELETED);
		userDao.save(user);

		log.debug("Deleted User with dni: " + dni);
	}

	// TODO: Check if some fields contain numbers
	@Override
	public void save(CreateUserDto u)
			throws EmptyAttributeException, ShortAttributeException, DuplicatedUniqueAttributeException {
		if (isEmpty(u.getName()) || isEmpty(u.getSurnames()) || isEmpty(u.getDni()) || isEmpty(u.getNickname())
				|| isEmpty(u.getPassword())) {
			throw new EmptyAttributeException();
		} else if (u.getPassword().length() < 6) {
			throw new ShortAttributeException("Password", 6);
		} else if (u.getDni().length() != 9) {
			throw new ShortAttributeException("DNI", 9);
		} else if (userDao.existsByDni(u.getDni())) {
			throw new DuplicatedUniqueAttributeException("DNI");
		} else if (userDao.existsByNickname(u.getNickname())) {
			throw new DuplicatedUniqueAttributeException("Nickname");
		}
		User user = transform(u);
		user.setStatus(UserStatus.ACTIVE);
		userDao.save(user);
		log.info("Saved User: " + user.toString());
	}

	// We keep this to be able to use a common interface for all Dtos
	@Override
	public void save(Dto dto)
			throws EmptyAttributeException, ShortAttributeException, DuplicatedUniqueAttributeException {
		save((CreateUserDto) dto);
	}

	/**
	 * Checks if string is null or "" (blank)
	 * 
	 * @param str
	 * @return
	 */
	private Boolean isEmpty(String str) {
		return StringUtils.isEmpty(str);
	}
}
