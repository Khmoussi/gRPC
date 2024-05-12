package com.example.User.Entities.Requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
public class UserRequest {
    String email;
    String firstName;
    String lastName;
    String password;

}
