package com.example.api.Repository;

import com.example.api.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepositories extends JpaRepository<User,Long> {

    Optional<User> findUserByEmail(String email);
    Optional<User> findByEmail(String email);
   User findUserByEmailAndPassword(String email,String password);
   User findByUsername(String username);



}
