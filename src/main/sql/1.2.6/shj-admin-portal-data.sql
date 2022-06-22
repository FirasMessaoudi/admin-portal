USE shc_portal
GO


SET IDENTITY_INSERT shc_portal.shc_complaint_type_lk ON;
INSERT INTO shc_portal.shc_complaint_type_lk (id, code, lang, label)
VALUES (1, 'FOOD', 'ar', N'شكوى خدمة طعام');
INSERT INTO shc_portal.shc_complaint_type_lk (id, code, lang, label)
VALUES (2, 'FOOD', 'en', 'Food Complaint');
INSERT INTO shc_portal.shc_complaint_type_lk (id, code, lang, label)
VALUES (3, 'TRANSPORTATION', 'ar', N'شكوى خدمة نقل');
INSERT INTO shc_portal.shc_complaint_type_lk (id, code, lang, label)
VALUES (4, 'TRANSPORTATION', 'en', 'Transportation Complaint');
INSERT INTO shc_portal.shc_complaint_type_lk (id, code, lang, label)
VALUES (5, 'HOUSING', 'ar', N'شكوى خدمة سكن');
INSERT INTO shc_portal.shc_complaint_type_lk (id, code, lang, label)
VALUES (6, 'HOUSING', 'en', 'Housing Complaint');
INSERT INTO shc_portal.shc_complaint_type_lk (id, code, lang, label)
VALUES (7, 'GENERAL', 'ar', N'شكوى عامة');
INSERT INTO shc_portal.shc_complaint_type_lk (id, code, lang, label)
VALUES (8, 'GENERAL', 'en', 'General Complaint');
SET
IDENTITY_INSERT shc_portal.shc_complaint_type_lk OFF;
GO


SET IDENTITY_INSERT shc_portal.shc_complaint_status_lk ON;
INSERT INTO shc_portal.shc_complaint_status_lk (id, code, lang, label)
VALUES (1, 'UNDER_PROCESSING', 'ar', N'جاري دراسة الشكوى');
INSERT INTO shc_portal.shc_complaint_status_lk (id, code, lang, label)
VALUES (2, 'UNDER_PROCESSING', 'en', 'Under Processing');
INSERT INTO shc_portal.shc_complaint_status_lk (id, code, lang, label)
VALUES (3, 'RESOLVED', 'ar', N'تم حل الشكوى');
INSERT INTO shc_portal.shc_complaint_status_lk (id, code, lang, label)
VALUES (4, 'RESOLVED', 'en', 'Resolved');
INSERT INTO shc_portal.shc_complaint_status_lk (id, code, lang, label)
VALUES (5, 'CLOSED', 'ar', N'تم إغلاق الشكوى');
INSERT INTO shc_portal.shc_complaint_status_lk (id, code, lang, label)
VALUES (6, 'CLOSED', 'en', 'Closed');
SET
IDENTITY_INSERT shc_portal.shc_complaint_status_lk OFF;
GO


SET IDENTITY_INSERT shc_portal.shc_city_lk ON;
INSERT INTO shc_portal.shc_city_lk (id, code, lang, label)
VALUES (1, 'MAKKAH', 'ar', N'مكة');
INSERT INTO shc_portal.shc_city_lk (id, code, lang, label)
VALUES (2, 'MAKKAH', 'en', 'Makkah');
INSERT INTO shc_portal.shc_city_lk (id, code, lang, label)
VALUES (3, 'HOLY_SITES', 'ar', N'المشاعر المقدسة ( منى – مزدلفة – عرفات)');
INSERT INTO shc_portal.shc_city_lk (id, code, lang, label)
VALUES (4, 'HOLY_SITES', 'en', 'Holy Sites(Mena- Muzdalifa – Arafat )');
INSERT INTO shc_portal.shc_city_lk (id, code, lang, label)
VALUES (5, 'MADINAH', 'ar', N'المدينة');
INSERT INTO shc_portal.shc_city_lk (id, code, lang, label)
VALUES (6, 'MADINAH', 'en', 'Madinah');
INSERT INTO shc_portal.shc_city_lk (id, code, lang, label)
VALUES (7, 'JEDDAH', 'ar', N'جدة');
INSERT INTO shc_portal.shc_city_lk (id, code, lang, label)
VALUES (8, 'JEDDAH', 'en', 'Jeddah');
INSERT INTO shc_portal.shc_city_lk (id, code, lang, label)
VALUES (9, 'OTHERS', 'ar', N'أخرى');
INSERT INTO shc_portal.shc_city_lk (id, code, lang, label)
VALUES (10, 'OTHERS', 'en', 'Others');
SET IDENTITY_INSERT shc_portal.shc_city_lk OFF;

GO

INSERT INTO shc_portal.shc_config (conf_key, conf_value) VALUES ('crm.url', 'https://haj-dev.alasilacx.sa');
INSERT INTO shc_portal.shc_config (conf_key, conf_value) VALUES ('crm.auth.url', '/api/v1/Auth/GetToken');
INSERT INTO shc_portal.shc_config (conf_key, conf_value) VALUES ('crm.complaint.update.url', '/api/v1/Ticket/UpdateStatus');
INSERT INTO shc_portal.shc_config (conf_key, conf_value) VALUES ('crm.create.user.profile.url', '/api/v1/UserProfile/CreateUserProfile');
INSERT INTO shc_portal.shc_config (conf_key, conf_value) VALUES ('crm.add.complaint.url', '/api/v1/Ticket/AddNewTicket');
INSERT INTO shc_portal.shc_config (conf_key, conf_value) VALUES ('crm.access.username', 'haj.integration.user@haj.gov.sa');
INSERT INTO shc_portal.shc_config (conf_key, conf_value) VALUES ('crm.access.password', 'P@$$w0rd');

GO 
INSERT INTO shc_portal.shc_config (conf_key, conf_value)
VALUES ('sftp.complaint.client.protocol', 'sftp');
INSERT INTO shc_portal.shc_config (conf_key, conf_value)
VALUES ('sftp.complaint.client.host', '127.0.0.1');
INSERT INTO shc_portal.shc_config (conf_key, conf_value)
VALUES ('sftp.complaint.client.port', '22');
INSERT INTO shc_portal.shc_config (conf_key, conf_value)
VALUES ('sftp.complaint.client.username', 'sftp-user');
INSERT INTO shc_portal.shc_config (conf_key, conf_value)
VALUES ('sftp.complaint.client.password', 'Aa123456');
INSERT INTO shc_portal.shc_config (conf_key, conf_value)
VALUES ('sftp.complaint.client.root-folder', '/data/smart-hajj/applicant-complaints/');
INSERT INTO shc_portal.shc_config (conf_key, conf_value)
VALUES ('sftp.complaint.client.session-strict-host-key-checking', 'no');
INSERT INTO shc_portal.shc_config (conf_key, conf_value)
VALUES ('sftp.complaint.client.session-connect-timeout', '15000');
INSERT INTO shc_portal.shc_config (conf_key, conf_value)
VALUES ('sftp.complaint.client.channel-connected-timeout', '15000');
INSERT INTO shc_portal.shc_config (conf_key, conf_value)
VALUES ('generate.applicant.complaint.scheduler.active.nodes', '127.0.0.1,localhost');
INSERT INTO shc_portal.shc_config (conf_key, conf_value)
VALUES ('scheduler.generate.applicant.complaint.delay.milliseconds', '900000');

GO