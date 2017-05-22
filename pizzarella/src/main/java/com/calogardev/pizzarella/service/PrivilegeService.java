package com.calogardev.pizzarella.service;

import java.util.List;

import com.calogardev.pizzarella.dto.PrivilegeDto;
import com.calogardev.pizzarella.exception.PrivilegeNotFoundException;

public interface PrivilegeService {

    /**
     * Saves a Privilege by its privilegeDto
     * 
     * @param privilegeDto
     */
    public void save(PrivilegeDto privilegeDto);

    /**
     * Finds all active privileges
     * 
     * @return list of PrivilegeDto
     */
    public List<PrivilegeDto> findAll();

    /**
     * Finds a privilege by its name
     * 
     * @param name
     * @return privilegeDto
     * @throws PrivilegeNotFoundException
     */
    PrivilegeDto findByName(String name) throws PrivilegeNotFoundException;
}
