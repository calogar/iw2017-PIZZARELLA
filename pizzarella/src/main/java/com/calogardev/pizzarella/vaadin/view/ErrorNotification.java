package com.calogardev.pizzarella.vaadin.view;

import com.vaadin.server.Page;
import com.vaadin.ui.Notification;

public class ErrorNotification {

    private String submessage = "\nClick to close.";

    public ErrorNotification(String message) {
	new Notification(message, submessage, Notification.Type.ERROR_MESSAGE, true).show(Page.getCurrent());
    }
}
