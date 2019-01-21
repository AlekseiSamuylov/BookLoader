package com.samuylov.projectstart.ui.view;

import com.samuylov.projectstart.dto.ChapterDto;
import com.samuylov.projectstart.service.BookService;
import com.samuylov.projectstart.service.ChapterService;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.*;
import org.springframework.beans.factory.annotation.Autowired;

@SpringView(name = ChapterView.NAME)
public class ChapterView extends VerticalLayout implements View {

    public static final String NAME = "chapter";

    @Autowired
    private ChapterService chapterService;

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        final String[] params = event.getParameters().split("/");
        final Long bookId = Long.parseLong(params[0]);
        final Long chapterId = Long.parseLong(params[1]);
        final ChapterDto chapterDto = chapterService.getChapterByBookIdAndChapterId(bookId, chapterId);
        final Long prevChapterId = chapterService.getPrevChapterId(bookId, chapterId);
        final Long nextChapterId = chapterService.getNextChapterId(bookId, chapterId);
        final HorizontalLayout navigationLayout = new HorizontalLayout();

        Button bookButton = new Button("Table of contents", e -> UI.getCurrent().getNavigator()
                .navigateTo(BookView.NAME + "/" + bookId));

        Button prevChapter = getChapterNavigateButton(bookId, prevChapterId, "Previous chapter");
        Button nextChapter = getChapterNavigateButton(bookId, nextChapterId, "Next chapter");

        Label chapterName = new Label();
        chapterName.setCaption("Chapter " + chapterDto.getNumber() + ". " + chapterDto.getName());
        chapterName.setContentMode(ContentMode.TEXT);

        Label chapterText = new Label(chapterDto.getText());
        chapterText.setContentMode(ContentMode.TEXT);

        navigationLayout.addComponent(bookButton);
        navigationLayout.addComponent(prevChapter);
        navigationLayout.addComponent(chapterName);
        navigationLayout.addComponent(nextChapter);

        addComponent(navigationLayout);
        addComponent(chapterText);
    }

    private Button getChapterNavigateButton(final Long bookId, final Long chapterId, final String caption) {
        Button button = new Button(caption);
        if (chapterId.equals((long) -1)) {
            button.setEnabled(false);
        } else {
            button.addClickListener(e -> UI.getCurrent().getNavigator()
                    .navigateTo(ChapterView.NAME + "/" + bookId + "/" + chapterId));
        }

        return button;
    }
}
