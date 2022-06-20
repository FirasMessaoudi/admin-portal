USE shc_portal
GO
SET IDENTITY_INSERT shc_portal.shc_meal_time_type_lk ON;
INSERT INTO shc_portal.shc_meal_time_type_lk (id, code, lang, label)
VALUES (7, 'OTHERS', 'ar', N'أخرى ');
INSERT INTO shc_portal.shc_meal_time_type_lk (id, code, lang, label)
VALUES (8, 'OTHERS', 'en', 'Others');
SET IDENTITY_INSERT shc_portal.shc_meal_time_type_lk OFF;
GO

SET IDENTITY_INSERT shc_portal.shc_meal_type_lk ON;
INSERT INTO shc_portal.shc_meal_type_lk (id, code, lang, label)
VALUES (9, 'OTHERS', 'ar', N'أخرى');
INSERT INTO shc_portal.shc_meal_type_lk (id, code, lang, label)
VALUES (10, 'OTHERS', 'en', 'Others');
SET IDENTITY_INSERT shc_portal.shc_meal_type_lk OFF;
GO

INSERT INTO shc_portal.shc_config (conf_key, conf_value)
VALUES ('sms.api.token', 'D60A13C6105742AD99FEDD608632A157');
INSERT INTO shc_portal.shc_config (conf_key, conf_value)
VALUES ('sms.api.url', 'https://172.16.72.65/api/SmartCards/smsService');
GO

-- update mobile number of default users
UPDATE shc_portal.shc_user SET mobile_number = '551367731' WHERE nin in ('1234567897', '9366014457', '1234567891');
GO

Delete FROM shc_portal.shc_portal.shc_ritual_season WHERE ID > 0;
GO
SET IDENTITY_INSERT shc_portal.shc_portal.shc_ritual_season ON;
INSERT INTO shc_portal.shc_portal.shc_ritual_season (id, season_year, ritual_type_code, season_start, season_end, active) VALUES (1, 1443, 'EXTERNAL_HAJJ', 14431101, 14431230, 'true');
INSERT INTO shc_portal.shc_portal.shc_ritual_season (id, season_year, ritual_type_code, season_start, season_end, active) VALUES (2, 1443, 'INTERNAL_HAJJ', 14431101, 14431230, 'true');
INSERT INTO shc_portal.shc_portal.shc_ritual_season (id, season_year, ritual_type_code, season_start, season_end, active) VALUES (3, 1443, 'COURTESY_HAJJ', 14431101, 14431230, 'true');
SET IDENTITY_INSERT shc_portal.shc_portal.shc_ritual_season OFF;
GO