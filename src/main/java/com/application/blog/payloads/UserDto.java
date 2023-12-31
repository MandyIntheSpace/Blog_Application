package com.application.blog.payloads;

//import jakarta.validation.constraints.*;
import com.application.blog.entities.Role;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
public class UserDto {
    private int id;
    @NotNull()
    @NotBlank()
    @Size(min = 4, message = "Username must be min of 4 characters")
    private String name;
    @NotNull()
    @NotBlank
    @Email(message = "Email address is not valid")
    private String email;
    @NotNull
    @NotBlank
    @Size(min = 3, max = 10, message = "Password must be minimum of 3 chars and max of 10 chars !!")
//    @Pattern(regexp = ) --> Reugular expression
    private String password;
    @NotNull
    @NotBlank
    private String about;

    private Set<RoleDto> roles = new HashSet<>();
}
