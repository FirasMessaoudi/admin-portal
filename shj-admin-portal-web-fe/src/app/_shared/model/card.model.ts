import {ApplicantRitual} from "@model/applicant-ritual.model";
import {Lookup} from "@model/lookup.model";

export class Card {
  id: number;
  applicantRitual: ApplicantRitual;
  referenceNumber: number;
  batchId: number;
  statusCode: Lookup;
}
