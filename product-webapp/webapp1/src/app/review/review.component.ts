import { HttpClient } from '@angular/common/http';
import { Component, OnInit, Type } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { Observable } from 'rxjs';
import { CompanyDetails } from '../model/company-details';
import { ReviewFormComponent } from '../review-form/review-form.component';
import {FormBuilder, FormGroup} from '@angular/forms';

import {startWith, map} from 'rxjs/operators';
import { newArray } from '@angular/compiler/src/util';
type MyPaginator = {
  length?: number;
  pageSize?: number;
  pageSizeOptions?:number[];
};
export interface CompanyNameGroup {
  letter: string;
  names: string[];
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


  constructor(private http: HttpClient, public dialog:MatDialog, private _formBuilder: FormBuilder) {
    
    

    this.getCompanies().subscribe((response: CompanyDetails[])=>{
      this.companies = response;
      this.retrieveLogos(this.companies)
      console.log(this.companies);
      if(this.companies.length>0)
        this.companyNameGroups = this.getCompanyNameGroup();
    })
   }
  ngOnInit(): void {
    this.companyGroupOptions = this.companyForm.get('companyGroup')!.valueChanges.pipe(
      startWith(''),
      map(value => this._filterGroup(value)),
    );
  }
  private _filterGroup(value: string): CompanyNameGroup[] {
    if (value) {
      return this.companyNameGroups
        .map(group => ({letter: group.letter, names: _filter(group.names, value)}))
        .filter(group => group.names.length > 0);
    }

    return this.companyNameGroups;
  }
  companyForm: FormGroup = this._formBuilder.group({
    companyGoup: '',
  });

  companyNameGroups: CompanyNameGroup[] = [];
  
  getCompanyNameGroup():CompanyNameGroup[]{
    console.log("inMethod")
    
    let nameGroup:CompanyNameGroup[]=[];
    let a:CompanyNameGroup={
      letter:'',
      names:[]
      };
    let array:string[]=[];
    let i=65
    while( i<91)
    {
      let newArr = this.companies;
      a.names=[];
      a.letter = String.fromCharCode(i);
      
      newArr.filter(o=>o.companyName.startsWith(a.letter))
      .forEach(o=>a.names.push(o.companyName));
      console.log(a)
      i++;
    }

    
    return nameGroup;
  }
 
  

  companyGroupOptions!: Observable<CompanyNameGroup[]>;
  ////////////Variables/////////////
  
  paginator: MyPaginator={
    length:0,
    pageSize:0,
    pageSizeOptions:[]
  }
  
  //////////////////////////////////

  openReviewForm(event:any): void{
    const dialogRef = this.dialog.open(ReviewFormComponent)
    
    dialogRef.afterClosed().subscribe(result => {
      console.log('Dialog Box: $(result)')
    })
  }

  
  logoSlideCount:any;
  companies:CompanyDetails[]=[];

  private getAllCompaniesGetRequest = "http://localhost:8087/api/v1/resources/get_all_companies";
  getCompanies(){
    return this.http.get<CompanyDetails[]>(this.getAllCompaniesGetRequest);
  }

  retrieveLogos(companies:CompanyDetails[]){
    companies.forEach(company => {
      company.retrievedImage = 'data:image/jpeg;base64,'+company.companyLogo;
    });
  }
  carouselSlidesAndCards(totalCards:number, cardsPerSlide:number){
    let array:any[]=[];
    let cards:number[]=[];
    let noOfSlides = Math.floor(totalCards/cardsPerSlide);
    let lastSlideCards=totalCards%cardsPerSlide;
    noOfSlides = (lastSlideCards==0)?noOfSlides:noOfSlides+1;
    for(let i=0; i<cardsPerSlide; i++){
      cards.push(i);
    }
    for(let i=0; i<noOfSlides-1; i++){
      array.push(cards)
    }
    
    if(lastSlideCards != 0){
      cards = [];
      for(let i=0; i<lastSlideCards; i++){
        cards.push(i);
      }
    }
    array.push(cards);
    console.log(array)
    return array;

  }
}