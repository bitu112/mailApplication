package com.example.mailApplication.controller;

import com.example.mailApplication.model.User;
import com.example.mailApplication.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;

@RestController
public class RegistrationController {

    @Autowired
    private MailService notificationService;

    @Autowired
    private User user;

    @RequestMapping("send-mail")
    public String send() {


        user.setFirstName("Gabriel");
        user.setLastName("Stancu");
        user.setEmailAddress("alecu.florin.gabriel@gmail.com"); //Receiver's email address

        try {
            notificationService.sendEmail(user);
        } catch (MailException mailException) {
            System.out.println(mailException);
        }
        return "Congratulations! Your mail has been send to the user.";
    }


    @RequestMapping("send-mail-attachment")
    public String sendWithAttachment() throws MessagingException {

        user.setFirstName("Gabi");
        user.setLastName("Stancu");
        user.setEmailAddress("bitu1997@yahoo.com"); //Receiver's email address

        try {
            notificationService.sendEmailWithAttachment(user);
        } catch (MailException mailException) {
            System.out.println(mailException);
        }
        return "Congratulations! Your mail has been send to the user.";
    }
}
