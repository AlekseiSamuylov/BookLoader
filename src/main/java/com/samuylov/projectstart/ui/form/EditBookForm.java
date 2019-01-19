package com.samuylov.projectstart.ui.form;

import com.samuylov.projectstart.dto.BookDto;
import com.samuylov.projectstart.ui.annotations.FormType;
import com.vaadin.data.ValidationException;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.ViewScope;
import com.vaadin.ui.*;

import javax.annotation.PostConstruct;

@SpringComponent
@FormType("edit")
@ViewScope
public class EditBookForm extends AbstractForm<BookDto>{

    private Label idLabel;
    private TextField nameTextField;
    private TextField descriptionTextField;
    private Label ratingLabel;
    private BookDto bookDtoEdited;

    @PostConstruct
    public void initContent() {
        this.setCaption("Edit user");

        bookDtoEdited = new BookDto();
        for (BookDto bookEntityGrid : grid.getSelectedItems()) {
            bookDtoEdited = bookEntityGrid;
        }

        idLabel = new Label(Long.toString(bookDtoEdited.getId()));
        idLabel.setCaption("Id");
        idLabel.setContentMode(ContentMode.TEXT);
        windowContent.addComponent(idLabel);

        nameTextField = new TextField("Name", bookDtoEdited.getName());
        nameTextField.setPlaceholder("Enter book name...");
        windowContent.addComponent(nameTextField);

        descriptionTextField = new TextField("Description", bookDtoEdited.getDescription());
        descriptionTextField.setPlaceholder("Enter description...");
        windowContent.addComponent(descriptionTextField);

        ratingLabel = new Label(Integer.toString(bookDtoEdited.getRating()));
        ratingLabel.setCaption("Rating");
        ratingLabel.setContentMode(ContentMode.TEXT);
        windowContent.addComponent(ratingLabel);

        buttonsLayout.addComponent(saveChangesButton);
        buttonsLayout.addComponent(closeButton);

        windowContent.addComponent(buttonsLayout);

        setBinder();
        setContent(windowContent);
    }

    @Override
    protected void setSaveChangesButton() {
        saveChangesButton = new Button("Edit");
        final BookDto bookDto = bookDtoEdited;

        saveChangesButton.addClickListener(clickEvent1 -> {
            try {
                binder.writeBean(bookDto);
                binder.readBean(new BookDto());
                service.update(bookDto.getId(), bookDto);
                grid.deselectAll();
                this.close();
            } catch (ValidationException e) {
                Notification.show("Book could not be saved, " +
                        "please check error messages for each field.");
            }
        });
    }

    @Override
    protected void setBinder() {
        binder.bind(nameTextField, BookDto::getName, BookDto::setName);
        binder.bind(descriptionTextField, BookDto::getDescription, BookDto::setDescription);
    }
}
