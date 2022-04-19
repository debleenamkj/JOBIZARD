// import {UserLogin} from "./userLogin"
// public user:UserLogin
export class UserDetails {

    public firstName:string="";
       public lastName:string="";
       public gender:string="";
       public dateOfBirth:string="";
       public objective:string="";
       public mobileNumber:string="";
       public profilePicture:any="";
       public lane:string="";
       public state:string="";
       public city:string="";
       public pincode:string="";
       public nationality:string="";
       public academicCertification:string[]=[];
       public skillSet:string[]=[];
       public jobPreference:string[]=[];
       public achievements:string[]=[]
    constructor(){
        this.firstName="",
        this.lastName="",
        this.gender="",
        this.dateOfBirth="",
        this.mobileNumber="",
        this.objective="",
        this.profilePicture="",
        this.lane="",
        this.state="",
        this.city="",
        this.pincode="",
        this.nationality="",
        this.academicCertification=[],
        this.skillSet=[],
        this.jobPreference=[],
        this.achievements=[]

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