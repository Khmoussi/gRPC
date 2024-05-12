package com.example.Notification;

import com.example.Notification.Entities.AccountCreatedNotif;
import com.example.Notification.Services.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class NotificationController {
    private final NotificationService notificationService;

    @PostMapping("/notify")
    ResponseEntity<?>notify(@RequestBody AccountCreatedNotif request){
        try {
            this.notificationService.sendEmail(request);
            return ResponseEntity.ok().body("email sent successfully");

        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.internalServerError().body("unknown error");
        }

    }


}
