package com.calogardev.pizzarella.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.calogardev.pizzarella.dao.PrivilegeDao;
import com.calogardev.pizzarella.dto.PrivilegeDto;
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
		final Privilege privilege = utilsService.transform(privilegeDto, Privilege.class);
		log.info("Saving privilege : " + privilege);
		privilegeDao.save(privilege);
	}

}
