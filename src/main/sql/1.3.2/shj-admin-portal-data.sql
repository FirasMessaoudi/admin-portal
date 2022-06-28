USE shc_portal
GO
INSERT INTO shc_portal.shc_config (conf_key, conf_value) VALUES ('complaint.period.minutes', '1440');


INSERT INTO shc_portal.shc_user (nin, gender, mobile_number, date_of_birth_gregorian, password_hash, first_name, family_name, number_of_tries, activated, deleted, creation_date)
VALUES (9553447526, 'M', 512345678, convert(date, '01/01/1970', 103), '$2a$10$A81/FuMFJWcxaJhUcL8isuVeKKa.hk7GVzTVTyf7xe/XoMVWuKckK', 'CRM', 'User', 0, 'true', 'false', current_timestamp);
GO

INSERT INTO shc_portal.shc_user_role(user_id, role_id, is_main_role) VALUES ((SELECT id FROM  shc_portal.shc_user where nin = 9553447526), 13, 1);
GO