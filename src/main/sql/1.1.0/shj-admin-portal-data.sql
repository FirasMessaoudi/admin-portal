USE shc_portal
-- insert integration web service user
SET IDENTITY_INSERT shc_portal.shc_user ON;
insert into shc_portal.shc_user (id, nin, gender, mobile_number, date_of_birth_gregorian, password_hash, first_name, family_name, number_of_tries, activated, deleted, creation_date)
values (2, 9366014457, 'M', 512345678, convert(date, '01/01/1970', 103), '$2a$10$A81/FuMFJWcxaJhUcL8isuVeKKa.hk7GVzTVTyf7xe/XoMVWuKckK', 'web', 'service', 0, 'true', 'false', current_timestamp);
SET IDENTITY_INSERT shc_portal.shc_user OFF;
GO
-- insert integration web service user role and authority
SET IDENTITY_INSERT shc_portal.shc_role ON;
INSERT INTO shc_portal.shc_role(id, label_ar, label_en, deleted, activated) VALUES (13, N'مستخدم خارجي', 'WS User', 0, 1);
SET IDENTITY_INSERT shc_portal.shc_role OFF;
GO
INSERT INTO shc_portal.shc_user_role(user_id, role_id, is_main_role) VALUES (2, 13, 1);
GO
SET IDENTITY_INSERT shc_portal.shc_authority_lk ON;
INSERT INTO shc_portal.shc_authority_lk(id, label_ar, label_en, code, parent_id)
VALUES (34, N'طلب خدمة ربط', 'Integration Web Service Call', 'INTEGRATION_WEB_SERVICE_CALL', NULL);
SET IDENTITY_INSERT shc_portal.shc_authority_lk OFF;
GO
INSERT INTO shc_portal.shc_role_authority(role_id, authority_id) VALUES (13, 34);
GO

SET IDENTITY_INSERT shc_portal.shc_authority_lk ON;

INSERT INTO shc_portal.shc_authority_lk(id, label_ar, label_en, code, parent_id)
VALUES (35, N'إدارة الإشعارات', 'Notification Management', 'NOTIFICATION_MANAGEMENT', NULL);

SET
IDENTITY_INSERT shc_portal.shc_authority_lk OFF;
GO

INSERT INTO shc_portal.shc_role_authority(role_id, authority_id) VALUES (1, 35);

GO
