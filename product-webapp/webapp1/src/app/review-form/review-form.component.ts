import { HttpClient, HttpParams } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar, MatSnackBarConfig, MatSnackBarHorizontalPosition, MatSnackBarVerticalPosition } from '@angular/material/snack-bar';
import { environment } from 'src/environments/environment';


import { ReviewService } from '../service/review/review.service';

export class Review {
  reviewId?:number;
  prosMessage?:string;
  consMessage?:string;
  reviewDate?:Date;
  companyRatings?:Ratings;
  companyName?:string;
  companyLogo?:any;
  user?:User;
  constructor(reviewId?:number,user?:User,prosMessage?:string,consMessage?:string,
    reviewDate?:Date,companyRatings?:Ratings,companyName?:string,companyLogo?:any){
      this.reviewDate=reviewDate;
      this.reviewId=reviewId;
      this.prosMessage=prosMessage;
      this.consMessage=consMessage;
      this.user=user;
      this.companyRatings=companyRatings;
      this.companyName = companyName;
      this.companyLogo=companyLogo;
    }
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
  constructor(private formBuilder:FormBuilder, private http:HttpClient,private alert:MatSnackBar  ,public reviewService:ReviewService) {
    // localStorage.setItem('loginId','sdsd')
   }

  ngOnInit(): void {
    // localStorage.setItem('loginId','Aka@sh@Gmail.com')
  }

  reviewForm:FormGroup = this.formBuilder.group({
    prosMessage: ['', [Validators.required, Validators.minLength(5), Validators.maxLength(100)]],
    consMessage: ['',[Validators.required, Validators.minLength(5), Validators.maxLength(100)]],
    companyRatings: ['', [Validators.required]],
    user:this.formBuilder.group({
      anonymousUser: ['true', Validators.required],
      email: [{value: 'akash@gmail.com',disabled: true}],
      name: [{value: 'Akash Suneja',disabled:true}],
      workDetails: this.formBuilder.group({
        currentlyWorking: ['false', [Validators.required]],
        jobRole: [''],
        yearsOfExperience: ['']
      })
    })
  })
  formRadio:string='';
  emailId:string=localStorage.getItem('loginId');
  name:string=''
  // private postReviewRequest = this.reviewService.baseUrl+'/api/v1/resources/saveReview';
  // private deleteReviewRequest = this.reviewService.baseUrl+'/api/v1/resources/deleteReview';

  

  setUserName(){
    let email="";
    let name=""
    
      email = localStorage.getItem('loginId');
      this.emailId=email;
      name = email.split('@')[0];
      this.name=name
    
    if(this.reviewForm.get(['user', 'anonymousUser']).value=='true'){
      this.reviewForm.get(['user', 'email']).setValue( email);
      this.reviewForm.get(['user', 'name']).setValue(name);
    }
    else{
      this.reviewForm.get(['user', 'email']).setValue( '');
      this.reviewForm.get(['user', 'name']).setValue('Anonymous');
    }
  }
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
    if(this.reviewForm.get(['user', 'anonymousUser']).value=="true"){
    review.user.email= "";
    review.user.name= "Anonymous";
    }
    else{
      review.user.email= localStorage.getItem('loginId');
      review.user.name= this.name;
    }
    let horizontalPosition:MatSnackBarHorizontalPosition='center';
    let verticalPosition:MatSnackBarVerticalPosition='top'; 
    console.log(review)
    this.reviewService.postReview(review, this.reviewService.selectedCompany.companyName)
          .subscribe({
            next: response=>{
              console.log(response);
              this.alert.open('Successfully submitted!!!','close',{
                horizontalPosition: horizontalPosition,
                verticalPosition: verticalPosition,
                duration:5000
              })
            },
            error: errorResponse=>{
              this.alert.open(errorResponse.message ,'close',{
                horizontalPosition: horizontalPosition,
                verticalPosition: verticalPosition,
                duration:5000
              })
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
  
  
}
