import {Applicant} from "@model/applicant.model";

export class ApplicantContact {

  id: number;
  applicant: Applicant;
  languageList: string;
  email: string;
  localMobileNumber: number;
  intlMobileNumber: number;
  street_name: string;
  districtName: string;
  cityName: string;
  buildingNumber: number;
  postal_code: number;
}
