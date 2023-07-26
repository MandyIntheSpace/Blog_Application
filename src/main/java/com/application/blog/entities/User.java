package com.application.blog.entities;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    public void User() {
    }

}
