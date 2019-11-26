package com.alokjgoyal.DonorServer.controller;

import com.alokjgoyal.DonorServer.model.User;
import com.alokjgoyal.DonorServer.service.UserService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping(value = "/user/update", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody String updateUser(@RequestBody String user_info)
    {
        JSONObject user_data = new JSONObject(user_info);
        JSONObject response = new JSONObject();

        UUID user_id = UUID.fromString(user_data.get("id").toString());
        Optional<User> user = userService.userExists(user_id);
        if(user.isPresent())
        {
//            System.out.println(user_data.toString());
            //Setting User fields
            user.get().setBlood_group(user_data.get("blood_group").toString());
            user.get().setFirst_name(user_data.get("first_name").toString());
            user.get().setLast_name(user_data.get("last_name").toString());
            user.get().setGender(user_data.get("gender").toString());
            user.get().setPhone_no(user_data.get("phone_no").toString());
            user.get().setAddress(user_data.get("address").toString());
            user.get().setLongitude(user_data.get("longitude").toString());
            user.get().setLatitude(user_data.get("latitude").toString());

            userService.updateUser(user.get());
            response.put("status", true);
            response.put("message", "Profile updated successfully");

//            else
//            {
//                response.put("status", false);
//                response.put("message", "Error updating profile. Please try again");
//            }
        }
        else
        {
            //User does not exist
            response.put("status", false);
            response.put("message", "Invalid request");
        }

        return response.toString();
    }

    @GetMapping(value = "/user/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody List<User> getAllUsers()
    {
        return userService.getAllUsers();
    }

    @GetMapping(value = "/user/{user_id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody String getUserById(@PathVariable UUID user_id)
    {
        JSONObject response = new JSONObject();
        Optional<User> user = userService.userExists(user_id);
        if(user.isPresent())
        {
            response.put("status", true);
            response.put("user_info", new JSONObject(user.get()));
            response.put("message", "User info fetched");
        }
        else
        {
            response.put("status", false);
            response.put("user_info", new JSONObject());
            response.put("message", "User not found");
        }

        return response.toString();
    }
}