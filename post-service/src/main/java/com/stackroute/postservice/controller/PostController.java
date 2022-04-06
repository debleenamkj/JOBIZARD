package com.stackroute.postservice.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.postservice.Exception.BlogNotFoundException;
import com.stackroute.postservice.Exception.ImageNotFoundException;
import com.stackroute.postservice.Exception.UserAlreadyExistsException;
import com.stackroute.postservice.Exception.UserNotFoundException;
import com.stackroute.postservice.model.PostBlog;
import com.stackroute.postservice.model.PostImage;
import com.stackroute.postservice.model.Review;
import com.stackroute.postservice.model.User;
import com.stackroute.postservice.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1/post")
public class PostController {

    private PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping("/saveUser")
    public ResponseEntity<?> saveUser(@RequestParam("file") MultipartFile file, String user) throws JsonProcessingException, UserAlreadyExistsException {
        User user1 = new ObjectMapper().readValue(user,User.class);
        return new ResponseEntity<>(postService.saveUser(file,user1), HttpStatus.CREATED);
    }

    @PostMapping("/image/{userEmailId}")
    public ResponseEntity<?> savePost(@RequestParam("file")MultipartFile file,@RequestParam("post") String postImage,@PathVariable String useremailId) throws JsonProcessingException, UserNotFoundException {
        PostImage post = new ObjectMapper().readValue(postImage,PostImage.class);
        return new ResponseEntity<>(postService.saveImagePost(file,post,useremailId), HttpStatus.CREATED);
    }

    @PostMapping("/blog/{userEmailId}")
    public ResponseEntity<?> saveBlog(@RequestParam("post") String postBlog,@PathVariable String useremailId) throws JsonProcessingException, UserNotFoundException {
        PostBlog post = new ObjectMapper().readValue(postBlog,PostBlog.class);
        return new ResponseEntity<>(postService.saveBlogPost(post,useremailId), HttpStatus.CREATED);
    }

    @PutMapping("/addLike/post")
    public ResponseEntity<?> addLikeInPost(@RequestBody String postId) throws UserNotFoundException {
        return new ResponseEntity(postService.updateLikeCountInImagePost(postId),HttpStatus.OK);
    }

    @PutMapping("/addLike/blog")
    public ResponseEntity<?> addLikeInBlog(@RequestBody String postId) throws UserNotFoundException {
        return new ResponseEntity(postService.updateLikeCountInBlogPost(postId),HttpStatus.OK);
    }

    @PutMapping("/addReviews/post")
    public ResponseEntity<?> addReviewsInPost(@RequestParam("post") String postId, @RequestParam("review") Review review) throws UserNotFoundException {
        return new ResponseEntity<>(postService.updateReviewsInImagePost(postId,review),HttpStatus.OK);
    }

    @PutMapping("/addReviews/blog")
    public ResponseEntity<?> addReviewsInBlogs(@RequestParam("post") String postId, @RequestParam("review") Review review) throws UserNotFoundException {
        return new ResponseEntity<>(postService.updateReviewsInBlogPost(postId,review),HttpStatus.OK);
    }

    @GetMapping("/getPost/{postId}")
    public ResponseEntity<?> getPost(@PathVariable String postId) throws ImageNotFoundException {
        return new ResponseEntity<>(postService.findPost(postId),HttpStatus.OK);
    }

    @GetMapping("/getBlog/{postId}")
    public ResponseEntity<?> getBlog(@PathVariable String postId) throws BlogNotFoundException, ImageNotFoundException {
        return new ResponseEntity<>(postService.findBlog(postId),HttpStatus.OK);
    }


    @GetMapping("/getAllBlog")
    public ResponseEntity<?> getAllBlog() {
        return new ResponseEntity<>(postService.getAllBlogPost(),HttpStatus.OK);
    }

    @GetMapping("/getAllPost")
    public ResponseEntity<?> getAllPost() {
        return new ResponseEntity<>(postService.getAllImagePost(),HttpStatus.OK);
    }

}
