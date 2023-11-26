package com.example.bssBack.controller;

import com.example.bssBack.entity.User;
import com.example.bssBack.service.UserService;
import com.example.bssBack.utility.Security;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

@Controller
public class UserContorller {

    private UserService userService;

    @Autowired
    public UserContorller(UserService userService){
        this.userService = userService;
    }


    @GetMapping("api/get/user")
    public ResponseEntity getUser(){
        User user = (User) userService.loadUserByUsername(Security.getCurrentUsername());

        HashMap<String, Object> result = new HashMap<>();
        result.put("username", user.getUsername());
        result.put("email", user.getEmail());
        result.put("id", user.getId());

        return new ResponseEntity<>(result, HttpStatus.OK);
    }



}
