package com.samuylov.projectstart.ui.view;

import com.samuylov.projectstart.dto.AbstractDto;
import com.samuylov.projectstart.entity.AbstractEntity;
import com.samuylov.projectstart.service.AbstractService;
import com.samuylov.projectstart.ui.form.AbstractForm;
import com.samuylov.projectstart.ui.form.CloseFormFunction;
import com.vaadin.data.provider.CallbackDataProvider;
import com.vaadin.navigator.View;
import com.vaadin.ui.*;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

public abstract class AbstractAdministrateBookListView<DtoClass extends AbstractDto,
                                                       EntityClass extends AbstractEntity>
        extends VerticalLayout
        implements View {

    @Autowired
    private AbstractService<DtoClass, EntityClass> service;

    @Autowired
    protected AbstractForm<DtoClass, EntityClass> windowForm;

    @Autowired
    protected Grid<DtoClass> grid;

    protected Button addButton;
    private Button editButton;
    private Button deleteButton;
    protected TextField filterTextField;
    protected CloseFormFunction closeFunc;

    @PostConstruct
    private void initContent() {
        this.setSizeFull();
        HorizontalLayout menuLayout = new HorizontalLayout();

        setAddButton();
        setEditButton();
        setDeleteButton();
        setFilterTextField();

        menuLayout.addComponent(addButton);
        menuLayout.addComponent(editButton);
        menuLayout.addComponent(deleteButton);
        menuLayout.addComponent(filterTextField);

        setupGrid();
        setCloseFunc();

        addComponent(menuLayout);
        addComponent(grid);

        this.setExpandRatio(menuLayout, 1);
        this.setExpandRatio(grid, 15);
    }

    protected abstract void setAddButton();

    private void setEditButton() {
        editButton = new Button("Edit");
        editButton.addClickListener(clickEvent -> {
            DtoClass dto = grid.getSelectedItems().iterator().next();
            windowForm.init(dto, closeFunc);
            getUI().addWindow(windowForm);
            grid.deselectAll();
        });
        editButton.setEnabled(false);
    }

    private void setDeleteButton() {
        deleteButton = new Button("Delete");
        deleteButton.addClickListener(clickEvent -> {
            for (DtoClass bookDtoGrid : grid.getSelectedItems()) {
                service.delete(bookDtoGrid.getId());
            }
            grid.deselectAll();
            grid.getDataProvider().refreshAll();
        });
        deleteButton.setEnabled(false);
    }

    protected abstract void setFilterTextField();

    private void setupGrid() {
        setGridColumns();

        grid.setDataProvider(new CallbackDataProvider<DtoClass, String>(
                q -> service.findWithPagination(q),
                q ->(int) service.findWithPagination(q).count()).withConfigurableFilter());
        grid.getDataProvider().refreshAll();

        grid.addSelectionListener(selectionEvent -> {
            final int selectedItemCount = selectionEvent.getAllSelectedItems().size();
            editButton.setEnabled(selectedItemCount == 1);
            deleteButton.setEnabled(selectedItemCount > 0);
        });
    }

    protected abstract void setGridColumns();

    private void setCloseFunc() {
        closeFunc = () -> {
            grid.deselectAll();
            grid.getDataProvider().refreshAll();
        };
    }
}
