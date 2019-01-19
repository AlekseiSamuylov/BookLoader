package com.samuylov.projectstart.ui.view;

import com.samuylov.projectstart.dto.*;
import com.samuylov.projectstart.service.*;
import com.samuylov.projectstart.ui.component.itemlist.ItemChapterListField;
import com.samuylov.projectstart.ui.component.itemlist.ItemCommunityListField;
import com.samuylov.projectstart.ui.component.itemlist.ItemListField;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import org.springframework.beans.factory.annotation.Autowired;

@SpringView(name = BookView.NAME)
public class BookView extends VerticalLayout implements View {

    public static final String NAME = "book";

    @Autowired
    private BookService bookService;

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private ChapterService chapterService;

    @Autowired
    private CommentService commentService;

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {

        final BookDto book = bookService.getBookById(Long.parseLong(event.getParameters()));
        VerticalLayout mainViewLayout = new VerticalLayout();
        mainViewLayout.setSizeFull();

        Label bookName = getLabel(book.getName());
        Label bookDescription = getLabel(book.getDescription());
        Label bookRating = getLabel(Integer.toString(book.getRating()));

        ItemListField<ReviewDto> reviewsList = new ItemCommunityListField<>(reviewService.getAllByBookId(book.getId()));
        reviewsList.setVisible(false);
        Button reviewList = getButton("Reviews", reviewsList);

        ItemListField<ChapterDto> chaptersList = new ItemChapterListField(chapterService.getAllByBookId(book.getId()));
        Button chapterList = getButton("Chapters", chaptersList);

        ItemListField<CommentDto> commentsList = new ItemCommunityListField<>(commentService.getAllByBookId(book.getId()));
        commentsList.setVisible(false);
        Button commentList = getButton("Comments", commentsList);

        mainViewLayout.addComponent(bookName);
        mainViewLayout.addComponent(bookDescription);
        mainViewLayout.addComponent(bookRating);
        mainViewLayout.addComponent(reviewList);
        mainViewLayout.addComponent(reviewsList);
        mainViewLayout.addComponent(chapterList);
        mainViewLayout.addComponent(chaptersList);
        mainViewLayout.addComponent(commentList);
        mainViewLayout.addComponent(commentsList);

        this.addComponent(mainViewLayout);
    }

    private Label getLabel(final String text) {
        Label label = new Label();
        label.setCaption(text);
        label.setContentMode(ContentMode.TEXT);

        return label;
    }

    private Button getButton(final String text, final ItemListField itemListField) {
        Button button = new Button();
        button.setCaption(text);
        button.addClickListener(e -> {
            if (itemListField.isVisible()) {
                itemListField.setVisible(false);
            } else {
                itemListField.setVisible(true);
            }
        });

        return button;
    }
}
