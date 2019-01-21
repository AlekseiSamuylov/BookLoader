package com.samuylov.projectstart.ui.form;

import com.samuylov.projectstart.dto.BookDto;
import com.samuylov.projectstart.entity.BookEntity;
import com.vaadin.data.Binder;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.ViewScope;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import net.bytebuddy.implementation.bind.annotation.FieldValue;

import javax.annotation.PostConstruct;

@SpringComponent
@ViewScope
public class BookForm extends AbstractForm<BookDto, BookEntity> {

    private Label idLabel;
    private TextField nameTextField;
    private TextField descriptionTextField;
    private Label ratingLabel;

    @PostConstruct
    private void postConstruct() {
        idLabel = getLabel("Id");
        nameTextField = getTextField("Name", "Enter book name...");
        descriptionTextField =
                getTextField("Description", "Enter description...");
        ratingLabel = getLabel("Rating");

        windowContent.addComponent(idLabel);
        windowContent.addComponents(nameTextField);
        windowContent.addComponents(descriptionTextField);
        windowContent.addComponent(ratingLabel);

        buttonsLayout.addComponent(saveChangesButton);
        buttonsLayout.addComponent(closeButton);

        windowContent.addComponent(buttonsLayout);

        setBinder();
        setContent(windowContent);
    }

    @Override
    public void init(final BookDto dto, final CloseFormFunction closeFunc) {
        this.dto = dto;
        this.closeFunc = closeFunc;
        setData();
    }

    private Label getLabel(final String caption) {
        Label label = new Label();
        label.setCaption(caption);
        label.setContentMode(ContentMode.TEXT);

        return label;
    }

    private TextField getTextField(final String caption, final String placeholder) {
        TextField textField = new TextField();
        textField.setCaption(caption);
        textField.setPlaceholder(placeholder);

        return textField;
    }

    private void setData() {
        if (dto.getId() != null) {
            idLabel.setVisible(true);
            ratingLabel.setVisible(true);

            idLabel.setValue(Long.toString(dto.getId()));
            ratingLabel.setValue(Long.toString(dto.getRating()));
            binder.readBean(dto);
        } else {
            idLabel.setVisible(false);
            ratingLabel.setVisible(false);
        }
    }

    @Override
    protected void setBinder() {
        binder = new Binder<>(BookDto.class);

        binder.forField(nameTextField)
                .asRequired("Enter book name")
                .bind(BookDto::getName, BookDto::setName);
        binder.forField(descriptionTextField)
                .asRequired("Enter description")
                .bind(BookDto::getDescription, BookDto::setDescription);
    }
}
