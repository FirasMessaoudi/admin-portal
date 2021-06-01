import {PrintRequest} from "@model/print-request.model";
import {PrintRequestBatchCard} from "@model/print-request-batch-applicant.model";

export class PrintRequestBatch {
  id: number;
  printRequest: PrintRequest;
  sequenceNumber: number;
  batchTypes: string;
  printRequestBatchCards: PrintRequestBatchCard[];
  creationDate: Date;
}
