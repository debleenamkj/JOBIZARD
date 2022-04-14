import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { JobSeekerLanding } from '../model/job-seeker-landing';
import { Skillset } from '../model/skillset';

@Injectable({
  providedIn: 'root'
})
export class RecruiterlandingService {

  email: string="";
  

  constructor(private httpCLient: HttpClient) { }

  getRecruiterProfile():any{
    return this.httpCLient.get("http://localhost:8098/api/v1/recruiterProfile/"+this.email)
  }

  getAllJobSeekers():Observable<Array<JobSeekerLanding>>{
    return this.httpCLient.get<Array<JobSeekerLanding>>("http://localhost:8098/api/v1/jobSeekers/"+this.email)
  }

  getSkillSet():Observable<Array<Skillset>>{
    return this.httpCLient.get<Array<Skillset>>("http://localhost:8098/api/v1/skillSet"+this.email)
  }
}
