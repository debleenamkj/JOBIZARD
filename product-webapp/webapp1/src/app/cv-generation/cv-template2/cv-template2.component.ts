import { Component, OnInit } from '@angular/core';
import { jsPDF } from 'jspdf';
import html2canvas from 'html2canvas';
import { CvTemplateService } from 'src/app/service/cv-generation/cv-template.service';

@Component({
  selector: 'app-cv-template2',
  templateUrl: './cv-template2.component.html',
  styleUrls: ['./cv-template2.component.css']
})
export class CvTemplate2Component implements OnInit {
  
  response:any
  constructor(private cvT: CvTemplateService) {
    this.cvT.getCv()
   .subscribe((response:any)=>{
    this.response=response;
    console.log(response);
   });
  }
  
  ngOnInit(): void {
  }
  public convertToPDF()
  {
  const data = document.getElementById('cv-template')!;
  html2canvas(data).then(canvas => {
  // Few necessary setting options
  var imgWidth = 208;
  var pageHeight = 200;
  var imgHeight = canvas.height * imgWidth / canvas.width;
  var heightLeft = imgHeight;
  
  const contentDataURL = canvas.toDataURL('image/png')
  let pdf = new jsPDF('p', 'mm', 'a4'); // A4 size page of PDF
  var position = 0;
  pdf.addImage(contentDataURL, 'PNG', 0, position, imgWidth, imgHeight)
  pdf.save('new-file.pdf'); // Generated PDF
  });
  }
}
