package com.samuylov.projectstart.ui.form;

import com.samuylov.projectstart.dto.BookDto;
import com.samuylov.projectstart.ui.annotations.FormType;
import com.vaadin.data.Binder;
import com.vaadin.data.ValidationException;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.ViewScope;
import com.vaadin.ui.*;

import javax.annotation.PostConstruct;

@SpringComponent
@FormType("add")
@ViewScope
public class AddBookForm extends AbstractForm<BookDto> {

    private TextField nameTextField;
    private TextField descriptionTextField;

    @PostConstruct
    public void initContent() {
        this.setCaption("Add book");

        nameTextField = new TextField("Name");
        nameTextField.setPlaceholder("Enter book name...");
        windowContent.addComponent(nameTextField);

        descriptionTextField = new TextField("Description");
        descriptionTextField.setPlaceholder("Enter description...");
        windowContent.addComponent(descriptionTextField);

        windowContent.addComponent(buttonsLayout);
        setBinder();

        this.setContent(windowContent);
    }

    @Override
    protected void setSaveChangesButton() {
        saveChangesButton = new Button("Save");

        saveChangesButton.addClickListener(clickEvent1 -> {
            try {
                BookDto bookDto = new BookDto();
                binder.writeBean(bookDto);
                binder.readBean(new BookDto());
                service.create(bookDto);
            } catch (ValidationException e) {
                Notification.show("Book could not be saved, " +
                        "please check error messages for each field.");
            }
        });
    }

    @Override
    protected void setBinder() {
        binder = new Binder<>(BookDto.class);

        binder.bind(nameTextField, BookDto::getName, BookDto::setName);
        binder.bind(descriptionTextField, BookDto::getDescription, BookDto::setDescription);
    }
}
