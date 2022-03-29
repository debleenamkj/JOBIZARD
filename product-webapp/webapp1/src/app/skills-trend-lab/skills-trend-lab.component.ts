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
   // if(this.mySkills == this.skill)
  }

}
