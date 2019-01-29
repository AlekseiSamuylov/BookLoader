package com.samuylov.projectstart.ui.component;

import com.samuylov.projectstart.ui.component.menu.HorizontalMainMenu;
import com.samuylov.projectstart.ui.component.menu.MainMenu;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewDisplay;
import com.vaadin.spring.annotation.SpringViewDisplay;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.AbstractComponent;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

import javax.annotation.PostConstruct;

@UIScope
@SpringViewDisplay
public class VerticalMainLayout extends VerticalLayout implements MainLayout, ViewDisplay {

    private final MainMenu menu;
    private Panel viewContainer;

    public VerticalMainLayout(HorizontalMainMenu menu) {
        this.menu = menu;
    }

    @PostConstruct
    public void init(){
        setSizeFull();
        viewContainer = new Panel();
        viewContainer.setSizeFull();
        addComponent(menu.getUnderlying());
        addComponentsAndExpand(viewContainer);
        setExpandRatio(menu.getUnderlying(), 1);
        setExpandRatio(viewContainer, 20);
    }

    @Override
    public AbstractComponent getUnderlying() {
        return this;
    }

    @Override
    public MainMenu getMenu() {
        return menu;
    }

    @Override
    public ViewDisplay getViewContainer() {
        return this;
    }

    @Override
    public void showView(final View view) {
        viewContainer.setContent(view.getViewComponent());
    }
}
