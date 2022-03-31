import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import {  CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { CvGenerationComponent } from './cv-generation/cv-generation.component';

import { JobDetailComponent } from './job-detail/job-detail.component';
// import {MatButtonModule} from '@angular/material/button';
import {MatChipsModule} from '@angular/material/chips';

import {MatInputModule} from '@angular/material/input';
import { TrendLabComponent } from './trend-lab/trend-lab.component';
import { SkillsTrendLabComponent } from './skills-trend-lab/skills-trend-lab.component';
import { SalaryTrendLabComponent } from './salary-trend-lab/salary-trend-lab.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClient, HttpClientModule } from '@angular/common/http';
import { ChatbotComponent } from './chatbot/chatbot.component';
import { NavBarComponent } from './nav-bar/nav-bar.component';
import { LayoutModule } from '@angular/cdk/layout';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatButtonModule } from '@angular/material/button';
import { MatSidenavModule } from '@angular/material/sidenav';
import { MatIconModule } from '@angular/material/icon';
import { MatListModule } from '@angular/material/list';
import {MatDatepickerModule} from '@angular/material/datepicker';
import { JobApplyComponent } from './job-apply/job-apply.component';
import { JobPostingComponent } from './job-posting/job-posting.component';
import {MatStepperModule} from '@angular/material/stepper';
import {MatFormFieldModule} from '@angular/material/form-field';

@NgModule({
  declarations: [
    AppComponent,
    CvGenerationComponent,
    JobDetailComponent,
    TrendLabComponent,
    SkillsTrendLabComponent,
    SalaryTrendLabComponent,
    ChatbotComponent,
    NavBarComponent,
    JobApplyComponent,
    JobPostingComponent

  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,

    MatChipsModule,
    MatDatepickerModule,
    MatStepperModule,
    MatButtonModule,
    // MatInputModule,
    // MatFormFieldModule,
    ReactiveFormsModule,
    FormsModule,
    HttpClientModule,
    LayoutModule,
    MatToolbarModule,
    MatButtonModule,
    MatSidenavModule,
    MatIconModule,
    MatListModule

  ],
  providers: [],
  bootstrap: [AppComponent],
  schemas:[ CUSTOM_ELEMENTS_SCHEMA]

})
export class AppModule { }
