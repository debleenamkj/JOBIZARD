import { Component, OnInit } from '@angular/core';
import { JobSeeker } from '../model/jobSeeker';

@Component({
  selector: 'app-job-seeker-profile',
  templateUrl: './job-seeker-profile.component.html',
  styleUrls: ['./job-seeker-profile.component.css']
})
export class JobSeekerProfileComponent implements OnInit {

  constructor() {
    this.jobseeker.jobSeekerImage = this.url;
    this.jobseeker.dateOfBirth = this.dob;
    this.jobseeker.emailId = this.email;
    this.jobseeker.firstName = this.firstname;
    this.jobseeker.lastName = this.lastname;
    this.jobseeker.gender = this.gender;
    this.jobseeker.mobileNumber = this.mobile;
   }

  jobseeker = new JobSeeker();
   url = "../assets/u1.jpeg";

  firstname = "Jobsie";
  lastname="Jobbie";
  email="jobseeker@gmail.com";
  mobile=9876543210;
  dob="01-01-1990"
  address={street:"jobizrd",city:"Kolkata",state:"West Bengal",pincode:"123456",nationality:"Indian"};
  gender="Female";
  educational=[{education:"B.Tech",Courses:["DBMS","OS","Networking"],Stream:"Computer Science and Engineering",CourseType:"Graduation",PassYear:"2019",Percentage:"85%"},{education:"Higher Secondary",Stream:"Science",CourseType:"Higher Secondary",PassYear:"2015",Percentage:"75%"},{education:"Senior Secondary",CourseType:"Senior Secondary",PassYear:"2013",Percentage:"90%"}];
  jobpreference=["Software Tester","Software Developer","Quality Analyst","Web Developer","Database Administrator"];

  workexp=[{year:"5",position:"Full Stack Software Developer",organisation:"Stackroute, NIIT"},];
  
  objective="To secure a challenging position in a reputable organization to expand my learnings, knowledge, and skills.";

  skills=["Core Java","Angular","Springboot","Bootstrap","Javascript","Networking","Content Writing","MySQL","Mongo Database","Neo4J"];

  achievements=["Won 2nd Price in Hackathon","Lead TechMantra Successfully","Won 1st Prize for Robotics in IIT","1st Prize in Content Writing"];

  ngOnInit(): void {
  }

}
