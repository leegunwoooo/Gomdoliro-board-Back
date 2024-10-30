package com.example.human2.domain.ReComment;

import com.example.human2.domain.Comment.Comment;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

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

    @Builder
    public ReComment(String reCommentContent, Comment comment, String reCommentWriter){
        this.comment = comment;
        this.reCommentContent = reCommentContent;
        this.reCommentWriter = reCommentWriter;
    }
}
