import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import {MatDialog, MatDialogRef, MAT_DIALOG_DATA} from '@angular/material/dialog';
import { Router } from '@angular/router';
import { JobSeeker } from '../model/jobSeeker';
import { passwordMatchValidator } from '../register-validation/registervalidation';
import { RegisterServiceService } from '../service/register-service.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  jobSeeker:JobSeeker = new JobSeeker();

  registerForm =new FormGroup({
    
    //role
    role:new FormControl("JOBSEEKER"),
    emailId:new FormControl(this.jobSeeker.emailId,[Validators.required,Validators.pattern('^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+.[a-zA-Z0-9-.]+$')]),
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
    private http:HttpClient , private matDialog :MatDialog) { }

  ngOnInit(): void {
  }
  
  openRegisterForm(): void
  {
    
    const dialogRef = this.matDialog.open(RegisterComponent)
    dialogRef.afterClosed().subscribe(result => {
      console.log('Dialog Box: $(result)')
    })
  }

  
  jobSeekerRegister()
  {

    this.jobSeeker.emailId = this.registerForm.value.emailId;
    this.jobSeeker.password = this.registerForm.value.password;
    // this.jobSeeker.role = this.registerForm.value.role;
    console.log(this.jobSeeker)
    this.registerService.jobSeekerRegister(this.jobSeeker).subscribe(data=>{
      //alert("JobSeeker data added successfully")

      // this.registerService.role = this.registerForm.get('role').value;

      // localStorage.setItem('role',this.user.role)

      this.router.navigate(["/userLogin"])
    },error=>alert("Sorry not able to register jobSeeker Details."));

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
  user:any={};
  
  //role
  get role()
  {
     return this.registerForm.get('role');
  }

  submit(){
    this.jobSeeker.emailId = this.registerForm.value.emailId;
   
  
    this.jobSeeker.password = this.registerForm.value.password;
    console.log("password");
    console.log(this.registerForm.value.password);
    console.log(this.jobSeeker.password);
    // this.jobSeeker.role = this.registerForm.value.role;
    console.log(this.jobSeeker)
    console.log("submit method ")
    console.log(this.jobSeeker);
  
  
    if(!this.confirm?.invalid && !this.password?.invalid && (this.confirm?.value==this.password?.value) )
    {
      //role /  role : this.role?.value , 
      this.jobSeeker={role : this.role?.value ,emailId : this.email?.value,password:this.password?.value}
      this.registerService.jobSeekerRegister(this.jobSeeker).subscribe(data=>
        {
          this.user=data;
          console.log(data);
          this.router.navigate(['/userLogin']);
        });
    }
    else
    {
      console.log("Registration failed");
    }
   
  }
  
}
