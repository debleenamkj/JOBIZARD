import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { NgbCarouselConfig } from '@ng-bootstrap/ng-bootstrap';
import { CompanyDetails } from '../model/company-details';

@Component({
  selector: 'app-learning-portal',
  templateUrl: './learning-portal.component.html',
  styleUrls: ['./learning-portal.component.css']
})
export class LearningPortalComponent implements OnInit {

  constructor(config: NgbCarouselConfig, private http: HttpClient) { 
    config.showNavigationArrows = true;
    config.showNavigationIndicators = true;
    config.pauseOnFocus = true;
    config.pauseOnHover = true;
    this.getCompanies().subscribe(response=>{
      this.companies = response;
      console.log(this.companies);
      this.setCarouselTemplateLength(this.companies.length);
    })
  }

  ngOnInit(): void {
  }

  companies: CompanyDetails[] = [];
  carouselTemplate: number[] = [];
  
  setCarouselTemplateLength(length:number){
    let b = Math.floor(length/6)
    let c = (length%6 == 0) ? b : b+1; 
    for(let i=0; i<c; i++){
      this.carouselTemplate[i]=i;
    }
  }

  private getAllCompaniesGetRequest = "http://localhost:8087/api/v1/resources/get_all_companies"

  getCompanies(){
    return this.http.get<CompanyDetails[]>(this.getAllCompaniesGetRequest);
  }
}
