package com.testament.veltahleon.dto.history.library;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ChapterDTO {
    private Long id;
    private String name;
    private Integer chapterNumber;
    private String book;
    private String text;
}
