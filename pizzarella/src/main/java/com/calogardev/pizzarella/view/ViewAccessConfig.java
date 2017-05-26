package com.calogardev.pizzarella.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.calogardev.pizzarella.service.SecurityService;
import com.vaadin.spring.access.ViewAccessControl;
import com.vaadin.ui.UI;

@Component
public class ViewAccessConfig implements ViewAccessControl {

    @Autowired
    SecurityService securityService;

    @Override
    public boolean isAccessGranted(UI ui, String beanName) {
	if (securityService.currentUserHasRole("ROLE_MANAGER")) {
	    // Managers can access all the views
	    return true;
	} else if (beanName.equals("ordersView") || beanName.equals("altOrdersView")) {
	    // TODO remove altOrdersView
	    // Only managers and waiters can do orders
	    return securityService.currentUserHasRole("ROLE_MANAGER")
		    || securityService.currentUserHasRole("ROLE_WAITER");
	} else if (beanName.equals("usersView") || beanName.equals("productsView")
		|| beanName.equals("productFamiliesView")) {
	    // Only managers can create new users, products, product families...
	    return securityService.currentUserHasRole("ROLE_MANAGER");
	} else
	    return true;
    }
}
