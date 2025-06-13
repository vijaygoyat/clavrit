package com.example.demo.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailService {

    @Autowired
    private JavaMailSender mailSender;

    Logger logger = LoggerFactory.getLogger(MailService.class);

    @Value("${spring.mail.username}")
    private String fromEmail;

    public String sendMail(String receiverMail, String subject, String body) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(fromEmail);
            message.setTo(receiverMail);
            message.setSubject(subject);
            message.setText(body);

            mailSender.send(message);

            String successMessage = "Email sent successfully to " + receiverMail;
            logger.info(successMessage);
            return successMessage;

        } catch (MailException e) {
            String errorMessage = "Failed to send email to " + receiverMail + ". Error: " + e.getMessage();
            logger.error(errorMessage+ e.getMessage());
            return errorMessage;
        } catch (Exception e) {
            String errorMessage = "Unexpected error occurred while sending mail to " + receiverMail + ". Error: " + e.getMessage();
            logger.error(errorMessage + e.getMessage());
            return errorMessage;
        }
    }
}
