package com.samuylov.projectstart.ui.view;

import com.samuylov.projectstart.dto.CommentDto;
import com.samuylov.projectstart.service.BookService;
import com.samuylov.projectstart.service.CommentService;
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

import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.Set;

@SpringView(name = CommentsListView.NAME)
public class CommentsListView extends VerticalLayout implements View {

    @Autowired
    private CommentService commentService;

    @Autowired
    private BookService bookService;

    public static final String NAME = "comments";
    private Grid<CommentDto> grid;
    private ListDataProvider<CommentDto> dataProvider;

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        dataProvider = DataProvider.ofCollection(commentService.getCommentsList());

        VerticalLayout pageStructLayout = new VerticalLayout();
        HorizontalLayout menuLayout = new HorizontalLayout();

        Button addBookButton = getAddCommentButton();
        Button editBookButton = getEditCommentButton();
        Button deleteBookButton = getDeleteCommentButton();
        TextField filterByBookName = getCommentFilterTextField();

        menuLayout.addComponent(addBookButton);
        menuLayout.addComponent(editBookButton);
        menuLayout.addComponent(deleteBookButton);
        menuLayout.addComponent(filterByBookName);

        grid = getGrid(editBookButton, deleteBookButton);

        pageStructLayout.addComponent(menuLayout);
        pageStructLayout.addComponent(grid);

        this.addComponent(pageStructLayout);
    }

    private Button getAddCommentButton() {
        Button addCommentButton = new Button("Add");
        addCommentButton.addClickListener(clickEvent -> {
            Window addCommentWindow = getEditForm("Add comment");

            HorizontalLayout buttonsLayout = new HorizontalLayout();
            FormLayout windowContent = new FormLayout();
            windowContent.setMargin(true);
            windowContent.addStyleName("outlined");
            windowContent.setSizeFull();

            final TextField bookIdTextField = new TextField("Book id");
            bookIdTextField.setPlaceholder("Enter user name...");
            windowContent.addComponent(bookIdTextField);

            final TextField nameTextField = new TextField("Name");
            nameTextField.setPlaceholder("Enter user name...");
            windowContent.addComponent(nameTextField);

            final TextField commentTextField = new TextField("Comment");
            commentTextField.setPlaceholder("Enter comment...");
            windowContent.addComponent(commentTextField);

            Button addButton = new Button("Add");
            Button closeButton = new Button("Close");

            final CommentDto commentDto = new CommentDto();
            Binder<CommentDto> binder = new Binder<>(CommentDto.class);

            binder.forField(bookIdTextField).withConverter(Long::valueOf, String::valueOf)
                    .bind(CommentDto::getBookId, CommentDto::setBookId);
            binder.bind(nameTextField, CommentDto::getNickName, CommentDto::setNickName);
            binder.bind(commentTextField, CommentDto::getText, CommentDto::setText);

            addButton.addClickListener(clickEvent1 -> {
                try {
                    binder.writeBean(commentDto);
                    binder.readBean(new CommentDto());

                    if (bookService.isContains(commentDto.getBookId())) {
                        commentService.create(commentDto);
                    } else {
                        Notification.show("Comment could not be saved, " +
                                "please check book id.");
                    }
                } catch (ValidationException e) {
                    Notification.show("Comment could not be saved, " +
                            "please check error messages for each field.");
                }
            });

            closeButton.addClickListener(clickEvent1 -> {
                refreshDataProvider();
                addCommentWindow.close();
            });

            buttonsLayout.addComponent(addButton);
            buttonsLayout.addComponent(closeButton);

            windowContent.addComponent(buttonsLayout);

            addCommentWindow.setContent(windowContent);

            getUI().addWindow(addCommentWindow);
        });

        return addCommentButton;
    }

    private Button getEditCommentButton() {
        Button editCommentButton = new Button("Edit");
        editCommentButton.addClickListener(clickEvent -> {
            Window editCommentWindow = getEditForm("Edit comment");

            CommentDto commentDtoEdited = new CommentDto();
            for (CommentDto commentDtoGrid : grid.getSelectedItems()) {
                commentDtoEdited = commentDtoGrid;
            }

            HorizontalLayout buttonsLayout = new HorizontalLayout();
            FormLayout windowContent = new FormLayout();
            windowContent.setMargin(true);
            windowContent.addStyleName("outlined");
            windowContent.setSizeFull();

            final Label idLabel = new Label(Long.toString(commentDtoEdited.getId()));
            idLabel.setCaption("Id");
            idLabel.setContentMode(ContentMode.TEXT);
            windowContent.addComponent(idLabel);

            final Label dateLabel =
                    new Label(new SimpleDateFormat("MM/dd/yyyy HH:mm:ss").format(commentDtoEdited.getDate()));
            dateLabel.setCaption("Date");
            dateLabel.setContentMode(ContentMode.TEXT);
            windowContent.addComponent(dateLabel);

            final TextField nameTextField = new TextField("Name", commentDtoEdited.getNickName());
            nameTextField.setPlaceholder("Enter user name...");
            windowContent.addComponent(nameTextField);

            final TextField commentTextField = new TextField("Comment", commentDtoEdited.getText());
            commentTextField.setPlaceholder("Enter comment...");
            windowContent.addComponent(commentTextField);

            final Label bookIdLabel = new Label(Long.toString(commentDtoEdited.getBookId()));
            bookIdLabel.setCaption("Book id");
            bookIdLabel.setContentMode(ContentMode.TEXT);
            windowContent.addComponent(bookIdLabel);

            Button editButton = new Button("Edit");
            Button closeButton = new Button("Close");

            final CommentDto commentDto = commentDtoEdited;
            Binder<CommentDto> binder = new Binder<>(CommentDto.class);

            binder.bind(nameTextField, CommentDto::getNickName, CommentDto::setNickName);
            binder.bind(commentTextField, CommentDto::getText, CommentDto::setText);

            editButton.addClickListener(clickEvent1 -> {
                try {
                    binder.writeBean(commentDto);
                    binder.readBean(new CommentDto());
                    commentService.update(commentDto.getId(), commentDto);
                    refreshDataProvider();
                    grid.deselectAll();
                    editCommentWindow.close();
                } catch (ValidationException e) {
                    Notification.show("Comment could not be saved, " +
                            "please check error messages for each field.");
                }
            });

            closeButton.addClickListener(clickEvent1 -> {
                grid.deselectAll();
                editCommentWindow.close();
            });

            buttonsLayout.addComponent(editButton);
            buttonsLayout.addComponent(closeButton);

            windowContent.addComponent(buttonsLayout);

            editCommentWindow.setContent(windowContent);

            getUI().addWindow(editCommentWindow);

        });
        editCommentButton.setEnabled(false);

        return editCommentButton;
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

    private Button getDeleteCommentButton() {
        Button deleteCommentButton = new Button("Delete");
        deleteCommentButton.addClickListener(clickEvent -> {
            for (CommentDto commentDtoGrid : grid.getSelectedItems()) {
                commentService.delete(commentDtoGrid.getId());
            }

            refreshDataProvider();
            grid.deselectAll();
        });
        deleteCommentButton.setEnabled(false);

        return deleteCommentButton;
    }

    private TextField getCommentFilterTextField() {
        TextField filter = new TextField();
        filter.setPlaceholder("Filter by name...");
        filter.addValueChangeListener(valueChangeEvent -> {
            dataProvider.setFilter(CommentDto::getNickName, name -> {
                String nameLower = (name == null) ? "" : name.toLowerCase(Locale.ENGLISH);
                String filterLower = valueChangeEvent.getValue().toLowerCase(Locale.ENGLISH);

                return nameLower.contains(filterLower);
            });
        });

        return filter;
    }

    private Grid<CommentDto> getGrid(final Button editCommentButton, final Button deleteCommentButton) {
        Grid<CommentDto> grid = new Grid<>();
        grid.setCaption("Book list");


        grid.addColumn(CommentDto::getNickName).setCaption("Name");
        grid.addColumn(CommentDto::getText).setCaption("Text");
        grid.addColumn(CommentDto::getDate).setCaption("Date");
        grid.addColumn(CommentDto::getBookId).setCaption("Book id");
        grid.addColumn(CommentDto::getId).setCaption("Comment id");

        grid.setDataProvider(dataProvider);
        grid.setSizeFull();
        grid.setSelectionMode(Grid.SelectionMode.MULTI);

        grid.addSelectionListener(selectionEvent -> {
            Set<CommentDto> selected = selectionEvent.getAllSelectedItems();

            if (selected.size() > 0) {
                deleteCommentButton.setEnabled(true);
                editCommentButton.setEnabled(true);

                if (selected.size() > 1) {
                    editCommentButton.setEnabled(false);
                }
            } else {
                deleteCommentButton.setEnabled(false);
                editCommentButton.setEnabled(false);
            }

        });

        return grid;
    }

    private void refreshDataProvider() {
        SerializablePredicate<CommentDto> filter = dataProvider.getFilter();
        dataProvider = DataProvider.ofCollection(commentService.getCommentsList());
        dataProvider.setFilter(filter);
        grid.setDataProvider(dataProvider);
    }
}