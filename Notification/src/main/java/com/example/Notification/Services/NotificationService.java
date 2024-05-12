package com.example.Notification.Services;

import com.example.Notification.Entities.AccountCreatedNotif;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;


@Service
public class NotificationService {
    @Autowired
      JavaMailSender javaMailSender;

   public void sendEmail(AccountCreatedNotif request){

            SimpleMailMessage msg = new SimpleMailMessage();
            msg.setFrom("khmoussiaouina@gmail.com");
            msg.setTo(request.getEmail());

            msg.setSubject("Our Service SignUp");
            msg.setText(  "Welcome "+ request.getFirstName()+" "+request.getLastname()+" To our service we are so glad tht you have subscribed "  + ". \n"
                    + "Regards \n");


            javaMailSender.send(msg);


    }
}
