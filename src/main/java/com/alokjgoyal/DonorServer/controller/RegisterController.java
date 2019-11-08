package com.alokjgoyal.DonorServer.controller;

import com.alokjgoyal.DonorServer.model.User;
import com.alokjgoyal.DonorServer.service.UserService;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.awt.*;
import java.util.Optional;

@RestController
public class RegisterController {

    @Autowired
    UserService userService;

    @PostMapping(value = "/register", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody String register(@RequestBody String registration_info)
    {
        JSONObject registration = new JSONObject(registration_info);

        JSONObject response = new JSONObject();
        String email = (String) registration.get("email");
        if(userService.userExists(email).isPresent())
        {
            response.put("status", false);
            response.put("message","User with this email already exists");
        }
        else
        {
            User user = userService.addUser(registration);
            response.put("status", true);
            response.put("message","User registered successfully");
            response.put("userid", user.getId().toString());
        }

        return response.toString();
    }


    @PostMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody String login(@RequestBody String login_info, HttpSession session)
    {
        JSONObject login_form = new JSONObject(login_info);

        String email = (String) login_form.get("email");
        Optional<User> user = userService.userExists(email);

        JSONObject response = new JSONObject();
        if(user.isPresent())
        {
            String password = login_form.get("password").toString();
            if(password.equals(user.get().getPassword()))
            {
                String token = user.get().getId().toString();
                session.setAttribute("token", token);
                response.put("status", true);
                response.put("token",token);
            }
            else
            {
                response.put("status", false);
                response.put("message","Invalid username and password");
            }
        }
        else
        {
            response.put("status", false);
            response.put("message","Invalid username and password");
        }

        return response.toString();
    }
}