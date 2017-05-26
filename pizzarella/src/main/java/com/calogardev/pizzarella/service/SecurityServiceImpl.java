package com.calogardev.pizzarella.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class SecurityServiceImpl implements SecurityService {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private AuthenticationManager authenticationManager;

    private static Boolean isLoggedIn = false;

    @Override
    public String findLoggedInUsername() {
	Object userDetails = SecurityContextHolder.getContext().getAuthentication().getDetails();
	if (userDetails instanceof UserDetails) {
	    return ((UserDetails) userDetails).getUsername();
	}
	return null;
    }

    @Override
    public Boolean login(String username, String password) {
	UserDetails userDetails = userDetailsService.loadUserByUsername(username);

	UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
		userDetails, password, userDetails.getAuthorities());
	authenticationManager.authenticate(usernamePasswordAuthenticationToken);
	if (usernamePasswordAuthenticationToken.isAuthenticated()) {
	    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
	    isLoggedIn = true;
	} else {
	    isLoggedIn = false;
	}
	return isLoggedIn;
    }

    @Override
    public Boolean isLoggedIn() {
	return isLoggedIn;
    }

    @Override
    public Boolean currentUserHasRole(String roleName) {
	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	return authentication != null && authentication.getAuthorities().contains(new SimpleGrantedAuthority(roleName));
    }

    @Override
    public void logout() {
	if (isLoggedIn) {
	    SecurityContextHolder.getContext().setAuthentication(null);
	}
    }

}
