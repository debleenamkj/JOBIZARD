import { Component, OnInit } from '@angular/core';
import { map } from 'rxjs/operators';
import { Breakpoints, BreakpointObserver } from '@angular/cdk/layout';
import {MatDialog} from '@angular/material/dialog';
import { AssesmentTestPortalComponent } from '../assesment-test-portal/assesment-test-portal.component';
import { SkilltestServiceService } from '../service/skilltest-service.service';
@Component({
  selector: 'app-assesment-portal',
  templateUrl: './assesment-portal.component.html',
  styleUrls: ['./assesment-portal.component.css']
})
export class AssesmentPortalComponent {
  
   constructor(public dialog:MatDialog,private service: SkilltestServiceService){
   }
   openDialog() {
    const dialogRef = this.dialog.open(AssesmentTestPortalComponent);

    dialogRef.afterClosed().subscribe((result: any) => {
      console.log(`Dialog result: ${result}`);
    });
  }

  getQuizName(name:any){
    console.log("dialog")
    console.log(name);
      this.service.quizName=name;
  }
}

 
  




