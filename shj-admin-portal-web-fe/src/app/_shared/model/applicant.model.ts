import {ApplicantRelative} from "@model/applicant-relative.model";
import {ApplicantContact} from "@model/applicant-contact.model";
import {ApplicantHealth} from "@model/applicant-health.model";

export class Applicant {
  id: number;
  gender: string;
  nationalityCode: any;
  idNumber: number;
  idNumberOriginal: string;
  passportNumber: string;
  dateOfBirthGregorian: any;
  dateOfBirthHijri: any;
  fullNameAr: string;
  fullNameEn: string;
  fullNameOriginal: string;
  maritalStatus: number;
  photo: any;
  requestId: number;
  relatives: ApplicantRelative[];
  contacts: ApplicantContact[];
  healths: ApplicantHealth[];
  status: number;
}
