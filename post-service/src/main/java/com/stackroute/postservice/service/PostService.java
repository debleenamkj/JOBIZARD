package com.stackroute.postservice.service;

import com.stackroute.postservice.Exception.BlogNotFoundException;
import com.stackroute.postservice.Exception.ImageNotFoundException;
import com.stackroute.postservice.Exception.UserAlreadyExistsException;
import com.stackroute.postservice.Exception.UserNotFoundException;
import com.stackroute.postservice.model.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface PostService {


    User saveUser(User user) throws UserAlreadyExistsException;

    Post saveImagePost(MultipartFile post, PostImage postImage, String userEmailId) throws UserNotFoundException;

    Post saveBlogPost(PostBlog postBlog, String userEmailId) throws UserNotFoundException;

    Post updateLikeCountInImagePost(String postId,String emailId) throws UserNotFoundException;

    Post updateReviewsInImagePost(String postId, Review review) throws UserNotFoundException;

    Post updateLikeCountInBlogPost(String postId,String likedEmailId) throws UserNotFoundException;

    Post updateReviewsInBlogPost(String postId, Review review) throws UserNotFoundException;

    List<Post> getAllPost();

//    List<PostBlog> getAllBlogPost();
//
//    List<PostImage> getAllImagePost();
//
//    PostBlog findBlog(String postId);
//
//    PostImage findPost(String postId) ;
}
