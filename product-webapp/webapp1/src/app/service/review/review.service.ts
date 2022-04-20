import { HttpClient } from '@angular/common/http';
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

  
  // baseUrl = environment.apiBaseUrl+'/resources-service';
  //baseUrl = 'http://localhost:8087';  
}

