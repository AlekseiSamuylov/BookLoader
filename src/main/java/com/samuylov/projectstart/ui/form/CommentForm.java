package com.samuylov.projectstart.ui.form;

import com.samuylov.projectstart.dto.BookDto;
import com.samuylov.projectstart.dto.CommentDto;
import com.samuylov.projectstart.entity.CommentEntity;
import com.samuylov.projectstart.service.BookService;
import com.vaadin.data.Binder;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.ViewScope;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.DateField;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

@SpringComponent
@ViewScope
public class CommentForm extends AbstractForm<CommentDto, CommentEntity> {

    @Autowired
    private BookService bookService;

    private Label idLabel;
    private ComboBox<BookDto> bookSearch;
    private TextField nameTextField;
    private TextField textTextField;
    private DateField dateField;

    @PostConstruct
    private void postConstruct() {
        idLabel = new Label();
        idLabel.setCaption("Id");
        idLabel.setContentMode(ContentMode.TEXT);

        nameTextField = getTextField("User name", "Enter user name...");
        textTextField = getTextField("Comment text", "Enter text...");

        dateField = new DateField("Registration date");
        dateField.setTextFieldEnabled(false);

        setBookSearchComboBox();

        windowContent.addComponents(bookSearch);
        windowContent.addComponents(idLabel);
        windowContent.addComponent(dateField);
        windowContent.addComponent(nameTextField);
        windowContent.addComponent(textTextField);
        windowContent.addComponents(buttonsLayout);

        setBinder();

        this.setContent(windowContent);
    }

    @Override
    public void init(CommentDto dto, CloseFormFunction closeFunc) {
        this.dto = dto;
        this.closeFunc = closeFunc;
        setData();
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
            dateField.setVisible(true);
            nameTextField.setVisible(true);
            textTextField.setVisible(true);

            idLabel.setValue(Long.toString(dto.getId()));
            dateField.setValue(
                    Instant.ofEpochMilli(dto.getDate().getTime()).atZone(ZoneId.systemDefault()).toLocalDate());
            binder.readBean(dto);
        } else {
            idLabel.setVisible(false);
            dateField.setVisible(false);
            nameTextField.setVisible(false);
            textTextField.setVisible(false);
        }
    }

    private void setBookSearchComboBox() {
        List<BookDto> books = bookService.getList();

        bookSearch = new ComboBox<>();
        bookSearch.setCaption("Book");
        bookSearch.setPlaceholder("Enter book name...");
        bookSearch.setItems(books);
        bookSearch.setItemCaptionGenerator(BookDto::getName);
        bookSearch.setEmptySelectionAllowed(false);

        bookSearch.addSelectionListener(singleSelectionEvent -> {
            nameTextField.setVisible(true);
            textTextField.setVisible(true);
        });
    }

    @Override
    protected void setBinder() {
        binder = new Binder<>(CommentDto.class);

        binder.forField(bookSearch)
                .asRequired("Choose book")
                .withConverter(BookDto::getId, v -> bookService.getBookById(v))
                .bind(CommentDto::getBookId, CommentDto::setBookId);
        binder.forField(nameTextField)
                .asRequired("Enter chapter name")
                .bind(CommentDto::getName, CommentDto::setName);
        binder.forField(textTextField)
                .asRequired("Enter chapter text")
                .bind(CommentDto::getText, CommentDto::setText);
    }
}
