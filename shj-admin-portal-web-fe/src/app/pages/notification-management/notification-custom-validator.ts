import {AbstractControl, FormGroup} from "@angular/forms";

export function validateIsRequired(group: FormGroup) {
  let required: boolean;
  let lang = group.get('lang').value;
  const title = group.get('title');
  let body = group.get('body');
  if (lang == "EN" || lang == "AR") {
    if (title?.value?.length == 0) {
      title.setErrors({required: true});
      required = true;
    }
    if (body?.value?.length == 0) {
      body.setErrors({required: true});
      required = true
    }
    return required ? {required: true} : null;
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
