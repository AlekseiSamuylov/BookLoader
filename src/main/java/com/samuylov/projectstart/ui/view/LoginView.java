package com.samuylov.projectstart.ui.view;

import com.samuylov.projectstart.ui.form.LoginForm;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.spring.security.VaadinSecurity;

import javax.naming.AuthenticationException;

@SpringView(name = LoginView.NAME)
public class LoginView extends HorizontalLayout implements View {

    public static final String NAME = "myLogin";

    @Autowired
    private LoginForm loginForm;

    @Autowired
    private VaadinSecurity vaadinSecurity;

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        setSizeFull();
        //setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
        addComponent(loginForm);
        loginForm.setOnLogin(credentials -> {
            try {
                vaadinSecurity.login(credentials.getUsername(), credentials.getPassword());
                getUI().getPage().reload();
            } catch (AuthenticationException e) {
                Notification.show("Wrong credentials", Notification.Type.WARNING_MESSAGE);
            } catch (Exception e) {
                Notification.show("Something bad just happened...", Notification.Type.WARNING_MESSAGE);
            }
        });
    }
}
