import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { jobPosting } from '../model/jobPosting';
import {MatChipInputEvent} from '@angular/material/chips';
import {COMMA, ENTER} from '@angular/cdk/keycodes';
import { jobDetails } from '../model/jobDetails';
import { HttpClient } from '@angular/common/http';
import { PostJobServiceService } from '../service/post-job-service.service';



export interface Fruit {
  name: string;
}

@Component({
  selector: 'app-job-posting',
  templateUrl: './job-posting.component.html',
  styleUrls: ['./job-posting.component.css']
})
export class JobPostingComponent implements OnInit {

  constructor(private fb:FormBuilder,private service:PostJobServiceService) { }

  file:any
  post=new jobPosting();
  details=new jobDetails();
  companyDetailsError:any;
  jobDetailsError:any;
  requirementsError:any;
  companyPresent:string="";
  companyDetails:any=null;

  ngOnInit(): void {
  }

  isLinear = false;

  isEditable = false;
  jobDescription:string="";

  date = new Date();

  skills:any=[];
  skill:string="";

  isPosted(){
    console.log(this.companyPresent);
    let companyForm = document.getElementsByClassName('companyForm') as HTMLCollectionOf<HTMLElement>;
    if(this.companyPresent=='yes'){
      companyForm[0].style.pointerEvents='all'
      companyForm[1].style.pointerEvents='all'
    }
    else{
      companyForm[0].style.pointerEvents='all'
      companyForm[1].style.pointerEvents='all'
      companyForm[2].style.pointerEvents='all'
    }
  }

  companyForm = this.fb.group({
    companyName:['', Validators.required],
    companyUrl:"",
    companyEmail:['', Validators.required],
    companyLogo:"",
    industryType:['', Validators.required]
   });

   jobForm = this.fb.group({
    jobTitle:['', Validators.required],
    //jobDescription:['', Validators.required],
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
  console.log("onchange");

  this.file = event.target.files[0];

  const reader = new FileReader();
  reader.readAsDataURL(event.target.files[0]); 
  reader.onload = (_event) => { 
    console.log(reader.result);
      this.upimage = reader.result; 
      console.log(this.upimage);
  }

}

getCompany(){
  if(this.companyPresent=='yes'){
    this.post.companyName=this.companyForm.controls['companyName'].value;
    this.service.getCompany(this.post.companyName).subscribe(data => {
      console.log(data);
      this.companyDetails = data;
      this.companyForm.patchValue({
      companyUrl:this.companyDetails.companyUrl,
      companyEmail:this.companyDetails.companyEmail,
      companyLogo:this.companyDetails.log,
      industryType:this.companyDetails.industryType,
      });
      this.post.companyUrl = this.companyDetails.companyUrl
      this.post.companyEmail = this.companyDetails.companyEmail
      this.post.industryType = this.companyDetails.industryType
      let img = this.companyDetails.logo;
      this.upimage = 'data:image/jpeg;base64,'+img;
      console.log(img);
      console.log(this.upimage);
    })
  }
 

  console.log(this.companyForm);
  console.log(this.post);
}
  preview(){
    if(this.companyDetails==null)
    {
      this.companyDetailsError='';
      this.companyDetailsError=this.companyForm.controls['status'];
      console.log(this.companyDetailsError);
      this.post.companyName=this.companyForm.controls['companyName'].value;
      this.post.companyEmail=this.companyForm.controls['companyEmail'].value;
      this.post.companyUrl=this.companyForm.controls['companyUrl'].value;
      this.post.industryType=this.companyForm.controls['industryType'].value;
    }
  
    //this.post.companyLogo=this.upimage;
    this.details.educationRequired= this.requirementsForm.controls['education'].value;
    this.details.experienceRequired= this.requirementsForm.controls['experience'].value;
  }

  preview1(){
    console.log("description");
    console.log(this.jobForm.value);
    console.log(this.jobForm.value.lastDate);
    this.details.jobRole=this.jobForm.controls['jobTitle'].value;
    //this.details.jobDescription=this.jobForm.value.jobDescription;
    this.details.jobLocation=this.jobForm.controls['location'].value;
    this.details.salary=this.jobForm.controls['salary'].value;
  }

  preview2(description:any){
    this.details.jobDescription=description;
  }

  previewdate(){
      // this.details.lastDate = date;
      let lastDate=this.jobForm.controls['lastDate'].value;
    this.details.lastDate = lastDate.getFullYear()+'-'+(lastDate.getMonth()+1)+'-'+lastDate.getDate();
  }
  company(){ 
  
    this.companyDetailsError="";
    console.log(this.companyForm);
    // let div = document.getElementsByClassName('b1') as HTMLCollectionOf<HTMLElement>;
    // div[0].style.backgroundColor='yellow';
   }


   finish(){
    let skills = new Array();
    this.fruits.forEach(element => {
      console.log("in for")
      console.log(element.name);
      skills.push(element.name);
    });
    console.log(skills);
    this.details.skillsRequired = skills;
     console.log("finish");
     this.post.jobDetailsList=[this.details];
     console.log(this.post);

     const uploadData = new FormData();
     if(this.companyDetails==''){
       console.log("company details is null")
      uploadData.append('file', this.file);
      uploadData.append('jobs',JSON.stringify(this.post));
      console.log(uploadData.get('file'));
      console.log(uploadData.get('jobs'));
      console.log(uploadData);
      this.service.postJob(uploadData).subscribe(data =>{
        console.log(data)
        let div = document.getElementsByClassName('finish') as HTMLCollectionOf<HTMLElement>;
        div[0].style.display='block';
      }) 
     }
     else if (this.companyDetails!=null){
      console.log("company details is present")
      uploadData.append('jobs',JSON.stringify(this.post));
      this.service.postJob1(uploadData).subscribe(data =>{
        console.log(data)
        let div = document.getElementsByClassName('finish') as HTMLCollectionOf<HTMLElement>;
        div[0].style.display='block';
      }) 
     }
    //  uploadData.append('file', this.file);
    //  uploadData.append('jobs',JSON.stringify(this.post));
    //  console.log(uploadData.get('file'));
    //  console.log(uploadData.get('jobs'));
    //  console.log(uploadData);
    //  this.service.postJob(uploadData).subscribe(data =>{
    //    console.log(data)
    //  })
   
   }

  addOnBlur = true;
  readonly separatorKeysCodes = [ENTER, COMMA] as const;
  fruits: Fruit[] = [];

  add(event: MatChipInputEvent): void {
    const value = (event.value || '').trim();

    if (value) {
      this.fruits.push({name: value});
    }

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





