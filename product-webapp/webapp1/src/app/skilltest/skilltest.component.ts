import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router, RouterModule } from '@angular/router';
import { toInteger } from '@ng-bootstrap/ng-bootstrap/util/util';
import { SkilltestServiceService } from '../service/skilltest-service.service';

@Component({
  selector: 'app-skilltest',
  templateUrl: './skilltest.component.html',
  styleUrls: ['./skilltest.component.css']
})
export class SkilltestComponent implements OnInit {

  constructor(private service:SkilltestServiceService, private router:Router) { }

  data : any = {name :"",questions:""};
  question: any ={question:"",a:"",b:"",c:"",d:""};
  no_of_questions:any=[];
  answers:any=[];
  bookmarks:any=[]
  actualAnswers:any;
  totalQuestions:number=0

  ngOnInit(): void {
    let qno =0;
    this.service.answeredQuestions=0;
    this.service.unAnsweredQuestions=0;
    this.service.percentage=0;
    this.service.getQuestions().subscribe(data =>{
      console.log(data);
      this.data = data;
      this.actualAnswers=this.data.answers;
      console.log(this.actualAnswers);
      this.totalQuestions=this.data.no_of_questions;
      console.log(this.actualAnswers);
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
  bookmark[qno].style.backgroundColor='#00CED1';
}


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
  let bookmark = document.getElementsByClassName("ques") as HTMLCollectionOf<HTMLElement>;
  if(this.bookmarks[qno-2]=="true"){
    console.log(this.bookmarks[qno]);
    bookmark[qno-2].style.backgroundColor ='#00CED1';
  }
  else if(this.answers[qno-1]!=""){
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
      let questions = new Array();
      questions.push(this.data.questions);

      for (let index = 0; index < questions[0].length; index++) {
        if(questions[0][index].qno==qno)
        this.question = questions[0][index];
      }
      console.log(this.question); 
  }

  end(){
    let end = document.getElementsByClassName("end") as HTMLCollectionOf<HTMLElement>;
    end[0].style.display='block'
    let ques = document.getElementsByClassName("questions") as HTMLCollectionOf<HTMLElement>;
    ques[0].style.opacity='0.1';
    ques[1].style.opacity='0.1';
  }

  end_yes(){
    setTimeout(() => {
      this.router.navigate(['/job-posting'])
    }, 300);
  }
  end_no(){
    let end = document.getElementsByClassName("end") as HTMLCollectionOf<HTMLElement>;
    end[0].style.display='none'
    let ques = document.getElementsByClassName("questions") as HTMLCollectionOf<HTMLElement>;
    ques[0].style.opacity='1.0';
    ques[1].style.opacity='1.0';
  }
  // counter = {"min":0,"sec":0};
  counter : any= { min: 0, sec: "15" }

  startTimer() {
    this.counter = { min: 0, sec: "30" } // choose whatever you want
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
        console.log(this.answers);
        this.calculateScores();
        this.router.navigate(['/job-posting'])
      }, 300);
   
    }
    }, 1000)
  }

  

  ques_counter(i: number) {
    var arr = new Array();
    for (let index = 1; index <= i; index++) {
        arr.push(index);
        this.answers[index]="";
        this.bookmarks[index]="";
    }
    this.no_of_questions = arr;
    return arr;
}

  calculateScores(){
    let correctAnswers = 0;
    let answerdQuestion = 0;
    let unAnswerdQuestion = 0;
    for (let index = 0; index < this.actualAnswers.length; index++) {
      if(this.answers[index+1]==''){
        unAnswerdQuestion++;
      }

      if(this.actualAnswers[index]==this.answers[index+1]){
        correctAnswers++;
      }
      
    }
    let percentage = (correctAnswers/this.totalQuestions)*100;
    answerdQuestion = this.totalQuestions-unAnswerdQuestion;
    // console.log(answerdQuestion);
    // console.log(unAnswerdQuestion);
    // console.log(correctAnswers);
    // console.log(percentage);
    this.service.answeredQuestions=answerdQuestion;
    this.service.unAnsweredQuestions=unAnswerdQuestion;
    this.service.percentage=percentage;
    let warrior_name="";
    if(percentage>=90){
      warrior_name='ninja'
    }
    else if(percentage>=80){
      warrior_name='xxxx'
    }
    else if(percentage>=70){
      warrior_name='yyyy'
    }
    else if(percentage>50){
      warrior_name='zzzz'
    }
    let data = {"emailId":"malumalathi032@gmail.com","percentage":percentage+"%","warrior-name":"ninja"}

    
  }
 
}
