import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class RecruiterlandingService {

  emailId: string;

  constructor(private httpCLient: HttpClient) { }

  baseUrl='https://jobizard.stackroute.io' + '/application-register-service'
  // baseUrl='http://localhost:8098'

  getRecruiterProfile():any{
    this.emailId=localStorage.getItem('loginId')
    return this.httpCLient.get(this.baseUrl+'/api/v1/recruiterProfile/'+this.emailId);
  }

  getAllJobSeekers():Observable<any>{
    return this.httpCLient.get(this.baseUrl+'/api/v1/jobSeekers');
  }
}
