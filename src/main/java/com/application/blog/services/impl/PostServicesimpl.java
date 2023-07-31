package com.application.blog.services.impl;

import com.application.blog.entities.Category;
import com.application.blog.entities.Post;
import com.application.blog.entities.User;
import com.application.blog.exceptions.ResourceNotFoundException;
import com.application.blog.payloads.CategoryDto;
import com.application.blog.payloads.PostDto;
import com.application.blog.repositories.CategoryRepo;
import com.application.blog.repositories.PostRepo;
import com.application.blog.repositories.UserRepository;
import com.application.blog.services.PostService;
import lombok.AllArgsConstructor;
import org.hibernate.annotations.SecondaryRow;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@Service
public class PostServicesimpl implements PostService {

    private PostRepo postRepo;

    private ModelMapper modelMapper;

    private UserRepository userRepository;

    private CategoryRepo categoryRepo;

    @Override
    public PostDto createPost(PostDto postDto, Integer userId, Integer categoryId) {
        User user = this.userRepository.findById(userId).
                orElseThrow(() ->
                        new ResourceNotFoundException("User", "user id", userId));

        Category category = this.categoryRepo.findById(categoryId).
                orElseThrow(() ->
                        new ResourceNotFoundException("Category", "categoryId", categoryId));

        Post post = this.modelMapper.map(postDto, Post.class);
        post.setImageName("default.png");
        post.setAddedDate(new Date());
        post.setUser(user);
        post.setCategory(category);
        Post post1 = this.postRepo.save(post);
        return this.modelMapper.map(post1, PostDto.class);
    }

    @Override
    public PostDto updatePost(PostDto postDto, Integer postId) {
        return null;
    }

    @Override
    public Void deletePost(Integer postId) {
        return null;
    }

    @Override
    public List<PostDto> getAllPost() {
        return null;
    }

    @Override
    public PostDto getPostByID(Integer postId) {
        return null;
    }

    @Override
    public List<PostDto> getPostByCategory(Integer categoryId) {
        return null;
    }

    @Override
    public List<PostDto> getPostByUser(Integer userId) {
        return null;
    }

    @Override
    public List<PostDto> searchPost(String keywords) {
        return null;
    }
}
