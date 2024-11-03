package com.example.human2.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Random;

@RequiredArgsConstructor
@Service
public class EmailService {
    private final JavaMailSender mailSender;

    public String sendVerificationEmail(String to) throws MessagingException {
        String verificationCode = generateVerificationCode();
        MimeMessage message = mailSender.createMimeMessage();
        message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(to));
        message.setSubject("비밀번호 재설정 인증 코드");
        message.setText("인증 코드: " + verificationCode, "utf-8");
        mailSender.send(message);
        return verificationCode;
    }

    private String generateVerificationCode() {
        Random random = new Random();
        return String.format("%06d", random.nextInt(1000000));
    }

}
