package com.calogardev.pizzarella.service;

import com.calogardev.pizzarella.dto.RoleDto;

/*
 * Main interface to handle Roles. Contains the bussiness logic.
 * @author calogar
 */
public interface RoleService {

	/*
	 * Saves a Role by its dto
	 * 
	 * @param roleDto
	 */
	public void save(RoleDto roleDto);

}
