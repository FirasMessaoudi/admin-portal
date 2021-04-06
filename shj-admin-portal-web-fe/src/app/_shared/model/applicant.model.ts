import {Lookup} from "@model/lookup.model";
import {ApplicantRelative} from "@model/applicant-relative.model";

export class Applicant {
  id: number;
  gender: string;
  nationalityCode: Lookup;
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
  status: number;
  relativeApplicants: ApplicantRelative[];
}
