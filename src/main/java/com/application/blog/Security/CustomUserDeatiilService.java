package com.application.blog.Security;

import com.application.blog.entities.User;
import com.application.blog.exceptions.ResourceNotFoundException;
import com.application.blog.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class CustomUserDeatiilService implements UserDetailsService {

    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = this.userRepository.findByEmail(username).
                orElseThrow(() -> new ResourceNotFoundException("User", "email: "+username, 0));
        return user;
    }
}
