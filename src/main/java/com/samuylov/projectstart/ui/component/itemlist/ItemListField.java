package com.samuylov.projectstart.ui.component.itemlist;

import com.samuylov.projectstart.dto.AbstractDto;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.*;

import java.util.ArrayList;
import java.util.List;

public abstract class ItemListField<DtoClass extends AbstractDto> extends CustomField<List<DtoClass>> {

    private List<DtoClass> value;
    protected VerticalLayout layout;

    public ItemListField(final List<DtoClass> value) {
        this.value = new ArrayList<>(value);
        layout = new VerticalLayout();
    }

    @Override
    protected Component initContent() {
        redraw();
        return layout;
    }

    @Override
    protected void doSetValue(final List<DtoClass> value) {
        if (value == null) {
            this.value = new ArrayList<>();
        } else {
            this.value = new ArrayList<>(value);
        }
        redraw();
    }

    @Override
    public List<DtoClass> getValue() {
        return value == null ? new ArrayList<>() : value;
    }

    private void redraw() {
        layout.removeAllComponents();
        drawExistingValues();
    }

    protected Label getLabel(final String caption) {
        final Label label = new Label();
        label.setCaption(caption);
        label.setContentMode(ContentMode.TEXT);

        return label;
    }

    protected abstract void drawExistingValues();
}
