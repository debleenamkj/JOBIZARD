import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder,FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { OrganizationDetails } from '../model/organizationDetails';
import { RegisterServiceService } from '../service/register-service.service';

@Component({
  selector: 'app-organization-details',
  templateUrl: './organization-details.component.html',
  styleUrls: ['./organization-details.component.css']
})
export class OrganizationDetailsComponent implements OnInit {

  organizationDetails:OrganizationDetails = new OrganizationDetails();

  registerForm = this.formBuilder.group({
    emailId: ["", Validators.required],
    organizationName: ["", Validators.required],
    organizationSector: ["", Validators.required],
    organizationOrigin:["", Validators.required],
    roleOfHiring:["", Validators.required],
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

  

  organizationRegister()
  {
    this.organizationDetails.emailId = this.registerForm.value.emailId;
    this.organizationDetails.organizationName = this.registerForm.value.organizationName;
    this.organizationDetails.organizationSector = this.registerForm.value.organizationSector;
    this.organizationDetails.organizationOrigin = this.registerForm.value.organizationOrigin;
    this.organizationDetails.roleOfHiring = this.registerForm.value.roleOfHiring;
    this.organizationDetails.contactNumber = this.registerForm.value.contactNumber;
    this.organizationDetails.password = this.registerForm.value.password;
    console.log(this.organizationRegister)
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
  this.organizationDetails.emailId = this.registerForm.value.emailId;
  this.organizationDetails.organizationName = this.registerForm.value.organizationName;
  this.organizationDetails.organizationSector = this.registerForm.value.organizationSector;
  this.organizationDetails.organizationOrigin = this.registerForm.value.organizationOrigin;
  this.organizationDetails.roleOfHiring = this.registerForm.value.roleOfHiring;
  this.organizationDetails.contactNumber = this.registerForm.value.contactNumber;

  // console.log(this.registerForm.value.gender);
  // console.log(this.jobSeeker.gender);
  
  this.organizationDetails.password = this.registerForm.value.password;
  // console.log("password");
  // console.log(this.registerForm.value.password);
  // console.log(this.jobSeeker.password);
  
  // console.log(this.jobSeeker)
  // console.log("submit method ")
  // console.log(this.jobSeeker);

  const uploadData = new FormData;
  uploadData.append('organizationDetails',JSON.stringify(this.organizationDetails))
  uploadData.append('file',this.uploadImageFile)
  console.log(uploadData.get("organizationDetails"));
  // this.registerService.jobSeekerRegister(uploadData)
  this.http.post("http://localhost:8098/api/v1/organizationDetails",uploadData).subscribe(data=>{
    console.log("data added")
    alert("Data added successfully")
    this.router.navigate(["/userLogin"])
  })
}}