import { Component, OnInit } from '@angular/core';
import { PostService } from '../service/post/post.service';
import { SkilltestServiceService } from '../service/skilltest-service.service';

@Component({
  selector: 'app-test-result-view',
  templateUrl: './test-result-view.component.html',
  styleUrls: ['./test-result-view.component.css'],
})
export class TestResultViewComponent implements OnInit {
  constructor(private service: SkilltestServiceService,private service1:PostService) {}

  ngOnInit(): void {

    this.service1.getSeeker(localStorage.getItem('loginId')).subscribe( data =>{
      this.username=data;
      console.log(this.username);
      
    })

    this.Testname=this.service.quizName;
    let worrior = this.service.worrior;
    if (worrior == 'beginner') {
      this.worrior_logo = this.service.getbeginner();
      console.log(this.worrior_logo);
    } else if (worrior == 'saga') {
      this.worrior_logo = this.service.getsaga();
      console.log(this.worrior_logo);
    } else if (worrior == 'gladiator') {
      this.worrior_logo = this.service.getgladiator();
      console.log(this.worrior_logo);
    } else if (worrior == 'ninja') {
      this.worrior_logo = this.service.getninja();
      console.log(this.worrior_logo);
    }
    // this.worrior_logo=this.service.logo;
    console.log(this.worrior_logo);
    this.level = this.service.worrior;
    this.attempted = this.service.answeredQuestions;
    this.unattempted = this.service.unAnsweredQuestions;
    this.marksGot = this.service.percentage;

    

    let card = document.getElementsByClassName(
      'flip-card'
    ) as HTMLCollectionOf<HTMLElement>;
    let head = document.getElementsByClassName(
      'head'
    ) as HTMLCollectionOf<HTMLElement>;
    
    if (this.marksGot < 50) {
      card[0].style.backgroundColor = 'red';
      this.message = 'FAIL';
      this.head ='Not qualified, Re-Take the test'
      this.skill={isVerified:false,skillName:this.service.quizName,level:worrior,percentage:this.service.percentage}
      
    } else {
      card[0].style.backgroundColor = 'green';
      this.message = 'PASS';
      // this.head ="Hey " +this.username.firstName+ ", you are our " +this.level+ ", with Score " +this.marksGot+ "%";
      this.head =" you are our " +this.level+ ", with Score " +this.marksGot+ "%";

      this.skill={isVerified:true,skillName:this.service.quizName,level:worrior,percentage:this.service.percentage}
    }

    console.log("skillss");
    console.log(this.service.quizName);
    
    console.log(this.skill)
    let email = localStorage.getItem('loginId')
    console.log("skilllsss");
    
    console.log(this.skill);
    
    this.service.sendMarks(email,this.skill).subscribe(data =>{
      console.log(data);
    })
  }

  skill:any;
  head:any;
  worrior_logo: any;
  marksGot: number = 0;
  correctAnswers = 0;
  attempted = 0;
  unattempted = 0;
  username :any;
  level: any;
  Testname = '';
  TotalQuestions = 10;
  TotalMarks = 100;
  CollegeName = 'NINJA';
  test = false;
  message = 'PASS';
}
