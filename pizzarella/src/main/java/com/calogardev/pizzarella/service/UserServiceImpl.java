package com.calogardev.pizzarella.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.calogardev.pizzarella.dao.UserDao;
import com.calogardev.pizzarella.exception.CustomValidationException;
import com.calogardev.pizzarella.exception.UserNotFoundException;
import com.calogardev.pizzarella.model.User;

import lombok.extern.slf4j.Slf4j;

/**
 * UserService implementation.
 * 
 * @author calogar
 *
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    UtilsService utilsService;

    @Autowired
    private BCryptPasswordEncoder bcrypt;

    @Override
    @Transactional(readOnly = true)
    public User findOne(Long id) throws UserNotFoundException {
	final User user = userDao.findOne(id);
	if (user == null) {
	    throw new UserNotFoundException();
	}

	log.info("Found user: " + user);
	return user;
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> findAll() {
	log.info("Finding all active users");
	return userDao.findAll();
    }

    @Override
    public void deleteByDni(String dni) throws UserNotFoundException {
	User user = userDao.findByDni(dni);
	if (user == null) {
	    throw new UserNotFoundException();
	}
	userDao.delete(user);
	log.debug("Deleted user with dni: " + dni);
    }

    // TODO: Check if some fields contain numbers
    @Override
    public User save(User user) throws CustomValidationException {

	if (user.getId() == null) {
	    // Perform create action (check uniqueness)
	    if (userDao.existsByDni(user.getDni()))
		throw new CustomValidationException("That DNI is already registered.");
	    else if (userDao.existsByUsername(user.getUsername()))
		throw new CustomValidationException("That Nickname is already registered");
	}

	if (user.getPassword().length() < 6) {
	    throw new CustomValidationException("Password must be at least 6 characters long.");
	} else if (user.getDni().length() != 9) {
	    throw new CustomValidationException("DNI must have 9 characters.");
	}

	user.setPassword(bcrypt.encode(user.getPassword()));
	user = userDao.save(user);
	log.info("Saved user: " + user);
	return user;
    }

    @Override
    public User findByUsername(String username) throws UsernameNotFoundException {
	User user = userDao.findByUsername(username);
	if (user == null) {
	    // We are using a spring security exception in order to comply with
	    // the SecurityService implementation
	    throw new UsernameNotFoundException(username);
	} else {
	    return user;
	}
    }

    @Override
    public void delete(User user) throws UserNotFoundException {
	if (user.getId() == null) {
	    // If it hasn't got id, it's not persisted
	    throw new UserNotFoundException();
	}
	String username = user.getUsername();
	userDao.delete(user);
	log.debug("Deleted user with username: " + username);
    }

    @Override
    public List<User> filterBySurnames(String filterText) {
	return userDao.findBySurnamesStartsWithIgnoringCase(filterText);
    }

}
