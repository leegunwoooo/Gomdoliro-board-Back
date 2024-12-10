package com.example.human2.service;

import com.example.human2.controller.dto.Board.BoardResponse;
import com.example.human2.controller.dto.Board.SaveBoardRequest;
import com.example.human2.controller.dto.Board.UpdateBoardRequest;
import com.example.human2.domain.Board.Board;
import com.example.human2.domain.Board.BoardRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    public BoardResponse save(SaveBoardRequest request) {

        log.info("새로운 게시물 정보: 제목={}, 작성자={}", request.getTitle(), request.getWriter());
        Board board = Board.builder()
                .title(request.getTitle())
                .content(request.getContent())
                .writer(request.getWriter())
                .build();

        Board save = boardRepository.save(board);

        return new BoardResponse(save);
    }

    public List<BoardResponse> findAll() {
        return boardRepository.findAll().stream()
                .map(BoardResponse::new)
                .toList();
    }

    public BoardResponse findOne(Long id) {
        log.info("{}번 게시물", id);
        Board board = boardRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("{}번 게시물은 없습니다", id);
                    return new IllegalArgumentException("Board not found");
                });
        return new BoardResponse(board);
    }

    @Transactional
    public BoardResponse update(UpdateBoardRequest request) {
        Board board = boardRepository.findById(request.getId())
                .orElseThrow(() ->{
                    log.error("{}번 게시물은 없습니다.", request.getId());
                    return new IllegalArgumentException("Board not found");
                });

        board.update(request.getTitle(), request.getContent());

        return new BoardResponse(board);
    }

    @Transactional
    public void delete(Long id) {
        boardRepository.findById(id)
                .orElseThrow(() ->{
                    log.error("{}번 게시물은 없습니다..", id);
                    return new IllegalArgumentException("Board not found");
                });

        boardRepository.deleteById(id);

    }
}
