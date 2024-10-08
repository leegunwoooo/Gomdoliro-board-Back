package com.example.human2.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@RequiredArgsConstructor
@EnableWebSecurity
@Configuration
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((authorize) -> authorize
                        .requestMatchers("/login", "/signup", "/board-all").permitAll() // 로그인과 회원가입은 누구나 접근 가능
                        .requestMatchers("/board", "/board/").authenticated() // 특정 페이지는 인증 필요
                        .anyRequest().permitAll() // 나머지 페이지는 인증 없이 접근 가능
                )
                .formLogin((formLogin) -> formLogin
                        .loginPage("/login") // 사용자 정의 로그인 페이지
                        .defaultSuccessUrl("/board-all", true) // 로그인 성공 시 이동할 페이지
                )
                .logout((logout) -> logout
                        .logoutSuccessUrl("/login") // 로그아웃 성공 후 이동할 페이지
                        .invalidateHttpSession(true) // 세션 무효화
                );

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // 비밀번호 암호화
    }
}
