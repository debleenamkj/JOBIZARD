export class RecruiterDetails {
    emailId!:string;
    skillsRequired!:string[]
    companyName!:string;
    educationRequired!:string
    constructor( emailId?:string,
        skillsRequired?:string[],
        companyName?:string,
        educationRequired?:string){
        this.companyName=companyName
        this.emailId=emailId
        this.skillsRequired=skillsRequired
        this.educationRequired=educationRequired
    }
}
