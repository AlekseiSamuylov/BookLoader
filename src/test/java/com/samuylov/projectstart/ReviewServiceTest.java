package com.samuylov.projectstart;

import com.samuylov.projectstart.entity.ReviewEntity;
import com.samuylov.projectstart.repository.ReviewRepository;
import com.samuylov.projectstart.service.ReviewService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDate;
import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.Silent.class)
public class ReviewServiceTest {
    @InjectMocks
    private ReviewService reviewService;

    @Mock
    private ReviewRepository reviewRepository;

    @Test
    public void testGetReviewsByBookId() {
        final ReviewEntity reviewEntity = new ReviewEntity();
        reviewEntity.setId(1);
        reviewEntity.setText("text");
        reviewEntity.setNickName("artur");
        reviewEntity.setDate(LocalDate.now());
        reviewEntity.setBookId(1);
        when(reviewRepository.findAllByBookIdOrderByDateDesc(anyLong())).
                thenReturn(Collections.singletonList(reviewEntity));
    }

    @Test
    public void testCreateReview() {
        when(reviewRepository.save(any(ReviewEntity.class))).thenAnswer(invocationOnMock -> {
            Object[] args = invocationOnMock.getArguments();
            if (args != null && args.length > 0 && args[0] != null) {
                ReviewEntity reviewEntity = (ReviewEntity) args[0];
                reviewEntity.setId(1);
                return reviewEntity;
            }
            return null;
        });
    }
}
