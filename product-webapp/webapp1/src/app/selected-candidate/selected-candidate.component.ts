import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { JobSeekerLanding } from '../model/job-seeker-landing';
import { ChatroomService } from '../service/chatroom.service';
import { RecruiterlandingService } from '../service/recruiterlanding.service';

@Component({
  selector: 'app-selected-candidate',
  templateUrl: './selected-candidate.component.html',
  styleUrls: ['./selected-candidate.component.css']
})
export class SelectedCandidateComponent implements OnInit {

  constructor(private recruiterService:RecruiterlandingService, private router:Router,private chat: ChatroomService) {
    // localStorage.setItem('loginId','s4@gmail.com')
   }

  ngOnInit(): void {
    this.getSelectedJobseekers();
  }


  selectedJobseekers:any[]=[]
  selectedJobseekersSlice:any[]=[]
  recruiterLandingData:any;
  
  pageChange(event:any){
    let start = event.pageSize*event.pageIndex;
    this.selectedJobseekersSlice = this.selectedJobseekersSlice.slice(start,start+event.pageSize)
  }
  // jobseeker(emailId: any) {
  //   this.post.selectedSeekerEmail = emailId;
  //   this.router.navigate(['/navbar/jobseekerprofile']);
  // }
  getImages(jobSeeker:JobSeekerLanding[]) {
    jobSeeker.forEach((d:any) => {
      d.seekerProfileImage = 'data:image/jpeg;base64,' + d.jobSeekerImage;    
      
    });
  }
  
  getSelectedJobseekers(){
    this.recruiterService.getRecruiterProfile().subscribe({
      next: (response:any)=>{
        if(response){
          this.recruiterLandingData = response;
          console.log(response)
          if(response.selectedJobSeekers){
          this.selectedJobseekers = response.selectedJobSeekers
          this.getImages(this.selectedJobseekers)
          this.selectedJobseekersSlice=this.selectedJobseekers.slice(0,10)
          }else{
            console.log("No Selected Jobseekers")
          }
        }
        else{
          console.log("response didn't receive")
        }
      },
      error: (errorResponse:any)=>{
        console.log(errorResponse.message)
      }
    })
  }
  onClick(recipientEmail: any, recipientName: any) {
    this.chat.senderId = this.recruiterLandingData.emailId;
    this.chat.senderName = this.recruiterLandingData.companyName;
    this.chat.recipientId = recipientEmail;
    this.chat.recipientName = recipientName;
    this.router.navigate(['/navbar/chatroom']);
  }
  

}

// neo4JMatchedJobseeker=[1,2,3,4,5];
//   selected=[3,5,8,9,10];
//   wishlist:number[]=[];
//   Recruiter{
//     List<Selected> selected;
//   }

//   Selected{               
//     String wishListName;
//     List<String> selected;
//   }

