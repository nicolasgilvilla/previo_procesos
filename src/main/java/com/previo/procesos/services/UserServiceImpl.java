package com.previo.procesos.services;

import com.previo.procesos.models.UserModel;
import com.previo.procesos.repository.UserRepository;
import com.previo.procesos.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JWTUtil jwtUtil;

    @Override
    public ResponseEntity<UserModel> createUser(UserModel userModel) {
        try {
            userModel.setPassword(passwordEncoder.encode(userModel.getPassword()));
            userRepository.save(userModel);
            return new ResponseEntity(userModel, HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @Override
    public ResponseEntity login(String email, String password) {
        try {
            UserModel user = userRepository.findByEmail(email);
            if (passwordEncoder.matches(password, user.getPassword())) {
                String token = jwtUtil.create(String.valueOf(user.getId()), user.getEmail());
                return ResponseEntity.ok(token);
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.notFound().build();
    }
}
