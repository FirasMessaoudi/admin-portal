import {NotificationTemplateContent} from "@model/notification-template-content.model";
import {NotificationTemplateParameter} from "@model/notification-template-parameter.model";


export class NotificationTemplate {
  id: number;
  categoryCode: string;
  nameCode: string;
  statusCode: string;
  typeCode: string;
  toDescription: string;
  important: boolean;
  actionRequired: boolean;
  enabled: boolean;
  userSpecific: boolean;
  expirationPeriodInMinutes: number;
  notificationTemplateContents: NotificationTemplateContent [];
  notificationTemplateParameters: NotificationTemplateParameter [];

  creationDate: Date;
}
