export class PrintRequestLite {
  id: number;
  referenceNumber: string;
  statusCode: string;
  cardsCount: number;
  batchesCount: number;
  creationDate: Date;
  updateDate: Date;
  confirmationDate: Date;
  description?: string;
}
