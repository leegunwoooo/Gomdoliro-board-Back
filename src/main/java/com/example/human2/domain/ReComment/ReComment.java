package com.example.human2.domain.ReComment;

import com.example.human2.domain.Comment.Comment;
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
@Table(name = "2human_reComments")
public class ReComment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 1000)
    private String reCommentContent;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "comment_id")  // 외래 키로 board와 연결
    private Comment comment;

    private String reCommentWriter;

    @CreatedDate//엔티티가 생성될 때 자동으로 생성시간을 기록하는 어노테이션
    @Column(updatable = false)//한번 올라가면 수정안되게
    private LocalDate reCommentDate; //LocalDate를 이용해 날짜를 저장

    @Builder
    public ReComment(String reCommentContent, Comment comment, String reCommentWriter){
        this.comment = comment;
        this.reCommentContent = reCommentContent;
        this.reCommentWriter = reCommentWriter;
    }

    public void update(String reCommentContent){
        this.reCommentContent = reCommentContent;
    }
}
