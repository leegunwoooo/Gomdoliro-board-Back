package com.example.human2.service;

import com.example.human2.domain.BoardRepository;
import com.example.human2.domain.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final BoardRepository boardRepository;
}
