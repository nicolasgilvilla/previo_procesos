package com.previo.procesos.controllers;

import com.previo.procesos.models.UserModel;
import com.previo.procesos.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping(value = "auth/login")
    public ResponseEntity login(@RequestBody UserModel userModel) {
        return userService.login(userModel.getEmail(), userModel.getPassword());

    }


}