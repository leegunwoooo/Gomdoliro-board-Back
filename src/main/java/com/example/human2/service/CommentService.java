package com.example.human2.service;

import com.example.human2.controller.dto.Comment.SaveCommentRequest;
import com.example.human2.controller.dto.Comment.UpdateCommentRequest;
import com.example.human2.domain.Board.Board;
import com.example.human2.domain.Board.BoardRepository;
import com.example.human2.domain.Comment.Comment;
import com.example.human2.controller.dto.Comment.CommentResponse;
import com.example.human2.domain.Comment.CommentRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final BoardRepository boardRepository;

    public CommentResponse saveComment(SaveCommentRequest request) {
        Board board = boardRepository.findById(request.getBoardId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 게시물입니다."));

        Comment comment = Comment.builder()
                .board(board)  // 조회한 Board 객체를 할당
                .commentContent(request.getCommentContent())
                .commentWriter(request.getCommentWriter())
                .build();

        Comment save = commentRepository.save(comment);

        return new CommentResponse(save);
    }

    @Transactional
    public CommentResponse updateComment(Long commentsId, UpdateCommentRequest request) {
        Comment comment = commentRepository.findById(commentsId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 댓글입니다."));

        // 작성자만 수정 가능하도록 처리
        if (!comment.getCommentWriter().equals(request.getCommentWriter())) {
            throw new IllegalArgumentException("본인만 댓글을 수정할 수 있습니다.");
        }

        comment.update(request.getCommentContent()); // 댓글 내용 업데이트

        return new CommentResponse(comment);
    }


    public void deleteComment(Long id) {
        commentRepository.deleteById(id);
    }

    public List<CommentResponse> findAllComments(Long boardId) {
        return commentRepository.findByBoardId(boardId).stream()
                .map(CommentResponse::new)
                .toList();
    }

}
