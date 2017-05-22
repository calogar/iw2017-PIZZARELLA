package com.calogardev.pizzarella.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.calogardev.pizzarella.model.Privilege;

public interface PrivilegeDao extends CrudRepository<Privilege, Long> {

    @Query("SELECT p FROM Privilege p WHERE p.status = 'ACTIVE' AND p.name = :name")
    public Privilege findByName(@Param("name") String name);

    @Override
    @Query("SELECT p FROM Privilege p WHERE p.status = 'ACTIVE'")
    public List<Privilege> findAll();

}
