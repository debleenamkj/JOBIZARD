import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { OrganizationDetails } from '../model/organizationDetails';
import { RegisterServiceService } from '../service/register-service.service';

@Component({
  selector: 'app-organization-details',
  templateUrl: './organization-details.component.html',
  styleUrls: ['./organization-details.component.css']
})
export class OrganizationDetailsComponent implements OnInit {

  constructor(private registerService : RegisterServiceService , private router : Router) { }

  ngOnInit(): void {}

  
  organization:OrganizationDetails = new OrganizationDetails;

  organizationRegister()
  {
    console.log(this.organizationRegister)
    this.registerService.organizationRegister(this.organization).subscribe(data=>{
      alert("Organization  data added successfully")
      this.router.navigate(["/userLogin"])
    },error=>alert("Sorry not able to register Organization Details. "));
  }



  uploadLogo:any;

  onUploadLogo(event: any)
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

}
