import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { JobDetailComponent } from './job-detail/job-detail.component';
import { JobApplyComponent } from './job-apply/job-apply.component';
import { JobPostingComponent } from './job-posting/job-posting.component';
import { NavBarComponent } from './nav-bar/nav-bar.component';
import { SalaryTrendLabComponent } from './salary-trend-lab/salary-trend-lab.component';
import { SkillsTrendLabComponent } from './skills-trend-lab/skills-trend-lab.component';
import { TrendLabComponent } from './trend-lab/trend-lab.component';
import { ChatbotComponent } from './chatbot/chatbot.component';
import { CvGenerationComponent } from './cv-generation/cv-generation.component';
import { LearningPortalComponent } from './learning-portal/learning-portal.component';
import { AssesmentPortalComponent } from './assesment-portal/assesment-portal.component';


import { LoginComponent } from './login/login.component';
import { NotFoundComponent } from './not-found/not-found.component';
import { AssesmentTestPortalComponent } from './assesment-test-portal/assesment-test-portal.component';

import { RegisterComponent } from './register/register.component';
import { SkilltestComponent } from './skilltest/skilltest.component';
import { UpdateDetailsComponent } from './update-details/update-details.component';
import { UpdateUserDetailsComponent } from './update-user-details/update-user-details.component';
import { OrganizationDetailsComponent } from './organization-details/organization-details.component';
import { JobSeekersRegisterComponent } from './job-seekers-register/job-seekers-register.component';

import { TechnewsComponent } from './technews/technews.component';
import { BusinessnewsComponent } from './businessnews/businessnews.component';

import { OrganizationDetails } from './model/organizationDetails';
import { ReviewComponent } from './review/review.component';
import { ChatroomComponent } from './chatroom/chatroom.component';
import { CvTemplate1Component } from './cv-generation/cv-template1/cv-template1.component';
import { RecruiterlandingComponent } from './recruiterlanding/recruiterlanding.component';

import { JobSeekerLandingComponent } from './job-seeker-landing/job-seeker-landing.component';
import { TestResultViewComponent } from './test-result-view/test-result-view.component';




const routes: Routes = [
  {path: 'trend',component: TrendLabComponent},
  {path: 'skills',component: SkillsTrendLabComponent},
  {path : 'salary',component: SalaryTrendLabComponent},
  { path : 'technews', component: TechnewsComponent },
  { path : 'businessnews', component: BusinessnewsComponent },
  {path:'job-apply',component:JobApplyComponent},
  {path:'job-posting',component:JobPostingComponent},
  {
    path:'cv-generation',component:CvGenerationComponent
  },
  {
    path:'cv-generation1',component:CvTemplate1Component
  },
  {
    path:"jobdetail",
    component:JobDetailComponent,

  },
  {path:"chatbot",component:ChatbotComponent},
  {path:"chatroom",component:ChatroomComponent},
  {
    path:"jobdetail",
    component:JobDetailComponent,

  },
  {path:"assesmentportal",component:AssesmentPortalComponent},
  {path:"assesmenttest",component:AssesmentTestPortalComponent},
  {path:"test",component:SkilltestComponent},
  {path:'jobdetail',component:JobDetailComponent},
  {path: 'learning', component:LearningPortalComponent},
  {path:'result',component:TestResultViewComponent},

  // providing path for register and login
  {path:"register",component:RegisterComponent},
  {path:"jobSeekerRegister",component:JobSeekersRegisterComponent},


  // material component
  {path:"jobSeekersRegister",component:JobSeekersRegisterComponent},


  {path:"organizationRegister",component:OrganizationDetailsComponent},
  {path:"userLogin",component:LoginComponent},
  {path: "not-found", component:NotFoundComponent},
  // {path:"Organization",component:OrganizationDetails},

  { path:"recruiterLanding", component: RecruiterlandingComponent },


  {path:"update",component:UpdateDetailsComponent},
  {path:"update-user",component:UpdateUserDetailsComponent},
  {path:"review",component:ReviewComponent},

  {path:"jobSeeker",component:JobSeekerLandingComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
