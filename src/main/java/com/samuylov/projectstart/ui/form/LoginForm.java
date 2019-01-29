package com.samuylov.projectstart.ui.form;

import com.samuylov.projectstart.dto.UserCredentials;
import com.samuylov.projectstart.dto.UserDto;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.ViewScope;
import com.vaadin.ui.*;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.util.function.Consumer;

@SpringComponent
@ViewScope
public class LoginForm extends FormLayout {

    //@Autowired
    //private UserForm registrationForm;
    //private Window registrationWindow;

    private TextField userNameField;
    private PasswordField passwordField;
    private Button loginButton;
    //private Button registerButton;
    private UserCredentials credentials;
    private Consumer<UserCredentials> onLogin;

    @PostConstruct
    public void postConstruct() {
        this.credentials = new UserCredentials();

        setSizeUndefined();
        setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
        userNameField = createUsernameField();
        passwordField = createPasswordField();
        loginButton = getLoginButton();
        //registerButton = createRegisterButton();
        //addComponents(userNameField, passwordField, new HorizontalLayout(loginButton));
        //setupRegistrationWindow();
        addComponent(userNameField);
        addComponent(passwordField);
        addComponent(loginButton);
    }

    private Button getLoginButton() {
        Button loginButton = new Button("Login");
        loginButton.addClickListener(click -> {
            final String username = userNameField.getValue();
            final String password = passwordField.getValue();
            if (username.equals("") || password.equals("")) {
                Notification.show("Please provide username and password");
            } else {
                credentials.setPassword(password);
                credentials.setUsername(username);
                onLogin.accept(credentials);
            }
        });
        return loginButton;
    }

//    private Button createRegisterButton() {
//        final Button registerButton = new Button("Register");
//        registerButton.addClickListener(click -> {
//            registrationForm.init(new UserDto(), () -> {});
//            registrationForm.getSaveButton().setCaption("Register");
//            registrationForm.hideRoleField();
//            UI.getCurrent().addWindow(registrationWindow);
//        });
//        return registerButton;
//    }

//    private void setupRegistrationWindow() {
//        registrationWindow = new Window("New user registration");
//        registrationWindow.setClosable(false);
//        registrationWindow.setModal(true);
//        registrationWindow.setDraggable(false);
//        registrationWindow.setResizable(false);
//        registrationWindow.setWidth(80, Unit.PERCENTAGE);
//        registrationWindow.setHeight(80, Unit.PERCENTAGE);
//    }

    private PasswordField createPasswordField() {
        return new PasswordField("Password");
    }

    private TextField createUsernameField() {
        return new TextField("Username");
    }

    public void setOnLogin(final Consumer<UserCredentials> onLogin) {
        this.onLogin = onLogin;
    }
}
