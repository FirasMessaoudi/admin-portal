import {PrintRequestBatch} from "@model/print-request-batch.model";
import {ApplicantCard} from "@model/card.model";
import {CompanyStaffCard} from "@model/staff-card.model";

export class PrintRequestBatchCard {
  id: number
  printRequestBatch: PrintRequestBatch;
  card: ApplicantCard;
  cardId: number;
  staffCard?: CompanyStaffCard;
  creationDate: Date;
}
