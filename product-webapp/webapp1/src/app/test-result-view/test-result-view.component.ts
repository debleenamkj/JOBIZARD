import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-test-result-view',
  templateUrl: './test-result-view.component.html',
  styleUrls: ['./test-result-view.component.css']
})
export class TestResultViewComponent implements OnInit {

  constructor() { }

  ngOnInit(): void {
  }
  marksGot: number = 90;
  correctAnswers = 0;
  attempted = 0;
  unattempted = 0;
  username = 'USER';
  level = 'NINJA';
  Testname = '';
  performance = '';
  TotalQuestions = 20;
  TotalMarks = 100;
  TotalTime = '5:00';
  TotalTimeTaken = '4:45';
  CollegeName = 'NINJA';
  test = false;
  message = 'PASS';

}
