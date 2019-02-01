package com.samuylov.projectstart.ui.view;

import com.samuylov.projectstart.dto.BookDto;
import com.samuylov.projectstart.service.BookService;
import com.vaadin.data.provider.CallbackDataProvider;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.*;
import org.springframework.beans.factory.annotation.Autowired;

@SpringView(name = BooksListView.NAME)
public class BooksListView extends VerticalLayout implements View {

    @Autowired
    private BookService bookService;

    public static final String NAME = "books";
    private Grid<BookDto> grid;

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        this.setSizeFull();
        setGrid();
        addComponent(grid);
    }

    private void setGrid() {
        grid = new Grid<>();
        grid.setSizeFull();
        grid.setCaption("Click on book");

        grid.addColumn(BookDto::getRating).setCaption("Rating");
        grid.addColumn(BookDto::getName).setCaption("Book");
        grid.addColumn(BookDto::getDescription).setCaption("Description");

        grid.setDataProvider(new CallbackDataProvider<BookDto, String>(
                q -> bookService.findWithPagination(q),
                q ->(int) bookService.findWithPagination(q).count()).withConfigurableFilter());
        grid.getDataProvider().refreshAll();

        grid.addSelectionListener(e -> {
            BookDto book = e.getFirstSelectedItem().get();

            UI.getCurrent().getNavigator().navigateTo(BookView.NAME + "/" + book.getId());
        });
    }
}
