package com.calogardev.pizzarella.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.calogardev.pizzarella.model.Role;

@Repository
public interface RoleDao extends CrudRepository<Role, Long> {

    @Query("SELECT r FROM Role r WHERE r.status = 'ACTIVE' AND r.name = :name")
    public Role findByName(@Param("name") String name);

    @Override
    @Query("SELECT r FROM Role r WHERE r.status = 'ACTIVE'")
    public List<Role> findAll();

    public Boolean existsByName(String name);
}
