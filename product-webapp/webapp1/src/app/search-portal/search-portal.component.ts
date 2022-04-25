import { Component, Input, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { EmailRequest } from '../model/email-request';
import { jobDetails } from '../model/jobDetails';
import { RecruiterDetails } from '../model/recruiter-details';
import { Search } from '../search';
import { SearchService } from '../service/search.service';
import { ChatroomService } from '../service/chatroom.service';

@Component({
  selector: 'app-search-portal',
  templateUrl: './search-portal.component.html',
  styleUrls: ['./search-portal.component.css']
})

export class SearchPortalComponent implements OnInit {
  emailList: Observable<Search[]>;
  recruiterData = new RecruiterDetails();
  gridColumns = 3;
  skillsFilter: string[] = []
  educationStringFilter: string = ""
  filterSkill: searchObject[] = []
  jobSeeker: Array<jobDetails>[] = []
  constructor(private fb: FormBuilder, private service: SearchService, private alert: MatSnackBar, private chat: ChatroomService, private router: Router, private jobSeekerLanding: SearchService) {
    console.log("JobSeeker List", this.jobSeekersList);
    console.log("Skills", this.skills);

  }
  matchedEmailList: string[];
  jobSeekersList: any[] = [];
  jobSeekersListSlice: any[] = [];
  skills: FormGroup = this.fb.group({
    s1: "", s2: "", s3: ""
  })
  ngOnInit(): void {
    // this.getEmailList()
    this.jobSeekerLanding.getRecruiter().subscribe((d: RecruiterDetails) => {
      this.recruiterData = d;
      if (this.recruiterData.skillsRequired) {
        this.recruiterData.skillsRequired.forEach(skill => {
          this.filterSkill.push({ isExist: true, skillname: skill.toLowerCase() })
        })
        localStorage.setItem('companyName', this.recruiterData.companyName)
        this.skillsFilter = this.recruiterData.skillsRequired
        this.educationStringFilter = this.recruiterData.educationRequired
        this.getJobSeekersList(new JobDetails(this.recruiterData.emailId, this.recruiterData.skillsRequired, this.recruiterData.educationRequired))
        console.log("Details of the Recruiter");
      }
    });
  }
  trial() {
    this.recruiterData.skillsRequired
  }
  pageChange(event: any) {
    let start = event.pageSize * event.pageIndex;
    this.jobSeekersListSlice = this.jobSeekersList.slice(start, start + 6)

  }
  skillFilter(skill: string, s1: boolean) {
    skill = skill.toLowerCase()
    console.log("000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000")
    if (s1) {
      this.filterSkill.forEach(e => {
        if (e.skillname == skill) {
          e.isExist = true;
        }
      })

    }
    else {
      this.filterSkill.forEach(e => {
        if (e.skillname == skill) {
          e.isExist = false;
        }

      })
    }
    console.log(this.skillsFilter);

    this.getJobSeekersList(new JobDetails(this.recruiterData.emailId, this.skillsFilter, this.educationStringFilter))

  }
  educationFilter(education: string, s2: boolean) {

    if (s2) {
      this.educationStringFilter = education.toLowerCase()
    }
    else {
      this.educationStringFilter = ""
    }
    this.getJobSeekersList(new JobDetails(this.recruiterData.emailId, this.skillsFilter, this.educationStringFilter))
  }
  getEmailList() {

    let details = new JobDetails(this.recruiterData.emailId, this.recruiterData.skillsRequired, this.recruiterData.educationRequired);
    this.getJobSeekersList(details)
    console.log("-------------------------job seeker");
    console.log(this.jobSeekersList)
    this.display()

  }

  getImages(jobSeeker: any) {
    jobSeeker.forEach((d: any) => {
      d.seekerProfileImage = 'data:image/jpeg;base64,' + d.jobSeekerImage;
    });
  }
  getJobSeekersList(details: JobDetails) {
    details = new JobDetails(this.recruiterData.emailId, [], this.educationStringFilter)

    this.filterSkill.forEach(e => {
      if (e.isExist) {
        details.skillsRequired.push(e.skillname)
      }
    })
    details.education = details.education.toLowerCase()
    details.skillsRequired = details.skillsRequired.map(d => { return d.toLowerCase() })
    // if(details.education==""&&details.skillsRequired==[]){

    // }
    console.log("**************************************************************");
    console.log(details)
    this.service.getEmail(details).subscribe((data: string[]) => {
      this.matchedEmailList = data;
      if (data == null) {
        this.alert.open("No Recommendation Available", 'close', {
          duration: 5000
        })
      }
      else {
        this.jobSeekersList = []
        let i = 0;

        this.matchedEmailList.forEach((element: any) => {
          console.log(element)
          this.service.getJobSeeker(element).subscribe({
            next: data => {
              console.log(data)
              this.jobSeekersList.push(data);
              this.getImages(this.jobSeekersList);
              if (this.jobSeekersList.length != 0) {
                this.jobSeekersListSlice = this.jobSeekersList.slice(0, 6)
              }
              else {
                this.jobSeekersListSlice = []
              }
            }, error: errorresponse => {
              console.log(++i + " " + errorresponse.message)
            }
          }
          )

        });
      }
      console.log("************")
      console.log(this.matchedEmailList)
      console.log(this.matchedEmailList.length)

    })

  }

  display() {

    console.log(this.jobSeekersList)
    this.jobSeekersList.forEach(element => {
      console.log("helloo")
      const img = 'data:image/jpeg;base64,' + element.jobSeekerImage
      console.log(img)
      element.jobSeekerImage = img;
    });
    //for chat service
  }
  onClick(recipientEmail: any, recipientName: any) {
    this.chat.senderId = localStorage.getItem('loginId')
    this.chat.senderName = localStorage.getItem('companyName')
    this.chat.recipientId = recipientEmail;
    this.chat.recipientName = recipientName;
    this.router.navigate(['/navbar/chatroom']);
  }
  //for emailservice
  sendEmail(jobseeker: any) {

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
type searchObject = {
  skillname?: string;
  isExist?: boolean

}



