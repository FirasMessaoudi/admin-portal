import {ApplicantRelative} from "@model/applicant-relative.model";
import {ApplicantContact} from "@model/applicant-contact.model";

export class Applicant {
  id: number;
  gender: string;
  nationalityCode: string;
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
  status: number;
}
