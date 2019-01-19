package com.samuylov.projectstart.ui.view;

import com.samuylov.projectstart.dto.ChapterDto;
import com.vaadin.data.provider.ConfigurableFilterDataProvider;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;

@SpringView(name = EditChapterView.NAME)
public class EditChapterView extends AbstractAdministrateBookListView<ChapterDto> {

    public static final String NAME = "chapterEditor";

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
    }

    @Override
    protected void setFilterTextField() {
        filterTextField = new TextField();
        filterTextField.setPlaceholder("Filter by name...");
        filterTextField.addValueChangeListener(valueChangeEvent -> {
            ((ConfigurableFilterDataProvider<ChapterDto, Void, String>)
                    grid.getDataProvider()).setFilter(valueChangeEvent.getValue());
        });
    }

    @Override
    protected void setGridColumns() {
        grid.addColumn(ChapterDto::getId).setCaption("Id");
        grid.addColumn(ChapterDto::getBookId).setCaption("Book id");
        grid.addColumn(ChapterDto::getNumber).setCaption("Chapter number");
        grid.addColumn(ChapterDto::getName).setCaption("Chapter name");
        grid.addColumn(ChapterDto::getText).setCaption("Chapter text");
    }
}
