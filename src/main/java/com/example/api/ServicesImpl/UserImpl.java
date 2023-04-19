package com.example.api.ServicesImpl;

import com.example.api.DTO.LoginDTO;
import com.example.api.DTO.UserDTO;
import com.example.api.Entity.User;
import com.example.api.Repository.UserRepository;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class UserImpl {
    private final UserRepository userRepository;
    private final HttpServletRequest request;

    public Optional<User> getByUserId(Long userid){
        return userRepository.findById(userid);
    }
    public List<User> getUsers(){
        return userRepository.findAll();
    }
    public User saves(UserDTO userDTO) {
        User find = userRepository.findUserByEmail(userDTO.getEmail());
        if (find!=null){
            throw  new RuntimeException("Email Already Exist");
        }
            User user = new User();
                    user.setName(userDTO.getName());
                    user.setEmail(userDTO.getEmail());
                    user.setPassword(userDTO.getPassword());
        return userRepository.save(user);
    }


    public User Login(LoginDTO loginDTO){
        User checkLogin = userRepository.findUserByEmailAndPassword(loginDTO.getEmail(),loginDTO.getPassword());
        if(checkLogin!=null){
            HttpSession session = request.getSession();
            session.setAttribute("id",checkLogin.getId());
            return checkLogin;
        }
        return null;
    }
}
