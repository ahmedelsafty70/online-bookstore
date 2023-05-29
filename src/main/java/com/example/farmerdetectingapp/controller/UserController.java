package com.example.farmerdetectingapp.controller;

import com.example.farmerdetectingapp.model.DtoUserProfile;
import com.example.farmerdetectingapp.model.UserProfile;
import com.example.farmerdetectingapp.service.UserService;
import com.google.firebase.messaging.FirebaseMessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/user-profile")
@CrossOrigin("*")
public class UserController {

    private final UserService userService;


    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/allUsers")
    public List<UserProfile> getUserProfiles(){
        return userService.getUserProfiles();
    }

    @PostMapping("/login/{email}")
    public String loginInAsAdmin(@PathVariable String email, @RequestBody String password){
        return userService.checkingLogin( email,  password);
    }

    @GetMapping("/details/{id}")
    public UserProfile getDetailsForUserProfile(@PathVariable Long id){
        return userService.getUserDetails(id);
    }

    @PostMapping("/creating")
    public String creatingUserProfile(@RequestBody DtoUserProfile dtoUserProfile){
        return userService.creatingUserProfile(dtoUserProfile);
    }

    @PostMapping("/notification")
    public void sendNotification() throws FirebaseMessagingException {
         userService.sendNotification("fMyAzcJ2SreudEPyOlHi7Z:APA91bEbhXi3NPP04lSU7T46w-eh6WJuPl0H0VjqOILV-NHlNx2CXLcpUjkvRjKBWkxcPUKQDRwBfQkPwJsBulm5wKmW64MQzaNAp6A0YGlxjgEzTM5Ia6DB3Z-WB9wUGYxm0Tr010od","Ahmed Elsafty","3ayaz sultan 3ashan as7a ba3ayat");
    }


    @PostMapping("/send-token/{email}")
    public void sendToken(@PathVariable String email, @RequestParam String token){
         userService.saveToken(email, token);
    }


}
