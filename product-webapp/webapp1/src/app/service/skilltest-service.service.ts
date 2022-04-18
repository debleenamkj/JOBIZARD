import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class SkilltestServiceService {

  constructor(private httpClient:HttpClient) { }
  answeredQuestions:number = 0;
  unAnsweredQuestions:number=0;
  percentage:number=0;
  worrior:string="";
  quizName:string="";

  getQuestions(){
    return this.httpClient.get("http://localhost:3000/test")
  }

  // http://localhost:8081/category/java
  // http://localhost:8081/category/java

  getTest(testName:string){
    const testname = testName.toLowerCase()
    console.log('http://localhost:8081/category/'+testName.toLowerCase());
    return this.httpClient.get("http://localhost:8081/category/"+testName);
  }
  getninja(){
    return 'assets\\warriors_logos\\ninja.png'
  }
  getbeginner(){
    return 'assets\\warriors_logos\\beginner.PNG';
  }
  getsaga(){
    return 'assets\\warriors_logos\\jobified.PNG';
  }
  getgladiator(){
    return 'assets\\warriors_logos\\gladiator.PNG';
  }
}
