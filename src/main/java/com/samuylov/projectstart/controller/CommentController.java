package com.samuylov.projectstart.controller;

import com.samuylov.projectstart.dto.CommentDto;
import com.samuylov.projectstart.service.CommentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/book/comment")
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/createComment")
    public ResponseEntity createComment(@RequestBody final CommentDto commentDto) {
        commentService.createComment(commentDto);
        return new ResponseEntity<>("Comment added.", HttpStatus.CREATED);
    }

    @PutMapping("/update/{commentId}")
    public ResponseEntity updateComment(@PathVariable final Long commentId, @RequestBody final CommentDto commentDto) {
        commentService.updateComment(commentId, commentDto);
        return new ResponseEntity<>("Comment updated.", HttpStatus.OK);
    }

    @DeleteMapping("/delete/{commentId}")
    public ResponseEntity deleteComment(@PathVariable final Long commentId) {
        commentService.deleteComment(commentId);
        return new ResponseEntity<>("Comment deleted.", HttpStatus.OK);
    }

    @GetMapping("/list/{commentId}")
    public ResponseEntity getAllCommentsByBookId(@PathVariable final Long commentId) {
        return new ResponseEntity<>(commentService.getAllByBookId(commentId), HttpStatus.FOUND);
    }
}
