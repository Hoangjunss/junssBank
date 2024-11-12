package com.hoangjunss.junsBank.service.User;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;


@Service
public class MailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendVerificationCode(String recipientEmail, String verificationCode) {
        String subject = "Your Verification Code";
        String body = "Hello,\n\nPlease use the following verification code to complete your registration: "
                + verificationCode
                + "\n\nThank you!";

        sendEmail(recipientEmail, subject, body);
    }

    private void sendEmail(String to, String subject, String body) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(body);

            mailSender.send(message);
            System.out.println("Email sent to " + to);
        } catch (MessagingException e) {
            System.err.println("Failed to send email to " + to + ". Error: " + e.getMessage());
            throw new RuntimeException("Failed to send email", e);
        }
    }
}
