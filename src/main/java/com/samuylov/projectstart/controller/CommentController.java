package com.samuylov.projectstart.controller;

import com.samuylov.projectstart.dto.CommentDto;
import com.samuylov.projectstart.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/comment")
public class CommentController {
    private final CommentService commentService;

    @Autowired
    public CommentController(final CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/createComment")
    public String createComment(final @RequestBody CommentDto commentDto) {
        commentService.createComment(commentDto);
        return "Comment created";
    }
}
