package com.example.human2.controller.dto.ReComment;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UpdateReCommentRequest {
    private String reCommentContent;
    private String reCommentWriter;
}
