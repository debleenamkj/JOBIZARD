import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { SkillTrend } from '../model/skill-trend';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class TrendsService {

  constructor(private http: HttpClient) { }


  baseUrl = environment.apiBaseUrl + '/trend-lab-service'

  // baseUrl='http://localhost:8086'

  getSkills(): Observable<SkillTrend[]> {
    return this.http.get<SkillTrend[]>(this.baseUrl + "/api/v6/getnames");
  }

  getAllSkills(): Observable<SkillTrend[]> {
    return this.http.get<SkillTrend[]>(this.baseUrl + "/api/v6/getskills");
  }

  getSalary(jobTitle: any): Observable<JSON> {
    return this.http.get<JSON>(this.baseUrl + "/api/v6/salary/" + jobTitle);
  }

  techNewsApiUrl: any = "https://newsapi.org/v2/top-headlines?country=in&category=technology&apiKey=0aaeaf07a51d4efcabb8a92ae8b1dd15";

  businessNewsApiUrl: any = "https://newsapi.org/v2/top-headlines?country=in&category=business&apiKey=0aaeaf07a51d4efcabb8a92ae8b1dd15";

  techNews(): Observable<any> {
    return this.http.get(this.techNewsApiUrl);
  }

  businessNews(): Observable<any> {
    return this.http.get(this.businessNewsApiUrl);
  }

  // getTechNews() : Observable<JSON[]>{
  //   return this.http.get<JSON[]>("http://localhost:8086/api/v2/getTechNews");
  // }

}
