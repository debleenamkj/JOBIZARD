import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import {  CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { CvGenerationComponent } from './cv-generation/cv-generation.component';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatInputModule} from '@angular/material/input';
import {MatDividerModule} from '@angular/material/divider';
import { JobApplyComponent } from './job-apply/job-apply.component';
import { JobPostingComponent } from './job-posting/job-posting.component';
import { JobDetailComponent } from './job-detail/job-detail.component';
import { TrendLabComponent } from './trend-lab/trend-lab.component';
import { SkillsTrendLabComponent } from './skills-trend-lab/skills-trend-lab.component';
import { SalaryTrendLabComponent } from './salary-trend-lab/salary-trend-lab.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { ChatbotComponent } from './chatbot/chatbot.component';
import { NavBarComponent } from './nav-bar/nav-bar.component';
import { LayoutModule } from '@angular/cdk/layout';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatButtonModule } from '@angular/material/button';
import { MatSidenavModule } from '@angular/material/sidenav';
import { MatIconModule } from '@angular/material/icon';
import { MatListModule } from '@angular/material/list';
import {MatStepperModule} from '@angular/material/stepper';
import {MatSelectModule} from '@angular/material/select';
import {MatAutocompleteModule} from '@angular/material/autocomplete';
import {MatChipsModule} from '@angular/material/chips';
import {MatTooltipModule} from '@angular/material/tooltip';
import {MatMenuModule} from '@angular/material/menu';
import {MatCardModule} from '@angular/material/card';
import {MatGridListModule} from '@angular/material/grid-list';
import {MatRippleModule,MatNativeDateModule} from '@angular/material/core';
import {MatRadioModule} from '@angular/material/radio';
import {MatDatepickerModule} from '@angular/material/datepicker';
import {MatSnackBarModule} from '@angular/material/snack-bar';
import {MatCheckboxModule} from '@angular/material/checkbox';
import { MatSliderModule } from '@angular/material/slider';
import {MatProgressBarModule} from '@angular/material/progress-bar';

import { LearningPortalComponent } from './learning-portal/learning-portal.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { AssesmentPortalComponent } from './assesment-portal/assesment-portal.component';
import { LoginComponent } from './login/login.component';

import { UpdateDetailsComponent } from './update-details/update-details.component';

import { SkilltestComponent } from './skilltest/skilltest.component';
import { JobSeekerRegisterComponent } from './job-seeker-register/job-seeker-register.component';
import { RecruiterRegisterComponent } from './recruiter-register/recruiter-register.component';


import { CvTemplate1Component } from './cv-generation/cv-template1/cv-template1.component';
import { CvTemplate2Component } from './cv-generation/cv-template2/cv-template2.component';
import { CvTemplate3Component } from './cv-generation/cv-template3/cv-template3.component';
import { RegisterComponent } from './register/register.component';
import { AssesmentTestPortalComponent } from './assesment-test-portal/assesment-test-portal.component';
import { UpdateUserDetailsComponent } from './update-user-details/update-user-details.component';
import { TechnewsComponent } from './technews/technews.component';



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

    
    
    CvTemplate1Component,
    CvTemplate2Component,
    CvTemplate3Component,

    JobSeekerRegisterComponent,
    RecruiterRegisterComponent,
    RegisterComponent,
    LoginComponent,

    AssesmentTestPortalComponent,

    CvTemplate1Component,
    CvTemplate2Component,
    CvTemplate3Component,
    UpdateUserDetailsComponent,
    TechnewsComponent,

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
    NgbModule
  ],
  providers: [],
  bootstrap: [AppComponent],
  schemas:[ CUSTOM_ELEMENTS_SCHEMA]

})
export class AppModule { }
