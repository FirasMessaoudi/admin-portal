import {Applicant} from "@model/applicant.model";
import {HamlahPackage} from "@model/hamlah-package.model";

export class ApplicantRitual {
  id: number;
  applicant: Applicant;
  hamlahPackage: HamlahPackage;
  hijriSeason: number;
  dateStartGregorian: Date;
  dateEndGregorian: Date;
  dateStartHijri: Date;
  dateEndHijri: Date;
  typeCode: any;
  visaNumber: string;
  permitNumber: string;
  insuranceNumber: string;
  borderNumber: string;
}
