package com.stackroute.postservice.service;

import com.stackroute.postservice.Exception.ImageNotFoundException;
import com.stackroute.postservice.Exception.UserAlreadyExistsException;
import com.stackroute.postservice.Exception.UserNotFoundException;
import com.stackroute.postservice.model.*;
import com.stackroute.postservice.repository.PostBlogRepository;
import com.stackroute.postservice.repository.PostImageRepository;
import com.stackroute.postservice.repository.PostRepository;
import com.stackroute.postservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

@Service
public class PostServiceImpl implements PostService{

    private PostRepository postRepository;
    private UserRepository userRepository;

    @Autowired
    public PostServiceImpl(PostRepository postRepository, UserRepository userRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }


    @Override
    public User saveUser( User user) throws UserAlreadyExistsException {
        try {
            if (userRepository.findById(user.getUserEmailId()).isPresent()) {
                throw new UserAlreadyExistsException();
            }
//            byte[] userImage = compressBytes(file.getBytes());
//            user.setUserImage(userImage);
        }
        catch (UserAlreadyExistsException e){
            System.out.println(e.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userRepository.save(user);
    }

//    @Override
//    public User saveUser(MultipartFile file, User user) throws UserAlreadyExistsException {
//        try {
//            if (userRepository.findById(user.getUserEmailId()).isPresent()) {
//                throw new UserAlreadyExistsException();
//            }
//            byte[] userImage = compressBytes(file.getBytes());
//            user.setUserImage(userImage);
//        }
//        catch (UserAlreadyExistsException e){
//            System.out.println(e.toString());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return userRepository.save(user);
//    }
    @Override
    public Post saveImagePost(MultipartFile post, PostImage postImage,String userEmailId) throws UserNotFoundException {
        byte[] postImg = new byte[0];
        Post post1 = new Post();
        try {
            User user = userRepository.findById(userEmailId).get();
            if(user==null){
                throw new UserNotFoundException();
            }
            postImg = post.getBytes();
            postImage.setPostImage(postImg);
            Like like = new Like();
            like.setLikeCount(0);
//            like.setLikedUserEmails();
            ArrayList likedEmails = new ArrayList();
            likedEmails.add("");
            like.setLikedUserEmails(likedEmails);
            postImage.setLike(like);
            post1.setPostImage(postImage);
            post1.setUser(user);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return postRepository.save(post1);
    }

    @Override
    public Post saveBlogPost(PostBlog postBlog,String userEmailId) throws UserNotFoundException {
        Post post = new Post();
        try {
            User user = userRepository.findById(userEmailId).get();
            if(user==null){
                throw new UserNotFoundException();
            }

            Like like = new Like();
            like.setLikeCount(0);
//            like.setLikedUserEmails();
            ArrayList likedEmails = new ArrayList();
            likedEmails.add("");
            like.setLikedUserEmails(likedEmails);
            postBlog.setLike(like);

            post.setPostBlog(postBlog);

            post.setUser(user);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return postRepository.save(post);
    }

    @Override
    public Post updateLikeCountInImagePost(String postId,String likedEmailId)  {
        Post post = postRepository.findById(postId).get();
        PostImage postImage = post.getPostImage();
        ArrayList likedEmails = new ArrayList();
//        Array
       try {
           if(post ==null){
               throw new ImageNotFoundException();
           }
           if(postImage==null){
               throw new ImageNotFoundException();
           }
           Like like = new Like();

           if(postImage.getLike().getLikeCount()==0){
               like.setLikeCount(1);
               likedEmails = postImage.getLike().getLikedUserEmails();
               likedEmails.set(0,likedEmailId);
           }else {
               like.setLikeCount(postImage.getLike().getLikeCount()+1);
               likedEmails = postImage.getLike().getLikedUserEmails();
               likedEmails.add(likedEmailId);
           }
           like.setLikedUserEmails(likedEmails);
           System.out.println(like);
           postImage.setLike(like);
           post.setPostImage(postImage);
           postRepository.save(post);
       }catch (Exception e){
           e.printStackTrace();
       } catch (ImageNotFoundException e) {
           e.printStackTrace();
       }
        return post;
    }
    @Override
    public Post updateReviewsInImagePost(String postId, Review review)  {
        Post post = postRepository.findById(postId).get();
        List<Review> reviewList = new ArrayList<>();
        PostImage postImage = post.getPostImage();
        try {
            if(post ==null){
                throw new ImageNotFoundException();
            }
            if(postImage ==null){
                throw new ImageNotFoundException();
            }

            if(post.getPostImage().getReviews()==null){
                reviewList.add(review);
            }
            else {
                reviewList = postImage.getReviews();
                reviewList.add(review);
            }
            postImage.setReviews(reviewList);
            post.setPostImage(postImage);
            postRepository.save(post);

        }catch (Exception e){
            e.printStackTrace();
        } catch (ImageNotFoundException e) {
            e.printStackTrace();
        }

        return post;
    }

    @Override
    public Post updateLikeCountInBlogPost(String postId,String likedEmailId)  {
        Post post = postRepository.findById(postId).get();
        System.out.println(post);
        PostBlog postBlog = post.getPostBlog();
        System.out.println(postBlog);
        ArrayList likedEmails = new ArrayList();
        try {
            if(post ==null){
                throw new ImageNotFoundException();
            }
            if(postBlog==null){
                throw new ImageNotFoundException();
            }
            Like like = new Like();
            if(postBlog.getLike().getLikeCount()==0){
                like.setLikeCount(1);
                likedEmails = postBlog.getLike().getLikedUserEmails();
                likedEmails.set(0,likedEmailId);
            }else {
                like.setLikeCount(postBlog.getLike().getLikeCount()+1);
                likedEmails = postBlog.getLike().getLikedUserEmails();
                likedEmails.add(likedEmailId);
            }
            like.setLikedUserEmails(likedEmails);
            System.out.println(like);
            postBlog.setLike(like);
            post.setPostBlog((postBlog));
            postRepository.save(post);
        }catch (Exception e){
            e.printStackTrace();
        } catch (ImageNotFoundException e) {
            e.printStackTrace();
        }
        return post;
    }
    @Override
    public Post updateReviewsInBlogPost(String postId, Review review)  {
        Post post = postRepository.findById(postId).get();
        List<Review> reviewList = new ArrayList<>();
        PostBlog postBlog = post.getPostBlog();
        try {
            if(post ==null){
                throw new ImageNotFoundException();
            }
            if(postBlog ==null){
                throw new ImageNotFoundException();
            }
            if(post.getPostBlog().getReviews()==null){
                reviewList.add(review);
            }
            else {
                reviewList = postBlog.getReviews();
                reviewList.add(review);
            }
            postBlog.setReviews(reviewList);
            post.setPostBlog(postBlog);
            postRepository.save(post);

        }catch (Exception e){
            e.printStackTrace();
        } catch (ImageNotFoundException e) {
            e.printStackTrace();
        }

        return post;
    }

    @Override
    public List<Post> getAllPost(){
        return postRepository.findAll();
    }

//    @Override
//    public List<PostBlog> getAllBlogPost(){
//        return postBlogRepository.findAll();
//    }
//
//    @Override
//    public List<PostImage> getAllImagePost(){
//        return postImageRepository.findAll();
//    }
//
//    @Override
//    public PostBlog findBlog(String postId) {
//        try {
//            if (postBlogRepository.findById(postId).isEmpty()) {
//                throw new BlogNotFoundException();
//            }
//        }catch (Exception e){
//            e.printStackTrace();
//        } catch (BlogNotFoundException e) {
//            e.printStackTrace();
//        }
//        return postBlogRepository.findById(postId).get();
//    }
//
//    @Override
//    public PostImage findPost(String postId) {
//        try {
//            if (postImageRepository.findById(postId).isEmpty()) {
//                throw new ImageNotFoundException();
//            }
//        }catch (Exception | ImageNotFoundException e){
//            e.printStackTrace();
//        }
//        return postImageRepository.findById(postId).get();
//    }



    public static byte[] compressBytes(byte[] data) {
        Deflater deflater = new Deflater();
        deflater.setInput(data);
        deflater.finish();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] buffer = new byte[1024];
        while (!deflater.finished()) {
            int count = deflater.deflate(buffer);
            outputStream.write(buffer, 0, count);
        }
        try {
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Compressed Image Byte Size - " + outputStream.toByteArray().length);
        return outputStream.toByteArray();
    }

    public static byte[] decompressBytes(byte[] data) {
        Inflater inflater = new Inflater();
        inflater.setInput(data);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] buffer = new byte[1024];
        try {
            while (!inflater.finished()) {
                int count = inflater.inflate(buffer);
                outputStream.write(buffer, 0, count);
            }
            outputStream.close();
        }  catch (Exception e) {
            e.printStackTrace();
        }
        return outputStream.toByteArray();
    }


}
