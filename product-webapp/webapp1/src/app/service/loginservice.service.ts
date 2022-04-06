// import { HttpClient } from '@angular/common/http';
// import { Injectable } from '@angular/core';
// import { UserLogin } from '../model/userLogin';

// @Injectable({
//   providedIn: 'root'
// })

// export class LoginserviceService
// {

//   isloggedIn = false;
//   loginUrl = '';
//   checkLogin() {
//     this.isloggedIn = true;
//   }


//   constructor(private http:HttpClient) { }

//   userLogIn(userLogin:UserLogin)  // any - jobSeeker and recruiter any one can log-in
//   {
//     // console.log(userLogin);
//     return this.http.post("http://localhost:8099/api/v2/login",userLogin)

//     // return this.http.get("http://localhost:8099/api/v2/login",userLogin)
//   }
// }