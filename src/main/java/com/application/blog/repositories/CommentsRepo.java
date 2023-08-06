package com.application.blog.repositories;

import com.application.blog.entities.Comments;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentsRepo extends JpaRepository<Comments, Integer> {

}
