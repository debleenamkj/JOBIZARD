import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-skilltest',
  templateUrl: './skilltest.component.html',
  styleUrls: ['./skilltest.component.css']
})
export class SkilltestComponent implements OnInit {

  constructor() { }

  // counter=90;
  ngOnInit(): void {
  this.startTimer();
//   let doc = document.getElementsByClassName("timer") as HTMLCollectionOf<HTMLElement>;
//   doc[0].innerHTML="helloo";
//   doc[0].innerHTML = `
// <div class="base-timer">
//   <svg class="base-timer__svg" viewBox="0 0 100 100" xmlns="http://www.w3.org/2000/svg">
//     <g class="base-timer__circle">
//       <circle class="base-timer__path-elapsed" cx="50" cy="50" r="45" />
//     </g>
//   </svg>
//   <span>
//     <!-- Remaining time label -->
//   </span>
// </div>
// `;
  }

  counter = { "min": 15, "sec": 0 };

  startTimer() {
    // this.counter = { min: 15, sec: 0 } // choose whatever you want
    let intervalId = setInterval(() => {
      if (this.counter.sec - 1 == -1) {
        this.counter.min -= 1;
        this.counter.sec = 59
      } 
      else this.counter.sec -= 1
      if (this.counter.min === 0 && this.counter.sec == 0) clearInterval(intervalId)
    }, 1000)
  }
  

}
