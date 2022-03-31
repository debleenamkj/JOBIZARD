import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { JobDetailComponent } from './job-detail/job-detail.component';


  

import { JobApplyComponent } from './job-apply/job-apply.component';
import { JobPostingComponent } from './job-posting/job-posting.component';
import { NavBarComponent } from './nav-bar/nav-bar.component';
import { SalaryTrendLabComponent } from './salary-trend-lab/salary-trend-lab.component';
import { SkillsTrendLabComponent } from './skills-trend-lab/skills-trend-lab.component';
import { TrendLabComponent } from './trend-lab/trend-lab.component';

const routes: Routes = [
  {path: 'trend',component: TrendLabComponent},
  {path: 'skills',component: SkillsTrendLabComponent},
  {path : 'salary',component: SalaryTrendLabComponent},
  {path:'job-apply',component:JobApplyComponent},
  {path:'job-posting',component:JobPostingComponent},
  {
    path:"jobdetail",
    component:JobDetailComponent,
    
  }

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
