import { Component, OnInit } from '@angular/core';
import { SkilltestServiceService } from '../service/skilltest-service.service';

@Component({
  selector: 'app-test-result-view',
  templateUrl: './test-result-view.component.html',
  styleUrls: ['./test-result-view.component.css'],
})
export class TestResultViewComponent implements OnInit {
  constructor(private service: SkilltestServiceService) {}

  ngOnInit(): void {
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
    if (this.marksGot <= 49) {
      card[0].style.backgroundColor = 'red';
      this.message = 'FAIL';
    } else {
      card[0].style.backgroundColor = 'green';
      this.message = 'PASS';
    }
  }
  worrior_logo: any;
  marksGot: number = 0;
  correctAnswers = 0;
  attempted = 0;
  unattempted = 0;
  username = 'USER';
  level: any;
  Testname = '';
  TotalQuestions = 10;
  TotalMarks = 100;
  CollegeName = 'NINJA';
  test = false;
  message = 'PASS';
}
