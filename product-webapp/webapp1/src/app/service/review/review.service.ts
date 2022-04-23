import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { CompanyDetails } from 'src/app/model/company-details';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class ReviewService {
  //[x: string]: CompanyDetails;
  
  constructor(private http:HttpClient) { }

  selectedCompany!:CompanyDetails;
  companyName!:any;
  to! :any;

  
  baseUrl = 'https://jobizard.stackroute.io'+'/resources-service';
  // baseUrl = 'http://localhost:8087';


  private postReviewRequest = this.baseUrl+'/api/v1/resources/saveReview';
  private deleteReviewRequest =this.baseUrl+'/api/v1/resources/deleteReview';


  private getAllCompaniesGetRequest = this.baseUrl+"/api/v1/resources/get_all_companies";
  private getCompanyByCompanyNameRequest=this.baseUrl+"/api/v1/resources/get_company";
  private getReviewsByCompanyNameRequest=this.baseUrl+"/api/v1/resources/get_reviews";
  private getAllDetailsRequest=this.baseUrl+"/api/v1/resources/getAllDetails";


  private getSkillTypesByCategory = this.baseUrl+"/api/v1/resources/suggestions/get_skillTypes_by_category";
  private getUrlBySkillType = this.baseUrl+"/api/v1/resources/suggestions/getSourceBySkills";


  postReview( review:Review, companyName:string){
    return this.http.post<Review>(this.postReviewRequest, review, 
      {params:new HttpParams().append('companyName', companyName)});
  }
  deleteReview(reviewId:number, companyName:string){
    let param = new HttpParams();
    param = param.append("reviewId", reviewId);
    param = param.append("companyName", companyName);
    return this.http.delete<boolean>( this.deleteReviewRequest,{params: param});
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
  getCategoriesAndSkillTypes(){
    return this.http.get<SkillAggregate[]>(this.getSkillTypesByCategory);
  }
  getSourceUrlBySkillTypes(){
    return this.http.get<SourceUrlAggregate[]>(this.getUrlBySkillType);
  }
}

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

export interface CompanyNameGroup {
  letter: string;
  names: string[];
}

export class SkillAggregate{
  category:string;
  image:string;
  skillTypes:string[];

  constructor(category?:string,skillTypes?:string[], image?:string){
    this.category = category;
    this.skillTypes = skillTypes;
    this.image = image;
  }
}
export class SourceUrlAggregate{
  skillType:string;
  source:string[];

  constructor(skillType?:string, source?:string[]){
    this.skillType=skillType;
    this.source=source;
  }
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