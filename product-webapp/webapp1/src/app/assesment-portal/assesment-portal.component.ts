import { Component, OnInit } from '@angular/core';
import { map } from 'rxjs/operators';
import { Breakpoints, BreakpointObserver } from '@angular/cdk/layout';
import {MatDialog} from '@angular/material/dialog';
import { AssesmentTestPortalComponent } from '../assesment-test-portal/assesment-test-portal.component';
@Component({
  selector: 'app-assesment-portal',
  templateUrl: './assesment-portal.component.html',
  styleUrls: ['./assesment-portal.component.css']
})
export class AssesmentPortalComponent {
  
   constructor(public dialog:MatDialog){
   }
   openDialog() {
    const dialogRef = this.dialog.open(AssesmentTestPortalComponent);

    dialogRef.afterClosed().subscribe((result: any) => {
      console.log(`Dialog result: ${result}`);
    });
  }
}

 
  




