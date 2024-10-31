package com.korea.blog.domain.main;

import com.korea.blog.domain.main.note.entity.Note;
import com.korea.blog.domain.main.note.service.NoteService;
import com.korea.blog.domain.main.notebook.entity.Notebook;
import com.korea.blog.domain.main.notebook.service.NotebookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MainService {

    private final NoteService noteService;
    private final NotebookService notebookService;

    public void init() {
        List<Note> noteList = noteService.getList();
        List<Notebook> notebookList = notebookService.getList();

        if(notebookList.isEmpty()) {
            notebookService.saveDefault();
        }

        if(noteList.isEmpty()) {
            noteService.saveDefault();
        }
    }

    public Notebook saveDefaultNote(long bookId) {
        Notebook notebook = notebookService.getOne(bookId);
        Note note = noteService.saveDefault();

        notebook.addNote(note);
        notebook = notebookService.save(notebook);

        return notebook;
    }

    public List<Note> getNoteList() {
        return noteService.getList();
    }

    public Note getNote(long noteId) {
        return noteService.getOne(noteId);
    }

    public List<Notebook> getNoteBookList() {
        return notebookService.getList();
    }
}
