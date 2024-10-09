package com.example.human2.service;

import com.example.human2.controller.dto.UserDto;
import com.example.human2.domain.User;
import com.example.human2.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public User loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(email));
    }

    public Long save(UserDto userDto) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodedPassword = encoder.encode(userDto.getPassword()); // 암호화된 비밀번호 저장

        // 빌더 패턴을 사용하여 User 객체 생성
        User user = User.builder()
                .email(userDto.getEmail())
                .password(encodedPassword) // 암호화된 비밀번호 사용
                .build();

        return userRepository.save(user).getId(); // 저장된 사용자 정보의 Id 반환
    }

    /*public String updateName(UserDto userDto) {
        User user = userRepository.findByEmail(userDto.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("회원 정보를 찾을 수 없습니다."));
        user.setNickname(userDto.getNickName());  // 닉네임 설정
        userRepository.save(user); // 한 번만 호출
        return user.getNickName(); // 저장된 사용자로부터 닉네임 반환
    }*/
    public String updateName(String email, String nickname) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("회원 정보를 찾을 수 없습니다."));
        user.setNickname(nickname);  // 닉네임 설정
        userRepository.save(user);
        return user.getNickName();
    }



}
