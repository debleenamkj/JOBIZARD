import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { ChatroomComponent } from '../chatroom/chatroom.component';
import { EmailRequest } from '../model/email-request';
import { JobSeekerLanding } from '../model/job-seeker-landing';
import {  RecruiterLandingData } from '../model/recruiter-landing-data';
import { SearchService } from '../service/search.service';
import { ChatroomService } from '../service/chatroom.service';
import { PostService } from '../service/post/post.service';
import { RecruiterlandingService } from '../service/recruiterlanding.service';

@Component({
  selector: 'app-recruiterlanding',
  templateUrl: './recruiterlanding.component.html',
  styleUrls: ['./recruiterlanding.component.css']
})
export class RecruiterlandingComponent implements OnInit {

  // recruiterName?: string;
  // recruiterImage?: string;
  // seekerName?: string;
  // seekerImage?: string;
  // skills: any=[];

  
  jobSeeker: Array<JobSeekerLanding>=[];
  recruiterLandingData= new RecruiterLandingData();
  jobSeekerSlice: Array<JobSeekerLanding>=[];
  images:any[]=[];

  // constructor(private recruiterLanding: RecruiterlandingService) { }
  constructor(private recruiterLanding: RecruiterlandingService, private chat: ChatroomService, private router: Router, private service: SearchService, private alert: MatSnackBar,private post:PostService) { }

  ngOnInit(): void {
    this.recruiterLanding.getRecruiterProfile().subscribe((d: RecruiterLandingData)=>{
      this.recruiterLandingData=d;
      localStorage.setItem('companyName',this.recruiterLandingData.companyName)
      console.log(this.recruiterLandingData);
      
    });

    this.recruiterLanding.getAllJobSeekers().subscribe(d=>{
      this.jobSeeker=d;
      this.getImages(this.jobSeeker);
      this.jobSeekerSlice=d.slice(0,8);
      console.log(this.jobSeeker);
    });
  }

  pageChange(event:any){
    let start = event.pageSize*event.pageIndex;
    this.jobSeekerSlice = this.jobSeeker.slice(start,start+8)
  }

  getImages(jobSeeker: JobSeekerLanding[]){
    jobSeeker.forEach(d => {
      d.seekerProfileImage = 'data:image/jpeg;base64,' + d.jobSeekerImage;    
      
    });
  }

  jobseeker(emailId:any){
    this.post.selectedSeekerEmail = emailId;
    this.router.navigate(['/navbar/jobseekerprofile']);
  }

  onClick(recipientEmail:any,recipientName:any){
    this.chat.senderId = this.recruiterLandingData.emailId;
    this.chat.senderName = this.recruiterLandingData.companyName;
    this.chat.recipientId = recipientEmail;
    this.chat.recipientName = recipientName;
    this.router.navigate(['/navbar/chatroom']);
  }

  sendEmail(emailId:any){
    let details = new EmailRequest(emailId,this.recruiterLandingData.companyName)
    let message: string="";
    this.service.sendEmail(details).subscribe({next:d=>{
      console.log(d)
      message=d.message
      this.alert.open(message,'close',{
        duration: 5000
      })
    },
    error:er=>{
      console.log(er)
      message=er.error.text
      this.alert.open(message,'close',{
        duration: 5000
      })
    }})
  }

  filterCards(jobseekers:any):boolean{
    if(jobseekers.additionalDetails.skillSet && jobseekers.seekerProfileImage && jobseekers.firstName && jobseekers.lastName && jobseekers.additionalDetails.academicsCertification.length!=0){
      return true;
    }
    return false;
  }
}
