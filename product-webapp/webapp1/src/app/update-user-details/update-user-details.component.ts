import { Component, OnInit } from '@angular/core';
import {COMMA, ENTER} from '@angular/cdk/keycodes';
import {MatChipInputEvent} from '@angular/material/chips';
import { UserDetails } from '../model/user-details';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatDialog, MatDialogConfig } from '@angular/material/dialog';

import { AddSkillsComponent } from '../add-skills/add-skills.component';
import { UserDetailsService } from '../service/user-details.service';

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
    private dialog:MatDialog,
    private userDetails:UserDetailsService
    )
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

userdetails1 = new UserDetails();

addPersonalInfoForm = this.fb.group({
  firstName:['', Validators.required],
  lastName:"",
  gender:"",
  dateOfBirth:"",
  objective:"",
  profilePicture:"",
  
 });


 userdetails(){  
  console.log(this.addPersonalInfoForm);
  this.userdetails1.firstName=this.addPersonalInfoForm.value.firstName;  
  this.userdetails1.lastName=this.addPersonalInfoForm.value.lastName; 
  this.userdetails1.gender=this.addPersonalInfoForm.value.gender;  
  this.userdetails1.dateOfBirth=this.addPersonalInfoForm.value.dateOfBirth;  
  this.userdetails1.mobileNumber=this.addPersonalInfoForm.value.mobileNumber;
  this.userdetails1.objective=this.addPersonalInfoForm.value.objective;
  this.userdetails1.profilePicture=this.addPersonalInfoForm.value.profilePicture;    
    
  console.log(this.userdetails1);
    
  this.save();  
} 


save() {  
  this.userDetails.updateUserDetails(this.userdetails1)  
    .subscribe(data => console.log(data));  
}

addskillOnClick(){
  this.dialog.open(AddSkillsComponent);
}




// *****************************************************************************************

userdetails2 = new UserDetails();

addContactInfoForm = this.fb.group({
 
  mobileNumber:"",
  lane:"",
  state:"",
  city:"",
  pincode:"",
  nationality:"",
  
 });


 userContactdetails(){  
  console.log(this.addContactInfoForm);
  

  this.userdetails2.mobileNumber=this.addContactInfoForm.value.mobileNumber;
     
  this.userdetails2.lane=this.addContactInfoForm.value.lane;  
  this.userdetails2.state=this.addContactInfoForm.value.state;  
  this.userdetails2.city=this.addContactInfoForm.value.city;  
  this.userdetails2.pincode=this.addContactInfoForm.value.pincode;  
  this.userdetails2.nationality=this.addContactInfoForm.value.nationality;  
    
  console.log(this.userdetails2);
    
  this.save2();  
} 


save2() {  
  this.userDetails.updateUserDetails(this.userdetails2)  
    .subscribe(data => console.log(data));  
}



// **********************************************************************************
userdetails3 = new UserDetails();

addDetailsInfoForm = this.fb.group({
  
  academicCertification:[],
  skillSet:[],
  jobPreference:[],
  achievements:[],
 });


 userDetailsInfo(){  
  console.log(this.addDetailsInfoForm);
   
  this.userdetails3.academicCertification=this.addDetailsInfoForm.value.academicCertification;  
  this.userdetails3.skillSet=this.addDetailsInfoForm.value.skillSet;
  this.userdetails3.jobPreference=this.addDetailsInfoForm.value.jobPreference; 
  this.userdetails3.achievements=this.addDetailsInfoForm.value.achievements;
  console.log(this.achievementOf);
     
  console.log(this.userdetails3);
    
  this.save3();  
} 


save3() {  
  this.userDetails.updateUserDetails(this.userdetails3)  
    .subscribe(data => console.log(data));  
}




}
