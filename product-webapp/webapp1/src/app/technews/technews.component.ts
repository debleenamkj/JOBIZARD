import { Component, OnInit } from '@angular/core';
import { TrendsService } from '../service/trends.service';

@Component({
  selector: 'app-technews',
  templateUrl: './technews.component.html',
  styleUrls: ['./technews.component.css']
})
export class TechnewsComponent implements OnInit {

  constructor(private trendsService:TrendsService) { }

  techNewsDisplay:any=[];

  ngOnInit(): void {
      this.trendsService.techNews().subscribe((result)=>{
        this.techNewsDisplay = result.articles;
      });
  }
}
