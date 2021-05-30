import {PrintRequestBatch} from "@model/print-request-batch.model";

export class PrintRequest {
  id: number;
  referenceNumber: string;
  statusCode: string;
  printRequestApplicants: any;
  printRequestBatches: PrintRequestBatch[];
  creationDate: Date;
}
