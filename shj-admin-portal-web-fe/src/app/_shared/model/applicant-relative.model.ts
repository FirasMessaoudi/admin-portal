import {Applicant} from "@model/applicant.model";
import {Lookup} from "@model/lookup.model";

export class ApplicantRelative {
  id: number;
  relationshipCode: Lookup;
  applicant: Applicant;
}
