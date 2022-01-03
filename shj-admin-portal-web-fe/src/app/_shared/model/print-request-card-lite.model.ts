export class PrintRequestLite {
  id: number;
  referenceNumber: string;
  statusCode: string;
  cardsCount: number;
  batchesCount: number;
  seasonYear: number;
  ritualTypeCode: string;
  creationDate: Date;
  updateDate: Date;
  confirmationDate: Date;
  description?: string;
}
