import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { OrganizationDetails } from '../model/organizationDetails';
import { Recruiter } from '../model/recruiter';
import { RegisterServiceService } from '../service/register-service.service';

@Component({
  selector: 'app-recruiter-register',
  templateUrl: './recruiter-register.component.html',
  styleUrls: ['./recruiter-register.component.css']
})
export class RecruiterRegisterComponent implements OnInit {

  constructor(private registerService : RegisterServiceService , private router : Router) { }

  ngOnInit(): void{} 

  recruiter:Recruiter = new Recruiter();
  // organization:OrganizationDetails = new OrganizationDetails;

  recruiterRegister()
  {
    console.log(this.recruiterRegister)
    this.registerService.recruiterRegister(this.recruiter).subscribe(data=>{
      alert("Recruiter data added successfully")
      this.router.navigate(["/userLogin"])
    },error=>alert("Sorry not able to register Recruiter Details."));
  }

  uploadLogo:any;
  onImageUpload(event: any)
  {
    console.log("onchange");
    const files = event.target.files[0];
    const reader = new FileReader();
    reader.readAsDataURL(event.target.files[0]); 
    reader.onload = (_event) =>
    { 
      console.log(reader.result);
      this.uploadLogo = reader.result; 
      console.log(this.uploadLogo);
    }
}

  

  // organizationRegister()
  // {
  //   console.log(this.organizationRegister)
  //   this.registerService.organizationRegister(this.organization).subscribe(data=>{
  //     alert("Organization  data added successfully")
  //     this.router.navigate(["/userLogin"])
  //   },error=>alert("Sorry not able to register Organization Details. "));
  // }

}