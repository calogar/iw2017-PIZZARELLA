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
	// System.out.println("bean name: " + beanName);
	// System.out.println("User: " +
	// securityService.findLoggedInUsername());

	if (beanName.equals("homeView")) {
	    return true;
	}

	if (securityService.currentUserHasRole("ROLE_MANAGER")) {
	    // Managers can access all the views
	    return true;
	} else if (securityService.currentUserHasRole("ROLE_ATTENDANT")) {
	    // Attendants can only manage orders
	    return beanName.equals("ordersView") || beanName.equals("createOrdersView")
		    || beanName.equals("altOrdersView");
	} else if (securityService.currentUserHasRole("ROLE_ATTENDANT")) {
	    // Attendants can only manage orders
	    return beanName.equals("ordersView") || beanName.equals("createOrdersView")
		    || beanName.equals("altOrdersView");
	} else {
	    return false;
	}

    }
}
