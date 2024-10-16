package com.example.human2.controller.dto.Comment;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UpdateCommentRequest {
    private Long id;
    private String commentContent;
}
