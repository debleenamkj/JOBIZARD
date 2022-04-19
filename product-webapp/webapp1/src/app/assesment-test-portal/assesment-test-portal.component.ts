import { Component, OnInit } from '@angular/core';
import {MatDialogModule} from '@angular/material/dialog';
import { Router } from '@angular/router';
import { SkilltestServiceService } from '../service/skilltest-service.service';
@Component({
  selector: 'app-assesment-test-portal',
  templateUrl: './assesment-test-portal.component.html',
  styleUrls: ['./assesment-test-portal.component.css']
})
export class AssesmentTestPortalComponent  {

  constructor(private service:SkilltestServiceService,private router:Router) { }

 startTest(){
  // let quizName = this.service.quizName;
  this.router.navigate(['/navbar/test']);
 }

}
