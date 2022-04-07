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
    this.organizationDetails.roleOfHiring = this.registerForm.value.roleOfHiring;
    this.organizationDetails.contactNumber = this.registerForm.value.contactNumber;
    this.organizationDetails.password = this.registerForm.value.password;
    console.log(this.organizationRegister)
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
      this.uploadImage = reader.result; 
    }
}

submit(){
  this.organizationDetails.emailId = this.registerForm.value.emailId;
  this.organizationDetails.organizationName = this.registerForm.value.organizationName;
  this.organizationDetails.organizationSector = this.registerForm.value.organizationSector;
  this.organizationDetails.roleOfHiring = this.registerForm.value.roleOfHiring;
  this.organizationDetails.contactNumber = this.registerForm.value.contactNumber;
  
  this.organizationDetails.password = this.registerForm.value.password;


  const uploadData = new FormData;
  uploadData.append('organizationDetails',JSON.stringify(this.organizationDetails))
  uploadData.append('file',this.uploadImageFile)
  console.log(uploadData.get("organizationDetails"));
  this.http.post("http://localhost:8098/api/v1/organizationDetails",uploadData).subscribe(data=>{
    console.log("data added")
    alert("Data added successfully")
    this.router.navigate(["/userLogin"])
  })
}}