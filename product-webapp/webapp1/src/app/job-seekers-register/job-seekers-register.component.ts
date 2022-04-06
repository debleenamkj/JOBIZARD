import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { JobSeeker } from '../model/jobSeeker';
import { RegisterServiceService } from '../service/register-service.service';


@Component({
  selector: 'app-job-seekers-register',
  templateUrl: './job-seekers-register.component.html',
  styleUrls: ['./job-seekers-register.component.css']
})
export class JobSeekersRegisterComponent {

  jobSeeker:JobSeeker = new JobSeeker();

  registerForm = this.formBuilder.group({
    emailId: ["", Validators.required],
    firstName: ["", Validators.required],
    lastName: ["", Validators.required],
    dateOfBirth:["", Validators.required],
    mobileNumber:[, Validators.required],
    gender: ["", Validators.required],
    password:["", Validators.required],
    // userImage:[],


 // this.jobbseeker.(attribute name)=this.(formgroup name).value.(formgroup control name)
    // country:[null, Validators.required],
    // state: [null, Validators.required],



    // city: [null, Validators.required],
    // postalCode: [null, Validators.compose([
    //   Validators.required, Validators.minLength(6), Validators.maxLength(6)])
    // ],
  });


  constructor(private formBuilder: FormBuilder ,private registerService : RegisterServiceService , private router : Router,
    private http:HttpClient )
  {
  }

  ngOnInit(): void { }



  jobSeekerRegister()
  {

    this.jobSeeker.emailId = this.registerForm.value.emailId;
    this.jobSeeker.firstName = this.registerForm.value.firstName;
    this.jobSeeker.lastName = this.registerForm.value.lastName;
    this.jobSeeker.dateOfBirth = this.registerForm.value.dateOfBirth;
    this.jobSeeker.mobileNumber = this.registerForm.value.mobileNumber;
    this.jobSeeker.gender = this.registerForm.value.gender;
    this.jobSeeker.password = this.registerForm.value.password;
    console.log(this.jobSeeker)
    // this.registerService.jobSeekerRegister(this.jobSeeker).subscribe(data=>{
    //   alert("JobSeeker data added successfully")
    //   this.router.navigate(["/userLogin"])
    // },error=>alert("Sorry not able to register jobSeeker Details."));

  }

  hide = true;


  uploadImage:any;
  uploadImageFile:any;

  onImageUpload(event: any)
  {
    console.log("onchange");
    this.uploadImageFile = event.target.files[0];

    const reader = new FileReader();
    reader.readAsDataURL(event.target.files[0]);
    reader.onload = (_event) =>
    {
      // console.log(reader.result);
      this.uploadImage = reader.result;
      // console.log(this.uploadImage);
    }
}

submit(){
  this.jobSeeker.emailId = this.registerForm.value.emailId;
  this.jobSeeker.firstName = this.registerForm.value.firstName;
  this.jobSeeker.lastName = this.registerForm.value.lastName;
  this.jobSeeker.dateOfBirth = this.registerForm.value.dateOfBirth;
  this.jobSeeker.mobileNumber = this.registerForm.value.mobileNumber;
  this.jobSeeker.gender = this.registerForm.value.gender;
  console.log("gender");
  console.log(this.registerForm.value.gender);
  console.log(this.jobSeeker.gender);

  this.jobSeeker.password = this.registerForm.value.password;
  console.log("password");
  console.log(this.registerForm.value.password);
  console.log(this.jobSeeker.password);

  console.log(this.jobSeeker)
  console.log("submit method ")
  console.log(this.jobSeeker);
  const uploadData = new FormData;
  uploadData.append('jobSeeker',JSON.stringify(this.jobSeeker))
  uploadData.append('file',this.uploadImageFile)
  console.log(uploadData.get("jobSeeker"));
  // this.registerService.jobSeekerRegister(uploadData)
  this.http.postImage("http://localhost:8098/api/v1/jobSeeker",uploadData).subscribe(data=>{
    console.log("data added")
    alert("JobSeeker data added successfully")
    this.router.navigate(["/userLogin"])
  })
}


  // hasUnitNumber = false;

  // states = [
  //   {
  //     "abbreviation": "AN",
  //     "name": "Andaman and Nicobar Islands"
  //   },
  //   {
  //     "abbreviation": "AP",
  //     "name": "Andhra Pradesh"
  //   },
  //   {
  //     "abbreviation": "AR",
  //     "name": "Arunachal Pradesh"
  //   },
  //   {
  //     "abbreviation": "AS",
  //     "name": "Assam"
  //   },
  //   {
  //     "abbreviation": "BR",
  //     "name": "Bihar"
  //   },
  //   {
  //     "abbreviation": "CG",
  //     "name": "Chandigarh"
  //   },
  //   {
  //     "abbreviation": "CH",
  //     "name": "Chhattisgarh"
  //   },
  //   {
  //     "abbreviation": "DH",
  //     "name": "Dadra and Nagar Haveli"
  //   },
  //   {
  //     "abbreviation": "DD",
  //     "name": "Daman and Diu"
  //   },
  //   {
  //     "abbreviation": "DL",
  //     "name": "Delhi"
  //   },
  //   {
  //     "abbreviation": "GA",
  //     "name": "Goa"
  //   },
  //   {
  //     "abbreviation": "GJ",
  //     "name": "Gujarat"
  //   },
  //   {
  //     "abbreviation": "HR",
  //     "name": "Haryana"
  //   },
  //   {
  //     "abbreviation": "HP",
  //     "name": "Himachal Pradesh"
  //   },
  //   {
  //     "abbreviation": "JK",
  //     "name": "Jammu and Kashmir"
  //   },
  //   {
  //     "abbreviation": "JH",
  //     "name": "Jharkhand"
  //   },
  //   {
  //     "abbreviation": "KA",
  //     "name": "Karnataka"
  //   },
  //   {
  //     "abbreviation": "KL",
  //     "name": "Kerala"
  //   },
  //   {
  //     "abbreviation": "LD",
  //     "name": "Lakshadweep"
  //   },
  //   {
  //     "abbreviation": "MP",
  //     "name": "Madhya Pradesh"
  //   },
  //   {
  //     "abbreviation": "MH",
  //     "name": "Maharashtra"
  //   },
  //   {
  //     "abbreviation": "MN",
  //     "name": "Manipur"
  //   },
  //   {
  //     "abbreviation": "ML",
  //     "name": "Meghalaya"
  //   },
  //   {
  //     "abbreviation": "MZ",
  //     "name": "Mizoram"
  //   },
  //   {
  //     "abbreviation": "NL",
  //     "name": "Nagaland"
  //   },
  //   {
  //     "abbreviation": "OR",
  //     "name": "Odisha"
  //   },
  //   {
  //     "abbreviation": "PY",
  //     "name": "Puducherry"
  //   },
  //   {
  //     "abbreviation": "PB",
  //     "name": "Punjab"
  //   },
  //   {
  //     "abbreviation": "RJ",
  //     "name": "Rajasthan"
  //   },
  //   {
  //     "abbreviation": "SK",
  //     "name": "Sikkim"
  //   },
  //   {
  //     "abbreviation": "TN",
  //     "name": "Tamil Nadu"
  //   },
  //   {
  //     "abbreviation": "TS",
  //     "name": "Telangana"
  //   },
  //   {
  //     "abbreviation": "TR",
  //     "name": "Tripura"
  //   },
  //   {
  //     "abbreviation": "UP",
  //     "name": "Uttar Pradesh"
  //   },
  //   {
  //     "abbreviation": "UK",
  //     "name": "Uttarakhand"
  //   },
  //   {
  //     "abbreviation": "WB",
  //     "name": "West Bengal"
  //   }
  // ]






}

