import { Component, Input, OnInit } from '@angular/core';
import {FormBuilder, FormGroup} from '@angular/forms';

@Component({
  selector: 'app-search-portal',
  templateUrl: './search-portal.component.html',
  styleUrls: ['./search-portal.component.css']
})
export class SearchPortalComponent implements OnInit {
  gridColumns = 3;
  skills:FormGroup;
  constructor(fb:FormBuilder) {
    this.skills=fb.group({
      java:false,
      spring:false,
      angular:false
    })
   }

  ngOnInit(): void {
  }
 
}
