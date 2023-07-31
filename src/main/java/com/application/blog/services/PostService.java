package com.application.blog.services;

import com.application.blog.entities.Post;
import com.application.blog.payloads.PostDto;

import java.util.List;

public interface PostService {
    PostDto createPost(PostDto postDto, Integer userId, Integer categoryId);
    PostDto updatePost(PostDto postDto, Integer postId);
    Void deletePost(Integer postId);
    List<PostDto> getAllPost();
    PostDto getPostByID(Integer postId);
    List<PostDto> getPostByCategory(Integer categoryId);
    List<PostDto> getPostByUser(Integer userId);
    List<PostDto> searchPost(String keywords);

}
