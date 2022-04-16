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
type MyPaginator = {
  length?: number;
  pageSize?: number;
  pageSizeOptions?: number[];
};

export interface CompanyNameGroup {
  letter: string;
  names: string[];
}
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

export const _filter = (opt: string[], value: string): string[] => {
  const filterValue = value.toLowerCase();

  return opt.filter(item => item.toLowerCase().includes(filterValue));
};

@Component({
  selector: 'app-review',
  templateUrl: './review.component.html',
  styleUrls: ['./review.component.css']
})
export class ReviewComponent implements OnInit {
  constructor(public reviewService:ReviewService, private http: HttpClient, public dialog: MatDialog, private _formBuilder: FormBuilder) {
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
  searchErrorMessage:string|undefined;
  pageEvent!: PageEvent;
  companies: CompanyDetails[] = [];
  allDetails: CompanyDetails[] = [];
  reviewHome:Review[]=[];
  reviewHomeSlice:any;
  companiesSlice: CompanyDetails[]= [];
  searchError:boolean = false;
  // starDisplay=(rating:Ratings)=>{
  //   let a:number;
  //   let array: number[]=[]
  //   switch(rating){
  //     case Ratings.POOR:a=1
  //       break;
  //       case Ratings.NOT_BAD:a=2
  //         break;
  //         case Ratings.GOOD:a=3
  //           break;
  //           case Ratings.VERY_GOOD:a=4
  //             break;
  //             case Ratings.EXCELLENT:a=5
  //               break;
  //     }
  //     for(let i=0;i<a;i++){
  //       array.push(i)
  //   }
  //   return array;
  // }

  private getAllCompaniesGetRequest = "http://localhost:8087/api/v1/resources/get_all_companies";
  private getCompanyByCompanyNameRequest="http://localhost:8087/api/v1/resources/get_company";
  private getReviewsByCompanyNameRequest="http://localhost:8087/api/v1/resources/get_reviews";
  private getAllDetailsRequest="http://localhost:8087/api/v1/resources/getAllDetails";
  ///////////////////////////////////////////
  companyPageChange(event:any){
    let start = event.pageSize*event.pageIndex;
    this.companiesSlice = this.companies.slice(start,start+event.pageSize)
  }
  reviewPageChange(event:any){
    let start = event.pageSize*event.pageIndex;
    let a = start;
    let b = event.pageSize;
    if(this.reviewService.selectedCompany){
        b--;
      if(start>0)
        a--;
    }
    this.reviewHomeSlice = this.reviewHome.slice(a,a+b)
    
  }
  openReviewForm(selectedCompany:CompanyDetails): void {
    this.reviewService.selectedCompany = selectedCompany;
    const dialogRef = this.dialog.open(ReviewFormComponent)

    dialogRef.afterClosed().subscribe(result => {
     // console.log('Dialog Box: $(result)')
    })
  }
  querySearch(){
    this.getCompanyByCompanyName(this.companyForm.get('companyGroup')?.value)
    .subscribe({next: response=>{
      console.log(response);
    },error: errorResponse=>{

    }})
    this.getReviewsByCompanyName(this.companyForm.get('companyGroup')?.value)
    .subscribe({ next: response =>{
      this.searchError=false;
      let a:Review[] = response;
      console.log(a)
    }, 
    error: errorResponse=>{
      this.searchError=true;
      console.log(errorResponse.error)
      if(errorResponse.error.status==404){
        this.searchErrorMessage = errorResponse.error.message;
        console.log(errorResponse.error.message)
      }
      else{
        console.log('Server Error');
      }
    }
  })
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
  requestResources() {
    this.getCompanies().subscribe((response: CompanyDetails[]) => {
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

    this.getAllDetails().subscribe({
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
        this.reviewHome = this.reviewHome.reverse();
        this.reviewHomeSlice = this.reviewHome.slice(0,12)
      },
      error: errorResponse=>{
        
      }
    })
    
  }

  getReviewsByCompanyName(companyName:string){
    return this.http.get<Review[]>(this.getReviewsByCompanyNameRequest, {
                params:new HttpParams().append('companyName',companyName)
    });
  }
  getCompanyByCompanyName(companyName:string){
    return this.http.get<CompanyDetails>(this.getCompanyByCompanyNameRequest, {
                params:new HttpParams().append('companyName',companyName)
    });
  }
  getCompanies() {
    return this.http.get<CompanyDetails[]>(this.getAllCompaniesGetRequest);
  }
  getAllDetails(){
    return this.http.get<CompanyDetails[]>(this.getAllDetailsRequest)
  }
}

type sliceReview={
  array:Review[]
}
