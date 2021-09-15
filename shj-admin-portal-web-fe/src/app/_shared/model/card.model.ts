import {ApplicantRitual} from "@model/applicant-ritual.model";
import {CompanyRitualMainDataStep} from "@model/company-ritual-step";

export class ApplicantCard {
  id: number;
  applicantRitual: ApplicantRitual;
  companyRitualSteps: CompanyRitualMainDataStep[];
  referenceNumber: number;
  batchId: number;
  statusCode: any;
}
