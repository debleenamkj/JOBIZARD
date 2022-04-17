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
          image= '../../assets/learning-portal/'+newString.substring( 0,newString.length-2);
          this.backgroundImages.push(image);
          console.log(image);
          console.log(this.backgroundImages);
    })
   
  }

  images:string[]=["Banking And Finance.jpg","Fin Tech.jpg","Math & Logic.jpg"];
  backgroundImages:string[]=[];
  // location:string="../../assets/learning-portal/"
  card:any[]=["1","2","3"]

}
