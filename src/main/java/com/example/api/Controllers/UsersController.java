package com.example.api.Controllers;

import com.example.api.DTO.RegisterRequest;
import com.example.api.DTO.UserRequest;
import com.example.api.Response.Response;
import com.example.api.ServicesImpl.UserServiceImplementations;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping

public class UsersController {
    private final UserServiceImplementations service;
    private final PostController postService;
    @PostMapping("/create")
    public ResponseEntity<Response> register(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(service.register(request));
    }
    @PostMapping("/authenticate")
    public ResponseEntity<Response> authenticate(@RequestBody UserRequest request) {
        return new ResponseEntity<>(service.authenticate(request), HttpStatus.OK);
    }

    @PostMapping("/refresh-token")
    public void refreshToken(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {
        service.refreshToken(request, response);
    }





}
