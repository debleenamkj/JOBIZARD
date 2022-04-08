import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class PostJobServiceService {

  constructor(private httpClient:HttpClient) { }

  postJob(job:any){
    console.log("service method called");
    return this.httpClient.post("http://localhost:8090/recruitment/posting",job)
  }

  postJob1(job:any){
    return this.httpClient.post("http://localhost:8090/recruitment/postjob",job)
  }

  getCompany(companyName:string){
    return this.httpClient.get('http://localhost:8090/recruitment/getCompany/'+companyName)
  }


}
