package com.calogardev.pizzarella.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.calogardev.pizzarella.dao.PrivilegeDao;
import com.calogardev.pizzarella.dto.PrivilegeDto;
import com.calogardev.pizzarella.enums.Status;
import com.calogardev.pizzarella.exception.PrivilegeNotFoundException;
import com.calogardev.pizzarella.model.Privilege;

@Service
public class PrivilegeServiceImpl implements PrivilegeService {

    private static final Logger log = LoggerFactory.getLogger(PrivilegeServiceImpl.class);

    @Autowired
    private PrivilegeDao privilegeDao;

    @Autowired
    private UtilsService utilsService;

    @Override
    public void save(PrivilegeDto privilegeDto) {
	try {
	    findByName(privilegeDto.getName());
	} catch (PrivilegeNotFoundException e) {
	    final Privilege privilege = utilsService.transform(privilegeDto, Privilege.class);
	    privilege.setStatus(Status.ACTIVE);
	    // privilege.setId(null); // TODO fix this
	    log.info("Saving privilege : " + privilege);
	    privilegeDao.save(privilege);
	}
    }

    @Override
    @Transactional(readOnly = true)
    public List<PrivilegeDto> findAll() {
	List<Privilege> privileges = privilegeDao.findAll();
	List<PrivilegeDto> privilegeDtos = utilsService.transform(privileges, PrivilegeDto.class);
	return privilegeDtos;
    }

    @Override
    @Transactional(readOnly = true)
    public PrivilegeDto findByName(String name) throws PrivilegeNotFoundException {
	Privilege privilege = privilegeDao.findByName(name);
	if (privilege == null) {
	    throw new PrivilegeNotFoundException();
	}
	// Not using privilege not found in order to adapt to implementation
	PrivilegeDto privilegeDto = utilsService.transform(privilege, PrivilegeDto.class);
	return privilegeDto;
    }
}
