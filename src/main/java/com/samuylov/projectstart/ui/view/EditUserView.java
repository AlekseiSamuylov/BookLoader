package com.samuylov.projectstart.ui.view;

import com.samuylov.projectstart.dto.UserDto;
import com.vaadin.data.provider.ConfigurableFilterDataProvider;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.TextField;

@SpringView(name = EditUserView.NAME)
public class EditUserView extends AbstractAdministrateBookListView<UserDto> {

    public static final String NAME = "usersEditor";

    @Override
    protected void setFilterTextField() {
        filterTextField = new TextField();
        filterTextField.setPlaceholder("Filter by name...");
        filterTextField.addValueChangeListener(valueChangeEvent -> {
            ((ConfigurableFilterDataProvider<UserDto, Void, String>)
                    grid.getDataProvider()).setFilter(valueChangeEvent.getValue());
        });
    }

    @Override
    protected void setGridColumns() {
        grid.addColumn(UserDto::getId).setCaption("Id");
        grid.addColumn(UserDto::getName).setCaption("Name");
        grid.addColumn(UserDto::getPassword).setCaption("Password");
        grid.addColumn(UserDto::getRole).setCaption("Role");
    }
}
