package com.application.blog.payloads;

//import jakarta.validation.constraints.NotBlank;
//import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@NoArgsConstructor
@Getter
@Setter
public class CategoryDto {
    private int categoryId;
    @NotBlank
    @Size(min = 4, message = "Min size of category title is 4")
    private String categoryTitle;
    @NotBlank
    @Size(min = 10, message = "Min size of category description is 10")
    private String categoryDescription;
}
