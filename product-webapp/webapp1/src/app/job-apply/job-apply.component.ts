import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-job-apply',
  templateUrl: './job-apply.component.html',
  styleUrls: ['./job-apply.component.css']
})
export class JobApplyComponent implements OnInit {

  constructor() { }

  ngOnInit(): void {
  }

  company(){
    let div = document.getElementsByClassName('b1') as HTMLCollectionOf<HTMLElement>;
    div[0].style.backgroundColor='#2D9BF0';
   }

   job(){
    let div = document.getElementsByClassName('b2') as HTMLCollectionOf<HTMLElement>;
    div[0].style.backgroundColor='#12CDD4';
   }
   requirements(){
    let div = document.getElementsByClassName('b3') as HTMLCollectionOf<HTMLElement>;
    div[0].style.backgroundColor= '#8FD14F';     
   }
   finish(){
     console.log("finish");
    let div = document.getElementsByClassName('b4') as HTMLCollectionOf<HTMLElement>;
    div[0].style.backgroundColor='#CEE741';
   }

}
