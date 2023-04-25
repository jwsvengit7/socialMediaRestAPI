package com.example.api.ServicesImpl;

import com.example.api.DTO.LoginDTO;
import com.example.api.DTO.UserDTO;
import com.example.api.Entity.User;
import com.example.api.Enums.Roles;
import com.example.api.Exceptions.EmailAddressAlreadyExistException;
import com.example.api.Exceptions.LoginExceptionMessages;
import com.example.api.Repository.UserRepository;

import com.example.api.Services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class UserServiceImplementation implements UserService {
    private final UserRepository userRepository;
    private final HttpServletRequest request;
    @Override

    public Optional<User> getByUserId(Long userid)
    {
        return userRepository.findById(userid);
    }
    @Override
    public List<User> getUsers(){
        return userRepository.findAll();
    }
    @Override
    public User saves(UserDTO userDTO) {
        userRepository.findUserByEmail(userDTO.getEmail()).ifPresent(user -> {
                    throw new EmailAddressAlreadyExistException(userDTO.getEmail());
                });
            User user = new User();
                    user.setName(userDTO.getName());
                    user.setEmail(userDTO.getEmail());
                    user.setPassword(userDTO.getPassword());
                    user.setRoles(Roles.USER);
            return userRepository.save(user);
    }

    @Override
    public User Login(LoginDTO loginDTO) throws EmailAddressAlreadyExistException {
        User user = userRepository.findUserByEmailAndPassword(loginDTO.getEmail(), loginDTO.getPassword());
        if (user!=null) {
            HttpSession session = request.getSession();
            session.setAttribute("userDTO", user);
            return user;
        } else {
            throw new LoginExceptionMessages("Invalid email or password");
        }

    }

}
