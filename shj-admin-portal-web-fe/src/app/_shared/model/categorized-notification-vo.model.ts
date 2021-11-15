import {NotificationTemplate} from "@model/notification-template.model";
import {ApplicantSearchCriteria} from "@model/applicant-search-criteria.model";

export class CategorizedNotificationVo {
  notificationTemplate: NotificationTemplate;
  applicantSearchCriteria: ApplicantSearchCriteria;

  constructor(notificationTemplate: NotificationTemplate, applicantSearchCriteria: ApplicantSearchCriteria) {
    this.notificationTemplate = notificationTemplate;
    this.applicantSearchCriteria = applicantSearchCriteria;
  }
}
