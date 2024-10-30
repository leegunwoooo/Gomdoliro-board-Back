package com.example.human2.service;

import com.example.human2.controller.dto.ReComment.ReCommentResponse;
import com.example.human2.controller.dto.ReComment.SaveReCommentRequest;
import com.example.human2.domain.Comment.Comment;
import com.example.human2.domain.Comment.CommentRepository;
import com.example.human2.domain.ReComment.ReComment;
import com.example.human2.domain.ReComment.ReCommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReCommentService {
    private final ReCommentRepository reCommentRepository;
    private final CommentRepository commentRepository;

    public ReCommentResponse save(SaveReCommentRequest request) {
        Comment comment = commentRepository.findById(request.getCommentId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 댓글입니다."));

        ReComment reComment = ReComment.builder()
                .comment(comment)
                .reCommentContent(request.getReCommentcontent())
                .reCommentWriter(request.getReCommentwriter())
                .build();

        ReComment save = reCommentRepository.save(reComment);

        return new ReCommentResponse(save);

    }

}
