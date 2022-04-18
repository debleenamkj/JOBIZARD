import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';


@Injectable({
  providedIn: 'root'
})
export class CvTemplateService {
 
  url:String="http://localhost:8082/api/v1/userByCvId/";
  userId:number;
  email:String="sajidhakhan@gmail.com";
  constructor(private http: HttpClient) { 
 
  }
  getCv(){
    return this.http.get(this.url+""+this.userId);
  }
  getByEmail(){
    return this.http.get('http://localhost:8082/api/v1/cvByEmail/'+this.email);
  }
}
