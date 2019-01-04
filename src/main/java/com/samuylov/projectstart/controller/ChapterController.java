package com.samuylov.projectstart.controller;

import com.samuylov.projectstart.dto.ChapterDto;
import com.samuylov.projectstart.service.ChapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ChapterController {

    private final ChapterService chapterService;

    @Autowired
    public ChapterController(final ChapterService chapterService) {
        this.chapterService = chapterService;
    }

    @PostMapping("/chapter/create")
    public String createChapter(@RequestBody final ChapterDto chapterDto) {
        chapterService.createChapter(chapterDto);
        return "Chapter created";
    }

    @GetMapping("/chapter/list")
    public List<ChapterDto> getAllChapters() {
        return chapterService.getChapterList();
    }

    @PostMapping("/book/list/{bookId}/chapters/create")
    public String createChapter(@PathVariable Long bookId, @RequestBody final ChapterDto chapterDto) {
        chapterService.createChapter(chapterDto);
        return "Chapter created";
    }

    @DeleteMapping("/book/list/{bookId}/chapters/{chapterNumber}")
    public String deleteChapter(@PathVariable Long bookId, @PathVariable Long chapterNumber) {
        chapterService.deleteChapter(bookId, chapterNumber);
        return "Chapter deleted";
    }

    @GetMapping("/book/list/{bookId}/chapters/{chapterNumber}")
    public ChapterDto getChapterByBookIdAndNumber(@PathVariable Long bookId, @PathVariable Long chapterNumber) {
        return chapterService.getChapterByBookIdAndNumber(bookId, chapterNumber);
    }

    @PutMapping("/list/{bookId}/chapters/{chapterNumber}/update")
    public String updateChapter(@PathVariable Long bookId, @PathVariable Long chapterNumber, @RequestBody final ChapterDto chapterDto) {
        chapterService.updateChapter(bookId, chapterNumber, chapterDto);
        return "Chapter updated";
    }

}