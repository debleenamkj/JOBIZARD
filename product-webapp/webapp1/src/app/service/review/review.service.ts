import { Injectable } from '@angular/core';
import { CompanyDetails } from 'src/app/model/company-details';

@Injectable({
  providedIn: 'root'
})
export class ReviewService {
  [x: string]: CompanyDetails;

  constructor() { }

  selectedCompany!:CompanyDetails;
}
