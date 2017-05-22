package com.calogardev.pizzarella;

import com.vaadin.event.ShortcutAction;
import com.vaadin.server.Page;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

public class LoginScreen extends CssLayout {

	private static final long serialVersionUID = 3051999240621717195L;
	public static final String VIEW_NAME = "Login";
	public static final String VIEW_ROUTE = "Login";

	private TextField usernameField;
	private PasswordField passwordField;
	private Button loginButton;
	private Button forgotPasswordButton;

	@FunctionalInterface
	public interface LoginCallback {
		boolean renderMainIfLogin(String username, String password);
	}

	public LoginScreen(LoginCallback callback) {

		VerticalLayout centeredLayout = new VerticalLayout();
		Component loginForm = buildLoginForm(callback);
		CssLayout loginInformation = buildLoginInformation();

		centeredLayout.setMargin(false);
		centeredLayout.setSpacing(false);
		centeredLayout.setStyleName("centering-layout");
		centeredLayout.addComponent(loginForm);
		centeredLayout.setComponentAlignment(loginForm, Alignment.MIDDLE_CENTER);

		centeredLayout.addComponents(loginForm, loginInformation);
		addComponent(centeredLayout);
	}

	private Component buildLoginForm(LoginCallback callback) {
		FormLayout loginForm = new FormLayout();

		usernameField = new TextField("Username", "Enter your username");
		passwordField = new PasswordField("Password");
		loginButton = new Button("Login");
		forgotPasswordButton = new Button("Forgot password?");

		loginForm.addComponent(usernameField);
		loginForm.addComponent(passwordField);

		CssLayout buttonsLayout = new CssLayout();
		loginButton.addClickListener(e -> {
			try {
				loginButton.setEnabled(false); // So we can't click login again
				String inputPassword = passwordField.getValue();
				passwordField.setValue(""); // Reset field value

				if (!callback.renderMainIfLogin(usernameField.getValue(), inputPassword)) {
					// If the login callback succeeds, it will render the
					// MainView (see implementation in MainUI)
					showNotification(new Notification("Invalid username or password"));
				}
			} finally {
				loginButton.setEnabled(true); // Can login again
			}
		});

		loginButton.setClickShortcut(ShortcutAction.KeyCode.ENTER);
		buttonsLayout.addComponents(loginButton, forgotPasswordButton);
		buttonsLayout.addStyleName("buttons");
		loginForm.addComponent(buttonsLayout);

		// TODO Add forgot password button

		// Add styling
		loginForm.addStyleName("login-form");
		loginForm.setSizeUndefined();
		loginForm.setMargin(false);
		usernameField.setWidth(15, Unit.EM);
		passwordField.setWidth(15, Unit.EM);
		buttonsLayout.setStyleName("buttons");
		loginButton.addStyleName(ValoTheme.BUTTON_FRIENDLY);
		forgotPasswordButton.addStyleName(ValoTheme.BUTTON_LINK);

		return loginForm;
	}

	private CssLayout buildLoginInformation() {
		return new CssLayout();
	}

	private void showNotification(Notification notification) {
		notification.setDelayMsec(2000);
		notification.show(Page.getCurrent());
	}
}
