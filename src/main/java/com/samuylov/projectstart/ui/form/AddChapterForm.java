package com.samuylov.projectstart.ui.form;

import com.samuylov.projectstart.dto.BookDto;
import com.samuylov.projectstart.dto.ChapterDto;
import com.samuylov.projectstart.service.BookService;
import com.samuylov.projectstart.service.ChapterService;
import com.samuylov.projectstart.ui.annotations.FormType;
import com.vaadin.data.Binder;
import com.vaadin.data.ValidationException;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.ViewScope;
import com.vaadin.ui.*;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.util.List;

@SpringComponent
@FormType("add")
@ViewScope
public class AddChapterForm extends AbstractForm<ChapterDto> {

    @Autowired
    private ChapterService chapterService;

    @Autowired
    private BookService bookService;

    private ComboBox<BookDto> bookSearch;
    private TextField chapterNumberTextField;
    private TextField nameTextField;
    private TextField textTextField;

    @PostConstruct
    public void initContent() {
        this.setCaption("Add chapter");

        chapterNumberTextField = new TextField("Chapter number");
        chapterNumberTextField.setPlaceholder("Enter chapter number...");
        chapterNumberTextField.setVisible(false);

        nameTextField = new TextField("Chapter name");
        nameTextField.setPlaceholder("Enter chapter name...");
        nameTextField.setVisible(false);

        textTextField = new TextField("ChapterText");
        textTextField.setPlaceholder("Enter text...");
        textTextField.setVisible(false);

        setBookSearchComboBox();

        windowContent.addComponents(bookSearch);

        windowContent.addComponent(chapterNumberTextField);
        windowContent.addComponent(nameTextField);
        windowContent.addComponent(textTextField);
        windowContent.addComponents(buttonsLayout);
        setBinder();

        this.setContent(windowContent);
    }

    @Override
    protected void setSaveChangesButton() {
        saveChangesButton = new Button("Save");

        saveChangesButton.addClickListener(clickEvent1 -> {
            try {
                ChapterDto chapterDto = new ChapterDto();
                binder.writeBean(chapterDto);
                chapterDto.setBookId(bookSearch.getSelectedItem().get().getId());
                binder.readBean(new ChapterDto());
                chapterService.create(chapterDto);
            } catch (ValidationException e) {
                Notification.show("Book could not be saved, " +
                        "please check error messages for each field.");
            }
        });
    }

    @Override
    protected void setBinder() {
        binder = new Binder<>(ChapterDto.class);

        binder.forField(chapterNumberTextField).withConverter(Long::valueOf, String::valueOf)
                .bind(ChapterDto::getNumber, ChapterDto::setNumber);
        binder.bind(nameTextField, ChapterDto::getName, ChapterDto::setName);
        binder.bind(textTextField, ChapterDto::getText, ChapterDto::setText);
    }

    private void setBookSearchComboBox() {
        List<BookDto> books = bookService.getList();

        bookSearch = new ComboBox<>();
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

//    private void setChapterSearchComboBox() {
//        List<ChapterDto> chapters = chapterService.getAllByBookId(bookSearch.getSelectedItem().get().getId());
//
//        chapterSearch = new ComboBox<>();
//        chapterSearch.setPlaceholder("Enter chapter name...");
//        chapterSearch.setItems(chapters);
//        chapterSearch.setItemCaptionGenerator(ChapterDto::getName);
//        chapterSearch.setEmptySelectionAllowed(false);
//
//        chapterSearch.addSelectionListener(singleSelectionEvent -> {
//            nameTextField.setVisible(true);
//            textTextField.setVisible(true);
//        });
//    }
}
