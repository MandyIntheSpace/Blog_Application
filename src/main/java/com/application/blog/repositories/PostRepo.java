package com.application.blog.repositories;

import com.application.blog.entities.Category;
import com.application.blog.entities.Post;
import com.application.blog.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepo extends JpaRepository <Post, Integer> {
    /**
     * These are the custome finder methods
     * **/
    List<Post> findByUser(User user);
//    List<Post> findAllByUser (User user);

    List<Post> findALlByCategory(Category category);

//    List<Post> searchPost(String keyword);

//    @Query("SELECT p from post p WHERE p.post_title like :key")
    List<Post> findByTitleContaining( String title);
}
