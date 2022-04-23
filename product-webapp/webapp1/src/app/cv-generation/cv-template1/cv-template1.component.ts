import { Component, OnInit } from '@angular/core';
import { jsPDF } from 'jspdf';
import html2canvas from 'html2canvas';
import { PostService } from 'src/app/service/post/post.service';



@Component({
  selector: 'app-cv-template1',
  templateUrl: './cv-template1.component.html',
  styleUrls: ['./cv-template1.component.css']
})
export class CvTemplate1Component implements OnInit {


  res: any;
  details:any;
  education:any[];
  jobPreferences:any[];
  count: number[] = [, 0, 1, 2, 3];
  emailId: any = localStorage.getItem('loginId');
  constructor(private post: PostService) {
    this.post.getSeeker(this.emailId)
      .subscribe((response: any) => {
        this.res = response;
        console.log("detailss")
        this.details=response.additionalDetails;
        this.jobPreferences=response.additionalDetails.jobPreferences
        this.education=response.educationDetails
        console.log(this.res);
      });
  }

  ngOnInit(): void {

  }
  public convertToPDF() {
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
