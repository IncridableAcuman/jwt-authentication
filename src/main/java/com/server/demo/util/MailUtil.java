package com.server.demo.util;

import com.server.demo.exception.BadRequestException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@EnableAsync
@Slf4j
public class MailUtil {
    private final JavaMailSender javaMailSender;

    @Async
    public void sendMail(String to,String subject,String text){
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(to);
            message.setSubject(subject);
            message.setText(text);
            javaMailSender.send(message);
        } catch (Exception e) {
            throw new BadRequestException(e.getMessage());
        }
    }
    @Async
    public void sendHTMLBody(String to,String subject,String htmlBody)  {
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message,"UTF-8");
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(htmlBody,true);
            javaMailSender.send(message);
        } catch (Exception e) {
            throw new BadRequestException(e.getMessage());
        }
    }
}
