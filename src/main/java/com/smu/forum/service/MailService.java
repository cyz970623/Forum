package com.smu.forum.service;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.springframework.mail.javamail.JavaMailSender;
import org.thymeleaf.context.Context;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Date;

@Service
public class MailService {

    @Value("${spring.mail.username}")
    private String mailUsername;

    @Resource
    private JavaMailSender javaMailSender;

    @Resource
    private TemplateEngine templateEngine;

    /**
     *send activate email
     * @param activationUrl
     * @param email
     */
    public void senMailForActivateAccount(String activationUrl, String email){
        //create mail object
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();

        try{
            MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true);

            //set mail theme
            message.setSubject("Welcome to cloud computing project!");

            //set sender
            message.setFrom(mailUsername);
            System.out.println(mailUsername);
            //set reciever
            message.setTo(email);
            //System.out.println();
            //cc
            //message.setCc();
            //bcc
            //message.setBcc();
            //set send date
            message.setSentDate(new Date());

            //create context
            Context context = new Context();
            //link address
            context.setVariable("activationUrl", activationUrl);
            String text = templateEngine.process("activation-account.html", context);

            //set mail content
            message.setText(text,true);
        }catch(MessagingException e){
            e.printStackTrace();
        }
        javaMailSender.send(mimeMessage);

    }
}
