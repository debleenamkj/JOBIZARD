import { Byte } from "@angular/compiler/src/util";


export class RecruiterLandingData
{
    emailId: string
    firstName!: string
    companyName!: string
    companyUrl!: string
    logo!: Byte[]
    logoImage!: any

    constructor( emailId?: string, firstName?: string, companyName?: string, logo?: Byte[], logoImage?: any){
        this.emailId=emailId
        this.firstName=firstName
        this.companyName=companyName
        this.logo=logo
        this.logoImage=logoImage
    }

}
