package com.samuylov.projectstart.controller;

import com.samuylov.projectstart.dto.ChapterDto;
import com.samuylov.projectstart.service.ChapterService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/book/chapters")
public class ChapterController {

    private final ChapterService chapterService;

    @GetMapping("/list/{bookId}")
    public List<ChapterDto> getAllChaptersByBookId(@PathVariable final Long bookId) {
        return chapterService.getAllByBookId(bookId);
    }

    @PostMapping("/create")
    public String createChapter(@RequestBody final ChapterDto chapterDto) {
        chapterService.createChapter(chapterDto);
        return "Chapter created";
    }

    @DeleteMapping("/delete/{bookId}/{chapterNumber}")
    public String deleteChapter(@PathVariable Long bookId, @PathVariable Long chapterNumber) {
        chapterService.deleteChapter(bookId, chapterNumber);
        return "Chapter deleted";
    }

    @GetMapping("/chapter/{bookId}/{chapterNumber}")
    public ChapterDto getChapterByBookIdAndNumber(@PathVariable Long bookId, @PathVariable Long chapterNumber) {
        return chapterService.getChapterByBookIdAndNumber(bookId, chapterNumber);
    }

    @PutMapping("/update/{chapterNumber}")
    public String updateChapter(@PathVariable Long chapterNumber, @RequestBody final ChapterDto chapterDto) {
        chapterService.updateChapter(chapterNumber, chapterDto);
        return "Chapter updated";
    }

}