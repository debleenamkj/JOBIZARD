import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { NgbCarouselConfig } from '@ng-bootstrap/ng-bootstrap';
import { CompanyDetails } from '../model/company-details';
import { CourseSuggestion } from '../model/course-suggestion';

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
      this.retrieveLogos(this.companies)
      console.log(this.companies);
      this.setLogoTemplateLength(this.companies.length);
    })

    /*this.getSuggestions().subscribe(response=>{
      this.suggestions = response;
      console.log(this.suggestions);

      if(this.suggestions.length > 0){
        this.setCourseTemplateLength(this.suggestions.length);
        this.skillTypes = this.groupBySkills(this.suggestions);
      }
    })*/
  }

  ngOnInit(): void {
  }

  private getAllCompaniesGetRequest = "http://localhost:8087/api/v1/resources/get_all_companies";
  private getAllSuggestionsGetRequest = "";

  companies: CompanyDetails[] = [];
  suggestions: CourseSuggestion[]=[];
  skillTypes: string[]=[];

  skillTemplate:number[]=[];
  courseTemplate:number[]=[];
  logoTemplate:number[]=[];

  retrieveLogos(companies:CompanyDetails[]){
    companies.forEach(company => {
      company.retrievedImage = 'data:image/jpeg;base64,'+company.companyLogo;
    });
  }

  groupBySkills(array:CourseSuggestion[]): string[]{
    let skills:string[] = [];
    
    for(let i=0; i<array.length; i++ ){
      if (i==0 || skills.indexOf(array[i].skillType) == -1)
        skills.push(array[i].skillType)
    }
    skills.forEach(element => {
      console.log(element)
    });
  
    return skills;
  }


  setSkillTemplateLength(length:number){
    let b = Math.floor(length/6)
    let c = (length%6 == 0) ? b : b+1; 
    for(let i=0; i<c; i++){
      this.skillTemplate[i]=i;
    }
  }
  setCourseTemplateLength(length:number){
    let b = Math.floor(length/6)
    let c = (length%6 == 0) ? b : b+1; 
    for(let i=0; i<c; i++){
      this.courseTemplate[i]=i;
    }
  }
  setLogoTemplateLength(length:number){
    let b = Math.floor(length/9)
    let c = (length%9 == 0) ? b : b+1; 
    for(let i=0; i<c; i++){
      this.logoTemplate[i]=i;
    }
  }

  getCompanies(){
    return this.http.get<CompanyDetails[]>(this.getAllCompaniesGetRequest);
  }
  getSuggestions(){
    return this.http.get<CourseSuggestion[]>(this.getAllSuggestionsGetRequest)
  }
}
