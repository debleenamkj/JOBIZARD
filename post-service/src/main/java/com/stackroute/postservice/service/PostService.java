package com.stackroute.postservice.service;

import com.stackroute.postservice.Exception.BlogNotFoundException;
import com.stackroute.postservice.Exception.ImageNotFoundException;
import com.stackroute.postservice.Exception.UserAlreadyExistsException;
import com.stackroute.postservice.Exception.UserNotFoundException;
import com.stackroute.postservice.model.PostBlog;
import com.stackroute.postservice.model.PostImage;
import com.stackroute.postservice.model.Review;
import com.stackroute.postservice.model.User;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface PostService {


    User saveUser(MultipartFile file, User user) throws UserAlreadyExistsException;

    PostImage saveImagePost(MultipartFile post, PostImage postImage, String userEmailId) throws UserNotFoundException;

    PostBlog saveBlogPost(PostBlog postBlog, String userEmailId) throws UserNotFoundException;

    PostImage updateLikeCountInImagePost(String postId) throws UserNotFoundException;

    PostImage updateReviewsInImagePost(String postId, Review review) throws UserNotFoundException;

    PostBlog updateLikeCountInBlogPost(String postId) throws UserNotFoundException;

    PostBlog updateReviewsInBlogPost(String postId, Review review) throws UserNotFoundException;

    List<PostBlog> getAllBlogPost();

    List<PostImage> getAllImagePost();

    PostBlog findBlog(String postId);

    PostImage findPost(String postId) ;
}
