import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { UserDetails } from '../model/user-details';

@Injectable({
  providedIn: 'root'
})
export class UserDetailsService {

  constructor(private http:HttpClient) { }
  baseUrl:string ="http://localhost:8098/api/v1/";
  
  // updateUserDetails(userdetail:any):Observable<any>
  // {
  //   return this.http.post<any>(this.baseUrl+"jobSeeker",userdetail)
  // }

  updateUserWithImage(email:string,data:any){
    return this.http.put(this.baseUrl+"jobSeekerWithImage/"+email,data);
  }

  updateUserWithoutImage(email:string,data:any){
    return this.http.put(this.baseUrl+'jobSeekerWithoutImage/'+email,data);
  }

  getJobSeeker(email:string){
    return this.http.get(this.baseUrl+email);
  }

  updateEducation(email:string,data:any){
    return this.http.put(this.baseUrl+'jobSeeker/education/'+email,data);
  }
}
