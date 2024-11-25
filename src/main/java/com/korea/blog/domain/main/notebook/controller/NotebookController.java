package com.korea.blog.domain.main.notebook.controller;

import com.korea.blog.domain.main.MainDataDto;
import com.korea.blog.domain.main.MainService;
import com.korea.blog.domain.main.note.entity.Note;
import com.korea.blog.domain.main.notebook.entity.Notebook;
import com.korea.blog.domain.main.notebook.service.NotebookService;
import com.korea.blog.global.dto.UrlBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/books")
public class NotebookController {

    private final NotebookService notebookService; // 노트북 처리 전문
    private final MainService mainService; // 노트북 + 노트 혼합 작업 전문

    @PostMapping("/write")
    public String writeBook(UrlBuilder urlBuilder) {
//        Notebook notebook = notebookService.saveDefault();

        Notebook notebook = mainService.saveDefaultNotebook();
        return urlBuilder.getRedirectUrl("/books/%d".formatted(notebook.getId()));
    }

    @PostMapping("/{bookId}/write")
    public String writeSubBook(@PathVariable long bookId, UrlBuilder urlBuilder) {

        notebookService.checkSubNotebook(bookId);

        Notebook subNotebook = mainService.saveSubNotebook(bookId);
        return urlBuilder.getRedirectUrl("/books/%d/notes/%d".formatted(subNotebook.getId(), subNotebook.getNoteList().getFirst().getId()));
    }

    @PostMapping("/{bookId}/modify")
    public String modifyBook(@PathVariable long bookId, String name, long selectedNoteId, UrlBuilder urlBuilder) {
        notebookService.modify(bookId, name);
        return urlBuilder.getRedirectUrl("/books/%d/notes/%d".formatted(bookId, selectedNoteId));
    }

    @PostMapping("/{bookId}/delete")
    public String deleteBook(@PathVariable long bookId, UrlBuilder urlBuilder) {
        notebookService.delete(bookId);
        return urlBuilder.getRedirectUrl("/books/%d".formatted(notebookService.getList().getFirst().getId()));
    }

    @PostMapping("/{bookId}/notes/write")
    public String writeNote(@PathVariable long bookId, UrlBuilder urlBuilder) {
        Note note = mainService.saveDefaultNote(bookId);
//        return "redirect:/books/%d/notes/%d".formatted(bookId, note.getId());
        return urlBuilder.getRedirectUrl("/books/%d/notes/%d".formatted(bookId, note.getId()));
    }

    @GetMapping("/{bookId}/notes/{noteId}")
    public String selectNote(@PathVariable long bookId, @PathVariable long noteId, Model model, UrlBuilder urlBuilder) {

        MainDataDto mainDataDto = mainService.getMainDataDto(bookId, noteId, urlBuilder.getKeyword(), urlBuilder.getSortTarget());
        model.addAttribute("mainDataDto", mainDataDto);

        return "main";
    }

    @GetMapping("/{bookId}")
    public String select(@PathVariable long bookId, Model model, UrlBuilder urlBuilder) {

        MainDataDto mainDataDto = mainService.getDefaulNoteMainDataDto(bookId, urlBuilder.getKeyword(), urlBuilder.getSortTarget());
        model.addAttribute("mainDataDto", mainDataDto);

        return "main";
    }
}
