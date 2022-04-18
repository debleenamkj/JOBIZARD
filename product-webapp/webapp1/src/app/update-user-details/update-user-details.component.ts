import { Component, OnInit } from '@angular/core';
import {COMMA, ENTER} from '@angular/cdk/keycodes';
import {MatChipInputEvent} from '@angular/material/chips';
import { UserDetails } from '../model/user-details';
import { FormBuilder, FormGroup } from '@angular/forms';
import { MatDialog, MatDialogConfig } from '@angular/material/dialog';
import { AddSkillComponent } from '../add-skill/add-skill.component';

//************************************** interface declared of ts components of chips********************************

export interface AcademicsCertification {
  name: string;
}
export interface SkillSet {
  name: string;
}
export interface JobPreferences {
  name: string;
}
export interface Achievements {
  name: string;
}
export interface Courses {
  name: string;
}

//****************************************** */  ts components of chips**************************************


@Component({
  selector: 'app-update-user-details',
  templateUrl: './update-user-details.component.html',
  styleUrls: ['./update-user-details.component.css']
})
export class UpdateUserDetailsComponent implements OnInit {
   updateDetails:any = FormGroup;
  constructor(private fb:FormBuilder,
    private dialog:MatDialog)
     { }

  ngOnInit(): void {
      this.updateDetails = this.fb.group({})
  }

  // ************************** Starting of Chips components code *************************************

  addOnBlur = true;
  readonly separatorKeysCodes = [ENTER, COMMA] as const;
  academicsCertifications: AcademicsCertification[] = [];

  add(event: MatChipInputEvent): void {
    console.log("acadamic");
    const value = (event.value || '').trim();

    // Add our Academics Certifications
    if (value) {
      this.academicsCertifications.push({name: value});
    }

    // Clear the input value
    event.chipInput!.clear();
  }

  remove(academicsCertification: AcademicsCertification): void {
    const index = this.academicsCertifications.indexOf(academicsCertification);

    if (index >= 0) {
      this.academicsCertifications.splice(index, 1);
    }
  }

  // ----------------------Skill Set--------------------------------------------


  // addOnBlur = true;
  // readonly separatorKeysCodes = [ENTER, COMMA] as const;
  skillSets: SkillSet[] = [];

  addskills(event: MatChipInputEvent): void {
    const value = (event.value || '').trim();

    // Add our SkillSet
    if (value) {
      this.skillSets.push({name: value});
    }

    // Clear the input value
    event.chipInput!.clear();
  }

  removeskills(SkillSet: SkillSet): void {
    const index = this.skillSets.indexOf(SkillSet);

    if (index >= 0) {
      this.skillSets.splice(index, 1);
    }
  }


  // addOnBlur = true;
  // readonly separatorKeysCodes = [ENTER, COMMA] as const;
  jobPreferences: JobPreferences[] = [];

  addJobPreferences(event: MatChipInputEvent): void {
    console.log("job preferences")
    const value = (event.value || '').trim();

    // Add our fruit
    if (value) {
      this.jobPreferences.push({name: value});
    }

    // Clear the input value
    event.chipInput!.clear();
  }

  removejobPreferences(jobPreference: JobPreferences): void {
    const index = this.jobPreferences.indexOf(jobPreference);

    if (index >= 0) {
      this.jobPreferences.splice(index, 1);
    }
  }


  achievementOf: Achievements[] = [];

  addachievements(event: MatChipInputEvent): void {
    console.log("achievements")
    const value = (event.value || '').trim();

    // Add our fruit
    if (value) {
      this.achievementOf.push({name: value});
    }

    // Clear the input value
    event.chipInput!.clear();
  }

  removeachievements(achievement: Achievements): void {
    const index = this.achievementOf.indexOf(achievement);

    if (index >= 0) {
      this.jobPreferences.splice(index, 1);
    }
  }



  courseOf: Courses[] = [];

  addcourses(event: MatChipInputEvent): void {
    console.log("courses")
    const value = (event.value || '').trim();

    // Add our fruit
    if (value) {
      this.courseOf.push({name: value});
    }

    // Clear the input value
    event.chipInput!.clear();
  }

  removecourses(course: Courses): void {
    const index = this.courseOf.indexOf(course);

    if (index >= 0) {
      this.courseOf.splice(index, 1);
    }
  }


    
// ************************************ Functionaltiy of Next Button ***********************************
//   selectedIndex: number = 0;

//   public tabChanged(tabChangeEvent: MatTabChangeEvent): void {
//     this.selectedIndex = tabChangeEvent.index;
// }

selectedIndex:number=0;
clickMe(){
  this.selectedIndex=1;
}
clickMe1(){
  this.selectedIndex=2;
}
clickMe2(){
  this.selectedIndex=3;
}
selectedIndexChange(val: number){
  this.selectedIndex=val;
}


// ************************************************* Model Class implementation *********************************

      firstName:string="";
      lastName:string="";
      gender:string="";
      dateOfBirth:string="";
      mobileNumber:string="";
      profilePicture:any="";
      lane:string="";
      state:string="";
      city:string="";
      pincode:string="";
      Nationality:string="";
      academicCertification:string[]=[];
      skillSet:string[]=[];
      jobPreference:string[]=[];
      achievements:string[]=[]; //changed value above
      // Education:string="";
      // courses :string[]=[];   // changed value above
      // stream:string="";
      // university:string="";
      // courseType:string="";
      // passOutYear:string="";
      // percentageOrCgpa:string="";

userDetails:UserDetails = new UserDetails(
  this.firstName,
  this.lastName,
  this.gender,
  this.dateOfBirth,
  this.mobileNumber,
  this.profilePicture,
  this.lane,
  this.state,
  this.city,
  this.pincode,
  this.Nationality,
  this.academicCertification,
  this.skillSet,
  this.jobPreference,
  this.achievements,
  // this.Education,
  // this.courses,
  // this.stream,
  // this.university,
  // this.courseType,
  // this.passOutYear,
  // this.percentageOrCgpa

);
saveSubmit(data:any){
  console.log(data);
  
}

addskillOnClick(){
  this.dialog.open(AddSkillComponent);
}


}
