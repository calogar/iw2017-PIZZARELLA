package com.calogardev.pizzarella.service;

import java.util.ArrayList;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.calogardev.PizzarellaApplication;
import com.calogardev.pizzarella.dao.UserDao;
import com.calogardev.pizzarella.dto.UserDto;
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
		for(User user : userDao.findAll()) {
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

}
