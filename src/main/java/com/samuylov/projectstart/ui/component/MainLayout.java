package com.samuylov.projectstart.ui.component;

import com.samuylov.projectstart.ui.component.menu.MainMenu;
import com.vaadin.navigator.ViewDisplay;

public interface MainLayout extends ComponentWrapper {

    MainMenu getMenu();

    ViewDisplay getViewContainer();
}
