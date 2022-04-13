import { Component, OnInit } from '@angular/core';
import {MatDialog, MatDialogRef, MAT_DIALOG_DATA} from '@angular/material/dialog';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {


  constructor(private dialog : MatDialog) { }

  ngOnInit(): void {
  }
  
  openRegisterForm(): void {
    
    const dialogRef = this.dialog.open(RegisterComponent)
    dialogRef.afterClosed().subscribe(result => {
      console.log('Dialog Box: $(result)')
    })
  }


}
