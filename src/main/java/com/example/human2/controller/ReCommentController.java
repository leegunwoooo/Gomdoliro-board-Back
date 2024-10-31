package com.example.human2.controller;

import com.example.human2.controller.dto.ReComment.ReCommentResponse;
import com.example.human2.controller.dto.ReComment.SaveReCommentRequest;
import com.example.human2.controller.dto.ReComment.UpdateReCommentRequest;
import com.example.human2.service.ReCommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/board/{boardId}/comments/{commentsId}/recomment")
public class ReCommentController {
    private final ReCommentService reCommentService;
    @PostMapping
    public ResponseEntity<ReCommentResponse> saveRecomment(@PathVariable Long commentsId, @RequestBody SaveReCommentRequest request){
        request.setCommentId(commentsId);
        ReCommentResponse reCommentResponse = reCommentService.save(request);
        return ResponseEntity.ok(reCommentResponse);
    }

    @PutMapping("/{recommentsId}")
    public ResponseEntity<ReCommentResponse> updateRecomment(@PathVariable Long recommentsId, @RequestBody UpdateReCommentRequest request){
        ReCommentResponse reCommentResponse = reCommentService.update(recommentsId, request);
        return ResponseEntity.ok(reCommentResponse);
    }

    @DeleteMapping("/{recommentsId}")
    public void deleteRecomment(@PathVariable Long recommentsId){
        reCommentService.delete(recommentsId);
    }

    @GetMapping("/read")
    public List<ReCommentResponse> getRecomments(@PathVariable Long commentsId){
        return reCommentService.findRecomments(commentsId);
    }
}
