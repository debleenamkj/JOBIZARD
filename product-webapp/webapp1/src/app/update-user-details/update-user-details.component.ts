import { Component, OnInit } from '@angular/core';
import {COMMA, ENTER} from '@angular/cdk/keycodes';
import {MatChipInputEvent} from '@angular/material/chips';

export interface AcademicsCertification {
  name: string;
}
export interface SkillSet {
  name: string;
}
export interface JobPreferences {
  name: string;
}
export interface Achievements {
  name: string;
}
export interface Courses {
  name: string;
}

@Component({
  selector: 'app-update-user-details',
  templateUrl: './update-user-details.component.html',
  styleUrls: ['./update-user-details.component.css']
})
export class UpdateUserDetailsComponent implements OnInit {

  constructor() { }

  ngOnInit(): void {
  }

  addOnBlur = true;
  readonly separatorKeysCodes = [ENTER, COMMA] as const;
  academicsCertifications: AcademicsCertification[] = [];

  add(event: MatChipInputEvent): void {
    console.log("acadamic");
    const value = (event.value || '').trim();

    // Add our Academics Certifications
    if (value) {
      this.academicsCertifications.push({name: value});
    }

    // Clear the input value
    event.chipInput!.clear();
  }

  remove(academicsCertification: AcademicsCertification): void {
    const index = this.academicsCertifications.indexOf(academicsCertification);

    if (index >= 0) {
      this.academicsCertifications.splice(index, 1);
    }
  }

  // ----------------------Skill Set--------------------------------------------


  // addOnBlur = true;
  // readonly separatorKeysCodes = [ENTER, COMMA] as const;
  skillSets: SkillSet[] = [];

  addskills(event: MatChipInputEvent): void {
    const value = (event.value || '').trim();

    // Add our SkillSet
    if (value) {
      this.skillSets.push({name: value});
    }

    // Clear the input value
    event.chipInput!.clear();
  }

  removeskills(SkillSet: SkillSet): void {
    const index = this.skillSets.indexOf(SkillSet);

    if (index >= 0) {
      this.skillSets.splice(index, 1);
    }
  }


  // addOnBlur = true;
  // readonly separatorKeysCodes = [ENTER, COMMA] as const;
  jobPreferences: JobPreferences[] = [];

  addJobPreferences(event: MatChipInputEvent): void {
    console.log("job preferences")
    const value = (event.value || '').trim();

    // Add our fruit
    if (value) {
      this.jobPreferences.push({name: value});
    }

    // Clear the input value
    event.chipInput!.clear();
  }

  removejobPreferences(jobPreference: JobPreferences): void {
    const index = this.jobPreferences.indexOf(jobPreference);

    if (index >= 0) {
      this.jobPreferences.splice(index, 1);
    }
  }


  achievements: Achievements[] = [];

  addachievements(event: MatChipInputEvent): void {
    console.log("achievements")
    const value = (event.value || '').trim();

    // Add our fruit
    if (value) {
      this.achievements.push({name: value});
    }

    // Clear the input value
    event.chipInput!.clear();
  }

  removeachievements(achievement: Achievements): void {
    const index = this.achievements.indexOf(achievement);

    if (index >= 0) {
      this.jobPreferences.splice(index, 1);
    }
  }



  courses: Courses[] = [];

  addcourses(event: MatChipInputEvent): void {
    console.log("courses")
    const value = (event.value || '').trim();

    // Add our fruit
    if (value) {
      this.courses.push({name: value});
    }

    // Clear the input value
    event.chipInput!.clear();
  }

  removecourses(course: Courses): void {
    const index = this.courses.indexOf(course);

    if (index >= 0) {
      this.jobPreferences.splice(index, 1);
    }
  }
}
