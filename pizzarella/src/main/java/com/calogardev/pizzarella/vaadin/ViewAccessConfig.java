package com.calogardev.pizzarella.vaadin;

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

	if (beanName.equals("homeView") || beanName.equals("ordersView") || beanName.equals("createOrderView")) {
	    return true;
	} else {
	    return securityService.currentUserHasRole("ROLE_MANAGER");
	}
    }
}
