package com.samuylov.projectstart.ui.view;

import com.samuylov.projectstart.dto.ReviewDto;
import com.samuylov.projectstart.entity.ReviewEntity;
import com.vaadin.data.provider.ConfigurableFilterDataProvider;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.ViewScope;
import com.vaadin.ui.Button;
import com.vaadin.ui.TextField;

@SpringView(name = EditReviewView.NAME)
@ViewScope
public class EditReviewView extends AbstractAdministrateBookListView<ReviewDto, ReviewEntity> {

    public static final String NAME = "reviewsEditor";

    @Override
    protected void setAddButton() {
        addButton = new Button("Add");
        addButton.addClickListener(clickEvent -> {
            windowForm.init(new ReviewDto(), closeFunc);
            getUI().addWindow(windowForm);
        });
    }

    @Override
    protected void setFilterTextField() {
        filterTextField = new TextField();
        filterTextField.setPlaceholder("Filter by name...");
        filterTextField.addValueChangeListener(valueChangeEvent -> {
            ((ConfigurableFilterDataProvider<ReviewDto, Void, String>)
                    grid.getDataProvider()).setFilter(valueChangeEvent.getValue());
        });
    }

    @Override
    protected void setGridColumns() {
        grid.addColumn(ReviewDto::getId).setCaption("Id");
        grid.addColumn(ReviewDto::getBookId).setCaption("Book id");
        grid.addColumn(ReviewDto::getName).setCaption("NickName");
        grid.addColumn(ReviewDto::getText).setCaption("Text");
        grid.addColumn(ReviewDto::getDate).setCaption("Date");
    }
}
