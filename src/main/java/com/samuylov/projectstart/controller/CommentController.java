package com.samuylov.projectstart.controller;

import com.samuylov.projectstart.dto.CommentDto;
import com.samuylov.projectstart.service.CommentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@Api(tags = "All requests for work with comment", description = "Better not to read them")
@RequestMapping("/book/comment")
public class CommentController {

    private final CommentService commentService;

    @ApiOperation(tags = "Create requests", value = "Create comment by name and text",
            notes = "Beautiful create comment")
    @PostMapping("/createComment")
    public ResponseEntity createComment(@RequestBody final CommentDto commentDto) {
        commentService.createComment(commentDto);
        return new ResponseEntity<>("Comment added.", HttpStatus.CREATED);
    }

    @ApiOperation(tags = "Update requests", value = "Update comment by comment id and old comment params",
            notes = "Beautiful update comment")
    @PutMapping("/update/{commentId}")
    public ResponseEntity updateComment(@PathVariable final Long commentId, @RequestBody final CommentDto commentDto) {
        commentService.updateComment(commentId, commentDto);
        return new ResponseEntity<>("Comment updated.", HttpStatus.OK);
    }

    @ApiOperation(tags = "Delete requests", value = "Delete comment by comment id",
            notes = "Beautiful delete comment")
    @DeleteMapping("/delete/{commentId}")
    public ResponseEntity deleteComment(@PathVariable final Long commentId) {
        commentService.deleteComment(commentId);
        return new ResponseEntity<>("Comment deleted.", HttpStatus.OK);
    }

    @ApiOperation(tags = "Get element(s) requests", value = "Get all comments by book id",
            notes = "Beautiful get comments")
    @GetMapping("/list/{bookId}")
    public ResponseEntity getAllCommentsByBookId(@PathVariable final Long bookId) {
        return new ResponseEntity<>(commentService.getAllByBookId(bookId), HttpStatus.FOUND);
    }
}
