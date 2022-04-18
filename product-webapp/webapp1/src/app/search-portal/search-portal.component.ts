import { Component, Input, OnInit } from '@angular/core';
import {FormBuilder, FormGroup} from '@angular/forms';
import { Observable } from 'rxjs';
import { Search } from '../search';
import { SearchService } from '../search.service';

@Component({
  selector: 'app-search-portal',
  templateUrl: './search-portal.component.html',
  styleUrls: ['./search-portal.component.css']
})

export class SearchPortalComponent implements OnInit {
  emailList: Observable<Search[]>;
  gridColumns = 3;
  skills:FormGroup;
  // jobSeeker: Array<Jobseeker>=[];
  // jobSeekerSlice: Array<JobSeekerLanding>=[];
  // images:any[]=[];
  
  constructor(fb:FormBuilder, private service:SearchService) {
    this.skills=fb.group({
      java:false,
      spring:false,
      angular:false
    })
   }
   matchedEmailList:any;
   jobSeekersList:any[]=[];
   buttonTitle:string = "Hide";
   visible:boolean = true;
   showhideUtility(){
    this.visible = this.visible?false:true;
    this.buttonTitle = this.visible?"Hide":"Show";
   }
  ngOnInit(): void {
    this.getEmailList();
  }
  getEmailList() {
    let value={
    "emailId":"rkgg@gmail.com",
    "skillsRequired":["java","angular"],
    "education":"B.tech"
    }
    this.matchedEmailList=["vishnu2@gmail.com","vishnu21@gmail.com","rs@gmail.com","vishnu23@gmail.com"]
    
    this.service.getEmail(value).subscribe(data=>{
      // this.matchedEmailList=data;
      console.log(this.matchedEmailList)
      // console.log(this.matchedEmailList.length)
        this.matchedEmailList.forEach((element: any)=> {
          this.service.getJobSeeker(element).subscribe( data =>{
            console.log(data)
            this.jobSeekersList.push(data);
            this.getImages(this.jobSeekersList);
            
          })
        
      });
    })
    console.log("-------------------------job seeker");
    console.log(this.jobSeekersList)
    this.display()
    // console.log("--" +JSON.stringify(this.emailList)); 
  }

  getImages(jobSeeker: any){
    // jobSeeker.forEach(d => {
    //   d.seekerProfileImage = 'data:image/jpeg;base64,' + d.jobSeekerImage;
    // });
  }

  display(){
      console.log("-------------------------display----------------")
      console.log(this.jobSeekersList)
      this.jobSeekersList.forEach(element => {
        console.log("helloo")
        const img = 'data:image/jpeg;base64,'+element.jobSeekerImage
        console.log(img)
        element.jobSeekerImage=img;
      });
      // console.log(this.jobSeekersList.length)
      // for (let index = 0; index < this.jobSeekersList.length; index++) {
      //   console.log("helloo")
      //   const img = 'data:image/jpeg;base64,'+this.jobSeekersList[index].jobSeekerImage
      //   console.log(img)
      //   this.jobSeekersList[index].jobSeekerImage=img;
      // }
  }
  sendEmail(){
    
  }

}