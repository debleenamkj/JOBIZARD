import { Component, OnInit } from '@angular/core';
import { TrendsService } from '../service/trends.service';

@Component({
  selector: 'app-salary-trend-lab',
  templateUrl: './salary-trend-lab.component.html',
  styleUrls: ['./salary-trend-lab.component.css']
})
export class SalaryTrendLabComponent implements OnInit {

  jobTitle:any;

  stringifiedData:any;
  parsedJson:any;
  average:any;
  standard:any;
  title:any;
  url!:string;
  graphType:string []=["bar","horizontalBar","violin"];
  myGraph = "bar";

  data:string = "";
  errorMessage = "";
  constructor(private trendsService:TrendsService) { }

  ngOnInit(): void {
    
  }

  selectedTitle(){
    console.log(this.jobTitle);
    this.trendsService.getSalary(this.jobTitle).subscribe((data)=>{
      console.log(data);  
      this.stringifiedData = JSON.stringify(data);    
      this.parsedJson = JSON.parse(this.stringifiedData);
      console.log(this.parsedJson);
      console.log(this.parsedJson.info[0].average);
      this.average = this.parsedJson.info[0].average;
      console.log(this.average);
      
      console.log(this.parsedJson.info[0].std);
      this.standard = this.parsedJson.info[0].std;

      console.log(this.standard);
      
      console.log(this.parsedJson.info[0].title);
      this.title = this.parsedJson.info[0].title;
      console.log(this.title);
      
      this.url = "https://quickchart.io/chart?width=500&height=270&format=img&c={type:'"+this.myGraph+"',data:{labels:[2022],datasets:[{label:'Basic Salary for "+this.title+"',data:["+this.standard+"]},{label:'Average Salary for "+this.title+"',data:["+this.average+"]}]}}";
    },
    (error)=>{
      console.log("error occured"+error);
      this.errorMessage = "The Graph Cannot be Generated at this moment. Please Try Again Later.";
    })
  }

}
