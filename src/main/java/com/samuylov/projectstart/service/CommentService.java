package com.samuylov.projectstart.service;

import com.samuylov.projectstart.converter.DtoEntityConverter;
import com.samuylov.projectstart.dto.CommentDto;
import com.samuylov.projectstart.entity.CommentEntity;
import com.samuylov.projectstart.repository.CommentRepository;
import com.samuylov.projectstart.repository.NamedEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentService extends AbstractService<CommentDto, CommentEntity> implements FindElementsService<CommentDto> {

    @Autowired
    public CommentService(final NamedEntityRepository<CommentEntity> repository,
                          final DtoEntityConverter<CommentDto, CommentEntity> converter) {
        super(repository, converter);
    }

    @Override
    public void create(final CommentDto commentDto) {
        commentDto.setDate(new Date());
        repository.save(converter.convertToEntity(commentDto));
    }

    @Override
    public void update(final Long commentId, final CommentDto commentDto) {
        final CommentEntity oldComment = ((CommentRepository) repository).findFirstById(commentId);

        oldComment.setText(commentDto.getText());
        repository.save(oldComment);
    }

    @Override
    public void save(final CommentDto commentDto) {
        if (commentDto.getId() == null) {
            create(commentDto);
        } else {
            update(commentDto.getId(), commentDto);
        }
    }

    @Override
    public void delete(final Long commentId) {
        repository.deleteById(commentId);
    }

    @Override
    public List<CommentDto> getList() {
        return repository.findAll().stream().map(converter::convertToDto).collect(Collectors.toList());
    }

    @Override
    public List<CommentDto> getAllByBookId(final Long bookId) {
        return ((CommentRepository) repository).findAllByBookIdOrderByDateDesc(bookId).
                stream().map(converter::convertToDto).collect(Collectors.toList());
    }

    public List<CommentDto> getCommentsList() {
        return repository.findAll().stream().map(converter::convertToDto).collect(Collectors.toList());
    }

    public void deleteAllCommentsByBookId(final Long bookId) {
        ((CommentRepository) repository).deleteAllByBookId(bookId);
    }
}
