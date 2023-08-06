package com.application.blog.services;

import com.application.blog.payloads.CommentsDto;
import com.application.blog.payloads.PostDto;

public interface CommentsService {
    CommentsDto createComment(CommentsDto commentsDto, Integer postId);
    void deleteComment(Integer comId);
}
