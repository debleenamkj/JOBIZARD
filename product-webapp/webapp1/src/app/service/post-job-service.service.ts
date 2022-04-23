import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class PostJobServiceService {

  constructor(private httpClient:HttpClient) { }

  baseUrl='https://jobizard.stackroute.io' + '/application-register-service'
  // baseUrl='http://localhost:8098'


  // postJob(job:any){
  //   console.log("service method called");
  //   // return this.httpClient.post('http://localhost:8098/api/v1/recruiter1/'+emailId,job);
  //   return this.httpClient.post("http://localhost:8090/recruitment/posting",job)
  // }

  postRecruiter(job:any,emailId:string){
    console.log("service method called");
    return this.httpClient.put(this.baseUrl + '/api/v1/recruiterWithImage/'+emailId,job);
  }

  getRecruiter(emailId:string){
    return this.httpClient.get(this.baseUrl + "/api/v1/recruiterProfile/"+emailId);
  }

  updateRecruiter(emailId:string,data:any){
    return this,this.httpClient.put(this.baseUrl + '/api/v1/recruiterWithoutImage/'+emailId,data)
  }

  // postJob1(job:any){
  //   return this.httpClient.post("http://localhost:8090/recruitment/postjob",job)
  // }

  // getCompany(companyName:string){
  //   return this.httpClient.get('http://localhost:8090/recruitment/getCompany/'+companyName)
  // }


}
