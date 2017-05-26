package com.calogardev.pizzarella.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.calogardev.pizzarella.dto.RoleDto;
import com.calogardev.pizzarella.dto.UserDto;

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
     * SpringSecurity. As it's a special case where we can't throw the
     * exceptions to the next level (because it's a Spring Security Interface),
     * we consider all the cases to increase robustness.
     * 
     * @param username
     * @return
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

	UserDto user = userService.findByUsername(username);

	Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
	for (RoleDto role : user.getRoles()) {
	    grantedAuthorities.add(new SimpleGrantedAuthority(role.getName().toString()));
	}
	return new org.springframework.security.core.userdetails.User(user.getNickname(), user.getPassword(),
		grantedAuthorities);

    }

    // /**
    // * Refactor method used in loadUserByUsername
    // *
    // * @param username
    // * @param password
    // * @param roles
    // * @return
    // */
    // private UserDetails createUserDetails(String username, String password,
    // List<RoleDto> roles) {
    // return new org.springframework.security.core.userdetails.User(username,
    // password, true, true, true, true,
    // getAuthorities(roles));
    // }
    //
    // /**
    // * Converts a collection of roles of a User into a collection of granted
    // * authorities
    // *
    // * @param roles
    // * @return collection of Granted Authorities
    // */
    // private Collection<? extends GrantedAuthority>
    // getAuthorities(Collection<RoleDto> roles) {
    // return getGrantedAuthorities(getPrivileges(roles));
    // }
    //
    // /**
    // * Returns a list of privileges (names) from a collection of roles.
    // *
    // * @param roles
    // * @return list of privileges names (String)
    // */
    // private List<String> getPrivileges(Collection<RoleDto> roles) {
    //
    // List<String> privilegesStr = new ArrayList<>();
    // List<PrivilegeDto> privileges = new ArrayList<>();
    // for (RoleDto role : roles) {
    // privileges.addAll(role.getPrivileges());
    // }
    // for (PrivilegeDto item : privileges) {
    // privilegesStr.add(item.getName());
    // }
    // return privilegesStr;
    // }
    //
    // /**
    // * Converts a list of privileges names to a list of Granted Authorities,
    // * which are used by Spring Security.
    // *
    // * @param privileges
    // * list of privileges names (String)
    // * @return list of Granted Authorities
    // */
    // private List<GrantedAuthority> getGrantedAuthorities(List<String>
    // privileges) {
    // List<GrantedAuthority> authorities = new ArrayList<>();
    // for (String privilege : privileges) {
    // authorities.add(new SimpleGrantedAuthority(privilege));
    // }
    // return authorities;
    // }

}
