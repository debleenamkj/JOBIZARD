import { Component } from '@angular/core';
import { map } from 'rxjs/operators';
import { Breakpoints, BreakpointObserver } from '@angular/cdk/layout';

@Component({
  selector: 'app-assesment-portal',
  templateUrl: './assesment-portal.component.html',
  styleUrls: ['./assesment-portal.component.css']
})
export class AssesmentPortalComponent {
  /** Based on the screen size, switch from standard to one column per row */
  cards = this.breakpointObserver.observe(Breakpoints.Handset).pipe(
    map(({ matches }) => {
      if (matches) {
        return [
          { title: 'Card 1', cols: 1, rows: 1 },
          { title: 'Card 1', cols: 1, rows: 1 },
         
        ];
      }

      return [
        { title: 'Candidate Category', cols: 2, rows: 1 },
        { title: 'Test', cols: 2, rows: 1 },
       
      ];
    })
  );

  constructor(private breakpointObserver: BreakpointObserver) {}
}
