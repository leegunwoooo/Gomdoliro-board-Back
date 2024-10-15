package com.example.human2.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@Entity
@Table(name = "2human_Comment")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;

    private String commentWriter;

    private String commentContent;

    @Builder
    public Comment(String commentContent, String commentWriter) {
        this.commentContent = commentContent;
        this.commentWriter = commentWriter;
    }

    public void update(String commentContent) {
        this.commentContent = commentContent;
    }

}
