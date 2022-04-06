import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { SkillTrend } from '../model/skill-trend';

@Injectable({
  providedIn: 'root'
})
export class TrendsService {

  constructor(private http:HttpClient) { }

  getSkills() : Observable<SkillTrend[]>{
    return this.http.get<SkillTrend[]>("http://localhost:8086/api/v6/getnames");
  }

  getAllSkills() : Observable<SkillTrend[]>{
    return this.http.get<SkillTrend[]>("http://localhost:8086/api/v6/getskills");
  }

  getSalary(jobTitle:any) : Observable<JSON>{
    return this.http.get<JSON>("http://localhost:8086/api/v6/salarys/"+jobTitle);
  }

  getTheVergeNews() : Observable<JSON[]>{
    return this.http.get<JSON[]>("http://localhost:8086/api/v2/getTechNews");
  }

}
