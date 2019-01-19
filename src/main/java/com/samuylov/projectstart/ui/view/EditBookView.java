package com.samuylov.projectstart.ui.view;

import com.samuylov.projectstart.dto.BookDto;
import com.vaadin.data.provider.ConfigurableFilterDataProvider;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.ViewScope;
import com.vaadin.ui.TextField;

@SpringView(name = EditBookView.NAME)
@ViewScope
//@Secured("ADMIN")
public class EditBookView extends AbstractAdministrateBookListView<BookDto> {

    public static final String NAME = "booksEditor";

    @Override
    protected void setFilterTextField() {
        filterTextField = new TextField();
        filterTextField.setPlaceholder("Filter by name...");
        filterTextField.addValueChangeListener(valueChangeEvent -> {
            ((ConfigurableFilterDataProvider<BookDto, Void, String>)
                    grid.getDataProvider()).setFilter(valueChangeEvent.getValue());
        });
    }

    @Override
    protected void setGridColumns() {
        grid.addColumn(BookDto::getId).setCaption("Id");
        grid.addColumn(BookDto::getRating).setCaption("Rating");
        grid.addColumn(BookDto::getName).setCaption("Name");
        grid.addColumn(BookDto::getDescription).setCaption("Description");
    }
}
