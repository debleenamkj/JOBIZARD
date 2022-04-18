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

      // role="jobseeker";
      role="recruiter";
      // role="";

  // name="sajal@gmail.com";
  // n1=name.split("@",3);
  firstname="Jobsie";
  lastname="Jobbie";
  email="jobseeker@gmail.com";

  constructor(private breakpointObserver: BreakpointObserver, private router: Router , private dialog : MatDialog, 
    private loginService : RegisterServiceService) {}


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


}
