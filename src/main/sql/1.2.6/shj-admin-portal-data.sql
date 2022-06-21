USE shc_portal
GO


SET IDENTITY_INSERT shc_portal.shc_complaint_type_lk ON;
INSERT INTO shc_portal.shc_complaint_type_lk (id, code, lang, label)
VALUES (1, 1, 'ar', N'شكوى خدمة طعام');
INSERT INTO shc_portal.shc_complaint_type_lk (id, code, lang, label)
VALUES (2, 1, 'en', 'Food Complaint');
INSERT INTO shc_portal.shc_complaint_type_lk (id, code, lang, label)
VALUES (3, 2, 'ar', N'شكوى خدمة نقل');
INSERT INTO shc_portal.shc_complaint_type_lk (id, code, lang, label)
VALUES (4, 2, 'en', 'Transportation Complaint');
INSERT INTO shc_portal.shc_complaint_type_lk (id, code, lang, label)
VALUES (5, 3, 'ar', N'شكوى خدمة سكن');
INSERT INTO shc_portal.shc_complaint_type_lk (id, code, lang, label)
VALUES (6, 3, 'en', 'Housing Complaint');
INSERT INTO shc_portal.shc_complaint_type_lk (id, code, lang, label)
VALUES (7, 4, 'ar', N'شكوى عامة');
INSERT INTO shc_portal.shc_complaint_type_lk (id, code, lang, label)
VALUES (8, 4, 'en', 'General Complaint');
SET
IDENTITY_INSERT shc_portal.shc_complaint_type_lk OFF;
GO


SET IDENTITY_INSERT shc_portal.shc_complaint_status_lk ON;
INSERT INTO shc_portal.shc_complaint_status_lk (id, code, lang, label)
VALUES (1, 1, 'ar', N'جاري دراسة الشكوى');
INSERT INTO shc_portal.shc_complaint_status_lk (id, code, lang, label)
VALUES (2, 1, 'en', 'Under Processing');
INSERT INTO shc_portal.shc_complaint_status_lk (id, code, lang, label)
VALUES (3, 2, 'ar', N'تم حل الشكوى');
INSERT INTO shc_portal.shc_complaint_status_lk (id, code, lang, label)
VALUES (4, 2, 'en', 'Resolved');
INSERT INTO shc_portal.shc_complaint_status_lk (id, code, lang, label)
VALUES (5, 3, 'ar', N'تم إغلاق الشكوى');
INSERT INTO shc_portal.shc_complaint_status_lk (id, code, lang, label)
VALUES (6, 3, 'en', 'Closed');
SET
IDENTITY_INSERT shc_portal.shc_complaint_status_lk OFF;
GO


SET IDENTITY_INSERT shc_portal.shc_city_lk ON;
INSERT INTO shc_portal.shc_city_lk (id, code, lang, label)
VALUES (1, 1, 'ar', N'مكة');
INSERT INTO shc_portal.shc_city_lk (id, code, lang, label)
VALUES (2, 1, 'en', 'Makkah');
INSERT INTO shc_portal.shc_city_lk (id, code, lang, label)
VALUES (3, 2, 'ar', N'المشاعر المقدسة ( منى – مزدلفة – عرفات)');
INSERT INTO shc_portal.shc_city_lk (id, code, lang, label)
VALUES (4, 2, 'en', 'Holy Sites(Mena- Muzdalifa – Arafat )');
INSERT INTO shc_portal.shc_city_lk (id, code, lang, label)
VALUES (5, 3, 'ar', N'المدينة');
INSERT INTO shc_portal.shc_city_lk (id, code, lang, label)
VALUES (6, 3, 'en', 'Madinah');
INSERT INTO shc_portal.shc_city_lk (id, code, lang, label)
VALUES (7, 4, 'ar', N'جدة');
INSERT INTO shc_portal.shc_city_lk (id, code, lang, label)
VALUES (8, 4, 'en', 'Jeddah');
INSERT INTO shc_portal.shc_city_lk (id, code, lang, label)
VALUES (9, 5, 'ar', N'أخرى');
INSERT INTO shc_portal.shc_city_lk (id, code, lang, label)
VALUES (10, 5, 'en', 'Others');
SET IDENTITY_INSERT shc_portal.shc_city_lk OFF;

GO

INSERT INTO shc_portal.shc_config (conf_key, conf_value) VALUES ('crm.url', 'https://haj-dev.alasilacx.sa');
INSERT INTO shc_portal.shc_config (conf_key, conf_value) VALUES ('crm.auth.url', '/api/v1/Auth/GetToken');
INSERT INTO shc_portal.shc_config (conf_key, conf_value) VALUES ('crm.complaint.update.url', '/api/v1/Ticket/UpdateStatus');
INSERT INTO shc_portal.shc_config (conf_key, conf_value) VALUES ('crm.access.username', 'haj.integration.user@haj.gov.sa');
INSERT INTO shc_portal.shc_config (conf_key, conf_value) VALUES ('crm.access.password', 'P@$$w0rd');

GO 
INSERT INTO shc_portal.shc_portal.shc_config (conf_key, conf_value)
VALUES ('sftp.complaint.client.protocol', 'sftp');
INSERT INTO shc_portal.shc_portal.shc_config (conf_key, conf_value)
VALUES ('sftp.complaint.client.host', '127.0.0.1');
INSERT INTO shc_portal.shc_portal.shc_config (conf_key, conf_value)
VALUES ('sftp.complaint.client.port', '22');
INSERT INTO shc_portal.shc_portal.shc_config (conf_key, conf_value)
VALUES ('sftp.complaint.client.username', 'sftp-user');
INSERT INTO shc_portal.shc_portal.shc_config (conf_key, conf_value)
VALUES ('sftp.complaint.client.password', 'Aa123456');
INSERT INTO shc_portal.shc_portal.shc_config (conf_key, conf_value)
VALUES ('sftp.complaint.client.root-folder', '/data/smart-hajj/applicant-complaints/');
INSERT INTO shc_portal.shc_portal.shc_config (conf_key, conf_value)
VALUES ('sftp.complaint.client.session-strict-host-key-checking', 'no');
INSERT INTO shc_portal.shc_portal.shc_config (conf_key, conf_value)
VALUES ('sftp.complaint.client.session-connect-timeout', '15000');
INSERT INTO shc_portal.shc_portal.shc_config (conf_key, conf_value)
VALUES ('sftp.complaint.client.channel-connected-timeout', '15000');

GO