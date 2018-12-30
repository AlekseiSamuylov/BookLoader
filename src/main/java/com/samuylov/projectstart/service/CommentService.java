package com.samuylov.projectstart.service;

import com.samuylov.projectstart.converter.CommentConverter;
import com.samuylov.projectstart.dto.CommentDto;
import com.samuylov.projectstart.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final CommentConverter commentConverter;

    @Autowired
    public CommentService(final CommentRepository commentRepository, final CommentConverter commentConverter) {
        this.commentRepository = commentRepository;
        this.commentConverter = commentConverter;
    }

    public void createComment(final CommentDto commentDto) {
        commentRepository.save(commentConverter.convertToEntity(commentDto));
    }

    public List<CommentDto> getAllByBookId(final long bookId) {
        return commentRepository.findAllByBookIdOrderByDateDesc(bookId).
                stream().map(commentConverter::convertToDto).collect(Collectors.toList());
    }
}
