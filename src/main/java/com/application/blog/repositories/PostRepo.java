package com.application.blog.repositories;

import com.application.blog.entities.Category;
import com.application.blog.entities.Post;
import com.application.blog.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepo extends JpaRepository <Post, Integer> {
    /**
     * These are the custome finder methods
     * **/
    List<Post> getAllByUser (User user);
    List<Category> findALlByCategory(Category category);
}
