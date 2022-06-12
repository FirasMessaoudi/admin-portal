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
