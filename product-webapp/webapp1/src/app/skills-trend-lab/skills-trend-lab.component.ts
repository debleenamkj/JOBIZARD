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
  url!:string;
  allYears: number [] = [];
  allCompanyDemands: number [] = [];
  mySkills = "Select Skills";
  selectedValue: any;
  graphType:string []=["bar","pie","line","doughnut","polarArea","radar","horizontalBar","violin","sparkline"];
  myGraph = "line";

  constructor(private trendsService:TrendsService, private router: Router) { }

  ngOnInit(): void {
    this.trendsService.getSkills().subscribe((data)=>{
      this.skill = data; 
      console.log(this.skill);      
    })
  }

  valid(){
    if(this.mySkills=="Select Skills"){
      return true;
    }
    else{
      return false;
    }
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
    console.log(this.myGraph);
    
    
    this.url = "https://quickchart.io/chart?width=500&height=270&format=img&c={type:'"+this.myGraph+"',data:{labels:["+this.allYears+"],datasets:[{label:'Number of Companies Hiring for "+this.mySkills+"',data:["+this.allCompanyDemands+"],backgroundColor:'rgba(84, 186, 185,0.7)',borderColor:'rgba(5,89,91, 1.9)',borderWidth:1}]}}";
  
  }

}
