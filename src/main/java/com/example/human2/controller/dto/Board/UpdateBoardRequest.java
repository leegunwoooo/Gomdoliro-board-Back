package com.example.human2.controller.dto.Board;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UpdateBoardRequest {
    private Long id;
    private String title;
    private String content;
}
