package com.stackroute.postservice.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.postservice.Exception.BlogNotFoundException;
import com.stackroute.postservice.Exception.ImageNotFoundException;
import com.stackroute.postservice.Exception.UserAlreadyExistsException;
import com.stackroute.postservice.Exception.UserNotFoundException;
import com.stackroute.postservice.model.*;
import com.stackroute.postservice.service.PostService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

//@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1/post")
@Slf4j
public class PostController {

    private PostService postService;

    @Autowired
    public PostController(PostService postService) {
        log.info("Autowiring PostService");
        this.postService = postService;
    }

    @PostMapping("/saveUser")
    public ResponseEntity<?> saveUser( @RequestParam("user") String user) throws JsonProcessingException, UserAlreadyExistsException {
        try{
            log.debug("PostController - saveUser");
            User user1 = new ObjectMapper().readValue(user,User.class);
            return new ResponseEntity<>(postService.saveUser(user1), HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(UserAlreadyExistsException.class, HttpStatus.CREATED);
        }

    }

    @PostMapping("/image/{userEmailId}")
    public ResponseEntity<?> savePost(@RequestParam("file")MultipartFile file,@RequestParam("post") String postImage,@PathVariable String userEmailId) throws JsonProcessingException, UserNotFoundException {
        PostImage post = new ObjectMapper().readValue(postImage,PostImage.class);
        return new ResponseEntity<>(postService.saveImagePost(file,post,userEmailId), HttpStatus.CREATED);
    }

    @PostMapping("/blog/{userEmailId}")
    public ResponseEntity<?> saveBlog(@RequestParam("post") String postBlog,@PathVariable String userEmailId) throws JsonProcessingException, UserNotFoundException {
        PostBlog post = new ObjectMapper().readValue(postBlog,PostBlog.class);
        return new ResponseEntity<>(postService.saveBlogPost(post,userEmailId), HttpStatus.CREATED);
    }

    @PutMapping("/addLike/post/{postId}/{email}")
    public ResponseEntity<?> addLikeInPost(@PathVariable String postId, @PathVariable String email) throws UserNotFoundException {
        return new ResponseEntity(postService.updateLikeCountInImagePost(postId,email),HttpStatus.OK);
    }

    @PutMapping("/addLike/blog/{postId}/{email}")
    public ResponseEntity<?> addLikeInBlog(@PathVariable String postId, @PathVariable String email) throws UserNotFoundException {
        return new ResponseEntity(postService.updateLikeCountInBlogPost(postId,email),HttpStatus.OK);
    }

    @PutMapping("/addReviews/post/{postId}")
    public ResponseEntity<?> addReviewsInPost(@PathVariable String postId, @RequestBody Review review) throws UserNotFoundException {
        return new ResponseEntity<>(postService.updateReviewsInImagePost(postId,review),HttpStatus.OK);
    }

    @PutMapping("/addReviews/blog/{postId}")
    public ResponseEntity<?> addReviewsInBlogs(@PathVariable String postId, @RequestBody Review review) throws UserNotFoundException {
        return new ResponseEntity<>(postService.updateReviewsInBlogPost(postId,review),HttpStatus.OK);
    }

    @GetMapping("/getAllPost")
        public ResponseEntity<?> getPost() throws ImageNotFoundException {
        return new ResponseEntity<>(postService.getAllPost(),HttpStatus.OK);
    }

//    @GetMapping("/getPost/{postId}")
//    public ResponseEntity<?> getPost(@PathVariable String postId) throws ImageNotFoundException {
//        return new ResponseEntity<>(postService.findPost(postId),HttpStatus.OK);
//    }
//
//    @GetMapping("/getBlog/{postId}")
//    public ResponseEntity<?> getBlog(@PathVariable String postId) throws BlogNotFoundException, ImageNotFoundException {
//        return new ResponseEntity<>(postService.findBlog(postId),HttpStatus.OK);
//    }
//
//
//    @GetMapping("/getAllBlog")
//    public ResponseEntity<?> getAllBlog() {
//        return new ResponseEntity<>(postService.getAllBlogPost(),HttpStatus.OK);
//    }
//
//    @GetMapping("/getAllPost")
//    public ResponseEntity<?> getAllPost() {
//        return new ResponseEntity<>(postService.getAllImagePost(),HttpStatus.OK);
//    }

}
