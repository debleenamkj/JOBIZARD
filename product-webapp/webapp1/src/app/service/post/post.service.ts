import { HttpClient } from '@angular/common/http';
// import { CoreEnvironment } from '@angular/compiler/src/compiler_facade_interface';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class PostService {

  constructor(private httpClient:HttpClient) { }

  baseUrl= 'https://jobizard.stackroute.io' + '/post-service';
  baseUrl1= 'https://jobizard.stackroute.io' + '/application-register-service';
  // baseUrl = 'http://localhost:9093';
  // baseUrl1 = 'http://localhost:8098';

  loginUser=localStorage.getItem('loginId');

  selectedSeekerEmail=localStorage.getItem('loginId');

  sendImagePost(email:string,data:any){
    console.log(this.loginUser);
    return  this.httpClient.post(this.baseUrl + '/api/v1/post/image/'+this.loginUser,data);
  }

  sendBlogPost(email:string,data:any){
    return this.httpClient.post(this.baseUrl + '/api/v1/post/blog/'+this.loginUser,data);
  }

  addReviewInImage(postId:string,data:any){
    return this.httpClient.put(this.baseUrl + '/api/v1/post/addReviews/post/'+postId,data);
  }

  addLikeInImage(postId:string){
    return this.httpClient.put(this.baseUrl + '/api/v1/post/addLike/post/'+postId+"/"+this.loginUser,null);
  }

  getAllPost(){
    return this.httpClient.get(this.baseUrl + '/api/v1/post/getAllPost')
  }

  addLikeInBlog(postId:string){
    return this.httpClient.put(this.baseUrl + '/api/v1/post/addLike/blog/'+postId+"/"+this.loginUser,null);
  }

  addReviewInBlog(postId:string,data:any){
    return this.httpClient.put(this.baseUrl + '/api/v1/post/addReviews/blog/'+postId,data)
  }

  getSeeker(emailId:any){
    console.log("seeekkerrr");
    
    return this.httpClient.get(this.baseUrl1 + '/api/v1/'+emailId)
  }
}
