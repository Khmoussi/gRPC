package com.example.Notification.Services;

import com.example.Notification.Entities.AccountCreatedNotif;
import com.example.grpc.EmailRequest;
import com.example.grpc.NotifyGrpc;
import com.example.grpc.sent;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;


@Service
public class NotificationService extends NotifyGrpc.NotifyImplBase {
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
    @Override
    public void notifyUnary(com.example.grpc.EmailRequest request,
                            io.grpc.stub.StreamObserver<com.example.grpc.sent> responseObserver) {
       boolean result =false;
       try {
           this.sendEmail(new AccountCreatedNotif(request.getEmail(), request.getFirstname(), request.getLastname()));
           result=true;
       }catch (Exception e){
           e.printStackTrace();
       }

        sent response = sent.newBuilder()
             .setSent(result)
             .build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

}
