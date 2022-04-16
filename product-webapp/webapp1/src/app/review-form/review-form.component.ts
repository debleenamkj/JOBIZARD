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
  companyName?:string;
  companyLogo?:any;
};
enum Ratings{
        POOR = 0,
        NOT_BAD = 1,
        GOOD = 2,
        VERY_GOOD = 3,
        EXCELLENT = 4
};
type User = {
  anonymousUser?:boolean;
  email?:string;
  name?:string;
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
  filter ={
    red:'invert(14%) sepia(94%) saturate(4822%) hue-rotate(357deg) brightness(91%) contrast(126%)',
    blue:'invert(79%) sepia(70%) saturate(2132%) hue-rotate(129deg) brightness(100%) contrast(109%)',
    yellow:'invert(87%) sepia(61%) saturate(909%) hue-rotate(359deg) brightness(104%) contrast(100%)',
    green:'invert(76%) sepia(93%) saturate(522%) hue-rotate(26deg) brightness(97%) contrast(117%)',
    golden:'invert(72%) sepia(95%) saturate(622%) hue-rotate(359deg) brightness(102%) contrast(106%)'
  }
  constructor(private formBuilder:FormBuilder, private http:HttpClient,public reviewService:ReviewService) { }

  ngOnInit(): void {
  }

  reviewForm:FormGroup = this.formBuilder.group({
    anonymousUser: 'true',
    email: '',
    name: '',
    currentlyWorking: 'false',
    jobRole: '',
    yearsOfExperience: '',
    prosMessage: ['', [Validators.required, Validators.minLength(5), Validators.maxLength(100)]],
    consMessage: ['',[Validators.required, Validators.minLength(5), Validators.maxLength(100)]],
    companyRatings: ['', Validators.required]
  })
  formRadio:string='';

  private postReviewRequest = 'http://localhost:8087/api/v1/resources/saveReview';
  private deleteReviewRequest = 'http://localhost:8087/api/v1/resources/deleteReview';

  formValidation(){
    return true
  }
  starColor(rating:number,
    one:any,onefilter:string,
    two:any,twofilter:string,
    three:any,threefilter:string,
    four:any,fourfilter:string,
    five:any,fivefilter:string
    ){
    
    one._elementRef.nativeElement.style.filter = onefilter;
    two._elementRef.nativeElement.style.filter = twofilter;
    three._elementRef.nativeElement.style.filter = threefilter;
    four._elementRef.nativeElement.style.filter = fourfilter;
    five._elementRef.nativeElement.style.filter = fivefilter;

    let ratingValue:Ratings;
    switch(rating){
      
      case 1:
        ratingValue = Ratings.POOR;
        break;
      case 2:
        ratingValue = Ratings.NOT_BAD;
        break;
      case 3:
        ratingValue = Ratings.GOOD;
          break;
      case 4:
        ratingValue = Ratings.VERY_GOOD;
        break;
      case 5:
        ratingValue = Ratings.EXCELLENT;
        break;
      default:
          break;
  }
  this.reviewForm.controls['companyRatings'].setValue(ratingValue)
}
  postReviewDetails(){
   
    let review:Review;
    review = this.reviewForm.value;
    review.reviewDate = new Date();
    
    this.postReview(review, this.reviewService.selectedCompany.companyName)
          .subscribe({
            next: response=>{
              console.log(response);
            },
            error: errorResponse=>{
              console.log(errorResponse);
            }
          })
  }
  validateRatings(){
    if(this.reviewForm.controls['companyRatings'].hasError('required'))
      return 'Select stars to give ratings.'
    return '';
  }
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
