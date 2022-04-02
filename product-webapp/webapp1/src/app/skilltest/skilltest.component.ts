import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-skilltest',
  templateUrl: './skilltest.component.html',
  styleUrls: ['./skilltest.component.css']
})
export class SkilltestComponent implements OnInit {

  constructor() { }

  ngOnInit(): void {
  this.startTimer();
  }

  counter : any;

  startTimer() {
    this.counter = { min: 15, sec: "00" } // choose whatever you want
    let intervalId = setInterval(() => {
      if (this.counter.sec - 1 == -1) {
        this.counter.min -= 1;
        this.counter.sec = 59
      } 
      else this.counter.sec -= 1
      
      if(this.counter.sec==0)
      {
        this.counter.sec = "00";
      }
      else if(this.counter.sec<=9){
        this.counter.sec = "0"+this.counter.sec;
      }
      if (this.counter.min === 0 && this.counter.sec == 0) clearInterval(intervalId)

      if(this.counter.min<=5 && this.counter.min>=2)
    {
      let timeLimit = document.getElementsByClassName('timer1') as HTMLCollectionOf<HTMLElement>;
      timeLimit[0].style.border = '10px solid orange'
    }
    
    if(this.counter.min<=1)
    {
      let timeLimit = document.getElementsByClassName('timer1') as HTMLCollectionOf<HTMLElement>;
      timeLimit[0].style.border = '10px solid red'
      if(this.counter.min==0 && this.counter.sec<=59)
      {
        timeLimit[0].style.animation = 'blink 1s steps(1, end) infinite';
      }
    }
   
    }, 1000)
  }
  

}
