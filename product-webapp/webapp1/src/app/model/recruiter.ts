import { OrganizationDetails } from "./organizationDetails"

export class Recruiter
{
    emailId!: string
    firstName!: string
    password!: string
    recruiterImage!:any
    organizationDetails!:OrganizationDetails
    
    constructor()
    {
        this.emailId="";
        this.firstName="";
        this.password="";
    }


    // organizationDetails!: [{
    //     organizationID: string;
    //     organizationName: string;
    //     organizationSector: string;
    //     rganizationOrigin: string;
    //     roleInHiring: string;
    //     organizationAddress: string;
    //     contactNumber: number;
    //     organizationLogo: any;
    // }]
}