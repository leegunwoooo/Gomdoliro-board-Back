package com.example.human2.controller.dto.ReComment;

import com.example.human2.domain.ReComment.ReComment;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class ReCommentResponse {
    private Long id;
    private String reCommentContent;
    private Long commentId;
    private String reCommentWriter;
    private LocalDate reCommentDate;

    public ReCommentResponse(ReComment reComment) {
        this.id = reComment.getId();
        this.commentId = reComment.getComment().getId();
        this.reCommentContent = reComment.getReCommentContent();
        this.reCommentWriter = reComment.getReCommentWriter();
        this.reCommentDate = reComment.getReCommentDate();

    }
}
