package com.samuylov.projectstart.ui;

import com.samuylov.projectstart.ui.view.BooksListView;
import com.samuylov.projectstart.ui.view.CommentsListView;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewDisplay;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.spring.annotation.SpringViewDisplay;
import com.vaadin.spring.navigator.SpringNavigator;
import com.vaadin.ui.*;
import org.springframework.beans.factory.annotation.Autowired;

@SpringUI
@SpringViewDisplay
public class BookReaderUI extends UI implements ViewDisplay {

    private Panel viewDisplay;

    @Autowired
    private SpringNavigator navigator;

    @Override
    protected void init(VaadinRequest vaadinRequest) {

        HorizontalLayout pageStructLayout = new HorizontalLayout();
        pageStructLayout.setSizeFull();
        VerticalLayout listViewsButtonsLayout = new VerticalLayout();

        Button booksListViewButton = createNavigationButton("Books", BooksListView.NAME);
        Button commentsListViewButton = createNavigationButton("Comments", CommentsListView.NAME);
        listViewsButtonsLayout.addComponent(booksListViewButton);
        listViewsButtonsLayout.addComponent(commentsListViewButton);

        viewDisplay = new Panel();
        viewDisplay.setSizeFull();

        navigator.init(this, viewDisplay);

        pageStructLayout.addComponent(listViewsButtonsLayout);
        pageStructLayout.addComponent(viewDisplay);

        pageStructLayout.setExpandRatio(listViewsButtonsLayout, 1);
        pageStructLayout.setExpandRatio(viewDisplay, 9);

        setContent(pageStructLayout);

        navigator.navigateTo(BooksListView.NAME);
    }

    private Button createNavigationButton(final String buttonCaption, final String viewName) {
        Button button = new Button(buttonCaption);
        button.addClickListener(clickEvent -> getUI().getNavigator().navigateTo(viewName));

        return button;
    }

    @Override
    public void showView(View view) {
        viewDisplay.setContent((Component) view);
    }
}
