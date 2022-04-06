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
  img:any;
  dateTime:any;
  link:any;
  articles:Array<string>=[];


  constructor(private trendsService:TrendsService) { }

  ngOnInit(): void {
    this.trendsService.getTheVergeNews().subscribe((res)=>{
      console.log(res);
      this.stringifiedData = JSON.stringify(res);    
      this.parsedJson = JSON.parse(this.stringifiedData);

      this.title = this.parsedJson[0].title;
      this.img = this.parsedJson[0].img;
      this.dateTime = this.parsedJson[0].dateTime;
      this.link = this.parsedJson[0].link;

      this.articles=this.stringifiedData;
      console.log(this.articles);
    

      
    })
  }

}
