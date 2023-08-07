package com.application.blog;

import com.application.blog.constant.PageAndSortingConstant;
import com.application.blog.entities.Role;
import com.application.blog.repositories.RoleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@SpringBootApplication
public class BlogAppApisApplication implements CommandLineRunner {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private RoleRepo roleRepo;
    public static void main(String[] args) {
        SpringApplication.run(BlogAppApisApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println(this.passwordEncoder.encode("1122311223"));
        Role role;
        Role role1;
        try{
            role = new Role();
            role.setId(PageAndSortingConstant.ADMIN_USER);
            role.setName("ADMIN_USER");

            role1 = new Role();
            role1.setId(PageAndSortingConstant.NORMAL_USERS);
            role1.setName("NORMAL_USER");

            List<Role> roles = List.of(role, role1);
            List<Role> roleList = this.roleRepo.saveAll(roles);
            roleList.forEach(result -> {
                System.out.println(result);
            });
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
