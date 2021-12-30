export class NotificationTemplateCategorizing {
  id: number;
  campId: number;
  companyId: number;
  nationalityCode: string;
  minAge: number;
  maxAge: number;
  gender: string;
  selectedApplicants: string;
  creationDate: Date;
  updateDate: Date;


  constructor(selectedApplicants: string) {
    this.selectedApplicants = selectedApplicants;
  }
}
