package com.example.human2.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@NoArgsConstructor
@Getter
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "2human_comment")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")  // 외래 키로 게시물(Board)와 연결
    private Board board;

    private String commentWriter;

    @Column(length = 1000)
    private String commentContent;

    @Builder
    public Comment(Board board, String commentWriter, String commentContent) {
        this.board = board;
        this.commentWriter = commentWriter;
        this.commentContent = commentContent;
    }

    public void update(String commentContent){
        this.commentContent = commentContent;
    }
}

