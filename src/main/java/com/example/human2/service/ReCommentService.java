package com.example.human2.service;

import com.example.human2.controller.dto.ReComment.ReCommentResponse;
import com.example.human2.controller.dto.ReComment.SaveReCommentRequest;
import com.example.human2.controller.dto.ReComment.UpdateReCommentRequest;
import com.example.human2.domain.Comment.Comment;
import com.example.human2.domain.Comment.CommentRepository;
import com.example.human2.domain.ReComment.ReComment;
import com.example.human2.domain.ReComment.ReCommentRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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
                .reCommentContent(request.getReCommentContent())
                .reCommentWriter(request.getReCommentWriter())
                .build();

        ReComment save = reCommentRepository.save(reComment);

        return new ReCommentResponse(save);

    }

    @Transactional
    public ReCommentResponse update(Long recommentsid, UpdateReCommentRequest request) {
        ReComment reComment = reCommentRepository.findById(recommentsid)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 대댓글입니다"));

        if (!reComment.getReCommentWriter().equals(request.getReCommentWriter())) {
            throw new IllegalArgumentException("본인만 대댓글을 수정할 수 있습니다.");
        }

        reComment.update(request.getReCommentContent());

        return new ReCommentResponse(reComment);
    }

    @Transactional
    public void delete(Long id){
        reCommentRepository.deleteById(id);
    }

    public void deleteByCommentId(Long commentId){
        reCommentRepository.deleteByCommentId(commentId);
    }
    /*
    public void deleteByBoardId(Long boardId) {
        commentRepository.deleteByBoardId(boardId);
    }*/

    public List<ReCommentResponse> findRecomments(Long commentsId){
        return reCommentRepository.findByCommentId(commentsId).stream()
                .map(ReCommentResponse::new)
                .toList();
    }

}
