import {NotificationTemplate} from "@model/notification-template.model";

export class NotificationTemplateContent {
  id: number;
  notificationTemplate: NotificationTemplate;
  lang: string;
  title: string;
  body: string;
  actionLabel: string;
}
