package com.example.human2.controller.dto.Board;

import com.example.human2.domain.Board;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;


@Getter
@AllArgsConstructor
public class BoardResponse {
    private Long id;
    private String title;
    private String content;
    private LocalDate date;
    private String writer;

    public BoardResponse(Board board) {
        this.id = board.getId();
        this.title = board.getTitle();
        this.content = board.getContent();
        this.date = board.getDate();
        this.writer = board.getWriter();
    }
}
