import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { JobSeeker } from '../model/jobSeeker';
import { User } from '../model/login';
import { RegisterServiceService } from '../service/register-service.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

 constructor(private loginService : RegisterServiceService , private router : Router) { }

  ngOnInit(): void {
  }

  user:User =new User();
 
  userLogin()
  {
    console.log(this.user)
    this.loginService.userLogIn(this.user)
    .subscribe(data=>{
      alert("Successfully User is logged in.")
      this.loginService.isloggedIn=true
      this.router.navigate([""])
     },error=>{
      alert("please check your username and password.")
     this.router.navigate(["/login"])
     this.loginService.isloggedIn=false
     }
     );

    }
  }
