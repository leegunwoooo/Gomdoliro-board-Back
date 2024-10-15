package com.example.human2.controller.dto.Board;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SaveBoardRequest {
    private String title;
    private String content;
    private String writer;
}
