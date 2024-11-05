package com.example.human2.controller;

import com.example.human2.controller.dto.User.UserDto;
import com.example.human2.domain.User.User;
import com.example.human2.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@RestController
public class UserController {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;

    @PostMapping("/signup")
    public ResponseEntity<Map<String, String>> signup(@RequestBody UserDto infoDto) { // 회원 추가
        userService.save(infoDto);
        Map<String, String> response = new HashMap<>();
        response.put("success", "1");  // 필드명을 "success"로 변경
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/signup-name")
    public ResponseEntity<Map<String, String>> updateName(@RequestBody Map<String, String> request) {
        String email = request.get("email"); // 요청에서 이메일 추출
        String nickname = request.get("nickName"); // 요청에서 닉네임 추출
        userService.updateName(email, nickname); // 닉네임 저장
        Map<String, String> response = new HashMap<>();
        response.put("success", "1");
        return ResponseEntity.ok(response);
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

            User user = (User) authentication.getPrincipal();
            // 로그인 성공 시 JSON 응답
            Map<String, String> response = new HashMap<>();
            response.put("success", "1");  // 필드명을 "success"로 변경
            response.put("nickname", user.getUsername());
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            // 인증 실패 시 401 상태 코드와 함께 실패 메시지 반환
            Map<String, String> response = new HashMap<>();
            response.put("success", "0");
            return ResponseEntity.status(401).body(response);
        }
    }
}
