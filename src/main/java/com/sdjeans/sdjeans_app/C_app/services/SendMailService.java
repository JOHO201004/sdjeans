package com.sdjeans.sdjeans_app.C_app.Services;

import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;

import jakarta.servlet.http.HttpSession;

@Component
public class SendMailService {    

    private final MailSender mailSender;

    public SendMailService(MailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendMail(HttpSession session) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom("201013@std.hi-joho.ac.jp"); // 送信元メールアドレス
        mailMessage.setTo("201004@std.hi-joho.ac.jp");
        mailMessage.setSubject("賞味・消費期限が近づいています！");
        mailMessage.setText(session.getAttribute("notifyItems") + "が賞味期限または消費期限に近づいています！");

        try {
            mailSender.send(mailMessage);
        } catch (MailException e) {
            // TODO: エラー処理
        }
    }
}
