export class EmailRequest{
    to: string;
    companyName: string;
  
    constructor(to?:string,companyName?:string){
      this.to=to
      this.companyName=companyName
    }
}