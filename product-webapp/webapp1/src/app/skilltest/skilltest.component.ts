import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router, RouterModule } from '@angular/router';
import { toInteger } from '@ng-bootstrap/ng-bootstrap/util/util';

@Component({
  selector: 'app-skilltest',
  templateUrl: './skilltest.component.html',
  styleUrls: ['./skilltest.component.css']
})
export class SkilltestComponent implements OnInit {

  constructor(private service:HttpClient, private router:Router) { }

  data : any = {name :"",questions:""};
  question: any ={question:"",a:"",b:"",c:"",d:""};
  no_of_questions:any=[];
  answers:any=[];
  bookmarks:any=[]

  ngOnInit(): void {
    this.welcome()
    let qno =0;

    this.service.get("http://localhost:3000/test").subscribe(data =>{
      console.log(data);
      this.data = data;
     this.ques_counter(this.data.no_of_questions);
     console.log(qno);
      console.log(this.data)
    })
  this.startTimer();
  setTimeout(() => {
    this.getQuestion(1);   
  }, 1000);


}

options: string="";

bookmark(qno:number){
  this.bookmarks[qno]="true";
  let bookmark = document.getElementsByClassName("ques") as HTMLCollectionOf<HTMLElement>;
  console.log(bookmark);
  console.log(bookmark[qno]);
  bookmark[qno].style.backgroundColor='#00CED1';
}

// unmark(qno:number){
//   let bookmark = document.getElementsByClassName("ques") as HTMLCollectionOf<HTMLElement>;
//   console.log(bookmark);
//   console.log(bookmark[qno]);
//   bookmark[qno].style.backgroundColor='#4E1B5F';
// }

option(qno:number){
  console.log("options in");
  if(this.answers[qno]==""){
    console.log("optionss");
   
  }
  else if(this.answers[qno]!=""){
    console.log(this.answers);
    let bookmark = document.getElementsByClassName("ques") as HTMLCollectionOf<HTMLElement>;
    bookmark[qno].style.backgroundColor='#4E1B5F';
    console.log("options checked");
  }
}

changeColor(qno:number){
  console.log(qno);
  console.log(this.bookmarks);
  let bookmark = document.getElementsByClassName("ques") as HTMLCollectionOf<HTMLElement>;
  if(this.bookmarks[qno-2]=="true"){
    console.log(this.bookmarks[qno]);
    bookmark[qno-2].style.backgroundColor ='#00CED1';
  }
  else if(this.answers[qno-1]!=""){
    console.log("changcolor");
    console.log(this.answers[qno]);
    setTimeout(() => {
      bookmark[qno-2].style.backgroundColor='#4E1B5F';   
    }, 200);
   
  }
}

  getQuestion(qno : number){
    let bookmark = document.getElementsByClassName("ques") as HTMLCollectionOf<HTMLElement>;
    if(this.answers[qno]==""){
      bookmark[qno-1].style.backgroundColor='rgb(177,23,132)';
      this.options = "";
    }
    // else if(this.answers[qno]!=""){
    //   bookmark[qno].style.backgroundColor='#4E1B5F';
    // }
      let questions = new Array();
      questions.push(this.data.questions);

      for (let index = 0; index < questions[0].length; index++) {
        if(questions[0][index].qno==qno)
        this.question = questions[0][index];
      }
      console.log(this.question); 
      // this.option(qno-1);
  }

  end(){
    console.log("enndd")
    let end = document.getElementsByClassName("end") as HTMLCollectionOf<HTMLElement>;
    end[0].style.display='block'
    let ques = document.getElementsByClassName("questions") as HTMLCollectionOf<HTMLElement>;
    ques[0].style.opacity='0.1';
  }

  end_yes(){
    console.log("hello end yes")
    setTimeout(() => {
      this.router.navigate(['/job-posting'])
    }, 300);
  }
  end_no(){
    let end = document.getElementsByClassName("end") as HTMLCollectionOf<HTMLElement>;
    end[0].style.display='none'
    let ques = document.getElementsByClassName("questions") as HTMLCollectionOf<HTMLElement>;
    ques[0].style.opacity='1.0';
  }
  // counter = {"min":0,"sec":0};
  counter : any= { min: 0, sec: "15" }

  startTimer() {
    this.counter = { min: 15, sec: "10" } // choose whatever you want
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

      if(this.counter.min<=4 && this.counter.min>=2)
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
    if(this.counter.min==0 && this.counter.sec=="00")
    {
      setTimeout(() => {
        this.router.navigate(['/job-posting'])
      }, 300);
   
    }
    }, 1000)
  }

  ques_counter(i: number) {
    var arr = new Array();
    for (let index = 1; index <= i; index++) {
       console.log(index);
        arr.push(index);
        this.answers[index]="";
        this.bookmarks[index]="";
    }
    this.no_of_questions = arr;
    console.log(this.no_of_questions)
    console.log(this.answers);
    return arr;
}

welcome(){
  console.log("hello");
}
  

}
