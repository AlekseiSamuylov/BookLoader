package com.samuylov.projectstart.ui.view;

import com.samuylov.projectstart.dto.UserDto;
import com.samuylov.projectstart.entity.UserEntity;
import com.vaadin.data.provider.ConfigurableFilterDataProvider;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Button;
import com.vaadin.ui.TextField;

@SpringView(name = EditUserView.NAME)
public class EditUserView extends AbstractAdministrateBookListView<UserDto, UserEntity> {

    public static final String NAME = "usersEditor";

    @Override
    protected void setAddButton() {
        addButton = new Button("Add");
        addButton.addClickListener(clickEvent -> {
            windowForm.init(new UserDto(), closeFunc);
            getUI().addWindow(windowForm);
        });
    }

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
