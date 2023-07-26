package com.application.blog.payloads;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

@NoArgsConstructor
@Getter
@Setter
public class UserDto {
    private int id;
    private String name;
    private String email;
    private String password;
    private String about;
}
