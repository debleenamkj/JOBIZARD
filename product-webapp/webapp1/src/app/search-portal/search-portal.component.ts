import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-search-portal',
  templateUrl: './search-portal.component.html',
  styleUrls: ['./search-portal.component.css']
})
export class SearchPortalComponent implements OnInit {
  gridColumns = 3;
  constructor() { }

  ngOnInit(): void {
  }

}
