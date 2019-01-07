package com.samuylov.projectstart.controller;

import com.samuylov.projectstart.dto.CommentDto;
import com.samuylov.projectstart.service.CommentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/book/comment")
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/createComment")
    public ResponseEntity createComment(final @RequestBody CommentDto commentDto) {
        commentService.createComment(commentDto);
        return new ResponseEntity<>("Comment added.", HttpStatus.CREATED);
    }
}
