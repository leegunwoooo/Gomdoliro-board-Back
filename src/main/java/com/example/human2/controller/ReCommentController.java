package com.example.human2.controller;

import com.example.human2.controller.dto.ReComment.ReCommentResponse;
import com.example.human2.controller.dto.ReComment.SaveReCommentRequest;
import com.example.human2.service.ReCommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/board/{boardId}/comments/{commentsId}/recomment")
public class ReCommentController {
    private final ReCommentService reCommentService;
    @PostMapping
    public ResponseEntity<ReCommentResponse> save(@PathVariable Long commentsId, @RequestBody SaveReCommentRequest request){
        request.setCommentId(commentsId);
        ReCommentResponse reCommentResponse = reCommentService.save(request);
        return ResponseEntity.ok(reCommentResponse);
    }
}
