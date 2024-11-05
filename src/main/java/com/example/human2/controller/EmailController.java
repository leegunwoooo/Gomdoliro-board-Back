package com.example.human2.controller;

import com.example.human2.service.EmailService;
import com.example.human2.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/email")
public class EmailController {

    private final EmailService emailService;
    private final UserService userService;

    // 인증번호 전송 요청
    @PostMapping("/sendCode")
    public ResponseEntity<Map<String, String>> sendVerificationCode(@RequestBody Map<String, String> request) {
        String email = request.get("email");
        emailService.sendVerificationEmail(email);

        Map<String, String> response = new HashMap<>();
        response.put("success", "1");
        return ResponseEntity.ok(response);
    }

    @PostMapping("/verifyCode")
    public ResponseEntity<Map<String, String>> verifyCode(@RequestBody Map<String, String> request) {
        String email = request.get("email");
        String code = request.get("code");

        Map<String, String> response = new HashMap<>();
        if (emailService.verifyCode(email, code)) {
            response.put("success", "1");
            return ResponseEntity.ok(response);
        } else {
            response.put("success", "0");
            response.put("message", "잘못된 코드입니다.");
            return ResponseEntity.status(401).body(response);
        }
    }

    // 비밀번호 변경 요청
    @PatchMapping("/changePw")
    public ResponseEntity<Map<String, String>> changePassword(@RequestBody Map<String, String> request) {
        String email = request.get("email");
        String newPassword = request.get("newPassword");

        userService.updatePasswordByEmail(email, newPassword); // 비밀번호 변경

        Map<String, String> response = new HashMap<>();
        response.put("success", "1");
        return ResponseEntity.ok(response);
    }
}
