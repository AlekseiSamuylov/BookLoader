package com.samuylov.projectstart.ui.view;

import com.samuylov.projectstart.dto.CommunityAbstractDto;
import com.vaadin.data.provider.ConfigurableFilterDataProvider;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.TextField;

@SpringView(name = EditCommunityDataView.NAME)
public class EditCommunityDataView extends AbstractAdministrateBookListView<CommunityAbstractDto>{

    public static final String NAME = "communityDataEditor";

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        super.enter(event);
    }

    @Override
    protected void setFilterTextField() {
        filterTextField = new TextField();
        filterTextField.setPlaceholder("Filter by name...");
        filterTextField.addValueChangeListener(valueChangeEvent -> {
            ((ConfigurableFilterDataProvider<CommunityAbstractDto, Void, String>)
                    grid.getDataProvider()).setFilter(valueChangeEvent.getValue());
        });
    }

    @Override
    protected void setGridColumns() {
        grid.addColumn(CommunityAbstractDto::getId).setCaption("Id");
        grid.addColumn(CommunityAbstractDto::getBookId).setCaption("Book id");
        grid.addColumn(CommunityAbstractDto::getNickName).setCaption("NickName");
        grid.addColumn(CommunityAbstractDto::getText).setCaption("Text");
        grid.addColumn(CommunityAbstractDto::getDate).setCaption("Date");
    }
}
