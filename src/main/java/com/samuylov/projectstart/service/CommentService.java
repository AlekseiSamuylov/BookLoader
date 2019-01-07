package com.samuylov.projectstart.service;

import com.samuylov.projectstart.converter.CommentConverter;
import com.samuylov.projectstart.dto.CommentDto;
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

    public List<CommentDto> getAllByBookId(final Long bookId) {
        return commentRepository.findAllByBookIdOrderByDateDesc(bookId).
                stream().map(commentConverter::convertToDto).collect(Collectors.toList());
    }
}
