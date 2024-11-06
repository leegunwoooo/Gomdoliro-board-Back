package com.example.human2.domain.ReComment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface ReCommentRepository extends JpaRepository<ReComment, Long> {
    List<ReComment> findByCommentId(Long commentId);
    void deleteByCommentId(Long commentId);
}
