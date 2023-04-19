package com.example.api.Entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Entity
@Data
@Table(name = "users")
@RequiredArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String email;
    private String password;

    @OneToMany(mappedBy = "users", fetch = FetchType.LAZY)
    private List<Comment> comments;

}
