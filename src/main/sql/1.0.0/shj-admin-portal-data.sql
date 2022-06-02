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

USE shc_portal
GO
SET IDENTITY_INSERT shc_portal.shc_country_lk ON;
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (1, 101, N'إماراتي', 'ar', '971', 'AE');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (2, 101, 'Emirati', 'en', '971', 'AE');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (3, 102, N'أردني', 'ar', '962', 'JO');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (4, 102, 'Jordanian', 'en', '962', 'JO');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (5, 103, N'بحريني', 'ar', '973', 'BH');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (6, 103, 'Bahraini', 'en', '973', 'BH');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (7, 104, N'سوري', 'ar', '963', 'SY');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (8, 104, 'Syrian', 'en', '963', 'SY');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (9, 105, N'عراقي', 'ar', '964', 'IQ');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (10, 105, 'Iraqi', 'en', '964', 'IQ');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (11, 106, N'عماني', 'ar', '968', 'OM');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (12, 106, 'Omani', 'en', '968', 'OM');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (13, 107, N'فلسطيني', 'ar', '970', 'PS');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (14, 107, 'Palestinian', 'en', '970', 'PS');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (15, 108, N'قطري', 'ar', '974', 'QA');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (16, 108, 'Qatari', 'en', '974', 'QA');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (17, 109, N'كويتي', 'ar', '965', 'KW');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (18, 109, 'Kuwaiti', 'en', '965', 'KW');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (19, 110, N'لبناني', 'ar', '961', 'LB');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (20, 110, 'Lebanese', 'en', '961', 'LB');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (21, 111, N'يمني', 'ar', '967', 'YE');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (22, 111, 'Yemen', 'en', '967', 'YE');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (23, 113, N'سعودي ', 'ar', '966', 'SA');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (24, 113, 'Saudi', 'en', '966', 'SA');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (25, 201, N'تونسي', 'ar', '216', 'TN');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (26, 201, 'Tunisian', 'en', '216', 'TN');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (27, 202, N'جزائري', 'ar', '213', 'DZ');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (28, 202, 'Algerian', 'en', '213', 'DZ');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (29, 203, N'جيبوتي', 'ar', '253', 'DJ');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (30, 203, 'Djiboutian', 'en', '253', 'DJ');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (31, 204, N'سوداني', 'ar', '249', 'SD');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (32, 204, 'Sudanese', 'en', '249', 'SD');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (33, 205, N'صومالي', 'ar', '252', 'SO');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (34, 205, 'Somalian ', 'en', '252', 'SO');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (35, 206, N'ليبي', 'ar', '218', 'LY');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (36, 206, 'Libyan', 'en', '218', 'LY');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (37, 207, N'مصري', 'ar', '20', 'EG');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (38, 207, 'Egyptian', 'en', '20', 'EG');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (39, 208, N'مغربي', 'ar', '212', 'MA');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (40, 208, 'Moroccan', 'en', '212', 'MA');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (41, 209, N'موريتاني', 'ar', '222', 'MR');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (42, 209, 'Mauritanian', 'en', '222', 'MR');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (43, 301, N'أفغانستاني', 'ar', '93', 'AF');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (44, 301, 'Afghan', 'en', '93', 'AF');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (45, 302, N'اندونيسي', 'ar', '62', 'ID');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (46, 302, 'Indonesian', 'en', '62', 'ID');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (47, 303, N'إيراني', 'ar', '98', 'IR');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (48, 303, 'Iranian', 'en', '98', 'IR');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (49, 304, N'باكستاني', 'ar', '92', 'PK');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (50, 304, 'Pakistani', 'en', '92', 'PK');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (51, 305, N'بنغلاديشي', 'ar', '880', 'BD');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (52, 305, 'Bangladeshi', 'en', '880', 'BD');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (53, 306, N'بروني', 'ar', '673', 'BN');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (54, 306, 'Brunein', 'en', '673', 'BN');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (55, 307, N'ميانماري', 'ar', '95', 'MM');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (56, 307, 'Myanmarian', 'en', '95', 'MM');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (57, 308, N'تايلندي', 'ar', '66', 'TH');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (58, 308, 'Thai', 'en', '66', 'TH');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (59, 309, N'تركي', 'ar', '90', 'TR');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (60, 309, 'Turkish', 'en', '90', 'TR');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (61, 310, N'جزر مالديف', 'ar', '960', 'MV');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (62, 310, 'Maldivian', 'en', '960', 'MV');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (63, 311, N'روسي إتحادي', 'ar', '7', 'RU');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (64, 311, 'Russian', 'en', '7', 'RU');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (65, 312, N'سنغافوري', 'ar', '65', 'SG');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (66, 312, 'Singaporean', 'en', '65', 'SG');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (67, 313, N'سريلانكي', 'ar', '94', 'LK');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (68, 313, 'Srilankan', 'en', '94', 'LK');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (69, 315, N'فلبيني', 'ar', '63', 'PH');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (70, 315, 'Philippino', 'en', '63', 'PH');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (71, 316, N'فيتنامي', 'ar', '84', 'VN');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (72, 316, 'Vietnamese', 'en', '84', 'VN');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (73, 317, N'كمبودي ', 'ar', '855', 'KH');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (74, 317, 'Cambodian', 'en', '855', 'KH');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (75, 318, N'كوري جنوبي', 'ar', '82', 'KR');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (76, 318, 'Korean', 'en', '82', 'KR');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (77, 319, N'ماليزي', 'ar', '60', 'MY');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (78, 319, 'Malaysian', 'en', '60', 'MY');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (79, 320, N'نيبالي ', 'ar', '977', 'NP');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (80, 320, 'Nepalese', 'en', '977', 'NP');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (81, 321, N'هندي', 'ar', '91', 'IN');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (82, 321, 'Indian', 'en', '91', 'IN');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (83, 322, N'هونج كونج', 'ar', '852', 'HK');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (84, 322, 'Hong Konger', 'en', '852', 'HK');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (85, 323, N'ياباني', 'ar', '81', 'JP');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (86, 323, 'Japanese', 'en', '81', 'JP');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (87, 324, N'بهوتاني', 'ar', '975', 'BT');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (88, 324, 'Bhutanese', 'en', '975', 'BT');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (89, 325, N'صيني', 'ar', '86', 'CN');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (90, 325, 'Chinese', 'en', '86', 'CN');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (91, 326, N'قبرصي', 'ar', '357', 'CY');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (92, 326, 'Cypriote', 'en', '357', 'CY');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (93, 328, N'كوري شمالي', 'ar', '850', 'KP');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (94, 328, 'Korean N.', 'en', '850', 'KP');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (95, 329, N'لاوسي', 'ar', '856', 'LA');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (96, 329, 'Laosian', 'en', '856', 'LA');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (97, 330, N'منغولي', 'ar', '976', 'MN');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (98, 330, 'Mongolian', 'en', '976', 'MN');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (99, 331, N'ماكاوي', 'ar', '853', 'MO');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (100, 331, 'Macauese', 'en', '853', 'MO');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (101, 332, N'تركستاني', 'ar', '7-72533', 'KZ-YUZ');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (102, 332, 'Turkistani', 'en', '7-72533', 'KZ-YUZ');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (103, 336, N'كازاخستاني', 'ar', '7', 'KZ');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (104, 336, 'Kazakh', 'en', '7', 'KZ');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (105, 337, N'أوزبكستاني', 'ar', '998', 'UZ');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (106, 337, 'Uzbek', 'en', '998', 'UZ');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (107, 338, N'تركمانستاني', 'ar', '993', 'TM');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (108, 338, 'Turkmenenian', 'en', '993', 'TM');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (109, 339, N'طاجكستاني', 'ar', '992', 'TJ');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (110, 339, 'Tajik', 'en', '992', 'TJ');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (111, 340, N'قيرغيزي', 'ar', '996', 'KG');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (112, 340, 'Kyrgyzian', 'en', '996', 'KG');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (113, 343, N'أذربيجاني', 'ar', '994', 'AZ');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (114, 343, 'Azerbaijani', 'en', '994', 'AZ');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (115, 344, N'شيشاني', 'ar', '7-871', 'RU-CE');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (116, 344, 'Chechenian', 'en', '7-871', 'RU-CE');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (117, 345, N'داغستاني', 'ar', '7-872', 'RU-DA');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (118, 345, 'Daghestani', 'en', '7-872', 'RU-DA');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (119, 347, N'تتارستاني', 'ar', '7-843', 'RU-TA');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (120, 347, 'Tataristani', 'en', '7-843', 'RU-TA');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (121, 349, N'تيمور الشرقية', 'ar', '670', 'TL');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (122, 349, 'East Timor', 'en', '670', 'TL');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (123, 401, N'اثيوبي', 'ar', '251', 'ET');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (124, 401, 'Ethiopian', 'en', '251', 'ET');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (125, 402, N'اوغندي', 'ar', '256', 'UG');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (126, 402, 'Ugandan', 'en', '256', 'UG');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (127, 403, N'بوتسواني', 'ar', '267', 'BW');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (128, 403, 'Botswanian', 'en', '267', 'BW');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (129, 404, N'بورندي', 'ar', '257', 'BI');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (130, 404, 'Burundian', 'en', '257', 'BI');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (131, 405, N'تشادي', 'ar', '235', 'TD');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (132, 405, 'Chadian', 'en', '235', 'TD');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (133, 406, N'تنزاني', 'ar', '255', 'TZ');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (134, 406, 'Tanzanian', 'en', '255', 'TZ');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (135, 407, N'توجوي', 'ar', '228', 'TG');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (136, 407, 'Togolese', 'en', '228', 'TG');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (137, 408, N'جابوني', 'ar', '241', 'GA');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (138, 408, 'Gabonese', 'en', '241', 'GA');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (139, 409, N'غامبي', 'ar', '220', 'GM');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (140, 409, 'Gambian', 'en', '220', 'GM');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (141, 410, N'جزر القمر', 'ar', '269', 'KM');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (142, 410, 'Comorosian', 'en', '269', 'KM');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (143, 411, N'جنوب أفريقي', 'ar', '27', 'ZA');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (144, 411, 'South African', 'en', '27', 'ZA');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (145, 412, N'ناميبي', 'ar', '264', 'NA');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (146, 412, 'Namibian', 'en', '264', 'NA');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (147, 413, N'بنيني', 'ar', '229', 'BJ');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (148, 413, 'Beninese', 'en', '229', 'BJ');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (149, 414, N'رواندي', 'ar', '250', 'RW');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (150, 414, 'Rwandan', 'en', '250', 'RW');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (151, 415, N'زمبابوي', 'ar', '263', 'ZW');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (152, 415, 'Zimbabwean', 'en', '263', 'ZW');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (153, 416, N'زائيري', 'ar', '243', 'CD');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (154, 416, 'Zairean', 'en', '243', 'CD');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (155, 417, N'زامبي', 'ar', '260', 'ZM');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (156, 417, 'Zambian', 'en', '260', 'ZM');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (157, 418, N'ساحل العاج', 'ar', '225', 'CI');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (158, 418, 'Ivorian', 'en', '225', 'CI');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (159, 419, N'سنغالي', 'ar', '221', 'SN');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (160, 419, 'Senegalese', 'en', '221', 'SN');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (161, 420, N'سيراليوني', 'ar', '232', 'SL');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (162, 420, 'Sierra Leonean', 'en', '232', 'SL');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (163, 421, N'غاني', 'ar', '233', 'GH');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (164, 421, 'Ghanian', 'en', '233', 'GH');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (165, 422, N'غيني', 'ar', '224', 'GN');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (166, 422, 'Guinean', 'en', '224', 'GN');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (167, 423, N'غيني بيساوي', 'ar', '245', 'GW');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (168, 423, 'Guinean Bissau', 'en', '245', 'GW');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (169, 424, N'بوركينافاسوي', 'ar', '226', 'BF');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (170, 424, 'Burkinian', 'en', '226', 'BF');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (171, 425, N'كاميروني', 'ar', '237', 'CM');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (172, 425, 'Cameroonian', 'en', '237', 'CM');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (173, 426, N'كونغوي', 'ar', '242', 'CG');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (174, 426, 'Congolese', 'en', '242', 'CG');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (175, 427, N'كيني', 'ar', '254', 'KE');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (176, 427, 'Kenyan', 'en', '254', 'KE');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (177, 428, N'ليسوتوي', 'ar', '266', 'LS');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (178, 428, 'Lesothian', 'en', '266', 'LS');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (179, 429, N'ليبيري', 'ar', '231', 'LR');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (180, 429, 'Liberian', 'en', '231', 'LR');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (181, 430, N'مالي', 'ar', '223', 'ML');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (182, 430, 'Malian', 'en', '223', 'ML');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (183, 432, N'ملاوي', 'ar', '265', 'MW');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (184, 432, 'Malawian', 'en', '265', 'MW');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (185, 433, N'موريشيوسي', 'ar', '230', 'MU');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (186, 433, 'Mauritian', 'en', '230', 'MU');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (187, 434, N'موزمبيقي', 'ar', '258', 'MZ');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (188, 434, 'Mozambican', 'en', '258', 'MZ');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (189, 435, N'نيجيري', 'ar', '234', 'NG');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (190, 435, 'Nigeria', 'en', '234', 'NG');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (191, 436, N'نيجري', 'ar', '227', 'NE');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (192, 436, 'Nigerois', 'en', '227', 'NE');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (193, 437, N'افريقيا الوسطى', 'ar', '236', 'CF');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (194, 437, 'Central African', 'en', '236', 'CF');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (195, 438, N'أنغولي', 'ar', '244', 'AO');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (196, 438, 'Angolan', 'en', '244', 'AO');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (197, 439, N'الراس الاخضر', 'ar', '238', 'CV');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (198, 439, 'Cape Verdean', 'en', '238', 'CV');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (199, 440, N'غيني استوائي', 'ar', '240', 'GQ');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (200, 440, 'Equatorial Guinea', 'en', '240', 'GQ');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (201, 441, N'ملاجاسي/مدغشقر', 'ar', '261', 'MG');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (202, 441, 'Malagasyan', 'en', '261', 'MG');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (203, 442, N'ساوتوميبرنسبى', 'ar', '239', 'ST');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (204, 442, 'São Tomé and Príncipe', 'en', '239', 'ST');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (205, 443, N'جزر سيشل', 'ar', '248', 'SC');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (206, 443, 'Seychelian', 'en', '248', 'SC');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (207, 444, N'سوزيلاندي', 'ar', '268', 'SZ');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (208, 444, 'Swazilander', 'en', '268', 'SZ');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (209, 449, N'إرتيري', 'ar', '291', 'ER');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (210, 449, 'Eritrean', 'en', '291', 'ER');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (211, 453, N'جمهورية جنوب السودان', 'ar', '211', 'SS');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (212, 453, 'جمهورية جنوب السودان', 'en', '211', 'SS');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (213, 501, N'أسباني', 'ar', '34', 'ES');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (214, 501, 'Spanish', 'en', '34', 'ES');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (215, 502, N'الباني', 'ar', '355', 'AL');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (216, 502, 'Albanian', 'en', '355', 'AL');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (217, 503, N'ألماني', 'ar', '49', 'DE');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (218, 503, 'German', 'en', '49', 'DE');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (219, 504, N'ايرلندي', 'ar', '353', 'IE');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (220, 504, 'Irish', 'en', '353', 'IE');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (221, 505, N'إيطالي', 'ar', '39', 'IT');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (222, 505, 'Italian', 'en', '39', 'IT');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (223, 506, N'بريطاني', 'ar', '44', 'GB');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (224, 506, 'British', 'en', '44', 'GB');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (225, 507, N'برتغالي', 'ar', '351', 'PT');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (226, 507, 'Portuguese', 'en', '351', 'PT');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (227, 508, N'بلغاري', 'ar', '359', 'BG');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (228, 508, 'Bulgarian', 'en', '359', 'BG');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (229, 509, N'بلجيكي', 'ar', '32', 'BE');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (230, 509, 'Belgian', 'en', '32', 'BE');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (231, 510, N'بولندي', 'ar', '48', 'PL');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (232, 510, 'Polish', 'en', '48', 'PL');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (233, 512, N'دانماركي', 'ar', '45', 'DK');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (234, 512, 'Danish', 'en', '45', 'DK');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (235, 513, N'روماني', 'ar', '40', 'RO');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (236, 513, 'Romanian', 'en', '40', 'RO');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (237, 514, N'سويدي', 'ar', '46', 'SE');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (238, 514, 'Swedish', 'en', '46', 'SE');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (239, 515, N'سويسري', 'ar', '41', 'CH');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (240, 515, 'Swiss', 'en', '41', 'CH');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (241, 516, N'فرنسي', 'ar', '33', 'FR');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (242, 516, 'Franch', 'en', '33', 'FR');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (243, 517, N'فنلندي', 'ar', '358', 'AX');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (244, 517, 'Finlander', 'en', '358', 'AX');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (245, 518, N'صربيا', 'ar', '381', 'RS');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (246, 518, 'Serbia', 'en', '381', 'RS');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (247, 519, N'هولندي', 'ar', '31', 'NL');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (248, 519, 'Dutch', 'en', '31', 'NL');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (249, 521, N'يوناني', 'ar', '30', 'GR');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (250, 521, 'GreeK', 'en', '30', 'GR');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (251, 522, N'اندوري', 'ar', '376', 'AD');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (252, 522, 'Andorian', 'en', '376', 'AD');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (253, 523, N'نمساوي', 'ar', '43', 'AT');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (254, 523, 'Austrian', 'en', '43', 'AT');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (255, 524, N'الجبل الأ سود', 'ar', '382', 'ME');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (256, 524, 'Montenegro', 'en', '382', 'ME');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (257, 525, N'هنغاري', 'ar', '36', 'HU');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (258, 525, 'Hungarian', 'en', '36', 'HU');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (259, 526, N'ايسلندي', 'ar', '354', 'IS');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (260, 526, 'Icelander', 'en', '354', 'IS');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (261, 527, N'ليختنشتيني', 'ar', '423', 'LI');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (262, 527, 'Liechtenstein', 'en', '423', 'LI');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (263, 528, N'لوكسمبورغي', 'ar', '352', 'LU');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (264, 528, 'Luxembourgian', 'en', '352', 'LU');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (265, 529, N'مالطي', 'ar', '356', 'MT');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (266, 529, 'Maltese', 'en', '356', 'MT');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (267, 530, N'موناكوي', 'ar', '377', 'MC');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (268, 530, 'Monacan', 'en', '377', 'MC');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (269, 531, N'نرويجي', 'ar', '47', 'SJ');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (270, 531, 'Norwegian', 'en', '47', 'SJ');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (271, 532, N'سان مورينو', 'ar', '378', 'SM');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (272, 532, 'San Marino', 'en', '378', 'SM');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (273, 533, N'مدينة الفاتيكان', 'ar', '39-06', 'VA');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (274, 533, 'Vatican', 'en', '39-06', 'VA');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (275, 534, N'جبل طارق', 'ar', '350', 'GI');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (276, 534, 'Gibraltarian', 'en', '350', 'GI');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (277, 536, N'اوكراني', 'ar', '380', 'UA');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (278, 536, 'Ucranian', 'en', '380', 'UA');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (279, 537, N'روسيا البيضاء', 'ar', '375', 'BY');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (280, 537, 'Belarussian', 'en', '375', 'BY');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (281, 539, N'ارميني', 'ar', '374', 'AM');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (282, 539, 'Armenian', 'en', '374', 'AM');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (283, 540, N'مولدافي', 'ar', '373', 'MD');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (284, 540, 'Moldovian', 'en', '373', 'MD');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (285, 541, N'جورجي', 'ar', '995', 'GE');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (286, 541, 'Georgian', 'en', '995', 'GE');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (287, 542, N'ليتواني', 'ar', '370', 'LT');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (288, 542, 'Lithuanian', 'en', '370', 'LT');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (289, 543, N'أستوني', 'ar', '372', 'EE');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (290, 543, 'Estonian', 'en', '372', 'EE');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (291, 544, N'لاتفي', 'ar', '371', 'LV');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (292, 544, 'Latvian', 'en', '371', 'LV');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (293, 545, N'بوسني', 'ar', '387', 'BA');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (294, 545, 'Bosnian', 'en', '387', 'BA');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (295, 546, N'كرواتي', 'ar', '385', 'HR');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (296, 546, 'Croatian', 'en', '385', 'HR');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (297, 547, N'سلوفيني', 'ar', '386', 'SI');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (298, 547, 'Slovenian', 'en', '386', 'SI');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (299, 549, N'مقدوني', 'ar', '389', 'MK');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (300, 549, 'Macedonian', 'en', '389', 'MK');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (301, 552, N'تشيكي', 'ar', '420', 'CZ');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (302, 552, 'Czechish', 'en', '420', 'CZ');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (303, 553, N'سلوفاكي', 'ar', '421', 'SK');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (304, 553, 'Slovakian', 'en', '421', 'SK');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (305, 554, N'جزر فيرو', 'ar', '298', 'FO');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (306, 554, 'Faroe Islands', 'en', '298', 'FO');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (307, 601, N'أمريكي', 'ar', '1', 'US');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (308, 601, 'American', 'en', '1', 'US');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (309, 602, N'أرجنتيني', 'ar', '54', 'AR');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (310, 602, 'Argentinian', 'en', '54', 'AR');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (311, 603, N'بربادوسي', 'ar', '1-246', 'BB');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (312, 603, 'Barbadian', 'en', '1-246', 'BB');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (313, 604, N'برازيلي', 'ar', '55', 'BR');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (314, 604, 'Brazilian', 'en', '55', 'BR');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (315, 605, N'بنمي', 'ar', '507', 'PA');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (316, 605, 'Panamanian', 'en', '507', 'PA');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (317, 606, N'ترينداد وتوباجو', 'ar', '1-868', 'TT');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (318, 606, 'Trinindad', 'en', '1-868', 'TT');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (319, 607, N'جامايكي', 'ar', '1-876', 'JM');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (320, 607, 'Jamaican', 'en', '1-876', 'JM');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (321, 608, N'جواني', 'ar', '592', 'GY');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (322, 608, 'Guyanese', 'en', '592', 'GY');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (323, 609, N'فنزويلي', 'ar', '58', 'VE');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (324, 609, 'Venezuelan', 'en', '58', 'VE');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (325, 610, N'كندي', 'ar', '1', 'CA');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (326, 610, 'Canadian', 'en', '1', 'CA');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (327, 611, N'كولمبي', 'ar', '57', 'CO');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (328, 611, 'Colombian', 'en', '57', 'CO');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (329, 612, N'جزر البهاما', 'ar', '1-242', 'BS');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (330, 612, 'Bahamian', 'en', '1-242', 'BS');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (331, 613, N'كوستاريكي', 'ar', '506', 'CR');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (332, 613, 'Costa Rican', 'en', '506', 'CR');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (333, 614, N'كوبي', 'ar', '53', 'CU');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (334, 614, 'Cuban', 'en', '53', 'CU');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (335, 615, N'دومينيكي', 'ar', '1-767', 'DM');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (336, 615, 'Dominican', 'en', '1-767', 'DM');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (337, 616, N'جمهورية دمينكان', 'ar', '1-809,1-829,1-849', 'DO');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (338, 616, 'Republic Dominica', 'en', '1-809,1-829,1-849', 'DO');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (339, 617, N'سلفادوري', 'ar', '503', 'SV');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (340, 617, 'Salvadorian', 'en', '503', 'SV');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (341, 618, N'جرانادي', 'ar', '1-473', 'GD');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (342, 618, 'Grenadian', 'en', '1-473', 'GD');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (343, 619, N'جواتيمالي', 'ar', '502', 'GT');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (344, 619, 'Guatemalan', 'en', '502', 'GT');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (345, 620, N'هايتي', 'ar', '509', 'HT');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (346, 620, 'Haitian', 'en', '509', 'HT');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (347, 621, N'هوندوراس', 'ar', '504', 'HN');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (348, 621, 'Honduranian', 'en', '504', 'HN');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (349, 622, N'مكسيكي', 'ar', '52', 'MX');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (350, 622, 'Mexican', 'en', '52', 'MX');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (351, 623, N'نيكاراجوي', 'ar', '505', 'NI');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (352, 623, 'Nicaraguan', 'en', '505', 'NI');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (353, 624, N'سانت لوسيا', 'ar', '1-758', 'LC');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (354, 624, 'Saint Lucia', 'en', '1-758', 'LC');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (355, 625, N'سان فينسنت', 'ar', '1-784', 'VC');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (356, 625, 'Saint Vincent', 'en', '1-784', 'VC');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (357, 626, N'بوليفي', 'ar', '591', 'BO');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (358, 626, 'Bolivian', 'en', '591', 'BO');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (359, 627, N'شيلي', 'ar', '56', 'CL');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (360, 627, 'Chilean', 'en', '56', 'CL');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (361, 628, N'اكوادوري', 'ar', '593', 'EC');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (362, 628, 'Ecuadorean', 'en', '593', 'EC');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (363, 629, N'باراجواي', 'ar', '595', 'PY');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (364, 629, 'Paraguayan', 'en', '595', 'PY');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (365, 630, N'بيروي', 'ar', '51', 'PE');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (366, 630, 'Peruvian', 'en', '51', 'PE');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (367, 701, N'استرالي', 'ar', '61', 'CC');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (368, 701, 'Australian', 'en', '61', 'CC');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (369, 702, N'نيوزيلندي', 'ar', '64', 'NZ');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (370, 702, 'N.zelander', 'en', '64', 'NZ');


INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (371, 112, N'يمني جنوبي ', 'ar', '967', 'YE');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (372, 112, N'يمني جنوبي ', 'en', '967', 'YE');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (373, 114, N'يمني جنوبيالسلاطين', 'ar', '967', 'YE');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (374, 114, N'يمني جنوبيالسلاطين', 'en', '967', 'YE');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (375, 115, N'بني حارث', 'ar', '967', 'YE');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (376, 115, N'بني حارث', 'en', '967', 'YE');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (377, 118, N'من سكان البحرين', 'ar', '967', 'BH');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (378, 118, 'Bahrain Res.', 'en', '967', 'BH');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (379, 314, N'تايواني', 'ar', '886', 'TWN');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (380, 314, 'Taiwanese', 'en', '886', 'TWN');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (381, 333, N'بلوشستاني', 'ar', '81', 'PK');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (382, 333, 'Blushistani', 'en', '81', 'PK');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (383, 334, N'بخارستاني', 'ar', '', '');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (384, 334, 'Bukharan', 'en', '', '');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (385, 335, N'القبائل النازحة', 'ar', '', '');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (386, 335, N'القبائل النازحة', 'en', '', '');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (387, 341, N'سقطري', 'ar', '', '');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (388, 341, 'Sukatra', 'en', '', '');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (389, 342, N'مهري', 'ar', '', '');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (390, 342, 'Mahara', 'en', '', '');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (391, 346, N'انقوشي ', 'ar', '', '');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (393, 346, 'Ingush', 'en', '', '');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (394, 446, N'رينيوني', 'ar', '262', 'REU');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (395, 446, N'Reunese', 'en', '262', 'REU');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (396, 447, N'ترانسكي', 'ar', '', '');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (397, 447, N'ترانسكي', 'en', '', '');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (398, 448, N'فيندي', 'ar', '', '');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (399, 448, N'فيندي', 'en', '', '');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (400, 511, N'تشيكوسلوفاكي', 'ar', '', 'CSK');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (401, 511, 'Czechlovakian', 'en', '42', 'CSK');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (402, 520, N'يوغسلافي', 'ar', '42', 'SER');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (403, 520, 'Yugoslavian', 'en', '38', 'SER');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (404, 631, N'سورينامي', 'ar', '597', 'SUR');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (405, 631, 'Suriname', 'en', '597', 'SUR');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (406, 632, N'اوراجواي', 'ar', '598', 'URY');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (407, 632, 'Uruguayan', 'en', '598', 'URY');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (408, 633, N'س بييري وميكويلن', 'ar', '', '');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (409, 633, N'س بييري وميكويلن', 'en', '', '');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (410, 634, N'جرينلاند', 'ar', '299', 'GRL');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (411, 634, 'GreenLander', 'en', '299', 'GRL');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (412, 635, N'بيليز', 'ar', '501', 'BLZ');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (413, 635, 'Belizian', 'en', '501', 'BLZ');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (414, 636, N'بيرمودي', 'ar', '', '');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (415, 636, N'بيرمودي', 'en', '', '');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (416, 637, N'ج الترك والقوقاز', 'ar', '', '');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (417, 637, N'ج الترك والقوقاز ', 'en', '', '');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (418, 638, N'سان كريستوفرنيفز', 'ar', '', '');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (419, 638, N'سان كريستوفرنيفز', 'en', '', '');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (420, 641, N'ج فيرجن البريطانية', 'ar', '1', 'VGB');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (421, 641, 'Virgin Isaland British', 'en', '1', 'VGB');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (422, 642, N'جزر كايمون', 'ar', '1', 'CYM');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (423, 642, 'Caymanian', 'en', '1', 'CYM');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (424, 643, N'مونت سيرات', 'ar', '1', 'MSR');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (425, 643, N'مونت سيرات', 'en', '1', 'MSR');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (426, 644, N'جيودي لوب', 'ar', '590', 'GLP');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (428, 644, 'Guadeloupean', 'en', '590', 'GLP');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (429, 645, N'مارتينيكوي', 'ar', '596', 'MTQ');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (430, 645, N'مارتينيكوي', 'en', '596', 'MTQ');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (431, 646, N'عروبي', 'ar', '', '');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (432, 646, N'عروبي', 'en', '', '');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (433, 647, N'بونيري', 'ar', '', '');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (434, 647, N'بونيري', 'en', '', '');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (435, 648, N'كيوراكو', 'ar', '', '');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (436, 648, N'كيوراكو', 'en', '', '');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (437, 650, N'سابا', 'ar', '60-8', 'SBH');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (438, 650, N'سابا', 'en', '60-8', 'SBH');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (439, 652, N'بورتوريكو', 'ar', '1', 'PRT');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (440, 652, 'Puerto Rican', 'en', '1', 'PRT');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (441, 653, N'ج فيرجن الامريكية', 'ar', '1', 'VIR');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (442, 653, 'Virgin Isaland US', 'en', '1', 'VIR');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (443, 654, N'جزر فاكلاند', 'ar', '500', 'FLK');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (444, 654, 'Falklander', 'en', '500', 'FLK');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (445, 655, N'جيانا الفرنسية', 'ar', '', '');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (446, 655, 'Guinean', 'en', '', '');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (447, 656, N'الامم المتحدة', 'ar', '', '');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (448, 656, 'United Nations', 'en', '', '');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (449, 703, N'بابوا نيوغينا', 'ar', '', '');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (450, 703, N'بابوا نيوغينا', 'en', '', '');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (451, 801, N'جزر فيجي', 'ar', '', '');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (452, 801, 'Fijian', 'en', '', '');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (453, 803, N'نوروي', 'ar', '', 'NRU');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (454, 803, 'Nauru', 'en', '', 'NRU');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (455, 804, N'جزر سليمان', 'ar', '', 'SLB');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (456, 804, 'Solomon Island', 'en', '', 'SLB');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (457, 805, N'تونجي', 'ar', '', 'TON');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (458, 805, 'Tongan', 'en', '', 'TON');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (459, 806, N'توفالوي', 'ar', '', 'TUV');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (460, 806, 'Tuvalu', 'en', '', 'TUV');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (461, 807, N'فانيوتو', 'ar', '', '');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (462, 807, N'فانيوتو', 'en', '', '');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (463, 808, N'ساموا الغربية', 'ar', '', '');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (464, 808, 'Samoan W.', 'en', '', '');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (465, 810, N'جوام', 'ar', '', '');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (466, 810, N'جوام', 'en', '', '');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (467, 811, N'جزر ماريانا', 'ar', '', '');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (468, 811, N'جزر ماريانا', 'en', '', '');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (469, 812, N'ميكرونيسيا', 'ar', '691', 'FSM');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (470, 812, 'Micronesia', 'en', '691', 'FSM');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (471, 813, N'جزر ماريشال', 'ar', '692', 'MHL');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (472, 813, 'Marshall Island', 'en', '692', 'MHL');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (473, 814, N'بيلو', 'ar', '', '');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (474, 814, N'بيلو', 'en', '', '');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (475, 548, N'صربي', 'ar', '381', 'SRB');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (476, 548, 'Serbian', 'en', '381', 'SRB');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (477, 550, N'كوسوفوا', 'ar', '', '');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (478, 550, 'Kosovar', 'en', '', '');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (479, 815, N'بولينيسياالفرنسية', 'ar', '', '');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (480, 815, N'بولينيسياالفرنسية', 'en', '', '');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (481, 816, N'Wallis and Futura Isl.', 'ar', '681', 'WLF');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (482, 816, N'Wallis and Futura Isl.', 'en', '681', 'WLF');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (483, 817, N'كاليدونيا الجديد', 'ar', '687', 'NCL');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (484, 817, N'كاليدونيا الجديد', 'en', '687', 'NCL');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (485, 900, N'غير معروف', 'ar', '', '');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (486, 900, 'Unknown', 'en', '', '');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (487, 818, N'مدغشقر', 'ar', '', '');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (488, 818, 'Madagascan', 'en', '261', 'MDG');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (489, 126, N'وثيقة قطرية ', 'ar', '261', 'MDG');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (490, 126, N'وثيقة قطرية', 'en', '', '');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (491, 128, N'وثيقة اماراتية', 'ar', '', '');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (492, 128, 'Emirati Doct', 'en', '', '');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (491, 128, N'دول افريقية اخري', 'ar', '', '');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (492, 128, 'Other Africans', 'en', '', '');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (493, 116, N'كويتي بدون', 'ar', '', '');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (494, 116, 'Kuwaiti Without', 'en', '', '');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (495, 121, N'فلسطيني بوثيقة مصرية', 'ar', '', '');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (496, 121, 'Palestinian (Egyptian T.Doct.)', 'en', '', '');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (497, 125, N'فلسطيني بوثيقة سورية', 'ar', '', '');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (498, 125, 'Palestinian (Syrian T.Doct.)', 'en', '', '');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (499, 352, N'ميانمار/جواز باكستان', 'ar', '', '');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (500, 352, 'Myanmar / Passport of Pakistan', 'en', '', '');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (501, 350, N'بدون', 'ar', '', '');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (502, 350, 'without', 'en', '', '');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (503, 353, N'ميانمار/جوازبنجلا دش', 'ar', '95', 'MMR');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (504, 353, N'ميانمار/جوازبنجلا دش', 'en', '95', 'MMR');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (505, 663, N'سانت كيتس  ونافيس', 'ar', 'Saint Kitts-Nevis-Anguilla-Aru', 'KN1');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (506, 663, N'سانت كيتس  ونافيس', 'en', 'Saint Kitts-Nevis-Anguilla-Aru', 'KN1');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (507, 351, N'ميانمار /مقيم', 'ar', '95', 'MMR');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (508, 351, N'ميانمار /مقيم', 'en', '95', 'MMR');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (509, 131, N'قبائل نازحة/الحليفه', 'ar', '', '');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (510, 131, N'قبائل نازحة/الحليفه', 'en', '', '');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (511, 136, N'غير قطري', 'ar', '', '');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (512, 136, N'غير قطري', 'en', '', '');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (513, 142, N'مقيم/أفراد القبائل', 'ar', '', '');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (514, 142, N'مقيم/أفراد القبائل', 'en', '', '');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (515, 142, N'مقيم/غير معروف', 'ar', '', '');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (516, 143, N'مقيم/غير معروف', 'en', '', '');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (517, 137, N'غير اماراتي', 'ar', '', '');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (518, 137, N'غير اماراتي', 'en', '', '');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (519, 129, N'وثيقة بحرينيه', 'ar', '', '');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (520, 129, N'وثيقة بحرينيه', 'en', '', '');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (521, 823, N'قبيلة نهد', 'ar', '', '');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (522, 823, N'قبيلة نهد', 'en', '', '');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (523, 144, N'مقيم/لا يحمل وثيقة', 'ar', '', '');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (524, 144, N'مقيم/لا يحمل وثيقة', 'en', '', '');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (525, 135, N'غير بحريني', 'ar', '', '');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (526, 135, N'غير بحريني', 'en', '', '');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (527, 707, N'توكيلاو', 'ar', '', 'TKL');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (528, 707, N'Tokelau', 'en', '', 'TKL');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (529, 710, N'فرنسا الجنوب القطبية', 'ar', '', '');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (530, 710, N'فرنسا الجنوب القطبية', 'en', '', '');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (531, 706, N'جزر نورفولك', 'ar', '', 'NFK');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (532, 706, N'جزر نورفولك', 'en', '', 'NFK');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (533, 824, N'جزر مينور', 'ar', '', '');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (534, 824, N'جزر مينور', 'en', '', '');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (535, 134, N'غير كويتي', 'ar', '', '');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (536, 134, N'غير كويتي', 'en', '', '');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (537, 705, N'انتاركتيكا', 'ar', '', '');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (538, 705, N'انتاركتيكا', 'en', '', '');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (539, 826, N'مقيم اجنبي - العدوان', 'ar', '', '');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (540, 826, N'مقيم اجنبي - العدوان', 'en', '', '');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (541, 825, N'مقيم اجنبي - الاشاجعة', 'ar', '', '');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (542, 825, N'مقيم اجنبي - الاشاجعة', 'en', '', '');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (543, 709, N'جزيرةكوكو-كيلنج', 'ar', '61', 'CCK');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (544, 709, N'Cocos (Keeling) Islands', 'en', '61', 'CCK');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (545, 708, N'جزيرةكريسماس', 'ar', '61', 'CXR');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (546, 708, N'جزيرةكريسماس', 'en', '61', 'CXR');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (547, 821, N'قبائل مجاورة للعبر', 'ar', '', '');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (548, 821, N'قبائل مجاورة للعبر', 'en', '', '');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (549, 662, N'البريطانية في المحيط', 'ar', '', '');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (550, 662, N'البريطانية في المحيط', 'en', '', '');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (551, 657, N'جزر كوك', 'ar', '', 'COK');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (552, 657, N'جزر كوك', 'en', '', 'COK');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (553, 664, N'جنوب جورجيا', 'ar', '', 'GEO');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (554, 664, N'جنوب جورجيا', 'en', '', 'GEO');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (555, 132, N'اليمن - لحج', 'ar', '', '');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (556, 132, N'اليمن - لحج', 'en', '', '');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (557, 130, N'عرب ثمانية وأربعون', 'ar', '', '');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (558, 130, N'عرب ثمانية وأربعون', 'en', '', '');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (559, 555, N'ميتروبوليتان فرنسية', 'ar', '', '');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (560, 555, N'ميتروبوليتان فرنسية', 'en', '', '');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (561, 711, N'جزيرة هيرد وماكدونلد', 'ar', '', '');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (562, 711, N'جزيرة هيرد وماكدونلد', 'en', '', '');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (563, 133, N'قبائل نازحة/الكويت', 'ar', '', '');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (564, 133, N'قبائل نازحة/الكويت', 'en', '', '');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (565, 146, N'المناهيل والمهرة', 'ar', '', '');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (566, 146, N'المناهيل والمهرة', 'en', '', '');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (567, 451, N'سانت هيلانة', 'ar', '290', 'SHN');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (568, 451, N'سانت هيلانة', 'en', '290', 'SHN');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (569, 659, N'باربودا', 'ar', '1', 'BRB');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (570, 659, N'باربودا', 'en', '1', 'BRB');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (571, 139, N'مقيم/نازح', 'ar', '', '');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (572, 139, N'مقيم/نازح', 'en', '', '');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (573, 901, N'اخرى', 'ar', '', '');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (574, 901, N'اخرى', 'en', '', '');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (575, 554, N'جزر فيرو', 'ar', '', '');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (576, 554, N'جزر فيرو', 'en', '', '');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (577, 820, N'قبيلة النسي', 'ar', '', '');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (578, 820, N'قبيلة النسي', 'en', '', '');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (579, 822, N'قبيلة الحرث', 'ar', '', '');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (580, 822, N'قبيلة الحرث', 'en', '', '');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (581, 704, N'نيو', 'ar', '', '');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (582, 704, N'نيو', 'en', '', '');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (583, 140, N'مقيم/مولود', 'ar', '', '');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (584, 140, N'مقيم/مولود', 'en', '', '');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (585, 141, N'مقيم/طالب جنسية', 'ar', '', '');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (586, 141, N'مقيم/طالب جنسية', 'en', '', '');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (587, 138, N'غير عماني', 'ar', '', '');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (588, 138, N'غير عماني', 'en', '', '');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (589, 660, N'انتيل الهولندية', 'ar', '599', 'ANT');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (590, 660, N'انتيل الهولندية', 'en', '599', 'ANT');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (591, 454, N'كاب فيرد', 'ar', '238', 'CPV');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (592, 454, N'كاب فيرد', 'en', '238', 'CPV');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (593, 712, N'جزر بيتكايرن', 'ar', '64', 'PCN');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (594, 712, N'Pitcairn', 'en', '64', 'PCN');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (595, 819, N'قبيلة بالعبيد', 'ar', '', '');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (596, 819, N'قبيلة بالعبيد', 'en', '', '');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (597, 145, N'قبيلة الصيعر', 'ar', '', '');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (598, 145, N'قبيلة الصيعر', 'en', '', '');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (599, 127, N'وثيقة عمانيه', 'ar', '', '');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (600, 127, N'وثيقة عمانيه', 'en', '', '');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (601, 117, N'افراد القبائل', 'ar', '', '');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (602, 117, N'افراد القبائل', 'en', '', '');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (603, 639, N'انجويلي', 'ar', '1', 'AIA');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (604, 639, 'Anguillian', 'en', '1', 'AIA');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (605, 649, N'سان استاتيوس', 'ar', '', '');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (606, 649, N'سان استاتيوس', 'en', '', '');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (607, 809, N'ساموا الامريكية', 'ar', '685', 'ASM');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (608, 809, 'Samoana', 'en', '685', 'ASM');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (609, 2222, N'جنسية غير معرفه', 'ar', '', '');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (610, 2222, N'جنسية غير معرفه', 'en', '', '');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (611, 997, N'جميع الجنسيات', 'ar', '', '');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (612, 997, N'جميع الجنسيات', 'en', '', '');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (613, 119, N'قبائل مجاورة للعطفين', 'ar', '', '');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (614, 119, N'قبائل مجاورة للعطفين', 'en', '', '');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (615, 640, N'انتيكوي', 'ar', '1', 'ATG');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (616, 640, 'Antiguan', 'en', '1', 'ATG');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (617, 651, N'سان مارتين', 'ar', '', '');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (618, 651, N'سان مارتين', 'en', '', '');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (619, 551, N'الجبل الاسود', 'ar', '', '');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (620, 551, 'Montenegrin', 'en', '', '');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (621, 348, N'كرغيزستاني', 'ar', '', '');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (622, 348, 'Kyrgyzian', 'en', '', '');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (623, 120, N'اجنبي بجواز سعودي', 'ar', '', '');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (624, 120, 'Foreigner Saudi Passport', 'en', '', '');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (625, 445, N'بوفثاتسواني', 'ar', '267', 'BWA');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (626, 445, 'Botswanian', 'en', '267', 'BWA');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (627, 661, N'جزر كوكوس', 'ar', '', '');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (628, 661, N'جزر كوكوس', 'en', '', '');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (629, 452, N'جزيرةمايوت', 'ar', '', '');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (630, 452, N'جزيرةمايوت', 'en', '', '');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (631, 122, N'فلسطيني بوثيقةعراقية', 'ar', '967', 'PS');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (632, 122, 'Palestinian (Iraqi T.Doct.)', 'en', '967', 'PS');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (633, 122, N'فلسطيني بوثيقةلبناني', 'ar', '967', 'PS');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (634, 122, 'Palestinian (lebanese T.Doct.)', 'en', '967', 'PS');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (635, 122, N'فلسطيني بوثيقةاردنية', 'ar', '967', 'PS');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (636, 122, 'Palestinian (Jordanian T.Doct.)', 'en', '967', 'PS');

SET
IDENTITY_INSERT shc_portal.shc_country_lk OFF;
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
