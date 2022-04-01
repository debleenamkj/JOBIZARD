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


  jobSeekerRegister()
  {
    console.log(this.jobSeeker)
    this.registerService.jobSeekerRegister(this.jobSeeker).subscribe(data=>{
      alert("User data added successfully")
      this.router.navigate(["/login"])
    },error=>alert("Sorry User not register"));
  }

}
