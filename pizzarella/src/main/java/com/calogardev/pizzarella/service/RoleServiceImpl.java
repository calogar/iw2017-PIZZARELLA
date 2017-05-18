package com.calogardev.pizzarella.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.calogardev.pizzarella.dao.RoleDao;
import com.calogardev.pizzarella.dto.RoleDto;
import com.calogardev.pizzarella.model.Role;

@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleDao roleDao;

	@Autowired
	private UtilsService utilsService;

	@Override
	public void save(RoleDto roleDto) {

		final Role role = utilsService.transform(roleDto, Role.class);
		roleDao.save(role);
	}

}
