package com.previo.procesos.services;

import com.previo.procesos.models.UserModel;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


public interface UserService {

    ResponseEntity<UserModel> createUser(UserModel userModel);

    ResponseEntity login(String email, String password);

}
