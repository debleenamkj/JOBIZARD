import { NONE_TYPE } from '@angular/compiler';
import { Component, OnInit } from '@angular/core';
import { JobSeekerLanding } from '../model/job-seeker-landing';
import { JobSeeker } from '../model/jobSeeker';
import { Recruiter } from '../model/recruiter';
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

  // skillSet: Array<String>=[];
  recruiter: Recruiter=new Recruiter;
  jobSeeker: JobSeekerLanding=new JobSeekerLanding;
  getAllJobSeekersArray: Array<Object>=[];
  recruiterLandingData: RecruiterLandingData=new RecruiterLandingData;
  

  constructor(private recruiterLanding: RecruiterlandingService) { }

  ngOnInit(): void {
    this.recruiterLanding.getRecruiterProfile().subscribe((d: RecruiterLandingData)=>{
      this.recruiterLandingData=d;
    });

    
    this.recruiterLanding.getAllJobSeekers().subscribe((d: JobSeekerLanding)=>{
      this.jobSeeker=d;
    });

    this.recruiterLanding.getSkillSet().subscribe(d=>{
      this.jobSeeker.skillSet=d;
    });
  }

  
}
