package com.example.human2.controller.dto;
import com.example.human2.domain.Board;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class SaveBoardRequest {
    private String title;
    private String content;
    private LocalDate createDate;
}
