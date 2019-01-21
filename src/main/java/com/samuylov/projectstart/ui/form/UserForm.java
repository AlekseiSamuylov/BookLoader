package com.samuylov.projectstart.ui.form;

import com.samuylov.projectstart.dto.UserDto;
import com.samuylov.projectstart.entity.UserEntity;
import com.samuylov.projectstart.enumeration.UserRole;
import com.vaadin.data.Binder;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.ViewScope;
import com.vaadin.ui.Label;
import com.vaadin.ui.RadioButtonGroup;
import com.vaadin.ui.TextField;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;

@SpringComponent
@ViewScope
public class UserForm extends AbstractForm<UserDto, UserEntity> {

    private Label idLabel;
    private TextField nameTextField;
    private TextField passwordTextField;
    private RadioButtonGroup<String> radioButtonGroup;

    @PostConstruct
    private void postConstruct() {
        setRadioButtonGroup();

        idLabel = new Label();
        idLabel.setCaption("Id");
        idLabel.setContentMode(ContentMode.TEXT);

        nameTextField = getTextField("Name", "Enter user name...");
        passwordTextField = getTextField("Password", "Enter password...");

        windowContent.addComponents(idLabel);
        windowContent.addComponent(nameTextField);
        windowContent.addComponent(passwordTextField);
        windowContent.addComponents(radioButtonGroup);
        windowContent.addComponent(buttonsLayout);
        setBinder();

        this.setContent(windowContent);
    }

    @Override
    public void init(UserDto dto, CloseFormFunction closeFunc) {
        this.dto = dto;
        this.closeFunc = closeFunc;
        setData();
    }

    private void setData() {
        if (dto.getId() != null) {
            idLabel.setVisible(true);

            idLabel.setValue(Long.toString(dto.getId()));
            binder.readBean(dto);
        } else {
            idLabel.setVisible(false);
        }
    }

    private void setRadioButtonGroup() {
        List<String> data = Arrays.asList("ADMIN", "USER");
        radioButtonGroup = new RadioButtonGroup<>("Select role", data);
        radioButtonGroup.setItemCaptionGenerator(item -> item);
    }

    private TextField getTextField(final String caption, final String placeholder) {
        TextField textField = new TextField();
        textField.setCaption(caption);
        textField.setPlaceholder(placeholder);

        return textField;
    }

    @Override
    protected void setBinder() {
        binder = new Binder<>(UserDto.class);

        binder.forField(radioButtonGroup)
                .asRequired("Choose role")
                .withConverter(UserRole::valueOf, String::valueOf)
                .bind(UserDto::getRole, UserDto::setRole);
        binder.forField(nameTextField)
                .asRequired("Enter user name")
                .bind(UserDto::getName, UserDto::setName);
        binder.forField(passwordTextField)
                .asRequired("Enter password")
                .bind(UserDto::getPassword, UserDto::setPassword);
    }
}
