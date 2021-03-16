USE shj_portal
GO
-- sha_user data
SET IDENTITY_INSERT shj_portal.sha_user ON;
insert into shj_portal.sha_user (id, nin, gender , mobile_number, date_of_birth_gregorian,
password_hash, first_name, father_name, family_name, activated, creation_date) values ('1', 1234567897, 'M',
 512345678, convert(date, '14/02/1972', 103), '$2a$10$A81/FuMFJWcxaJhUcL8isuVeKKa.hk7GVzTVTyf7xe/XoMVWuKckK', N'سعد'
 , N'عبد الرحمن', N'الغامدي', 1, current_timestamp);
SET IDENTITY_INSERT shj_portal.sha_user OFF;

-- sha_user_authorities data
SET IDENTITY_INSERT shj_portal.sha_user_authorities ON;
insert into shj_portal.sha_user_authorities (id, user_id, authority) values (1, 1, 'SHA_ADMIN');
SET IDENTITY_INSERT shj_portal.sha_user_authorities OFF;

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
UPDATE shj_portal.sha_user SET email = 'sgh@elm.sa', subtribe_name='ghamdi' where id = 1;
GO

INSERT INTO shj_portal.sha_config (conf_key, conf_value) VALUES ('login.simultaneous.enabled', 'false');
GO
-- update script for shj_portal aash version 1.8.0
USE shj_portal
GO
SET IDENTITY_INSERT shj_portal.sha_user_role_lk ON;
INSERT INTO shj_portal.sha_user_role_lk (id, name_arabic, name_english, code, level) VALUES (1, N'مشر�? نظام', 'System Admin', 'SHA_ADMIN', 1);
SET IDENTITY_INSERT shj_portal.sha_user_role_lk OFF;
GO

DELETE FROM shj_portal.sha_user_roles WHERE id > 0;
INSERT INTO shj_portal.sha_user_roles (user_id, role_id) VALUES (1, 1);
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
INSERT INTO shj_portal.sha_authority_lk(id, name_arabic, name_english, code, parent_id) VALUES (2, N'ادارة المستخدمين', 'User Management', 'USER_MANAGEMENT', NULL);
INSERT INTO shj_portal.sha_authority_lk(id, name_arabic, name_english, code, parent_id) VALUES (3, N'اضافة مستخدم', 'Add User', 'ADD_USER', 2);
INSERT INTO shj_portal.sha_authority_lk(id, name_arabic, name_english, code, parent_id) VALUES (4, N'تعديل مستخدم', 'Edit User', 'EDIT_USER', 2);
INSERT INTO shj_portal.sha_authority_lk(id, name_arabic, name_english, code, parent_id) VALUES (5, N'تغيير حالة مستخدم', 'Change User Status', 'CHANGE_USER_STATUS', 2);
INSERT INTO shj_portal.sha_authority_lk(id, name_arabic, name_english, code, parent_id) VALUES (6, N'إعادة تعيين كلمة المرور', 'Reset Password', 'RESET_PASSWORD', 2);
INSERT INTO shj_portal.sha_authority_lk(id, name_arabic, name_english, code, parent_id) VALUES (7, N'حذف مستخدم', 'Delete User', 'DELETE_USER', 2);
INSERT INTO shj_portal.sha_authority_lk(id, name_arabic, name_english, code, parent_id) VALUES (8, N'ادارة الأدوار', 'Role Management', 'ROLE_MANAGEMENT', NULL);
INSERT INTO shj_portal.sha_authority_lk(id, name_arabic, name_english, code, parent_id) VALUES (9, N'اضافة دور', 'Add Role', 'ADD_ROLE', 8);
INSERT INTO shj_portal.sha_authority_lk(id, name_arabic, name_english, code, parent_id) VALUES (10, N'تعديل دور', 'Edit Role', 'EDIT_ROLE', 8);
INSERT INTO shj_portal.sha_authority_lk(id, name_arabic, name_english, code, parent_id) VALUES (11, N'حذف دور', 'Delete Role', 'DELETE_ROLE', 8);
INSERT INTO shj_portal.sha_authority_lk(id, name_arabic, name_english, code, parent_id) VALUES (12, N'تغيير حالة دور', 'Change Role Status', 'CHANGE_ROLE_STATUS', 8);
INSERT INTO shj_portal.sha_authority_lk(id, name_arabic, name_english, code, parent_id) VALUES (13, N'إعادة تعيين كلمة مرور المستخدم', 'Reset User Password', 'RESET_USER_PASSWORD', 2);
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

UPDATE shj_portal.sha_user SET role_id = 1 WHERE id = 1;
GO

SET IDENTITY_INSERT shj_portal.sha_role ON;
INSERT INTO shj_portal.sha_role(id, name_arabic, name_english, deleted, activated) VALUES (2, N'مستخدم النظام', 'System User', 0, 1);
SET IDENTITY_INSERT shj_portal.sha_role OFF;
GO

INSERT INTO shj_portal.sha_role_authority(role_id, authority_id) VALUES (2, 1);
GO

UPDATE shj_portal.sha_user SET number_of_tries = 0 WHERE number_of_tries IS NULL;
GO


