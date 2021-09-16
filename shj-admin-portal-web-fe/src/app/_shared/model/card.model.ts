import {ApplicantRitual} from "@model/applicant-ritual.model";
import {CompanyRitualMainDataStep} from "@model/company-ritual-step";
import { groupeLeader } from "./groupe-leader.model";

export class ApplicantCard {
  id: number;
  applicantRitual: ApplicantRitual;
  companyRitualSteps: CompanyRitualMainDataStep[];
  groupLeaders: groupeLeader[];
  referenceNumber: number;
  batchId: number;
  statusCode: any;
}
