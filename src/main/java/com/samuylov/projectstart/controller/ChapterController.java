package com.samuylov.projectstart.controller;

import com.samuylov.projectstart.dto.ChapterDto;
import com.samuylov.projectstart.service.ChapterService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/book/chapters")
public class ChapterController {

    private final ChapterService chapterService;

    @PostMapping("/create")
    public ResponseEntity createChapter(@RequestBody final ChapterDto chapterDto) {
        chapterService.createChapter(chapterDto);
        return new ResponseEntity<>("Chapter created.", HttpStatus.CREATED);
    }

    @PutMapping("/update/{chapterId}")
    public ResponseEntity updateChapter(@PathVariable final Long chapterId, @RequestBody final ChapterDto chapterDto) {
        chapterService.updateChapter(chapterId, chapterDto);
        return new ResponseEntity<>("Chapter updated.", HttpStatus.OK);
    }

    @DeleteMapping("/delete/{chapterId}")
    public ResponseEntity deleteChapter(@PathVariable final Long chapterId) {
        chapterService.deleteChapter(chapterId);
        return new ResponseEntity<>("Chapter deleted.", HttpStatus.OK);
    }

    @GetMapping("/chapter/{bookId}/{chapterId}")
    public ResponseEntity getChapterByBookIdAndChapterId(@PathVariable final Long bookId,
                                                         @PathVariable final Long chapterId) {
        final ChapterDto chapterDto = chapterService.getChapterByBookIdAndChapterId(bookId, chapterId);
        return new ResponseEntity<>(chapterDto, HttpStatus.FOUND);
    }

    @GetMapping("/list/{bookId}")
    public ResponseEntity getAllChaptersByBookId(@PathVariable final Long bookId) {
        return new ResponseEntity<>(chapterService.getAllByBookId(bookId), HttpStatus.FOUND);
    }
}