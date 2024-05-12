package com.example.Notification.Entities;

import lombok.Data;

@Data
public class AccountCreatedNotif {
    String email;
    String firstName;
    String lastname;

    public AccountCreatedNotif(String email, String firstName, String lastname) {
        this.email = email;
        this.firstName = firstName;
        this.lastname = lastname;
    }
}
