export class CompanyDetails {
    cin:string;
    companyName:string;
    companyLogo:any;

    constructor(cin:string, companyName:string, companyLogo:File){
        this.cin = cin;
        this.companyName=companyName;
        this.companyLogo = companyLogo;
    }
}
