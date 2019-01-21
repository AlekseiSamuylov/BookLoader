package com.samuylov.projectstart;

import com.samuylov.projectstart.entity.CommentEntity;
import com.samuylov.projectstart.repository.CommentRepository;
import com.samuylov.projectstart.service.CommentService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Date;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.Silent.class)
public class CommentServiceTest {
    @InjectMocks
    private CommentService commentService;

    @Mock
    private CommentRepository commentRepository;

    @Test
    public void testGetCommentsByBookId() {
        final CommentEntity commentEntity = new CommentEntity();
        commentEntity.setId(1L);
        commentEntity.setText("text");
        commentEntity.setName("artur");
        commentEntity.setDate(new Date());
        commentEntity.setBookId(1L);
        when(commentRepository.findAllByBookIdOrderByDateDesc(anyLong())).
                thenReturn(Collections.singletonList(commentEntity));
    }

    @Test
    public void testCreateReview() {
        when(commentRepository.save(any(CommentEntity.class))).thenAnswer(invocationOnMock -> {
            Object[] args = invocationOnMock.getArguments();
            if (args != null && args.length > 0 && args[0] != null) {
                CommentEntity reviewEntity = (CommentEntity) args[0];
                reviewEntity.setId(1L);
                return reviewEntity;
            }
            return null;
        });
    }
}
