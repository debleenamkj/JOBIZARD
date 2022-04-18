import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';  
import { Search } from './search';
import { HttpClient, HttpHeaders } from '@angular/common/http';  
@Injectable({
  providedIn: 'root'
})
export class SearchService {

  private baseUrl = 'http://localhost:8083/api/v1/recommend/match';
  private url='http://localhost:8098/api/v1/getALlJobSeeker';

  constructor(private http: HttpClient) { }

  
  getEmail(data:any): Observable<any> {
    return this.http.post(`${this.baseUrl}`,data);
  }
  getDetail(): Observable<any> {
    return this.http.get(`${this.url}`);
  }
}

  