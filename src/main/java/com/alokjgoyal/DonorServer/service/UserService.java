package com.alokjgoyal.DonorServer.service;

import com.alokjgoyal.DonorServer.model.User;
import com.alokjgoyal.DonorServer.repository.UserRepository;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.*;

import javax.swing.text.html.Option;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public User addUser(JSONObject registration_data)
    {
        User user = new User();
        user.setId(UUID.randomUUID());
        user.setEmail(registration_data.get("email").toString());
        user.setPhone_no(registration_data.get("phone_no").toString());
        user.setPassword(registration_data.get("password").toString());
        userRepository.save(user);
        return user;
//        return "User saved with ID: " + user.getId();
    }

    public Optional<User> userExists(String email)
    {
        return userRepository.findUserByEmail(email);
    }
}
