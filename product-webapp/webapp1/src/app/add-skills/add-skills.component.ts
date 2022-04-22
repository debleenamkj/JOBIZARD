import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { AddSkill } from '../model/add-skill';
import { UserDetailsService } from '../service/user-details.service';

@Component({
  selector: 'app-add-skills',
  templateUrl: './add-skills.component.html',
  styleUrls: ['./add-skills.component.css']
})
export class AddSkillsComponent implements OnInit {

  constructor( private userDetails:UserDetailsService,private fb:FormBuilder) { }

  addskill1 = new AddSkill();

  ngOnInit(): void {
  }

  addSkillForm = this.fb.group({
    course:['', Validators.required],
    stream:"",
    university:"",
    courseType:"",
    passOutYear:"",
    percentageOrCgpa:""
   });



   

   addskill(){  
     let email=localStorage.getItem('loginId');
    console.log(this.addSkillForm);
    this.addskill1.courses=this.addSkillForm.value.course.toLowerCase();  
    this.addskill1.streamOrField=this.addSkillForm.value.stream; 
    this.addskill1.universityOrInstitute=this.addSkillForm.value.university;  
    this.addskill1.courseType=this.addSkillForm.value.courseType;  
    this.addskill1.passingOutYear=this.addSkillForm.value.passOutYear;
    this.addskill1.percentageOrCgpa=this.addSkillForm.value.percentageOrCgpa;    
    console.log(this.addskill1);
      this.userDetails.updateEducation(email,this.addskill1).subscribe(data =>{
        console.log(data)
      })
    this.save();  
  } 


  save() {  
    location.reload();
    // this.userDetails.updateUserDetails(this.addskill1)  
    //   .subscribe(data => console.log(data));  
  }
}
