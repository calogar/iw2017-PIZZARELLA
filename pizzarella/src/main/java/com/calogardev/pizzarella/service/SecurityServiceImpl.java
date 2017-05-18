package com.calogardev.pizzarella.service;

import org.springframework.stereotype.Service;

@Service
public class SecurityServiceImpl implements SecurityService {

	private static Boolean isLoggedIn = false;

	@Override
	public Boolean login(String username, String password) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean isLoggedIn() {
		return isLoggedIn;
	}

	@Override
	public void logout() {
		// TODO Auto-generated method stub

	}

}
