package com.example.human2.controller.dto.Comment;

import com.example.human2.domain.Comment;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CommetResponse {
    private Long id;
    private Long boardId;
    private String commentWriter;
    private String commentContent;

    public CommetResponse(Comment comment) {
        this.id = comment.getId();
        this.boardId = comment.getBoard().getId();
        this.commentWriter =  comment.getCommentWriter();
        this.commentContent = comment.getCommentContent();
    }
}
