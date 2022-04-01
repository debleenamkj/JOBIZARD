import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Recruiter } from '../model/recruiter';
import { RegisterServiceService } from '../service/register-service.service';

@Component({
  selector: 'app-recruiter-register',
  templateUrl: './recruiter-register.component.html',
  styleUrls: ['./recruiter-register.component.css']
})
export class RecruiterRegisterComponent implements OnInit {

  constructor(private registerService : RegisterServiceService , private router : Router) { }

  ngOnInit(): void {
  }

  recruiter:Recruiter = new Recruiter();


  recruiterRegister()
  {
    console.log(this.recruiterRegister)
    this.registerService.recruiterRegister(this.recruiter).subscribe(data=>{
      alert("User data added successfully")
      this.router.navigate(["/login"])
    },error=>alert("Sorry User not register"));
  }
}