package com.samuylov.projectstart.ui.component;

import com.samuylov.projectstart.dto.CommentDto;
import com.vaadin.ui.Component;
import com.vaadin.ui.CustomField;

import java.util.List;

public class CommentListField extends CustomField<List<CommentDto>> {
    private List<CommentDto> comments;

    @Override
    protected Component initContent() {
        return null;
    }

    @Override
    protected void doSetValue(List<CommentDto> commentDtos) {
        comments.addAll(commentDtos);
    }

    @Override
    public List<CommentDto> getValue() {
        return comments;
    }
}
