USE shc_portal
GO
UPDATE shc_portal.shc_config SET conf_value = '10.1.93.131' WHERE conf_key = 'generate.digital.ids.scheduler.active.nodes';
UPDATE shc_portal.shc_config SET conf_value = '10.1.93.132' WHERE conf_key = 'generate.applicant.card.scheduler.active.nodes';
GO

SET IDENTITY_INSERT shc_portal.shc_housing_type_lk ON;
INSERT INTO shc_portal.shc_housing_type_lk (id, code, lang, label) VALUES (7, 'APARTMENT', 'ar', N'شقة');
INSERT INTO shc_portal.shc_housing_type_lk (id, code, lang, label) VALUES (8, 'APARTMENT', 'en', 'Apartment');
SET IDENTITY_INSERT shc_portal.shc_housing_type_lk OFF;
GO