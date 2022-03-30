import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { delay } from 'rxjs';
import { SkillTrend } from '../model/skill-trend';
import { TrendsService } from '../service/trends.service';

@Component({
  selector: 'app-skills-trend-lab',
  templateUrl: './skills-trend-lab.component.html',
  styleUrls: ['./skills-trend-lab.component.css']
})
export class SkillsTrendLabComponent implements OnInit {

  skill : SkillTrend [] = [];
  allSkill : SkillTrend [] = [];
  url!:string;
  allYears: number [] = [];
  allCompanyDemands: number [] = [];
  mySkills = '1';
  selectedValue: any;

  constructor(private trendsService:TrendsService, private router: Router) { }

  ngOnInit(): void {
    this.trendsService.getSkills().subscribe((data)=>{
      this.skill = data; 
      console.log(this.skill);      
    })
  }

  selectedSkill(){
    console.log(this.mySkills);

    this.trendsService.getAllSkills().subscribe((data)=>{

      this.allSkill = data.filter(e=>{return e.onDemandSkills==this.mySkills});


      this.allYears = data.filter(e=>e.onDemandSkills==this.mySkills).map((item)=>{
        return item.year;
      }).reverse();

      this.allCompanyDemands = data.filter(e=>e.onDemandSkills==this.mySkills).map((item)=>{
        return item.skillDemandedByCompany;
      }).reverse();
    })

    
    console.log(this.allSkill);
    console.log(this.allYears);
    console.log(this.allCompanyDemands);
    
    this.url = "https://quickchart.io/chart?width=500&height=300&format=img&c={type:'line',data:{labels:["+this.allYears+"],datasets:[{label:'"+this.mySkills+"',data:["+this.allCompanyDemands+"]}]}}";

  }

}
