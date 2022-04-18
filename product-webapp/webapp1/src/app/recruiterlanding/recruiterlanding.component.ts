import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { JobSeekerLanding } from '../model/job-seeker-landing';
import {  RecruiterLandingData } from '../model/recruiter-landing-data';
import { RecruiterlandingService } from './recruiterlanding.service';

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
  recruiterLandingData:RecruiterLandingData;
  jobSeekerSlice: Array<JobSeekerLanding>=[];
  images:any[]=[];

  constructor(private recruiterLanding: RecruiterlandingService) { }

  ngOnInit(): void {
    this.recruiterLanding.getRecruiterProfile().subscribe((d: RecruiterLandingData)=>{
      this.recruiterLandingData=d;
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


}
