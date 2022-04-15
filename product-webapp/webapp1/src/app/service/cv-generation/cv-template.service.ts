import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class CvTemplateService {
   response:any;
  constructor(private http: HttpClient) { 
   
  }
  getCv(){
    this.http.get('http://localhost:8082/api/v1/userByCvId/2')
    .subscribe((response: any) =>{
      this.response=response;
      console.log(response)
    });
  }
}
