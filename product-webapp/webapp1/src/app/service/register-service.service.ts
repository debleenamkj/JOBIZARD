import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { JobSeeker } from '../model/jobSeeker';
import { Recruiter } from '../model/recruiter';

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
  jobSeekerRegister(jobSeeker:JobSeeker)
  {
    return this.http.post("http://localhost:8098/api/v1/jobSeeker",jobSeeker)
  }

  recruiterRegister(recruiter:Recruiter)
  {
    return this.http.post("http://localhost:8098/api/v1/registerRecruiter",recruiter)
  }

  userLogIn(user:any)  // any - jobSeeker and recruiter any one can log-in
  {
    return this.http.post("http://localhost:8099/api/v2/login",user)
  }
}