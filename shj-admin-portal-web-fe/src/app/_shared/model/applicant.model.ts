import {Lookup} from "@model/lookup.model";

export class Applicant {
  id: number;
  gender: string;
  nationalityCode: Lookup;
  idNumber: number;
  idNumberOriginal: string;
  passportNumber: string;
  dateOfBirthGregorian: Date;
  dateOfBirthHijri: Date;
  fullNameAr: string;
  fullNameEn: string;
  fullNameOriginal: string;
  maritalStatus: number;
  photo: any;
  requestId: number;
  status: number;
}
