import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { JobSeeker } from '../model/jobSeeker';
import { Recruiter } from '../model/recruiter';
import { UserLogin } from '../model/userLogin';
import { Observable } from 'rxjs';
import { OrganizationDetails } from '../model/organizationDetails';
import { environment } from 'src/environments/environment';




@Injectable({
  providedIn: 'root'
})
export class RegisterServiceService {

  isloggedIn = false;
  loginUrl = '';
  checkLogin(){
    this.isloggedIn = true;
  }

  role = '';
  


  constructor(private http:HttpClient) { }

    // baseUrl='https://jobizard.stackroute.io' + '/application-register-service'
    // baseUrl1='https://jobizard.stackroute.io' + '/authentication-service'
    baseUrl='http://localhost:8098'
    baseUrl1='http://localhost:8099'

  jobSeekerRegister(jobSeekerData:any)
  {
    console.log("in service")
    return this.http.post(this.baseUrl + "/api/v1/registerJobSeeker",jobSeekerData)
  }

  recruiterRegister(recruiter:Recruiter):Observable<object>
  {
    return this.http.post(this.baseUrl + "/api/v1/registerRecruiter",recruiter)
  }

  // organizationRegister(organization:OrganizationDetails):Observable<object>
  // {
  //   return this.http.post("http://localhost:8098/api/v1/organizationDetails",organization)
  // }


  userLogIn(userLogin:UserLogin)  // any - jobSeeker and recruiter any one can log-in
  {
    // console.log(userLogin);
    return this.http.post(this.baseUrl1+ "/api/v2/login",userLogin)

    // return this.http.get("http://localhost:8099/api/v2/login",userLogin)
  }


  getUserById(emailId:string)
  {
    return this.http.get(this.baseUrl1 + "/api/v2/find/"+emailId)
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
