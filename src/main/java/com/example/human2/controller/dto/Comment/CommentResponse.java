package com.example.human2.controller.dto.Comment;

import com.example.human2.domain.Comment.Comment;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class CommentResponse {
    private Long id;
    private Long boardId;
    private String commentWriter;
    private String commentContent;
    private LocalDate commentDate;


    public CommentResponse(Comment comment) {
        this.id = comment.getId();
        this.boardId = comment.getBoard().getId();
        this.commentWriter =  comment.getCommentWriter();
        this.commentContent = comment.getCommentContent();
        this.commentDate = comment.getCommentDate();
    }
}
