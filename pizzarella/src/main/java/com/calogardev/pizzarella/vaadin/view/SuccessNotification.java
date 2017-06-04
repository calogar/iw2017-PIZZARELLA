package com.calogardev.pizzarella.vaadin.view;

import com.vaadin.server.Page;
import com.vaadin.ui.Notification;

public class SuccessNotification {

    private String message = "Success";
    private String submessage = "\nThe record has been saved in the database";

    public SuccessNotification() {
	new Notification(message, submessage, Notification.Type.TRAY_NOTIFICATION, true).show(Page.getCurrent());
    }
}
