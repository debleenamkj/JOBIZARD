import { Component } from '@angular/core';
import { BreakpointObserver, Breakpoints } from '@angular/cdk/layout';
import { Observable } from 'rxjs';
import { map, shareReplay } from 'rxjs/operators';
import { Router } from '@angular/router';
import { RegisterComponent } from '../register/register.component';
import { MatDialog } from '@angular/material/dialog';
import { RegisterServiceService } from '../service/register-service.service';

@Component({
  selector: 'app-nav-bar',
  templateUrl: './nav-bar.component.html',
  styleUrls: ['./nav-bar.component.css']
})
export class NavBarComponent {

  

  isHandset$: Observable<boolean> = this.breakpointObserver.observe(Breakpoints.Handset)
    .pipe(
      map(result => result.matches),
      shareReplay()
    );

    role = localStorage.getItem('role');
      // role="RECRUITER";
      // role="";

  emailId: string;
  name: string;
  

  constructor(private breakpointObserver: BreakpointObserver, private router: Router , private dialog : MatDialog, 
    private loginService : RegisterServiceService) {
      // this.getUsername()
    }


  hide=false;

  loggedOut(){
    localStorage.setItem('loginId',null)
    this.loginService.isloggedIn=false
    this.hide=true;
    this.router.navigate(["../userLogin"]);
  }

  openRegisterForm(): void {
    
    const dialogRef = this.dialog.open(RegisterComponent)
    dialogRef.afterClosed().subscribe(result => {
      console.log('Dialog Box: $(result)')
    })
  }

  // getUsername(){
  //   this.emailId=localStorage.getItem('loginId');
  //   console.log(this.emailId)
  //   // this.name = this.emailId.slice(0,this.emailId.indexOf('@'));
  //   this.name = this.emailId.split('@',2)[0];
  //   console.log(this.name);
  // }


}
