import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { SalaryTrendLabComponent } from './salary-trend-lab/salary-trend-lab.component';
import { SkillsTrendLabComponent } from './skills-trend-lab/skills-trend-lab.component';
import { TrendLabComponent } from './trend-lab/trend-lab.component';

const routes: Routes = [
  {
    path: 'trend',component: TrendLabComponent
  },
  {
    path: 'skills',component: SkillsTrendLabComponent
  },
  {
    path : 'salary',component: SalaryTrendLabComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
