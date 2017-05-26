package com.calogardev.pizzarella.service;

public interface SecurityService {

    /**
     * Logs in a User by its password.
     * 
     * @param username
     * @param password
     * @return Boolean true if login is successful
     */
    public Boolean login(String username, String password);

    /**
     * Checks if the current user is logged in.
     * 
     * @return Boolean if current user is logged in
     */
    public Boolean isLoggedIn();

    /**
     * Logs out a User
     */
    public void logout();

    /**
     * Finds the username of the current logged in user
     * 
     * @return
     */
    public String findLoggedInUsername();

    /**
     * Returns true or false if the current logged in user has a specific role
     * 
     * @param roleName
     * @return
     */
    public Boolean currentUserHasRole(String roleName);
}
