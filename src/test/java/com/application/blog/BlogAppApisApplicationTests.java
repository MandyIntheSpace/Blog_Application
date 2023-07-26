package com.application.blog;

import com.application.blog.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BlogAppApisApplicationTests {

    @Autowired
    private UserRepository userRepository;

    @Test
    void contextLoads() {
    }

    @Test
    void repoTest() {
        String className = this.userRepository.getClass().getName();
        String packageName = this.userRepository.getClass().getPackageName();
        System.out.println("The class name isn" + className);
        System.out.println("The package name is: " + packageName);
    }

}
