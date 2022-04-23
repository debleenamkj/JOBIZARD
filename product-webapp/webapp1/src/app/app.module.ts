import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { CvGenerationComponent } from './cv-generation/cv-generation.component';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatDividerModule } from '@angular/material/divider';
import { JobApplyComponent } from './job-apply/job-apply.component';
import { JobPostingComponent } from './job-posting/job-posting.component';
import { JobDetailComponent } from './job-detail/job-detail.component';
import { TrendLabComponent } from './trend-lab/trend-lab.component';
import { SkillsTrendLabComponent } from './skills-trend-lab/skills-trend-lab.component';
import { SalaryTrendLabComponent } from './salary-trend-lab/salary-trend-lab.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule, HttpInterceptor } from '@angular/common/http';
import { ChatbotComponent } from './chatbot/chatbot.component';
import { NavBarComponent } from './nav-bar/nav-bar.component';
import { LayoutModule } from '@angular/cdk/layout';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatButtonModule } from '@angular/material/button';
import { MatSidenavModule } from '@angular/material/sidenav';
import { MatIconModule } from '@angular/material/icon';
import { MatListModule } from '@angular/material/list';
import { MatStepperModule } from '@angular/material/stepper';
import { MatSelectModule } from '@angular/material/select';
import { MatAutocompleteModule } from '@angular/material/autocomplete';
import { MatChipsModule } from '@angular/material/chips';
import { MatTooltipModule } from '@angular/material/tooltip';
import { MatMenuModule } from '@angular/material/menu';
import { MatCardModule } from '@angular/material/card';
import { MatGridListModule } from '@angular/material/grid-list';
import { MatRippleModule, MatNativeDateModule } from '@angular/material/core';
import { MatRadioModule } from '@angular/material/radio';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatSnackBarModule } from '@angular/material/snack-bar';
import { MatCheckboxModule } from '@angular/material/checkbox';
import { MatSliderModule } from '@angular/material/slider';
import { MatProgressBarModule } from '@angular/material/progress-bar';
import { MatExpansionModule } from '@angular/material/expansion';
import { MatTabsModule } from '@angular/material/tabs';
import { MatDialogModule } from '@angular/material/dialog';
import { LearningPortalComponent } from './learning-portal/learning-portal.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { AssesmentPortalComponent } from './assesment-portal/assesment-portal.component';
import { LoginComponent } from './login/login.component';
import { MatPaginatorModule } from '@angular/material/paginator';
import { UpdateDetailsComponent } from './update-details/update-details.component';
import { CommonModule } from '@angular/common';
// import { FormBuilder, FormGroup } from '@angular/forms';

import { SkilltestComponent } from './skilltest/skilltest.component';




import { NotFoundComponent } from './not-found/not-found.component';

import { CvTemplate1Component } from './cv-generation/cv-template1/cv-template1.component';
import { CvTemplate2Component } from './cv-generation/cv-template2/cv-template2.component';
import { CvTemplate3Component } from './cv-generation/cv-template3/cv-template3.component';
import { RegisterComponent } from './register/register.component';
import { AssesmentTestPortalComponent } from './assesment-test-portal/assesment-test-portal.component';
import { UpdateUserDetailsComponent } from './update-user-details/update-user-details.component';

import { TechnewsComponent } from './technews/technews.component';

import { ChatroomComponent } from './chatroom/chatroom.component';
import { OrganizationDetailsComponent } from './organization-details/organization-details.component';
import { JobSeekersRegisterComponent } from './job-seekers-register/job-seekers-register.component';


import { ReviewComponent } from './review/review.component';
import { BusinessnewsComponent } from './businessnews/businessnews.component';
import { ReviewFormComponent } from './review-form/review-form.component';


import { TockenInterceptorService } from './service/tocken-interceptor.service';

import { RecruiterlandingComponent } from './recruiterlanding/recruiterlanding.component';

import { JobSeekerLandingComponent } from './job-seeker-landing/job-seeker-landing.component';



import { JobSeekerProfileComponent } from './job-seeker-profile/job-seeker-profile.component';
import { SearchPortalComponent } from './search-portal/search-portal.component';
import { TestResultViewComponent } from './test-result-view/test-result-view.component';
import { ImagePostDialogComponent } from './image-post-dialog/image-post-dialog.component';
import { BlogPostDialogComponent } from './blog-post-dialog/blog-post-dialog.component';
import { RecruiterRegisterComponent } from './recruiter-register/recruiter-register.component';
import { HomepageComponent } from './homepage/homepage.component';
import { AddSkillsComponent } from './add-skills/add-skills.component';
import { environment } from 'src/environments/environment';





@NgModule({
  declarations: [
    AppComponent,
    CvGenerationComponent,
    JobDetailComponent,
    LearningPortalComponent,
    TrendLabComponent,
    JobPostingComponent,
    JobApplyComponent,
    NavBarComponent,
    SkillsTrendLabComponent,
    SalaryTrendLabComponent,
    ChatbotComponent,
    JobApplyComponent,
    JobPostingComponent,
    JobDetailComponent,
    AssesmentPortalComponent,
    RegisterComponent,
    LoginComponent,
    UpdateDetailsComponent,
    LoginComponent,
    SkilltestComponent,
    AssesmentTestPortalComponent,

    ChatroomComponent,

    CvTemplate1Component,
    CvTemplate2Component,
    CvTemplate3Component,

    JobSeekersRegisterComponent,

    NotFoundComponent,

    RegisterComponent,
    LoginComponent,

    AssesmentTestPortalComponent,
    CvTemplate1Component,
    CvTemplate2Component,
    CvTemplate3Component,
    UpdateUserDetailsComponent,
    OrganizationDetailsComponent,
    JobSeekersRegisterComponent,
    TechnewsComponent,
    ReviewComponent,
    BusinessnewsComponent,
    ReviewFormComponent,
    TechnewsComponent,
    BusinessnewsComponent,

    RecruiterlandingComponent,
    JobSeekerLandingComponent,
    JobSeekerProfileComponent,
    SearchPortalComponent,
    TestResultViewComponent,
    ImagePostDialogComponent,
    BlogPostDialogComponent,
    RecruiterRegisterComponent,
    HomepageComponent,
    AddSkillsComponent

  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MatChipsModule,
    MatDatepickerModule,
    MatStepperModule,
    MatButtonModule,
    LayoutModule,
    FormsModule,
    // FormBuilder,
    // FormGroup,
    ReactiveFormsModule,
    HttpClientModule,
    MatFormFieldModule,
    MatInputModule,
    MatListModule,
    MatIconModule,
    MatSidenavModule,
    MatButtonModule,
    MatToolbarModule,
    MatButtonModule,
    ReactiveFormsModule,
    FormsModule,
    HttpClientModule,
    LayoutModule,
    MatDividerModule,
    MatSelectModule,
    MatFormFieldModule,
    MatAutocompleteModule,
    MatChipsModule,
    MatTooltipModule,
    MatMenuModule,
    MatCardModule,
    MatGridListModule,
    MatRippleModule,
    MatNativeDateModule,
    MatDatepickerModule,
    MatCheckboxModule,
    MatRadioModule,
    MatSnackBarModule,
    MatSliderModule,
    MatProgressBarModule,
    NgbModule,
    MatExpansionModule,
    MatTabsModule,
    MatDialogModule,
    CommonModule,
    MatPaginatorModule
  ],
  providers: [TockenInterceptorService],
  bootstrap: [AppComponent],
  schemas: [CUSTOM_ELEMENTS_SCHEMA],
  entryComponents: [UpdateUserDetailsComponent]
})
export class AppModule { }
