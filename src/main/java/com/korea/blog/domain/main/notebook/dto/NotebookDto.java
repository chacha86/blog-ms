package com.korea.blog.domain.main.notebook.dto;

import com.korea.blog.domain.main.note.dto.NoteDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NotebookDto {
    private Long id;
    private String name;
    private List<NotebookDto> subNotebooks;
    private List<NoteDto> notes;
    private String url;
}
