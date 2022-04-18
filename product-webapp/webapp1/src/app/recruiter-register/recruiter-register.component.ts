import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Router, RouterLink } from '@angular/router';
import { Recruiter } from '../model/recruiter';
import { passwordMatchValidator } from '../register-validation/registervalidation';
import { RegisterServiceService } from '../service/register-service.service';

@Component({
  selector: 'app-recruiter-register',
  templateUrl: './recruiter-register.component.html',
  styleUrls: ['./recruiter-register.component.css']
})
export class RecruiterRegisterComponent {
  recruiter:Recruiter = new Recruiter();

  registerForm =new FormGroup({
    //role
    role:new FormControl("RECRUITER"),
    emailId:new FormControl(this.recruiter.emailId,[Validators.required,Validators.pattern('^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+.[a-zA-Z0-9-.]+$')]),
    password: new FormControl('', Validators.compose([
      Validators.minLength(5),
      Validators.required,
      Validators.pattern('^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])[a-zA-Z0-9]+$') //this is for the letters (both uppercase and lowercase) and numbers validation
   ])),

    confirm:new FormControl('',[Validators.required])
  },{
    validators: [passwordMatchValidator('password', 'confirm')]
  }
  );

  
  constructor(private formBuilder: FormBuilder ,private registerService : RegisterServiceService , private router : Router,
    private http:HttpClient )
  {
  }

  ngOnInit(): void { }



  recruiterRegister()
  {

    this.recruiter.emailId = this.registerForm.value.emailId;
    this.recruiter.password = this.registerForm.value.password;
    console.log(this.recruiter)
    this.registerService.recruiterRegister(this.recruiter).subscribe(data=>{
      //alert("JobSeeker data added successfully")
      this.router.navigate(["/userLogin"])
    },error=>alert("Sorry not able to register recruiter Details."));

  }

  hide = true;


  
  get email(){
    return this.registerForm.get('emailId');
  }
  
  get password(){
    return this.registerForm.get('password');
  }
  
  get confirm(){
    return this.registerForm.get('confirm');
  }

  //role
  get role()
  {
    return this.registerForm.get('role');
  }

  user:any={};


  submit(){
    this.recruiter.emailId = this.registerForm.value.emailId;
   
  
    this.recruiter.password = this.registerForm.value.password;
    console.log("password");
    console.log(this.registerForm.value.password);
    console.log(this.recruiter.password);
  
    console.log(this.recruiter)
    console.log("submit method ")
    console.log(this.recruiter);
  
  
    if(!this.confirm?.invalid && !this.password?.invalid && (this.confirm?.value==this.password?.value))
    {
      //role /  role : this.role?.value ,
      this.recruiter={role : this.role?.value , emailId : this.email?.value, password:this.password?.value}
      this.registerService.recruiterRegister(this.recruiter).subscribe(data=>{
        this.user=data;
        console.log(data);
         this.router.navigate(['/userLogin']);
      });
    }
    else{
      console.log("Registration failed");
    }
 

 }

}