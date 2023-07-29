package com.application.blog.controllers;

import com.application.blog.payloads.ApiResponse;
import com.application.blog.payloads.UserDto;
import com.application.blog.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
@AllArgsConstructor
public class UserController {

    private UserService userService;
    @PostMapping("/")
    public ResponseEntity<UserDto> createuser(@RequestBody UserDto userDto) {
        UserDto userDto1 = this.userService.create(userDto);
        return new ResponseEntity<>(userDto1, HttpStatus.CREATED);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<UserDto> updateUser(@RequestBody UserDto userDto, @PathVariable("userId") Integer userId) {
        UserDto userDto1 = this.userService.updateUser(userDto, userId);
        return ResponseEntity.ok(userDto1);
    }
    @DeleteMapping("/{userId}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable("userId") Integer userId) {
        this.userService.deleteUser(userId);
//        return new ResponseEntity(Map.of("message", "User Deleted Successfully"), HttpStatus.OK);
        return new ResponseEntity(new ApiResponse("User Delete Successfully", true), HttpStatus.OK);
    }
    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getUserById(@PathVariable("userId") Integer userId) {
        UserDto userDto = this.userService.getUserById(userId);
        System.out.println(userDto);
        if (userDto == null) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.ok(userDto);
    }
    @GetMapping("/")
    public ResponseEntity<List<UserDto>> getAllUser() {
//        List<UserDto> userDtoList = this.userService.getAllUsers();
//        System.out.println(userDtoList);
//        if (userDtoList == null) {
//            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
//        }
//        return new ResponseEntity(new ApiResponse("User got all successfully", true), HttpStatus.OK);
        return ResponseEntity.ok(this.userService.getAllUsers());
    }
}
