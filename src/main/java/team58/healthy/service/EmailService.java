package team58.healthy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    @Autowired
    public JavaMailSender emailSender;

    public void voidSendSimpleMail(String to, String subject, String text)
    {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setFrom("isaprojektovanje@gmail.com");
        message.setSubject(subject);
        message.setText(text);
        emailSender.send(message);
    }
}
