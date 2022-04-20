import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';  
import { Search } from '../search';
import { HttpClient, HttpHeaders } from '@angular/common/http';  
import { environment } from 'src/environments/environment';
@Injectable({
  providedIn: 'root'
})
export class SearchService {
  baseUrl=environment.apiBaseUrl+'/recommendation-service';

  //baseUrl = "http://localhost:8083";
  baseUrl1=environment.apiBaseUrl +'/application-register-service'
  //baseUrl1="http://localhost:8098";
  baseUrl2=environment.apiBaseUrl+'/email-service'
  //baseUrl2="http://localhost:8085";
  
  emailId!:string
  constructor(private http: HttpClient) { }

  
  getEmail(data:any): Observable<any> {
    return this.http.post<string[]>("/api/v1/recommend/match",data);
  }
  getDetail(): Observable<any> {
    return this.http.get(this.baseUrl1+"/api/v1/getALlJobSeeker");
  }
  sendEmail(data1:any):Observable<any>{
    return this.http.post(this.baseUrl2+"/api/v1/sendemail",data1);
  }

  getJobSeeker(emailId:string){
    return this.http.get(this.baseUrl1+"/api/v1/"+emailId);
  }
  getRecruiter():any{
    this.emailId=localStorage.getItem('loginId')
    return this.http.get(this.baseUrl1+"/api/v1/recruiterProfile/"+this.emailId);
  }
}

  