import { Component, OnInit } from '@angular/core';
import { AssesmentTestPortalComponent } from '../assesment-test-portal/assesment-test-portal.component';
import { MatDialog } from '@angular/material/dialog';
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

  constructor(public dialog: MatDialog, private service: SkilltestServiceService, private fb: FormBuilder, private service1: PostService) { }


  post = this.fb.group({
    comment: ""
  });



  ngOnInit(): void {
    // let file = new File('src\assets\1.jpg','')

    this.getJobSeeker();

    setTimeout(() => {

      let skill = { name: "", level: "" }
      let count = 0;
      console.log(this.skills)
      this.skills.forEach((element: any) => {
        console.log("element");
        console.log(element);

        if (element.isVerified == false) {
          console.log("not")
          this.notVerifiedSkills.push(element.skillName)
          count++;
        }
        else if (element.isVerified == true) {
          let skill = { name: element.skillName, level: element.level, logo: "" }
          if (skill.name != "") {
            if (skill.level == 'beginner') {
              skill.logo = this.service.getbeginner();

            } else if (skill.level == 'saga') {
              skill.logo = this.service.getsaga();

            } else if (skill.level == 'gladiator') {
              skill.logo = this.service.getgladiator();

            } else if (skill.level == 'ninja') {
              skill.logo = this.service.getninja();

            }
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
      console.log("hreklooooooooo")
      console.log(this.verifiedSkills)
    }, 500);


    this.getPosts()

  }

  jobSeeker: any;
  click: boolean = false;

  onButtonClick() {
    this.click = !this.click;
  }




  comments: any;
  postBlog: any;
  allPost: any[] = [];
  user: any;

  sendReview(comment: any, post: any) {
    const review = { reviewUserName: "Malathi", review: "" }
    review.review = comment;
    const comment1 = { reviewUserName: review.reviewUserName, review: comment };
    if (comment != "") {
      if (post.postImage != null) {
        this.service1.addReviewInImage(post.postId, review).subscribe(data => {
          this.getPosts();
        })
      }
      else if (post.postBlog != null) {
        this.service1.addReviewInBlog(post.postId, review).subscribe(data => {
          this.getPosts();
        })
      }
    }


  }

  testName = ""
  verifiedSkills = new Array();
  notVerifiedSkills = new Array();
  profileProgress: number = 60;
  skills: any;
  // [{name:"java",verified:"false",warrior:"",badge:""},
  //             {name:"spring",verified:"false",warrior:"",badge:""},
  //             {name:"angular",verified:"false",warrior:"",badge:""},
  //             {name:"JavaScript",verified:"true",warrior:"",badge:""},
  //             {name:"HTML",verified:"true",warrior:"",badge:""},
  //             {name:"CSS",verified:"true",warrior:"",badge:""},
  //             // {name:"JavaScript",verified:"true",warrior:"",badge:""},
  //             // {name:"HTML",verified:"true",warrior:"",badge:""},
  //             // {name:"CSS",verified:"true",warrior:"",badge:""}
  //           ]

  openDialog() {
    const dialogRef = this.dialog.open(AssesmentTestPortalComponent);

    dialogRef.afterClosed().subscribe((result: any) => {
      console.log(`Dialog result: ${result}`);
    });
  }

  getTestName(test: any) {
    console.log("test Name is");
    console.log(test);
    this.service.quizName = test;
    console.log(this.service.quizName);


  }

  openDialog1(): void {
    const dialogRef = this.dialog.open(ImagePostDialogComponent, {
      width: '500px',
      data: { name: 'malathi' },
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed');
      this.getJobSeeker();
      // this.animal = result;
    });
  }

  openDialog2(): void {
    const dialogRef = this.dialog.open(BlogPostDialogComponent, {
      width: '500px',
      data: { name: 'malathi' },
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed');
      this.getJobSeeker();
      // this.animal = result;
    });
  }

  openProfile() {
    let div = document.getElementsByClassName("skill") as HTMLCollectionOf<HTMLElement>;
    div[0].style.display = "block"
    // let div1 = document.getElementsByClassName("profile-box") as HTMLCollectionOf<HTMLElement>;
    // div[0].style.display="none"
    console.log(div[0])
  }


  updateLike: { like: any };

  addLike(post: any) {
    let postLike = "like" + post.postId;
    this.updateLike = { like: "like" + post.postId }
    let flag = false;
    let flag1 = false;
    if (post.postImage != null) {
      if (post.postImage.like.likeCount != 0) {
        const like = post.postImage.like.likeCount + 1;
        const likedUserEmails = post.postImage.like.likedUserEmails;
        console.log("likedUserEmail")
        console.log(likedUserEmails);
        for (let index = 0; index <= likedUserEmails.length; index++) {
          console.log("in for");
          console.log(likedUserEmails[index]);
          console.log(this.service1.loginUser);

          if (likedUserEmails[index] == this.service1.loginUser) {
            console.log("userr");
            console.log(likedUserEmails[0]);
            flag = true;
          }
        }

        if (flag == false) {
          for (let index = 0; index < this.allPost[0].length; index++) {
            if (this.allPost[0][index].postId == post.postId) {
              this.allPost[0][index].postImage.like.likeCount = like;
              likedUserEmails.push(this.service1.loginUser);
              this.allPost[0][index].postImage.like.like = likedUserEmails;
              this.allPost[0][index] = post;
            }

          }
          this.service1.addLikeInImage(post.postId).subscribe(data => {
            console.log(data)
          });

        }
      } else if (post.postImage.like.likeCount == 0) {
        console.log("likkkee  00")
        const like = 1;
        for (let index = 0; index < this.allPost[0].length; index++) {
          if (this.allPost[0][index].postId == post.postId) {
            this.allPost[0][index].postImage.like.likeCount = like;
            this.allPost[0][index].postImage.like.like = this.service1.loginUser;
          }

        }
        this.service1.addLikeInImage(post.postId).subscribe(data => {
          console.log(data)
        });
        let div = document.getElementsByClassName("like" + post.postId) as HTMLCollectionOf<HTMLElement>;
        console.log(div[0])
        // console.log(div[0].className)
        // div[0].style.color='#b01782'
        div[0].style.pointerEvents = 'none'
        // div[0].style.backgroundColor='red'
        div[0].ariaDisabled = "true"

      }


    }
    else if (post.postBlog != null) {


      if (post.postBlog.like.likeCount != 0) {
        const like = post.postBlog.like.likeCount + 1;
        const likedUserEmails = post.postBlog.like.likedUserEmails;
        console.log("likedUserEmail")
        console.log(likedUserEmails);
        for (let index = 0; index < likedUserEmails.length; index++) {
          if (likedUserEmails[index] == this.service1.loginUser) {
            console.log("userr");
            console.log(likedUserEmails[0]);
            flag = true;
          }
        }

        if (flag == false) {
          for (let index = 0; index < this.allPost[0].length; index++) {
            if (this.allPost[0][index].postId == post.postId) {
              this.allPost[0][index].postBlog.like.likeCount = like;
              likedUserEmails.push(this.service1.loginUser);
              this.allPost[0][index].postBlog.like.like = likedUserEmails;
              this.allPost[0][index] = post;
            }

          }
          this.service1.addLikeInBlog(post.postId).subscribe(data => {
            console.log(data)
          });
          let div = document.getElementsByClassName("like" + post.postId) as HTMLCollectionOf<HTMLElement>;
          console.log(div[0])
          div[0].ariaDisabled = "true"
          // div[0].style.backgroundColor='red'
          // console.log(div[0].className)
          // div[0].style.color='#b01782'
          div[0].style.pointerEvents = 'none'
          // this.getPosts();
        }
      } else {
        console.log("likkkee  00")
        const like = 1;
        for (let index = 0; index < this.allPost[0].length; index++) {
          if (this.allPost[0][index].postId == post.postId) {
            this.allPost[0][index].postBlog.like.likeCount = like;
            this.allPost[0][index].postBlog.like.like = this.service1.loginUser;
          }

        }
        this.service1.addLikeInBlog(post.postId).subscribe(data => {
          console.log(data)
        });
        let div = document.getElementsByClassName("like" + post.postId) as HTMLCollectionOf<HTMLElement>;
        console.log(div[0])
        // console.log(div[0].className)
        // div[0].style.color='#b01782'
        div[0].style.pointerEvents = 'none'
        // div[0].style.backgroundColor='red'
        div[0].ariaDisabled = "true"

      }
    }

    let div = document.getElementsByClassName("like" + post.postId) as HTMLCollectionOf<HTMLElement>;
    console.log(div[0])
    div[0].ariaDisabled = "true"
    // div[0].style.backgroundColor='red'
    div[0].style.pointerEvents = 'none'

  }

  like: number = 0

  getAllComments(post: any) {
    let div = document.getElementsByClassName('comments' + post.postId) as HTMLCollectionOf<HTMLElement>;
    div[0].style.display = 'block';
  }

  getPosts() {
    this.allPost.length = 0;
    this.service1.getAllPost().subscribe(data => {
      this.allPost.push(data);
      this.allPost[0].reverse();
      console.log("All post length")
      console.log(this.allPost[0].length)
      for (let index = 0; index < this.allPost[0].length; index++) {
        console.log("image");
        if (this.allPost[0][index].postImage != null) {

          const img = 'data:image/jpeg;base64,' + this.allPost[0][index].postImage.postImage;
          this.allPost[0][index].postImage.postImage = img;

          console.log(img);

        }
        else if (this.allPost[0][index].postBlog != null) {
          console.log("hellohii")

        }
      }
      console.log(this.allPost[0]);
    })
  }

  getPost1() {
    console.log("hiii")
    this.allPost.length = 0;
    this.service1.getAllPost().subscribe(data => {
      console.log(this.allPost)
      this.allPost.push(data);
      this.allPost[0].reverse();
      console.log(this.allPost[0].length)
      let index = 0;
      while (index < this.allPost[0].length) {
        console.log("hiii")
        index = index + 1
        console.log(index + 1)
      }
    });
  }

  getJobSeeker() {

    let email = localStorage.getItem('loginId')

    this.service1.getSeeker(email).subscribe(data => {
      console.log(data);
      this.jobSeeker = data;
      const img = 'data:image/jpeg;base64,' + this.jobSeeker.jobSeekerImage;
      this.jobSeeker.jobSeekerImage = img;
      console.log(this.jobSeeker.jobSeekerImage)
      this.skills = this.jobSeeker.additionalDetails.skillSet;
      console.log(this.skills)
    })
  }


}
