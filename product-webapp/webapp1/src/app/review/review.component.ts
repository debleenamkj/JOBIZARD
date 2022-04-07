import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { CompanyDetails } from '../model/company-details';
import { ReviewFormComponent } from '../review-form/review-form.component';

@Component({
  selector: 'app-review',
  templateUrl: './review.component.html',
  styleUrls: ['./review.component.css']
})
export class ReviewComponent implements OnInit {

  constructor(private http: HttpClient, public dialog:MatDialog) {

    this.getCompanies().subscribe((response: CompanyDetails[])=>{
      this.companies = response;
      this.retrieveLogos(this.companies)
      console.log(this.companies);
      if(this.companies.length>0)
        this.logoSlideCount = this.carouselSlidesAndCards(this.companies.length,8);
    })
   }



  ngOnInit(): void {
  }

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

    // private getAllCompaniesGetRequest = "http://localhost:8087/api/v1/resources/get_all_companies";
  // getCompanies(){
  //   return this.http.get<CompanyDetails[]>(this.getAllCompaniesGetRequest);
  // }



   
}
