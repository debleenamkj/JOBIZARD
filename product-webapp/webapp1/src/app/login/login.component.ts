import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { UserLogin } from '../model/userLogin';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

// import { LoginserviceService } from '../service/loginservice.service';
import { RegisterServiceService } from '../service/register-service.service';
import { TockenInterceptorService } from '../service/tocken-interceptor.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

 constructor(private formBuilder: FormBuilder,private loginService : RegisterServiceService , private router : Router) { }

  ngOnInit(): void {
  }
  loginForm = this.formBuilder.group({
    emailId: [null, Validators.required],
    password:[null, Validators.required]
  });

  hasUnitNumber = false;
    


  user:UserLogin =new UserLogin();
  
  
  userLogin(user:any)
  {
    console.log(this.user)
    this.loginService.userLogIn(this.user)
    .subscribe(()=>{
      alert("Successfully User is logged in.")
      this.loginService.isloggedIn=true
      this.router.navigate(["/jobdetail"])
     },()=>{
      alert("please check your username and password.")
     this.router.navigate(["/userLogin"])
     this.loginService.isloggedIn=false
     }
     );

    }

    // Credentials={
    //   emailId:'',
    //   password:''
    // }

    // onSubmit(){
    //   console.log("form is submitted")
    //   if((this.Credentials.emailId!=''&&this.Credentials.password!='')&&(this.Credentials.emailId!=null&&this.Credentials.password!=null)){
    //     //token generate
    //     this.loginService.generateToken(this.Credentials).subscribe(
    //        (response:any)=>{
    //          //if token generated successfully
    //             console.log(response.token);
    //             this.loginService.loginuser(response.token);
    //             alert("Welcome Back !! ")
    //             window.location.href="/profiles"
    //        },
    //        error=>{
    //              alert("The credentials entered are incorrect .Please try again!!")
    //              window.location.href="/register"
    //              alert("please register if not registered")
    //              console.log(error);
    //        }
    //      )
    //   }else{
    //     alert("Fields are empty. Kindly enter username and password");
    //   }
    // }

}