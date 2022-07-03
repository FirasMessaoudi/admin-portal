USE shc_portal
GO
SET IDENTITY_INSERT shc_portal.shc_notification_template_name_lk ON;
INSERT INTO shc_portal.shc_notification_template_name_lk (id, code, lang, label)
VALUES (11, 'RESOLVE_COMPLAINT', 'ar', N'حل شكوى');
INSERT INTO shc_portal.shc_notification_template_name_lk (id, code, lang, label)
VALUES (12, 'RESOLVE_COMPLAINT', 'en', 'Resolve Complaint');
INSERT INTO shc_portal.shc_notification_template_name_lk (id, code, lang, label)
VALUES (13, 'CLOSE_COMPLAINT', 'ar', N'حل شكوى');
INSERT INTO shc_portal.shc_notification_template_name_lk (id, code, lang, label)
VALUES (14, 'CLOSE_COMPLAINT', 'en', 'Close Complaint');
SET IDENTITY_INSERT shc_portal.shc_notification_template_name_lk OFF;
GO

INSERT INTO shc_portal.shc_config (conf_key, conf_value)
VALUES ('generate.applicant.incident.scheduler.active.nodes', '127.0.0.1,localhost');
INSERT INTO shc_portal.shc_config (conf_key, conf_value)
VALUES ('scheduler.generate.applicant.incident.delay.milliseconds', '3600000');

GO
