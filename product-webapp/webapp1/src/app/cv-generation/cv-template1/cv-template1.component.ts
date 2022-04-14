import { Component, OnInit } from '@angular/core';
import { jsPDF } from 'jspdf';
import html2canvas from 'html2canvas';

@Component({
  selector: 'app-cv-template1',
  templateUrl: './cv-template1.component.html',
  styleUrls: ['./cv-template1.component.css']
})
export class CvTemplate1Component implements OnInit {

  Name:String="Sajidha Mohammed";

  constructor() { }

  ngOnInit(): void {
  }
  public convertToPDF()
      {
      const data = document.getElementById('cv-body')!;
      html2canvas(data).then(canvas => {
      // Few necessary setting options
      var imgWidth = 205;
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
