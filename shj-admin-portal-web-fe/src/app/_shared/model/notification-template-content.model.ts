import {NotificationTemplate} from "@model/notification-template.model";

export class NotificationTemplateContent {
  id: number;
  notificationTemplate: NotificationTemplate;
  lang: string;
  title: string;
  body: string;
  actionLabel: string;

  constructor(lang: string, title: string, body: string, actionLabel: string) {
    this.lang = lang;
    this.title = lang;
    this.body = lang;
    this.actionLabel = lang;
  }
}
