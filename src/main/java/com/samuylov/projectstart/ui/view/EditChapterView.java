package com.samuylov.projectstart.ui.view;

import com.samuylov.projectstart.dto.ChapterDto;
import com.samuylov.projectstart.entity.ChapterEntity;
import com.vaadin.data.provider.ConfigurableFilterDataProvider;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Button;
import com.vaadin.ui.TextField;

@SpringView(name = EditChapterView.NAME)
public class EditChapterView extends AbstractAdministrateBookListView<ChapterDto, ChapterEntity> {

    public static final String NAME = "chapterEditor";

    @Override
    protected void setAddButton() {
        addButton = new Button("Add");
        addButton.addClickListener(clickEvent -> {
            windowForm.init(new ChapterDto(), closeFunc);
            getUI().addWindow(windowForm);
        });
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
