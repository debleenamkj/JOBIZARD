import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { FlexLayoutModule } from '@angular/flex-layout';
import { RegisterComponent } from './register/register.component';
import { NavBarComponent } from './nav-bar/nav-bar.component';
import { LoginComponent } from './login/login.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
<<<<<<< HEAD
<<<<<<< HEAD
import { JobDetailComponent } from './job-detail/job-detail.component';
=======
import { CvGenerationComponent } from './cv-generation/cv-generation.component';
>>>>>>> 6a71759a04f5ddd37a4f7e845385070eb6dfb2c0
=======
import { TrendLabComponent } from './trend-lab/trend-lab.component';
import { SkillsTrendLabComponent } from './skills-trend-lab/skills-trend-lab.component';
import { SalaryTrendLabComponent } from './salary-trend-lab/salary-trend-lab.component';
<<<<<<< HEAD
>>>>>>> 087b6d3c25a31526861a6edc70991a12491c8556
=======
>>>>>>> 60681837c3dc1b8217e2c4609f35feb6c790dade
>>>>>>> 6a71759a04f5ddd37a4f7e845385070eb6dfb2c0

@NgModule({
  declarations: [
    AppComponent,
    RegisterComponent,
    NavBarComponent,
    LoginComponent,
<<<<<<< HEAD
<<<<<<< HEAD
    JobDetailComponent
=======
    CvGenerationComponent
>>>>>>> 6a71759a04f5ddd37a4f7e845385070eb6dfb2c0
=======
    TrendLabComponent,
    SkillsTrendLabComponent,
    SalaryTrendLabComponent
<<<<<<< HEAD
>>>>>>> 087b6d3c25a31526861a6edc70991a12491c8556
=======
>>>>>>> 60681837c3dc1b8217e2c4609f35feb6c790dade
>>>>>>> 6a71759a04f5ddd37a4f7e845385070eb6dfb2c0
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    FlexLayoutModule,
    FormsModule, 
    ReactiveFormsModule
  ],
  providers: [],
  bootstrap: [AppComponent],
})
export class AppModule { }
