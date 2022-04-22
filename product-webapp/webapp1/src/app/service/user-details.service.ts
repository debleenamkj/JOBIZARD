import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { UserDetails } from '../model/user-details';

@Injectable({
  providedIn: 'root'
})
export class UserDetailsService {

  constructor(private http:HttpClient) { }


  // baseUrl=environment.apiBaseUrl + '/application-register-service'
  // baseUrl:"http://localhost:8098";
   baseUrl='https://jobizard.stackroute.io' + '/application-register-service'
  //baseUrl:"http://localhost:8098";
  
  // updateUserDetails(userdetail:any):Observable<any>
  // {
  //   return this.http.post<any>(this.baseUrl+"jobSeeker",userdetail)
  // }

  updateUserWithImage(email:string,data:any){
    return this.http.put(this.baseUrl+"/api/v1/jobSeekerWithImage/"+email,data);
  }

  updateUserWithoutImage(email:string,data:any){
    return this.http.put(this.baseUrl+'/api/v1/jobSeekerWithoutImage/'+email,data);
  }

  getJobSeeker(email:string){
    return this.http.get(this.baseUrl+"/api/v1/"+email);
  }

  updateEducation(email:string,data:any){
    return this.http.put(this.baseUrl+'/api/v1/jobSeeker/education/'+email,data);
  }
}
