package com.samuylov.projectstart.ui.form;

import com.samuylov.projectstart.dto.UserDto;
import com.samuylov.projectstart.enumeration.UserRole;
import com.samuylov.projectstart.service.UserService;
import com.samuylov.projectstart.ui.annotations.FormType;
import com.vaadin.data.Binder;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.ViewScope;
import com.vaadin.ui.Button;
import com.vaadin.ui.Notification;
import com.vaadin.ui.RadioButtonGroup;
import com.vaadin.ui.TextField;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;

@SpringComponent
@FormType("add")
@ViewScope
public class AddUserForm extends AbstractForm<UserDto> {

    @Autowired
    private UserService userService;

    private TextField nameTextField;
    private TextField passwordTextField;
    private RadioButtonGroup<String> radioButtonGroup;

    @PostConstruct
    public void initContent() {
        setRadioButtonGroup();

        this.setCaption("Add user");

        nameTextField = new TextField("Name");
        nameTextField.setPlaceholder("Enter user name...");
        windowContent.addComponent(nameTextField);

        passwordTextField = new TextField("Password");
        passwordTextField.setPlaceholder("Enter password...");
        windowContent.addComponent(passwordTextField);

        windowContent.addComponents(radioButtonGroup);

        windowContent.addComponent(buttonsLayout);
        setBinder();

        this.setContent(windowContent);
    }

    @Override
    protected void setSaveChangesButton() {
        saveChangesButton = new Button("Save");

        saveChangesButton.addClickListener(clickEvent1 -> {
            UserDto userDto = new UserDto();
            if (binder.writeBeanIfValid(userDto)) {
                String role = radioButtonGroup.getSelectedItem().get();
                userDto.setRole(UserRole.valueOf(role));
                userService.create(userDto);
                Notification.show("User saved");
            } else {
                Notification.show("User not saved");
            }

            binder.readBean(new UserDto());
        });
    }

    @Override
    protected void setBinder() {
        binder = new Binder<>(UserDto.class);

        binder.bind(nameTextField, UserDto::getName, UserDto::setName);
        binder.bind(passwordTextField, UserDto::getPassword, UserDto::setPassword);
    }

    private void setRadioButtonGroup() {
        List<String> data = Arrays.asList("ADMIN", "USER");

        radioButtonGroup = new RadioButtonGroup<>("Select role", data);
        radioButtonGroup.setItemCaptionGenerator(item -> item);
        radioButtonGroup.setSelectedItem(data.get(1));
    }
}
