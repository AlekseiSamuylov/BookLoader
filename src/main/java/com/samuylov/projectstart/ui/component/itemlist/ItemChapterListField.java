package com.samuylov.projectstart.ui.component.itemlist;

import com.samuylov.projectstart.dto.ChapterDto;
import com.samuylov.projectstart.ui.view.ChapterView;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

import java.util.List;

public class ItemChapterListField extends ItemListField<ChapterDto> {

    public ItemChapterListField(List<ChapterDto> value) {
        super(value);
    }

    @Override
    protected void drawExistingValues() {
        for (int i = 0; i < getValue().size(); i++) {
            final ChapterDto chapterDto = getValue().get(i);
            final VerticalLayout itemLayout = new VerticalLayout();
            final Label chapter = getLabel("Chapter " + chapterDto.getNumber() + ". " + chapterDto.getName());

            itemLayout.addComponent(chapter);
            itemLayout.addLayoutClickListener(e -> UI.getCurrent().getNavigator()
                    .navigateTo(ChapterView.NAME
                            + "/" + chapterDto.getBookId()
                            + "/" + chapterDto.getId()));

            layout.addComponent(itemLayout);
        }
    }
}
