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
INSERT INTO shj_portal.sha_authority_lk(id, name_arabic, name_english, code, parent_id) VALUES (1, N'لوحة المعلومات', 'Admin Dashboard', 'ADMIN_DASHBOARD', NULL);
INSERT INTO shj_portal.sha_authority_lk(id, name_arabic, name_english, code, parent_id) VALUES (2, N'إدارة المستخدمين', 'User Management', 'USER_MANAGEMENT', NULL);
INSERT INTO shj_portal.sha_authority_lk(id, name_arabic, name_english, code, parent_id) VALUES (3, N'إضافة مستخدم', 'Add User', 'ADD_USER', 2);
INSERT INTO shj_portal.sha_authority_lk(id, name_arabic, name_english, code, parent_id) VALUES (4, N'تعديل مستخدم', 'Edit User', 'EDIT_USER', 2);
INSERT INTO shj_portal.sha_authority_lk(id, name_arabic, name_english, code, parent_id) VALUES (5, N'تغيير حالة مستخدم', 'Change User Status', 'CHANGE_USER_STATUS', 2);
INSERT INTO shj_portal.sha_authority_lk(id, name_arabic, name_english, code, parent_id) VALUES (6, N'إعادة تعيين كلمة المرور', 'Reset Password', 'RESET_PASSWORD', 2);
INSERT INTO shj_portal.sha_authority_lk(id, name_arabic, name_english, code, parent_id) VALUES (7, N'حذف مستخدم', 'Delete User', 'DELETE_USER', 2);
INSERT INTO shj_portal.sha_authority_lk(id, name_arabic, name_english, code, parent_id) VALUES (8, N'إدارة الأدوار', 'Role Management', 'ROLE_MANAGEMENT', NULL);
INSERT INTO shj_portal.sha_authority_lk(id, name_arabic, name_english, code, parent_id) VALUES (9, N'إضافة دور', 'Add Role', 'ADD_ROLE', 8);
INSERT INTO shj_portal.sha_authority_lk(id, name_arabic, name_english, code, parent_id) VALUES (10, N'تعديل دور', 'Edit Role', 'EDIT_ROLE', 8);
INSERT INTO shj_portal.sha_authority_lk(id, name_arabic, name_english, code, parent_id) VALUES (11, N'حذف دور', 'Delete Role', 'DELETE_ROLE', 8);
INSERT INTO shj_portal.sha_authority_lk(id, name_arabic, name_english, code, parent_id) VALUES (12, N'تغيير حالة دور', 'Change Role Status', 'CHANGE_ROLE_STATUS', 8);
INSERT INTO shj_portal.sha_authority_lk(id, name_arabic, name_english, code, parent_id) VALUES (13, N'إعادة تعيين كلمة مرور المستخدم', 'Reset User Password', 'RESET_USER_PASSWORD', 2);
-- new authorities
INSERT INTO shj_portal.sha_authority_lk(id, name_arabic, name_english, code, parent_id) VALUES (14, N'إدارة المعرفات الرقمية', 'Manage Digital ID', 'MANAGE_DIGITAL_ID', NULL);
INSERT INTO shj_portal.sha_authority_lk(id, name_arabic, name_english, code, parent_id) VALUES (15, N'عرض معلومات ضيف الرحمن', 'View Applicant Profile', 'VIEW_APPLICANT_PROFILE', 14);
INSERT INTO shj_portal.sha_authority_lk(id, name_arabic, name_english, code, parent_id) VALUES (16, N'تعديل معلومات ضيف الرحمن', 'Update Applicant Profile', 'UPDATE_APPLICANT_PROFILE', 14);
INSERT INTO shj_portal.sha_authority_lk(id, name_arabic, name_english, code, parent_id) VALUES (17, N'تفعيل بطاقة تعريف ضيف الرحمن', 'Activate Applicant Card', 'ACTIVATE_APPLICANT_CARD', 14);
INSERT INTO shj_portal.sha_authority_lk(id, name_arabic, name_english, code, parent_id) VALUES (18, N'إلغاء بطاقة تعريف ضيف الرحمن', 'Cancel Applicant Card', 'CANCEL_APPLICANT_CARD', 14);
INSERT INTO shj_portal.sha_authority_lk(id, name_arabic, name_english, code, parent_id) VALUES (19, N'إيقاف بطاقة تعريف ضيف الرحمن', 'Suspend Applicant Card', 'SUSPEND_APPLICANT_CARD', 14);
INSERT INTO shj_portal.sha_authority_lk(id, name_arabic, name_english, code, parent_id) VALUES (20, N'إعادة إصدار بطاقة تعريف ضيف الرحمن', 'Reissue Applicant Card', 'REISSUE_APPLICANT_CARD', 14);
INSERT INTO shj_portal.sha_authority_lk(id, name_arabic, name_english, code, parent_id) VALUES (21, N'إضافة رقم تعريف ضيف الرحمن', 'Add Digital ID', 'ADD_DIGITAL_ID', 14);
INSERT INTO shj_portal.sha_authority_lk(id, name_arabic, name_english, code, parent_id) VALUES (22, N'إنشاء معلومات ضيف الرحمن', 'Add Applicant Profile', 'ADD_APPLICANT_PROFILE', 14);
INSERT INTO shj_portal.sha_authority_lk(id, name_arabic, name_english, code, parent_id) VALUES (23, N'موافقة إنشاء معلومات ضيف الرحمن', 'Approve Applicant Profile', 'APPROVE_APPLICANT_PROFILE', 14);
INSERT INTO shj_portal.sha_authority_lk(id, name_arabic, name_english, code, parent_id) VALUES (24, N'إدارة طلبات الطباعة', 'Manage Printing Requests', 'MANAGE_PRINTING_REQUEST', NULL);
INSERT INTO shj_portal.sha_authority_lk(id, name_arabic, name_english, code, parent_id) VALUES (25, N'عرض تفاصيل طلب الطباعة', 'View Printing Request Details', 'VIEW_PRINTING_REQUEST_DETAILS', 24);
INSERT INTO shj_portal.sha_authority_lk(id, name_arabic, name_english, code, parent_id) VALUES (26, N'إضافة طلب طباعة', 'Add Printing Request', 'ADD_PRINTING_REQUEST', 24);
INSERT INTO shj_portal.sha_authority_lk(id, name_arabic, name_english, code, parent_id) VALUES (27, N'إدارة الطلبات', 'Manage Requests', 'MANAGE_REQUESTS', NULL);
INSERT INTO shj_portal.sha_authority_lk(id, name_arabic, name_english, code, parent_id) VALUES (28, N'عرض تفاصيل الطلب', 'View Request Details', 'VIEW_REQUEST_DETAILS', 27);
INSERT INTO shj_portal.sha_authority_lk(id, name_arabic, name_english, code, parent_id) VALUES (29, N'إنشاء طلب جديد', 'Create New Request', 'CREATE_NEW_REQUEST', 27);
INSERT INTO shj_portal.sha_authority_lk(id, name_arabic, name_english, code, parent_id) VALUES (30, N'عرض الملف الشخصي', 'View My Profile', 'VIEW_MY_PROFILE', 2);
INSERT INTO shj_portal.sha_authority_lk(id, name_arabic, name_english, code, parent_id) VALUES (31, N'تعديل الملف الشخصي', 'Update My Profile', 'UPDATE_MY_PROFILE', 2);
INSERT INTO shj_portal.sha_authority_lk(id, name_arabic, name_english, code, parent_id) VALUES (32, N'تسجيل حملة', 'Register Hamlah', 'REGISTER_HAMLAH', NULL);
INSERT INTO shj_portal.sha_authority_lk(id, name_arabic, name_english, code, parent_id) VALUES (33, N'استعلام طلب تسجيل حملة', 'Enquiry Hamlah Registration', 'ENQUIRY_HAMLAH_REGISTRATION', 32);
SET IDENTITY_INSERT shj_portal.sha_authority_lk OFF;
GO

SET IDENTITY_INSERT shj_portal.sha_role ON;
INSERT INTO shj_portal.sha_role(id, name_arabic, name_english, deleted, activated) VALUES (1, N'مشرف النظام', 'System Admin', 0, 1);
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
INSERT INTO shj_portal.sha_role(id, name_arabic, name_english, deleted, activated) VALUES (2, N'مستخدم النظام', 'System User', 0, 1);
-- new roles
INSERT INTO shj_portal.sha_role(id, name_arabic, name_english, deleted, activated) VALUES (3, N'مشرف التسجيل', 'Enrollment Officer Supervisor', 0, 1);
INSERT INTO shj_portal.sha_role(id, name_arabic, name_english, deleted, activated) VALUES (4, N'موظف التسجيل', 'Enrollment Officer', 0, 1);
INSERT INTO shj_portal.sha_role(id, name_arabic, name_english, deleted, activated) VALUES (5, N'مشرف مركز الخدمة', 'Service Center Agent Supervisor', 0, 1);
INSERT INTO shj_portal.sha_role(id, name_arabic, name_english, deleted, activated) VALUES (6, N'موظف مركز الخدمة', 'Service Center Agent', 0, 1);
INSERT INTO shj_portal.sha_role(id, name_arabic, name_english, deleted, activated) VALUES (7, N'مشرف الطباعة', 'Printing Supervisor', 0, 1);
INSERT INTO shj_portal.sha_role(id, name_arabic, name_english, deleted, activated) VALUES (8, N'موظف الطباعة', 'Printing User', 0, 1);
INSERT INTO shj_portal.sha_role(id, name_arabic, name_english, deleted, activated) VALUES (9, N'مشرف الحملة', 'Hamlah Owner', 0, 1);
INSERT INTO shj_portal.sha_role(id, name_arabic, name_english, deleted, activated) VALUES (10, N'موظف الحملة', 'Hamlah User', 0, 1);
INSERT INTO shj_portal.sha_role(id, name_arabic, name_english, deleted, activated) VALUES (11, N'مشرف المعرفات الرقمية', 'Digital ID Issuer Supervisor', 0, 1);
INSERT INTO shj_portal.sha_role(id, name_arabic, name_english, deleted, activated) VALUES (12, N'موظف المعرفات الرقمية', 'Digital ID Issuer', 0, 1);
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


INSERT INTO shj_portal.sha_decision_rule (dmn, label_ar, label_en, creation_date, update_date) VALUES (
                                                                                                       N'<?xml version="1.0" encoding="UTF-8"?>
            <definitions xmlns="https://www.omg.org/spec/DMN/20191111/MODEL/" xmlns:dmndi="https://www.omg.org/spec/DMN/20191111/DMNDI/" xmlns:dc="http://www.omg.org/spec/DMN/20180521/DC/" xmlns:di="http://www.omg.org/spec/DMN/20180521/DI/" id="dish" name="Dish" namespace="http://camunda.org/schema/1.0/dmn" exporter="dmn-js (https://demo.bpmn.io/dmn)" exporterVersion="8.3.0">
            <inputData id="dayType_id" name="Type of day">
            <variable id="dayType_ii" name="Type of day" typeRef="string" />
            </inputData>
            <inputData id="temperature_id" name="Weather in Celsius">
            <variable id="temperature_ii" name="Weather in Celsius" typeRef="integer" />
            </inputData>
            <knowledgeSource id="host_ks" name="Host" />
            <knowledgeSource id="guest_ks" name="Guest Type">
            <authorityRequirement id="AuthorityRequirement_0vkhray">
            <requiredDecision href="#guestCount" />
            </authorityRequirement>
            </knowledgeSource>
            <businessKnowledgeModel id="elMenu" name="The Menu" />
            <decision id="dish-decision" name="Dish Decision">
            <informationRequirement id="InformationRequirement_1d56kg6">
            <requiredDecision href="#guestCount" />
            </informationRequirement>
            <informationRequirement id="InformationRequirement_11onl5b">
            <requiredDecision href="#season" />
            </informationRequirement>
            <authorityRequirement id="AuthorityRequirement_142y75e">
            <requiredAuthority href="#host_ks" />
            </authorityRequirement>
            <decisionTable id="dishDecisionTable">
            <input id="seasonInput" label="Season">
            <inputExpression id="seasonInputExpression" typeRef="string">
            <text>season</text>
            </inputExpression>
            </input>
            <input id="guestCountInput" label="How many guests">
            <inputExpression id="guestCountInputExpression" typeRef="integer">
            <text>guestCount</text>
            </inputExpression>
            </input>
            <output id="output1" label="Dish" name="desiredDish" typeRef="string" />
            <rule id="row-495762709-1">
            <inputEntry id="UnaryTests_1nxcsjr">
            <text>"Winter"</text>
            </inputEntry>
            <inputEntry id="UnaryTests_1r9yorj">
            <text>&lt;= 8</text>
            </inputEntry>
            <outputEntry id="LiteralExpression_1mtwzqz">
            <text>"Spareribs"</text>
            </outputEntry>
            </rule>
            <rule id="row-495762709-2">
            <inputEntry id="UnaryTests_1lxjbif">
            <text>"Winter"</text>
            </inputEntry>
            <inputEntry id="UnaryTests_0nhiedb">
            <text>&gt; 8</text>
            </inputEntry>
            <outputEntry id="LiteralExpression_1h30r12">
            <text>"Pasta"</text>
            </outputEntry>
            </rule>
            <rule id="row-495762709-3">
            <inputEntry id="UnaryTests_0ifgmfm">
            <text>"Summer"</text>
            </inputEntry>
            <inputEntry id="UnaryTests_12cib9m">
            <text>&gt; 10</text>
            </inputEntry>
            <outputEntry id="LiteralExpression_0wgaegy">
            <text>"Light salad"</text>
            </outputEntry>
            </rule>
            <rule id="row-495762709-7">
            <inputEntry id="UnaryTests_0ozm9s7">
            <text>"Summer"</text>
            </inputEntry>
            <inputEntry id="UnaryTests_0sesgov">
            <text>&lt;= 10</text>
            </inputEntry>
            <outputEntry id="LiteralExpression_1dvc5x3">
            <text>"Beans salad"</text>
            </outputEntry>
            </rule>
            <rule id="row-445981423-3">
            <inputEntry id="UnaryTests_1er0je1">
            <text>"Spring"</text>
            </inputEntry>
            <inputEntry id="UnaryTests_1uzqner">
            <text>&lt; 10</text>
            </inputEntry>
            <outputEntry id="LiteralExpression_1pxy4g1">
            <text>"Stew"</text>
            </outputEntry>
            </rule>
            <rule id="row-445981423-4">
            <inputEntry id="UnaryTests_06or48g">
            <text>"Spring"</text>
            </inputEntry>
            <inputEntry id="UnaryTests_0wa71sy">
            <text>&gt;= 10</text>
            </inputEntry>
            <outputEntry id="LiteralExpression_09ggol9">
            <text>"Steak"</text>
            </outputEntry>
            </rule>
            </decisionTable>
            </decision>
            <decision id="season" name="Season decision">
            <informationRequirement id="InformationRequirement_1sdwefx">
            <requiredInput href="#temperature_id" />
            </informationRequirement>
            <decisionTable id="seasonDecisionTable">
            <input id="temperatureInput" label="Weather in Celsius">
            <inputExpression id="temperatureInputExpression" typeRef="integer">
            <text>temperature</text>
            </inputExpression>
            </input>
            <output id="seasonOutput" label="season" name="season" typeRef="string" />
            <rule id="row-495762709-5">
            <inputEntry id="UnaryTests_1fd0eqo">
            <text>&gt;30</text>
            </inputEntry>
            <outputEntry id="LiteralExpression_0l98klb">
            <text>"Summer"</text>
            </outputEntry>
            </rule>
            <rule id="row-495762709-6">
            <inputEntry id="UnaryTests_1nz6at2">
            <text>&lt;10</text>
            </inputEntry>
            <outputEntry id="LiteralExpression_08moy1k">
            <text>"Winter"</text>
            </outputEntry>
            </rule>
            <rule id="row-445981423-2">
            <inputEntry id="UnaryTests_1a0imxy">
            <text>[10..30]</text>
            </inputEntry>
            <outputEntry id="LiteralExpression_1poftw4">
            <text>"Spring"</text>
            </outputEntry>
            </rule>
            </decisionTable>
            </decision>
            <decision id="guestCount" name="Guest Count">
            <informationRequirement id="InformationRequirement_0j60f3j">
            <requiredInput href="#dayType_id" />
            </informationRequirement>
            <knowledgeRequirement id="KnowledgeRequirement_0n56cqb">
            <requiredKnowledge href="#elMenu" />
            </knowledgeRequirement>
            <decisionTable id="guestCountDecisionTable">
            <input id="typeOfDayInput" label="Type of day">
            <inputExpression id="typeOfDayInputExpression" typeRef="string">
            <text>dayType</text>
            </inputExpression>
            </input>
            <output id="guestCountOutput" label="Guest count" name="guestCount" typeRef="integer" />
            <rule id="row-495762709-8">
            <inputEntry id="UnaryTests_0l72u8n">
            <text>"Weekday"</text>
            </inputEntry>
            <outputEntry id="LiteralExpression_0wuwqaz">
            <text>4</text>
            </outputEntry>
            </rule>
            <rule id="row-495762709-9">
            <inputEntry id="UnaryTests_03a73o9">
            <text>"Holiday"</text>
            </inputEntry>
            <outputEntry id="LiteralExpression_1whn119">
            <text>10</text>
            </outputEntry>
            </rule>
            <rule id="row-495762709-10">
            <inputEntry id="UnaryTests_12tygwt">
            <text>"Weekend"</text>
            </inputEntry>
            <outputEntry id="LiteralExpression_1b5k9t8">
            <text>15</text>
            </outputEntry>
            </rule>
            </decisionTable>
            </decision>
            <textAnnotation id="TextAnnotation_1">
            <text>Week day or week end</text>
            </textAnnotation>
            <association id="Association_18hoj4i">
            <sourceRef href="#dayType_id" />
            <targetRef href="#TextAnnotation_1" />
            </association>
            <dmndi:DMNDI>
            <dmndi:DMNDiagram id="DMNDiagram_05sfxgt">
            <dmndi:DMNShape id="DMNShape_1nkrqp5" dmnElementRef="dayType_id">
            <dc:Bounds height="45" width="125" x="417" y="377" />
            </dmndi:DMNShape>
            <dmndi:DMNShape id="DMNShape_0wgwr3t" dmnElementRef="temperature_id">
            <dc:Bounds height="45" width="125" x="188" y="377" />
            </dmndi:DMNShape>
            <dmndi:DMNShape id="DMNShape_17n98pm" dmnElementRef="host_ks">
            <dc:Bounds height="63" width="100" x="646" y="48" />
            </dmndi:DMNShape>
            <dmndi:DMNShape id="DMNShape_1i9incu" dmnElementRef="guest_ks">
            <dc:Bounds height="63" width="100" x="660" y="198" />
            </dmndi:DMNShape>
            <dmndi:DMNEdge id="DMNEdge_0tdfvdg" dmnElementRef="AuthorityRequirement_0vkhray">
            <di:waypoint x="570" y="245" />
            <di:waypoint x="660" y="235" />
            </dmndi:DMNEdge>
            <dmndi:DMNShape id="DMNShape_1uo50vq" dmnElementRef="elMenu">
            <dc:Bounds height="46" width="135" x="642" y="307" />
            </dmndi:DMNShape>
            <dmndi:DMNShape id="DMNShape_0s7a8pk" dmnElementRef="dish-decision">
            <dc:Bounds height="80" width="180" x="301" y="48" />
            </dmndi:DMNShape>
            <dmndi:DMNEdge id="DMNEdge_1cvfntf" dmnElementRef="InformationRequirement_1d56kg6">
            <di:waypoint x="480" y="210" />
            <di:waypoint x="421" y="148" />
            <di:waypoint x="421" y="128" />
            </dmndi:DMNEdge>
            <dmndi:DMNEdge id="DMNEdge_0djoiii" dmnElementRef="InformationRequirement_11onl5b">
            <di:waypoint x="251" y="210" />
            <di:waypoint x="361" y="148" />
            <di:waypoint x="361" y="128" />
            </dmndi:DMNEdge>
            <dmndi:DMNEdge id="DMNEdge_0qqxexx" dmnElementRef="AuthorityRequirement_142y75e">
            <di:waypoint x="646" y="81" />
            <di:waypoint x="481" y="86" />
            </dmndi:DMNEdge>
            <dmndi:DMNShape id="DMNShape_06z5z89" dmnElementRef="season">
            <dc:Bounds height="80" width="180" x="161" y="210" />
            </dmndi:DMNShape>
            <dmndi:DMNEdge id="DMNEdge_1383eyj" dmnElementRef="InformationRequirement_1sdwefx">
            <di:waypoint x="251" y="377" />
            <di:waypoint x="251" y="310" />
            <di:waypoint x="251" y="290" />
            </dmndi:DMNEdge>
            <dmndi:DMNShape id="DMNShape_0qbhe8q" dmnElementRef="guestCount">
            <dc:Bounds height="80" width="180" x="390" y="210" />
            </dmndi:DMNShape>
            <dmndi:DMNEdge id="DMNEdge_131oa1j" dmnElementRef="KnowledgeRequirement_0n56cqb">
            <di:waypoint x="691" y="307" />
            <di:waypoint x="570" y="262" />
            </dmndi:DMNEdge>
            <dmndi:DMNEdge id="DMNEdge_1avtdb1" dmnElementRef="InformationRequirement_0j60f3j">
            <di:waypoint x="480" y="377" />
            <di:waypoint x="480" y="310" />
            <di:waypoint x="480" y="290" />
            </dmndi:DMNEdge>
            <dmndi:DMNShape id="DMNShape_0bblyhb" dmnElementRef="TextAnnotation_1">
            <dc:Bounds height="45" width="125" x="328" y="477" />
            </dmndi:DMNShape>
            <dmndi:DMNEdge id="DMNEdge_0aqnkob" dmnElementRef="Association_18hoj4i">
            <di:waypoint x="480" y="422" />
            <di:waypoint x="391" y="477" />
            </dmndi:DMNEdge>
            </dmndi:DMNDiagram>
            </dmndi:DMNDI>
            </definitions>',
            N'بيانات المتقدم', N'Applicant Data', 1, N'2021-04-13 13:38:43', null);
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
INSERT INTO shj_portal.sha_ritual_type_lk(id, code, lang, label) VALUES (1, 'EXTERNAL_HAJJ', 'ar', 'حجاج الخارج');
INSERT INTO shj_portal.sha_ritual_type_lk(id, code, lang, label) VALUES (2, 'EXTERNAL_HAJJ', 'en', 'External Hajj');
INSERT INTO shj_portal.sha_ritual_type_lk(id, code, lang, label) VALUES (3, 'INTERNAL_HAJJ', 'ar', 'حجاج الداخل');
INSERT INTO shj_portal.sha_ritual_type_lk(id, code, lang, label) VALUES (4, 'INTERNAL_HAJJ', 'en', 'Internal Hajj');
INSERT INTO shj_portal.sha_ritual_type_lk(id, code, lang, label) VALUES (5, 'COURTESY_HAJJ', 'ar', 'حجاج المجاملة');
INSERT INTO shj_portal.sha_ritual_type_lk(id, code, lang, label) VALUES (6, 'COURTESY_HAJJ', 'en', 'Courtesy Hajj');
INSERT INTO shj_portal.sha_ritual_type_lk(id, code, lang, label) VALUES (7, 'EXTERNAL_UMRAH', 'ar', 'عمرة الخارج');
INSERT INTO shj_portal.sha_ritual_type_lk(id, code, lang, label) VALUES (8, 'EXTERNAL_UMRAH', 'en', 'External Umrah');
INSERT INTO shj_portal.sha_ritual_type_lk(id, code, lang, label) VALUES (9, 'INTERNAL_UMRAH', 'ar', 'عمرة الداخل');
INSERT INTO shj_portal.sha_ritual_type_lk(id, code, lang, label) VALUES (10, 'INTERNAL_UMRAH', 'en', 'Internal Umrah');
SET IDENTITY_INSERT shj_portal.sha_ritual_type_lk OFF;

