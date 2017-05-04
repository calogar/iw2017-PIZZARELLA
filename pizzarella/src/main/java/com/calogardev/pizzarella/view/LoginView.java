package com.calogardev.pizzarella.view;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

@SpringView(name = LoginView.VIEW_ROUTE)
@UIScope
public class LoginView extends VerticalLayout implements View {

	private static final long serialVersionUID = 3051999240621717195L;
	public static final String VIEW_NAME = "Login";
	public static final String VIEW_ROUTE = "Login";

	private TextField username;
	private PasswordField password;
	private Button loginButton;
	private Button forgotPasswordButton;

	@FunctionalInterface
	public interface LoginCallback {
		boolean login(String username, String password);
	}

	public LoginView(LoginCallback callback) {

		Component loginForm = buildLoginForm(callback);

		CssLayout loginInformation = buildLoginInformation();

		addComponents(loginForm, loginInformation);
	}

	private Component buildLoginForm(LoginCallback callback) {
		FormLayout loginForm = new FormLayout();

		loginForm.addComponent(username = new TextField("Username", "Enter your username"));
		loginForm.addComponent(password = new PasswordField("Password"));

		CssLayout buttonsLayout = new CssLayout();
		loginButton.addClickListener(e -> {
			try {

			} finally {

			}
		});
		buttonsLayout.addComponents(loginButton, forgotPasswordButton);
		buttonsLayout.addStyleName("buttons");
		loginForm.addComponent(buttonsLayout);

		return loginForm;
	}

	private CssLayout buildLoginInformation() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void enter(ViewChangeEvent event) {
		username.focus();
	}
}
