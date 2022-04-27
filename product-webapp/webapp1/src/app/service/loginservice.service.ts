import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Subject } from 'rxjs';
import { environment } from 'src/environments/environment';


// import { UserLogin } from '../model/userLogin';

@Injectable({
  providedIn: 'root'
})

export class LoginserviceService
{

//   isloggedIn = false;
//   loginUrl = '';
//   checkLogin() {
//     this.isloggedIn = true;
//   }


role="";

  constructor(private http:HttpClient) { }

//  baseUrl='https://jobizard.stackroute.io' + '/authentication-service'
baseUrl='http://localhost:8099'

//   userLogIn(userLogin:UserLogin)  // any - jobSeeker and recruiter any one can log-in
//   {
//     // console.log(userLogin);
//     return this.http.postImage("http://localhost:8099/api/v2/login",userLogin)

//     // return this.http.get("http://localhost:8099/api/v2/login",userLogin)
//   }

    public loginStatusSubject = new Subject<boolean>();


    //generate token
    public generateToken(loginData: any)
    {
        return this.http.post(this.baseUrl + "/api/v2/login", loginData);
    }
  

    //login user: set token in localStorage
    public loginUser(token:any)
    {
        localStorage.setItem('token', token);
        return true;
    }
  

    //isLogin: user is logged in or not
    public isLoggedIn()
    {
        let tokenStr = localStorage.getItem('token');
        if (tokenStr == undefined || tokenStr == '' || tokenStr == null)
        {
            return false;
        }
        else
        {
            return true;
        }
    }
  

    // logout : remove token from local storage
    public logout()
    {
        localStorage.removeItem('token');
        localStorage.removeItem('user');
        return true;
    }

  
    //get token
    public getToken()
    {
        return localStorage.getItem('token');
    }
  
    //set userDetail
    public setUser(user:any)
    {
        localStorage.setItem('user', JSON.stringify(user));
    }
  
    //getUser
    public getUser()
    {
        let userStr = localStorage.getItem('user');
        if (userStr != null)
        {
            return JSON.parse(userStr);
        }
        else
        {
            this.logout();
            return null;
        }
    }
  
    //get user role
    public getUserRole()
    {
        let user = this.getUser();
        return user.authorities[0].authority;
    }

}
// }
