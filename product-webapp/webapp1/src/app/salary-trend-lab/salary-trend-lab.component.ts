import { Component, OnInit } from '@angular/core';
import { TrendsService } from '../service/trends.service';

@Component({
  selector: 'app-salary-trend-lab',
  templateUrl: './salary-trend-lab.component.html',
  styleUrls: ['./salary-trend-lab.component.css']
})
export class SalaryTrendLabComponent implements OnInit {

  jobTitle:any;

  data:string = "";
  constructor(private trendsService:TrendsService) { }

  ngOnInit(): void {
    
  }

  selectedTitle(){
    console.log(this.jobTitle);
    this.trendsService.getSalary(this.jobTitle).subscribe((data)=>{
      console.log(data);      

    })
  }

}
