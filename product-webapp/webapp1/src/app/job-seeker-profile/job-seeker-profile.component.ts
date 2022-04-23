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
   url = "";

  firstname = "";
  lastname="";
  // email="jobseeker@gmail.com";

  email = this.post.selectedSeekerEmail;
  mobile="";
  data:any;
  dob="";
  address={city:"",state:""};
  gender="";
  academicCertification: any=[];
  educational:any;
  
  objective="";

  skills:any;
  experience="";
  achievements: any []=[];

  verifiedSkills = new Array();
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
      this.achievements = this.data.additionalDetails.achievements;
      this.academicCertification = this.data.additionalDetails.academicsCertification;
      this.objective = this.data.objective;
      this.url='data:image/jpeg;base64,' + this.data.jobSeekerImage;
      this.skills = this.data.additionalDetails.skillSet;
      this.educational = this.data.educationDetails;
      this.experience = this.data.experience;
     } )
  }

}
