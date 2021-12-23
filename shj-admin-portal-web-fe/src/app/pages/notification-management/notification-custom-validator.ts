import {AbstractControl, FormArray, FormGroup, ValidatorFn} from "@angular/forms";

export function requiredArabicAndEnglishContentValidator(): ValidatorFn {
  return (formArray: FormArray): { [key: string]: any } | null => {
    let valid: boolean = true;
    formArray.controls.filter(c => c.value.lang.toLowerCase() === 'ar' || c.value.lang.toLowerCase() === 'en')
      .forEach((contents: FormGroup) => {
        valid = valid && contents.value.title.length > 0 && contents.value.body.length > 0;
      });
    return valid ? null : {requiredContent: 'Arabic and english content required'}
  }
}

export function ageRangeValidator(c: AbstractControl): { [key: string]: any } | null {
  let minAge = c.get('minAge');
  let maxAge = c.get('maxAge');
  if (minAge.value === null || maxAge.value === null) {
    return null;
  }
  if (minAge.pristine || maxAge.pristine) {
    return null;
  }
  if (minAge.value > maxAge.value) {
    c.setErrors({invalidAgeRange: 'Invalid age range'});
    return {invalidAgeRange: 'Invalid age range'};
  }
  return null;
}
