import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { JobSeeker } from '../model/jobSeeker';
import { RegisterServiceService } from '../service/register-service.service';

@Component({
  selector: 'app-job-seeker-register',
  templateUrl: './job-seeker-register.component.html',
  styleUrls: ['./job-seeker-register.component.css']
})
export class JobSeekerRegisterComponent implements OnInit {

  constructor(private registerService : RegisterServiceService , private router : Router) { }

  ngOnInit(): void {
  }



  
  jobSeeker:JobSeeker = new JobSeeker();

  uploadImage:any;
  onImageUpload(event: any)
  {
    console.log("onchange");
    const files = event.target.files[0];
    const reader = new FileReader();
    reader.readAsDataURL(event.target.files[0]); 
    reader.onload = (_event) =>
    { 
      console.log(reader.result);
      this.uploadImage = reader.result; 
      console.log(this.uploadImage);
    }
}

  jobSeekerRegister()
  {
    console.log(this.jobSeeker)
    this.registerService.jobSeekerRegister(this.jobSeeker).subscribe(data=>{
      alert("JobSeeker data added successfully")
      this.router.navigate(["/userLogin"])
    },error=>alert("Sorry not able to register jobSeeker Details."));
  }

}
