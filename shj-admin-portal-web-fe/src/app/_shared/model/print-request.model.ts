import {PrintRequestBatch} from "@model/print-request-batch.model";
import {PrintRequestBatchCard} from "@model/print-request-batch-applicant.model";

export class PrintRequest {
  id: number;
  referenceNumber: string;
  statusCode: string;
  printRequestCards: PrintRequestBatchCard[];
  printRequestBatches: PrintRequestBatch[];
  creationDate: Date;
  updateDate: Date;
}
