package com.example.api.Controllers;

import com.example.api.DTO.LoginDTO;
import com.example.api.DTO.UserDTO;
import com.example.api.Entity.User;
import com.example.api.ServicesImpl.UserServiceImplementation;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping

public class UserController {
    private  final UserServiceImplementation userImpl;
    private  final HttpServletRequest request;

    @GetMapping("/{userid}")
    public Optional<User> allbyId(@PathVariable Long userid){
        return userImpl.getByUserId(userid);
    }

    @GetMapping("/all")
    public ResponseEntity<List<User>> all(){
        return new ResponseEntity<>(userImpl.getUsers(),HttpStatus.OK);
    }
    @PostMapping("/create")
    public ResponseEntity<Object> allsave(@RequestBody UserDTO userDTO) {
        UserDTO user = userImpl.saves(userDTO);
        if(user!=null)
            return new ResponseEntity<>(user, HttpStatus.CREATED);
        else
            return new ResponseEntity<>(HttpStatus.CONFLICT);


    }
    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody LoginDTO loginDTO){
        UserDTO user = userImpl.Login(loginDTO);
        if(user!=null)
            return new ResponseEntity<>(user, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.CONFLICT);

    }

    @GetMapping("/logout")
    public ResponseEntity<Object> logout(HttpSession session){
        session.invalidate();
        HashMap<String,String> message = new HashMap<>();
        message.put("message","logout");
        return new ResponseEntity<>(message, HttpStatus.OK);

    }


}
