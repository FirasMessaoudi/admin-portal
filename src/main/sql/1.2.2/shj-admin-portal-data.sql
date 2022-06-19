USE shc_portal
GO
INSERT INTO shc_portal.shc_config(conf_key, conf_value) VALUES('scheduler.generate.digital.ids.delay.milliseconds','300000');
INSERT INTO shc_portal.shc_config(conf_key, conf_value)
VALUES ('scheduler.generate.card.applicant.ritual.delay.milliseconds', '600000');
GO

SET IDENTITY_INSERT shc_portal.shc_data_segment ON;
insert into shc_portal.shc_data_segment (id, template_file_name, label_ar, label_en)
values (14, 'applicant-package-housing-data-company.xlsx', N'بيانات باقة السكن  ',
        'Applicant Package Housing Data ');
SET
IDENTITY_INSERT shc_portal.shc_data_segment OFF;
GO

DELETE FROM shc_portal.shc_company_ritual_step_lk WHERE id > 0;
GO
SET IDENTITY_INSERT shc_portal.shc_company_ritual_step_lk ON;
INSERT INTO shc_portal.shc_company_ritual_step_lk (id, code, lang, label, step_index, location_lat, location_lng)
VALUES (1, 'TAWAF_AL_QUDOM', 'ar', N'طواف القدوم', 1, 21.423617600219412, 39.82591208333528);
INSERT INTO shc_portal.shc_company_ritual_step_lk (id, code, lang, label, step_index, location_lat, location_lng)
VALUES (2, 'TAWAF_AL_QUDOM', 'en', 'Tawaf AlQudom', 1, 21.423617600219412, 39.82591208333528);

INSERT INTO shc_portal.shc_company_ritual_step_lk (id, code, lang, label, step_index, location_lat, location_lng)
VALUES (3, 'STAY_IN_MINA', 'ar', N'المبيت في منى', 2, 21.414274989677875, 39.88756806782604);
INSERT INTO shc_portal.shc_company_ritual_step_lk (id, code, lang, label, step_index, location_lat, location_lng)
VALUES (4, 'STAY_IN_MINA', 'en', 'Stay In Mina', 2, 21.414274989677875, 39.88756806782604);

INSERT INTO shc_portal.shc_company_ritual_step_lk (id, code, lang, label, step_index, location_lat, location_lng)
VALUES (5, 'ARAFAT', 'ar', N'الوقوف في عرفات', 3, 21.35497495980284, 39.983982909305766);
INSERT INTO shc_portal.shc_company_ritual_step_lk (id, code, lang, label, step_index, location_lat, location_lng)
VALUES (6, 'ARAFAT', 'en', 'Arafat', 3, 21.35497495980284, 39.983982909305766);

INSERT INTO shc_portal.shc_company_ritual_step_lk (id, code, lang, label, step_index, location_lat, location_lng)
VALUES (7, 'STAY_IN_MUZDALIFA', 'ar', N'المبيت في مزدلفة', 4, 21.38988147228351, 39.90458872373766);
INSERT INTO shc_portal.shc_company_ritual_step_lk (id, code, lang, label, step_index, location_lat, location_lng)
VALUES (8, 'STAY_IN_MUZDALIFA', 'en', 'Stay in Muzdalifa', 4, 21.38988147228351, 39.90458872373766);

INSERT INTO shc_portal.shc_company_ritual_step_lk (id, code, lang, label, step_index, location_lat, location_lng)
VALUES (9, 'THROUGH_GAMARAT_STONES_10', 'ar', N'رمي الجمرات يوم 10', 5, 21.42208621108833, 39.87105874249593);
INSERT INTO shc_portal.shc_company_ritual_step_lk (id, code, lang, label, step_index, location_lat, location_lng)
VALUES (10, 'THROUGH_GAMARAT_STONES_10', 'en', 'Through Gamarat Stones 10', 5, 21.42208621108833, 39.87105874249593);

INSERT INTO shc_portal.shc_company_ritual_step_lk (id, code, lang, label, step_index, location_lat, location_lng)
VALUES (11, 'TAWAF_ELFADAH', 'ar', N'طواف الإفاضة', 6, 21.423617600219412, 39.82591208333528);
INSERT INTO shc_portal.shc_company_ritual_step_lk (id, code, lang, label, step_index, location_lat, location_lng)
VALUES (12, 'TAWAF_ELFADAH', 'en', 'Tawaf Elfadah', 6, 21.423617600219412, 39.82591208333528);

INSERT INTO shc_portal.shc_company_ritual_step_lk (id, code, lang, label, step_index, location_lat, location_lng)
VALUES (13, 'THROUGH_GAMARAT_STONES_11', 'ar', N'رمي الجمرات يوم 11', 7, 21.42208621108833, 39.87105874249593);
INSERT INTO shc_portal.shc_company_ritual_step_lk (id, code, lang, label, step_index, location_lat, location_lng)
VALUES (14, 'THROUGH_GAMARAT_STONES_11', 'en', 'Through Gamarat Stones 11', 7, 21.42208621108833, 39.87105874249593);

INSERT INTO shc_portal.shc_company_ritual_step_lk (id, code, lang, label, step_index, location_lat, location_lng)
VALUES (15, 'THROUGH_GAMARAT_STONES_12', 'ar', N'رمي الجمرات يوم 12', 8, 21.42208621108833, 39.87105874249593);
INSERT INTO shc_portal.shc_company_ritual_step_lk (id, code, lang, label, step_index, location_lat, location_lng)
VALUES (16, 'THROUGH_GAMARAT_STONES_12', 'en', 'Through Gamarat Stones 12', 8, 21.42208621108833, 39.87105874249593);

INSERT INTO shc_portal.shc_company_ritual_step_lk (id, code, lang, label, step_index, location_lat, location_lng)
VALUES (17, 'THROUGH_GAMARAT_STONES_13', 'ar', N'رمي الجمرات يوم 13', 9, 21.42208621108833, 39.87105874249593);
INSERT INTO shc_portal.shc_company_ritual_step_lk (id, code, lang, label, step_index, location_lat, location_lng)
VALUES (18, 'THROUGH_GAMARAT_STONES_13', 'en', 'Through Gamarat Stones 13', 9, 21.42208621108833, 39.87105874249593);

INSERT INTO shc_portal.shc_company_ritual_step_lk (id, code, lang, label, step_index, location_lat, location_lng)
VALUES (19, 'TAWAF_AL_WADAA', 'ar', N'طواف الوداع', 10, 21.423617600219412, 39.82591208333528);
INSERT INTO shc_portal.shc_company_ritual_step_lk (id, code, lang, label, step_index, location_lat, location_lng)
VALUES (20, 'TAWAF_AL_WADAA', 'en', 'Tawaf Alwadaa', 10, 21.423617600219412, 39.82591208333528);
SET IDENTITY_INSERT shc_portal.shc_company_ritual_step_lk OFF;
GO

UPDATE shc_portal.shc_config SET conf_value = 'Hajj App' WHERE conf_key = 'elm.providers.email.from.name';
GO

UPDATE shc_portal.shc_config SET conf_value = '0 0/40 * * * *' WHERE conf_key = 'scheduler.notification.template.processing.cron';
UPDATE shc_portal.shc_config SET conf_value = '0 0/50 * * * *' WHERE conf_key = 'scheduler.notification.processing.cron';
GO

DELETE FROM shc_portal.shc_transportation_type_lk WHERE id > 0;
GO
SET IDENTITY_INSERT shc_portal.shc_transportation_type_lk ON;
INSERT INTO shc_portal.shc_transportation_type_lk (id, code, lang, label) VALUES (1, 'TRAIN', 'ar', N'قطار');
INSERT INTO shc_portal.shc_transportation_type_lk (id, code, lang, label) VALUES (2, 'TRAIN', 'en', 'Train');
INSERT INTO shc_portal.shc_transportation_type_lk (id, code, lang, label) VALUES (3, 'BUS', 'ar', N'باص');
INSERT INTO shc_portal.shc_transportation_type_lk (id, code, lang, label) VALUES (4, 'BUS', 'en', 'Bus');
INSERT INTO shc_portal.shc_transportation_type_lk (id, code, lang, label) VALUES (5, 'CAR', 'ar', N'سيارة');
INSERT INTO shc_portal.shc_transportation_type_lk (id, code, lang, label) VALUES (6, 'CAR', 'en', 'Car');
INSERT INTO shc_portal.shc_transportation_type_lk (id, code, lang, label) VALUES (7, 'AIRPLANE', 'ar', N'طائرة');
INSERT INTO shc_portal.shc_transportation_type_lk (id, code, lang, label) VALUES (8, 'AIRPLANE', 'en', 'AirPlane');
SET IDENTITY_INSERT shc_portal.shc_transportation_type_lk OFF;
GO

DELETE FROM shc_portal.shc_company_staff_title_lk WHERE id > 0;
GO
SET IDENTITY_INSERT shc_portal.shc_company_staff_title_lk ON;
INSERT INTO shc_portal.shc_company_staff_title_lk (id, code, lang, label) VALUES (1, 'GROUP_LEADER', 'en', 'Group Leader ');
INSERT INTO shc_portal.shc_company_staff_title_lk (id, code, lang, label) VALUES (2, 'GROUP_LEADER', 'ar', N'قائد مجموعة');
INSERT INTO shc_portal.shc_company_staff_title_lk (id, code, lang, label) VALUES (3, 'INSPECTOR', 'en', 'Inspector');
INSERT INTO shc_portal.shc_company_staff_title_lk (id, code, lang, label) VALUES (4, 'INSPECTOR', 'ar', N'أخصائي جودة');
INSERT INTO shc_portal.shc_company_staff_title_lk (id, code, lang, label) VALUES (5, 'OTHERS', 'en', 'Others');
INSERT INTO shc_portal.shc_company_staff_title_lk (id, code, lang, label) VALUES (6, 'OTHERS', 'ar', N'أخرى');
SET IDENTITY_INSERT shc_portal.shc_company_staff_title_lk OFF;
GO

UPDATE shc_portal.shc_user SET email = 'aelsayed@elm.sa' WHERE nin in ('1234567897', '9366014457', '1234567891');
GO




UPDATE shc_portal.shc_company SET contact_number = '8001111752' WHERE code = '1_ESTABLISHMENT';
UPDATE shc_portal.shc_company SET contact_number = '8002450013' WHERE code = '2_ESTABLISHMENT';
GO