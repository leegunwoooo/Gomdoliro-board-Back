package com.example.human2.config;

import com.example.human2.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@RequiredArgsConstructor
@EnableWebSecurity
@Configuration
public class WebSecurityConfig {

    private final UserService userService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((authorize) -> authorize // 권한 설정
                        .requestMatchers("/login", "/signup", "/user").permitAll() // 누구나 접근 허용
                )
                .formLogin((formLogin) -> formLogin // 폼 기반 로그인 설정
                        .loginPage("/login") // 사용자 정의 로그인 페이지
                        .defaultSuccessUrl("/board-all") // 로그인 성공 시 이동할 페이지
                )
                .logout((logout) -> logout // 로그아웃 설정
                        .logoutSuccessUrl("/login") // 로그아웃 성공 후 이동할 페이지
                        .invalidateHttpSession(true) // 세션 무효화
                );

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // 비밀번호 암호화
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}
