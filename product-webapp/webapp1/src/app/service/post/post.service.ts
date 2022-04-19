import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class PostService {

  constructor(private httpClient:HttpClient) { }

  loginUser='m8@gmail.com';

  sendImagePost(email:string,data:any){
    return  this.httpClient.post('http://localhost:9093/api/v1/post/image/'+email,data);
  }

  sendBlogPost(email:string,data:any){
    return this.httpClient.post('http://localhost:9093/api/v1/post/blog/m1@gmail.com',data);
  }

  addReviewInImage(postId:string,data:any){
    return this.httpClient.put('http://localhost:9093/api/v1/post/addReviews/post/'+postId,data);
  }

  addLikeInImage(postId:string){
    return this.httpClient.put('http://localhost:9093/api/v1/post/addLike/post/'+postId+"/"+this.loginUser,null);
  }

  getAllPost(){
    return this.httpClient.get('http://localhost:9093/api/v1/post/getAllPost')
  }

  addLikeInBlog(postId:string){
    return this.httpClient.put('http://localhost:9093/api/v1/post/addLike/blog/'+postId+"/"+this.loginUser,null);
  }

  addReviewInBlog(postId:string,data:any){
    return this.httpClient.put('http://localhost:9093/api/v1/post/addReviews/blog/'+postId,data)
  }

  getSeeker(emailId:any){
    return this.httpClient.get('http://localhost:8098/api/v1/'+emailId)
  }
}
