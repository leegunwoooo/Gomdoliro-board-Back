package com.example.human2.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;

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

    @CreatedDate//엔티티가 생성될 때 자동으로 생성시간을 기록하는 어노테이션
    @Column(updatable = false)//한번 올라가면 수정안되게
    private LocalDate commentDate; //LocalDate를 이용해 날짜를 저장

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

