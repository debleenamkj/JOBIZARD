import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-job-posting',
  templateUrl: './job-posting.component.html',
  styleUrls: ['./job-posting.component.css']
})
export class JobPostingComponent implements OnInit {

  constructor() { }

  ngOnInit(): void {
  }
  company(){ 
    let div = document.getElementsByClassName('b1') as HTMLCollectionOf<HTMLElement>;
    div[0].style.backgroundColor='yellow';
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
    let div = document.getElementsByClassName('b4') as HTMLCollectionOf<HTMLElement>;
    div[0].style.backgroundColor='#8FD14F';
   }


}
