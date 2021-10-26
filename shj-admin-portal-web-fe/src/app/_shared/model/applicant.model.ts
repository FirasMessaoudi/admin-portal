import {ApplicantRelative} from "@model/applicant-relative.model";
import {ApplicantContact} from "@model/applicant-contact.model";
import {ApplicantHealth} from "@model/applicant-health.model";
import {ApplicantDigitalId} from "@model/applicant-digital-id.model";

export class Applicant {
  id: number;
  gender: string;
  nationalityCode: string;
  idNumber: string;
  idNumberOriginal: string;
  passportNumber: string;
  dateOfBirthGregorian: any;
  dateOfBirthHijri: any;
  fullNameAr: string;
  fullNameEn: string;
  fullNameOrigin: string;
  maritalStatusCode: string;
  photo: any;
  requestId: number;
  digitalIds: ApplicantDigitalId[];
  relatives: ApplicantRelative[];
  contacts: ApplicantContact[];
  applicantHealth: ApplicantHealth;

}
