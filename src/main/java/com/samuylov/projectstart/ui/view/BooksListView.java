package com.samuylov.projectstart.ui.view;

import com.samuylov.projectstart.dto.BookDto;
import com.samuylov.projectstart.service.BookService;
import com.vaadin.data.Binder;
import com.vaadin.data.ValidationException;
import com.vaadin.data.provider.DataProvider;
import com.vaadin.data.provider.ListDataProvider;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.SerializablePredicate;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Locale;
import java.util.Set;

@SpringView(name = BooksListView.NAME)
public class BooksListView extends VerticalLayout implements View {

    @Autowired
    private BookService bookService;

    public static final String NAME = "books";
    private Grid<BookDto> grid;
    private ListDataProvider<BookDto> dataProvider;

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        dataProvider = DataProvider.ofCollection(bookService.getBooksList());

        VerticalLayout pageStructLayout = new VerticalLayout();
        pageStructLayout.setSizeFull();
        HorizontalLayout menuLayout = new HorizontalLayout();

        Button addBookButton = getAddBookButton();
        Button editBookButton = getEditBookButton();
        Button deleteBookButton = getDeleteBookButton();
        TextField filterByBookName = getBookFilterTextField();

        menuLayout.addComponent(addBookButton);
        menuLayout.addComponent(editBookButton);
        menuLayout.addComponent(deleteBookButton);
        menuLayout.addComponent(filterByBookName);

        grid = getGrid(editBookButton, deleteBookButton);

        pageStructLayout.addComponent(menuLayout);
        pageStructLayout.addComponent(grid);

        this.addComponent(pageStructLayout);
    }

    private Button getAddBookButton() {
        Button addBookButton = new Button("Add");
        addBookButton.addClickListener(clickEvent -> {
            Window addBookWindow = getEditForm("Add book");

            HorizontalLayout buttonsLayout = new HorizontalLayout();
            FormLayout windowContent = new FormLayout();
            windowContent.setMargin(true);
            windowContent.addStyleName("outlined");
            windowContent.setSizeFull();

            final TextField nameTextField = new TextField("Name");
            nameTextField.setPlaceholder("Enter book name...");
            windowContent.addComponent(nameTextField);

            final TextField descriptionTextField = new TextField("Description");
            descriptionTextField.setPlaceholder("Enter description...");
            windowContent.addComponent(descriptionTextField);

            Button addButton = new Button("Add");
            Button closeButton = new Button("Close");

            final BookDto bookDto = new BookDto();
            Binder<BookDto> binder = new Binder<>(BookDto.class);

            binder.bind(nameTextField, BookDto::getName, BookDto::setName);
            binder.bind(descriptionTextField, BookDto::getDescription, BookDto::setDescription);

            addButton.addClickListener(clickEvent1 -> {
                try {
                    binder.writeBean(bookDto);
                    binder.readBean(new BookDto());
                    bookService.createBook(bookDto);
                } catch (ValidationException e) {
                    Notification.show("Book could not be saved, " +
                            "please check error messages for each field.");
                }
            });

            closeButton.addClickListener(clickEvent1 -> {
                refreshDataProvider();
                addBookWindow.close();
            });

            buttonsLayout.addComponent(addButton);
            buttonsLayout.addComponent(closeButton);

            windowContent.addComponent(buttonsLayout);

            addBookWindow.setContent(windowContent);

            getUI().addWindow(addBookWindow);
        });

        return addBookButton;
    }

    private Button getEditBookButton() {
        Button editBookButton = new Button("Edit");
        editBookButton.addClickListener(clickEvent -> {
            Window editUserWindow = getEditForm("Edit user");

            BookDto bookDtoEdited = new BookDto();
            for (BookDto bookEntityGrid : grid.getSelectedItems()) {
                bookDtoEdited = bookEntityGrid;
            }

            HorizontalLayout buttonsLayout = new HorizontalLayout();
            FormLayout windowContent = new FormLayout();
            windowContent.setMargin(true);
            windowContent.addStyleName("outlined");
            windowContent.setSizeFull();

            final Label idLabel = new Label(Long.toString(bookDtoEdited.getId()));
            idLabel.setCaption("Id");
            idLabel.setContentMode(ContentMode.TEXT);
            windowContent.addComponent(idLabel);

            final TextField nameTextField = new TextField("Name", bookDtoEdited.getName());
            nameTextField.setPlaceholder("Enter book name...");
            windowContent.addComponent(nameTextField);

            final TextField descriptionTextField = new TextField("Description", bookDtoEdited.getDescription());
            descriptionTextField.setPlaceholder("Enter description...");
            windowContent.addComponent(descriptionTextField);

            final Label ratingLabel = new Label(Integer.toString(bookDtoEdited.getRating()));
            ratingLabel.setCaption("Rating");
            ratingLabel.setContentMode(ContentMode.TEXT);
            windowContent.addComponent(ratingLabel);

            Button editButton = new Button("Edit");
            Button closeButton = new Button("Close");

            final BookDto bookDto = bookDtoEdited;
            Binder<BookDto> binder = new Binder<>(BookDto.class);

            binder.bind(nameTextField, BookDto::getName, BookDto::setName);
            binder.bind(descriptionTextField, BookDto::getDescription, BookDto::setDescription);

            editButton.addClickListener(clickEvent1 -> {
                try {
                    binder.writeBean(bookDto);
                    binder.readBean(new BookDto());
                    bookService.updateBook(bookDto.getId(), bookDto);
                    refreshDataProvider();
                    grid.deselectAll();
                    editUserWindow.close();
                } catch (ValidationException e) {
                    Notification.show("Book could not be saved, " +
                            "please check error messages for each field.");
                }
            });

            closeButton.addClickListener(clickEvent1 -> {
                grid.deselectAll();
                editUserWindow.close();
            });

            buttonsLayout.addComponent(editButton);
            buttonsLayout.addComponent(closeButton);

            windowContent.addComponent(buttonsLayout);

            editUserWindow.setContent(windowContent);

            getUI().addWindow(editUserWindow);

        });
        editBookButton.setEnabled(false);

        return editBookButton;
    }

    private Window getEditForm(final String formName) {
        Window window = new Window(formName);
        window.setHeight("90%");
        window.setWidth("98%");
        window.center();
        window.setModal(true);
        window.setClosable(false);
        window.setResizable(false);

        return window;
    }

    private Button getDeleteBookButton() {
        Button deleteBookButton = new Button("Delete");
        deleteBookButton.addClickListener(clickEvent -> {
            for (BookDto bookDtoGrid : grid.getSelectedItems()) {
                bookService.deleteBook(bookDtoGrid.getId());
            }

            refreshDataProvider();
            grid.deselectAll();
        });
        deleteBookButton.setEnabled(false);

        return deleteBookButton;
    }

    private TextField getBookFilterTextField() {
        TextField filter = new TextField();
        filter.setPlaceholder("Filter by name...");
        filter.addValueChangeListener(valueChangeEvent -> {
            dataProvider.setFilter(BookDto::getName, name -> {
                String nameLower = (name == null) ? "" : name.toLowerCase(Locale.ENGLISH);
                String filterLower = valueChangeEvent.getValue().toLowerCase(Locale.ENGLISH);

                return nameLower.contains(filterLower);
            });
        });

        return filter;
    }

    private Grid<BookDto> getGrid(final Button editBookButton, final Button deleteBookButton) {
        Grid<BookDto> grid = new Grid<>();
        grid.setCaption("Book list");

        grid.addColumn(BookDto::getId).setCaption("Id");
        grid.addColumn(BookDto::getName).setCaption("Name");
        grid.addColumn(BookDto::getDescription).setCaption("Description");
        grid.addColumn(BookDto::getRating).setCaption("Rating");

        grid.setDataProvider(dataProvider);
        grid.setSizeFull();
        grid.setSelectionMode(Grid.SelectionMode.MULTI);

        grid.addSelectionListener(selectionEvent -> {
            Set<BookDto> selected = selectionEvent.getAllSelectedItems();

            if (selected.size() > 0) {
                deleteBookButton.setEnabled(true);
                editBookButton.setEnabled(true);

                if (selected.size() > 1) {
                    editBookButton.setEnabled(false);
                }
            } else {
                deleteBookButton.setEnabled(false);
                editBookButton.setEnabled(false);
            }

        });

        return grid;
    }

    private void refreshDataProvider() {
        SerializablePredicate<BookDto> filter = dataProvider.getFilter();
        dataProvider = DataProvider.ofCollection(bookService.getBooksList());
        dataProvider.setFilter(filter);
        grid.setDataProvider(dataProvider);
    }


}
