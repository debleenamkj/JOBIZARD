import { Component, OnInit } from '@angular/core';
import { TrendsService } from '../service/trends.service';

@Component({
  selector: 'app-technews',
  templateUrl: './technews.component.html',
  styleUrls: ['./technews.component.css']
})
export class TechnewsComponent implements OnInit {

  stringifiedData:any;
  parsedJson:any;
  uri!:string;
  title:any;
  url:any;
  source:any;
  articles:Array<string>=[];


  constructor(private trendsService:TrendsService) { }

  ngOnInit(): void {
    this.trendsService.getTheVergeNews().subscribe((res)=>{
      console.log(res);
      this.stringifiedData = JSON.stringify(res);    
      this.parsedJson = JSON.parse(this.stringifiedData);

      this.title = this.parsedJson[0].title;
      this.url = this.parsedJson[0].url;
      this.source = this.parsedJson[0].source;

      this.articles=this.stringifiedData;
      console.log(this.articles);
    

      
    })
  }

}
