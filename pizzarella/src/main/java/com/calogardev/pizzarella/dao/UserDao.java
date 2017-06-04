package com.calogardev.pizzarella.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.calogardev.pizzarella.model.User;

@Repository
public interface UserDao extends CrudRepository<User, Long> {

    @Override
    public List<User> findAll();

    public User findByDni(String dni);

    public User findByUsername(String username);

    public Boolean existsByDni(String dni);

    public Boolean existsByUsername(String username);

    public List<User> findBySurnamesStartsWithIgnoringCase(String surnames);
}
