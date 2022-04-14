import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router'

@Component({
  selector: 'app-cv-generation',
  templateUrl: './cv-generation.component.html',
  styleUrls: ['./cv-generation.component.css']
})
export class CvGenerationComponent implements OnInit {

  constructor(private router : Router) { }

  ngOnInit(): void {
  }
  
  temp(){
    this.router.navigate(['/cv-template1']);
  }
}
