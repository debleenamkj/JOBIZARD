import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
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

  mySkills = '1';
  selectedValue: any;

  constructor(private trendsService:TrendsService, private router: Router) { }

  ngOnInit(): void {
    this.trendsService.getSkills().subscribe((data)=>{
      this.skill = data;
      console.log(this.skill);
      console.log(data.filter(e=>e.onDemandSkills==this.mySkills).values);
      
    })
  }

  selectedSkill(){
    console.log(this.mySkills);
   // if(this.mySkills == this.skill)
    this.trendsService.getAllSkills().subscribe((data)=>{
      console.log(data.filter(e=>e.onDemandSkills==this.mySkills));
      const result = data.filter(e=>{return e.onDemandSkills==this.mySkills});
      console.log(result);
      this.allSkill = result;
      console.log(this.allSkill);
      
    })
    
  }

}
