package com.korea.blog.domain.main.notebook.controller;

import com.korea.blog.domain.main.MainService;
import com.korea.blog.domain.main.note.entity.Note;
import com.korea.blog.domain.main.notebook.entity.Notebook;
import com.korea.blog.domain.main.notebook.service.NotebookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/books")
public class NotebookController {

    private final NotebookService notebookService; // 노트북 처리 전문
    private final MainService mainService; // 노트북 + 노트 혼합 작업 전문

    @PostMapping("/write")
    public String write() {
        Notebook notebook = notebookService.saveDefault();
        return "redirect:/books/%d".formatted(notebook.getId());
    }


//    @GetMapping("")
//    public String list(Model model) {
//
//        List<Notebook> notebookList = notebookService.getList();
//        List<Note> noteList = mainService.getNoteList();
//        Note selectedNote = noteList.getFirst();
//
//        model.addAttribute("notebookList", notebookList);
//        model.addAttribute("noteList", noteList);
//        model.addAttribute("selectedNote", selectedNote);
//
//        return "main";
//    }

    @GetMapping("/{bookId}")
    public String select(@PathVariable long bookId, Model model) {

        List<Notebook> notebookList = notebookService.getList();
        Notebook selectedNotebook = notebookService.getOne(bookId);
        List<Note> noteList = mainService.getNoteList();
        Note selectedNote = noteList.getFirst();


        model.addAttribute("notebookList", notebookList);
        model.addAttribute("noteList", noteList);
        model.addAttribute("selectedNotebook", selectedNotebook);
        model.addAttribute("selectedNote", selectedNote);

        return "main";
    }
}
