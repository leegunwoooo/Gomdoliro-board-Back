package com.example.human2.service;

import com.example.human2.controller.dto.User.UserDto;
import com.example.human2.domain.User.User;
import com.example.human2.domain.User.UserRepository;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
@NoArgsConstructor(force = true)
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final EmailService emailService;
    private final PasswordEncoder passwordEncoder; // 주입된 PasswordEncoder 사용

    private final Map<String, String> verificationCodes = new HashMap<>();

    @Override
    public User loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(email));
    }

    public Long save(UserDto userDto) {
        if (userRepository.findByEmail(userDto.getEmail()).isPresent()) {
            throw new IllegalArgumentException("이미 존재하는 이메일입니다.");
        }

        String encodedPassword = passwordEncoder.encode(userDto.getPassword()); // 암호화된 비밀번호 저장

        User user = User.builder()
                .email(userDto.getEmail())
                .password(encodedPassword)
                .build();

        return userRepository.save(user).getId();
    }

    public void updateName(String email, String nickname) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("회원 정보를 찾을 수 없습니다."));
        if (userRepository.findByNickName(nickname).isPresent()) {
            throw new IllegalArgumentException("이미 존재하는 닉네임입니다.");
        }
        user.setNickname(nickname);
        userRepository.save(user);
    }

    public void sendVerificationCode(String email) throws Exception {
        String code = emailService.sendVerificationEmail(email);
        verificationCodes.put(email, code);
    }

    public boolean verifyCode(String email, String code) {
        return code.equals(verificationCodes.get(email));
    }

    public void resetPassword(String email, String newPassword) {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 이메일입니다."));
        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);
    }
}
