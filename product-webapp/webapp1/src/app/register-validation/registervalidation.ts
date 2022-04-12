import { AbstractControl, FormControl, ValidatorFn } from "@angular/forms";


export function passwordMatchValidator(controlName: string, checkControlName: string): ValidatorFn {
  return (controls: AbstractControl) => {
    const control = controls.get(controlName);
    const checkControl = controls.get(checkControlName);
    if (checkControl?.errors && !checkControl?.errors["matching"]) {
      return null;
    } 
    if (control?.value !== checkControl?.value) {
      controls?.get(checkControlName)?.setErrors({ passmatching: true });
      return { matching: true };
    } else {
      return null;
    }
  };
}