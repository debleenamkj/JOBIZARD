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
   
    let quizName = this.service.quizName;
    console.log("quizName :"+quizName);
    this.service.quizName = quizName;
    this.service.getTest(quizName).subscribe(data =>{
      console.log("daaattaaaa");
      
      console.log(data);
      this.data = data;
      this.actualAnswers=this.data.answers;
      console.log(this.actualAnswers);
      this.totalQuestions=this.data.numberOfQuestions;
      console.log(this.actualAnswers);
      this.ques_counter(this.totalQuestions);
      console.log(qno);
      console.log(this.data)
    }) 
    // this.service.getQuestions().subscribe(data =>{
    //   console.log(data);
    //   this.data = data;
    //   this.actualAnswers=this.data.answers;
    //   console.log(this.actualAnswers);
    //   this.totalQuestions=this.data.no_of_questions;
    //   console.log(this.actualAnswers);
    //   this.ques_counter(this.data.no_of_questions);
    //   console.log(qno);
    //   console.log(this.data)
    // })
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
  console.log(qno);
  console.log(this.answers)
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
  console.log(this.changeColor)
  console.log(qno)
  let bookmark = document.getElementsByClassName("ques") as HTMLCollectionOf<HTMLElement>;
  if(this.bookmarks[qno]=="true"){
    console.log(this.bookmarks[qno]);
    bookmark[qno-1].style.backgroundColor ='#00CED1';
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
        if(questions[0][index].quesNo==qno)
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
    
    console.log("endd  yesss");
    
    this.calculateScores();
      this.router.navigate(['/navbar/result'])
    
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
    this.counter = { min: 1, sec: "00" } // choose whatever you want
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
        this.router.navigate(['/navbar/result'])
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
    console.log("answersss")
    console.log(this.answers)
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
    console.log("Answered Question :"+answerdQuestion);
    console.log("Unanswered Question :"+unAnswerdQuestion);
    console.log("Correct Answers :"+correctAnswers);
    console.log("percentage :"+percentage+"%");
    
    this.service.answeredQuestions=answerdQuestion;
    this.service.unAnsweredQuestions=unAnswerdQuestion;
    this.service.percentage=percentage;
    let warrior_name="";
    let warrior=""
    if(percentage>=90){
      warrior_name='ninja'
      // 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'
    }
    else if(percentage>=80){
      
      warrior_name='gladiator'
    }
    else if(percentage>=70){
      warrior_name='saga'
    }
    else if(percentage>=50){
      warrior_name='beginner'
    }
    this.service.worrior=warrior_name;
    let data = {"emailId":"malumalathi032@gmail.com","percentage":percentage+"%","warrior-name":"ninja"}

    console.log("warrior name:"+warrior_name)
  }
 
}
