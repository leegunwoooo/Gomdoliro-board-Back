package com.example.human2.controller.dto;
import com.example.human2.domain.Board;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UpdateBoardRequest {
    private Long id;
    private String title;
    private String content;
}
