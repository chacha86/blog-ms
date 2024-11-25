package com.korea.blog.domain.main.note.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Getter
@NoArgsConstructor
@Builder
public class NoteDto {
    private Long id;
    private String title;
    private String content;
}
