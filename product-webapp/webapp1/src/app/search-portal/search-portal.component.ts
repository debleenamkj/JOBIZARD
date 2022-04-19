import { Component, Input, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { EmailRequest } from '../model/email-request';
import { Search } from '../search';
import { SearchService } from '../search.service';
import { ChatroomService } from '../service/chatroom.service';

@Component({
  selector: 'app-search-portal',
  templateUrl: './search-portal.component.html',
  styleUrls: ['./search-portal.component.css']
})

export class SearchPortalComponent implements OnInit {
  emailList: Observable<Search[]>;
  gridColumns = 3;
  // skills:FormGroup;
  // education:FormGroup;
  // jobSeeker: Array<Jobseeker>=[];
  // jobSeekerSlice: Array<JobSeekerLanding>=[];
  // images:any[]=[];

  constructor(private fb: FormBuilder, private service: SearchService, private alert: MatSnackBar,private chat:ChatroomService,private router:Router) {

  }
  matchedEmailList: string[];
  jobSeekersList: any[] = [];
  buttonTitle: string = "Hide";
  visible: boolean = true;
  skills: FormGroup = this.fb.group({
    java: "",
    spring: "",
    angular: ""
  })
  educationgroup: FormGroup = this.fb.group({
    education: ""
  })
  showhideUtility() {
    this.visible = this.visible ? false : true;
    this.buttonTitle = this.visible ? "Hide" : "Show";
  }
  ngOnInit(): void {
    // this.getEmailList();
  }
  getEmailList() {
    let value = {
      "emailId": "s4@gmail.com",
      "skillsRequired": ["angular,java"],
      "education": "b.tech"
    }
    let details = new JobDetails(" ", [], "");
    // details.skillsRequired=[];
    localStorage.setItem('loginId', "s4@gmail.com")
    details.emailId = localStorage.getItem('loginId');
    let v1 = this.skills.value;
    if (v1.java == true) {
      details.skillsRequired.push("java")
    }
    if (v1.angular == true) {
      details.skillsRequired.push("angular")
    }
    if (v1.spring == true) {
      details.skillsRequired.push("spring")
    }

    details.education = this.educationgroup.get("education").value;
    console.log("************")
    console.log(details)
    //  this.matchedEmailList=["vishnu2@gmail.com","vishnu21@gmail.com","rs@gmail.com","vishnu23@gmail.com"]

    this.service.getEmail(details).subscribe((data: string[]) => {
      this.matchedEmailList = data;
      console.log("************")
      console.log(this.matchedEmailList)
      console.log(this.matchedEmailList.length)
      this.matchedEmailList.forEach((element: any) => {
        this.service.getJobSeeker(element).subscribe(data => {
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

  getImages(jobSeeker: any) {
    jobSeeker.forEach((d: any) => {
      d.seekerProfileImage = 'data:image/jpeg;base64,' + d.jobSeekerImage;
    });
  }

  display() {
    console.log("-------------------------display----------------")
    console.log(this.jobSeekersList)
    this.jobSeekersList.forEach(element => {
      console.log("helloo")
      const img = 'data:image/jpeg;base64,' + element.jobSeekerImage
      console.log(img)
      element.jobSeekerImage = img;
    });
    // console.log(this.jobSeekersList.length)
    // for (let index = 0; index < this.jobSeekersList.length; index++) {
    //   console.log("helloo")
    //   const img = 'data:image/jpeg;base64,'+this.jobSeekersList[index].jobSeekerImage
    //   console.log(img)
    //   this.jobSeekersList[index].jobSeekerImage=img;
    // }
  }
  onClick(recipientEmail:any,recipientName:any){
    this.chat.senderId = localStorage.getItem('loginId')
    this.chat.senderName = localStorage.getItem('companyName')
    this.chat.recipientId = recipientEmail;
    this.chat.recipientName = recipientName;
    this.router.navigate(['/navbar/chatroom']);
  }
  sendEmail(jobseeker: any) {
    localStorage.setItem('companyName', "Netflix")
    let details = new EmailRequest(jobseeker.emailId, localStorage.getItem('companyName'))
    let message: string = ""
    this.service.sendEmail(details).subscribe({
      next: d => {
        console.log(d)
        message = d.message
        this.alert.open(message, 'close', {
          duration: 5000
        })
      },
      error: er => {
        console.log(er)
        message = er.error.text
        this.alert.open(message, 'close', {
          duration: 5000
        })
      }
    })
  }
}




export class JobDetails {
  emailId: string;
  skillsRequired: string[];
  education: string;
  
  constructor(emailId: string,
    skillsRequired: string[],
    education: string) {
    this.emailId = emailId
    this.skillsRequired = skillsRequired
    this.education = education
    
  }
 

}

