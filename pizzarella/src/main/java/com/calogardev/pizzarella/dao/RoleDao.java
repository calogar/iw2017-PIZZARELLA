package com.calogardev.pizzarella.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.calogardev.pizzarella.model.Role;

@Repository
public interface RoleDao extends CrudRepository<Role, Long> {

}
