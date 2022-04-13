import { Component, OnInit } from '@angular/core';
import { AssesmentTestPortalComponent } from '../assesment-test-portal/assesment-test-portal.component';
import {MatDialog} from '@angular/material/dialog';
import { SkilltestServiceService } from '../service/skilltest-service.service';
import { ImagePostDialogComponent } from '../image-post-dialog/image-post-dialog.component';
import { BlogPostDialogComponent } from '../blog-post-dialog/blog-post-dialog.component';
import { FormBuilder, FormControl, FormGroup } from '@angular/forms';
import { PostService } from '../service/post/post.service';
import { D } from '@angular/cdk/keycodes';
@Component({
  selector: 'app-job-seeker-landing',
  templateUrl: './job-seeker-landing.component.html',
  styleUrls: ['./job-seeker-landing.component.css']
})
export class JobSeekerLandingComponent implements OnInit {

  constructor(public dialog:MatDialog,private service:SkilltestServiceService,private fb:FormBuilder,private service1:PostService) { }


  post = this.fb.group({
    comment:""
   });

  

  ngOnInit(): void {
    // let file = new File('src\assets\1.jpg','')

    let skill = {name:"",warrior:"",badge:""}
    let count =0;
    this.skills.forEach((element: any) => {
      if(element.verified=='false'){
        this.notVerifiedSkills.push(element.name)
        count++;
      }
      else if(element.verified=='true'){
        let skill = {name:element.name,warrior:element.warrior,badge:element.badge}
        if(skill.name!=""){
          // console.log(skill)
          this.verifiedSkills.push(skill);
          count++;
        }
        // skill.name=element.name;
        // skill.badge=element.badge;
        // skill.warrior=element.warrior;
      // this.notVerifiedSkills.length=0
      }
    });
    
    this.getPosts()
    
  }
  comments:any;
  postBlog:any;
  allPost:any[]=[];
  user:any;

  sendReview(comment:any,post:any){
   const review = {reviewUserName:"Malathi",review:""}
   review.review=comment;
   const comment1 = {reviewUserName:review.reviewUserName,review:comment};
   if(comment!=""){
    if(post.postImage!=null){
      this.service1.addReviewInImage(post.postId,review).subscribe(data =>{
        this.getPosts();
      })
     }
     else if(post.postBlog!=null){
      this.service1.addReviewInBlog(post.postId,review).subscribe(data =>{
        this.getPosts(); 
      })
     }
   }

  
  }

  testName=""
  verifiedSkills = new Array();
  notVerifiedSkills= new Array();
  profileProgress:number=60;
  skills:any=[{name:"java",verified:"false",warrior:"",badge:""},
              {name:"spring",verified:"false",warrior:"",badge:""},
              {name:"angular",verified:"false",warrior:"",badge:""},
              {name:"JavaScript",verified:"true",warrior:"",badge:""},
              {name:"HTML",verified:"true",warrior:"",badge:""},
              {name:"CSS",verified:"true",warrior:"",badge:""},
              // {name:"JavaScript",verified:"true",warrior:"",badge:""},
              // {name:"HTML",verified:"true",warrior:"",badge:""},
              // {name:"CSS",verified:"true",warrior:"",badge:""}
            ]

  openDialog() {
    const dialogRef = this.dialog.open(AssesmentTestPortalComponent);

    dialogRef.afterClosed().subscribe((result: any) => {
      console.log(`Dialog result: ${result}`);
    });
  }

  getTestName(test:any){
    // console.log(test)
    console.log("test Name is"+test);
    // this.service.getTest(test);
    this.service.quizName=test;

  }

  openDialog1(): void {
    const dialogRef = this.dialog.open(ImagePostDialogComponent, {
      width: '30%',
      data: {name: 'malathi'},
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed');
      // this.animal = result;
    });
  }

  openDialog2(): void {
    const dialogRef = this.dialog.open(BlogPostDialogComponent, {
      width: '30%',
      data: {name: 'malathi'},
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed');
      // this.animal = result;
    });
  }

  addLike(post:any){
    if(post.postImage!=null){
      const like=post.postImage.like.likeCount+1;
      for (let index = 0; index < this.allPost[0].length; index++) {
        if(this.allPost[0][index].postId==post.postId){
          this.allPost[0][index].postImage.like.likeCount=like;
        }
        
      }
      this.service1.addLikeInImage(post.postId).subscribe(data => {
        let div = document.getElementsByClassName('icon'+post.postId) as HTMLCollectionOf<HTMLElement>;
        console.log(div[0].className)
        div[0].style.color='#b01782'
        div[0].style.pointerEvents='none'
        console.log(data)
      });
    }
    else if(post.postBlog!=null){
      const like=post.postBlog.like.likeCount+1
      for (let index = 0; index < this.allPost[0].length; index++) {
        // const element = array[index];
        if(this.allPost[0][index].postId==post.postId){
          this.allPost[0][index].postBlog.like.likeCount=like;
        }
      }
      this.service1.addLikeInBlog(post.postId).subscribe(data => {
        let div = document.getElementsByClassName('icon'+post.postId) as HTMLCollectionOf<HTMLElement>;
        console.log(div[0].className)
        div[0].style.color='#b01782'
        div[0].style.pointerEvents='none'
        console.log(data)
      });
    }
    
  }

  like:number=0

  getAllComments(post:any){
    let div = document.getElementsByClassName('comments'+post.postId) as HTMLCollectionOf<HTMLElement>;
    div[0].style.display='block';
  }

  getPosts(){
    this.allPost.length=0;
    this.service1.getAllPost().subscribe(data =>{
      this.allPost.push(data);
      this.allPost[0].reverse();
      console.log(this.allPost)
     for (let index = 0; index < this.allPost[0].length; index++) {

       if(this.allPost[0][index].postImage!=null){
        console.log("imaagee");
        const img = 'data:image/jpeg;base64,'+this.allPost[0][index].postImage.postImage;
        this.allPost[0][index].postImage.postImage=img;
        
        console.log(img);
        if(this.allPost[0][index].postImage.like!=null){  
          const likedUserEmails = this.allPost[0][index].postImage.like.likedUserEmails;
          for (let index = 0; index < likedUserEmails.length; index++) {
            if(likedUserEmails[0]==this.service1.loginUser){
              console.log("matched");

              const postId = this.allPost[0][index].postId;
              const post1 = "icon"+postId
              console.log(post1)
              let div = document.getElementsByClassName(post1) as HTMLCollectionOf<HTMLElement>;
              console.log(post1)
              div[0].style.color='red'
              div[0].style.pointerEvents='none'
            }
            
          }
        }
     
        // console.log(this.allPost[0][index].postImage.postImage)
       }
      else if(this.allPost[0][index].postBlog!=null){
         console.log("hellohii")
        //  console.log(this.allPost[0][index].postBlog.postBlog)
        //  this.postBlog=this.allPost[0][index].postBlog.postBlog.join();
        //  console.log(this.postBlog);
       }
     }
    //  +this.allPost.postImage.postImage;
      // console.log("img");
      // console.log(img);
      console.log(this.allPost[0]);
    })
  }

  getPost1(){
    console.log("hiii")
    this.allPost.length=0;
    this.service1.getAllPost().subscribe(data =>{
      console.log(this.allPost)
      this.allPost.push(data);
      this.allPost[0].reverse();
      console.log(this.allPost[0].length)
     let index =0;
     while(index<this.allPost[0].length){
      console.log("hiii")
      index=index+1
      console.log(index+1)
     }
  });
}


  }
