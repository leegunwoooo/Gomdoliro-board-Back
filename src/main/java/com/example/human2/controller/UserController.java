package com.example.human2.controller;

import com.example.human2.controller.dto.UserDto;
import com.example.human2.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody UserDto infoDto) { // 회원 추가
        userService.save(infoDto);
        return ResponseEntity.ok("회원가입이 완료되었습니다."); // 성공 메시지 반환
    }
}
