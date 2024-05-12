package com.example.User.Controller;

import com.example.User.Entities.Requests.UserRequest;
import com.example.User.Services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class UserController {
    private final UserService userService;

    @PostMapping("/signup")
    ResponseEntity<?> signup(@RequestBody UserRequest request){

        try{
            this.userService.saveUser(request);
            return  ResponseEntity.ok().body("user signed successfully");

        }catch (Exception e){
            e.printStackTrace();
           return  ResponseEntity.internalServerError().body("unknown error");
        }
    }
}
