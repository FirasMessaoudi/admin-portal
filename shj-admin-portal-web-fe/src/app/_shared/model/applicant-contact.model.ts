import {Applicant} from "@model/applicant.model";

export class ApplicantContact {

  id: number;
  applicant: Applicant;
  languageList: string;
  email: string;
  localMobileNumber: number;
  intlMobileNumber: number;
  streetName: string;
  districtName: string;
  cityName: string;
  buildingNumber: number;
  postalCode: number;
}
