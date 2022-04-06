import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { JobSeeker } from '../model/jobSeeker';
import { Recruiter } from '../model/recruiter';
import { UserLogin } from '../model/userLogin';
import { Observable } from 'rxjs';
import { OrganizationDetails } from '../model/organizationDetails';




@Injectable({
  providedIn: 'root'
})
export class RegisterServiceService {

  isloggedIn = false;
  loginUrl = '';
  checkLogin() {
    this.isloggedIn = true;
  }

  constructor(private http:HttpClient) { }
  jobSeekerRegister(jobSeeker:JobSeeker):Observable<object>
  {
    return this.http.post("http://localhost:8098/api/v1/registerJobSeeker",jobSeeker)
  }

  recruiterRegister(recruiter:Recruiter):Observable<object>
  {
    return this.http.post("http://localhost:8098/api/v1/recruiter",recruiter)
  }

  organizationRegister(organization:OrganizationDetails):Observable<object>
  {
    return this.http.post("http://localhost:8098/api/v1/organizationDetails",organization)
  }


  userLogIn(userLogin:any)  // any - jobSeeker and recruiter any one can log-in
  {
    // console.log(userLogin);
    return this.http.post("http://localhost:8099/api/v2/login",userLogin)

    // return this.http.get("http://localhost:8099/api/v2/login",userLogin)
  }
}