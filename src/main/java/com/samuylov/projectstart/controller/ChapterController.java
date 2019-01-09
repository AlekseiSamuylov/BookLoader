package com.samuylov.projectstart.controller;

import com.samuylov.projectstart.dto.ChapterDto;
import com.samuylov.projectstart.service.ChapterService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@Api(tags = "All requests for work with chapters", description = "Chapters wait u orders")
@RequestMapping("/book/chapters")
public class ChapterController {

    private final ChapterService chapterService;

    @ApiOperation(tags = "Create requests", value = "Create chapter by name, text and bookId",
            notes = "Beautiful create chapter")
    @PostMapping("/create")
    public ResponseEntity createChapter(@RequestBody final ChapterDto chapterDto) {
        chapterService.createChapter(chapterDto);
        return new ResponseEntity<>("Chapter created.", HttpStatus.CREATED);
    }

    @ApiOperation(tags = "Update requests", value = "Update chapter by chapter id and old chapter params",
            notes = "Beautiful update chapter")
    @PutMapping("/update/{chapterId}")
    public ResponseEntity updateChapter(@PathVariable final Long chapterId, @RequestBody final ChapterDto chapterDto) {
        chapterService.updateChapter(chapterId, chapterDto);
        return new ResponseEntity<>("Chapter updated.", HttpStatus.OK);
    }

    @ApiOperation(tags = "Delete requests", value = "Delete chapter by chapter id",
            notes = "Beautiful delete chapter")
    @DeleteMapping("/delete/{chapterId}")
    public ResponseEntity deleteChapter(@PathVariable final Long chapterId) {
        chapterService.deleteChapter(chapterId);
        return new ResponseEntity<>("Chapter deleted.", HttpStatus.OK);
    }

    @ApiOperation(tags = "Get element(s) requests", value = "Get one chapter by book id and chapter id",
            notes = "Beautiful get chapter")
    @GetMapping("/chapter/{bookId}/{chapterId}")
    public ResponseEntity getChapterByBookIdAndChapterId(@PathVariable final Long bookId,
                                                         @PathVariable final Long chapterId) {
        final ChapterDto chapterDto = chapterService.getChapterByBookIdAndChapterId(bookId, chapterId);
        return new ResponseEntity<>(chapterDto, HttpStatus.FOUND);
    }

    @ApiOperation(tags = "Get element(s) requests", value = "Get all chapters by book id",
            notes = "Beautiful get chapters")
    @GetMapping("/list/{bookId}")
    public ResponseEntity getAllChaptersByBookId(@PathVariable final Long bookId) {
        return new ResponseEntity<>(chapterService.getAllByBookId(bookId), HttpStatus.FOUND);
    }
}