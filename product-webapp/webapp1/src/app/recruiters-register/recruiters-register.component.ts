import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { OrganizationDetails } from '../model/organizationDetails';
import { RegisterServiceService } from '../service/register-service.service';

@Component({
  selector: 'app-recruiters-register',
  templateUrl: './recruiters-register.component.html',
  styleUrls: ['./recruiters-register.component.css']
})
export class RecruitersRegisterComponent
{
  organization:OrganizationDetails = new OrganizationDetails();

  recruiterForm = this.formBuilder.group({
    emailId: ["", Validators.required],
    organizationName: ["", Validators.required],
    organizationSector: ["", Validators.required],
    organizationOrigin:["", Validators.required],
    roleOfHiring:[, Validators.required],
    contactNumber: ["", Validators.required],
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

  

  // recruiterRegister()
  // {
    
  //   this.recruiterRegister.emailId = this.recruiterForm.value.emailId;
  //   this.recruiterRegister.organizationName = this.recruiterForm.value.organizationName;
  //   this.recruiterRegister.organizationSector = this.recruiterForm.value.organizationSector;
  //   this.recruiterRegister.organizationOrigin = this.recruiterForm.value.organizationOrigin;
  //   this.recruiterRegister.roleOfHiring = this.recruiterForm.value.roleOfHiring;
  //   this.recruiterRegister.contactNumber = this.recruiterForm.value.contactNumber;
  //   this.recruiterRegister.password = this.recruiterForm.value.password;
  //   console.log(this.recruiterRegister)
  //   // this.registerService.jobSeekerRegister(this.jobSeeker).subscribe(data=>{
  //   //   alert("JobSeeker data added successfully")
  //   //   this.router.navigate(["/userLogin"])
  //   // },error=>alert("Sorry not able to register jobSeeker Details."));

  // }

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
  this.organization.emailId = this.recruiterForm.value.emailId;
  this.organization.organizationName = this.recruiterForm.value.organizationName;
  this.organization.organizationSector = this.recruiterForm.value.organizationSector;
  this.organization.organizationOrigin = this.recruiterForm.value.organizationOrigin;
  this.organization.roleOfHiring = this.recruiterForm.value.roleOfHiring;
  this.organization.contactNumber = this.recruiterForm.value.contactNumber;

  // console.log(this.registerForm.value.gender);
  // console.log(this.jobSeeker.gender);
  
  this.organization.password = this.recruiterForm.value.password;
  // console.log("password");
  // console.log(this.registerForm.value.password);
  // console.log(this.jobSeeker.password);
  
  // console.log(this.jobSeeker)
  // console.log("submit method ")
  // console.log(this.jobSeeker);

  const uploadData = new FormData;
  uploadData.append('jobSeeker',JSON.stringify(this.organization))
  uploadData.append('file',this.uploadImageFile)
  console.log(uploadData.get("jobSeeker"));
  // this.registerService.jobSeekerRegister(uploadData)
  this.http.post("http://localhost:8098/api/v1/jobSeeker",uploadData).subscribe(data=>{
    console.log("data added")
    alert("JobSeeker data added successfully")
    this.router.navigate(["/userLogin"])
  })
}}