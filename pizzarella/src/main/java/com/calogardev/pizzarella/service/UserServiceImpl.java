package com.calogardev.pizzarella.service;

import java.util.ArrayList;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.calogardev.pizzarella.dao.UserDao;
import com.calogardev.pizzarella.dto.UserDto;
import com.calogardev.pizzarella.model.User;

@Service
public class UserServiceImpl implements UserService {

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
		List<UserDto> userDtos = new ArrayList<>();
		for(User user : userDao.findAll()) {
			userDtos.add(transform(user));
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
