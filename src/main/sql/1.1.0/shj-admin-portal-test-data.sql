USE shc_portal
----------------      shc_package_type_lk           --------------------------------------

SET IDENTITY_INSERT shc_portal.shc_package_type_lk ON;

INSERT INTO shc_portal.shc_package_type_lk (id, code, lang, label, creation_date)
VALUES (6, N'VIP', N'en', N'VIP', N'2021-09-19 16:06:00');
INSERT INTO shc_portal.shc_package_type_lk (id, code, lang, label, creation_date)
VALUES (7, N'VIP', N'ar', N'مميز', N'2021-09-19 16:06:00');

SET IDENTITY_INSERT shc_portal.shc_package_type_lk OFF;
GO


----------------      shc_housing_category_lk           --------------------------------------


SET IDENTITY_INSERT shc_portal.shc_housing_category_lk ON;

INSERT INTO shc_portal.shc_housing_category_lk (id, code, lang, label, type_code, creation_date)
VALUES (3, N'A', N'en', N'A', null, N'2021-09-19 16:05:00');
INSERT INTO shc_portal.shc_housing_category_lk (id, code, lang, label, type_code, creation_date)
VALUES (4, N'A', N'ar', N'أ', null, N'2021-09-19 16:05:00');

SET
    IDENTITY_INSERT shc_portal.shc_housing_category_lk OFF;
GO


----------------      shc_housing_type_lk           --------------------------------------


SET IDENTITY_INSERT shc_portal.shc_housing_type_lk ON;

INSERT INTO shc_portal.shc_housing_type_lk (id, code, lang, label, creation_date)
VALUES (1, N'HOTEL', N'en', N'Hotel', N'2021-09-17 16:39:00');
INSERT INTO shc_portal.shc_housing_type_lk (id, code, lang, label, creation_date)
VALUES (2, N'HOTEL', N'ar', N'فندق', N'2021-09-17 16:40:00');

SET
    IDENTITY_INSERT shc_portal.shc_housing_type_lk OFF;
GO


----------------      shc_housing_site_lk           --------------------------------------


SET IDENTITY_INSERT shc_portal.shc_housing_site_lk ON;

INSERT INTO shc_portal.shc_housing_site_lk (id, code, lang, label, creation_date)
VALUES (1, N'MAKKAH', N'en', N'Makkah', N'2021-09-19 15:22:00');
INSERT INTO shc_portal.shc_housing_site_lk (id, code, lang, label, creation_date)
VALUES (2, N'MAKKAH', N'ar', N'مكة', N'2021-09-19 15:22:00');

SET
    IDENTITY_INSERT shc_portal.shc_housing_site_lk OFF;
GO


----------------      shc_housing_zone           --------------------------------------


SET IDENTITY_INSERT shc_portal.shc_housing_zone ON;

INSERT INTO shc_portal.shc_housing_zone (id, label_ar, label_en, color)
VALUES (1, N'مكة', N'Macka', N'red');

SET
    IDENTITY_INSERT shc_portal.shc_housing_zone OFF;
GO


----------------      shc_transportation_type_lk           --------------------------------------


SET IDENTITY_INSERT shc_portal.shc_transportation_type_lk ON;
INSERT INTO shc_portal.shc_transportation_type_lk (id, code, lang, label) VALUES (1, 'CAR', 'ar', N'سيارة');
INSERT INTO shc_portal.shc_transportation_type_lk (id, code, lang, label) VALUES (2, 'CAR', 'en', 'Car');
INSERT INTO shc_portal.shc_transportation_type_lk (id, code, lang, label) VALUES (3, 'BUS', 'ar', N'باص');
INSERT INTO shc_portal.shc_transportation_type_lk (id, code, lang, label) VALUES (4, 'BUS', 'en', 'Bus');
INSERT INTO shc_portal.shc_transportation_type_lk (id, code, lang, label) VALUES (5, 'TRAIN', 'ar', N'قطار');
INSERT INTO shc_portal.shc_transportation_type_lk (id, code, lang, label) VALUES (6, 'TRAIN', 'en', 'Train');
SET IDENTITY_INSERT shc_portal.shc_transportation_type_lk OFF;
GO

----------------      shc_company_ritual_step_lk           --------------------------------------


SET IDENTITY_INSERT shc_portal.shc_company_ritual_step_lk ON;

INSERT INTO shc_portal.shc_company_ritual_step_lk (id, code, lang, label, description, creation_date)
VALUES (1, N'ARRIVE_TO_KSA', N'ar', N'القدوم إلى المملكة', N'', N'2021-09-21 12:40:00');
INSERT INTO shc_portal.shc_company_ritual_step_lk (id, code, lang, label, description, creation_date)
VALUES (2, N'ARRIVE_TO_KSA', N'en', N'Arrive to KSA', N'', N'2021-09-21 12:40:00');
INSERT INTO shc_portal.shc_company_ritual_step_lk (id, code, lang, label, description, creation_date)
VALUES (3, N'HOTEL', N'ar', N'الذهاب إلي الفندق', N'', N'2021-09-21 12:40:00');
INSERT INTO shc_portal.shc_company_ritual_step_lk (id, code, lang, label, description, creation_date)
VALUES (4, N'HOTEL', N'en', N'Go to Hotel', N'', N'2021-09-21 12:40:00');
INSERT INTO shc_portal.shc_company_ritual_step_lk (id, code, lang, label, description, creation_date)
VALUES (5, N'FOR_UMRAH', N'ar', N'الذهاب إلى الحرم للطواف والسعي لأداء العمرة', N'',
        N'2021-09-21 12:40:00');
INSERT INTO shc_portal.shc_company_ritual_step_lk (id, code, lang, label, description, creation_date)
VALUES (6, N'FOR_UMRAH', N'en', N'Go to Haram for Umrah', N'', N'2021-09-21 12:40:00');
INSERT INTO shc_portal.shc_company_ritual_step_lk (id, code, lang, label, description, creation_date)
VALUES (7, N'FOR_TAWAF', N'ar', N'الذهاب إلى الحرم طواف القدوم', N'', N'2021-09-21 12:40:00');
INSERT INTO shc_portal.shc_company_ritual_step_lk (id, code, lang, label, description, creation_date)
VALUES (8, N'FOR_TAWAF', N'en', N'Go to Haram for Tawaf', N'', N'2021-09-21 12:40:00');

SET
    IDENTITY_INSERT shc_portal.shc_company_ritual_step_lk OFF;
GO




----------------      shc_company_staff_title_lk           --------------------------------------


SET IDENTITY_INSERT shc_portal.shc_company_staff_title_lk ON;

INSERT INTO shc_portal.shc_company_staff_title_lk (id, code, lang, label, creation_date)
VALUES (1, N'GROUP_LEADER', N'en', N'Group leader ', null);
INSERT INTO shc_portal.shc_company_staff_title_lk (id, code, lang, label, creation_date)
VALUES (2, N'GROUP_LEADER', N'ar', N'قائد مجموعة', null);

SET
    IDENTITY_INSERT shc_portal.shc_company_staff_title_lk OFF;
GO


----------------      shc_company           --------------------------------------


SET IDENTITY_INSERT shc_portal.shc_company ON;

INSERT INTO shc_portal.shc_company (id, code, label_ar, label_en, mission_id, contact_number, website,
                                    accreditation_organization, accreditation_number, accreditation_date,
                                    accreditation_expiry, email, creation_date, update_date)
VALUES (1, N'111111', N'علم', N'elm', 113, 45567788, null, N'test', N'3445667', N'2021-09-14 12:12:00',
        N'2027-09-14 12:12:00', null, N'2021-09-14 12:12:00', null);

SET IDENTITY_INSERT shc_portal.shc_company OFF;
GO


----------------      shc_ritual_season           --------------------------------------


SET IDENTITY_INSERT shc_portal.shc_ritual_season ON;

INSERT INTO shc_portal.shc_ritual_season (id, season_year, ritual_type_code, active, season_start, season_end)
VALUES (2, 1443, N'INTERNAL_HAJJ', 1, 14430210, 14430410);

SET IDENTITY_INSERT shc_portal.shc_ritual_season OFF;
GO


----------------      shc_company_ritual_season           --------------------------------------


SET IDENTITY_INSERT shc_portal.shc_company_ritual_season ON;

INSERT INTO shc_portal.shc_company_ritual_season (id, company_id, ritual_season_id, active, total_quota, air_quota,
                                                  sea_quota, land_quota, creation_date, update_date, season_start,
                                                  season_end)
VALUES (1, 1, 2, 1, 1, 1, 1, 1, N'2021-09-14 13:48:00', null, 14430210, 14430410);

SET IDENTITY_INSERT shc_portal.shc_company_ritual_season OFF;
GO

----------------      shc_company_staff           --------------------------------------


SET IDENTITY_INSERT shc_portal.shc_company_staff ON;

INSERT INTO shc_portal.shc_company_staff (id, full_name_ar, full_name_en, id_number, company_id, title_code,
                                          mobile_number, email, creation_date, update_date, company_ritual_season_id)
VALUES (1, N'احمد السيد', N'Ahmed Elsayed', 1234567897, 1, N'GROUP_LEADER', N'54321678', null, N'2021-09-14 14:27:00',
        null, 1);

SET
    IDENTITY_INSERT shc_portal.shc_company_staff OFF;
GO



----------------      shc_applicant_group           --------------------------------------


SET IDENTITY_INSERT shc_portal.shc_applicant_group ON;

INSERT INTO shc_portal.shc_applicant_group (id, local_office_id, reference_number, arrival_date, departure_date,
                                            group_leader_id, company_ritual_season_id, group_type_code,
                                            entry_transportation_type_code, creation_date, update_date)
VALUES (1, 1, N'123', null, null, 1, 1, null, null, N'2021-09-14 14:22:00', null);

SET IDENTITY_INSERT shc_portal.shc_applicant_group OFF;
GO


----------------      shc_company_ritual_step           --------------------------------------
SET IDENTITY_INSERT shc_portal.shc_company_ritual_step ON;

INSERT INTO shc_portal.shc_company_ritual_step (id, applicant_group_id, transportation_type_code, step_index, step_code,
                                                time, location_lat, location_lng, location_name_ar, location_name_en,
                                                creation_date, update_date)
VALUES (1, 1, N'BUS', 1, N'ARRIVE_TO_KSA', N'2021-07-16 15:27:00', 21.36666520, 40.00166666, N'عرفة', N'Arafat',
        N'2021-09-17 15:27:00', N'2021-09-16 15:27:00');
INSERT INTO shc_portal.shc_company_ritual_step (id, applicant_group_id, transportation_type_code, step_index, step_code,
                                                time, location_lat, location_lng, location_name_ar, location_name_en,
                                                creation_date, update_date)
VALUES (2, 1, N'TRAIN', 2, N'HOTEL', N'2021-08-01 10:34:00', 21.38883178, 39.93599626, N'مزدلفة', N'Muzdalifa',
        N'2021-09-18 10:34:00', N'2021-09-19 10:34:00');
INSERT INTO shc_portal.shc_company_ritual_step (id, applicant_group_id, transportation_type_code, step_index, step_code,
                                                time, location_lat, location_lng, location_name_ar, location_name_en,
                                                creation_date, update_date)
VALUES (3, 1, N'CAR', 3, N'FOR_UMRAH', N'2021-09-21 11:24:00', 21.36666520, 40.00166666, N'عرفة', N'Arafat',
        N'2021-09-19 11:24:00', N'2021-09-20 11:24:00');
INSERT INTO shc_portal.shc_company_ritual_step (id, applicant_group_id, transportation_type_code, step_index, step_code,
                                                time, location_lat, location_lng, location_name_ar, location_name_en,
                                                creation_date, update_date)
VALUES (4, 1, N'BUS', 4, N'FOR_TAWAF', N'2021-09-22 11:24:00', 21.38883178, 39.93599626, N'مزدلفة',
        N'Muzdalifa', N'2021-09-20 11:24:00', N'2021-09-20 11:24:00');

SET
    IDENTITY_INSERT shc_portal.shc_company_ritual_step OFF;
GO


----------------      shc_ritual_package           --------------------------------------


SET IDENTITY_INSERT shc_portal.shc_ritual_package ON;

INSERT INTO shc_portal.shc_ritual_package (id, type_code, price, departure_city, country_id, creation_date, update_date,
                                           company_ritual_season_id, reference_number)
VALUES (1, N'VIP', 100, null, 1, N'2021-09-15 09:54:00', null, 1, N'PKG1442');

SET
    IDENTITY_INSERT shc_portal.shc_ritual_package OFF;
GO

----------------      shc_package_housing           --------------------------------------


SET IDENTITY_INSERT shc_portal.shc_package_housing ON;

INSERT INTO shc_portal.shc_package_housing (id, package_id, zone_id, reference_number, category_code, type_code,
                                            location_name_ar, location_name_en, validity_start, validity_end,
                                            address_ar, address_en, lat, lng, creation_date, update_date, is_default,
                                            site_code)
VALUES (1, 1, 1, N'1111', N'A', N'HOTEL', N'فندق دار التوحيد', N'Dar al Tawhid Hotel', N'2021-09-16 13:15:00',
        N'2021-09-16 13:15:00', N'شارع ابراهيم الخليل،مكة المكرمة', N'Ibrahim El Khalil Street, Makkah ', N'1123',
        N'1233', N'2021-09-15 10:10:00', null, 1, N'MAKKAH');

SET
    IDENTITY_INSERT shc_portal.shc_package_housing OFF;
GO


----------------      shc_package_transportation           --------------------------------------


SET IDENTITY_INSERT shc_portal.shc_package_transportation ON;

INSERT INTO shc_portal.shc_package_transportation (id, package_id, type_code, validity_start, validity_end,
                                                   location_from_name_ar, location_from_name_en, location_to_name_ar,
                                                   location_to_name_en, ritual_step_code, creation_date, update_date)
VALUES (1, 1, N'CAR', N'2021-09-15 20:43:00', N'2021-09-15 20:43:00', N'مكه', N'Macka', N'الطائف', N'el Taaff', null,
        N'2021-09-15 20:44:00', null);

SET
    IDENTITY_INSERT shc_portal.shc_package_transportation OFF;
GO


----------------      shc_package_catering           --------------------------------------


SET IDENTITY_INSERT shc_portal.shc_package_catering ON;

INSERT INTO shc_portal.shc_package_catering (id, package_housing_id, meal_code, meal_time, meal_description, type,
                                             description_ar, description_en, creation_date, update_date)
VALUES (1, 1, N'123', N'10:35:00', N'mail', N'BREAKFAST', N'بوفيه مفتوح', N'Open buffet', N'2021-09-15 20:28:00', null);
INSERT INTO shc_portal.shc_package_catering (id, package_housing_id, meal_code, meal_time, meal_description, type,
                                             description_ar, description_en, creation_date, update_date)
VALUES (2, 1, N'123', N'10:00:00', N'mail', N'type', null, null, N'2021-09-15 20:28:00', null);

SET IDENTITY_INSERT shc_portal.shc_package_catering OFF;
GO
