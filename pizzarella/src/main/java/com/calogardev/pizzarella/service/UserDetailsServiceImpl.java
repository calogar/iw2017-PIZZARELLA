package com.calogardev.pizzarella.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.calogardev.pizzarella.dto.PrivilegeDto;
import com.calogardev.pizzarella.dto.RoleDto;
import com.calogardev.pizzarella.dto.UserDto;
import com.calogardev.pizzarella.exception.UserNotFoundException;

/**
 * Implementation of the UserDetailsService by Spring Security
 * 
 * @author calogar
 *
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserService userService;

	@Autowired
	private RoleService roleService;

	/**
	 * Loads a User by its username and creates a UserDetails object used by
	 * SpringSecurity
	 * 
	 * @param username
	 * @return
	 */
	@Override
	public UserDetails loadUserByUsername(String username) {

		try {
			UserDto userDto = userService.findByUsername(username);
			return new org.springframework.security.core.userdetails.User(userDto.getNickname(), userDto.getPassword(),
					true, true, true, true, getAuthorities(userDto.getRoles()));

		} catch (UserNotFoundException e) {
			// Return empty UserDetails (without privileges)
			return new org.springframework.security.core.userdetails.User(" ", " ", true, true, true, true,
					getAuthorities(Arrays.asList(roleService.findByName("ROLE_EMPTY"))));
		}
	}

	/**
	 * Converts a collection of roles of a User into a collection of granted
	 * authorities
	 * 
	 * @param roles
	 * @return collection of Granted Authorities
	 */
	private Collection<? extends GrantedAuthority> getAuthorities(Collection<RoleDto> roles) {
		return getGrantedAuthorities(getPrivileges(roles));
	}

	/**
	 * Returns a list of privileges (names) from a colection of roles.
	 * 
	 * @param roles
	 * @return list of privileges names (String)
	 */
	private List<String> getPrivileges(Collection<RoleDto> roles) {

		List<String> privilegesStr = new ArrayList<>();
		List<PrivilegeDto> privileges = new ArrayList<>();
		for (RoleDto role : roles) {
			privileges.addAll(role.getPrivileges());
		}
		for (PrivilegeDto item : privileges) {
			privilegesStr.add(item.getName());
		}
		return privilegesStr;
	}

	/**
	 * Converts a list of privileges names to a list of Granted Authorities,
	 * which are used by Spring Security.
	 * 
	 * @param privileges
	 *            list of privileges names (String)
	 * @return list of Granted Authorities
	 */
	private List<GrantedAuthority> getGrantedAuthorities(List<String> privileges) {
		List<GrantedAuthority> authorities = new ArrayList<>();
		for (String privilege : privileges) {
			authorities.add(new SimpleGrantedAuthority(privilege));
		}
		return authorities;
	}

}
