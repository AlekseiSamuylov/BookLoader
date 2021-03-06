package com.samuylov.projectstart.ui;

import com.samuylov.projectstart.ui.component.MainLayout;
import com.samuylov.projectstart.ui.view.BooksListView;
import com.vaadin.annotations.Push;
import com.vaadin.navigator.PushStateNavigation;
import com.vaadin.server.VaadinRequest;
import com.vaadin.shared.ui.ui.Transport;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.spring.navigator.SpringNavigator;
import com.vaadin.spring.navigator.SpringViewProvider;
import com.vaadin.ui.*;
import org.springframework.beans.factory.annotation.Autowired;

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

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        setSizeFull();
        setContent(mainLayout.getUnderlying());
        navigator.init(this, mainLayout.getViewContainer());

        navigator.navigateTo(BooksListView.NAME);
    }

    public void setMenuVisibility(final boolean visible) {
        mainLayout.getMenu().getUnderlying().setVisible(visible);
    }
}
