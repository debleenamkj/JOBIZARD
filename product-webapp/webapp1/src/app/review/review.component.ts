import { HttpClient, HttpParams } from '@angular/common/http';
import { Component, ComponentFactoryResolver, OnInit, Type } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { Observable } from 'rxjs';
import { CompanyDetails } from '../model/company-details';
import { ReviewFormComponent } from '../review-form/review-form.component';
import { FormBuilder, FormControl, FormGroup } from '@angular/forms';
import { startWith, map } from 'rxjs/operators';
import { PageEvent } from '@angular/material/paginator';
import { ReviewService } from '../service/review/review.service';
import { MatSnackBar, MatSnackBarHorizontalPosition, MatSnackBarVerticalPosition } from '@angular/material/snack-bar';



@Component({
  selector: 'app-review',
  templateUrl: './review.component.html',
  styleUrls: ['./review.component.css']
})
export class ReviewComponent implements OnInit {
  constructor(public reviewService:ReviewService, private http: HttpClient, public dialog: MatDialog,private alert:MatSnackBar ,private _formBuilder: FormBuilder) {
    this.requestResources();
  }
  ngOnInit(): void { 
    
   }

  ////////////Variables/////////////
  companyForm: FormGroup = this._formBuilder.group({
    companyGroup: '',
  });
  
  companyNameGroups: CompanyNameGroup[] = [];
  companyGroupOptions!: Observable<CompanyNameGroup[]>;
  companies: CompanyDetails[] = [];
  companiesSlice: CompanyDetails[]= [];
  allDetails: CompanyDetails[] = [];
  reviewHome:Review[]=[];
  reviewHomeSlice:any;
  starDisplay:number[]=[];
  searchExists:boolean=false;
  searchedCompany:CompanyDetails;
  searchedReviews:Review[]=[];
  searchedReviewsSlice:Review[]=[];
  

  
  ///////////////////////////////////////////
  companyPageChange(event:any){
    let start = event.pageSize*event.pageIndex;
    this.companiesSlice = this.companies.slice(start,start+event.pageSize)
  }
  reviewPageChange(event:any){
    let start = event.pageSize*event.pageIndex;
    let a = start;
    let b = event.pageSize;
    
    if(this.searchExists){
      if(this.reviewService.selectedCompany){
        a--;
      if(start>0)
        b--;
      }
      this.searchedReviewsSlice=this.searchedReviews.slice(a,a+b);
    }
    else
      this.reviewHomeSlice = this.reviewHome.slice(a,a+b);

    
  }
  openReviewForm(selectedCompany:CompanyDetails): void {
    this.reviewService.selectedCompany = selectedCompany;
    const dialogRef = this.dialog.open(ReviewFormComponent);

    // dialogRef.afterClosed().subscribe(result => {
    //  // console.log('Dialog Box: $(result)')
    //  console.log(result)
    // })
  }
  querySearch(){
    let verticalPosition:MatSnackBarVerticalPosition='top';
    let horizontalPosition:MatSnackBarHorizontalPosition='center';
    this.reviewService.getCompanyByCompanyName(this.companyForm.get('companyGroup')?.value)
    .subscribe({next: response=>{
console.log(response);
      this.searchedCompany = response;
      this.searchedCompany.retrievedImage = 'data:image/jpeg;base64,'+this.searchedCompany.companyLogo;


      this.reviewService.getReviewsByCompanyName(this.companyForm.get('companyGroup')?.value)
      .subscribe({ next: response =>{
        this.searchedReviews = response;
        this.searchedReviews.forEach((review)=>{
          review.companyLogo = this.searchedCompany.retrievedImage;
          review.companyName = this.searchedCompany.companyName;
        })

        this.searchedReviewsSlice=this.searchedReviews.slice(0,12);
  console.log(this.searchedReviews);
        this.searchExists=true;
      }, 
      error: errorResponse=>{
        let message:string;
        this.searchExists=false;
        if(errorResponse.error.status==404){
         message = errorResponse.error.message;
        }
        else{
          message = 'Server Error';
        }
        this.alert.open(message,'close',{
          verticalPosition: verticalPosition,
          horizontalPosition: horizontalPosition,
          duration: 5000
        })
      }
    })


    },error: errorResponse=>{

    }})
    
  }
  retrieveLogos(companies: CompanyDetails[]) {
    companies.forEach(company => {
      company.retrievedImage = 'data:image/jpeg;base64,' + company.companyLogo;
    });
  }
  private _filterGroup(value: string): CompanyNameGroup[] {
    if (value) {
      return this.companyNameGroups
        .map(group => ({ letter: group.letter, names: _filter(group.names, value) }))
        .filter(group => group.names.length > 0);
    }
    return this.companyNameGroups;
  }
  getCompanyNameGroup(): CompanyNameGroup[] {
    let nameGroup: CompanyNameGroup[] = [];
    for (let i = 65; i < 91; i++) {
      let letterGroup: CompanyNameGroup = {
        letter: '',
        names: []
      };
      letterGroup.letter = String.fromCharCode(i);
      this.companies.filter(o => o.companyName.startsWith(letterGroup.letter))
        .forEach(o => { letterGroup.names.push(o.companyName) });

      if (letterGroup.names.length != 0)
        nameGroup.push(letterGroup)
    }
console.log(nameGroup)
    return nameGroup;
  }

  ratingsLoopArray(rating:Ratings, starParent:any){
    let a:number;
    let array: number[]=[]
    let filter ={
      red:'invert(14%) sepia(94%) saturate(4822%) hue-rotate(357deg) brightness(91%) contrast(126%)',
      blue:'invert(79%) sepia(70%) saturate(2132%) hue-rotate(129deg) brightness(100%) contrast(109%)',
      yellow:'invert(87%) sepia(61%) saturate(909%) hue-rotate(359deg) brightness(104%) contrast(100%)',
      green:'invert(76%) sepia(93%) saturate(522%) hue-rotate(26deg) brightness(97%) contrast(117%)',
      golden:'invert(72%) sepia(95%) saturate(622%) hue-rotate(359deg) brightness(102%) contrast(106%)'
    }
    switch(rating.toString()){
      case 'POOR':
        a=1;
        starParent.style.filter=filter.red;
        break;
        case 'NOT_BAD':
          a=2;
          starParent.style.filter=filter.blue;
          break;
          case 'GOOD':
            a=3;
            starParent.style.filter=filter.yellow;
            break;
            case 'VERY_GOOD':
              a=4;
              starParent.style.filter=filter.green;
              break;
              case 'EXCELLENT':
                a=5;
                starParent.style.filter=filter.golden;
                break;
      }
      for(let i=0;i<a;i++){
        array.push(i)
    }
    return array;
  }

  requestResources() {
    this.reviewService.getCompanies().subscribe((response: CompanyDetails[]) => {
      this.companies = response;
      this.retrieveLogos(this.companies);
console.log(this.companies)
      this.companiesSlice=this.companies.slice(0,5);
      if (this.companies.length > 0) {
        this.companyNameGroups = this.getCompanyNameGroup();
        this.companyGroupOptions = this.companyForm.get('companyGroup')!.valueChanges.pipe(
          startWith(''),
          map(value => this._filterGroup(value)),
        );
      }
    })

    this.reviewService.getAllDetails().subscribe({
      next: response=>{
        
        this.allDetails=response;
        this.retrieveLogos(this.allDetails);
        this.allDetails.forEach(company=>{
          if(company.reviews!=null){
            let sliced:any=[];
            sliced.push(company.reviews);
            sliced[0].forEach((review:any)=>{
              this.reviewHome.push(review);
              this.reviewHome[this.reviewHome.length-1].companyName=company.companyName;
              this.reviewHome[this.reviewHome.length-1].companyLogo=company.retrievedImage;
            })
          }
        })
      
console.log(this.reviewHome);
        this.reviewHome = this.reviewHome.sort((a:Review,b:Review)=>{
          if(a.reviewDate>b.reviewDate)
            return -1;
          else
           return 1;
        }
        );
        this.reviewHomeSlice = this.reviewHome.slice(0,12)
      },
      error: errorResponse=>{
        
      }
    })
    
  }

  
}

export interface CompanyNameGroup {
  letter: string;
  names: string[];
}
// export interface Review {
//   reviewId?:number;
//   user?:User;
//   prosMessage?:string;
//   consMessage?:string;
//   reviewDate?:Date;
//   companyRatings?:Ratings;
//   companyName?:string;
//   companyLogo?:any;
// };
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
        POOR = 1,
        NOT_BAD = 2,
        GOOD = 3,
        VERY_GOOD = 4,
        EXCELLENT = 5
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

export const _filter = (opt: string[], value: string): string[] => {
  const filterValue = value.toLowerCase();

  return opt.filter(item => item.toLowerCase().includes(filterValue));
};