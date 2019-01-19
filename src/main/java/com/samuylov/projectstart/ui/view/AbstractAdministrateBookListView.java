package com.samuylov.projectstart.ui.view;

import com.samuylov.projectstart.dto.AbstractDto;
import com.samuylov.projectstart.service.AbstractService;
import com.samuylov.projectstart.ui.annotations.FormType;
import com.samuylov.projectstart.ui.form.AbstractForm;
import com.vaadin.data.provider.CallbackDataProvider;
import com.vaadin.navigator.View;
import com.vaadin.ui.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

import javax.annotation.PostConstruct;

public abstract class AbstractAdministrateBookListView<DtoClass extends AbstractDto>
        extends VerticalLayout
        implements View {

    @Autowired
    private AbstractService<DtoClass> service;

    @Autowired
    @Lazy
    @FormType("add")
    private AbstractForm<DtoClass> addForm;

    @Autowired
    @Lazy
    @FormType("edit")
    private AbstractForm<DtoClass> editForm;

    protected Grid<DtoClass> grid;
    private Button addButton;
    private Button editButton;
    private Button deleteButton;
    protected TextField filterTextField;

    @PostConstruct
    public void initContent() {
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

        setGrid();

        addComponent(menuLayout);
        addComponent(grid);

        this.setExpandRatio(menuLayout, 1);
        this.setExpandRatio(grid, 15);
    }

    private void setAddButton() {
        addButton = new Button("Add");
        addButton.addClickListener(clickEvent -> getUI().addWindow(addForm));
    }

    private void setEditButton() {
        editButton = new Button("Edit");
        editButton.addClickListener(clickEvent -> getUI().addWindow(editForm));
        editButton.setEnabled(false);
    }

    private void setDeleteButton() {
        deleteButton = new Button("Delete");
        deleteButton.addClickListener(clickEvent -> {
            for (DtoClass bookDtoGrid : grid.getSelectedItems()) {
                service.delete(bookDtoGrid.getId());
            }
            grid.deselectAll();
        });
        deleteButton.setEnabled(false);
    }

    protected abstract void setFilterTextField();

    private void setGrid() {
        grid = new Grid<>();
        grid.setSelectionMode(Grid.SelectionMode.MULTI);
        grid.setSizeFull();

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
}
