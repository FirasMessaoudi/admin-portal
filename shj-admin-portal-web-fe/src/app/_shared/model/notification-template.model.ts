import {NotificationTemplateContent} from "@model/notification-template-content.model";

export class NotificationTemplate {
  id: number;
  categoryCode: string;
  nameCode: string;
  statusCode: string;
  typeCode: string;
  important: boolean;
  actionRequired: boolean;
  enabled: boolean;
  userSpecific: boolean;
  notificationTemplateContents: NotificationTemplateContent [];
}
