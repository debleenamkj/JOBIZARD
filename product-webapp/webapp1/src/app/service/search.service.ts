import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';  
import { Search } from '../search';
import { HttpClient, HttpHeaders } from '@angular/common/http';  
import { environment } from 'src/environments/environment';
@Injectable({
  providedIn: 'root'
})
export class SearchService {
  baseUrl='https://jobizard.stackroute.io'+'/recommendation-service';
  baseUrl1='https://jobizard.stackroute.io' +'/application-register-service'
  baseUrl2='https://jobizard.stackroute.io'+'/email-service'
  // baseUrl = "http://localhost:8083";
  // baseUrl1="http://localhost:8098";
  // baseUrl2="http://localhost:8085";
  
  emailId!:string
  constructor(private http: HttpClient) { }

  
  getEmail(data:any): Observable<any> {
    return this.http.post<string[]>('https://jobizard.stackroute.io'+'/recommendation-service'+"/api/v1/recommend/match",data);
  }
  getDetail(): Observable<any> {
    return this.http.get('https://jobizard.stackroute.io' +'/application-register-service'+"/api/v1/getALlJobSeeker");
  }
  sendEmail(data1:any):Observable<any>{
    return this.http.post('https://jobizard.stackroute.io'+'/email-service'+"/api/v1/sendemail",data1);
  }

  getJobSeeker(emailId:string){
    return this.http.get('https://jobizard.stackroute.io' +'/application-register-service'+"/api/v1/"+emailId);
  }
  getRecruiter():any{
    this.emailId=localStorage.getItem('loginId')
    return this.http.get('https://jobizard.stackroute.io' +'/application-register-service'+"/api/v1/recruiterProfile/"+this.emailId);
  }
}

  