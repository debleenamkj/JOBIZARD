import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class RecruiterlandingService {

  email: string="";
  

  constructor(private httpCLient: HttpClient) { }

  getRecruiterProfile():any{
    return this.httpCLient.get("http://localhost:8098/api/v1/recruiterProfile/"+this.email)
  }

  getAllJobSeekers():any{
    return this.httpCLient.get("http://localhost:8098/api/v1/jobSeekers/"+this.email)
  }

  getSkillSet():Observable<Array<String>>{
    return this.httpCLient.get<Array<String>>("http://localhost:8098/api/v1/skillSet"+this.email)
  }
}
