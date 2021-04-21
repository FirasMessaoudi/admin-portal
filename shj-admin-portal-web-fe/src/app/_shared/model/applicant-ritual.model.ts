import {Applicant} from "@model/applicant.model";

export class ApplicantRitual {
  id: number;
  applicant: Applicant;
  hamlahPackageId: number;
  hijriSeason: number;
  dateStartGregorian: Date;
  dateEndGregorian: Date;
  dateStartHijri: Date;
  dateEndHijri: Date;
  typeCode: string;
  visaNumber: string;
  permitNumber: string;
  insuranceNumber: string;
  borderNumber: string;
}
