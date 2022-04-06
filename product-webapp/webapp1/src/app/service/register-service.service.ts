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
  jobSeekerRegister(jobSeekerData:any)
  {
    console.log("in service")
    return this.http.postImage("http://localhost:8098/api/v1/jobSeeker",jobSeekerData)
  }

  recruiterRegister(recruiter:Recruiter):Observable<object>
  {
    return this.http.postImage("http://localhost:8098/api/v1/registerRecruiter",recruiter)
  }

  organizationRegister(organization:OrganizationDetails):Observable<object>
  {
    return this.http.postImage("http://localhost:8098/api/v1/saveOrganizationDetails",organization)
  }


  userLogIn(userLogin:UserLogin)  // any - jobSeeker and recruiter any one can log-in
  {
    // console.log(userLogin);
    return this.http.postImage("http://localhost:8099/api/v2/login",userLogin)

    // return this.http.get("http://localhost:8099/api/v2/login",userLogin)
  }

    // upload(file: File) {
  //   throw new Error('Method not implemented.');
  // }
  // url="http://localhost:8089/api/v1";
  // constructor(private httpclient:HttpClient) { }
  // registeruser(jobSeeker:JobSeeker):Observable<object>{
  //   console.log(jobSeeker);
  //   return this.httpclient.postImage(`${this.url}/register`,jobSeeker);
  // }




}
