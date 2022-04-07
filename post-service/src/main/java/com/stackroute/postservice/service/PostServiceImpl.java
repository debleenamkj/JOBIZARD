package com.stackroute.postservice.service;

import com.stackroute.postservice.Exception.BlogNotFoundException;
import com.stackroute.postservice.Exception.ImageNotFoundException;
import com.stackroute.postservice.Exception.UserAlreadyExistsException;
import com.stackroute.postservice.Exception.UserNotFoundException;
import com.stackroute.postservice.model.PostBlog;
import com.stackroute.postservice.model.PostImage;
import com.stackroute.postservice.model.Review;
import com.stackroute.postservice.model.User;
import com.stackroute.postservice.repository.PostBlogRepository;
import com.stackroute.postservice.repository.PostImageRepository;
import com.stackroute.postservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

@Service
public class PostServiceImpl implements PostService{

    private PostImageRepository postImageRepository;
    private PostBlogRepository postBlogRepository;
    private UserRepository userRepository;

    @Autowired
    public PostServiceImpl(PostImageRepository postImageRepository, PostBlogRepository postBlogRepository, UserRepository userRepository) {
        this.postImageRepository = postImageRepository;
        this.postBlogRepository = postBlogRepository;
        this.userRepository = userRepository;
    }

    @Override
    public User saveUser(MultipartFile file, User user) throws UserAlreadyExistsException {
        try {
            if (userRepository.findById(user.getUserEmailId()).isPresent()) {
                throw new UserAlreadyExistsException();
            }
            byte[] userImage = compressBytes(file.getBytes());
            user.setUserImage(userImage);
        }
        catch (UserAlreadyExistsException e){
            System.out.println(e.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return userRepository.save(user);
    }
    @Override
    public PostImage saveImagePost(MultipartFile post, PostImage postImage,String userEmailId) throws UserNotFoundException {
        byte[] postImg = new byte[0];
        try {
            User user = userRepository.findById(userEmailId).get();
            if(user==null){
                throw new UserNotFoundException();
            }
            postImg = compressBytes(post.getBytes());
            postImage.setUser(user);
            postImage.setPostImage(postImg);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return postImageRepository.save(postImage);
    }

    @Override
    public PostBlog saveBlogPost(PostBlog postBlog,String userEmailId) throws UserNotFoundException {
        try {
            User user = userRepository.findById(userEmailId).get();
            if(user==null){
                throw new UserNotFoundException();
            }
            postBlog.setUser(user);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return postBlogRepository.save(postBlog);
    }

    @Override
    public PostImage updateLikeCountInImagePost(String postId)  {
       PostImage postImage = postImageRepository.findById(postId).get();
       try {
           if(postImage ==null){
               throw new ImageNotFoundException();
           }
           postImage.setLike(postImage.getLike()+1);
           postImageRepository.save(postImage);
       }catch (Exception e){
           e.printStackTrace();
       } catch (ImageNotFoundException e) {
           e.printStackTrace();
       }
        return postImage;
    }
    @Override
    public PostImage updateReviewsInImagePost(String postId, Review review)  {
        PostImage postImage = postImageRepository.findById(postId).get();
        try {
            if(postImage ==null){
                throw new ImageNotFoundException();
            }
            List<Review> reviewList = postImage.getReviews();
            reviewList.add(review);
            postImage.setReviews(reviewList);
            postImageRepository.save(postImage);

        }catch (Exception e){
            e.printStackTrace();
        } catch (ImageNotFoundException e) {
            e.printStackTrace();
        }

        return postImage;
    }

    @Override
    public PostBlog updateLikeCountInBlogPost(String postId)  {
        PostBlog postBlog = postBlogRepository.findById(postId).get();
        try {
            if(postBlog ==null){
                throw new BlogNotFoundException();
            }
            postBlog.setLike(postBlog.getLike()+1);
            postBlogRepository.save(postBlog);
        }catch (Exception e) {
            e.printStackTrace();
        } catch (BlogNotFoundException e) {
            e.printStackTrace();
        }
        return postBlog;
    }
    @Override
    public PostBlog updateReviewsInBlogPost(String postId, Review review)  {
        PostBlog postBlog = postBlogRepository.findById(postId).get();
        try {
            if(postBlog ==null){
                throw new BlogNotFoundException();
            }
            List<Review> reviewList = postBlog.getReviews();
            reviewList.add(review);
            postBlog.setReviews(reviewList);
            postBlogRepository.save(postBlog);

        }catch (Exception | BlogNotFoundException e){
            e.printStackTrace();
        }

        return postBlog;
    }


    @Override
    public List<PostBlog> getAllBlogPost(){
        return postBlogRepository.findAll();
    }

    @Override
    public List<PostImage> getAllImagePost(){
        return postImageRepository.findAll();
    }

    @Override
    public PostBlog findBlog(String postId) {
        try {
            if (postBlogRepository.findById(postId).isEmpty()) {
                throw new BlogNotFoundException();
            }
        }catch (Exception e){
            e.printStackTrace();
        } catch (BlogNotFoundException e) {
            e.printStackTrace();
        }
        return postBlogRepository.findById(postId).get();
    }

    @Override
    public PostImage findPost(String postId) {
        try {
            if (postImageRepository.findById(postId).isEmpty()) {
                throw new ImageNotFoundException();
            }
        }catch (Exception | ImageNotFoundException e){
            e.printStackTrace();
        }
        return postImageRepository.findById(postId).get();
    }


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
