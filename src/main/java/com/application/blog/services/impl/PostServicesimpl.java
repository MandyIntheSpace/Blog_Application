package com.application.blog.services.impl;

import com.application.blog.entities.Category;
import com.application.blog.entities.Post;
import com.application.blog.entities.User;
import com.application.blog.exceptions.ResourceNotFoundException;
import com.application.blog.payloads.CategoryDto;
import com.application.blog.payloads.PostDto;
import com.application.blog.payloads.PostResponse;
import com.application.blog.repositories.CategoryRepo;
import com.application.blog.repositories.PostRepo;
import com.application.blog.repositories.UserRepository;
import com.application.blog.services.PostService;
//import jakarta.validation.constraints.Null;
import lombok.AllArgsConstructor;
//import lombok.NoArgsConstructor;
//import org.hibernate.NonUniqueObjectException;
//import org.hibernate.annotations.SecondaryRow;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.*;
import java.util.stream.Collectors;

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
        Post post = this.postRepo.findById(postId).
                orElseThrow(() -> new ResourceNotFoundException("Post", "post id", postId));
        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        post.setImageName(postDto.getImageName());
        Post updatedPost = this.postRepo.save(post);
        return this.modelMapper.map(updatedPost, PostDto.class);


    }

    @Override
    public void deletePost(Integer postId) {
        Post post = this.postRepo.findById(postId).
                orElseThrow(() -> new ResourceNotFoundException("Post", "post id", postId));
        this.postRepo.delete(post);
    }

    @Override
    public PostResponse getAllPost(Integer pageNumber, Integer pageSize, String sortBy, String sortDir) {

        Sort sort = (
                sortDir.equalsIgnoreCase("asc")?Sort.by(sortBy).ascending()
                        :Sort.by(sortBy).descending()
                );

        PageRequest pageable = PageRequest.of(pageNumber, pageSize, sort);
        Page<Post> postList = this.postRepo.findAll(pageable);
        List<Post> postList1 = postList.getContent();
        List<PostDto> postDtoList = postList1.stream().map(post ->
                this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());

        try {
            PostResponse postResponse = new PostResponse();
            postResponse.setContent(postDtoList);
            postResponse.setPageNumber(postList.getNumber());
            postResponse.setPageSize(postList.getSize());
            postResponse.setTotalElements((int) postList.getTotalElements());
            postResponse.setTotalPages(postList.getTotalPages());
            postResponse.setLastPages(postList.isLast());
            return postResponse;
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    @Override
    public PostDto getPostByID(Integer postId) {
        Post post = this.postRepo.findById(postId).
                orElseThrow(() -> new ResourceNotFoundException("Post", "post id", postId));
        return this.modelMapper.map(post, PostDto.class);
    }

    @Override
    public List<PostDto> getPostByCategory(Integer categoryId) {
        Category cat = this.categoryRepo.findById(categoryId).
                orElseThrow(() -> new ResourceNotFoundException("Category", "category id", categoryId));
        List<Post> postList = this.postRepo.findALlByCategory(cat);
        System.out.println(postList);
        List<PostDto> postDtoList = postList.stream().map(post ->
                this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
        return postDtoList;
    }

    @Override
    public List<PostDto> getPostByUser(Integer userId) {
        User user = this.userRepository.findById(userId).
                orElseThrow(() -> new ResourceNotFoundException("User", "user id", userId));
        List<Post> postList = this.postRepo.findByUser(user);
        System.out.println(postList);
        List<PostDto> postDtoList = postList.stream().map(post1 ->
                this.modelMapper.map(post1, PostDto.class)).collect(Collectors.toList());
        return postDtoList;
    }

    @Override
    public List<PostDto> searchPost1(String keywords) {
        List<Post> postList = this.postRepo.findByTitleContaining(keywords);
        List<PostDto> postDtoList = postList.stream().map((post) -> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
        return postDtoList;
    }
}
