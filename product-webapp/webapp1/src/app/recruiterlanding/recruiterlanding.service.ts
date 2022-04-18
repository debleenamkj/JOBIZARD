import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class RecruiterlandingService {

  emailId: string;

  constructor(private httpCLient: HttpClient) { }

  getRecruiterProfile():any{
    this.emailId=localStorage.getItem('loginId')
    console.log(this.emailId);
    return this.httpCLient.get("http://localhost:8098/api/v1/recruiterProfile/"+this.emailId);
  }

  getAllJobSeekers():Observable<any>{
    return this.httpCLient.get("http://localhost:8098/api/v1/jobSeekers");
  }
}
