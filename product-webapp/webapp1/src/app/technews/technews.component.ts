import { Component, OnInit } from '@angular/core';
import { TrendsService } from '../service/trends.service';

@Component({
  selector: 'app-technews',
  templateUrl: './technews.component.html',
  styleUrls: ['./technews.component.css']
})
export class TechnewsComponent implements OnInit {

  // stringifiedData:any;
  // parsedJson:any;
  // uri!:string;
  // title:any;
  // url:any;
  // imgurl:any;
  // source:any;
  // articles:Array<string>=[];


  constructor(private trendsService:TrendsService) { }

  // ngOnInit(): void {
  //   this.trendsService.getTechNews().subscribe((res)=>{
  //     console.log(res);
  //     this.stringifiedData = JSON.stringify(res);
  //     this.parsedJson = JSON.parse(this.stringifiedData);
  //     console.log(this.stringifiedData);

  //     this.title = this.parsedJson[0].title;
  //     console.log(this.title);
  //     this.url = this.parsedJson[0].url;
  //     this.imgurl = this.parsedJson[0].imgurl;
  //     this.source = this.parsedJson[0].source;

  //     this.articles=this.stringifiedData;
      

  //     this.uri = this.title+this.url+this.imgurl+this.source;
  //     console.log(this.uri);

  //     this.articles.forEach(this.stringifiedData);
  //     console.log(this.articles);

      
  //   })
  // }

  techNewsDisplay:any=[];

  ngOnInit(): void {
      this.trendsService.techNews().subscribe((result)=>{
        this.techNewsDisplay = result.articles;
      });
  }
}
