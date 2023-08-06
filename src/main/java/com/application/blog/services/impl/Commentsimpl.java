package com.application.blog.services.impl;

import com.application.blog.entities.Comments;
import com.application.blog.entities.Post;
import com.application.blog.exceptions.ResourceNotFoundException;
import com.application.blog.payloads.CommentsDto;
import com.application.blog.repositories.CommentsRepo;
import com.application.blog.repositories.PostRepo;
import com.application.blog.services.CommentsService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class Commentsimpl implements CommentsService {
    private CommentsRepo commentsRepo;
    private ModelMapper modelMapper;
    private PostRepo postRepo;
    @Override
    public CommentsDto createComment(CommentsDto commentsDto, Integer postId) {
        Post post = this.postRepo.findById(postId).
                orElseThrow(() -> new ResourceNotFoundException("Post", "post id", postId));
        Comments comments = this.modelMapper.map(commentsDto, Comments.class);
        comments.setPost(post);
        Comments comments1 = this.commentsRepo.save(comments);
        return this.modelMapper.map(comments1, CommentsDto.class);
    }

    @Override
    public void deleteComment(Integer comId) {
        Comments comments = this.commentsRepo.findById(comId).
                orElseThrow(() -> new ResourceNotFoundException("Comments", "comment id", comId));
        this.commentsRepo.delete(comments);
    }
}
