package com.samuylov.projectstart.ui.view;

import com.samuylov.projectstart.dto.CommentDto;
import com.samuylov.projectstart.entity.CommentEntity;
import com.vaadin.data.provider.ConfigurableFilterDataProvider;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.ViewScope;
import com.vaadin.ui.Button;
import com.vaadin.ui.TextField;

@SpringView(name = EditCommentView.NAME)
@ViewScope
public class EditCommentView extends AbstractAdministrateBookListView<CommentDto, CommentEntity> {

    public static final String NAME = "commentsEditor";

    @Override
    protected void setAddButton() {
        addButton = new Button("Add");
        addButton.addClickListener(clickEvent -> {
            windowForm.init(new CommentDto(), closeFunc);
            getUI().addWindow(windowForm);
        });
    }

    @Override
    protected void setFilterTextField() {
        filterTextField = new TextField();
        filterTextField.setPlaceholder("Filter by name...");
        filterTextField.addValueChangeListener(valueChangeEvent -> {
            ((ConfigurableFilterDataProvider<CommentDto, Void, String>)
                    grid.getDataProvider()).setFilter(valueChangeEvent.getValue());
        });
    }

    @Override
    protected void setGridColumns() {
        grid.addColumn(CommentDto::getId).setCaption("Id");
        grid.addColumn(CommentDto::getBookId).setCaption("Book id");
        grid.addColumn(CommentDto::getName).setCaption("NickName");
        grid.addColumn(CommentDto::getText).setCaption("Text");
        grid.addColumn(CommentDto::getDate).setCaption("Date");
    }
}
