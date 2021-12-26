import {NotificationTemplateContent} from "@model/notification-template-content.model";
import {NotificationTemplateParameter} from "@model/notification-template-parameter.model";
import {NotificationTemplateCategorizing} from "@model/notification-template-categorizing.model";

export class NotificationTemplate {
  id: number;
  categoryCode: string;
  nameCode: string;
  statusCode: string;
  typeCode: string;
  description: string;
  important: boolean;
  actionRequired: boolean;
  enabled: boolean;
  userSpecific: boolean;
  forceSending: boolean;
  expirationPeriodInMinutes: number;
  notificationTemplateContents: NotificationTemplateContent [];
  notificationTemplateParameters: NotificationTemplateParameter [];
  notificationTemplateCategorizing: NotificationTemplateCategorizing;
  creationDate: Date;
  sendingDate: any;
}
