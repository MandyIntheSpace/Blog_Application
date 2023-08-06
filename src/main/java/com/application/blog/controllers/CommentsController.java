package com.application.blog.controllers;

import com.application.blog.constant.ApiConstant;
import com.application.blog.entities.Post;
import com.application.blog.payloads.ApiResponse;
import com.application.blog.payloads.CommentsDto;
import com.application.blog.services.CommentsService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(ApiConstant.POST_COMMENT_REQUEST_MAPPING)
@AllArgsConstructor
public class CommentsController {
    @Autowired
    private CommentsService commentsService;

    @PostMapping(ApiConstant.POST_URL + ApiConstant.POSTID_URL + ApiConstant.COMMENTS_URL)
    public ResponseEntity<CommentsDto> createComment(@RequestBody CommentsDto commentsDto,
                                                     @PathVariable("postId") Integer postId) {
        CommentsDto commentsDto1 = this.commentsService.createComment(commentsDto, postId);
        return new ResponseEntity<CommentsDto>(commentsDto1, HttpStatus.CREATED);
    }

    @DeleteMapping(ApiConstant.COMMENTS_URL + ApiConstant.COMMENT_ID)
    public ResponseEntity<ApiResponse> deleteComment (@PathVariable("postId") Integer postId) {
        this.commentsService.deleteComment(postId);
        return new ResponseEntity<ApiResponse>(new ApiResponse("The post with the specific id has been deleted", true), HttpStatus.OK);
    }
}