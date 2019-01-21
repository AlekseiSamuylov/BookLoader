package com.samuylov.projectstart.ui.form;

import com.samuylov.projectstart.dto.AbstractDto;
import com.samuylov.projectstart.entity.AbstractEntity;
import com.samuylov.projectstart.service.AbstractService;
import com.vaadin.data.Binder;
import com.vaadin.data.ValidationException;
import com.vaadin.ui.*;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

public abstract class AbstractForm<DtoClass extends AbstractDto, EntityClass extends AbstractEntity> extends Window {

    @Autowired
    protected AbstractService<DtoClass, EntityClass> service;

    protected HorizontalLayout buttonsLayout;
    protected FormLayout windowContent;
    protected Button saveChangesButton;
    protected Button closeButton;
    protected Binder<DtoClass> binder;
    protected DtoClass dto;
    protected CloseFormFunction closeFunc;

    public abstract void init(final DtoClass dto, final CloseFormFunction closeFunc);

    @PostConstruct
    private void initContent() {
        setWindowParams();
        setWindowContent();
        setSaveChangesButton();
        setCloseButton();
        setButtonsLayout();
    }

    protected void setWindowParams() {
        this.setHeight("90%");
        this.setWidth("98%");
        this.center();
        this.setModal(true);
        this.setClosable(false);
        this.setResizable(false);
    }

    protected void setButtonsLayout() {
        buttonsLayout = new HorizontalLayout();
        buttonsLayout.addComponent(saveChangesButton);
        buttonsLayout.addComponent(closeButton);
    }

    protected void setWindowContent() {
        windowContent = new FormLayout();
        windowContent.setMargin(true);
        windowContent.addStyleName("outlined");
        windowContent.setSizeFull();
    }

    protected void setCloseButton() {
        closeButton = new Button("Close");
        closeButton.addClickListener(clickEvent1 -> {
            binder.readBean(null);
            closeFunc.onClose();
            this.close();
        });
    }

    protected void setSaveChangesButton() {
        saveChangesButton = new Button("Save");

        saveChangesButton.addClickListener(clickEvent1 -> {
            try {
                final DtoClass dtoClass = dto;
                binder.writeBean(dtoClass);
                binder.readBean(null);
                service.save(dtoClass);

                closeFunc.onClose();
                this.close();
                Notification.show("Saved", Notification.Type.HUMANIZED_MESSAGE);
            } catch (ValidationException e) {
                Notification.show("Validation failed", Notification.Type.ERROR_MESSAGE);
            }
        });
    }

    protected abstract void setBinder();
}