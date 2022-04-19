import { Component, OnInit } from '@angular/core';
import { JobSeeker } from '../model/jobSeeker';
import { PostService } from '../service/post/post.service';

@Component({
  selector: 'app-job-seeker-profile',
  templateUrl: './job-seeker-profile.component.html',
  styleUrls: ['./job-seeker-profile.component.css']
})
export class JobSeekerProfileComponent implements OnInit {

  constructor(private post:PostService) {
   }

  jobseeker = new JobSeeker();
   url = "../assets/u1.jpeg";

  firstname = "";
  lastname="";
  // email="jobseeker@gmail.com";

  email = this.post.selectedSeekerEmail;
  mobile="";
  data:any;
  dob="";
  address={city:"",state:""};
  gender="";
  educational=[{education:"B.Tech",Courses:["DBMS","OS","Networking"],Stream:"Computer Science and Engineering",CourseType:"Graduation",PassYear:"2019",Percentage:"85%"},{education:"Higher Secondary",Stream:"Science",CourseType:"Higher Secondary",PassYear:"2015",Percentage:"75%"},{education:"Senior Secondary",CourseType:"Senior Secondary",PassYear:"2013",Percentage:"90%"}];
  jobpreference=["Software Tester","Software Developer","Quality Analyst","Web Developer","Database Administrator"];

  workexp=[{year:"5",position:"Full Stack Software Developer",organisation:"Stackroute, NIIT"},];
  
  objective="To secure a challenging position in a reputable organization to expand my learnings, knowledge, and skills.";

  skills=["Core Java","Angular","Springboot","Bootstrap","Javascript","Networking","Content Writing","MySQL","Mongo Database","Neo4J"];

  achievements=["Won 2nd Price in Hackathon","Lead TechMantra Successfully","Won 1st Prize for Robotics in IIT","1st Prize in Content Writing"];

  ngOnInit(): void {

    this.post.getSeeker(this.email).subscribe((data)=>{
    console.log(data);
      this.data = data.valueOf();      
      this.mobile = this.data.mobileNumber;
      this.firstname = this.data.firstName;
      this.lastname = this.data.lastName;
      this.gender = this.data.gender;
      this.dob = this.data.dateOfBirth;
      this.address.city = this.data.address.city;
      this.address.state = this.data.address.state;
     } )
  }

}
