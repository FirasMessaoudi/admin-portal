import {PrintRequest} from "@model/print-request.model";
import {PrintRequestBatchApplicant} from "@model/print-request-batch-applicant.model";

export class PrintRequestBatch {
  id: number;
  printRequest: PrintRequest;
  sequenceNumber: number;
  batchTypes: string;
  printRequestBatchApplicants: PrintRequestBatchApplicant[];
  creationDate: Date;
}
