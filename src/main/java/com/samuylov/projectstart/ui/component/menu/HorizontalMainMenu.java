package com.samuylov.projectstart.ui.component.menu;

import com.samuylov.projectstart.dto.BookDto;
import com.samuylov.projectstart.service.BookService;
import com.samuylov.projectstart.ui.view.*;
import com.vaadin.annotations.Theme;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

import javax.annotation.PostConstruct;
import java.util.List;

@UIScope
@SpringComponent
public class HorizontalMainMenu extends HorizontalLayout implements MainMenu {

    @Autowired
    private BookService bookService;

    @PostConstruct
    public void init() {
        Button editUsersButton = createNavigationButton("Edit users", EditUserView.NAME);
        Button editBooksButton = createNavigationButton("Edit books", EditBookView.NAME);
        Button editChaptersButton = createNavigationButton("Edit chapters", EditChapterView.NAME);
        Button editCommunityDataButton =
                createNavigationButton("Edit comments & reviews", EditCommunityDataView.NAME);
        Button booksListViewButton = createNavigationButton("All books", BooksListView.NAME);
        //Button commentsListViewButton = createNavigationButton("Comments", CommentsListView.NAME);

        addComponent(editUsersButton);
        addComponent(editBooksButton);
        addComponent(editChaptersButton);
        addComponent(editCommunityDataButton);
        addComponent(booksListViewButton);
        //addComponent(commentsListViewButton);
        addComponent(getBookSearchComboBox());
    }

    private Button createNavigationButton(final String buttonCaption, final String viewName) {
        Button button = new Button(buttonCaption);
        button.addClickListener(clickEvent -> {
            UI.getCurrent().getNavigator().navigateTo(viewName);
        });

        return button;
    }

    private ComboBox<BookDto> getBookSearchComboBox() {
        List<BookDto> books = bookService.getList();

        ComboBox<BookDto> bookSearch = new ComboBox<>();
        bookSearch.setPlaceholder("Enter book name...");
        bookSearch.setItems(books);
        bookSearch.setItemCaptionGenerator(BookDto::getName);
        bookSearch.setEmptySelectionAllowed(false);

        bookSearch.addSelectionListener(singleSelectionEvent -> {
            BookDto selectedBook = singleSelectionEvent.getValue();
            UI.getCurrent().getNavigator().navigateTo(BookView.NAME + "/" + selectedBook.getId());
        });

        return bookSearch;
    }

    @Override
    public AbstractComponent getUnderlying() {
        return this;
    }
}
