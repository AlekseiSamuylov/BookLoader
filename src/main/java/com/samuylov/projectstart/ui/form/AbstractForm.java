package com.samuylov.projectstart.ui.form;

import com.samuylov.projectstart.dto.AbstractDto;
import com.samuylov.projectstart.service.AbstractService;
import com.vaadin.data.Binder;
import com.vaadin.ui.*;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

public abstract class AbstractForm<DtoClass extends AbstractDto> extends Window {

    @Autowired
    protected Grid<DtoClass> grid;

    @Autowired
    protected AbstractService<DtoClass> service;

    protected HorizontalLayout buttonsLayout;
    protected FormLayout windowContent;
    protected Button saveChangesButton;
    protected Button closeButton;
    protected Binder<DtoClass> binder;

    @PostConstruct
    private void init() {
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
        closeButton.addClickListener(clickEvent1 -> this.close());
    }

    protected abstract void setSaveChangesButton();

    protected abstract void setBinder();
}
