import {PrintRequestBatch} from "@model/print-request-batch.model";
import {ApplicantCard} from "@model/card.model";

export class PrintRequestBatchCard {
  id: number
  printRequestBatch: PrintRequestBatch;
  card: ApplicantCard;
  creationDate: Date;
}
