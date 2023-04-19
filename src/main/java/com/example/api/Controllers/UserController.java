package com.example.api.Controllers;

import com.example.api.DTO.LoginDTO;
import com.example.api.DTO.UserDTO;
import com.example.api.Entity.User;
import com.example.api.ServicesImpl.UserImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class UserController {
    private  final UserImpl userImpl;

    @GetMapping("/user/{userid}")
    public Optional<User> allbyId(@PathVariable Long userid){
        return userImpl.getByUserId(userid);
    }

    @GetMapping("/users")
    public List<User> all(){
        return userImpl.getUsers();
    }
    @PostMapping("/save")
    public ResponseEntity<Object> allsave(@RequestBody UserDTO userDTO) {
        User user = userImpl.saves(userDTO);
        return new ResponseEntity<>(user, HttpStatus.CREATED);

    }
    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody LoginDTO loginDTO){
        User user1 = userImpl.Login(loginDTO);
        return new ResponseEntity<>(user1, HttpStatus.OK);

    }


}
