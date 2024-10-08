package com.example.human2.controller;

import com.example.human2.controller.dto.UserDto;
import com.example.human2.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication; // Spring Security의 Authentication 사용
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class UserController {

    private final UserService userService;
    private final AuthenticationManager authenticationManager; // AuthenticationManager 주입

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody UserDto infoDto) { // 회원 추가
        userService.save(infoDto);
        return ResponseEntity.ok("1");
    }

    @PostMapping("/signup-name")
    public ResponseEntity<String> name(@RequestBody UserDto userDto) {
        userService.saveName(userDto);  // 2단계 닉네임 저장
        return ResponseEntity.ok("1");
    }

    @PostMapping("/login") // /login 경로로 들어오는 POST 요청을 처리
    public ResponseEntity<?> login(@RequestBody UserDto userDto) { // 요청 본문에서 UserDto 객체를 받음
        try {
            // 인증 요청을 수행하고 인증 정보를 얻음
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(userDto.getEmail(), userDto.getPassword())
            );

            // 인증 정보가 성공적으로 설정되면 SecurityContext에 저장
            SecurityContextHolder.getContext().setAuthentication(authentication);

            // 로그인 성공 시 JSON 응답
            return ResponseEntity.ok("1");
        } catch (Exception e) {
            // 인증 실패 시 401 상태 코드와 함께 실패 메시지 반환
            return ResponseEntity.status(401).body("0");
        }
    }
}
