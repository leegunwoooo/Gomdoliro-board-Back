package com.example.human2.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDate;
import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Entity
@Table(name = "2human")
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Column(length = 50000)
    private String content;

    @CreatedDate//엔티티가 생성될 때 자동으로 생성시간을 기록하는 어노테이션
    @Column(updatable = false)//한번 올라가면 수정안되게
    private LocalDate createDate; //LocalDate를 이용해 날짜를 저장

    @Builder
    public Board(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
