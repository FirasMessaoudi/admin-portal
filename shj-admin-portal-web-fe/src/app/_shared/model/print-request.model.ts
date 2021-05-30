import {PrintRequestBatch} from "@model/print-request-batch.model";
import {PrintRequestBatchApplicant} from "@model/print-request-batch-applicant.model";

export class PrintRequest {
  id: number;
  referenceNumber: string;
  statusCode: string;
  printRequestApplicants: PrintRequestBatchApplicant[];
  printRequestBatches: PrintRequestBatch[];
  creationDate: Date;
}
