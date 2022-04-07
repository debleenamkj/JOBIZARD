import { Component, OnInit } from '@angular/core';
import { TrendsService } from '../service/trends.service';

@Component({
  selector: 'app-businessnews',
  templateUrl: './businessnews.component.html',
  styleUrls: ['./businessnews.component.css']
})
export class BusinessnewsComponent implements OnInit {

  constructor(private trendsService:TrendsService) { }

  businessNewsDisplay:any=[];

  ngOnInit(): void {
    this.trendsService.businessNews().subscribe((result)=>{
      this.businessNewsDisplay = result.articles;
    });
  }
}
