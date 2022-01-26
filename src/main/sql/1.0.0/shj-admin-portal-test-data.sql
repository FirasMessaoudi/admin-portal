USE shc_portal
GO

SET IDENTITY_INSERT shc_portal.shc_ritual_zone ON;
INSERT INTO shc_portal.shc_ritual_zone (id, code, lang, label)
VALUES (1, 'A', 'ar', N'منطقة أ');
INSERT INTO shc_portal.shc_ritual_zone (id, code, lang, label)
VALUES (2, 'A', 'en', 'Zone A');
INSERT INTO shc_portal.shc_ritual_zone (id, code, lang, label)
VALUES (3, 'B', 'ar', N'منطقة ب');
INSERT INTO shc_portal.shc_ritual_zone (id, code, lang, label)
VALUES (4, 'B', 'en', 'Zone B');
INSERT INTO shc_portal.shc_ritual_zone (id, code, lang, label)
VALUES (5, 'C', 'ar', N'منطقة ج');
INSERT INTO shc_portal.shc_ritual_zone (id, code, lang, label)
VALUES (6, 'C', 'en', 'Zone C');
INSERT INTO shc_portal.shc_ritual_zone (id, code, lang, label)
VALUES (7, 'D', 'ar', N'منطقة د');
INSERT INTO shc_portal.shc_ritual_zone (id, code, lang, label)
VALUES (8, 'D', 'en', 'Zone D');
INSERT INTO shc_portal.shc_ritual_zone (id, code, lang, label)
VALUES (9, 'E', 'ar', N'منطقة ه');
INSERT INTO shc_portal.shc_ritual_zone (id, code, lang, label)
VALUES (10, 'E', 'en', 'Zone E');
SET IDENTITY_INSERT shc_portal.shc_ritual_zone OFF;
GO

SET IDENTITY_INSERT shc_portal.shc_ritual_group ON;
INSERT INTO shc_portal.shc_ritual_group (id, code, lang, label)
VALUES (1, '1', 'ar', N'مجموعة 1');
INSERT INTO shc_portal.shc_ritual_group (id, code, lang, label)
VALUES (2, '1', 'en', 'Group 1');
INSERT INTO shc_portal.shc_ritual_group (id, code, lang, label)
VALUES (3, '2', 'ar', N'مجموعة 2');
INSERT INTO shc_portal.shc_ritual_group (id, code, lang, label)
VALUES (4, '2', 'en', 'Group 2');
INSERT INTO shc_portal.shc_ritual_group (id, code, lang, label)
VALUES (5, '3', 'ar', N'مجموعة 3');
INSERT INTO shc_portal.shc_ritual_group (id, code, lang, label)
VALUES (6, '3', 'en', 'Group 3');
INSERT INTO shc_portal.shc_ritual_group (id, code, lang, label)
VALUES (7, '4', 'ar', N'مجموعة 4');
INSERT INTO shc_portal.shc_ritual_group (id, code, lang, label)
VALUES (8, '4', 'en', 'Group 4');
INSERT INTO shc_portal.shc_ritual_group (id, code, lang, label)
VALUES (9, '5', 'ar', N'مجموعة 5');
INSERT INTO shc_portal.shc_ritual_group (id, code, lang, label)
VALUES (10, '5', 'en', 'Group 5');
SET
IDENTITY_INSERT shc_portal.shc_ritual_group OFF;
GO

SET IDENTITY_INSERT shc_portal.shc_ritual_unit ON;
INSERT INTO shc_portal.shc_ritual_unit (id, code, lang, label)
VALUES (1, '1', 'ar', N'وحدة تنفيذية 1');
INSERT INTO shc_portal.shc_ritual_unit (id, code, lang, label)
VALUES (2, '1', 'en', 'Unit 1');
INSERT INTO shc_portal.shc_ritual_unit (id, code, lang, label)
VALUES (3, '2', 'ar', N'وحدة تنفيذية 2');
INSERT INTO shc_portal.shc_ritual_unit (id, code, lang, label)
VALUES (4, '2', 'en', 'Unit 2');
INSERT INTO shc_portal.shc_ritual_unit (id, code, lang, label)
VALUES (5, '3', 'ar', N'وحدة تنفيذية 3');
INSERT INTO shc_portal.shc_ritual_unit (id, code, lang, label)
VALUES (6, '3', 'en', 'Unit 3');
INSERT INTO shc_portal.shc_ritual_unit (id, code, lang, label)
VALUES (7, '4', 'ar', N'وحدة تنفيذية 4');
INSERT INTO shc_portal.shc_ritual_unit (id, code, lang, label)
VALUES (8, '4', 'en', 'Unit 4');
INSERT INTO shc_portal.shc_ritual_unit (id, code, lang, label)
VALUES (9, '5', 'ar', N'وحدة تنفيذية 5');
INSERT INTO shc_portal.shc_ritual_unit (id, code, lang, label)
VALUES (10, '5', 'en', 'Unit 5');
SET
IDENTITY_INSERT shc_portal.shc_ritual_unit OFF;
GO

-- insert printing user
SET IDENTITY_INSERT shc_portal.shc_user ON;
insert into shc_portal.shc_user (id, nin, gender, mobile_number, date_of_birth_gregorian, date_of_birth_hijri,
                                 password_hash,
                                 email, first_name, father_name, grand_father_name, family_name, activated, deleted,
                                 blocked,
                                 number_of_tries, preferred_language, change_password_required, creation_date)
values (9001, 1234567881, 'M', 512345678, convert(date, '14/02/1972', 103), '14400505',
        '$2a$10$A81/FuMFJWcxaJhUcL8isuVeKKa.hk7GVzTVTyf7xe/XoMVWuKckK',
        'sgh@elm.sa', N'سعيد', N'عبد الرحمن', '', N'العتيبي', 'true', 'false', 'false', 0, 'en', 'false',
        current_timestamp);
SET
IDENTITY_INSERT shc_portal.shc_user OFF;
GO
INSERT INTO shc_portal.shc_user_password_history (user_id, old_password_hash) values (9001, '$2a$10$MLt2QkqgBSo5WdVu5UJXjunvi0t/h.BKDJQWzO2tyrQKBysLmc9ou');
GO
-- assign role for printing user
INSERT INTO shc_portal.shc_user_role (user_id, role_id, is_main_role) VALUES (9001, 8, 1);
GO

-- assign main roles for the system admin user
INSERT INTO shc_portal.shc_user_role (user_id, role_id) VALUES (1, 5);
INSERT INTO shc_portal.shc_user_role (user_id, role_id) VALUES (1, 7);
INSERT INTO shc_portal.shc_user_role (user_id, role_id) VALUES (1, 11);
GO

-- remove user management authorities for non system admin users
DELETE FROM shc_portal.shc_role_authority WHERE authority_id IN (2, 3, 4, 5, 7, 13) AND role_id IN (3, 5, 7, 9, 11);
GO
