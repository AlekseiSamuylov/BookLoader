package com.samuylov.projectstart.ui.component.itemlist;

import com.samuylov.projectstart.dto.CommunityAbstractDto;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

import java.text.SimpleDateFormat;
import java.util.List;

public class ItemCommunityListField<T extends CommunityAbstractDto> extends ItemListField<T> {

    public ItemCommunityListField(final List<T> value) {
        super(value);
    }

    @Override
    protected void drawExistingValues() {
        for (int i = 0; i < getValue().size(); i++) {
            final T dto = getValue().get(i);
            final VerticalLayout itemLayout = new VerticalLayout();
            final Label userName = getLabel(dto.getName());
            final Label text = getLabel(dto.getText());
            final SimpleDateFormat parser = new SimpleDateFormat("EEE MMM d HH:mm:ss zzz yyyy");
            final Label date = getLabel(parser.format(dto.getDate()));

            itemLayout.addComponent(userName);
            itemLayout.addComponent(text);
            itemLayout.addComponent(date);
            layout.addComponent(itemLayout);
        }
    }
}
