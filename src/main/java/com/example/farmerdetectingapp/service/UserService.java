package com.example.farmerdetectingapp.service;

import com.example.farmerdetectingapp.model.DtoUserProfile;
import com.example.farmerdetectingapp.model.UserProfile;
import com.example.farmerdetectingapp.repository.UserRepository;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {


    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    private UserRepository userRepository;

    public List<UserProfile> getUserProfiles() {
        return userRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
    }

    public String checkingLogin(String email, String password) {
        UserProfile userProfile = userRepository.findAllByEmail(email).orElse(null);

        if(!EmailValidator.getInstance().isValid(email))
            return "enter valid email";
        if (userProfile == null ) {
            return "create an account";
        } else if (!userProfile.getPassword().equals(password)) {
            return "wrong password";
        } else{
            return "true";
        }
    }

    public UserProfile getUserDetails(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public String creatingUserProfile(DtoUserProfile dtoUserProfile) {

        if(dtoUserProfile.getEmail() == null)
            return "email is null";
        if(!EmailValidator.getInstance().isValid(dtoUserProfile.getEmail()))
            return "enter valid email";
            //throw new ResourceNotFoundException("email is null");
        if(userRepository.findAllByEmail(dtoUserProfile.getEmail()).orElse(null) != null)
            return "user with this email is added before!";
            //throw new BadArgumentsException("user with this email is added before!");
        if(dtoUserProfile.getFirstName() == null)
            return "firstname is null";
            //throw new ResourceNotFoundException("firstname is null");
        if(dtoUserProfile.getLastName() == null)
            return "lastname is null";
            //throw new ResourceNotFoundException("lastname is null");
        if(dtoUserProfile.getPassword() == null)
            return "password is null";
            //throw new ResourceNotFoundException("password is null");



        UserProfile userProfile = UserProfile.builder()
                .firstName(dtoUserProfile.getFirstName())
                .lastName(dtoUserProfile.getLastName())
                .email(dtoUserProfile.getEmail())
                .password(dtoUserProfile.getPassword())
                .build();

         userRepository.save(userProfile);

         return "created";
    }

//    public void sendNotification(String token, String title, String body) throws FirebaseMessagingException {
//        Notification notification = Notification.builder()
//                .setTitle(title)
//                .setBody(body)
//                .build();
//
//        Message message = Message.builder()
//                .setToken(token)
//                .setNotification(notification)
//                .build();
//
//        FirebaseMessaging.getInstance().send(message);
//    }
//
//    public void saveToken(String email, String token) {
//        UserProfile userProfile = userRepository.findAllByEmail(email).orElse(null);
//        userProfile.setToken(token);
//
//    }
}
