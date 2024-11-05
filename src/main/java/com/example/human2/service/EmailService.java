package com.example.human2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Service
public class EmailService {

    @Autowired//이 부분이 문제에요
    private JavaMailSender javaMailSender;//이 부분이 문제에요

    private final Map<String, String> verificationCodes = new HashMap<>();

    // 인증번호 생성 메서드
    public String generateVerificationCode() {
        Random random = new Random();
        int code = 100000 + random.nextInt(900000);
        return String.valueOf(code);
    }

    // 인증번호 전송 메서드
    public void sendVerificationEmail(String email) {
        String code = generateVerificationCode();
        verificationCodes.put(email, code);

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("인증코드입니다");
        message.setText("인증코드: " + code);
        message.setFrom("leegunwoo0325@gmail.com");

        javaMailSender.send(message);
    }

    // 인증번호 검증 메서드
    public boolean verifyCode(String email, String code) {
        String storedCode = verificationCodes.get(email);
        if (storedCode != null && storedCode.equals(code)) {
            verificationCodes.remove(email);
            return true;
        }
        return false;
    }
}