import {Applicant} from "@model/applicant.model";

export class ApplicantRelative {
  id: number;
  relationshipCode: any;
  applicantRelative: Applicant;
  applicant: Applicant;
}
