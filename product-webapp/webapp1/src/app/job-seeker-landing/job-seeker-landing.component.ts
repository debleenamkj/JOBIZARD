import { Component, OnInit } from '@angular/core';
import { AssesmentTestPortalComponent } from '../assesment-test-portal/assesment-test-portal.component';
import {MatDialog} from '@angular/material/dialog';
import { SkilltestServiceService } from '../service/skilltest-service.service';
@Component({
  selector: 'app-job-seeker-landing',
  templateUrl: './job-seeker-landing.component.html',
  styleUrls: ['./job-seeker-landing.component.css']
})
export class JobSeekerLandingComponent implements OnInit {

  constructor(public dialog:MatDialog,private service:SkilltestServiceService) { }

  ngOnInit(): void {
    // let file = new File('src\assets\1.jpg','')

    let skill = {name:"",warrior:"",badge:""}
    let count =0;
    this.skills.forEach((element: any) => {
      if(element.verified=='false'){
        this.notVerifiedSkills.push(element.name)
        count++;
      }
      else if(element.verified=='true'){
        let skill = {name:element.name,warrior:element.warrior,badge:element.badge}
        if(skill.name!=""){
          console.log(skill)
          this.verifiedSkills.push(skill);
          count++;
        }
        // skill.name=element.name;
        // skill.badge=element.badge;
        // skill.warrior=element.warrior;
      // this.notVerifiedSkills.length=0
      }
    });
    console.log(this.verifiedSkills);
    console.log(this.skills)
  }

  testName=""
  verifiedSkills = new Array();
  notVerifiedSkills= new Array();
  profileProgress:number=60;
  skills:any=[{name:"java",verified:"false",warrior:"",badge:""},
              {name:"spring",verified:"false",warrior:"",badge:""},
              {name:"angular",verified:"false",warrior:"",badge:""},
              {name:"JavaScript",verified:"true",warrior:"",badge:""},
              {name:"HTML",verified:"true",warrior:"",badge:""},
              {name:"CSS",verified:"true",warrior:"",badge:""},
              // {name:"JavaScript",verified:"true",warrior:"",badge:""},
              // {name:"HTML",verified:"true",warrior:"",badge:""},
              // {name:"CSS",verified:"true",warrior:"",badge:""}
            ]

  openDialog() {
    const dialogRef = this.dialog.open(AssesmentTestPortalComponent);

    dialogRef.afterClosed().subscribe((result: any) => {
      console.log(`Dialog result: ${result}`);
    });
  }

  getTestName(test:any){
    // console.log(test)
    console.log("test Name is"+test);
    // this.service.getTest(test);
    this.service.quizName=test;

  }

}
