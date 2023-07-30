package com.application.blog.payloads;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class ApiResponse {
    private String message;
    private boolean success;

//    public ApiResponse(String userDeleteSuccessfully, boolean b) {
//        message=userDeleteSuccessfully;
//        success=b;
//    }
}
