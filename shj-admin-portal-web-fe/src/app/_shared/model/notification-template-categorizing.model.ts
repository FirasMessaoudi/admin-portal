export class NotificationTemplateCategorizing {
  id: number;
  campId: number;
  companyId: number;
  nationalityCode: string;
  minAge: number;
  maxAge: number;
  gender: string;
  notificationCategory: number;
  selectedApplicants: string;
  selectedStaff: string;
  creationDate: Date;
  updateDate: Date;


  constructor(selectedApplicants: string, selectedStaff: string, notificationCategory: number) {
    this.selectedApplicants = selectedApplicants;
    this.selectedStaff = selectedStaff;
    this.notificationCategory = notificationCategory;
  }
}
