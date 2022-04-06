import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { UserLogin } from '../model/userLogin';

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

  user:UserLogin =new UserLogin();
 
  
  userLogin(user:any)
  {
    console.log(this.user)
    this.loginService.userLogIn(this.user)
    .subscribe(data=>{
      alert("Successfully User is logged in.")
      this.loginService.isloggedIn=true
      this.router.navigate(["/jobdetail"])
     },error=>{
      alert("please check your username and password.")
     this.router.navigate(["/userLogin"])
     this.loginService.isloggedIn=false
     }
     );

    }
}