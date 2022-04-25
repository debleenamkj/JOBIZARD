import { Component, OnInit } from '@angular/core';
import { RecruiterlandingService } from '../service/recruiterlanding.service';

@Component({
  selector: 'app-selected-candidate',
  templateUrl: './selected-candidate.component.html',
  styleUrls: ['./selected-candidate.component.css']
})
export class SelectedCandidateComponent implements OnInit {

  constructor(private recruiterService:RecruiterlandingService) {
    
   }

  ngOnInit(): void {
    this.getSelectedJobseekers();
  }


  selectedJobseekers:any[]=[]
  selectedJobseekersSlice:any[]=[]
  
  pageChange(event:any){
    let start = event.pageSize*event.pageIndex;
    this.selectedJobseekersSlice = this.selectedJobseekersSlice.slice(start,start+event.pageSize)
  }
  
  getSelectedJobseekers(){
    this.recruiterService.getRecruiterProfile().subscribe({
      next: (response:any)=>{
        if(response){
          if(response.selectedJobseekers){
          this.selectedJobseekers = response.selectedJobSeekers
          this.selectedJobseekersSlice=this.selectedJobseekers.slice(0,10)
          }else{
            console.log("No Selected Jobsekers")
          }
        }
        else{
          console.log("response didn't receive")
        }
      },
      error: (errorResponse:any)=>{
        console.log(errorResponse.message)
      }
    })
  }
  

}

// neo4JMatchedJobseeker=[1,2,3,4,5];
//   selected=[3,5,8,9,10];
//   wishlist:number[]=[];
//   Recruiter{
//     List<Selected> selected;
//   }

//   Selected{               
//     String wishListName;
//     List<String> selected;
//   }

