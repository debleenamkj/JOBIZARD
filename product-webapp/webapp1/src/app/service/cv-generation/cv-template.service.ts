import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';


@Injectable({
  providedIn: 'root'
})
export class CvTemplateService {
 
  url:String="http://localhost:8082/api/v1/userByCvId/";
  userId:number=13;
  constructor(private http: HttpClient) { 
 
  }
  getCv(){
    return this.http.get(this.url+""+this.userId);
  }
}
