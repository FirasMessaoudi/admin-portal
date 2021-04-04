import {Applicant} from "@model/applicant.model";
import {HamlahPackage} from "@model/hamlah-package.model";
import {Lookup} from "@model/lookup.model";

export class ApplicantRitual {
  id: number;
  applicant: Applicant;
  hamlahPackage: HamlahPackage;
  hijriSeason: number;
  dateStartGregorian: Date;
  dateEndGregorian: Date;
  dateStartHijri: Date;
  dateEndHijri: Date;
  typeCode: Lookup;
  visaNumber: string;
  permitNumber: string;
  borderNumber: string;
}
