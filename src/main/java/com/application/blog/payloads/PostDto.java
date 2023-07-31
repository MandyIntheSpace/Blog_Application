package com.application.blog.payloads;

import com.application.blog.entities.Category;
import com.application.blog.entities.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@NoArgsConstructor
@Getter
@Setter
public class PostDto {
    @NotBlank
    @Size(min = 4, message = "The min size of the title is four")
    private String title;
    @NotBlank(message = "The content should not be blank")
    private String content;

    private String imageName;
    private Date addedDate;
    private CategoryDto category;
    private UserDto user;
}
