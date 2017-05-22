package com.calogardev.pizzarella.service;

import java.util.List;

import com.calogardev.pizzarella.dto.PrivilegeDto;

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
}
