package com.application.blog.controllers;

import com.application.blog.constant.ApiConstant;
import com.application.blog.constant.PageAndSortingConstant;
import com.application.blog.entities.Post;
import com.application.blog.exceptions.ResourceNotFoundException;
import com.application.blog.payloads.ApiResponse;
import com.application.blog.payloads.PostDto;
import com.application.blog.payloads.PostResponse;
import com.application.blog.services.FileService;
import com.application.blog.services.PostService;
import com.application.blog.services.impl.PathUtils;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@RestController
@RequestMapping(ApiConstant.POST_REQUEST_MAPPING)
public class PostController {
    @Autowired
    private PostService postService;
    @Autowired
    private FileService fileService;
    @Autowired
    private PathUtils pathUtils;
    @Autowired
    @Value("${project.image}")
    private String path;

    @PostMapping(ApiConstant.USER_URL + ApiConstant.USERID_URL + ApiConstant.CATEGORY_URL + ApiConstant.CATEGORYID_URL + ApiConstant.POST_URL)
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto,
                                              @PathVariable Integer userId,
                                              @PathVariable Integer categoryId) {
        PostDto postDto1 = this.postService.createPost(postDto, userId, categoryId);
        return new ResponseEntity<>(postDto1, HttpStatus.CREATED);
    }
//    @GetMapping("/user/{userId}/posts")
    @GetMapping(ApiConstant.USER_URL + ApiConstant.USERID_URL + ApiConstant.POST_URL)
    public ResponseEntity<List<PostDto>> getPostByUser(@PathVariable Integer userId) {
        List<PostDto> postDtoList = this.postService.getPostByUser(userId);
        return new ResponseEntity<List<PostDto>>(postDtoList, HttpStatus.OK);
//        return  null;
    }
//    @GetMapping("/category/{categoryId}/posts")
    @GetMapping(ApiConstant.CATEGORY_URL + ApiConstant.CATEGORYID_URL + ApiConstant.POST_URL)
    public ResponseEntity<List<PostDto>> getPostByCategory(@PathVariable Integer categoryId){
        List<PostDto> postDtoList = this.postService.getPostByCategory(categoryId);
        return new ResponseEntity<List<PostDto>>(postDtoList, HttpStatus.OK);
    }

//    @GetMapping("/posts")
    @GetMapping(ApiConstant.POST_URL)
    public ResponseEntity<PostResponse> getAllPost(
            @RequestParam(value = "pageNumber", defaultValue =  PageAndSortingConstant.DEFAULT_PAGE_NUMBER, required = false) Integer pageNumber,
            @RequestParam(value = "pageSize", defaultValue =  PageAndSortingConstant.DEFAULT_PAGE_SIZE, required = false) Integer pageSize,
            @RequestParam(value = "sortBy", defaultValue =  PageAndSortingConstant.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = PageAndSortingConstant.DEFAULT_SORT_DIRECTION, required = false) String sortDir
    ) {
        PostResponse postDtoList = this.postService.getAllPost(pageNumber, pageSize, sortBy, sortDir);
        return new ResponseEntity<PostResponse>(postDtoList, HttpStatus.OK);
    }

//    @DeleteMapping("/delete/{postId}")
    @DeleteMapping(ApiConstant.DELETE_URL + ApiConstant.POSTID_URL)
    public ResponseEntity<Void> deleteUserById(@PathVariable Integer postId) {
        this.postService.deletePost(postId);
        return new ResponseEntity(new ApiResponse("The post having the specific postid have been deleted", true), HttpStatus.OK);
    }

//    @PutMapping("/posts/{postId}")
    @PutMapping(ApiConstant.POST_URL + ApiConstant.POSTID_URL)
    public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto, @PathVariable Integer postId) {
        PostDto postDto1 = this.postService.updatePost(postDto, postId);
        return new ResponseEntity<PostDto>(postDto1, HttpStatus.OK);
    }

//    @GetMapping("/posts/search/{keywords}")
    @GetMapping(ApiConstant.POST_URL + ApiConstant.SEARCH_URL + ApiConstant.KEYWORDS_URL)
    public ResponseEntity<List<PostDto>> searchPost(@PathVariable ("keywords") String keywords){
        List<PostDto> postDtoList = this.postService.searchPost1(keywords);
        return new ResponseEntity<List<PostDto>>(postDtoList, HttpStatus.OK);
    }

    @PostMapping(ApiConstant.POST_URL + ApiConstant.IMAGE_URL + ApiConstant.UPLOAD_URL + ApiConstant.POSTID_URL)
    public ResponseEntity<PostDto> uploadImage(@RequestParam("image")
                                                   MultipartFile multipartFile,
                                               @PathVariable Integer postId) throws IOException {
        String normalizedPath = this.pathUtils.normalizedPath(path);
        String fileName = this.fileService.uploadImage(normalizedPath, multipartFile);
        PostDto postDto1 = this.postService.getPostByID(postId);
        postDto1.setImageName(fileName);
        PostDto updatePost = this.postService.updatePost(postDto1, postId);
        return new ResponseEntity<PostDto>(updatePost, HttpStatus.CREATED);
    }

    @GetMapping(value = ApiConstant.POST_URL + ApiConstant.IMAGE_URL + ApiConstant.IMAGE_NAME, produces = MediaType.IMAGE_JPEG_VALUE)
    public void downloadImage(
            @PathVariable("imageName") String imageName,
            HttpServletResponse httpServletResponse
    ) throws IOException {
        InputStream inputStream = this.fileService.getResource(path, imageName);
        httpServletResponse.setContentType(MediaType.IMAGE_JPEG_VALUE);
        StreamUtils.copy(inputStream, httpServletResponse.getOutputStream());
    }
}
