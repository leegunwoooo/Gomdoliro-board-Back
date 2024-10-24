package com.example.human2.controller;

import com.example.human2.controller.dto.Comment.CommentResponse;
import com.example.human2.controller.dto.Comment.SaveCommentRequest;
import com.example.human2.controller.dto.Comment.UpdateCommentRequest;
import com.example.human2.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/board/{boardId}/comments")
public class CommentController {
    private final CommentService commentService;

    @PostMapping
    public ResponseEntity<CommentResponse> saveComment(@PathVariable Long boardId, @RequestBody SaveCommentRequest request) {
        request.setBoardId(boardId);
        CommentResponse commentResponse = commentService.saveComment(request);
        return ResponseEntity.ok(commentResponse);
    }

    @PutMapping("/{commentsId}")
    public ResponseEntity<CommentResponse> updateComment(@PathVariable Long commentsId, @RequestBody UpdateCommentRequest request) {
        CommentResponse commentResponse = commentService.updateComment(commentsId, request);
        return ResponseEntity.ok(commentResponse);
    }

    @DeleteMapping("/{commentsId}")
    public void deleteComment(@PathVariable Long commentsId) {
        commentService.deleteComment(commentsId);
    }
}

