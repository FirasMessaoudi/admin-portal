USE shc_portal
GO
-- shc_user data
SET IDENTITY_INSERT shc_portal.shc_user ON;
insert into shc_portal.shc_user (id, nin, gender, mobile_number, date_of_birth_gregorian,
                                 password_hash, first_name, father_name, family_name, activated, creation_date)
values ('1', 1234567897, 'M',
        512345678, convert(date, '14/02/1972', 103), '$2a$10$A81/FuMFJWcxaJhUcL8isuVeKKa.hk7GVzTVTyf7xe/XoMVWuKckK',
        N'سعد'
           , N'عبد الرحمن', N'الغامدي', 1, current_timestamp);
SET IDENTITY_INSERT shc_portal.shc_user OFF;

-- shc_config data
INSERT INTO shc_portal.shc_config (conf_key, conf_value)
VALUES ('elm.providers.cache.read.timeout', 500);
INSERT INTO shc_portal.shc_config (conf_key, conf_value)
VALUES ('elm.providers.cache.write.timeout', 1000);
INSERT INTO shc_portal.shc_config (conf_key, conf_value)
VALUES ('elm.providers.cache.delete.timeout', 1000);

INSERT INTO shc_portal.shc_config (conf_key, conf_value)
VALUES ('elm.providers.yakeen.username', 'changeit');
INSERT INTO shc_portal.shc_config (conf_key, conf_value)
VALUES ('elm.providers.yakeen.password', 'changeit');
INSERT INTO shc_portal.shc_config (conf_key, conf_value)
VALUES ('elm.providers.yakeen.chargecode', 'changeit');
INSERT INTO shc_portal.shc_config (conf_key, conf_value)
VALUES ('elm.providers.yakeen.url', 'changeit');
INSERT INTO shc_portal.shc_config (conf_key, conf_value)
VALUES ('elm.providers.yakeen.connect.timeout', 500);
INSERT INTO shc_portal.shc_config (conf_key, conf_value)
VALUES ('elm.providers.yakeen.receive.timeout', 500);
INSERT INTO shc_portal.shc_config (conf_key, conf_value)
VALUES ('elm.providers.yakeen.mock.enabled', 'true');

INSERT INTO shc_portal.shc_config (conf_key, conf_value)
VALUES ('elm.providers.email.host', '192.168.0.200');
INSERT INTO shc_portal.shc_config (conf_key, conf_value)
VALUES ('elm.providers.email.port', 25);
INSERT INTO shc_portal.shc_config (conf_key, conf_value)
VALUES ('elm.providers.email.username', '');
INSERT INTO shc_portal.shc_config (conf_key, conf_value)
VALUES ('elm.providers.email.password', '');
INSERT INTO shc_portal.shc_config (conf_key, conf_value)
VALUES ('elm.providers.email.from.address', 'no-reply@elm.sa');
INSERT INTO shc_portal.shc_config (conf_key, conf_value)
VALUES ('elm.providers.email.from.name', 'Elm Product');
INSERT INTO shc_portal.shc_config (conf_key, conf_value)
VALUES ('elm.providers.email.smtp.auth', 'false');
INSERT INTO shc_portal.shc_config (conf_key, conf_value)
VALUES ('elm.providers.email.mock.enabled', 'false');

INSERT INTO shc_portal.shc_config (conf_key, conf_value)
VALUES ('elm.providers.smsgateway.url', 'http://te1-iqa-rv-sg:8080/ElmSMSGatewayEJB/ElmSMSGatewayWS');
INSERT INTO shc_portal.shc_config (conf_key, conf_value)
VALUES ('elm.providers.smsgateway.connect.timeout', 500);
INSERT INTO shc_portal.shc_config (conf_key, conf_value)
VALUES ('elm.providers.smsgateway.receive.timeout', 500);
INSERT INTO shc_portal.shc_config (conf_key, conf_value)
VALUES ('elm.providers.smsgateway.username', 'changeit');
INSERT INTO shc_portal.shc_config (conf_key, conf_value)
VALUES ('elm.providers.smsgateway.password', 'changeit');
INSERT INTO shc_portal.shc_config (conf_key, conf_value)
VALUES ('elm.providers.smsgateway.sendername', 'changeit');
INSERT INTO shc_portal.shc_config (conf_key, conf_value)
VALUES ('elm.providers.smsgateway.mock.enabled', 'true');

INSERT INTO shc_portal.shc_config (conf_key, conf_value)
VALUES ('elm.providers.recaptcha.key.site', '6Le30G0UAAAAAM3Kwdf4V_feWJ-zD7OFAjxqO3Vo');
INSERT INTO shc_portal.shc_config (conf_key, conf_value)
VALUES ('elm.providers.recaptcha.key.secret', '6Le30G0UAAAAAIBhhUZ-TtNdmbCRqzoxNftB5W1w');
INSERT INTO shc_portal.shc_config (conf_key, conf_value)
VALUES ('elm.providers.recaptcha.verify.url', 'https://www.google.com/recaptcha/api/siteverify');
INSERT INTO shc_portal.shc_config (conf_key, conf_value)
VALUES ('elm.providers.recaptcha.mock.enabled', 'false');
INSERT INTO shc_portal.shc_config (conf_key, conf_value)
VALUES ('elm.providers.recaptcha.connection.timeout', 500);
INSERT INTO shc_portal.shc_config (conf_key, conf_value)
VALUES ('elm.providers.recaptcha.read.timeout', 500);

INSERT INTO shc_portal.shc_config (conf_key, conf_value)
VALUES ('login.failed.max.attempts', '3');

INSERT INTO shc_portal.shc_config (conf_key, conf_value)
VALUES ('elm.providers.filescan.mock.enabled', 'false');
INSERT INTO shc_portal.shc_config (conf_key, conf_value)
VALUES ('elm.providers.filescan.read.timeout', 10000);
INSERT INTO shc_portal.shc_config (conf_key, conf_value)
VALUES ('elm.providers.filescan.connection.timeout', 10000);
INSERT INTO shc_portal.shc_config (conf_key, conf_value)
VALUES ('elm.providers.filescan.rest.url', 'http://localhost:8080/dcc-engines-filescan/scan-file');
INSERT INTO shc_portal.shc_config (conf_key, conf_value)
VALUES ('elm.providers.filescan.username', 'dccfilescan-username');
INSERT INTO shc_portal.shc_config (conf_key, conf_value)
VALUES ('elm.providers.filescan.password', 'dccfilescan-password');

INSERT INTO shc_portal.shc_config (conf_key, conf_value)
VALUES ('elm.engines.filescan.host', '192.168.46.145');
INSERT INTO shc_portal.shc_config (conf_key, conf_value)
VALUES ('elm.engines.filescan.port', '1344');
INSERT INTO shc_portal.shc_config (conf_key, conf_value)
VALUES ('elm.engines.filescan.username', 'shc_portalfilescan-username');
INSERT INTO shc_portal.shc_config (conf_key, conf_value)
VALUES ('elm.engines.filescan.password', 'shc_portalfilescan-password');

GO
-- update shc_user data in 1.2.0 version
USE shc_portal
GO
UPDATE shc_portal.shc_user
SET password_hash = '$2a$10$A81/FuMFJWcxaJhUcL8isuVeKKa.hk7GVzTVTyf7xe/XoMVWuKckK'
where id = 1;

INSERT INTO shc_portal.shc_config (conf_key, conf_value)
VALUES ('elm.providers.recaptcha.invisible.key.site', '6Le30G0UAAAAAM3Kwdf4V_feWJ-zD7OFAjxqO3Vo');
INSERT INTO shc_portal.shc_config (conf_key, conf_value)
VALUES ('elm.providers.recaptcha.invisible.key.secret', '6Le30G0UAAAAAIBhhUZ-TtNdmbCRqzoxNftB5W1w');
GO
INSERT INTO shc_portal.shc_user_password_history (user_id, old_password_hash) values (1, '$2a$10$MLt2QkqgBSo5WdVu5UJXjunvi0t/h.BKDJQWzO2tyrQKBysLmc9ou');

DELETE FROM shc_portal.shc_config
where conf_key = 'elm.engines.filescan.host';
DELETE FROM shc_portal.shc_config
where conf_key = 'elm.engines.filescan.port';
GO

UPDATE shc_portal.shc_config
SET conf_value='http://192.168.2.149:8080/dcc-engines-filescan/scan-file'
where conf_key = 'elm.providers.filescan.rest.url';
GO
UPDATE shc_portal.shc_user
SET date_of_birth_hijri='14400505'
where id = 1;
GO

DELETE
FROM shc_portal.shc_config
where conf_key = 'elm.engines.filescan.username';
DELETE
FROM shc_portal.shc_config
where conf_key = 'elm.engines.filescan.password';
GO

INSERT INTO shc_portal.shc_config (conf_key, conf_value) VALUES ('elm.providers.email.ssl.enabled', 'false');
INSERT INTO shc_portal.shc_config (conf_key, conf_value)
VALUES ('elm.providers.email.read.timeout', 5000);
INSERT INTO shc_portal.shc_config (conf_key, conf_value)
VALUES ('elm.providers.email.connection.timeout', 5000);
GO
-- update script for shc_portal aash version 1.7.1
USE shc_portal
GO
UPDATE shc_portal.shc_user
SET email = 'sgh@elm.sa'
where id = 1;
GO

INSERT INTO shc_portal.shc_config (conf_key, conf_value) VALUES ('login.simultaneous.enabled', 'false');
GO
-- update script for shc_portal aash version 1.8.0
USE shc_portal
GO
INSERT INTO shc_portal.shc_config (conf_key, conf_value) VALUES ('elm.commons.web.cors.allowed_origins', 'http://localhost:8080,http://localhost:4200,http://localhost:4100,http://localhost:8200,http://127.0.0.1:8200,http://ci-shc_portal.elm.com.sa:8080');
INSERT INTO shc_portal.shc_config (conf_key, conf_value)
VALUES ('elm.commons.web.cors.allowed_methods', 'GET,POST,PUT,OPTIONS');
GO

UPDATE shc_portal.shc_config
SET conf_value = '5000'
WHERE conf_key = 'elm.providers.cache.read.timeout';
UPDATE shc_portal.shc_config
SET conf_value = '5000'
WHERE conf_key = 'elm.providers.yakeen.connect.timeout';
UPDATE shc_portal.shc_config
SET conf_value = '5000'
WHERE conf_key = 'elm.providers.yakeen.receive.timeout';
UPDATE shc_portal.shc_config
SET conf_value = '5000'
WHERE conf_key = 'elm.providers.smsgateway.connect.timeout';
UPDATE shc_portal.shc_config
SET conf_value = '5000'
WHERE conf_key = 'elm.providers.smsgateway.receive.timeout';
UPDATE shc_portal.shc_config
SET conf_value = '5000'
WHERE conf_key = 'elm.providers.recaptcha.connection.timeout';
UPDATE shc_portal.shc_config
SET conf_value = '5000'
WHERE conf_key = 'elm.providers.recaptcha.read.timeout';
GO

-- insert authorities
SET IDENTITY_INSERT shc_portal.shc_authority_lk ON;
INSERT INTO shc_portal.shc_authority_lk(id, label_ar, label_en, code, parent_id)
VALUES (1, N'لوحة المعلومات', 'Admin Dashboard', 'ADMIN_DASHBOARD', NULL);
INSERT INTO shc_portal.shc_authority_lk(id, label_ar, label_en, code, parent_id)
VALUES (2, N'إدارة المستخدمين', 'User Management', 'USER_MANAGEMENT', NULL);
INSERT INTO shc_portal.shc_authority_lk(id, label_ar, label_en, code, parent_id)
VALUES (3, N'إضافة مستخدم', 'Add User', 'ADD_USER', 2);
INSERT INTO shc_portal.shc_authority_lk(id, label_ar, label_en, code, parent_id)
VALUES (4, N'تعديل مستخدم', 'Edit User', 'EDIT_USER', 2);
INSERT INTO shc_portal.shc_authority_lk(id, label_ar, label_en, code, parent_id)
VALUES (5, N'تغيير حالة مستخدم', 'Change User Status', 'CHANGE_USER_STATUS', 2);
INSERT INTO shc_portal.shc_authority_lk(id, label_ar, label_en, code, parent_id)
VALUES (6, N'إعادة تعيين كلمة المرور', 'Reset Password', 'RESET_PASSWORD', 2);
INSERT INTO shc_portal.shc_authority_lk(id, label_ar, label_en, code, parent_id)
VALUES (7, N'حذف مستخدم', 'Delete User', 'DELETE_USER', 2);
INSERT INTO shc_portal.shc_authority_lk(id, label_ar, label_en, code, parent_id)
VALUES (8, N'إدارة الأدوار', 'Role Management', 'ROLE_MANAGEMENT', NULL);
INSERT INTO shc_portal.shc_authority_lk(id, label_ar, label_en, code, parent_id)
VALUES (9, N'إضافة دور', 'Add Role', 'ADD_ROLE', 8);
INSERT INTO shc_portal.shc_authority_lk(id, label_ar, label_en, code, parent_id)
VALUES (10, N'تعديل دور', 'Edit Role', 'EDIT_ROLE', 8);
INSERT INTO shc_portal.shc_authority_lk(id, label_ar, label_en, code, parent_id)
VALUES (11, N'حذف دور', 'Delete Role', 'DELETE_ROLE', 8);
INSERT INTO shc_portal.shc_authority_lk(id, label_ar, label_en, code, parent_id)
VALUES (12, N'تغيير حالة دور', 'Change Role Status', 'CHANGE_ROLE_STATUS', 8);
INSERT INTO shc_portal.shc_authority_lk(id, label_ar, label_en, code, parent_id)
VALUES (13, N'إعادة تعيين كلمة مرور المستخدم', 'Reset User Password', 'RESET_USER_PASSWORD', 2);
-- new authorities
INSERT INTO shc_portal.shc_authority_lk(id, label_ar, label_en, code, parent_id)
VALUES (14, N'إدارة البطاقات', 'Card Management', 'CARD_MANAGEMENT', NULL);
INSERT INTO shc_portal.shc_authority_lk(id, label_ar, label_en, code, parent_id)
VALUES (15, N'عرض تفاصيل البطاقة', 'View Card Details', 'VIEW_CARD_DETAILS', 14);
INSERT INTO shc_portal.shc_authority_lk(id, label_ar, label_en, code, parent_id)
VALUES (16, N'تعديل البطاقة', 'Update Card', 'UPDATE_CARD', 14);
INSERT INTO shc_portal.shc_authority_lk(id, label_ar, label_en, code, parent_id)
VALUES (17, N'تفعيل البطاقة', 'Activate Card', 'ACTIVATE_CARD', 14);
INSERT INTO shc_portal.shc_authority_lk(id, label_ar, label_en, code, parent_id)
VALUES (18, N'إلغاء البطاقة', 'Cancel Card', 'CANCEL_CARD', 14);
INSERT INTO shc_portal.shc_authority_lk(id, label_ar, label_en, code, parent_id)
VALUES (19, N'إيقاف البطاقة', 'Suspend Card', 'SUSPEND_CARD', 14);
INSERT INTO shc_portal.shc_authority_lk(id, label_ar, label_en, code, parent_id)
VALUES (20, N'إعادة إصدار البطاقة', 'Reissue Card', 'REISSUE_CARD', 14);
INSERT INTO shc_portal.shc_authority_lk(id, label_ar, label_en, code, parent_id)
VALUES (21, N'إضافة بطاقة', 'Add Card', 'ADD_CARD', 14);
INSERT INTO shc_portal.shc_authority_lk(id, label_ar, label_en, code, parent_id)
VALUES (22, N'إنشاء معلومات ضيف الرحمن', 'Add Applicant Profile', 'ADD_APPLICANT_PROFILE', 14);
INSERT INTO shc_portal.shc_authority_lk(id, label_ar, label_en, code, parent_id)
VALUES (23, N'موافقة إنشاء معلومات ضيف الرحمن', 'Approve Applicant Profile', 'APPROVE_APPLICANT_PROFILE', 14);
INSERT INTO shc_portal.shc_authority_lk(id, label_ar, label_en, code, parent_id)
VALUES (24, N'إدارة طلبات الطباعة', 'Printing Request Management', 'PRINTING_REQUEST_MANAGEMENT', NULL);
INSERT INTO shc_portal.shc_authority_lk(id, label_ar, label_en, code, parent_id)
VALUES (25, N'عرض تفاصيل طلب الطباعة', 'View Printing Request Details', 'VIEW_PRINTING_REQUEST_DETAILS', 24);
INSERT INTO shc_portal.shc_authority_lk(id, label_ar, label_en, code, parent_id)
VALUES (26, N'إضافة طلب طباعة', 'Add Printing Request', 'ADD_PRINTING_REQUEST', 24);
INSERT INTO shc_portal.shc_authority_lk(id, label_ar, label_en, code, parent_id)
VALUES (27, N'إدارة طلبات البيانات', 'Data Request Management', 'DATA_REQUEST_MANAGEMENT', NULL);
INSERT INTO shc_portal.shc_authority_lk(id, label_ar, label_en, code, parent_id)
VALUES (28, N'عرض تفاصيل طلب البيانات', 'View Data Request Details', 'VIEW_DATA_REQUEST_DETAILS', 27);
INSERT INTO shc_portal.shc_authority_lk(id, label_ar, label_en, code, parent_id)
VALUES (29, N'إنشاء طلب بيانات', 'Create Data Request', 'CREATE_DATA_REQUEST', 27);
INSERT INTO shc_portal.shc_authority_lk(id, label_ar, label_en, code, parent_id)
VALUES (30, N'عرض الملف الشخصي', 'View My Profile', 'VIEW_MY_PROFILE', 2);
INSERT INTO shc_portal.shc_authority_lk(id, label_ar, label_en, code, parent_id)
VALUES (31, N'تعديل الملف الشخصي', 'Update My Profile', 'UPDATE_MY_PROFILE', 2);
INSERT INTO shc_portal.shc_authority_lk(id, label_ar, label_en, code, parent_id)
VALUES (32, N'تسجيل حملة', 'Register Hamlah', 'REGISTER_HAMLAH', NULL);
INSERT INTO shc_portal.shc_authority_lk(id, label_ar, label_en, code, parent_id)
VALUES (33, N'استعلام طلب تسجيل حملة', 'Enquiry Hamlah Registration', 'ENQUIRY_HAMLAH_REGISTRATION', 32);
SET
IDENTITY_INSERT shc_portal.shc_authority_lk OFF;
GO

SET IDENTITY_INSERT shc_portal.shc_role ON;
INSERT INTO shc_portal.shc_role(id, label_ar, label_en, deleted, activated)
VALUES (1, N'مشرف النظام', 'System Admin', 0, 1);
INSERT INTO shc_portal.shc_role(id, label_ar, label_en, deleted, activated)
VALUES (2, N'مستخدم النظام', 'System User', 0, 1);
-- new roles
INSERT INTO shc_portal.shc_role(id, label_ar, label_en, deleted, activated)
VALUES (3, N'مشرف التسجيل', 'Enrollment Officer Supervisor', 0, 1);
INSERT INTO shc_portal.shc_role(id, label_ar, label_en, deleted, activated)
VALUES (4, N'موظف التسجيل', 'Enrollment Officer', 0, 1);
INSERT INTO shc_portal.shc_role(id, label_ar, label_en, deleted, activated)
VALUES (5, N'مشرف مركز الخدمة', 'Service Center Agent Supervisor', 0, 1);
INSERT INTO shc_portal.shc_role(id, label_ar, label_en, deleted, activated)
VALUES (6, N'موظف مركز الخدمة', 'Service Center Agent', 0, 1);
INSERT INTO shc_portal.shc_role(id, label_ar, label_en, deleted, activated)
VALUES (7, N'مشرف الطباعة', 'Printing Supervisor', 0, 1);
INSERT INTO shc_portal.shc_role(id, label_ar, label_en, deleted, activated)
VALUES (8, N'موظف الطباعة', 'Printing User', 0, 1);
INSERT INTO shc_portal.shc_role(id, label_ar, label_en, deleted, activated)
VALUES (9, N'مشرف الحملة', 'Hamlah Owner', 0, 1);
INSERT INTO shc_portal.shc_role(id, label_ar, label_en, deleted, activated)
VALUES (10, N'موظف الحملة', 'Hamlah User', 0, 1);
INSERT INTO shc_portal.shc_role(id, label_ar, label_en, deleted, activated)
VALUES (11, N'مشرف المعرفات الرقمية', 'Digital ID Issuer Supervisor', 0, 1);
INSERT INTO shc_portal.shc_role(id, label_ar, label_en, deleted, activated)
VALUES (12, N'موظف المعرفات الرقمية', 'Digital ID Issuer', 0, 1);


SET
IDENTITY_INSERT shc_portal.shc_role OFF;
GO


INSERT INTO shc_portal.shc_role_authority(role_id, authority_id) VALUES (1, 1);
INSERT INTO shc_portal.shc_role_authority(role_id, authority_id)
VALUES (1, 2);
INSERT INTO shc_portal.shc_role_authority(role_id, authority_id)
VALUES (1, 3);
INSERT INTO shc_portal.shc_role_authority(role_id, authority_id)
VALUES (1, 4);
INSERT INTO shc_portal.shc_role_authority(role_id, authority_id)
VALUES (1, 5);
INSERT INTO shc_portal.shc_role_authority(role_id, authority_id)
VALUES (1, 6);
INSERT INTO shc_portal.shc_role_authority(role_id, authority_id)
VALUES (1, 7);
INSERT INTO shc_portal.shc_role_authority(role_id, authority_id)
VALUES (1, 8);
INSERT INTO shc_portal.shc_role_authority(role_id, authority_id)
VALUES (1, 9);
INSERT INTO shc_portal.shc_role_authority(role_id, authority_id)
VALUES (1, 10);
INSERT INTO shc_portal.shc_role_authority(role_id, authority_id)
VALUES (1, 11);
INSERT INTO shc_portal.shc_role_authority(role_id, authority_id)
VALUES (1, 12);
INSERT INTO shc_portal.shc_role_authority(role_id, authority_id)
VALUES (1, 13);
INSERT INTO shc_portal.shc_role_authority(role_id, authority_id)
VALUES (3, 13);
INSERT INTO shc_portal.shc_role_authority(role_id, authority_id)
VALUES (5, 13);
INSERT INTO shc_portal.shc_role_authority(role_id, authority_id)
VALUES (7, 13);
INSERT INTO shc_portal.shc_role_authority(role_id, authority_id)
VALUES (9, 13);
INSERT INTO shc_portal.shc_role_authority(role_id, authority_id)
VALUES (11, 13);

INSERT INTO shc_portal.shc_role_authority(role_id, authority_id) VALUES (2, 1);
-- new roles authorities
-- Enrollment Officer Supervisor
INSERT INTO shc_portal.shc_role_authority(role_id, authority_id)
VALUES (3, 1);
INSERT INTO shc_portal.shc_role_authority(role_id, authority_id)
VALUES (3, 2);
INSERT INTO shc_portal.shc_role_authority(role_id, authority_id)
VALUES (3, 3);
INSERT INTO shc_portal.shc_role_authority(role_id, authority_id)
VALUES (3, 4);
INSERT INTO shc_portal.shc_role_authority(role_id, authority_id)
VALUES (3, 5);
INSERT INTO shc_portal.shc_role_authority(role_id, authority_id)
VALUES (3, 6);
INSERT INTO shc_portal.shc_role_authority(role_id, authority_id)
VALUES (3, 7);
INSERT INTO shc_portal.shc_role_authority(role_id, authority_id)
VALUES (3, 14);
INSERT INTO shc_portal.shc_role_authority(role_id, authority_id)
VALUES (3, 15);
INSERT INTO shc_portal.shc_role_authority(role_id, authority_id)
VALUES (3, 16);
INSERT INTO shc_portal.shc_role_authority(role_id, authority_id)
VALUES (3, 17);
INSERT INTO shc_portal.shc_role_authority(role_id, authority_id)
VALUES (3, 18);
INSERT INTO shc_portal.shc_role_authority(role_id, authority_id)
VALUES (3, 19);
INSERT INTO shc_portal.shc_role_authority(role_id, authority_id)
VALUES (3, 30);
INSERT INTO shc_portal.shc_role_authority(role_id, authority_id)
VALUES (3, 31);
-- Enrollment Officer
INSERT INTO shc_portal.shc_role_authority(role_id, authority_id)
VALUES (4, 1);
INSERT INTO shc_portal.shc_role_authority(role_id, authority_id)
VALUES (4, 6);
INSERT INTO shc_portal.shc_role_authority(role_id, authority_id)
VALUES (4, 14);
INSERT INTO shc_portal.shc_role_authority(role_id, authority_id)
VALUES (4, 15);
INSERT INTO shc_portal.shc_role_authority(role_id, authority_id)
VALUES (4, 16);
INSERT INTO shc_portal.shc_role_authority(role_id, authority_id)
VALUES (4, 17);
INSERT INTO shc_portal.shc_role_authority(role_id, authority_id)
VALUES (4, 18);
INSERT INTO shc_portal.shc_role_authority(role_id, authority_id)
VALUES (4, 19);
INSERT INTO shc_portal.shc_role_authority(role_id, authority_id)
VALUES (4, 30);
INSERT INTO shc_portal.shc_role_authority(role_id, authority_id)
VALUES (4, 31);
-- Service Center Agent Supervisor
INSERT INTO shc_portal.shc_role_authority(role_id, authority_id)
VALUES (5, 1);
INSERT INTO shc_portal.shc_role_authority(role_id, authority_id)
VALUES (5, 2);
INSERT INTO shc_portal.shc_role_authority(role_id, authority_id)
VALUES (5, 3);
INSERT INTO shc_portal.shc_role_authority(role_id, authority_id)
VALUES (5, 4);
INSERT INTO shc_portal.shc_role_authority(role_id, authority_id)
VALUES (5, 5);
INSERT INTO shc_portal.shc_role_authority(role_id, authority_id)
VALUES (5, 6);
INSERT INTO shc_portal.shc_role_authority(role_id, authority_id)
VALUES (5, 7);
INSERT INTO shc_portal.shc_role_authority(role_id, authority_id)
VALUES (5, 14);
INSERT INTO shc_portal.shc_role_authority(role_id, authority_id)
VALUES (5, 15);
INSERT INTO shc_portal.shc_role_authority(role_id, authority_id)
VALUES (5, 16);
INSERT INTO shc_portal.shc_role_authority(role_id, authority_id)
VALUES (5, 17);
INSERT INTO shc_portal.shc_role_authority(role_id, authority_id)
VALUES (5, 18);
INSERT INTO shc_portal.shc_role_authority(role_id, authority_id)
VALUES (5, 19);
INSERT INTO shc_portal.shc_role_authority(role_id, authority_id)
VALUES (5, 20);
INSERT INTO shc_portal.shc_role_authority(role_id, authority_id)
VALUES (5, 21);
INSERT INTO shc_portal.shc_role_authority(role_id, authority_id)
VALUES (5, 22);
INSERT INTO shc_portal.shc_role_authority(role_id, authority_id)
VALUES (5, 23);
INSERT INTO shc_portal.shc_role_authority(role_id, authority_id)
VALUES (5, 30);
INSERT INTO shc_portal.shc_role_authority(role_id, authority_id)
VALUES (5, 31);
-- Service Center Agent
INSERT INTO shc_portal.shc_role_authority(role_id, authority_id)
VALUES (6, 1);
INSERT INTO shc_portal.shc_role_authority(role_id, authority_id)
VALUES (6, 6);
INSERT INTO shc_portal.shc_role_authority(role_id, authority_id)
VALUES (6, 14);
INSERT INTO shc_portal.shc_role_authority(role_id, authority_id)
VALUES (6, 15);
INSERT INTO shc_portal.shc_role_authority(role_id, authority_id)
VALUES (6, 16);
INSERT INTO shc_portal.shc_role_authority(role_id, authority_id)
VALUES (6, 17);
INSERT INTO shc_portal.shc_role_authority(role_id, authority_id)
VALUES (6, 18);
INSERT INTO shc_portal.shc_role_authority(role_id, authority_id)
VALUES (6, 19);
INSERT INTO shc_portal.shc_role_authority(role_id, authority_id)
VALUES (6, 20);
INSERT INTO shc_portal.shc_role_authority(role_id, authority_id)
VALUES (6, 21);
INSERT INTO shc_portal.shc_role_authority(role_id, authority_id)
VALUES (6, 22);
INSERT INTO shc_portal.shc_role_authority(role_id, authority_id)
VALUES (6, 30);
INSERT INTO shc_portal.shc_role_authority(role_id, authority_id)
VALUES (6, 31);
-- Printing Supervisor
INSERT INTO shc_portal.shc_role_authority(role_id, authority_id)
VALUES (7, 1);
INSERT INTO shc_portal.shc_role_authority(role_id, authority_id)
VALUES (7, 2);
INSERT INTO shc_portal.shc_role_authority(role_id, authority_id)
VALUES (7, 3);
INSERT INTO shc_portal.shc_role_authority(role_id, authority_id)
VALUES (7, 4);
INSERT INTO shc_portal.shc_role_authority(role_id, authority_id)
VALUES (7, 5);
INSERT INTO shc_portal.shc_role_authority(role_id, authority_id)
VALUES (7, 6);
INSERT INTO shc_portal.shc_role_authority(role_id, authority_id)
VALUES (7, 7);
INSERT INTO shc_portal.shc_role_authority(role_id, authority_id)
VALUES (7, 24);
INSERT INTO shc_portal.shc_role_authority(role_id, authority_id)
VALUES (7, 25);
INSERT INTO shc_portal.shc_role_authority(role_id, authority_id)
VALUES (7, 26);
INSERT INTO shc_portal.shc_role_authority(role_id, authority_id)
VALUES (7, 30);
INSERT INTO shc_portal.shc_role_authority(role_id, authority_id)
VALUES (7, 31);
-- Printing User
INSERT INTO shc_portal.shc_role_authority(role_id, authority_id)
VALUES (8, 1);
INSERT INTO shc_portal.shc_role_authority(role_id, authority_id)
VALUES (8, 6);
INSERT INTO shc_portal.shc_role_authority(role_id, authority_id)
VALUES (8, 24);
INSERT INTO shc_portal.shc_role_authority(role_id, authority_id)
VALUES (8, 25);
INSERT INTO shc_portal.shc_role_authority(role_id, authority_id)
VALUES (8, 26);
INSERT INTO shc_portal.shc_role_authority(role_id, authority_id)
VALUES (8, 30);
INSERT INTO shc_portal.shc_role_authority(role_id, authority_id)
VALUES (8, 31);
-- Hamlah Owner
INSERT INTO shc_portal.shc_role_authority(role_id, authority_id)
VALUES (9, 1);
INSERT INTO shc_portal.shc_role_authority(role_id, authority_id)
VALUES (9, 2);
INSERT INTO shc_portal.shc_role_authority(role_id, authority_id)
VALUES (9, 3);
INSERT INTO shc_portal.shc_role_authority(role_id, authority_id)
VALUES (9, 4);
INSERT INTO shc_portal.shc_role_authority(role_id, authority_id)
VALUES (9, 5);
INSERT INTO shc_portal.shc_role_authority(role_id, authority_id)
VALUES (9, 6);
INSERT INTO shc_portal.shc_role_authority(role_id, authority_id)
VALUES (9, 7);
INSERT INTO shc_portal.shc_role_authority(role_id, authority_id)
VALUES (9, 14);
INSERT INTO shc_portal.shc_role_authority(role_id, authority_id)
VALUES (9, 15);
INSERT INTO shc_portal.shc_role_authority(role_id, authority_id)
VALUES (9, 16);
INSERT INTO shc_portal.shc_role_authority(role_id, authority_id)
VALUES (9, 17);
INSERT INTO shc_portal.shc_role_authority(role_id, authority_id)
VALUES (9, 30);
INSERT INTO shc_portal.shc_role_authority(role_id, authority_id)
VALUES (9, 31);
INSERT INTO shc_portal.shc_role_authority(role_id, authority_id)
VALUES (9, 32);
INSERT INTO shc_portal.shc_role_authority(role_id, authority_id)
VALUES (9, 33);
-- Hamlah User
INSERT INTO shc_portal.shc_role_authority(role_id, authority_id)
VALUES (10, 1);
INSERT INTO shc_portal.shc_role_authority(role_id, authority_id)
VALUES (10, 6);
INSERT INTO shc_portal.shc_role_authority(role_id, authority_id)
VALUES (10, 14);
INSERT INTO shc_portal.shc_role_authority(role_id, authority_id)
VALUES (10, 15);
INSERT INTO shc_portal.shc_role_authority(role_id, authority_id)
VALUES (10, 30);
INSERT INTO shc_portal.shc_role_authority(role_id, authority_id)
VALUES (10, 31);
-- Digital ID Issuer Supervisor
INSERT INTO shc_portal.shc_role_authority(role_id, authority_id)
VALUES (11, 1);
INSERT INTO shc_portal.shc_role_authority(role_id, authority_id)
VALUES (11, 2);
INSERT INTO shc_portal.shc_role_authority(role_id, authority_id)
VALUES (11, 3);
INSERT INTO shc_portal.shc_role_authority(role_id, authority_id)
VALUES (11, 4);
INSERT INTO shc_portal.shc_role_authority(role_id, authority_id)
VALUES (11, 5);
INSERT INTO shc_portal.shc_role_authority(role_id, authority_id)
VALUES (11, 6);
INSERT INTO shc_portal.shc_role_authority(role_id, authority_id)
VALUES (11, 7);
INSERT INTO shc_portal.shc_role_authority(role_id, authority_id)
VALUES (11, 27);
INSERT INTO shc_portal.shc_role_authority(role_id, authority_id)
VALUES (11, 28);
INSERT INTO shc_portal.shc_role_authority(role_id, authority_id)
VALUES (11, 29);
INSERT INTO shc_portal.shc_role_authority(role_id, authority_id)
VALUES (11, 30);
INSERT INTO shc_portal.shc_role_authority(role_id, authority_id)
VALUES (11, 31);
-- Digital ID Issuer
INSERT INTO shc_portal.shc_role_authority(role_id, authority_id)
VALUES (12, 1);
INSERT INTO shc_portal.shc_role_authority(role_id, authority_id)
VALUES (12, 6);
INSERT INTO shc_portal.shc_role_authority(role_id, authority_id)
VALUES (12, 27);
INSERT INTO shc_portal.shc_role_authority(role_id, authority_id)
VALUES (12, 28);
INSERT INTO shc_portal.shc_role_authority(role_id, authority_id)
VALUES (12, 29);
INSERT INTO shc_portal.shc_role_authority(role_id, authority_id)
VALUES (12, 30);
INSERT INTO shc_portal.shc_role_authority(role_id, authority_id)
VALUES (12, 31);

GO

UPDATE shc_portal.shc_user
SET number_of_tries = 0
WHERE number_of_tries IS NULL;
GO

SET IDENTITY_INSERT shc_portal.shc_user_role ON;
INSERT INTO shc_portal.shc_user_role(id, user_id, role_id, is_main_role)
VALUES (1, 1, 1, 1);
SET
IDENTITY_INSERT shc_portal.shc_user_role OFF;
GO

SET IDENTITY_INSERT shc_portal.shc_data_segment ON;
INSERT INTO shc_portal.shc_data_segment (id, template_file_name, label_ar, label_en)
VALUES (1, 'applicant-data.xlsx', N'بيانات ضيف الرحمن', 'Applicant Data'),
       (2, 'applicant-ritual-data.xlsx', N'بيانات الشعيرة لضيف الرحمن', 'Applicant Ritual Data'),
       (3, 'applicant-relatives-data.xlsx', N'بيانات أقارب ضيف الرحمن', 'Applicant Relatives Data'),
       (4, 'applicant-health-data.xlsx', N'البيانات الصحية لضيف الرحمن', 'Applicant Health Data'),
       (5, 'applicant-vaccination-data.xlsx', N'بيانات تطعيمات ضيف الرحمن', 'Applicant Vaccination Data'),
       (6, 'applicant-disease-data.xlsx', N'بيانات أمراض ضيف الرحمن', 'Applicant Disease Data');
SET IDENTITY_INSERT shc_portal.shc_data_segment OFF;
GO

-- add data request statuses
SET IDENTITY_INSERT shc_portal.shc_data_request_status_lk ON;
INSERT INTO shc_portal.shc_data_request_status_lk(id, label_ar, label_en, creation_date)
VALUES (1, N'جديد', 'New', current_timestamp),
       (2, N'مؤكد', 'Confirmed', current_timestamp),
       (3, N'تحت المعالجة', 'Under Processing', current_timestamp),
       (4, N'معالج بنجاح', 'Processed Successfully', current_timestamp),
       (5, N'معالج مع أخطاء', 'Processed With Errors', current_timestamp),
       (6, N'ملغى', 'Cancelled', current_timestamp);
SET
IDENTITY_INSERT shc_portal.shc_data_request_status_lk OFF;

-- add sftp config
INSERT INTO shc_portal.shc_config (conf_key, conf_value)
VALUES ('sftp.client.protocol', 'sftp'),
       ('sftp.client.host', '127.0.0.1'),
       ('sftp.client.port', '22'),
       ('sftp.client.username', 'sftp-user'),
       ('sftp.client.password', 'Aa123456'),
       ('sftp.client.root-folder', '/data/smart-hajj/data-uploads/'),
       ('sftp.client.session-strict-host-key-checking', 'no'),
       ('sftp.client.session-connect-timeout', '15000'),
       ('sftp.client.channel-connected-timeout', '15000');
GO
-- insert ritual types
SET IDENTITY_INSERT shc_portal.shc_ritual_type_lk ON;
INSERT INTO shc_portal.shc_ritual_type_lk(id, code, lang, label)
VALUES (1, 'EXTERNAL_HAJJ', 'ar', N'حجاج الخارج');
INSERT INTO shc_portal.shc_ritual_type_lk(id, code, lang, label)
VALUES (2, 'EXTERNAL_HAJJ', 'en', 'External Hajj');
INSERT INTO shc_portal.shc_ritual_type_lk(id, code, lang, label)
VALUES (3, 'INTERNAL_HAJJ', 'ar', N'حجاج الداخل');
INSERT INTO shc_portal.shc_ritual_type_lk(id, code, lang, label)
VALUES (4, 'INTERNAL_HAJJ', 'en', 'Internal Hajj');
INSERT INTO shc_portal.shc_ritual_type_lk(id, code, lang, label)
VALUES (5, 'COURTESY_HAJJ', 'ar', N'حجاج المجاملة');
INSERT INTO shc_portal.shc_ritual_type_lk(id, code, lang, label)
VALUES (6, 'COURTESY_HAJJ', 'en', 'Courtesy Hajj');
INSERT INTO shc_portal.shc_ritual_type_lk(id, code, lang, label)
VALUES (7, 'EXTERNAL_UMRAH', 'ar', N'عمرة الخارج');
INSERT INTO shc_portal.shc_ritual_type_lk(id, code, lang, label)
VALUES (8, 'EXTERNAL_UMRAH', 'en', 'External Umrah');
INSERT INTO shc_portal.shc_ritual_type_lk(id, code, lang, label)
VALUES (9, 'INTERNAL_UMRAH', 'ar', N'عمرة الداخل');
INSERT INTO shc_portal.shc_ritual_type_lk(id, code, lang, label)
VALUES (10, 'INTERNAL_UMRAH', 'en', 'Internal Umrah');
SET
IDENTITY_INSERT shc_portal.shc_ritual_type_lk OFF;
GO

SET IDENTITY_INSERT shc_portal.shc_relative_relationship_lk ON;
INSERT INTO shc_portal.shc_relative_relationship_lk (id, code, lang, label)
VALUES (1, 'MOTHER', 'ar', N'أم');
INSERT INTO shc_portal.shc_relative_relationship_lk (id, code, lang, label)
VALUES (2, 'MOTHER', 'en', 'Mother');
INSERT INTO shc_portal.shc_relative_relationship_lk (id, code, lang, label)
VALUES (3, 'FATHER', 'ar', N'أب');
INSERT INTO shc_portal.shc_relative_relationship_lk (id, code, lang, label)
VALUES (4, 'FATHER', 'en', 'Father');
INSERT INTO shc_portal.shc_relative_relationship_lk (id, code, lang, label)
VALUES (5, 'WIFE', 'ar', N'زوجة');
INSERT INTO shc_portal.shc_relative_relationship_lk (id, code, lang, label)
VALUES (6, 'WIFE', 'en', 'Wife');
INSERT INTO shc_portal.shc_relative_relationship_lk (id, code, lang, label)
VALUES (7, 'HUSBAND', 'ar', N'زوج');
INSERT INTO shc_portal.shc_relative_relationship_lk (id, code, lang, label)
VALUES (8, 'HUSBAND', 'en', 'Husband');
INSERT INTO shc_portal.shc_relative_relationship_lk (id, code, lang, label)
VALUES (9, 'SON', 'ar', N'ابن');
INSERT INTO shc_portal.shc_relative_relationship_lk (id, code, lang, label)
VALUES (10, 'SON', 'en', 'Son');
INSERT INTO shc_portal.shc_relative_relationship_lk (id, code, lang, label)
VALUES (11, 'DAUGHTER', 'ar', N'ابنة');
INSERT INTO shc_portal.shc_relative_relationship_lk (id, code, lang, label)
VALUES (12, 'DAUGHTER', 'en', 'Daughter');
INSERT INTO shc_portal.shc_relative_relationship_lk (id, code, lang, label)
VALUES (13, 'BROTHER', 'ar', N'أخ');
INSERT INTO shc_portal.shc_relative_relationship_lk (id, code, lang, label)
VALUES (14, 'BROTHER', 'en', 'Brother');
INSERT INTO shc_portal.shc_relative_relationship_lk (id, code, lang, label)
VALUES (15, 'SISTER', 'ar', N'أخت');
INSERT INTO shc_portal.shc_relative_relationship_lk (id, code, lang, label)
VALUES (16, 'SISTER', 'en', 'Sister');
INSERT INTO shc_portal.shc_relative_relationship_lk (id, code, lang, label)
VALUES (17, 'RELATIVE', 'ar', N'قريب');
INSERT INTO shc_portal.shc_relative_relationship_lk (id, code, lang, label)
VALUES (18, 'RELATIVE', 'en', 'Relative');
INSERT INTO shc_portal.shc_relative_relationship_lk (id, code, lang, label)
VALUES (19, 'COMPANION', 'ar', N'مرافق');
INSERT INTO shc_portal.shc_relative_relationship_lk (id, code, lang, label)
VALUES (20, 'COMPANION', 'en', 'Companion');
SET
IDENTITY_INSERT shc_portal.shc_relative_relationship_lk OFF;
GO

SET IDENTITY_INSERT shc_portal.shc_health_immunization_lk ON;
INSERT INTO shc_portal.shc_health_immunization_lk(id, code, lang, label)
VALUES (1, 'COVID-19', 'ar', N'كورونا');
INSERT INTO shc_portal.shc_health_immunization_lk(id, code, lang, label)
VALUES (2, 'COVID-19', 'en', 'Covid-19');
INSERT INTO shc_portal.shc_health_immunization_lk(id, code, lang, label)
VALUES (3, 'YELLOW_FEVER', 'ar', N'الحمى الصفراء');
INSERT INTO shc_portal.shc_health_immunization_lk(id, code, lang, label)
VALUES (4, 'YELLOW_FEVER', 'en', 'Yellow Fever');
INSERT INTO shc_portal.shc_health_immunization_lk(id, code, lang, label)
VALUES (5, 'NEISSERIA_MENINGITIS', 'ar', N'الحمى الشوكية النيسرية');
INSERT INTO shc_portal.shc_health_immunization_lk(id, code, lang, label)
VALUES (6, 'NEISSERIA_MENINGITIS', 'en', 'Neisseria Meningitis');
INSERT INTO shc_portal.shc_health_immunization_lk(id, code, lang, label)
VALUES (7, 'POLIOMYELITIS', 'ar', N'شلل الأطفال');
INSERT INTO shc_portal.shc_health_immunization_lk(id, code, lang, label)
VALUES (8, 'POLIOMYELITIS', 'en', 'Poliomyelitis');
INSERT INTO shc_portal.shc_health_immunization_lk(id, code, lang, label)
VALUES (9, 'SEASONAL_FLU', 'ar', N'الإنفلونزا الموسمية');
INSERT INTO shc_portal.shc_health_immunization_lk(id, code, lang, label)
VALUES (10, 'SEASONAL_FLU', 'en', 'Seasonal Flu');
SET
IDENTITY_INSERT shc_portal.shc_health_immunization_lk OFF;
GO

SET IDENTITY_INSERT shc_portal.shc_health_special_needs_type_lk ON;
INSERT INTO shc_portal.shc_health_special_needs_type_lk (id, code, lang, label)
VALUES (1, 'WHEELCHAIR', 'ar', N'كرسي متحرك');
INSERT INTO shc_portal.shc_health_special_needs_type_lk (id, code, lang, label)
VALUES (2, 'WHEELCHAIR', 'en', 'Wheelchair');
INSERT INTO shc_portal.shc_health_special_needs_type_lk (id, code, lang, label)
VALUES (3, 'GOLF_CAR', 'ar', N'عربة نقل');
INSERT INTO shc_portal.shc_health_special_needs_type_lk (id, code, lang, label)
VALUES (4, 'GOLF_CAR', 'en', 'Golf Car');
INSERT INTO shc_portal.shc_health_special_needs_type_lk (id, code, lang, label)
VALUES (5, 'DEAF_DUMB', 'ar', N'صم وبكم');
INSERT INTO shc_portal.shc_health_special_needs_type_lk (id, code, lang, label)
VALUES (6, 'DEAF_DUMB', 'en', 'Deaf and Dumb');
SET
IDENTITY_INSERT shc_portal.shc_health_special_needs_type_lk OFF;
GO

SET IDENTITY_INSERT shc_portal.shc_card_status_lk ON;
INSERT INTO shc_portal.shc_card_status_lk (id, code, lang, label)
VALUES (1, 'READY_TO_PRINT', 'ar', N'جاهز للطباعة');
INSERT INTO shc_portal.shc_card_status_lk (id, code, lang, label)
VALUES (2, 'READY_TO_PRINT', 'en', 'Ready to Print');
INSERT INTO shc_portal.shc_card_status_lk (id, code, lang, label)
VALUES (3, 'SENT_FOR_PRINT', 'ar', N'تم الإرسال للطباعة');
INSERT INTO shc_portal.shc_card_status_lk (id, code, lang, label)
VALUES (4, 'SENT_FOR_PRINT', 'en', 'Sent for Print');
INSERT INTO shc_portal.shc_card_status_lk (id, code, lang, label)
VALUES (5, 'PRINTED', 'ar', N'تمت الطباعة');
INSERT INTO shc_portal.shc_card_status_lk (id, code, lang, label)
VALUES (6, 'PRINTED', 'en', 'Printed');
INSERT INTO shc_portal.shc_card_status_lk (id, code, lang, label)
VALUES (7, 'DISTRIBUTED', 'ar', N'تم التوزيع');
INSERT INTO shc_portal.shc_card_status_lk (id, code, lang, label)
VALUES (8, 'DISTRIBUTED', 'en', 'Distributed');
INSERT INTO shc_portal.shc_card_status_lk (id, code, lang, label)
VALUES (9, 'ACTIVE', 'ar', N'تم التفعيل');
INSERT INTO shc_portal.shc_card_status_lk (id, code, lang, label)
VALUES (10, 'ACTIVE', 'en', 'Active');
INSERT INTO shc_portal.shc_card_status_lk (id, code, lang, label)
VALUES (11, 'SUSPENDED', 'ar', N'تم الإيقاف');
INSERT INTO shc_portal.shc_card_status_lk (id, code, lang, label)
VALUES (12, 'SUSPENDED', 'en', 'Suspended');
INSERT INTO shc_portal.shc_card_status_lk (id, code, lang, label)
VALUES (13, 'CANCELLED', 'ar', N'تم الإلغاء');
INSERT INTO shc_portal.shc_card_status_lk (id, code, lang, label)
VALUES (14, 'CANCELLED', 'en', 'Cancelled');
INSERT INTO shc_portal.shc_card_status_lk (id, code, lang, label)
VALUES (15, 'WAITING_TO_SEND', 'ar', N'جاري الإرسال للطباعة');
INSERT INTO shc_portal.shc_card_status_lk (id, code, lang, label)
VALUES (16, 'WAITING_TO_SEND', 'en', 'Waiting to Send');
SET
IDENTITY_INSERT shc_portal.shc_card_status_lk OFF;
GO

-- add otp config
INSERT INTO shc_portal.shc_config (conf_key, conf_value)
VALUES ('otp.expiry.minutes', '5'),
       ('otp.pin.length', '4'),
       ('otp.mock.enabled', 'true');
GO

SET IDENTITY_INSERT shc_portal.shc_marital_status_lk ON;
INSERT INTO shc_portal.shc_marital_status_lk (id, code, lang, label)
VALUES (1, 'SINGLE', 'ar', N'أعزب');
INSERT INTO shc_portal.shc_marital_status_lk (id, code, lang, label)
VALUES (2, 'SINGLE', 'en', 'Single');
INSERT INTO shc_portal.shc_marital_status_lk (id, code, lang, label)
VALUES (3, 'MARRIED', 'ar', N'متزوج');
INSERT INTO shc_portal.shc_marital_status_lk (id, code, lang, label)
VALUES (4, 'MARRIED', 'en', 'Married');
INSERT INTO shc_portal.shc_marital_status_lk (id, code, lang, label)
VALUES (5, 'WIDOWED', 'ar', N'أرمل');
INSERT INTO shc_portal.shc_marital_status_lk (id, code, lang, label)
VALUES (6, 'WIDOWED', 'en', 'Widowed');
INSERT INTO shc_portal.shc_marital_status_lk (id, code, lang, label)
VALUES (7, 'DIVORCED', 'ar', N'مطلق');
INSERT INTO shc_portal.shc_marital_status_lk (id, code, lang, label)
VALUES (8, 'DIVORCED', 'en', 'Divorced');
SET
IDENTITY_INSERT shc_portal.shc_marital_status_lk OFF;
GO

SET IDENTITY_INSERT shc_portal.shc_language_lk ON;
INSERT INTO shc_portal.shc_language_lk (id, code, lang, label)
VALUES (1, 'AR', 'ar', N'العربية');
INSERT INTO shc_portal.shc_language_lk (id, code, lang, label)
VALUES (2, 'AR', 'en', 'Arabic');
INSERT INTO shc_portal.shc_language_lk (id, code, lang, label)
VALUES (3, 'AR', 'fr', 'Arabe');
INSERT INTO shc_portal.shc_language_lk (id, code, lang, label)
VALUES (4, 'AR', 'ur', N'عربی');
INSERT INTO shc_portal.shc_language_lk (id, code, lang, label)
VALUES (5, 'AR', 'fa', N'عربی');
INSERT INTO shc_portal.shc_language_lk (id, code, lang, label)
VALUES (6, 'AR', 'tr', N'Arapça');
INSERT INTO shc_portal.shc_language_lk (id, code, lang, label)
VALUES (7, 'EN', 'ar', N'الإنجليزية');
INSERT INTO shc_portal.shc_language_lk (id, code, lang, label)
VALUES (8, 'EN', 'en', 'English');
INSERT INTO shc_portal.shc_language_lk (id, code, lang, label)
VALUES (9, 'EN', 'fr', 'Anglais');
INSERT INTO shc_portal.shc_language_lk (id, code, lang, label)
VALUES (10, 'EN', 'ur', N'انگریزی');
INSERT INTO shc_portal.shc_language_lk (id, code, lang, label)
VALUES (11, 'EN', 'fa', N'انگلیسی');
INSERT INTO shc_portal.shc_language_lk (id, code, lang, label)
VALUES (12, 'EN', 'tr', N'İngilizce');
INSERT INTO shc_portal.shc_language_lk (id, code, lang, label)
VALUES (13, 'FR', 'ar', N'الفرنسية');
INSERT INTO shc_portal.shc_language_lk (id, code, lang, label)
VALUES (14, 'FR', 'en', 'French');
INSERT INTO shc_portal.shc_language_lk (id, code, lang, label)
VALUES (15, 'FR', 'fr', N'Français');
INSERT INTO shc_portal.shc_language_lk (id, code, lang, label)
VALUES (16, 'FR', 'ur', N'فرانسیسی');
INSERT INTO shc_portal.shc_language_lk (id, code, lang, label)
VALUES (17, 'FR', 'fa', N'فرانسوی');
INSERT INTO shc_portal.shc_language_lk (id, code, lang, label)
VALUES (18, 'FR', 'tr', N'Fransızca');
INSERT INTO shc_portal.shc_language_lk (id, code, lang, label)
VALUES (19, 'UR', 'ar', N'الأردية');
INSERT INTO shc_portal.shc_language_lk (id, code, lang, label)
VALUES (20, 'UR', 'en', 'Urdu');
INSERT INTO shc_portal.shc_language_lk (id, code, lang, label)
VALUES (21, 'UR', 'fr', 'Ourdou');
INSERT INTO shc_portal.shc_language_lk (id, code, lang, label)
VALUES (22, 'UR', 'ur', N'اردو');
INSERT INTO shc_portal.shc_language_lk (id, code, lang, label)
VALUES (23, 'UR', 'fa', N'اردو');
INSERT INTO shc_portal.shc_language_lk (id, code, lang, label)
VALUES (24, 'UR', 'tr', 'Urduca');
INSERT INTO shc_portal.shc_language_lk (id, code, lang, label)
VALUES (25, 'FA', 'ar', N'الفارسية');
INSERT INTO shc_portal.shc_language_lk (id, code, lang, label)
VALUES (26, 'FA', 'en', 'Persian');
INSERT INTO shc_portal.shc_language_lk (id, code, lang, label)
VALUES (27, 'FA', 'fr', 'Persan');
INSERT INTO shc_portal.shc_language_lk (id, code, lang, label)
VALUES (28, 'FA', 'ur', N'فارسی');
INSERT INTO shc_portal.shc_language_lk (id, code, lang, label)
VALUES (29, 'FA', 'fa', N'فارسی');
INSERT INTO shc_portal.shc_language_lk (id, code, lang, label)
VALUES (30, 'FA', 'tr', N'Farsça');
INSERT INTO shc_portal.shc_language_lk (id, code, lang, label)
VALUES (31, 'TR', 'ar', N'التركية');
INSERT INTO shc_portal.shc_language_lk (id, code, lang, label)
VALUES (32, 'TR', 'en', 'Turkish');
INSERT INTO shc_portal.shc_language_lk (id, code, lang, label)
VALUES (33, 'TR', 'fr', 'Turc');
INSERT INTO shc_portal.shc_language_lk (id, code, lang, label)
VALUES (34, 'TR', 'ur', N'ترکی');
INSERT INTO shc_portal.shc_language_lk (id, code, lang, label)
VALUES (35, 'TR', 'fa', N'ترکی');
INSERT INTO shc_portal.shc_language_lk (id, code, lang, label)
VALUES (36, 'TR', 'tr', N'Türkçe');
SET
IDENTITY_INSERT shc_portal.shc_language_lk OFF;
GO

SET IDENTITY_INSERT shc_portal.shc_print_request_status_lk ON;
INSERT INTO shc_portal.shc_print_request_status_lk (id, code, lang, label)
VALUES (1, 'NEW', 'ar', N'جديد');
INSERT INTO shc_portal.shc_print_request_status_lk (id, code, lang, label)
VALUES (2, 'NEW', 'en', 'New');
INSERT INTO shc_portal.shc_print_request_status_lk (id, code, lang, label)
VALUES (3, 'CONFIRMED', 'ar', N'مؤكد');
INSERT INTO shc_portal.shc_print_request_status_lk (id, code, lang, label)
VALUES (4, 'CONFIRMED', 'en', 'Confirmed');
INSERT INTO shc_portal.shc_print_request_status_lk (id, code, lang, label)
VALUES (5, 'UNDER_PROCESSING', 'ar', N'تحت المعالجة');
INSERT INTO shc_portal.shc_print_request_status_lk (id, code, lang, label)
VALUES (6, 'UNDER_PROCESSING', 'en', 'Under processing');
INSERT INTO shc_portal.shc_print_request_status_lk (id, code, lang, label)
VALUES (7, 'PROCESSED', 'ar', N'تم التجميع');
INSERT INTO shc_portal.shc_print_request_status_lk (id, code, lang, label)
VALUES (8, 'PROCESSED', 'en', 'Batched');
INSERT INTO shc_portal.shc_print_request_status_lk (id, code, lang, label)
VALUES (9, 'SENT_TO_PRINTING', 'ar', N'تم الإرسال للطباعة');
INSERT INTO shc_portal.shc_print_request_status_lk (id, code, lang, label)
VALUES (10, 'SENT_TO_PRINTING', 'en', 'Sent to printing');
INSERT INTO shc_portal.shc_print_request_status_lk (id, code, lang, label)
VALUES (11, 'CANCELLED', 'ar', N'ملغى');
INSERT INTO shc_portal.shc_print_request_status_lk (id, code, lang, label)
VALUES (12, 'CANCELLED', 'en', 'Cancelled');
SET
IDENTITY_INSERT shc_portal.shc_print_request_status_lk OFF;
GO

SET IDENTITY_INSERT shc_portal.shc_print_batch_type_lk ON;
INSERT INTO shc_portal.shc_print_batch_type_lk (id, label_ar, label_en, code)
VALUES (1, N'رئيس المجموعة', 'Group Leader', 'GROUP_LEADER');
INSERT INTO shc_portal.shc_print_batch_type_lk (id, label_ar, label_en, code)
VALUES (2, N'الحملة', 'Hamlah', 'HAMLAH');
INSERT INTO shc_portal.shc_print_batch_type_lk (id, label_ar, label_en, code)
VALUES (3, N'رقم الفوج', 'Fawj Number', 'FAWJ_NUMBER');
INSERT INTO shc_portal.shc_print_batch_type_lk (id, label_ar, label_en, code)
VALUES (4, N'الجنسية', 'Nationality', 'NATIONALITY');
INSERT INTO shc_portal.shc_print_batch_type_lk (id, label_ar, label_en, code)
VALUES (5, N'مؤسسة الطوافة', 'Motawif', 'MOTAWIF');
SET
IDENTITY_INSERT shc_portal.shc_print_batch_type_lk OFF;
GO

-- add applicant data override config
INSERT INTO shc_portal.shc_config (conf_key, conf_value)
VALUES ('data.request.applicant.override', 'false');
INSERT INTO shc_portal.shc_config (conf_key, conf_value)
VALUES ('scheduler.generate.digital.ids.cron', '0 0/15 * * * *');
INSERT INTO shc_portal.shc_config (conf_key, conf_value)
VALUES ('scheduler.generate.card.applicant.ritual.cron', '0 0/15 * * * *');
GO
