package com.example.User.Controller;

import com.example.Notification.Entities.AccountCreatedNotif;
import com.example.User.Entities.Requests.DeleteRequest;
import com.example.User.Entities.Requests.UserRequest;
import com.example.User.Services.NotifyClient;
import com.example.User.Services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class UserController {
    private final UserService userService;
    private final NotifyClient notifyClient;

    public UserController(UserService userService) {
        this.userService = userService;
        NotifyClient client = new NotifyClient("localhost", 9090);
        this.notifyClient=client;

    }

    @PostMapping("/signup")
    ResponseEntity<?> signup(@RequestBody UserRequest request){

        try{
            this.userService.saveUser(request);
            this.notifyClient.notifyUnaryAsync(new AccountCreatedNotif(request.getEmail(), request.getFirstName(), request.getLastName()));
           System.out.println("user signed successfully");
            return  ResponseEntity.ok().body("user signed successfully");

        }catch (Exception e){
            e.printStackTrace();
           return  ResponseEntity.internalServerError().body("unknown error");
        }
    }
    @DeleteMapping("/deleteUser")
    ResponseEntity<?> deleteUser(@RequestBody DeleteRequest request){
        try {
            this.userService.deleteUser(request.getEmail());
            return ResponseEntity.ok().body("deleted successfully");

        }catch (Exception e){
            e.printStackTrace();
        }
        return ResponseEntity.internalServerError().body("unknown error");

    }
}
