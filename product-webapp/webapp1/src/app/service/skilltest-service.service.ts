import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class SkilltestServiceService {

  constructor(private httpClient:HttpClient) { }

  getQuestions(){
    return this.httpClient.get("http://localhost:3000/test")
  }
}
