// import {UserLogin} from "./userLogin"

import { details } from "./details";

// public user:UserLogin
export class UserDetails {
    public seekerProgress:any
    public progress:number=0
    public emailId:string=""
    public firstName:string="";
       public lastName:string="";
       public gender:string="";
       public dateOfBirth:string="";
       public objective:any="";
       public mobileNumber:string="";
    //    public profilePicture:any="";
    //    public lane:string="";
    //    public state:string="";
    //    public city:string="";
    //    public pincode:string="";
    //    public nationality:string="";
    public address:any;
    public additionalDetails:details;

    //    public academicCertification:string[]=[];
    //    public skillSet:string[]=[];
    //    public jobPreference:string[]=[];
    //    public achievements:string[]=[]
    constructor(){
        this.seekerProgress=""
        this.progress=0;
        this.emailId=""
        this.firstName="",
        this.lastName="",
        this.gender="",
        this.dateOfBirth="",
        this.mobileNumber="",
        this.objective="",
        // this.profilePicture="",
        // this.lane="",
        // this.state="",
        // this.city="",
        // this.pincode="",
        // this.nationality="",
        this.address=null
        this.additionalDetails=null
        // this.academicCertification=[],
        // this.skillSet=[],
        // this.jobPreference=[],
        // this.achievements=[]

    }
    

}

// // 
// export class Person {
//  constructor(
//   public name: string,
//    public lastName: string,
//    public age:number
  
//   ) {}
// }
// 