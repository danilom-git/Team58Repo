package team58.healthy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class EmailService {
    @Autowired
    public JavaMailSender emailSender;

    public void sendSimpleMail(String to, String subject, String text)
    {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setFrom("isaprojektovanje@gmail.com");
        message.setSubject(subject);
        message.setText(text);
        emailSender.send(message);
    }

    public void sendHtmlMail(String to,String subject,String htmlText) throws MessagingException {
        MimeMessage mess = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mess, "utf-8");
        helper.setText(htmlText,true);
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setFrom("isaprojektovanje@gmail.com");
        emailSender.send(mess);
    }
}
