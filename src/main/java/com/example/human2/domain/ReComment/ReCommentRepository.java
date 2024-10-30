package com.example.human2.domain.ReComment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ReCommentRepository extends JpaRepository<ReComment, Long> {
    Optional<ReComment> findByCommentId(Long commentId);
}
