import { Component, OnInit } from '@angular/core';
import { NgbCarouselConfig } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-homepage',
  templateUrl: './homepage.component.html',
  styleUrls: ['./homepage.component.css']
})
export class HomepageComponent implements OnInit {

  constructor(config:NgbCarouselConfig) {
     config.showNavigationArrows = true;
     config.showNavigationIndicators = false;
     config.pauseOnFocus = true;
     config.pauseOnHover = true;
   }

  ngOnInit(): void {
  this.images.forEach(image=>{
      // if(image.toLowerCase() == pic.toLowerCase().split(".")[0]){
        var newString:string='';
          image.split(" ").forEach(
          word=>{
            word=word+'\\ '
            newString=newString+word
          })
          image= '../../assets/home-page/'+newString.substring( 0,newString.length-2);
          this.backgroundImages.push(image);
    })
   
  }

  images:string[]=["18.jpg","19.jpg","14.png","15.jpg"];
  backgroundImages:string[]=[];
  // location:string="../../assets/learning-portal/"
  card:any[]=["../../assets/home-page/12.webp","2","3"]
  ourFeatureImagesLocation:string="../../assets/home-page/";
  ourFeatures:OurFeatures[] = [
    {
      imageName:"jzi.png",
      description:" Getting the latest information on demanding " +
      "skills and other related news made available, "+
       "so you don't need to look for various platforms and "+
       "only use Jobizard to stay updated about industry new with detailed stats."
    },
    {
      imageName:"22.png",
      description:"Recruitment made easier than ever, no need to worry about hiring skilled "+
      "candidates, Verified and assesed profiles are made available through our search filters "+
      "designed to suit your expectations from the candidates."
    },
    {
      imageName:"resume.png",
      description:"“Your dream job doesn't exist you need to create it “. A good resume along will your skills will help you to "+
      "get your dream job. Try out our resume template to stand out from the crowd. Just fill required fields and "+
      "the resume will be created for you."
    }
  ];
}

type OurFeatures={
  imageName:string
  description:string
}