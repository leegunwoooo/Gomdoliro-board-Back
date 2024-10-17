package com.example.human2.controller.dto.Comment;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class SaveCommentRequest {
    private Long boardId;
    private String commentContent;
    private String commentWriter;
}
