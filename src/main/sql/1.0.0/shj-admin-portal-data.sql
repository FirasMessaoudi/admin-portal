USE shj_portal
GO
-- sha_user data
SET IDENTITY_INSERT shj_portal.sha_user ON;
insert into shj_portal.sha_user (id, nin, gender , mobile_number, date_of_birth_gregorian,
password_hash, first_name, father_name, family_name, activated, creation_date) values ('1', 1234567897, 'M',
 512345678, convert(date, '14/02/1972', 103), '$2a$10$A81/FuMFJWcxaJhUcL8isuVeKKa.hk7GVzTVTyf7xe/XoMVWuKckK', N'سعد'
 , N'عبد الرحمن', N'الغامدي', 1, current_timestamp);
SET IDENTITY_INSERT shj_portal.sha_user OFF;

-- sha_config data
INSERT INTO shj_portal.sha_config (conf_key, conf_value) VALUES ('elm.providers.cache.read.timeout', 500);
INSERT INTO shj_portal.sha_config (conf_key, conf_value) VALUES ('elm.providers.cache.write.timeout', 1000);
INSERT INTO shj_portal.sha_config (conf_key, conf_value) VALUES ('elm.providers.cache.delete.timeout', 1000);

INSERT INTO shj_portal.sha_config (conf_key, conf_value) VALUES ('elm.providers.yakeen.username', 'changeit');
INSERT INTO shj_portal.sha_config (conf_key, conf_value) VALUES ('elm.providers.yakeen.password', 'changeit');
INSERT INTO shj_portal.sha_config (conf_key, conf_value) VALUES ('elm.providers.yakeen.chargecode', 'changeit');
INSERT INTO shj_portal.sha_config (conf_key, conf_value) VALUES ('elm.providers.yakeen.url', 'changeit');
INSERT INTO shj_portal.sha_config (conf_key, conf_value) VALUES ('elm.providers.yakeen.connect.timeout', 500);
INSERT INTO shj_portal.sha_config (conf_key, conf_value) VALUES ('elm.providers.yakeen.receive.timeout', 500);
INSERT INTO shj_portal.sha_config (conf_key, conf_value) VALUES ('elm.providers.yakeen.mock.enabled', 'true');

INSERT INTO shj_portal.sha_config (conf_key, conf_value) VALUES ('elm.providers.email.host', '192.168.0.200');
INSERT INTO shj_portal.sha_config (conf_key, conf_value) VALUES ('elm.providers.email.port', 25);
INSERT INTO shj_portal.sha_config (conf_key, conf_value) VALUES ('elm.providers.email.username', '');
INSERT INTO shj_portal.sha_config (conf_key, conf_value) VALUES ('elm.providers.email.password', '');
INSERT INTO shj_portal.sha_config (conf_key, conf_value) VALUES ('elm.providers.email.from.address', 'no-reply@elm.sa');
INSERT INTO shj_portal.sha_config (conf_key, conf_value) VALUES ('elm.providers.email.from.name', 'Elm Product');
INSERT INTO shj_portal.sha_config (conf_key, conf_value) VALUES ('elm.providers.email.smtp.auth', 'true');
INSERT INTO shj_portal.sha_config (conf_key, conf_value) VALUES ('elm.providers.email.mock.enabled', 'false');

INSERT INTO shj_portal.sha_config (conf_key, conf_value) VALUES ('elm.providers.smsgateway.url', 'http://te1-iqa-rv-sg:8080/ElmSMSGatewayEJB/ElmSMSGatewayWS');
INSERT INTO shj_portal.sha_config (conf_key, conf_value) VALUES ('elm.providers.smsgateway.connect.timeout', 500);
INSERT INTO shj_portal.sha_config (conf_key, conf_value) VALUES ('elm.providers.smsgateway.receive.timeout', 500);
INSERT INTO shj_portal.sha_config (conf_key, conf_value) VALUES ('elm.providers.smsgateway.username', 'changeit');
INSERT INTO shj_portal.sha_config (conf_key, conf_value) VALUES ('elm.providers.smsgateway.password', 'changeit');
INSERT INTO shj_portal.sha_config (conf_key, conf_value) VALUES ('elm.providers.smsgateway.sendername', 'changeit');
INSERT INTO shj_portal.sha_config (conf_key, conf_value) VALUES ('elm.providers.smsgateway.mock.enabled', 'true');

INSERT INTO shj_portal.sha_config (conf_key, conf_value) VALUES ('elm.providers.recaptcha.key.site', '6Le30G0UAAAAAM3Kwdf4V_feWJ-zD7OFAjxqO3Vo');
INSERT INTO shj_portal.sha_config (conf_key, conf_value) VALUES ('elm.providers.recaptcha.key.secret', '6Le30G0UAAAAAIBhhUZ-TtNdmbCRqzoxNftB5W1w');
INSERT INTO shj_portal.sha_config (conf_key, conf_value) VALUES ('elm.providers.recaptcha.verify.url', 'https://www.google.com/recaptcha/api/siteverify');
INSERT INTO shj_portal.sha_config (conf_key, conf_value) VALUES ('elm.providers.recaptcha.mock.enabled', 'false');
INSERT INTO shj_portal.sha_config (conf_key, conf_value) VALUES ('elm.providers.recaptcha.connection.timeout', 500);
INSERT INTO shj_portal.sha_config (conf_key, conf_value) VALUES ('elm.providers.recaptcha.read.timeout', 500);

INSERT INTO shj_portal.sha_config (conf_key, conf_value) VALUES ('login.failed.max.attempts', '3');

INSERT INTO shj_portal.sha_config (conf_key, conf_value) VALUES ('elm.providers.filescan.mock.enabled', 'false');
INSERT INTO shj_portal.sha_config (conf_key, conf_value) VALUES ('elm.providers.filescan.read.timeout', 10000);
INSERT INTO shj_portal.sha_config (conf_key, conf_value) VALUES ('elm.providers.filescan.connection.timeout', 10000);
INSERT INTO shj_portal.sha_config (conf_key, conf_value) VALUES ('elm.providers.filescan.rest.url', 'http://localhost:8080/shj_portal-engines-filescan/scan-file');
INSERT INTO shj_portal.sha_config (conf_key, conf_value) VALUES ('elm.providers.filescan.username', 'shj_portalfilescan-username');
INSERT INTO shj_portal.sha_config (conf_key, conf_value) VALUES ('elm.providers.filescan.password', 'shj_portalfilescan-password');

INSERT INTO shj_portal.sha_config (conf_key, conf_value) VALUES ('elm.engines.filescan.host', '192.168.46.145');
INSERT INTO shj_portal.sha_config (conf_key, conf_value) VALUES ('elm.engines.filescan.port', '1344');
INSERT INTO shj_portal.sha_config (conf_key, conf_value) VALUES ('elm.engines.filescan.username', 'shj_portalfilescan-username');
INSERT INTO shj_portal.sha_config (conf_key, conf_value) VALUES ('elm.engines.filescan.password', 'shj_portalfilescan-password');

GO
-- update sha_user data in 1.2.0 version
USE shj_portal
GO
UPDATE shj_portal.sha_user SET password_hash = '$2a$10$A81/FuMFJWcxaJhUcL8isuVeKKa.hk7GVzTVTyf7xe/XoMVWuKckK'
where id = 1;

INSERT INTO shj_portal.sha_config (conf_key, conf_value) VALUES ('elm.providers.recaptcha.invisible.key.site', '6Le30G0UAAAAAM3Kwdf4V_feWJ-zD7OFAjxqO3Vo');
INSERT INTO shj_portal.sha_config (conf_key, conf_value) VALUES ('elm.providers.recaptcha.invisible.key.secret', '6Le30G0UAAAAAIBhhUZ-TtNdmbCRqzoxNftB5W1w');
GO
USE shj_portal
GO
INSERT INTO shj_portal.sha_user_password_history (user_id, old_password_hash) values (1, '$2a$10$MLt2QkqgBSo5WdVu5UJXjunvi0t/h.BKDJQWzO2tyrQKBysLmc9ou');

DELETE FROM shj_portal.sha_config where conf_key = 'elm.engines.filescan.host';
DELETE FROM shj_portal.sha_config where conf_key = 'elm.engines.filescan.port';
GO
USE shj_portal
GO
UPDATE shj_portal.sha_config SET conf_value='http://192.168.2.149:8080/shj_portal-engines-filescan/scan-file'
where conf_key = 'elm.providers.filescan.rest.url';
GO
UPDATE shj_portal.sha_user SET date_of_birth_hijri='14400505' where id = 1;
GO

DELETE FROM shj_portal.sha_config where conf_key = 'elm.engines.filescan.username';
DELETE FROM shj_portal.sha_config where conf_key = 'elm.engines.filescan.password';
GO

INSERT INTO shj_portal.sha_config (conf_key, conf_value) VALUES ('elm.providers.email.ssl.enabled', 'false');
INSERT INTO shj_portal.sha_config (conf_key, conf_value) VALUES ('elm.providers.email.read.timeout', 5000);
INSERT INTO shj_portal.sha_config (conf_key, conf_value) VALUES ('elm.providers.email.connection.timeout', 5000);
GO
-- update script for shj_portal aash version 1.7.1
USE shj_portal
GO
UPDATE shj_portal.sha_user SET email = 'sgh@elm.sa' where id = 1;
GO

INSERT INTO shj_portal.sha_config (conf_key, conf_value) VALUES ('login.simultaneous.enabled', 'false');
GO
-- update script for shj_portal aash version 1.8.0
USE shj_portal
GO
INSERT INTO shj_portal.sha_config (conf_key, conf_value) VALUES ('elm.commons.web.cors.allowed_origins', 'http://localhost:8080,http://localhost:4200,http://127.0.0.1:4200,http://localhost:8200,http://127.0.0.1:8200,http://ci-shj_portal.elm.com.sa:8080');
INSERT INTO shj_portal.sha_config (conf_key, conf_value) VALUES ('elm.commons.web.cors.allowed_methods', 'GET,POST,PUT,OPTIONS');
GO

UPDATE shj_portal.sha_config SET conf_value = '5000' WHERE conf_key = 'elm.providers.cache.read.timeout';
UPDATE shj_portal.sha_config SET conf_value = '5000' WHERE conf_key = 'elm.providers.yakeen.connect.timeout';
UPDATE shj_portal.sha_config SET conf_value = '5000' WHERE conf_key = 'elm.providers.yakeen.receive.timeout';
UPDATE shj_portal.sha_config SET conf_value = '5000' WHERE conf_key = 'elm.providers.smsgateway.connect.timeout';
UPDATE shj_portal.sha_config SET conf_value = '5000' WHERE conf_key = 'elm.providers.smsgateway.receive.timeout';
UPDATE shj_portal.sha_config SET conf_value = '5000' WHERE conf_key = 'elm.providers.recaptcha.connection.timeout';
UPDATE shj_portal.sha_config SET conf_value = '5000' WHERE conf_key = 'elm.providers.recaptcha.read.timeout';
GO

-- insert authorities
SET IDENTITY_INSERT shj_portal.sha_authority_lk ON;
INSERT INTO shj_portal.sha_authority_lk(id, label_ar, label_en, code, parent_id) VALUES (1, N'لوحة المعلومات', 'Admin Dashboard', 'ADMIN_DASHBOARD', NULL);
INSERT INTO shj_portal.sha_authority_lk(id, label_ar, label_en, code, parent_id) VALUES (2, N'إدارة المستخدمين', 'User Management', 'USER_MANAGEMENT', NULL);
INSERT INTO shj_portal.sha_authority_lk(id, label_ar, label_en, code, parent_id) VALUES (3, N'إضافة مستخدم', 'Add User', 'ADD_USER', 2);
INSERT INTO shj_portal.sha_authority_lk(id, label_ar, label_en, code, parent_id) VALUES (4, N'تعديل مستخدم', 'Edit User', 'EDIT_USER', 2);
INSERT INTO shj_portal.sha_authority_lk(id, label_ar, label_en, code, parent_id) VALUES (5, N'تغيير حالة مستخدم', 'Change User Status', 'CHANGE_USER_STATUS', 2);
INSERT INTO shj_portal.sha_authority_lk(id, label_ar, label_en, code, parent_id) VALUES (6, N'إعادة تعيين كلمة المرور', 'Reset Password', 'RESET_PASSWORD', 2);
INSERT INTO shj_portal.sha_authority_lk(id, label_ar, label_en, code, parent_id) VALUES (7, N'حذف مستخدم', 'Delete User', 'DELETE_USER', 2);
INSERT INTO shj_portal.sha_authority_lk(id, label_ar, label_en, code, parent_id) VALUES (8, N'إدارة الأدوار', 'Role Management', 'ROLE_MANAGEMENT', NULL);
INSERT INTO shj_portal.sha_authority_lk(id, label_ar, label_en, code, parent_id) VALUES (9, N'إضافة دور', 'Add Role', 'ADD_ROLE', 8);
INSERT INTO shj_portal.sha_authority_lk(id, label_ar, label_en, code, parent_id) VALUES (10, N'تعديل دور', 'Edit Role', 'EDIT_ROLE', 8);
INSERT INTO shj_portal.sha_authority_lk(id, label_ar, label_en, code, parent_id) VALUES (11, N'حذف دور', 'Delete Role', 'DELETE_ROLE', 8);
INSERT INTO shj_portal.sha_authority_lk(id, label_ar, label_en, code, parent_id) VALUES (12, N'تغيير حالة دور', 'Change Role Status', 'CHANGE_ROLE_STATUS', 8);
INSERT INTO shj_portal.sha_authority_lk(id, label_ar, label_en, code, parent_id) VALUES (13, N'إعادة تعيين كلمة مرور المستخدم', 'Reset User Password', 'RESET_USER_PASSWORD', 2);
-- new authorities
INSERT INTO shj_portal.sha_authority_lk(id, label_ar, label_en, code, parent_id) VALUES (14, N'إدارة المعرفات الرقمية', 'Manage Digital ID', 'MANAGE_DIGITAL_ID', NULL);
INSERT INTO shj_portal.sha_authority_lk(id, label_ar, label_en, code, parent_id) VALUES (15, N'عرض معلومات ضيف الرحمن', 'View Applicant Profile', 'VIEW_APPLICANT_PROFILE', 14);
INSERT INTO shj_portal.sha_authority_lk(id, label_ar, label_en, code, parent_id) VALUES (16, N'تعديل معلومات ضيف الرحمن', 'Update Applicant Profile', 'UPDATE_APPLICANT_PROFILE', 14);
INSERT INTO shj_portal.sha_authority_lk(id, label_ar, label_en, code, parent_id) VALUES (17, N'تفعيل بطاقة تعريف ضيف الرحمن', 'Activate Applicant Card', 'ACTIVATE_APPLICANT_CARD', 14);
INSERT INTO shj_portal.sha_authority_lk(id, label_ar, label_en, code, parent_id) VALUES (18, N'إلغاء بطاقة تعريف ضيف الرحمن', 'Cancel Applicant Card', 'CANCEL_APPLICANT_CARD', 14);
INSERT INTO shj_portal.sha_authority_lk(id, label_ar, label_en, code, parent_id) VALUES (19, N'إيقاف بطاقة تعريف ضيف الرحمن', 'Suspend Applicant Card', 'SUSPEND_APPLICANT_CARD', 14);
INSERT INTO shj_portal.sha_authority_lk(id, label_ar, label_en, code, parent_id) VALUES (20, N'إعادة إصدار بطاقة تعريف ضيف الرحمن', 'Reissue Applicant Card', 'REISSUE_APPLICANT_CARD', 14);
INSERT INTO shj_portal.sha_authority_lk(id, label_ar, label_en, code, parent_id) VALUES (21, N'إضافة رقم تعريف ضيف الرحمن', 'Add Digital ID', 'ADD_DIGITAL_ID', 14);
INSERT INTO shj_portal.sha_authority_lk(id, label_ar, label_en, code, parent_id) VALUES (22, N'إنشاء معلومات ضيف الرحمن', 'Add Applicant Profile', 'ADD_APPLICANT_PROFILE', 14);
INSERT INTO shj_portal.sha_authority_lk(id, label_ar, label_en, code, parent_id) VALUES (23, N'موافقة إنشاء معلومات ضيف الرحمن', 'Approve Applicant Profile', 'APPROVE_APPLICANT_PROFILE', 14);
INSERT INTO shj_portal.sha_authority_lk(id, label_ar, label_en, code, parent_id) VALUES (24, N'إدارة طلبات الطباعة', 'Manage Printing Requests', 'MANAGE_PRINTING_REQUEST', NULL);
INSERT INTO shj_portal.sha_authority_lk(id, label_ar, label_en, code, parent_id) VALUES (25, N'عرض تفاصيل طلب الطباعة', 'View Printing Request Details', 'VIEW_PRINTING_REQUEST_DETAILS', 24);
INSERT INTO shj_portal.sha_authority_lk(id, label_ar, label_en, code, parent_id) VALUES (26, N'إضافة طلب طباعة', 'Add Printing Request', 'ADD_PRINTING_REQUEST', 24);
INSERT INTO shj_portal.sha_authority_lk(id, label_ar, label_en, code, parent_id) VALUES (27, N'إدارة الطلبات', 'Manage Requests', 'MANAGE_REQUESTS', NULL);
INSERT INTO shj_portal.sha_authority_lk(id, label_ar, label_en, code, parent_id) VALUES (28, N'عرض تفاصيل الطلب', 'View Request Details', 'VIEW_REQUEST_DETAILS', 27);
INSERT INTO shj_portal.sha_authority_lk(id, label_ar, label_en, code, parent_id) VALUES (29, N'إنشاء طلب جديد', 'Create New Request', 'CREATE_NEW_REQUEST', 27);
INSERT INTO shj_portal.sha_authority_lk(id, label_ar, label_en, code, parent_id) VALUES (30, N'عرض الملف الشخصي', 'View My Profile', 'VIEW_MY_PROFILE', 2);
INSERT INTO shj_portal.sha_authority_lk(id, label_ar, label_en, code, parent_id) VALUES (31, N'تعديل الملف الشخصي', 'Update My Profile', 'UPDATE_MY_PROFILE', 2);
INSERT INTO shj_portal.sha_authority_lk(id, label_ar, label_en, code, parent_id) VALUES (32, N'تسجيل حملة', 'Register Hamlah', 'REGISTER_HAMLAH', NULL);
INSERT INTO shj_portal.sha_authority_lk(id, label_ar, label_en, code, parent_id) VALUES (33, N'استعلام طلب تسجيل حملة', 'Enquiry Hamlah Registration', 'ENQUIRY_HAMLAH_REGISTRATION', 32);
SET IDENTITY_INSERT shj_portal.sha_authority_lk OFF;
GO

SET IDENTITY_INSERT shj_portal.sha_role ON;
INSERT INTO shj_portal.sha_role(id, label_ar, label_en, deleted, activated) VALUES (1, N'مشرف النظام', 'System Admin', 0, 1);
SET IDENTITY_INSERT shj_portal.sha_role OFF;
GO

INSERT INTO shj_portal.sha_role_authority(role_id, authority_id) VALUES (1, 1);
INSERT INTO shj_portal.sha_role_authority(role_id, authority_id) VALUES (1, 2);
INSERT INTO shj_portal.sha_role_authority(role_id, authority_id) VALUES (1, 3);
INSERT INTO shj_portal.sha_role_authority(role_id, authority_id) VALUES (1, 4);
INSERT INTO shj_portal.sha_role_authority(role_id, authority_id) VALUES (1, 5);
INSERT INTO shj_portal.sha_role_authority(role_id, authority_id) VALUES (1, 6);
INSERT INTO shj_portal.sha_role_authority(role_id, authority_id) VALUES (1, 7);
INSERT INTO shj_portal.sha_role_authority(role_id, authority_id) VALUES (1, 8);
INSERT INTO shj_portal.sha_role_authority(role_id, authority_id) VALUES (1, 9);
INSERT INTO shj_portal.sha_role_authority(role_id, authority_id) VALUES (1, 10);
INSERT INTO shj_portal.sha_role_authority(role_id, authority_id) VALUES (1, 11);
INSERT INTO shj_portal.sha_role_authority(role_id, authority_id) VALUES (1, 12);
INSERT INTO shj_portal.sha_role_authority(role_id, authority_id) VALUES (1, 13);
GO

SET IDENTITY_INSERT shj_portal.sha_role ON;
INSERT INTO shj_portal.sha_role(id, label_ar, label_en, deleted, activated) VALUES (2, N'مستخدم النظام', 'System User', 0, 1);
-- new roles
INSERT INTO shj_portal.sha_role(id, label_ar, label_en, deleted, activated) VALUES (3, N'مشرف التسجيل', 'Enrollment Officer Supervisor', 0, 1);
INSERT INTO shj_portal.sha_role(id, label_ar, label_en, deleted, activated) VALUES (4, N'موظف التسجيل', 'Enrollment Officer', 0, 1);
INSERT INTO shj_portal.sha_role(id, label_ar, label_en, deleted, activated) VALUES (5, N'مشرف مركز الخدمة', 'Service Center Agent Supervisor', 0, 1);
INSERT INTO shj_portal.sha_role(id, label_ar, label_en, deleted, activated) VALUES (6, N'موظف مركز الخدمة', 'Service Center Agent', 0, 1);
INSERT INTO shj_portal.sha_role(id, label_ar, label_en, deleted, activated) VALUES (7, N'مشرف الطباعة', 'Printing Supervisor', 0, 1);
INSERT INTO shj_portal.sha_role(id, label_ar, label_en, deleted, activated) VALUES (8, N'موظف الطباعة', 'Printing User', 0, 1);
INSERT INTO shj_portal.sha_role(id, label_ar, label_en, deleted, activated) VALUES (9, N'مشرف الحملة', 'Hamlah Owner', 0, 1);
INSERT INTO shj_portal.sha_role(id, label_ar, label_en, deleted, activated) VALUES (10, N'موظف الحملة', 'Hamlah User', 0, 1);
INSERT INTO shj_portal.sha_role(id, label_ar, label_en, deleted, activated) VALUES (11, N'مشرف المعرفات الرقمية', 'Digital ID Issuer Supervisor', 0, 1);
INSERT INTO shj_portal.sha_role(id, label_ar, label_en, deleted, activated) VALUES (12, N'موظف المعرفات الرقمية', 'Digital ID Issuer', 0, 1);
SET IDENTITY_INSERT shj_portal.sha_role OFF;
GO

INSERT INTO shj_portal.sha_role_authority(role_id, authority_id) VALUES (2, 1);
-- new roles authorities
-- Enrollment Officer Supervisor
INSERT INTO shj_portal.sha_role_authority(role_id, authority_id) VALUES (3, 1);
INSERT INTO shj_portal.sha_role_authority(role_id, authority_id) VALUES (3, 2);
INSERT INTO shj_portal.sha_role_authority(role_id, authority_id) VALUES (3, 3);
INSERT INTO shj_portal.sha_role_authority(role_id, authority_id) VALUES (3, 4);
INSERT INTO shj_portal.sha_role_authority(role_id, authority_id) VALUES (3, 5);
INSERT INTO shj_portal.sha_role_authority(role_id, authority_id) VALUES (3, 6);
INSERT INTO shj_portal.sha_role_authority(role_id, authority_id) VALUES (3, 7);
INSERT INTO shj_portal.sha_role_authority(role_id, authority_id) VALUES (3, 14);
INSERT INTO shj_portal.sha_role_authority(role_id, authority_id) VALUES (3, 15);
INSERT INTO shj_portal.sha_role_authority(role_id, authority_id) VALUES (3, 16);
INSERT INTO shj_portal.sha_role_authority(role_id, authority_id) VALUES (3, 17);
INSERT INTO shj_portal.sha_role_authority(role_id, authority_id) VALUES (3, 18);
INSERT INTO shj_portal.sha_role_authority(role_id, authority_id) VALUES (3, 19);
INSERT INTO shj_portal.sha_role_authority(role_id, authority_id) VALUES (3, 30);
INSERT INTO shj_portal.sha_role_authority(role_id, authority_id) VALUES (3, 31);
-- Enrollment Officer
INSERT INTO shj_portal.sha_role_authority(role_id, authority_id) VALUES (4, 1);
INSERT INTO shj_portal.sha_role_authority(role_id, authority_id) VALUES (4, 6);
INSERT INTO shj_portal.sha_role_authority(role_id, authority_id) VALUES (4, 14);
INSERT INTO shj_portal.sha_role_authority(role_id, authority_id) VALUES (4, 15);
INSERT INTO shj_portal.sha_role_authority(role_id, authority_id) VALUES (4, 16);
INSERT INTO shj_portal.sha_role_authority(role_id, authority_id) VALUES (4, 17);
INSERT INTO shj_portal.sha_role_authority(role_id, authority_id) VALUES (4, 18);
INSERT INTO shj_portal.sha_role_authority(role_id, authority_id) VALUES (4, 19);
INSERT INTO shj_portal.sha_role_authority(role_id, authority_id) VALUES (4, 30);
INSERT INTO shj_portal.sha_role_authority(role_id, authority_id) VALUES (4, 31);
-- Service Center Agent Supervisor
INSERT INTO shj_portal.sha_role_authority(role_id, authority_id) VALUES (5, 1);
INSERT INTO shj_portal.sha_role_authority(role_id, authority_id) VALUES (5, 2);
INSERT INTO shj_portal.sha_role_authority(role_id, authority_id) VALUES (5, 3);
INSERT INTO shj_portal.sha_role_authority(role_id, authority_id) VALUES (5, 4);
INSERT INTO shj_portal.sha_role_authority(role_id, authority_id) VALUES (5, 5);
INSERT INTO shj_portal.sha_role_authority(role_id, authority_id) VALUES (5, 6);
INSERT INTO shj_portal.sha_role_authority(role_id, authority_id) VALUES (5, 7);
INSERT INTO shj_portal.sha_role_authority(role_id, authority_id) VALUES (5, 14);
INSERT INTO shj_portal.sha_role_authority(role_id, authority_id) VALUES (5, 15);
INSERT INTO shj_portal.sha_role_authority(role_id, authority_id) VALUES (5, 16);
INSERT INTO shj_portal.sha_role_authority(role_id, authority_id) VALUES (5, 17);
INSERT INTO shj_portal.sha_role_authority(role_id, authority_id) VALUES (5, 18);
INSERT INTO shj_portal.sha_role_authority(role_id, authority_id) VALUES (5, 19);
INSERT INTO shj_portal.sha_role_authority(role_id, authority_id) VALUES (5, 20);
INSERT INTO shj_portal.sha_role_authority(role_id, authority_id) VALUES (5, 21);
INSERT INTO shj_portal.sha_role_authority(role_id, authority_id) VALUES (5, 22);
INSERT INTO shj_portal.sha_role_authority(role_id, authority_id) VALUES (5, 23);
INSERT INTO shj_portal.sha_role_authority(role_id, authority_id) VALUES (5, 30);
INSERT INTO shj_portal.sha_role_authority(role_id, authority_id) VALUES (5, 31);
-- Service Center Agent
INSERT INTO shj_portal.sha_role_authority(role_id, authority_id) VALUES (6, 1);
INSERT INTO shj_portal.sha_role_authority(role_id, authority_id) VALUES (6, 6);
INSERT INTO shj_portal.sha_role_authority(role_id, authority_id) VALUES (6, 14);
INSERT INTO shj_portal.sha_role_authority(role_id, authority_id) VALUES (6, 15);
INSERT INTO shj_portal.sha_role_authority(role_id, authority_id) VALUES (6, 16);
INSERT INTO shj_portal.sha_role_authority(role_id, authority_id) VALUES (6, 17);
INSERT INTO shj_portal.sha_role_authority(role_id, authority_id) VALUES (6, 18);
INSERT INTO shj_portal.sha_role_authority(role_id, authority_id) VALUES (6, 19);
INSERT INTO shj_portal.sha_role_authority(role_id, authority_id) VALUES (6, 20);
INSERT INTO shj_portal.sha_role_authority(role_id, authority_id) VALUES (6, 21);
INSERT INTO shj_portal.sha_role_authority(role_id, authority_id) VALUES (6, 22);
INSERT INTO shj_portal.sha_role_authority(role_id, authority_id) VALUES (6, 30);
INSERT INTO shj_portal.sha_role_authority(role_id, authority_id) VALUES (6, 31);
-- Printing Supervisor
INSERT INTO shj_portal.sha_role_authority(role_id, authority_id) VALUES (7, 1);
INSERT INTO shj_portal.sha_role_authority(role_id, authority_id) VALUES (7, 2);
INSERT INTO shj_portal.sha_role_authority(role_id, authority_id) VALUES (7, 3);
INSERT INTO shj_portal.sha_role_authority(role_id, authority_id) VALUES (7, 4);
INSERT INTO shj_portal.sha_role_authority(role_id, authority_id) VALUES (7, 5);
INSERT INTO shj_portal.sha_role_authority(role_id, authority_id) VALUES (7, 6);
INSERT INTO shj_portal.sha_role_authority(role_id, authority_id) VALUES (7, 7);
INSERT INTO shj_portal.sha_role_authority(role_id, authority_id) VALUES (7, 24);
INSERT INTO shj_portal.sha_role_authority(role_id, authority_id) VALUES (7, 25);
INSERT INTO shj_portal.sha_role_authority(role_id, authority_id) VALUES (7, 26);
INSERT INTO shj_portal.sha_role_authority(role_id, authority_id) VALUES (7, 30);
INSERT INTO shj_portal.sha_role_authority(role_id, authority_id) VALUES (7, 31);
-- Printing User
INSERT INTO shj_portal.sha_role_authority(role_id, authority_id) VALUES (8, 1);
INSERT INTO shj_portal.sha_role_authority(role_id, authority_id) VALUES (8, 6);
INSERT INTO shj_portal.sha_role_authority(role_id, authority_id) VALUES (8, 24);
INSERT INTO shj_portal.sha_role_authority(role_id, authority_id) VALUES (8, 25);
INSERT INTO shj_portal.sha_role_authority(role_id, authority_id) VALUES (8, 26);
INSERT INTO shj_portal.sha_role_authority(role_id, authority_id) VALUES (8, 30);
INSERT INTO shj_portal.sha_role_authority(role_id, authority_id) VALUES (8, 31);
-- Hamlah Owner
INSERT INTO shj_portal.sha_role_authority(role_id, authority_id) VALUES (9, 1);
INSERT INTO shj_portal.sha_role_authority(role_id, authority_id) VALUES (9, 2);
INSERT INTO shj_portal.sha_role_authority(role_id, authority_id) VALUES (9, 3);
INSERT INTO shj_portal.sha_role_authority(role_id, authority_id) VALUES (9, 4);
INSERT INTO shj_portal.sha_role_authority(role_id, authority_id) VALUES (9, 5);
INSERT INTO shj_portal.sha_role_authority(role_id, authority_id) VALUES (9, 6);
INSERT INTO shj_portal.sha_role_authority(role_id, authority_id) VALUES (9, 7);
INSERT INTO shj_portal.sha_role_authority(role_id, authority_id) VALUES (9, 14);
INSERT INTO shj_portal.sha_role_authority(role_id, authority_id) VALUES (9, 15);
INSERT INTO shj_portal.sha_role_authority(role_id, authority_id) VALUES (9, 16);
INSERT INTO shj_portal.sha_role_authority(role_id, authority_id) VALUES (9, 17);
INSERT INTO shj_portal.sha_role_authority(role_id, authority_id) VALUES (9, 30);
INSERT INTO shj_portal.sha_role_authority(role_id, authority_id) VALUES (9, 31);
INSERT INTO shj_portal.sha_role_authority(role_id, authority_id) VALUES (9, 32);
INSERT INTO shj_portal.sha_role_authority(role_id, authority_id) VALUES (9, 33);
-- Hamlah User
INSERT INTO shj_portal.sha_role_authority(role_id, authority_id) VALUES (10, 1);
INSERT INTO shj_portal.sha_role_authority(role_id, authority_id) VALUES (10, 6);
INSERT INTO shj_portal.sha_role_authority(role_id, authority_id) VALUES (10, 14);
INSERT INTO shj_portal.sha_role_authority(role_id, authority_id) VALUES (10, 15);
INSERT INTO shj_portal.sha_role_authority(role_id, authority_id) VALUES (10, 30);
INSERT INTO shj_portal.sha_role_authority(role_id, authority_id) VALUES (10, 31);
-- Digital ID Issuer Supervisor
INSERT INTO shj_portal.sha_role_authority(role_id, authority_id) VALUES (11, 1);
INSERT INTO shj_portal.sha_role_authority(role_id, authority_id) VALUES (11, 2);
INSERT INTO shj_portal.sha_role_authority(role_id, authority_id) VALUES (11, 3);
INSERT INTO shj_portal.sha_role_authority(role_id, authority_id) VALUES (11, 4);
INSERT INTO shj_portal.sha_role_authority(role_id, authority_id) VALUES (11, 5);
INSERT INTO shj_portal.sha_role_authority(role_id, authority_id) VALUES (11, 6);
INSERT INTO shj_portal.sha_role_authority(role_id, authority_id) VALUES (11, 7);
INSERT INTO shj_portal.sha_role_authority(role_id, authority_id) VALUES (11, 27);
INSERT INTO shj_portal.sha_role_authority(role_id, authority_id) VALUES (11, 28);
INSERT INTO shj_portal.sha_role_authority(role_id, authority_id) VALUES (11, 29);
INSERT INTO shj_portal.sha_role_authority(role_id, authority_id) VALUES (11, 30);
INSERT INTO shj_portal.sha_role_authority(role_id, authority_id) VALUES (11, 31);
-- Digital ID Issuer
INSERT INTO shj_portal.sha_role_authority(role_id, authority_id) VALUES (12, 1);
INSERT INTO shj_portal.sha_role_authority(role_id, authority_id) VALUES (12, 6);
INSERT INTO shj_portal.sha_role_authority(role_id, authority_id) VALUES (12, 27);
INSERT INTO shj_portal.sha_role_authority(role_id, authority_id) VALUES (12, 28);
INSERT INTO shj_portal.sha_role_authority(role_id, authority_id) VALUES (12, 29);
INSERT INTO shj_portal.sha_role_authority(role_id, authority_id) VALUES (12, 30);
INSERT INTO shj_portal.sha_role_authority(role_id, authority_id) VALUES (12, 31);
GO

UPDATE shj_portal.sha_user SET number_of_tries = 0 WHERE number_of_tries IS NULL;
GO

SET IDENTITY_INSERT shj_portal.sha_user_role ON;
INSERT INTO shj_portal.sha_user_role(id, user_id, role_id, is_main_role) VALUES (1, 1, 1, 1);
SET IDENTITY_INSERT shj_portal.sha_user_role OFF;
GO

SET IDENTITY_INSERT shj_portal.sha_data_segment ON;
INSERT INTO shj_portal.sha_data_segment (id, template_file_name, label_ar, label_en, creation_date, update_date)
VALUES
       (1, 'applicant-data.xlsx', N'بيانات ضيف الرحمان', 'Applicant Data', N'2021-04-13 13:38', null),
       (2, 'applicant-relatives-data.xlsx', N'بيانات أقارب ضيف الرحمان', 'Applicant Relatives Data', N'2021-04-13 13:38', null),
       (3, 'applicant-health-data.xlsx', N'البيانات الصحية لضيف الرحمان', 'Applicant Health Data', N'2021-04-13 13:38', null),
       (4, 'applicant-vaccination-data.xlsx', N'بيانات تطعيمات ضيف الرحمان', 'Applicant Vaccination Data', N'2021-04-13 13:38', null),
       (5, 'applicant-disease-data.xlsx', N'بيانات أمراض ضيف الرحمان', 'Applicant Disease Data', N'2021-04-13 13:38', null),
       (6, 'applicant-special-needs-data.xlsx', N'بيانات الاحتياجات الخاصة لضيف الرحمان', 'Applicant Special Needs Data', N'2021-04-13 13:38', null);
SET IDENTITY_INSERT shj_portal.sha_data_segment OFF;
GO

-- add data request statuses
SET IDENTITY_INSERT shj_portal.sha_data_request_status_lk ON;
INSERT INTO shj_portal.sha_data_request_status_lk(id, label_ar, label_en, creation_date) VALUES
(1, N'جديد', 'New', current_timestamp),
(2, N'مؤكد', 'Confirmed', current_timestamp),
(3, N'تحت المعالجة', 'Under Processing', current_timestamp),
(4, N'معالج بنجاح', 'Processed Successfully', current_timestamp),
(5, N'معالج مع أخطاء', 'Processed With Errors', current_timestamp),
(6, N'ملغى', 'Cancelled', current_timestamp);
SET IDENTITY_INSERT shj_portal.sha_data_request_status_lk OFF;

-- add sftp config
INSERT INTO shj_portal.sha_config (conf_key, conf_value)
VALUES ('sftp.client.protocol', 'sftp'),
       ('sftp.client.host', '127.0.0.1'),
       ('sftp.client.port', '22'),
       ('sftp.client.username', 'sftp-user'),
       ('sftp.client.password', 'Aa123456'),
       ('sftp.client.root-folder', '/smart-hajj/data-uploads/'),
       ('sftp.client.session-strict-host-key-checking', 'no'),
       ('sftp.client.session-connect-timeout', '15000'),
       ('sftp.client.channel-connected-timeout', '15000');
GO
-- insert ritual types
SET IDENTITY_INSERT shj_portal.sha_ritual_type_lk ON;
INSERT INTO shj_portal.sha_ritual_type_lk(id, code, lang, label) VALUES (1, 'EXTERNAL_HAJJ', 'ar', N'حجاج الخارج');
INSERT INTO shj_portal.sha_ritual_type_lk(id, code, lang, label) VALUES (2, 'EXTERNAL_HAJJ', 'en', 'External Hajj');
INSERT INTO shj_portal.sha_ritual_type_lk(id, code, lang, label) VALUES (3, 'INTERNAL_HAJJ', 'ar', N'حجاج الداخل');
INSERT INTO shj_portal.sha_ritual_type_lk(id, code, lang, label) VALUES (4, 'INTERNAL_HAJJ', 'en', 'Internal Hajj');
INSERT INTO shj_portal.sha_ritual_type_lk(id, code, lang, label) VALUES (5, 'COURTESY_HAJJ', 'ar', N'حجاج المجاملة');
INSERT INTO shj_portal.sha_ritual_type_lk(id, code, lang, label) VALUES (6, 'COURTESY_HAJJ', 'en', 'Courtesy Hajj');
INSERT INTO shj_portal.sha_ritual_type_lk(id, code, lang, label) VALUES (7, 'EXTERNAL_UMRAH', 'ar', N'عمرة الخارج');
INSERT INTO shj_portal.sha_ritual_type_lk(id, code, lang, label) VALUES (8, 'EXTERNAL_UMRAH', 'en', 'External Umrah');
INSERT INTO shj_portal.sha_ritual_type_lk(id, code, lang, label) VALUES (9, 'INTERNAL_UMRAH', 'ar', N'عمرة الداخل');
INSERT INTO shj_portal.sha_ritual_type_lk(id, code, lang, label) VALUES (10, 'INTERNAL_UMRAH', 'en', 'Internal Umrah');
SET IDENTITY_INSERT shj_portal.sha_ritual_type_lk OFF;
GO

SET IDENTITY_INSERT shj_portal.sha_relative_relationship_lk ON;
INSERT INTO shj_portal.sha_relative_relationship_lk (id, code, lang, label) VALUES (1, 'MOTHER', 'ar', N'أم');
INSERT INTO shj_portal.sha_relative_relationship_lk (id, code, lang, label) VALUES (2, 'MOTHER', 'en', 'Mother');
INSERT INTO shj_portal.sha_relative_relationship_lk (id, code, lang, label) VALUES (3, 'FATHER', 'ar', N'أب');
INSERT INTO shj_portal.sha_relative_relationship_lk (id, code, lang, label) VALUES (4, 'FATHER', 'en', 'Father');
INSERT INTO shj_portal.sha_relative_relationship_lk (id, code, lang, label) VALUES (5, 'WIFE', 'ar', N'زوجة');
INSERT INTO shj_portal.sha_relative_relationship_lk (id, code, lang, label) VALUES (6, 'WIFE', 'en', 'Wife');
INSERT INTO shj_portal.sha_relative_relationship_lk (id, code, lang, label) VALUES (7, 'HUSBAND', 'ar', N'زوج');
INSERT INTO shj_portal.sha_relative_relationship_lk (id, code, lang, label) VALUES (8, 'HUSBAND', 'en', 'Husband');
INSERT INTO shj_portal.sha_relative_relationship_lk (id, code, lang, label) VALUES (9, 'SON', 'ar', N'ابن');
INSERT INTO shj_portal.sha_relative_relationship_lk (id, code, lang, label) VALUES (10, 'SON', 'en', 'Son');
INSERT INTO shj_portal.sha_relative_relationship_lk (id, code, lang, label) VALUES (11, 'DAUGHTER', 'ar', N'ابنة');
INSERT INTO shj_portal.sha_relative_relationship_lk (id, code, lang, label) VALUES (12, 'DAUGHTER', 'en', 'Daughter');
INSERT INTO shj_portal.sha_relative_relationship_lk (id, code, lang, label) VALUES (13, 'BROTHER', 'ar', N'أخ');
INSERT INTO shj_portal.sha_relative_relationship_lk (id, code, lang, label) VALUES (14, 'BROTHER', 'en', 'Brother');
INSERT INTO shj_portal.sha_relative_relationship_lk (id, code, lang, label) VALUES (15, 'SISTER', 'ar', N'أخت');
INSERT INTO shj_portal.sha_relative_relationship_lk (id, code, lang, label) VALUES (16, 'SISTER', 'en', 'Sister');
INSERT INTO shj_portal.sha_relative_relationship_lk (id, code, lang, label) VALUES (17, 'RELATIVE', 'ar', N'قريب');
INSERT INTO shj_portal.sha_relative_relationship_lk (id, code, lang, label) VALUES (18, 'RELATIVE', 'en', 'Relative');
INSERT INTO shj_portal.sha_relative_relationship_lk (id, code, lang, label) VALUES (19, 'COMPANION', 'ar', N'مرافق');
INSERT INTO shj_portal.sha_relative_relationship_lk (id, code, lang, label) VALUES (20, 'COMPANION', 'en', 'Companion');
SET IDENTITY_INSERT shj_portal.sha_relative_relationship_lk OFF;
GO

SET IDENTITY_INSERT shj_portal.sha_health_immunization_lk ON;
INSERT INTO shj_portal.sha_health_immunization_lk(id, code, lang, label) VALUES (1, 'COVID-19', 'ar', N'كورونا');
INSERT INTO shj_portal.sha_health_immunization_lk(id, code, lang, label) VALUES (2, 'COVID-19', 'en', 'Covid-19');
INSERT INTO shj_portal.sha_health_immunization_lk(id, code, lang, label) VALUES (3, 'YELLOW_FEVER', 'ar', N'الحمى الصفراء');
INSERT INTO shj_portal.sha_health_immunization_lk(id, code, lang, label) VALUES (4, 'YELLOW_FEVER', 'en', 'Yellow Fever');
INSERT INTO shj_portal.sha_health_immunization_lk(id, code, lang, label) VALUES (5, 'NEISSERIA_MENINGITIS', 'ar', N'الحمى الشوكية النيسرية');
INSERT INTO shj_portal.sha_health_immunization_lk(id, code, lang, label) VALUES (6, 'NEISSERIA_MENINGITIS', 'en', 'Neisseria Meningitis');
INSERT INTO shj_portal.sha_health_immunization_lk(id, code, lang, label) VALUES (7, 'POLIOMYELITIS', 'ar', N'شلل الأطفال');
INSERT INTO shj_portal.sha_health_immunization_lk(id, code, lang, label) VALUES (8, 'POLIOMYELITIS', 'en', 'Poliomyelitis');
INSERT INTO shj_portal.sha_health_immunization_lk(id, code, lang, label) VALUES (9, 'SEASONAL_FLU', 'ar', N'الإنفلونزا الموسمية');
INSERT INTO shj_portal.sha_health_immunization_lk(id, code, lang, label) VALUES (10, 'SEASONAL_FLU', 'en', 'Seasonal Flu');
SET IDENTITY_INSERT shj_portal.sha_health_immunization_lk OFF;
GO

SET IDENTITY_INSERT shj_portal.sha_health_special_needs_type_lk ON;
INSERT INTO shj_portal.sha_health_special_needs_type_lk (id, code, lang, label) VALUES (1, 'WHEELCHAIR', 'ar', N'كرسي متحرك');
INSERT INTO shj_portal.sha_health_special_needs_type_lk (id, code, lang, label) VALUES (2, 'WHEELCHAIR', 'en', 'Wheelchair');
INSERT INTO shj_portal.sha_health_special_needs_type_lk (id, code, lang, label) VALUES (3, 'GOLF_CAR', 'ar', N'عربة نقل');
INSERT INTO shj_portal.sha_health_special_needs_type_lk (id, code, lang, label) VALUES (4, 'GOLF_CAR', 'en', 'Golf Car');
INSERT INTO shj_portal.sha_health_special_needs_type_lk (id, code, lang, label) VALUES (5, 'DEAF_DUMB', 'ar', N'صم وبكم');
INSERT INTO shj_portal.sha_health_special_needs_type_lk (id, code, lang, label) VALUES (6, 'DEAF_DUMB', 'en', 'Deaf and Dumb');
SET IDENTITY_INSERT shj_portal.sha_health_special_needs_type_lk OFF;
GO

SET IDENTITY_INSERT shj_portal.sha_card_status_lk ON;
INSERT INTO shj_portal.sha_card_status_lk (id, code, lang, label) VALUES (1, 'READY_TO_SEND', 'ar', N'جاهز للطباعة');
INSERT INTO shj_portal.sha_card_status_lk (id, code, lang, label) VALUES (2, 'READY_TO_SEND', 'en', 'Ready to Send');
INSERT INTO shj_portal.sha_card_status_lk (id, code, lang, label) VALUES (3, 'SENT_FOR_PRINT', 'ar', N'تم الإرسال للطباعة');
INSERT INTO shj_portal.sha_card_status_lk (id, code, lang, label) VALUES (4, 'SENT_FOR_PRINT', 'en', 'Sent for Print');
INSERT INTO shj_portal.sha_card_status_lk (id, code, lang, label) VALUES (5, 'PRINTED', 'ar', N'تمت الطباعة');
INSERT INTO shj_portal.sha_card_status_lk (id, code, lang, label) VALUES (6, 'PRINTED', 'en', 'Printed');
INSERT INTO shj_portal.sha_card_status_lk (id, code, lang, label) VALUES (7, 'DISTRIBUTED', 'ar', N'تم التوزيع');
INSERT INTO shj_portal.sha_card_status_lk (id, code, lang, label) VALUES (8, 'DISTRIBUTED', 'en', 'Distributed');
INSERT INTO shj_portal.sha_card_status_lk (id, code, lang, label) VALUES (9, 'ACTIVE', 'ar', N'تم التفعيل');
INSERT INTO shj_portal.sha_card_status_lk (id, code, lang, label) VALUES (10, 'ACTIVE', 'en', 'Active');
INSERT INTO shj_portal.sha_card_status_lk (id, code, lang, label) VALUES (11, 'SUSPENDED', 'ar', N'تم الإيقاف');
INSERT INTO shj_portal.sha_card_status_lk (id, code, lang, label) VALUES (12, 'SUSPENDED', 'en', 'Suspended');
INSERT INTO shj_portal.sha_card_status_lk (id, code, lang, label) VALUES (13, 'CANCELLED', 'ar', N'تم الإلغاء');
INSERT INTO shj_portal.sha_card_status_lk (id, code, lang, label) VALUES (14, 'CANCELLED', 'en', 'Cancelled');
SET IDENTITY_INSERT shj_portal.sha_card_status_lk OFF;
GO

-- add otp config
INSERT INTO shj_portal.sha_config (conf_key, conf_value)
VALUES ('otp.expiry.minutes', '5'),
       ('otp.pin.length', '4'),
       ('otp.mock.enabled', 'true');
GO

SET IDENTITY_INSERT shj_portal.sha_country_lk ON;
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (1, 'AE', 101, N'الإمارات العربية المتحدة', 'ar', '971', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (2, 'AE', 101, 'Arab Emirates', 'en', '971', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (3, 'JO', 102, N'الاردن', 'ar', '962', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (4, 'JO', 102, 'Jordan', 'en', '962', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (5, 'BH', 103, N'البحرين', 'ar', '973', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (6, 'BH', 103, 'Bahrain', 'en', '973', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (7, 'SY', 104, N'سوريا', 'ar', '963', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (8, 'SY', 104, 'Syria', 'en', '963', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (9, 'IQ', 105, N'العراق', 'ar', '964', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (10, 'IQ', 105, 'Iraq', 'en', '964', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (11, 'OM', 106, N'عمان', 'ar', '968', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (12, 'OM', 106, 'Oman', 'en', '968', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (13, 'PS', 107, N'فلسطين', 'ar', '970', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (14, 'PS', 107, 'Palestine', 'en', '970', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (15, 'QA', 108, N'قطر', 'ar', '974', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (16, 'QA', 108, 'Qatar', 'en', '974', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (17, 'KW', 109, N'الكويت', 'ar', '965', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (18, 'KW', 109, 'Kuwait', 'en', '965', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (19, 'LB', 110, N'لبنان', 'ar', '961', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (20, 'LB', 110, 'Lebanon', 'en', '961', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (21, 'YE', 111, N'اليمن', 'ar', '967', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (22, 'YE', 111, 'Yemen', 'en', '967', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (23, 'SA', 113, N'العربية السعودية', 'ar', '966', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (24, 'SA', 113, 'Saudi Arabia', 'en', '966', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (25, 'TN', 201, N'تونس', 'ar', '216', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (26, 'TN', 201, 'Tunisia', 'en', '216', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (27, 'DZ', 202, N'الجزائر', 'ar', '213', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (28, 'DZ', 202, 'Algeria', 'en', '213', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (29, 'DJ', 203, N'جيبوتي', 'ar', '253', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (30, 'DJ', 203, 'Djibouti', 'en', '253', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (31, 'SD', 204, N'السودان', 'ar', '249', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (32, 'SD', 204, 'Sudan', 'en', '249', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (33, 'SO', 205, N'الصومال', 'ar', '252', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (34, 'SO', 205, 'Somalia', 'en', '252', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (35, 'LY', 206, N'ليبيا', 'ar', '218', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (36, 'LY', 206, 'Libya', 'en', '218', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (37, 'EG', 207, N'مصر', 'ar', '20', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (38, 'EG', 207, 'Egypt', 'en', '20', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (39, 'EH', 208, N'المغرب', 'ar', '212', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (40, 'EH', 208, 'Morocco', 'en', '212', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (41, 'MR', 209, N'موريتانيا', 'ar', '222', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (42, 'MR', 209, 'Mauritania', 'en', '222', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (43, 'AF', 301, N'افغانستان', 'ar', '93', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (44, 'AF', 301, 'Afghanistan', 'en', '93', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (45, 'ID', 302, N'اندونيسيا', 'ar', '62', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (46, 'ID', 302, 'Indonesia', 'en', '62', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (47, 'IR', 303, N'ايران', 'ar', '98', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (48, 'IR', 303, 'Iran', 'en', '98', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (49, 'PK', 304, N'باكستان', 'ar', '92', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (50, 'PK', 304, 'Pakistan', 'en', '92', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (51, 'BD', 305, N'بنجلاديش', 'ar', '880', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (52, 'BD', 305, 'Bangladesh', 'en', '880', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (53, 'BN', 306, N'بروني', 'ar', '673', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (54, 'BN', 306, 'Brunei', 'en', '673', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (55, 'MM', 307, N'جمهورية ميانمار', 'ar', '95', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (56, 'MM', 307, 'Myanmar', 'en', '95', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (57, 'TH', 308, N'تايلند', 'ar', '66', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (58, 'TH', 308, 'Thailand', 'en', '66', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (59, 'TR', 309, N'تركيا', 'ar', '90', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (60, 'TR', 309, 'Turkey', 'en', '90', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (61, 'MV', 310, N'جزر مالديف', 'ar', '960', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (62, 'MV', 310, 'Maldives', 'en', '960', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (63, 'RU', 311, N'روسيا الاتحادية', 'ar', '7', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (64, 'RU', 311, 'Russia', 'en', '7', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (65, 'SG', 312, N'سنغافورة', 'ar', '65', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (66, 'SG', 312, 'Singapore', 'en', '65', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (67, 'LK', 313, N'سري لنكا', 'ar', '94', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (68, 'LK', 313, 'Sri Lanka', 'en', '94', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (69, 'PH', 315, N'الفلبين', 'ar', '63', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (70, 'PH', 315, 'Philippines', 'en', '63', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (71, 'VN', 316, N'فيتنام', 'ar', '84', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (72, 'VN', 316, 'Vietnam', 'en', '84', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (73, 'KH', 317, N'كمبوديا', 'ar', '855', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (74, 'KH', 317, 'Cambodia', 'en', '855', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (75, 'KR', 318, N'كوريا الجنوبية', 'ar', '82', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (76, 'KR', 318, 'South Korea', 'en', '82', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (77, 'MY', 319, N'ماليزيا', 'ar', '60', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (78, 'MY', 319, 'Malaysia', 'en', '60', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (79, 'NP', 320, N'نيبال', 'ar', '977', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (80, 'NP', 320, 'Nepal', 'en', '977', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (81, 'IN', 321, N'الهند', 'ar', '91', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (82, 'IN', 321, 'India', 'en', '91', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (83, 'HK', 322, N'هونج كونج', 'ar', '852', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (84, 'HK', 322, 'Hong Kong', 'en', '852', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (85, 'JP', 323, N'اليابان', 'ar', '81', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (86, 'JP', 323, 'Japan', 'en', '81', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (87, 'BT', 324, N'بهوتان', 'ar', '975', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (88, 'BT', 324, 'Bhutan', 'en', '975', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (89, 'CN', 325, N'الصين الشعبية', 'ar', '86', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (90, 'CN', 325, 'China', 'en', '86', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (91, 'CY', 326, N'قبرص', 'ar', '357', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (92, 'CY', 326, 'Cyprus', 'en', '357', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (93, 'KP', 328, N'كوريا الشمالية', 'ar', '850', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (94, 'KP', 328, 'North Korea', 'en', '850', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (95, 'LA', 329, N'لاوس', 'ar', '856', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (96, 'LA', 329, 'Laos', 'en', '856', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (97, 'MN', 330, N'منغوليا', 'ar', '976', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (98, 'MN', 330, 'Mongolia', 'en', '976', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (99, 'MO', 331, N'ماكاو', 'ar', '853', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (100, 'MO', 331, 'Macao', 'en', '853', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (101, 'KZ-YUZ', 332, N'تركستان', 'ar', '7-72533', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (102, 'KZ-YUZ', 332, 'Turkistan', 'en', '7-72533', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (103, 'KZ', 336, N'كازاخستان', 'ar', '7', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (104, 'KZ', 336, 'Kazakhstan', 'en', '7', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (105, 'UZ', 337, N'ازبكستان', 'ar', '998', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (106, 'UZ', 337, 'Uzbekistan', 'en', '998', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (107, 'TM', 338, N'تركمانستان', 'ar', '993', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (108, 'TM', 338, 'Turkmenistan', 'en', '993', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (109, 'TJ', 339, N'طاجكستان', 'ar', '992', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (110, 'TJ', 339, 'Tajikistan', 'en', '992', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (111, 'KG', 340, N'قرغيزستان', 'ar', '996', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (112, 'KG', 340, 'kyrgyzstan', 'en', '996', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (113, 'AZ', 343, N'اذربيجان', 'ar', '994', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (114, 'AZ', 343, 'Azerbaijan', 'en', '994', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (115, 'RU-CE', 344, N'الشاشان', 'ar', '7-871', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (116, 'RU-CE', 344, 'Chechnya', 'en', '7-871', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (117, 'RU-DA', 345, N'داغستان', 'ar', '7-872', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (118, 'RU-DA', 345, 'Dagestan', 'en', '7-872', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (119, 'RU-TA', 347, N'تتارستان', 'ar', '7-843', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (120, 'RU-TA', 347, 'Tatarstan', 'en', '7-843', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (121, 'TL', 349, N'تيمور الشرقية', 'ar', '670', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (122, 'TL', 349, 'East Timor', 'en', '670', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (123, 'ET', 401, N'اثيوبيا', 'ar', '251', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (124, 'ET', 401, 'Ethiopia', 'en', '251', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (125, 'UG', 402, N'اوغندة', 'ar', '256', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (126, 'UG', 402, 'Uganda', 'en', '256', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (127, 'BW', 403, N'بوتسوانا', 'ar', '267', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (128, 'BW', 403, 'Botswana', 'en', '267', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (129, 'BI', 404, N'بورندي', 'ar', '257', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (130, 'BI', 404, 'Burundi', 'en', '257', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (131, 'TD', 405, N'تشاد', 'ar', '235', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (132, 'TD', 405, 'Chad', 'en', '235', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (133, 'TZ', 406, N'تنزانيا', 'ar', '255', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (134, 'TZ', 406, 'Tanzania', 'en', '255', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (135, 'TG', 407, N'توجو', 'ar', '228', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (136, 'TG', 407, 'Togo', 'en', '228', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (137, 'GA', 408, N'الغابون', 'ar', '241', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (138, 'GA', 408, 'Gabon', 'en', '241', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (139, 'GM', 409, N'غامبيا', 'ar', '220', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (140, 'GM', 409, 'Gambia', 'en', '220', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (141, 'KM', 410, N'جزر القمر', 'ar', '269', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (142, 'KM', 410, 'Comoros', 'en', '269', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (143, 'ZA', 411, N'جنوب افريقيا', 'ar', '27', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (144, 'ZA', 411, 'South Africa', 'en', '27', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (145, 'NA', 412, N'ناميبيا', 'ar', '264', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (146, 'NA', 412, 'Namibia', 'en', '264', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (147, 'BJ', 413, N'بنين', 'ar', '229', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (148, 'BJ', 413, 'Benin', 'en', '229', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (149, 'RW', 414, N'رواندا', 'ar', '250', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (150, 'RW', 414, 'Rwanda', 'en', '250', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (151, 'ZW', 415, N'زمبابوي', 'ar', '263', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (152, 'ZW', 415, 'Zimbabwe', 'en', '263', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (153, 'CD', 416, N'الكونغو الديمقراطية', 'ar', '243', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (154, 'CD', 416, 'Zaire', 'en', '243', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (155, 'ZM', 417, N'زامبيا', 'ar', '260', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (156, 'ZM', 417, 'Zambia', 'en', '260', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (157, 'CI', 418, N'ساحل العاج', 'ar', '225', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (158, 'CI', 418, 'Ivory Coast', 'en', '225', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (159, 'SN', 419, N'السنغال', 'ar', '221', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (160, 'SN', 419, 'Senegal', 'en', '221', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (161, 'SL', 420, N'سيراليون', 'ar', '232', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (162, 'SL', 420, 'Sierra Leone', 'en', '232', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (163, 'GH', 421, N'غانا', 'ar', '233', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (164, 'GH', 421, 'Ghana', 'en', '233', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (165, 'GN', 422, N'غينيا', 'ar', '224', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (166, 'GN', 422, 'Guinea', 'en', '224', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (167, 'GW', 423, N'غينيابيساو', 'ar', '245', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (168, 'GW', 423, 'Guinea Bissau', 'en', '245', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (169, 'BF', 424, N'بوركينافاسو', 'ar', '226', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (170, 'BF', 424, 'Burkina Faso', 'en', '226', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (171, 'CM', 425, N'الكاميرون', 'ar', '237', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (172, 'CM', 425, 'Cameroon', 'en', '237', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (173, 'CG', 426, N'الكونغو', 'ar', '242', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (174, 'CG', 426, 'Congo', 'en', '242', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (175, 'KE', 427, N'كينيا', 'ar', '254', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (176, 'KE', 427, 'Kenya', 'en', '254', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (177, 'LS', 428, N'ليسوتو', 'ar', '266', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (178, 'LS', 428, 'Lesotho', 'en', '266', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (179, 'LR', 429, N'ليبيريا', 'ar', '231', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (180, 'LR', 429, 'Liberia', 'en', '231', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (181, 'ML', 430, N'مالي', 'ar', '223', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (182, 'ML', 430, 'Mali', 'en', '223', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (183, 'MW', 432, N'ملاوي', 'ar', '265', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (184, 'MW', 432, 'Malawi', 'en', '265', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (185, 'MU', 433, N'موريشيوس', 'ar', '230', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (186, 'MU', 433, 'Mauritius', 'en', '230', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (187, 'MZ', 434, N'موزمبيق', 'ar', '258', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (188, 'MZ', 434, 'Mozambique', 'en', '258', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (189, 'NG', 435, N'نيجيريا', 'ar', '234', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (190, 'NG', 435, 'Nigeria', 'en', '234', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (191, 'NE', 436, N'النيجر', 'ar', '227', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (192, 'NE', 436, 'Niger', 'en', '227', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (193, 'CF', 437, N'افريقيا الوسطى', 'ar', '236', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (194, 'CF', 437, 'Central Africa', 'en', '236', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (195, 'AO', 438, N'انجولا', 'ar', '244', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (196, 'AO', 438, 'Angola', 'en', '244', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (197, 'CV', 439, N'الراس الاخضر', 'ar', '238', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (198, 'CV', 439, 'Cape Verde', 'en', '238', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (199, 'GQ', 440, N'غينيا الاستوائية', 'ar', '240', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (200, 'GQ', 440, 'Equatorial Guinea', 'en', '240', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (201, 'MG', 441, N'مدغشقر', 'ar', '261', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (202, 'MG', 441, 'Mlajasi', 'en', '261', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (203, 'ST', 442, N'ساوتومي/برنسبى', 'ar', '239', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (204, 'ST', 442, 'São Tomé and Príncipe', 'en', '239', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (205, 'SC', 443, N'جزر سيشل', 'ar', '248', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (206, 'SC', 443, 'Seychelles Islands', 'en', '248', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (207, 'SZ', 444, N'اسواتيني', 'ar', '268', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (208, 'SZ', 444, 'Swaziland', 'en', '268', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (209, 'ER', 449, N'ارتيريا', 'ar', '291', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (210, 'ER', 449, 'Eritrea', 'en', '291', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (211, 'SS', 453, N'جمهورية جنوب السودان', 'ar', '211', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (212, 'SS', 453, 'Republic of South Sudan', 'en', '211', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (213, 'ES', 501, N'اسبانيا', 'ar', '34', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (214, 'ES', 501, 'Spain', 'en', '34', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (215, 'AL', 502, N'البانيا', 'ar', '355', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (216, 'AL', 502, 'Albania', 'en', '355', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (217, 'DE', 503, N'المانيا', 'ar', '49', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (218, 'DE', 503, 'Germany', 'en', '49', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (219, 'IE', 504, N'ايرلندا', 'ar', '353', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (220, 'IE', 504, 'Ireland', 'en', '353', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (221, 'IT', 505, N'ايطاليا', 'ar', '39', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (222, 'IT', 505, 'Italy', 'en', '39', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (223, 'GB', 506, N'المملكة المتحدة', 'ar', '44', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (224, 'GB', 506, 'United Kingdom', 'en', '44', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (225, 'PT', 507, N'البرتغال', 'ar', '351', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (226, 'PT', 507, 'Portugal', 'en', '351', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (227, 'BG', 508, N'بلغاريا', 'ar', '359', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (228, 'BG', 508, 'Bulgaria', 'en', '359', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (229, 'BE', 509, N'بلجيكا', 'ar', '32', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (230, 'BE', 509, 'Belgium', 'en', '32', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (231, 'PL', 510, N'بولندا', 'ar', '48', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (232, 'PL', 510, 'Poland', 'en', '48', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (233, 'DK', 512, N'الدانمارك', 'ar', '45', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (234, 'DK', 512, 'Denmark', 'en', '45', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (235, 'RO', 513, N'رومانيا', 'ar', '40', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (236, 'RO', 513, 'Romania', 'en', '40', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (237, 'SE', 514, N'السويد', 'ar', '46', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (238, 'SE', 514, 'Sweden', 'en', '46', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (239, 'CH', 515, N'سويسرا', 'ar', '41', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (240, 'CH', 515, 'Switzerland', 'en', '41', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (241, 'FR', 516, N'فرنسا', 'ar', '33', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (242, 'FR', 516, 'France', 'en', '33', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (243, 'AX', 517, N'فنلندا', 'ar', '358', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (244, 'AX', 517, 'Finland', 'en', '358', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (245, 'RS', 518, N'صربيا', 'ar', '381', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (246, 'RS', 518, 'Serbia', 'en', '381', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (247, 'NL', 519, N'هولندا', 'ar', '31', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (248, 'NL', 519, 'Netherlands', 'en', '31', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (249, 'GR', 521, N'اليونان', 'ar', '30', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (250, 'GR', 521, 'Greece', 'en', '30', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (251, 'AD', 522, N'اندورا', 'ar', '376', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (252, 'AD', 522, 'Andorra', 'en', '376', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (253, 'AT', 523, N'النمسا', 'ar', '43', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (254, 'AT', 523, 'Austria', 'en', '43', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (255, 'ME', 524, N'الجبل الأ سود', 'ar', '382', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (256, 'ME', 524, 'Montenegro', 'en', '382', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (257, 'HU', 525, N'هنغاريا', 'ar', '36', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (258, 'HU', 525, 'Hungary', 'en', '36', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (259, 'IS', 526, N'ايسلندا', 'ar', '354', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (260, 'IS', 526, 'Iceland', 'en', '354', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (261, 'LI', 527, N'ليختنشتين', 'ar', '423', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (262, 'LI', 527, 'Liechtenstein', 'en', '423', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (263, 'LU', 528, N'لوكسمبورغ', 'ar', '352', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (264, 'LU', 528, 'Luxembourg', 'en', '352', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (265, 'MT', 529, N'مالطا', 'ar', '356', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (266, 'MT', 529, 'Malta', 'en', '356', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (267, 'MC', 530, N'موناكو', 'ar', '377', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (268, 'MC', 530, 'Monaco', 'en', '377', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (269, 'SJ', 531, N'النرويج', 'ar', '47', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (270, 'SJ', 531, 'Norway', 'en', '47', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (271, 'SM', 532, N'سان مارينو', 'ar', '378', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (272, 'SM', 532, 'San Marino', 'en', '378', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (273, 'VA', 533, N'مدينة الفاتيكان', 'ar', '39-06', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (274, 'VA', 533, 'Vatican City', 'en', '39-06', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (275, 'GI', 534, N'جبل طارق', 'ar', '350', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (276, 'GI', 534, 'Gibraltar', 'en', '350', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (277, 'UA', 536, N'اوكرانيا', 'ar', '380', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (278, 'UA', 536, 'Ukraine', 'en', '380', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (279, 'BY', 537, N'روسيا البيضاء', 'ar', '375', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (280, 'BY', 537, 'Byelorussia', 'en', '375', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (281, 'AM', 539, N'ارمينيا', 'ar', '374', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (282, 'AM', 539, 'Armenia', 'en', '374', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (283, 'MD', 540, N'مولدافيا', 'ar', '373', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (284, 'MD', 540, 'Moldova', 'en', '373', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (285, 'GE', 541, N'جورجيا', 'ar', '995', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (286, 'GE', 541, 'Georgia', 'en', '995', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (287, 'LT', 542, N'ليتوانيا', 'ar', '370', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (288, 'LT', 542, 'Lithuania', 'en', '370', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (289, 'EE', 543, N'استونيا', 'ar', '372', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (290, 'EE', 543, 'Estonia', 'en', '372', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (291, 'LV', 544, N'لاتفيا', 'ar', '371', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (292, 'LV', 544, 'Latvia', 'en', '371', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (293, 'BA', 545, N'البوسنة والهرسك', 'ar', '387', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (294, 'BA', 545, 'Bosnia / Herzegovina', 'en', '387', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (295, 'HR', 546, N'كرواتيا', 'ar', '385', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (296, 'HR', 546, 'Croatia', 'en', '385', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (297, 'SI', 547, N'سلوفينيا', 'ar', '386', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (298, 'SI', 547, 'Slovenia', 'en', '386', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (299, 'MK', 549, N'مقدونيا', 'ar', '389', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (300, 'MK', 549, 'Macedonia', 'en', '389', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (301, 'CZ', 552, N'تشيك', 'ar', '420', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (302, 'CZ', 552, 'Czech Republic', 'en', '420', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (303, 'SK', 553, N'سلوفاكيا', 'ar', '421', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (304, 'SK', 553, 'Slovakia', 'en', '421', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (305, 'FO', 554, N'جزر فيرو', 'ar', '298', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (306, 'FO', 554, 'Faroe Islands', 'en', '298', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (307, 'US', 601, N'الولايات المتحدة', 'ar', '1', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (308, 'US', 601, 'United States', 'en', '1', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (309, 'AR', 602, N'الارجنتين', 'ar', '54', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (310, 'AR', 602, 'Argentina', 'en', '54', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (311, 'BB', 603, N'بربادوس', 'ar', '1-246', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (312, 'BB', 603, 'Barbados', 'en', '1-246', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (313, 'BR', 604, N'البرازيل', 'ar', '55', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (314, 'BR', 604, 'Brazil', 'en', '55', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (315, 'PA', 605, N'بنما', 'ar', '507', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (316, 'PA', 605, 'Panama', 'en', '507', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (317, 'TT', 606, N'ترينداد وتوباجو', 'ar', '1-868', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (318, 'TT', 606, 'Trinidad and Tobago', 'en', '1-868', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (319, 'JM', 607, N'جامايكا', 'ar', '1-876', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (320, 'JM', 607, 'Jamaica', 'en', '1-876', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (321, 'GY', 608, N'جوانا', 'ar', '592', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (322, 'GY', 608, 'Joanna', 'en', '592', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (323, 'VE', 609, N'فنزويلا', 'ar', '58', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (324, 'VE', 609, 'Venezuela', 'en', '58', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (325, 'CA', 610, N'كندا', 'ar', '1', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (326, 'CA', 610, 'Canada', 'en', '1', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (327, 'CO', 611, N'كولمبيا', 'ar', '57', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (328, 'CO', 611, 'Columbia', 'en', '57', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (329, 'BS', 612, N'جزر البهاما', 'ar', '1-242', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (330, 'BS', 612, 'Bahamas', 'en', '1-242', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (331, 'CR', 613, N'كوستاريكا', 'ar', '506', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (332, 'CR', 613, 'Costa Rica', 'en', '506', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (333, 'CU', 614, N'كوبا', 'ar', '53', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (334, 'CU', 614, 'Cuba', 'en', '53', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (335, 'DM', 615, N'دومينيكا', 'ar', '1-767', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (336, 'DM', 615, 'Dominica', 'en', '1-767', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (337, 'DO', 616, N'جمهورية دمينكان', 'ar', '1-809,1-829,1-849', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (338, 'DO', 616, 'Republic Dominica', 'en', '1-809,1-829,1-849', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (339, 'SV', 617, N'السلفادور', 'ar', '503', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (340, 'SV', 617, 'El Salvador', 'en', '503', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (341, 'GD', 618, N'جرانادا', 'ar', '1-473', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (342, 'GD', 618, 'Granada', 'en', '1-473', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (343, 'GT', 619, N'جواتيمالا', 'ar', '502', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (344, 'GT', 619, 'Guatemala', 'en', '502', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (345, 'HT', 620, N'هايتي', 'ar', '509', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (346, 'HT', 620, 'Haiti', 'en', '509', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (347, 'HN', 621, N'هوندوراس', 'ar', '504', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (348, 'HN', 621, 'Honduras', 'en', '504', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (349, 'MX', 622, N'المكسيك', 'ar', '52', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (350, 'MX', 622, 'Mexico', 'en', '52', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (351, 'NI', 623, N'نيكاراجوا', 'ar', '505', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (352, 'NI', 623, 'Nicaragua', 'en', '505', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (353, 'LC', 624, N'سانت لوسيا', 'ar', '1-758', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (354, 'LC', 624, 'Saint Lucia', 'en', '1-758', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (355, 'VC', 625, N'سان فينسنت وجزر جرينادين', 'ar', '1-784', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (356, 'VC', 625, 'Saint Vincent', 'en', '1-784', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (357, 'BO', 626, N'بوليفيا', 'ar', '591', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (358, 'BO', 626, 'Bolivia', 'en', '591', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (359, 'CL', 627, N'شيلي', 'ar', '56', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (360, 'CL', 627, 'Chile', 'en', '56', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (361, 'EC', 628, N'اكوادور', 'ar', '593', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (362, 'EC', 628, 'Ecuador', 'en', '593', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (363, 'PY', 629, N'باراجواي', 'ar', '595', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (364, 'PY', 629, 'Paraguay', 'en', '595', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (365, 'PE', 630, N'بيرو', 'ar', '51', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (366, 'PE', 630, 'Peru', 'en', '51', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (367, 'CC', 701, N'استراليا', 'ar', '61', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (368, 'CC', 701, 'Australia', 'en', '61', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (369, 'NZ', 702, N'نيوزيلندا', 'ar', '64', current_timestamp);
INSERT INTO shj_portal.sha_country_lk(id, code, nic_code, label, lang, country_phone_prefix, creation_date) VALUES (370, 'NZ', 702, 'New Zealand', 'en', '64', current_timestamp);
SET IDENTITY_INSERT shj_portal.sha_country_lk OFF;
GO

SET IDENTITY_INSERT shj_portal.sha_marital_status_lk ON;
INSERT INTO shj_portal.sha_marital_status_lk (id, code, lang, label) VALUES (1, 'SINGLE', 'ar', N'أعزب');
INSERT INTO shj_portal.sha_marital_status_lk (id, code, lang, label) VALUES (2, 'SINGLE', 'en', 'Single');
INSERT INTO shj_portal.sha_marital_status_lk (id, code, lang, label) VALUES (3, 'MARRIED', 'ar', N'متزوج');
INSERT INTO shj_portal.sha_marital_status_lk (id, code, lang, label) VALUES (4, 'MARRIED', 'en', 'Married');
INSERT INTO shj_portal.sha_marital_status_lk (id, code, lang, label) VALUES (5, 'WIDOWED', 'ar', N'أرمل');
INSERT INTO shj_portal.sha_marital_status_lk (id, code, lang, label) VALUES (6, 'WIDOWED', 'en', 'Widowed');
INSERT INTO shj_portal.sha_marital_status_lk (id, code, lang, label) VALUES (7, 'DIVORCED', 'ar', N'مطلق');
INSERT INTO shj_portal.sha_marital_status_lk (id, code, lang, label) VALUES (8, 'DIVORCED', 'en', 'Divorced');
SET IDENTITY_INSERT shj_portal.sha_marital_status_lk OFF;
GO

SET IDENTITY_INSERT shj_portal.sha_language_lk ON;
INSERT INTO shj_portal.sha_language_lk (id, code, lang, label) VALUES (1, 'AR', 'ar', N'العربية');
INSERT INTO shj_portal.sha_language_lk (id, code, lang, label) VALUES (2, 'AR', 'en', 'Arabic');
INSERT INTO shj_portal.sha_language_lk (id, code, lang, label) VALUES (3, 'AR', 'fr', 'Arabe');
INSERT INTO shj_portal.sha_language_lk (id, code, lang, label) VALUES (4, 'AR', 'ur', N'عربی');
INSERT INTO shj_portal.sha_language_lk (id, code, lang, label) VALUES (5, 'AR', 'fa', N'عربی');
INSERT INTO shj_portal.sha_language_lk (id, code, lang, label) VALUES (6, 'AR', 'tr', N'Arapça');
INSERT INTO shj_portal.sha_language_lk (id, code, lang, label) VALUES (7, 'EN', 'ar', N'الإنجليزية');
INSERT INTO shj_portal.sha_language_lk (id, code, lang, label) VALUES (8, 'EN', 'en', 'English');
INSERT INTO shj_portal.sha_language_lk (id, code, lang, label) VALUES (9, 'EN', 'fr', 'Anglais');
INSERT INTO shj_portal.sha_language_lk (id, code, lang, label) VALUES (10, 'EN', 'ur', N'انگریزی');
INSERT INTO shj_portal.sha_language_lk (id, code, lang, label) VALUES (11, 'EN', 'fa', N'انگلیسی');
INSERT INTO shj_portal.sha_language_lk (id, code, lang, label) VALUES (12, 'EN', 'tr', N'İngilizce');
INSERT INTO shj_portal.sha_language_lk (id, code, lang, label) VALUES (13, 'FR', 'ar', N'الفرنسية');
INSERT INTO shj_portal.sha_language_lk (id, code, lang, label) VALUES (14, 'FR', 'en', 'French');
INSERT INTO shj_portal.sha_language_lk (id, code, lang, label) VALUES (15, 'FR', 'fr', N'Français');
INSERT INTO shj_portal.sha_language_lk (id, code, lang, label) VALUES (16, 'FR', 'ur', N'فرانسیسی');
INSERT INTO shj_portal.sha_language_lk (id, code, lang, label) VALUES (17, 'FR', 'fa', N'فرانسوی');
INSERT INTO shj_portal.sha_language_lk (id, code, lang, label) VALUES (18, 'FR', 'tr', N'Fransızca');
INSERT INTO shj_portal.sha_language_lk (id, code, lang, label) VALUES (19, 'UR', 'ar', N'الأردية');
INSERT INTO shj_portal.sha_language_lk (id, code, lang, label) VALUES (20, 'UR', 'en', 'Urdu');
INSERT INTO shj_portal.sha_language_lk (id, code, lang, label) VALUES (21, 'UR', 'fr', 'Ourdou');
INSERT INTO shj_portal.sha_language_lk (id, code, lang, label) VALUES (22, 'UR', 'ur', N'اردو');
INSERT INTO shj_portal.sha_language_lk (id, code, lang, label) VALUES (23, 'UR', 'fa', N'اردو');
INSERT INTO shj_portal.sha_language_lk (id, code, lang, label) VALUES (24, 'UR', 'tr', 'Urduca');
INSERT INTO shj_portal.sha_language_lk (id, code, lang, label) VALUES (25, 'FA', 'ar', N'الفارسية');
INSERT INTO shj_portal.sha_language_lk (id, code, lang, label) VALUES (26, 'FA', 'en', 'Persian');
INSERT INTO shj_portal.sha_language_lk (id, code, lang, label) VALUES (27, 'FA', 'fr', 'Persan');
INSERT INTO shj_portal.sha_language_lk (id, code, lang, label) VALUES (28, 'FA', 'ur', N'فارسی');
INSERT INTO shj_portal.sha_language_lk (id, code, lang, label) VALUES (29, 'FA', 'fa', N'فارسی');
INSERT INTO shj_portal.sha_language_lk (id, code, lang, label) VALUES (30, 'FA', 'tr', N'Farsça');
INSERT INTO shj_portal.sha_language_lk (id, code, lang, label) VALUES (31, 'TR', 'ar', N'التركية');
INSERT INTO shj_portal.sha_language_lk (id, code, lang, label) VALUES (32, 'TR', 'en', 'Turkish');
INSERT INTO shj_portal.sha_language_lk (id, code, lang, label) VALUES (33, 'TR', 'fr', 'Turc');
INSERT INTO shj_portal.sha_language_lk (id, code, lang, label) VALUES (34, 'TR', 'ur', N'ترکی');
INSERT INTO shj_portal.sha_language_lk (id, code, lang, label) VALUES (35, 'TR', 'fa', N'ترکی');
INSERT INTO shj_portal.sha_language_lk (id, code, lang, label) VALUES (36, 'TR', 'tr', N'Türkçe');
SET IDENTITY_INSERT shj_portal.sha_language_lk OFF;
GO

SET IDENTITY_INSERT shj_portal.sha_print_request_status_lk ON;
INSERT INTO shj_portal.sha_print_request_status_lk (id, code, lang, label) VALUES (1, 'NEW', 'ar', N'جديد');
INSERT INTO shj_portal.sha_print_request_status_lk (id, code, lang, label) VALUES (2, 'NEW', 'en', 'New');
INSERT INTO shj_portal.sha_print_request_status_lk (id, code, lang, label) VALUES (3, 'CONFIRMED', 'ar', N'مؤكد');
INSERT INTO shj_portal.sha_print_request_status_lk (id, code, lang, label) VALUES (4, 'CONFIRMED', 'en', 'Confirmed');
INSERT INTO shj_portal.sha_print_request_status_lk (id, code, lang, label) VALUES (5, 'UNDER_PROCESSING', 'ar', N'تحت المعالجة');
INSERT INTO shj_portal.sha_print_request_status_lk (id, code, lang, label) VALUES (6, 'UNDER_PROCESSING', 'en', 'Under processing');
INSERT INTO shj_portal.sha_print_request_status_lk (id, code, lang, label) VALUES (7, 'PROCESSED', 'ar', N'تم التجميع');
INSERT INTO shj_portal.sha_print_request_status_lk (id, code, lang, label) VALUES (8, 'PROCESSED', 'en', 'Batched');
INSERT INTO shj_portal.sha_print_request_status_lk (id, code, lang, label) VALUES (9, 'SENT_TO_PRINTING', 'ar', N'تم الإرسال للطباعة');
INSERT INTO shj_portal.sha_print_request_status_lk (id, code, lang, label) VALUES (10, 'SENT_TO_PRINTING', 'en', 'Sent to printing');
INSERT INTO shj_portal.sha_print_request_status_lk (id, code, lang, label) VALUES (11, 'CANCELLED', 'ar', N'ملغى');
INSERT INTO shj_portal.sha_print_request_status_lk (id, code, lang, label) VALUES (12, 'CANCELLED', 'en', 'Cancelled');
SET IDENTITY_INSERT shj_portal.sha_print_request_status_lk OFF;
GO