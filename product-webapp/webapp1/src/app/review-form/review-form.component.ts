import { HttpClient, HttpParams } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { ReviewService } from '../service/review/review.service';

export interface Review {
  reviewId?:number;
  user?:User;
  prosMessage?:string;
  consMessage?:string;
  reviewDate?:Date;
  companyRatings?:Ratings;
};
enum Ratings{
        POOR = 1,
        NOT_BAD = 2,
        GOOD = 3,
        VERY_GOOD = 4,
        EXCELLENT = 5
};
type User = {
  userId?:number;
  email?:number;
  firstName?:string;
  middleName?:string;
  lastName?:string;
  workDetails?:WorkDetails;
}
type WorkDetails = {
  currentlyWorking?:boolean;
  jobRole?:string;
  yearsOfExperience?:string;
}

@Component({
  selector: 'app-review-form',
  templateUrl: './review-form.component.html',
  styleUrls: ['./review-form.component.css']
})
export class ReviewFormComponent implements OnInit {

  constructor(private formBuilder:FormBuilder, private http:HttpClient,public reviewService:ReviewService) { }

  ngOnInit(): void {
  }

  reviewForm:FormGroup = this.formBuilder.group({
    // userId: new FormControl('', Validators.required),
    // email: ['', Validators.required, Validators.email],
    // firstName: ['', Validators.required],
    // middleName: [''],
    // lastName: [''],
    currentlyWorking: ['', Validators.required],
    jobRole: ['', Validators.required],
    yearsOfExperience: ['', Validators.required],
    prosMessage: ['', [Validators.required, Validators.minLength(5), Validators.maxLength(100)]],
    consMessage: ['',[Validators.required, Validators.minLength(5), Validators.maxLength(100)]],
    reviewDate: ['', Validators.required],
    companyRatings: ['', Validators.required]
  })
  formRadio:string='';

  private postReviewRequest = 'http://localhost:8087/api/v1/resources/saveReview';
  private deleteReviewRequest = 'http://localhost:8087/api/v1/resources/deleteReview';


  validateProsMessage(){
      if(this.reviewForm.controls['prosMessage'].hasError('required'))
        return 'You must enter a value';
      if(this.reviewForm.controls['prosMessage'].errors?.["minlength"] || this.reviewForm.controls['prosMessage'].errors?.['maxlength'])
        return 'Message must be 5 to 100 characters long'
    return '' ;
  }
  validateConsMessage(){
    if(this.reviewForm.controls['consMessage'].hasError('required'))
        return 'You must enter a value';
      if(this.reviewForm.controls['consMessage'].errors?.['minlength'] || this.reviewForm.controls['consMessage'].errors?.['maxlength'])
        return 'Message must be 5 to 100 characters long';
    return '' ;
  }
  postReview( review:Review, companyName:string){
    return this.http.post<Review>(this.postReviewRequest, review, 
      {params:new HttpParams().append('companyName', companyName)});
  }
  deleteReview(reviewId:number, companyName:string){
    let param = new HttpParams();
    param = param.append("reviewId", reviewId);
    param = param.append("companyName", companyName);
    return this.http.delete<boolean>(this.deleteReviewRequest,{params: param});
  }
}
