package com.example.mailApplication.service;

import com.example.mailApplication.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class MailService {


    private JavaMailSender javaMailSender;

    @Autowired
    public MailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }


    public void sendEmail(com.example.mailApplication.model.User user) throws MailException {

        SimpleMailMessage mail = new SimpleMailMessage();

        mail.setTo(user.getEmailAddress());
        mail.setSubject("Testing Mail API");
            mail.setText("SENTTT");

        javaMailSender.send(mail);
    }

    public void sendEmailWithAttachment(User user) throws MailException, MessagingException {

        MimeMessage message = javaMailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setTo(user.getEmailAddress());
        helper.setSubject("Testing Mail with Attachment");
        helper.setText("Please find the attached document below.");


        ClassPathResource classPathResource = new ClassPathResource("CV.pdf");
        helper.addAttachment(classPathResource.getFilename(), classPathResource);

        javaMailSender.send(message);
    }

}
