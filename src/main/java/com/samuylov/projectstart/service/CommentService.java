package com.samuylov.projectstart.service;

import com.samuylov.projectstart.converter.CommentConverter;
import com.samuylov.projectstart.dto.CommentDto;
import com.samuylov.projectstart.entity.CommentEntity;
import com.samuylov.projectstart.repository.CommentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final CommentConverter commentConverter;

    public void createComment(final CommentDto commentDto) {
        commentDto.setDate(new Date());
        commentRepository.save(commentConverter.convertToEntity(commentDto));
    }

    public void updateComment(final Long commentId, final CommentDto commentDto) {
        final CommentEntity oldComment = commentRepository.findFirstById(commentId);

        oldComment.setText(commentDto.getText());
        commentRepository.save(oldComment);
    }

    public void deleteComment(final Long commentId) {
        commentRepository.deleteById(commentId);
    }

    public List<CommentDto> getAllByBookId(final Long bookId) {
        return commentRepository.findAllByBookIdOrderByDateDesc(bookId).
                stream().map(commentConverter::convertToDto).collect(Collectors.toList());
    }

    public void deleteAllCommentsByBookId(final Long bookId) {
        commentRepository.deleteAllByBookId(bookId);
    }
}
