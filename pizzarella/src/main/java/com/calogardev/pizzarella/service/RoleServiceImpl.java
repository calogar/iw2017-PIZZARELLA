package com.calogardev.pizzarella.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.calogardev.pizzarella.dao.RoleDao;
import com.calogardev.pizzarella.dto.RoleDto;
import com.calogardev.pizzarella.enums.Status;
import com.calogardev.pizzarella.exception.RoleNotFoundException;
import com.calogardev.pizzarella.model.Role;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private UtilsService utilsService;

    @Override
    public void save(RoleDto roleDto) {
	try {
	    findByName(roleDto.getName());
	} catch (RoleNotFoundException e) {
	    final Role role = utilsService.transform(roleDto, Role.class);
	    role.setStatus(Status.ACTIVE);
	    roleDao.save(role);
	}
    }

    @Override
    @Transactional(readOnly = true)
    public RoleDto findByName(String name) throws RoleNotFoundException {
	Role role = roleDao.findByName(name);
	if (role == null) {
	    throw new RoleNotFoundException();
	}
	// Not using role not found in order to adapt to implementation
	RoleDto roleDto = utilsService.transform(role, RoleDto.class);
	return roleDto;
    }

    @Override
    @Transactional(readOnly = true)
    public List<RoleDto> findAll() {
	List<Role> roles = roleDao.findAll();
	List<RoleDto> rolesDto = utilsService.transform(roles, RoleDto.class);
	return rolesDto;
    }

}
