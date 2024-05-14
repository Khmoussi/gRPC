package com.example.User.Services;

import com.example.User.Entities.Requests.UserRequest;
import com.example.User.Entities.User;
import com.example.User.Repositories.UserRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public void saveUser(UserRequest userRequest){
        this.userRepository.save(new User(userRequest.getEmail(), userRequest.getFirstName(), userRequest.getLastName(), userRequest.getPassword()));
    }

    public void deleteUser(String email) {
        this.userRepository.deleteById(email);
    }
}
