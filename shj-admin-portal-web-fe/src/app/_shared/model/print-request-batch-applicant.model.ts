import {PrintRequestBatch} from "@model/print-request-batch.model";
import {Applicant} from "@model/applicant.model";

export class PrintRequestBatchApplicant {
  id: number
  printRequestBatch: PrintRequestBatch;
  applicant: Applicant;
  creationDate: Date;
}
