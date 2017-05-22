package com.calogardev.pizzarella.service;

import java.util.List;

import com.calogardev.pizzarella.dto.RoleDto;
import com.calogardev.pizzarella.exception.RoleNotFoundException;

/*
 * Main interface to handle Roles. Contains the business logic.
 * @author calogar
 */
public interface RoleService {

    /*
     * Saves a Role by its dto
     * 
     * @param roleDto
     */
    public void save(RoleDto roleDto);

    /**
     * Finds an active Role by its name
     * 
     * @param name
     * @return roleDto
     * @throws RoleNotFoundException
     */
    RoleDto findByName(String name) throws RoleNotFoundException;

    /**
     * Finds all active roles
     * 
     * @return list of RoleDto
     */
    List<RoleDto> findAll();
}
