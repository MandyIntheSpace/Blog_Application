package com.application.blog.services.impl;

import com.application.blog.entities.User;
import com.application.blog.payloads.UserDto;
import com.application.blog.repositories.UserRepository;
import com.application.blog.services.UserService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.application.blog.exceptions.ResourceNotFoundException;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserServicesimpl implements UserService {
    private UserRepository userRepository;
    private final ModelMapper modelMapper;
    @Override
    public UserDto create(UserDto userDto) {
        User user = this.dtoToUser(userDto);
        User user1 = this.userRepository.save(user);
        System.out.println(user1);
        return this.userToDto(user1);
    }

    @Override
    public UserDto updateUser(UserDto userDto, Integer userId) {
        User user = this.userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User", "id", userId));
            user.setName(userDto.getName());
            user.setEmail(userDto.getEmail());
            user.setPassword(userDto.getPassword());
            user.setAbout(userDto.getAbout());

            User user1 = this.userRepository.save(user);
            UserDto updatedUSer = this.userToDto(user1);
            return updatedUSer;
    }

    @Override
    public UserDto getUserById(Integer userId) {
        User user = this.userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
        System.out.println(user);
        return this.userToDto(user);
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> userList = this.userRepository.findAll();
//        List<UserDto> userDtoList = userList.stream().
//                map(user -> this.userToDto(user)).collect(Collectors.toList());
//        return userDtoList;
        List<UserDto> userDtoList = new ArrayList<>();
        for (User user : userList) {
            UserDto userDto = this.userToDto(user);
            userDtoList.add(userDto);
        }
        return userDtoList;
    }

    @Override
    public void deleteUser(Integer userId) {
        User user = this.userRepository.findById(userId).orElseThrow(() ->
            new ResourceNotFoundException("User", "id", userId)
        );
        this.userRepository.delete(user);
    }
    private User dtoToUser(UserDto userDto) {
//        User user = new User();
//        user.setId(userDto.getId());
//        user.setName(userDto.getName());
//        user.setEmail(userDto.getEmail());
//        user.setPassword(userDto.getPassword());
//        user.setAbout(userDto.getAbout());
        User user = this.modelMapper.map(userDto, User.class);
        return user;
    }

    public UserDto userToDto(User user) {
//        UserDto userDto = new UserDto();
//        userDto.setId(user.getId());
//        userDto.setName(user.getName());
//        userDto.setEmail(user.getEmail());
//        userDto.setPassword(user.getPassword());
//        userDto.setAbout(user.getAbout());
        UserDto userDto = this.modelMapper.map(user, UserDto.class);
        return userDto;
    }
}
