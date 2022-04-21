import { F, T } from '@angular/cdk/keycodes';
import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { JobSeeker } from '../model/jobSeeker';
import { passwordMatchValidator } from '../register-validation/registervalidation';
import { RegisterServiceService } from '../service/register-service.service';


@Component({
  selector: 'app-job-seekers-register',
  templateUrl: './job-seekers-register.component.html',
  styleUrls: ['./job-seekers-register.component.css']
})
export class JobSeekersRegisterComponent {

  jobSeeker: JobSeeker = new JobSeeker();

  registerForm = new FormGroup({

    //role
    role: new FormControl("JOBSEEKER"),
    emailId: new FormControl(this.jobSeeker.emailId, [Validators.required, Validators.pattern('^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+.[a-zA-Z0-9-.]+$')]),
    password: new FormControl('', Validators.compose([
      Validators.minLength(5),
      Validators.required,
      Validators.pattern('^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])[a-zA-Z0-9]+$') //this is for the letters (both uppercase and lowercase) and numbers validation
    ])),

    confirm: new FormControl('', [Validators.required])
  }, {
    validators: [passwordMatchValidator('password', 'confirm')]
  }

  );



  constructor(private formBuilder: FormBuilder, private registerService: RegisterServiceService, private router: Router,
    private http: HttpClient) {
  }

  ngOnInit(): void { }



  // jobSeekerRegister()
  // {

  //   this.jobSeeker.emailId = this.registerForm.value.emailId;
  //   this.jobSeeker.password = this.registerForm.value.password;
  //   // this.jobSeeker.role = this.registerForm.value.role;
  //   console.log(this.jobSeeker)
  //   this.registerService.jobSeekerRegister(this.jobSeeker).subscribe(data=>{
  //     //alert("JobSeeker data added successfully")

  //     // this.registerService.role = this.registerForm.get('role').value;

  //     // localStorage.setItem('role',this.user.role)

  //     this.router.navigate(["/userLogin"])
  //   },error=>alert("Sorry not able to register jobSeeker Details."));

  // }

  hide = true;



  get email() {
    return this.registerForm.get('emailId');
  }

  get password() {
    return this.registerForm.get('password');
  }

  get confirm() {
    return this.registerForm.get('confirm');
  }
  user: any = {};

  //role
  get role() {
    return this.registerForm.get('role');
  }

  submit() {
    this.jobSeeker.emailId = this.registerForm.value.emailId;


    this.jobSeeker.password = this.registerForm.value.password;
    console.log("password");
    console.log(this.registerForm.value.password);
    console.log(this.jobSeeker.password);
    // this.jobSeeker.role = this.registerForm.value.role;
    console.log(this.jobSeeker)
    console.log("submit method ")
    console.log(this.jobSeeker);


    if (!this.confirm?.invalid && !this.password?.invalid && (this.confirm?.value == this.password?.value)) {
      //role /  role : this.role?.value , 
      this.jobSeeker = { role: this.role?.value, emailId: this.email?.value, password: this.password?.value }
      this.registerService.jobSeekerRegister(this.jobSeeker).subscribe(data => {
        this.user = data;
        console.log("Jobseeker Registraton", data);
        this.router.navigate(['/userLogin']);
      });
    }
    else {
      console.log("Registration failed");
    }

 }

 validateEmailId()
 {
   if(this.registerForm.controls['emailId'].hasError('required'))
   {
     return "Email-ID is required";
   }
   if(this.registerForm.controls['emailId'].hasError('pattern'))
   {
     return "Must be email type required";
   }
   return "";
 }

 

//  account_validation_messages = {
  
//   'emailId': [
//     { type: 'required', message: 'Email is required' },
//     { type: 'pattern', message: 'Enter a valid email' }
//   ],
//   'confirm': [
//     { type: 'required', message: 'Confirm password is required' },
//     { type: 'areEqual', message: 'Password mismatch' }
//   ],
//   'password': [
//     { type: 'required', message: 'Password is required' },
//     { type: 'minlength', message: 'Password must be at least 5 characters long' },
//     { type: 'pattern', message: 'Your password must contain at least one uppercase, one lowercase, and one number' }
//   ],
//   'terms': [
//     { type: 'pattern', message: 'You must accept terms and conditions' }
//   ]
//   }



}