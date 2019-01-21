package com.samuylov.projectstart.ui.form;

import com.samuylov.projectstart.dto.BookDto;
import com.samuylov.projectstart.dto.ChapterDto;
import com.samuylov.projectstart.entity.ChapterEntity;
import com.samuylov.projectstart.service.BookService;
import com.vaadin.data.Binder;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.ViewScope;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.util.List;

@SpringComponent
@ViewScope
public class ChapterForm extends AbstractForm<ChapterDto, ChapterEntity> {

    @Autowired
    private BookService bookService;

    private Label idLabel;
    private ComboBox<BookDto> bookSearch;
    private TextField chapterNumberTextField;
    private TextField nameTextField;
    private TextField textTextField;

    @PostConstruct
    private void postConstruct() {
        idLabel = new Label();
        idLabel.setCaption("Id");
        idLabel.setContentMode(ContentMode.TEXT);

        chapterNumberTextField = getTextField("Chapter number", "Enter chapter number...");
        nameTextField = getTextField("Chapter name", "Enter chapter name...");
        textTextField = getTextField("Chapter text", "Enter chapter text...");

        setBookSearchComboBox();

        windowContent.addComponents(bookSearch);
        windowContent.addComponents(idLabel);
        windowContent.addComponent(chapterNumberTextField);
        windowContent.addComponent(nameTextField);
        windowContent.addComponent(textTextField);
        windowContent.addComponents(buttonsLayout);

        setBinder();

        this.setContent(windowContent);
    }

    @Override
    public void init(ChapterDto dto, CloseFormFunction closeFunc) {
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
            chapterNumberTextField.setVisible(true);
            nameTextField.setVisible(true);
            textTextField.setVisible(true);

            idLabel.setValue(Long.toString(dto.getId()));
            binder.readBean(dto);
        } else {
            idLabel.setVisible(false);
            chapterNumberTextField.setVisible(false);
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
            chapterNumberTextField.setVisible(true);
            nameTextField.setVisible(true);
            textTextField.setVisible(true);
        });
    }

    @Override
    protected void setBinder() {
        binder = new Binder<>(ChapterDto.class);

        binder.forField(bookSearch)
                .asRequired("Choose book")
                .withConverter(BookDto::getId, v -> bookService.getBookById(v))
                .bind(ChapterDto::getBookId, ChapterDto::setBookId);
        binder.forField(chapterNumberTextField)
                .asRequired("Enter chapter number")
                .withConverter(Long::valueOf, String::valueOf)
                .bind(ChapterDto::getNumber, ChapterDto::setNumber);
        binder.forField(nameTextField)
                .asRequired("Enter chapter name")
                .bind(ChapterDto::getName, ChapterDto::setName);
        binder.forField(textTextField)
                .asRequired("Enter chapter text")
                .bind(ChapterDto::getText, ChapterDto::setText);
    }
}
