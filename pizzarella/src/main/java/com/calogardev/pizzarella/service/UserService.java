package com.calogardev.pizzarella.service;

import java.util.List;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.calogardev.pizzarella.exception.CustomValidationException;
import com.calogardev.pizzarella.exception.UserNotFoundException;
import com.calogardev.pizzarella.model.User;

/**
 * Main interface to handle the User entity. Contains the business logic.
 * 
 * @author calogar
 *
 */
public interface UserService {

    public User findOne(Long id) throws UserNotFoundException;

    public List<User> findAll();

    public void deleteByDni(String dni) throws UserNotFoundException;

    public User save(User user) throws CustomValidationException;

    public User findByUsername(String username) throws UsernameNotFoundException;

    public void delete(User dto) throws UserNotFoundException;

    public List<User> filterBySurnames(String filterText);
}
