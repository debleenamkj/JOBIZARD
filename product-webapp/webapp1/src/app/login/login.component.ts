import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { UserLogin } from '../model/userLogin';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

// import { LoginserviceService } from '../service/loginservice.service';
import { RegisterServiceService } from '../service/register-service.service';
import { TockenInterceptorService } from '../service/tocken-interceptor.service';
import { RecruiterlandingService } from '../recruiterlanding/recruiterlanding.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

 constructor(private formBuilder: FormBuilder,private loginService : RegisterServiceService , private router : Router, private recruiterLanding: RecruiterlandingService) { }

  ngOnInit(): void {
  }
  loginForm = this.formBuilder.group({
    role:[null,Validators.required],
    emailId: [null, Validators.required],
    password:[null, Validators.required]
    // role:[this.loginService.role]
  });

  hasUnitNumber = false;
  error: string | null = '';

  hide=true;
  user:UserLogin =new UserLogin();
  user1:UserLogin = new UserLogin();
  
  
  userLogin()
  {
    this.user.emailId=this.loginForm.value.emailId;
    this.user.password=this.loginForm.value.password;
    // console.log(this.loginService.role)
    console.log(this.user);
    
    this.loginService.userLogIn(this.user).subscribe(()=>{
      // alert("Successfully User is logged in.")
      localStorage.setItem('loginId',this.user.emailId)  // store in local storage
      this.loginService.isloggedIn=true
      this.loginService.getUserById(this.user.emailId).subscribe((response:any)=>
      {
         this.user1=response;
        console.log(this.user1);
        if(this.user1.role=="JOBSEEKER"){
          this.router.navigate(["/jobSeeker"])
        }
        else{
          localStorage.setItem('role',"RECRUITER");
          this.router.navigate(["/recruiterLanding"])
        }
        // store in local storage
        localStorage.setItem('loginId',this.user.emailId)
        localStorage.setItem('role',this.user1.role);
      })
      
     },()=>{
      //alert("please check your username and password.")
    this.error =  "please check your Email-Id or password.";
    
      
    console.log('loginId')
    
    console.log(localStorage.getItem('loginId'))
    this.router.navigate(["/userLogin"])
    this.loginService.isloggedIn=false
     }
     );



    }

}