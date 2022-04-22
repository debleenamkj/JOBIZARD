import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-selected-candidate',
  templateUrl: './selected-candidate.component.html',
  styleUrls: ['./selected-candidate.component.css']
})
export class SelectedCandidateComponent implements OnInit {

  constructor() {
    
   }

  ngOnInit(): void {
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

//   get(){
//     this.neo4JMatchedJobseeker.forEach(p=>{
//       if(this.selected.includes(p)){
//         this.neo4JMatchedJobseeker.splice(this.neo4JMatchedJobseeker.indexOf(p),1);
//         this.wishlist.push(p)
//       }
//     })
//     console.log(this.neo4JMatchedJobseeker);
//     console.log(this.wishlist);
//   }
