package com.example.human2.controller.dto.ReComment;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class SaveReCommentRequest {
    private Long commentId;
    private String reCommentContent;
    private String reCommentWriter;
}
