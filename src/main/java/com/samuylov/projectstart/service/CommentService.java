package com.samuylov.projectstart.service;

import com.samuylov.projectstart.converter.CommentConverter;
import com.samuylov.projectstart.dto.CommentDto;
import com.samuylov.projectstart.entity.CommentEntity;
import com.samuylov.projectstart.repository.CommentRepository;
import com.vaadin.data.provider.Query;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@AllArgsConstructor
@Service
public class CommentService extends AbstractService<CommentDto> implements FindElementsService<CommentDto> {

    private final CommentRepository commentRepository;
    private final CommentConverter commentConverter;

    @Override
    public void create(final CommentDto commentDto) {
        commentDto.setDate(new Date());
        commentRepository.save(commentConverter.convertToEntity(commentDto));
    }

    @Override
    public void update(final Long commentId, final CommentDto commentDto) {
        final CommentEntity oldComment = commentRepository.findFirstById(commentId);

        oldComment.setText(commentDto.getText());
        commentRepository.save(oldComment);
    }

    @Override
    public void delete(final Long commentId) {
        commentRepository.deleteById(commentId);
    }

    @Override
    public List<CommentDto> getList() {
        return commentRepository.findAll().stream().map(commentConverter::convertToDto).collect(Collectors.toList());
    }

    @Override
    public List<CommentDto> getAllByBookId(final Long bookId) {
        return commentRepository.findAllByBookIdOrderByDateDesc(bookId).
                stream().map(commentConverter::convertToDto).collect(Collectors.toList());
    }

    public List<CommentDto> getCommentsList() {
        return commentRepository.findAll().stream().map(commentConverter::convertToDto).collect(Collectors.toList());
    }

    public void deleteAllCommentsByBookId(final Long bookId) {
        commentRepository.deleteAllByBookId(bookId);
    }

    @Override
    public Stream<CommentDto> findWithPagination(final Query<CommentDto, String> query) {
        final PageRequest pageRequest = preparePageRequest(query);
        final List<CommentDto> items = commentRepository.findAll(pageRequest)
                .stream().map(commentConverter::convertToDto).collect(Collectors.toList());
        return items.stream();
    }
}
