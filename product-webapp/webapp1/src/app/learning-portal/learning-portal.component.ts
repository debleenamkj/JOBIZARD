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

    /*this.getCompanies().subscribe((response: CompanyDetails[])=>{
      this.companies = response;
      this.retrieveLogos(this.companies)
      console.log(this.companies);
      if(this.companies.length>0)
      this.logoSlideCount = this.calculateNumberOfSlidesForCarouselTemplate(this.companies.length,8);
    })*/

    if(this.companyLogos.length>0)
      this.logoSlideCount = this.calculateNumberOfSlidesForCarouselTemplate(this.companyLogos.length,8)

    this.getCategoriesAndSkillTypes().subscribe((response:SkillAggregate[])=>{
      this.skillAggregate = response;  
      console.log(this.skillAggregate);
      if(this.skillAggregate.length > 0){
        this.categorySlideCount = this.calculateNumberOfSlidesForCarouselTemplate(this.skillAggregate.length, 5);
      }
    })

    this.getSourceUrlBySkillTypes().subscribe((response:SourceUrlAggregate[])=>{
      this.courses = response;
      console.log(this.courses);
    })
  }

  ngOnInit(): void {
  }

  private getAllCompaniesGetRequest = "http://localhost:8087/api/v1/resources/get_all_companies";
  private getSkillTypesByCategory = "http://localhost:8087/api/v1/resources/suggestions/get_skillTypes_by_category";
  private getUrlBySkillType = "http://localhost:8087/api/v1/resources/suggestions/getSourceBySkills";

  

  courses:SourceUrlAggregate[]=[];
  skillTypes: string[]=[];
  suggestion: string[]=[];
  cardsPerSlide:number[]=[0,1,2,3,4,5,6,7]

  categorySlideCount:number[]=[];
  courseSlideCount:number[]=[];
  logoSlideCount:number[]=[];

  hideCourseVariable = true;

  skillAggregate:SkillAggregate[]=[];
  companies: CompanyDetails[] = [];
  logoLocation = "../../assets/learning-portal/CourseProviderLogos/"
  companyLogos: String[] = [
    "NIIT-logo.png",
    "codecademy-logo.jpg",
    "coursera-logo.png",
    "edureka-logo.png",
    "edX-logopng.png",
    "Qwiklabs-logo.jpg",
    "skillShare-logo.png",
    "swayam-logo.png",
    "udemy-logopng.png",
    "unacademy-logo.svg"
  ];
  

  hideCourses(){
    this.hideCourseVariable = true;
  }

  retrieveLogos(companies:CompanyDetails[]){
    companies.forEach(company => {
      company.retrievedImage = 'data:image/jpeg;base64,'+company.companyLogo;
    });
  }

  /*groupBySkills(array:any[]): string[]{
    let skills:string[] = [];
    
    for(let i=0; i<array.length; i++ ){
      if (i==0 || skills.indexOf(array[i].skillType) == -1)
        skills.push(array[i].skillType)
    }
    skills.forEach(element => {
      console.log(element)
    });
  
    return skills;
  }*/
  onSkillTypeClick(event:any){
    this.hideCourseVariable = false;
    console.log(event.target.innerText)
    let skill:string = event.target.innerText;
    this.courses.forEach((course)=>{
      if(skill == course.skillType)
        this.suggestion = course.source;
    })
    //this.suggestion = this.courses.find((course)=>{skill == course.skillType})?.source;
    if(this.suggestion)
      this.courseSlideCount = this.calculateNumberOfSlidesForCarouselTemplate(this.suggestion.length, 5);
  }

  calculateNumberOfSlidesForCarouselTemplate(totalCards:number, cardsPerSlide:number):number[]{
    let template:number[]=[];
    let noOfSlides = Math.floor(totalCards/cardsPerSlide);
    noOfSlides = (totalCards%cardsPerSlide == 0) ? noOfSlides : noOfSlides+1;
    for(let i=0; i<noOfSlides; i++){
      template.push(i);
    }
    return template;
  }
  calculateNumberOfCardsPerSlide = (totalCards:number):number[]=>{
    let array:number[]=[];
    for(let i=0; i<totalCards; i++){
      array.push(i);
    }
    return array;
  }


  getCompanies(){
    return this.http.get<CompanyDetails[]>(this.getAllCompaniesGetRequest);
  }
  getCategoriesAndSkillTypes(){
    return this.http.get<SkillAggregate[]>(this.getSkillTypesByCategory);
  }
  getSourceUrlBySkillTypes(){
    return this.http.get<SourceUrlAggregate[]>(this.getUrlBySkillType);
  }
}

export class SkillAggregate{
  category:string;
  skillTypes:string[];

  constructor(category:string,skillTypes:string[]){
    this.category = category;
    this.skillTypes = skillTypes;
  }
}
export class SourceUrlAggregate{
  skillType:string;
  source:string[];
  constructor(skillType:string, source:string[]){
    this.skillType=skillType;
    this.source=source;
  }
}
