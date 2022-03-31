export class CompanyDetails {
    cin:string;
    companyName:string;
    companyLogo:any;
    retrievedImage?:any;

    constructor(cin:string, companyName:string, companyLogo:File, retrievedImage?:any){
        this.cin = cin;
        this.companyName=companyName;
        this.companyLogo = companyLogo;
        this.retrievedImage = retrievedImage;
    }
}
