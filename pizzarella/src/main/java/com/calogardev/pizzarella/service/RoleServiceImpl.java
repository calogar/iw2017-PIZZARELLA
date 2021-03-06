package com.calogardev.pizzarella.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.calogardev.pizzarella.dao.RoleDao;
import com.calogardev.pizzarella.dto.RoleDto;
import com.calogardev.pizzarella.enums.Status;
import com.calogardev.pizzarella.exception.CustomValidationException;
import com.calogardev.pizzarella.exception.RoleNotFoundException;
import com.calogardev.pizzarella.model.Role;

@Service
public class RoleServiceImpl implements RoleService {

    private static final Logger log = LoggerFactory.getLogger(RoleServiceImpl.class);

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private UtilsService utilsService;

    @Override
    public RoleDto save(RoleDto roleDto) throws CustomValidationException {

	if (roleDto.getId() == null) {
	    // Perform create
	    if (roleDao.existsByName(roleDto.getName())) {
		throw new CustomValidationException("That Role is already registered");
	    }
	} else {
	    // Perform update action (check active)
	    if (roleDao.findOne(roleDto.getId()).getStatus() != Status.ACTIVE) {
		throw new CustomValidationException("That Role is deleted and cannot be updated");
	    }
	}

	final Role role = utilsService.transform(roleDto, Role.class);
	role.setStatus(Status.ACTIVE);
	Role persisted = roleDao.save(role);
	log.info("Saved role: " + persisted);
	return utilsService.transform(persisted, RoleDto.class);
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
