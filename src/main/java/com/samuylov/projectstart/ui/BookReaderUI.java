package com.samuylov.projectstart.ui;

import com.samuylov.projectstart.ui.component.MainLayout;
import com.samuylov.projectstart.ui.view.BooksListView;
import com.samuylov.projectstart.ui.view.LoginView;
import com.vaadin.annotations.Push;
import com.vaadin.navigator.PushStateNavigation;
import com.vaadin.server.VaadinRequest;
import com.vaadin.shared.ui.ui.Transport;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.spring.navigator.SpringNavigator;
import com.vaadin.spring.navigator.SpringViewProvider;
import com.vaadin.ui.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.spring.security.VaadinSecurity;

@SpringUI
@Push(transport = Transport.WEBSOCKET_XHR)
@PushStateNavigation
public class BookReaderUI extends UI {

    @Autowired
    private SpringNavigator navigator;

    @Autowired
    private SpringViewProvider viewProvider;

    @Autowired
    private MainLayout mainLayout;

    @Autowired
    private VaadinSecurity security;

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        setSizeFull();
        setContent(mainLayout.getUnderlying());
        navigator.init(this, mainLayout.getViewContainer());

        if (security.isAuthenticated()) {
            setMenuVisibility(true);
            navigator.navigateTo(BooksListView.NAME);
        } else {
            setMenuVisibility(false);
            navigator.navigateTo(LoginView.NAME);
        }

        //navigator.navigateTo(BooksListView.NAME);
    }

    public void setMenuVisibility(final boolean visible) {
        mainLayout.getMenu().getUnderlying().setVisible(visible);
    }
}
