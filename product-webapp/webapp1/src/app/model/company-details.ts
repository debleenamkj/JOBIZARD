import { Review } from "../review-form/review-form.component";

export class CompanyDetails {
    cin:string;
    companyName:string;
    companyLogo:any;
    retrievedImage?:any;
    reviews?:Review[]
    avgRating?:number

    constructor(cin:string, companyName:string, companyLogo?:File, retrievedImage?:any, reviews?:Review[],avgRating?:number){
        this.cin = cin;
        this.companyName=companyName;
        this.companyLogo = companyLogo;
        this.retrievedImage = retrievedImage;
        this.reviews = reviews;
        this.avgRating = avgRating;
    }
}
