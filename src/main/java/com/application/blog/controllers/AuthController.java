package com.application.blog.controllers;

import com.application.blog.Security.JwtTokenHelper;
import com.application.blog.payloads.JwtAuthRequest;
import com.application.blog.payloads.JwtAuthResponse;
import com.application.blog.constant.ApiConstant;
import com.application.blog.payloads.UserDto;
import com.application.blog.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
//@RequestMapping(ApiConstant.POST_COMMENT_REQUEST_MAPPING + ApiConstant.AUTH_URL)
public class AuthController {
    private JwtTokenHelper jwtTokenHelper;
    private UserDetailsService userDetailsService;
    private AuthenticationManager authenticationManager;
    private UserService userService;
    @PostMapping(ApiConstant.LOGIN_URL)
//@PostMapping("/login")
public ResponseEntity<JwtAuthResponse> createToken(
            @RequestBody JwtAuthRequest jwtAuthRequest
            ) {
        this.authenticate(jwtAuthRequest.getEmail(), jwtAuthRequest.getPassword());
        UserDetails userDetails = this.userDetailsService.loadUserByUsername(jwtAuthRequest.getEmail());
        String token = this.jwtTokenHelper.generateToken(userDetails);
        JwtAuthResponse jwtAuthResponse = new JwtAuthResponse();
        jwtAuthResponse.setToken(token);
        System.out.println(token + "Checking token");
        return new ResponseEntity<JwtAuthResponse>(jwtAuthResponse, HttpStatus.OK);
    }

    private void authenticate(String username, String password){
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(username, password);
            this.authenticationManager.authenticate(usernamePasswordAuthenticationToken);
    }

    @PostMapping(ApiConstant.REGISTER_URL)
    private ResponseEntity<UserDto> registerUser(@RequestBody UserDto userDto) {
        UserDto userDto1 = this.userService.registerNewUser(userDto);
        return new ResponseEntity<UserDto>(userDto1, HttpStatus.CREATED);
    }
}
