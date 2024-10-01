package com.example.human2.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SaveBoardRequest {
    private String title;
    private String content;
}
