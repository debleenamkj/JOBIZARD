// import { Component, OnInit } from '@angular/core';


// import {COMMA, ENTER} from '@angular/cdk/keycodes';
// import {MatChipInputEvent} from '@angular/material/chips';
// import { UserDetails } from '../model/user-details';
// // import { FormBuilder, FormGroup } from '@angular/forms';
// import { MatDialog, MatDialogConfig } from '@angular/material/dialog';
// import { UserDetailsService } from '../service/user-details.service';
// import { AddSkill } from '../model/add-skill';
// import {FormBuilder, FormControl,Validators} from '@angular/forms';  




// @Component({
//   selector: 'app-add-skill',
//   templateUrl: './add-skill.component.html',
//   styleUrls: ['./add-skill.component.css']
// })
// export class AddSkillComponent implements OnInit {

//   constructor( private userDetails:UserDetailsService,private fb:FormBuilder) { }

//   addskill1 = new AddSkill();

//   ngOnInit(): void {
//   }
//   // addSkillForm = new FormGroup(
//   //   course:new FormControl(),
//   //   stream:new FormControl(),
//   //   university:new FormControl(),
//   //   courseType:new FormControl(),
//   //   passOutYear:new FormControl(),
//   //   percentageOrCgpa:new FormControl()
//   // );
//   addSkillForm = this.fb.group({
//     course:['', Validators.required],
//     stream:"",
//     university:"",
//     courseType:"",
//     passOutYear:"",
//     percentageOrCgpa:""
//    });

//     addskill(){  
//     console.log(this.addSkillForm);
//     this.addskill1.course=this.addSkillForm.value.course;  
//     this.addskill1.stream=this.addSkillForm.value.stream; 
//     this.addskill1.university=this.addSkillForm.value.university;  
//     this.addskill1.courseType=this.addSkillForm.value.courseType;  
//     this.addskill1.passOutYear=this.addSkillForm.value.passOutYear;
//     this.addskill1.percentageOrCgpa=this.addSkillForm.value.percentageOrCgpa;    
//     console.log(this.addskill1);
      
//     this.save();  
//   } 


//   save() {  
//     this.userDetails.updateUserDetails(this.addskill1)  
//       .subscribe(data => console.log(data));  
//   }  
// }
