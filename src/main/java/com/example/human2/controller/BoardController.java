package com.example.human2.controller;

import com.example.human2.controller.dto.BoardResponse;
import com.example.human2.controller.dto.SaveBoardRequest;
import com.example.human2.controller.dto.UpdateBoardRequest;
import com.example.human2.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @PostMapping("/board")
    public BoardResponse save(@RequestBody SaveBoardRequest request) {
        return boardService.save(request);
    }

    @GetMapping("/board-all")
    public List<BoardResponse> getAll() {
        return boardService.findAll();
    }

    @GetMapping("/board/{id}")
    public BoardResponse findOne(@PathVariable Long id) {
        return boardService.findOne(id);
    }

    @PutMapping("/board")
    public BoardResponse update(@RequestBody UpdateBoardRequest request) {
        return boardService.update(request);
    }

    @DeleteMapping("/board/{id}")
    public void delete(@PathVariable Long id) {
        boardService.delete(id);
    }
}
