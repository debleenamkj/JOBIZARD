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

  quizName:string="";

  getQuestions(){
    return this.httpClient.get("http://localhost:3000/test")
  }

  getTest(testName:any){
    console.log('http://localhost:3000/test/'+testName);
    return this.httpClient.get("http://localhost:3000/test/"+testName);
  }
}
