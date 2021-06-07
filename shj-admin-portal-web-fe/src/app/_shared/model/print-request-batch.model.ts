import {PrintRequest} from "@model/print-request.model";
import {PrintRequestBatchCard} from "@model/print-request-batch-applicant.model";

export class PrintRequestBatch {
  id: number;
  printRequest: PrintRequest;
  sequenceNumber: number;
  batchTypeKey: string;
  batchTypeValue: string;
  printRequestBatchCards: PrintRequestBatchCard[];
  creationDate: Date;
}
