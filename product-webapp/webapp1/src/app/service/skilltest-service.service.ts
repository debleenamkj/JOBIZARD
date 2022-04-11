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

  getTest(testName:any){
    console.log('http://localhost:8081/category/'+testName);
    return this.httpClient.get("http://localhost:8081/category/JAVA");
  }
  getninja(){
    return 'assets\\warriors_logos\\ninja.png'
  }
  getbeginner(){
    return 'assets\\warriors_logos\\beginner.PNG';
  }
  getjobified(){
    return 'assets\\warriors_logos\\jobified.PNG';
  }
  getgladiator(){
    return 'assets\\warriors_logos\\gladitor.PNG';
  }
}
