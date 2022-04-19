import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class PostJobServiceService {

  constructor(private httpClient:HttpClient) { }

  postJob(job:any){
    console.log("service method called");
    // return this.httpClient.post('http://localhost:8098/api/v1/recruiter1/'+emailId,job);
    return this.httpClient.post("http://localhost:8090/recruitment/posting",job)
  }

  postRecruiter(job:any,emailId:string){
    console.log("service method called");
    return this.httpClient.put('http://localhost:8098/api/v1/recruiter1/'+emailId,job);
  }

  getRecruiter(emailId:string){
    return this.httpClient.get("http://localhost:8098/api/v1/recruiterProfile/"+emailId);
  }

  updateRecruiter(emailId:string,data:any){
    return this,this.httpClient.put('http://localhost:8098/api/v1/recruiterWithoutImage/'+emailId,data)
  }

  postJob1(job:any){
    return this.httpClient.post("http://localhost:8090/recruitment/postjob",job)
  }

  getCompany(companyName:string){
    return this.httpClient.get('http://localhost:8090/recruitment/getCompany/'+companyName)
  }


}
