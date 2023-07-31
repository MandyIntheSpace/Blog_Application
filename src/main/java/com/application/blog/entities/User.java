package com.application.blog.entities;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="User")
@NoArgsConstructor
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "user_name", nullable = false, length = 225)
    private String name;

    @Column(name = "user_email", nullable = false, length = 225)
    private String email;

    @Column(name  = "user_password", nullable = false, length = 225)
    private String password;

    @Column(name = "user_about", nullable = false, length = 225)
    private String about;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Post> postList = new ArrayList<>();

    public void User() {
    }

}
