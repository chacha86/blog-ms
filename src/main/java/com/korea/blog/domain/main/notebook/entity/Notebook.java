package com.korea.blog.domain.main.notebook.entity;

import com.korea.blog.domain.main.note.dto.NoteDto;
import com.korea.blog.domain.main.note.entity.Note;
import com.korea.blog.domain.main.notebook.dto.NotebookDto;
import com.korea.blog.global.dto.UrlBuilder;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Notebook {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;

    @OneToMany(mappedBy = "parent", cascade = CascadeType.REMOVE)
    @Builder.Default
    private List<Note> noteList = new ArrayList<>();

    @OneToMany(mappedBy = "parent", cascade = CascadeType.REMOVE)
    @Builder.Default
    private List<Notebook> subNotebookList = new ArrayList<>();

    @ManyToOne
    private Notebook parent;

    public NotebookDto toDto() {
        List<NotebookDto> subBooks = subNotebookList.stream().map(Notebook::toDto).toList();
        List<NoteDto> notes = noteList.stream().map(Note::toDto).toList();

        return NotebookDto.builder()
                .id(this.id)
                .name(this.name)
                .subNotebooks(subBooks)
                .notes(notes)
                .build();
    }

    public void addNote(Note note) {
        note.setParent(this);
        noteList.add(note);
    }

    public void addSubNotebook(Notebook subNotebook) {
        subNotebook.setParent(this);
        subNotebookList.add(subNotebook);
    }
}
