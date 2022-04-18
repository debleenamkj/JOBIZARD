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
      "emailId":"s4@gmail.com",
      "skillsRequired":["angular,java"],
      "education":"b.tech"
    
      
    }
    

    this.service.getEmail(value).subscribe(data=>{console.log(data)})
    console.log("--" +JSON.stringify(this.emailList)); 
    
  }
  sendEmail(){
    
  }

}