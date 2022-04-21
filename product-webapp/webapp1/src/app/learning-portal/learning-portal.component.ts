import { BreakpointObserver, Breakpoints, BreakpointState } from '@angular/cdk/layout';
import { HttpClient } from '@angular/common/http';
import { Component, OnInit, ViewChild } from '@angular/core';
import { FormControl } from '@angular/forms';
import { MatAccordion } from '@angular/material/expansion';
import { NgbCarouselConfig } from '@ng-bootstrap/ng-bootstrap';
import { last, map, Observable, startWith, Subject, takeUntil } from 'rxjs';
import { CompanyDetails } from '../model/company-details';
import { CourseSuggestion } from '../model/course-suggestion';
import { ReviewService } from '../service/review/review.service';

@Component({
  selector: 'app-learning-portal',
  templateUrl: './learning-portal.component.html',
  styleUrls: ['./learning-portal.component.css']
})
export class LearningPortalComponent implements OnInit {
  destroyed=new Subject<void>();
  
  // categoryCardsPerSlideMap= new Map([
  //   [Breakpoints.XSmall, 1],
  //   [Breakpoints.Small, 1],
  //   [Breakpoints.Medium, 2],
  //   [Breakpoints.Large, 3],
  //   [Breakpoints.XLarge,5]
  // ])
  courseCardsPerSlideMap= new Map([
    [Breakpoints.XSmall, 1],
    [Breakpoints.Small, 1],
    [Breakpoints.Medium, 2],
    [Breakpoints.Large, 3],
    [Breakpoints.XLarge,6]
  ])
  logoCardsPerSlideMap= new Map([
    [Breakpoints.XSmall, 2],
    [Breakpoints.Small, 3],
    [Breakpoints.Medium, 5],
    [Breakpoints.Large, 7],
    [Breakpoints.XLarge,11]
  ])
  constructor(config: NgbCarouselConfig, private http: HttpClient,private breakpointObserver:BreakpointObserver, private reviewService:ReviewService) { 
     config.showNavigationArrows = true;
     config.showNavigationIndicators = false;
     config.pauseOnFocus = true;
     config.pauseOnHover = true;

    
     
    this.requestResource();
  }


  ngOnInit(): void {
    this.filteredOptions = this.query.valueChanges.pipe(
      startWith(''),
      map(value => this.searchCourses(value)),
    );
  }
  
  ///////////////////////////Variables//////////////////////////////////////
  @ViewChild(MatAccordion)
  accordion: MatAccordion = new MatAccordion();
 
  queryString:string='';
  query=new FormControl();
  queryValue:string='';
  clearQuery:boolean=true;
  options: string[] = [];
  filteredOptions!: Observable<string[]>;
  

  // private getSkillTypesByCategory = "http://localhost:8087/api/v1/resources/suggestions/get_skillTypes_by_category";
  // private getUrlBySkillType = "http://localhost:8087/api/v1/resources/suggestions/getSourceBySkills";

  courses:SourceUrlAggregate[]=[];
  skillTypes: string[]=[];
  suggestion: string[]=[];

  categoryTemplate:any[]=[];
  courseTemplate:any[]=[];
  logoTemplate:any[]=[];
  cardsPerSlide = {
    "category":0,
    "course":0,
    "logo":0
  }

  

  skillAggregate:SkillAggregate[]=[];
  companies: CompanyDetails[] = [];
  logoLocation = "../../assets/learning-portal/CourseProviderLogos/"
  skillImageLocation="../../assets/learning-portal/skillType-Images/"
  skillImage:string|undefined;
  skilTypeImages:string[]=[
    "GoTo Course.png",
    "Angular.png",
    "bootstrap.jpg",
    "css.png",
    "Data Science.png",
    "html.png",
    "java.png",
    "JavaScript.png",
    "mongoDB.png",
    "My Sql.png",
    "Spring Boot.png",
    "Web Development.png"
  ]
  companyLogos: string[] = [
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
  categoryImages: string[]=[
    "Math & Logic.jpg",
    "Personality Development.jpg",
    "Technology Courses.jpg",
    "Banking And Finance.jpg",
    "Fin Tech.jpg"
  ]

  ///////////////////////////Methods//////////////////////////////////////
  getCardsPerSlide(){
    return 8;
  }
  //************  Search Methods

  queryReSet(){
    this.query = new FormControl();
  }
  
  getAllSkills():string[]{
    let array:string[]=[];

    this.skillAggregate.forEach(
      element=>{
        element.skillTypes.forEach(
          obj=>{
            array.push(obj);
          })
      })
    
    return array; 
  };
  
  hideCourses(){
    this.accordion.closeAll();
  }
  /**
   * @param queryValue: takes the search query value as param
   * 
   * update the auto completion as users type;
   * 
   * search whether the entered skill is available or not
   * and filters options[] results. 
   */
  searchCourses(queryValue:string) : string[]{
     const searchQuery = queryValue.toLowerCase();
     
    return this.options.filter((option)=>
      option.toLowerCase().includes(searchQuery)
    );
  }

  /**
   * 
   * makes the search result block visible
   * 
   * search this.courses[] for required skillType
   * according change the output in suggestion [] with href links
   * 
   * also update the carousel with slide and cards needed to show. 
   * 
   * @param event :takes either the element or search query value as 
   *              {target:{innerText: Here Name Of Skill/Course}}
   */
  
  onSkillTypeClick(event:any){
    // this.hideCourseVariable = false;
    this.accordion.openAll();
    
    let skill:string = event.target.innerText;
    this.queryValue = skill;
    this.skillImageAddOn(skill);
    
    this.courses.forEach((course)=>{
      if(skill == course.skillType)
        this.suggestion = course.source;
    }) 
    // this.suggestion = this.courses.find((course)=>{skill == course.skillType})?.source;
    if(this.suggestion){
      this.courseTemplate = this.carouselSlidesAndCards(this.suggestion.length, 5);
      this.cardsPerSlide.course=5;


      ///////////Responsive//////////////////////////
      this.breakpointObserver.observe([
        Breakpoints.XSmall,
        Breakpoints.Small,
        Breakpoints.Medium,
        Breakpoints.Large,
        Breakpoints.XLarge
      ]).pipe(takeUntil(this.destroyed))
      .subscribe(result=>{
      for(const query of Object.keys(result.breakpoints)){
        if(result.breakpoints[query]){
          this.responsiveCourseSlideAndCards(this.courseCardsPerSlideMap.get(query)??0); 
        }
      }
    })
    /////////////////////////////////////
  }
}
    
    skillImageAddOn(skillType:string){
      this.skillImage = this.skilTypeImages.find((image)=>
      image.toLowerCase().split(".",2)[0] == skillType.toLowerCase()
      )
      if(this.skillImage == undefined)
        this.skillImage = this.skilTypeImages[0];
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
    return array;
  }

  responsiveSlideAndCards( categoryCards:number, logoCards:number){
    
        this.categoryTemplate = this.carouselSlidesAndCards(this.skillAggregate.length, categoryCards);
        this.cardsPerSlide.category = categoryCards;
        this.logoTemplate = this.carouselSlidesAndCards(this.companyLogos.length,logoCards);
        this.cardsPerSlide.logo=logoCards;
  }
  responsiveCourseSlideAndCards(courseCards:number){
    this.courseTemplate = this.carouselSlidesAndCards(this.suggestion.length, courseCards);
    this.cardsPerSlide.course = courseCards;
  }


  


  requestResource(){
    if(this.companyLogos.length>0){
      this.logoTemplate = this.carouselSlidesAndCards(this.companyLogos.length,10)
      this.cardsPerSlide.logo=10;}

      this.reviewService.getCategoriesAndSkillTypes().subscribe((response:SkillAggregate[])=>{
        this.skillAggregate = response;  
      
        if(this.skillAggregate.length > 0){
          this.categoryTemplate = this.carouselSlidesAndCards(this.skillAggregate.length, 5);
          this.cardsPerSlide.category=5
          this.options = this.getAllSkills();
        }        
        this.categoryImages.forEach(pic=>{
          this.skillAggregate.forEach(skill=>{
            if(skill.category.toLowerCase() == pic.toLowerCase().split(".")[0]){
              var newString:string='';
              pic.split(" ").forEach(
                word=>{
                  word=word+'\\ '
                  newString=newString+word
                })
                skill.image = '../../assets/learning-portal/'+newString.substring( 0,newString.length-2);
            }
          })
        })
  
        // this.breakpointObserver.observe([
        //   Breakpoints.XSmall,
        //   Breakpoints.Small,
        //   Breakpoints.Medium,
        //   Breakpoints.Large,
        //   Breakpoints.XLarge
        // ]).pipe(takeUntil(this.destroyed))
        // .subscribe(result=>{
        //   for(const query of Object.keys(result.breakpoints)){
        //     if(result.breakpoints[query]){
        //       this.responsiveSlideAndCards(this.categoryCardsPerSlideMap.get(query)?? 0,
        //                                       this.logoCardsPerSlideMap.get(query)??0); 
        //     }
        //   }
        // })
      })
    this.reviewService.getSourceUrlBySkillTypes().subscribe((response:SourceUrlAggregate[])=>{
      this.courses = response;
    })
  }
}

export class SkillAggregate{
  category:string;
  image:string;
  skillTypes:string[];

  constructor(category:string,skillTypes:string[], image:string){
    this.category = category;
    this.skillTypes = skillTypes;
    this.image = image;
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


   

  
