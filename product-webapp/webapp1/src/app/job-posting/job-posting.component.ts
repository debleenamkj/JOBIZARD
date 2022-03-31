import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { jobPost } from '../model/jobPost';
import {MatChipInputEvent} from '@angular/material/chips';
import {COMMA, ENTER} from '@angular/cdk/keycodes';

export interface Fruit {
  name: string;
}

@Component({
  selector: 'app-job-posting',
  templateUrl: './job-posting.component.html',
  styleUrls: ['./job-posting.component.css']
})
export class JobPostingComponent implements OnInit {

  constructor(private fb:FormBuilder) { }

  post=new jobPost();
  companyDetailsError="";


  ngOnInit(): void {
  }

  isLinear = false;
  isEditable = false;

  date = new Date();

  skills:any=[];
  skill:string="";
  companyForm = this.fb.group({
    companyName:['', Validators.required],
    companyUrl:"",
    companyEmail:['', Validators.required],
    companyLogo:"",
    industryType:['', Validators.required]
   });

   jobForm = this.fb.group({
    jobTitle:['', Validators.required],
    jobDescription:['', Validators.required],
    location:['', Validators.required],
    salary:"",
    lastDate:['', Validators.required],
   });

   requirementsForm = this.fb.group({
    education:['', Validators.required],
    experience:['', Validators.required],
    skills:['', Validators.required],
   });
  
   upimage:any;
onFileChanged(event: any) {
  const files = event.target.files[0];
//  this.upimage =   URL.createObjectURL(event.target.files[0]);
  // if (files.length === 0)
  //     return;

  // const mimeType = files[0].type;
  // if (mimeType.match(/image\/*/) == null) {
  //    // this.message = "Only images are supported.";
  //     return;
  // }

  const reader = new FileReader();
  //this.post.companyLogo = files;
  reader.readAsDataURL(event.target.files[0]); 
  reader.onload = (_event) => { 
      this.upimage = reader.result; 
  }
}
  preview(){
    let div = document.getElementsByClassName('details') as HTMLCollectionOf<HTMLElement>;
    div[0].style.display='block';
   // console.log(this.post.companyLogo);
    this.post.companyName=this.companyForm.controls['companyName'].value;
    this.post.companyEmail=this.companyForm.controls['companyEmail'].value;
    this.post.companyUrl=this.companyForm.controls['companyUrl'].value;
    this.post.industryType=this.companyForm.controls['industryType'].value;
    this.post.companyLogo=this.upimage;
    console.log(this.post);
    console.log(this.date);

    this.post.eduation= this.requirementsForm.controls['education'].value;
    this.post.experience= this.requirementsForm.controls['experience'].value;
    //this.post.skills= this.fruits.toString;
   this.skills.clear();
   console.log("---------")
    this.fruits.forEach(element => {
    //  this.skills.push(element.name);
    console.log(element.name);
      this.skill = this.skill + element.name + " ";
    });
    
    console.log(this.skill);
  }

  preview1(date : any){
    this.post.jobRole=this.jobForm.controls['jobTitle'].value;
    this.post.jobDescription=this.jobForm.controls['jobDescription'].value;
    this.post.location=this.jobForm.controls['location'].value;
    this.post.lastDate=date;
    this.post.salary=this.jobForm.controls['salary'].value;
  }
  company(){ 
  
    this.companyDetailsError="";
    console.log(this.companyForm);
    // let div = document.getElementsByClassName('b1') as HTMLCollectionOf<HTMLElement>;
    // div[0].style.backgroundColor='yellow';
   }

   job(){
    let div = document.getElementsByClassName('b2') as HTMLCollectionOf<HTMLElement>;
    div[0].style.backgroundColor='#FAC710';
   }
   requirements(){
    let div = document.getElementsByClassName('b3') as HTMLCollectionOf<HTMLElement>;
    div[0].style.backgroundColor='#CEE741';
   }
   finish(){
     console.log("finish");
    let div = document.getElementsByClassName('finish') as HTMLCollectionOf<HTMLElement>;
    div[0].style.display='block';
   }

  addOnBlur = true;
  readonly separatorKeysCodes = [ENTER, COMMA] as const;
  fruits: Fruit[] = [{name: 'skills'}];

  add(event: MatChipInputEvent): void {
    const value = (event.value || '').trim();

    // Add our fruit
    if (value) {
      this.fruits.push({name: value});
    }

    // Clear the input value
    event.chipInput!.clear();
  }

  remove(fruit: Fruit): void {
    const index = this.fruits.indexOf(fruit);

    if (index >= 0) {
      this.fruits.splice(index, 1);
    }
  }

  skillslist(skills : string){
    this.skills.push(skills);
  }
}





