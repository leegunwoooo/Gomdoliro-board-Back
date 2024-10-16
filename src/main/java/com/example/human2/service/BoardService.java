package com.example.human2.service;

import com.example.human2.controller.dto.Board.BoardResponse;
import com.example.human2.controller.dto.Board.SaveBoardRequest;
import com.example.human2.controller.dto.Board.UpdateBoardRequest;
import com.example.human2.domain.Board;
import com.example.human2.domain.BoardRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.example.human2.domain.UserRepository;


import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
    private final UserRepository userRepository;


    public BoardResponse save(SaveBoardRequest request) {

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
        Board board = boardRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Board not found"));
        return new BoardResponse(board);
    }

    @Transactional
    public BoardResponse update(UpdateBoardRequest request) {
        Board board = boardRepository.findById(request.getId())
                .orElseThrow(() -> new IllegalArgumentException("Board not found"));

        board.update(request.getTitle(), request.getContent());

        return new BoardResponse(board);
    }

    public void delete(Long id) {
        boardRepository.deleteById(id);
    }
}
