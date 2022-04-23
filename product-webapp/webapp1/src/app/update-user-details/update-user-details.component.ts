import { Component, OnInit } from '@angular/core';
import {COMMA, ENTER} from '@angular/cdk/keycodes';
import {MatChipInputEvent} from '@angular/material/chips';
import { UserDetails } from '../model/user-details';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatDialog, MatDialogConfig } from '@angular/material/dialog';

import { AddSkillsComponent } from '../add-skills/add-skills.component';
import { UserDetailsService } from '../service/user-details.service';
import { details } from '../model/details';
import { address } from '../model/address';
import { progress } from '../model/progress';

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

     seekerDetails:any;
     email:string="";
progress=new progress();

  ngOnInit(): void {
      this.email = localStorage.getItem('loginId');
      console.log(this.email)
      this.updateDetails = this.fb.group({})
      this.userDetails.getJobSeeker(this.email).subscribe( data =>{
        console.log(data)
        this.seekerDetails = data;
        this.addPersonalInfoForm.patchValue({
          firstName:this.seekerDetails.firstName,
          lastName:this.seekerDetails.lastName,
          gender:this.seekerDetails.gender,
          dateOfBirth:this.seekerDetails.dateOfBirth,
          objective:this.seekerDetails.objective,
        })
        console.log("---------personal form ------------")
        console.log(this.addPersonalInfoForm);
        
        this.addContactInfoForm.patchValue({
          mobileNumber:this.seekerDetails.mobileNumber,
          lane:this.seekerDetails.address.lane,
          state:this.seekerDetails.address.state,
          city:this.seekerDetails.address.city,
          pincode:this.seekerDetails.address.pincode,
          nationality:this.seekerDetails.address.nationality,
        })
      
        this.seekerDetails.additionalDetails.academicsCertification.forEach((element: any)  => {
          this.academicsCertifications.push({name:element})
        })
        this.seekerDetails.additionalDetails.skillSet.forEach((element: any) => {
          this.skillSets.push({name:element.skillName})
        });
        this.seekerDetails.additionalDetails.jobPreferences.forEach((element: any) => {
          this.jobPreferences.push({name:element})
        });
        this.seekerDetails.additionalDetails.achievements.forEach((element: any) => {
          this.achievementOf.push({name:element})
        });
        
        let jobPreference = new Array();
        this.jobPreferences.forEach(element =>{
          jobPreference.push(element.name);
        });
        console.log("---------jobPreference-------- ");
        console.log(this.jobPreferences);
        console.log(jobPreference);
    
    
    
        let achievement = new Array();
        this.achievementOf.forEach(element => {
          achievement.push(element.name);
        });


        this.addDetailsInfoForm.patchValue({
          academicCertification:[],
          skillSet:[],
          jobPreference:[],
          achievements:[],
        })
        this.updateDetails.emailId=this.seekerDetails.emailId;
        this.userdetails1.firstName=this.seekerDetails.firstName;  
        this.userdetails1.lastName=this.seekerDetails.lastName; 
        this.userdetails1.gender=this.seekerDetails.gender;  
        this.userdetails1.dateOfBirth=this.seekerDetails.dateOfBirth;  
        // this.userdetails1.mobileNumber=this.addPersonalInfoForm.value.mobileNumber;
        this.userdetails1.objective=this.seekerDetails.objective;
      
        this.userdetails2.mobileNumber=this.seekerDetails.mobileNumber;
           
        // this.address.lane=this.seekerDetails.address.lane;
        console.log(this.addContactInfoForm.value.state);
          
        // this.address.state=this.seekerDetails.address.state;  
        // this.address.city=this.seekerDetails.address.city;  
        // this.address.pincode=this.seekerDetails.address.pincode;  
        // this.address.nationality=this.seekerDetails.address.nationality;
        
        this.userdetails1.address=this.seekerDetails.address


        // this.addDetailsInfoForm.patchValue({
        //   academicCertification:[],
        //   skillSet:[],
        //   jobPreference:[],
        //   achievements:[],
        // })
      })
  }

  // ************************** Starting of Chips components code *************************************

  details=new details();
  addOnBlur = true;
  readonly separatorKeysCodes = [ENTER, COMMA] as const;
  academicsCertifications: AcademicsCertification[] = [];

  userDetailsInfo(){ 
  this.progress.personalInfo=25;
  this.progress.contactInfo=25;
  this.progress.additionalInfo=25;

  console.log(this.addPersonalInfoForm);
  this.userdetails1.emailId=this.email
  console.log(this.userdetails1.emailId)
  this.userdetails1.firstName=this.addPersonalInfoForm.value.firstName;
  // console.log("----firstName ------")
  // console.log(this.userdetails1.firstName);
   
  this.userdetails1.lastName=this.addPersonalInfoForm.value.lastName; 
  this.userdetails1.gender=this.addPersonalInfoForm.value.gender;  
  this.userdetails1.dateOfBirth=this.addPersonalInfoForm.value.dateOfBirth;  
  // this.userdetails1.mobileNumber=this.addPersonalInfoForm.value.mobileNumber;
  this.userdetails1.objective=this.addPersonalInfoForm.value.objective;

  this.userdetails1.mobileNumber=this.addContactInfoForm.value.mobileNumber;
     
  this.address.lane=this.addContactInfoForm.value.lane;
  console.log(this.addContactInfoForm.value.state);
    
  this.address.state=this.addContactInfoForm.value.state;  
  this.address.city=this.addContactInfoForm.value.city;  
  console.log("pincode---------")
  console.log(this.addContactInfoForm.value.pincode);

  this.address.pincode=this.addContactInfoForm.value.pincode;  
  console.log(this.address.pincode);
  this.address.nationality=this.addContactInfoForm.value.nationality;
  
  this.userdetails1.address=this.address
  console.log(this.userdetails1);
    console.log(this.addDetailsInfoForm);

    let academic = new Array();
    this.academicsCertifications.forEach(element => {
      academic.push(element.name);
    });
    // console.log("---------Academic-------- ");
    // console.log(this.academicsCertifications);
    // console.log(academic);
    
    let skill = new Array();
    this.skillSets.forEach(element => {
      let skill1 = {isVerified:false,skillName:element.name.toLowerCase(),level:"",percentage:""}
      console.log(skill1);
      skill.push(skill1);
    });
    // console.log("---------skills-------- ");
    // console.log(this.skillSets);
    // console.log(skill);

    let jobPreference = new Array();
    this.jobPreferences.forEach(element =>{
      jobPreference.push(element.name);
    });
    // console.log("---------jobPreference-------- ");
    // console.log(this.jobPreferences);
    // console.log(jobPreference);



    let achievement = new Array();
    this.achievementOf.forEach(element => {
      achievement.push(element.name);
    });
    // console.log("---------achievement-------- ");
    // console.log(this.achievementOf);
    // console.log(achievement);
    this.details.academicsCertification=academic;  
    console.log(academic)
    console.log(this.details.academicsCertification);
    this.details.skillSet=skill;
    this.details.jobPreferences=jobPreference; 
    this.details.achievements=achievement;
    this.userdetails1.additionalDetails=this.details;

    this.userdetails1.seekerProgress=this.progress;
    console.log(this.userdetails1);
    this.userDetails.updateUserWithoutImage(this.email,this.userdetails1).subscribe( data =>{
      console.log(data);
    })
    console.log(this.achievementOf);
       
    console.log(this.userdetails3);
      
    this.save3();  
  } 

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
file:any
upimage:any
onFileChanged(event: any) {
  console.log("onchange");

  this.file = event.target.files[0];
  console.log(this.file);

  const reader = new FileReader();
  reader.readAsDataURL(event.target.files[0]); 
  reader.onload = (_event) => { 
    console.log(reader.result);
      this.upimage = reader.result;
      console.log(this.upimage);
  }

}

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
  this.progress.personalInfo=25;
  this.userdetails1.emailId=this.email
  this.userdetails1.firstName=this.addPersonalInfoForm.value.firstName;  
  this.userdetails1.lastName=this.addPersonalInfoForm.value.lastName; 
  this.userdetails1.gender=this.addPersonalInfoForm.value.gender;  
  this.userdetails1.dateOfBirth=this.addPersonalInfoForm.value.dateOfBirth;  
  // this.userdetails1.mobileNumber=this.addPersonalInfoForm.value.mobileNumber;
  this.userdetails1.objective=this.addPersonalInfoForm.value.objective;
  this.userdetails1.seekerProgress=this.progress
  // this.userdetails1.profilePicture=this.addPersonalInfoForm.value.profilePicture;
  
  let uploadData = new FormData();
  uploadData.append('jobSeeker1',JSON.stringify(this.userdetails1));
  uploadData.append('file',this.file);

  this.email = localStorage.getItem('loginId');
  console.log(this.email);
    
  this.userDetails.updateUserWithImage(this.email,uploadData).subscribe( data =>{
    console.log(data);
  })
  
  console.log(this.userdetails1);
    
  this.save();  
} 


save() {  
  // this.userDetails.updateUserDetails(this.userdetails1)  
  //   .subscribe(data => console.log(data));  
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

 address=new address();

 userContactdetails(){  
   console.log("in user contact details---------------");
   this.progress.personalInfo=25;
   this.progress.contactInfo=25;
  console.log(this.addPersonalInfoForm);
  this.userdetails1.emailId=this.email
  console.log(this.userdetails1.emailId)
  
  this.userdetails1.firstName=this.addPersonalInfoForm.value.firstName;
  // console.log("----firstName ------")
  // console.log(this.userdetails1.firstName);
   
  this.userdetails1.lastName=this.addPersonalInfoForm.value.lastName; 
  this.userdetails1.gender=this.addPersonalInfoForm.value.gender;  
  this.userdetails1.dateOfBirth=this.addPersonalInfoForm.value.dateOfBirth;  
  // this.userdetails1.mobileNumber=this.addPersonalInfoForm.value.mobileNumber;
  this.userdetails1.objective=this.addPersonalInfoForm.value.objective;

  this.userdetails1.mobileNumber=this.addContactInfoForm.value.mobileNumber;
     
  this.address.lane=this.addContactInfoForm.value.lane;
  console.log(this.addContactInfoForm.value.state);
    
  this.address.state=this.addContactInfoForm.value.state;  
  this.address.city=this.addContactInfoForm.value.city;  
  console.log("pincode---------")
  console.log(this.addContactInfoForm.value.pincode);

  this.address.pincode=this.addContactInfoForm.value.pincode;  
  console.log(this.address.pincode);
  this.address.nationality=this.addContactInfoForm.value.nationality;
  
  this.userdetails1.address=this.address
  this.userdetails1.seekerProgress=this.progress
  
  console.log(this.userdetails1);
  
  this.userDetails.updateUserWithoutImage(this.email,this.userdetails1).subscribe( data =>{
    console.log(data);
  })
  console.log(this.userdetails2);
    
  this.save2();  
} 


save2() {  
  // this.userDetails.updateUserDetails(this.userdetails2)  
  //   .subscribe(data => console.log(data));  
}



// **********************************************************************************
userdetails3 = new UserDetails();

addDetailsInfoForm = this.fb.group({
  
  academicCertification:[],
  skillSet:[],
  jobPreference:[],
  achievements:[],
 });





save3() {  
  // this.userDetails.updateUserDetails(this.userdetails3)  
  //   .subscribe(data => console.log(data));  
}




}
