USE shc_portal
----------------      shc_package_type_lk           --------------------------------------
SET IDENTITY_INSERT shc_portal.shc_package_type_lk ON;
INSERT INTO shc_portal.shc_package_type_lk (id, code, lang, label)
VALUES (6, N'VIP', N'en', N'VIP');
INSERT INTO shc_portal.shc_package_type_lk (id, code, lang, label)
VALUES (7, N'VIP', N'ar', N'مميز');
SET IDENTITY_INSERT shc_portal.shc_package_type_lk OFF;
GO

----------------      shc_housing_category_lk           --------------------------------------
SET IDENTITY_INSERT shc_portal.shc_housing_category_lk ON;
INSERT INTO shc_portal.shc_housing_category_lk (id, code, lang, label, type_code, creation_date)
VALUES (3, N'A', N'en', N'A', null, N'2021-09-19 16:05:00');
INSERT INTO shc_portal.shc_housing_category_lk (id, code, lang, label, type_code, creation_date)
VALUES (4, N'A', N'ar', N'أ', null, N'2021-09-19 16:05:00');
SET IDENTITY_INSERT shc_portal.shc_housing_category_lk OFF;
GO

----------------      shc_housing_type_lk           --------------------------------------
SET IDENTITY_INSERT shc_portal.shc_housing_type_lk ON;
INSERT INTO shc_portal.shc_housing_type_lk (id, code, lang, label)
VALUES (1, 'HOTEL', 'en', 'Hotel');
INSERT INTO shc_portal.shc_housing_type_lk (id, code, lang, label)
VALUES (2, 'HOTEL', 'ar', N'فندق');
INSERT INTO shc_portal.shc_housing_type_lk(id, code, label, lang)
VALUES (3, 'CAMP', 'Camp', 'en');
INSERT INTO shc_portal.shc_housing_type_lk(id, code, label, lang)
VALUES (4, 'CAMP', N'مخيم', 'ar');
SET IDENTITY_INSERT shc_portal.shc_housing_type_lk OFF;
GO

----------------      shc_housing_site_lk           --------------------------------------
SET IDENTITY_INSERT shc_portal.shc_housing_site_lk ON;
INSERT INTO shc_portal.shc_housing_site_lk (id, code, lang, label, creation_date)
VALUES (1, N'MAKKAH', N'en', N'Makkah', N'2021-09-19 15:22:00');
INSERT INTO shc_portal.shc_housing_site_lk (id, code, lang, label, creation_date)
VALUES (2, N'MAKKAH', N'ar', N'مكة', N'2021-09-19 15:22:00');
SET IDENTITY_INSERT shc_portal.shc_housing_site_lk OFF;
GO

----------------      shc_housing_zone           --------------------------------------
SET IDENTITY_INSERT shc_portal.shc_housing_zone ON;
INSERT INTO shc_portal.shc_housing_zone (id, label_ar, label_en, color)
VALUES (1, N'مكة', N'Macka', N'red');
SET IDENTITY_INSERT shc_portal.shc_housing_zone OFF;
GO

----------------      shc_transportation_type_lk           --------------------------------------
SET IDENTITY_INSERT shc_portal.shc_transportation_type_lk ON;
INSERT INTO shc_portal.shc_transportation_type_lk (id, code, lang, label) VALUES (1, 'CAR', 'ar', N'سيارة');
INSERT INTO shc_portal.shc_transportation_type_lk (id, code, lang, label) VALUES (2, 'CAR', 'en', 'Car');
INSERT INTO shc_portal.shc_transportation_type_lk (id, code, lang, label) VALUES (3, 'BUS', 'ar', N'باص');
INSERT INTO shc_portal.shc_transportation_type_lk (id, code, lang, label) VALUES (4, 'BUS', 'en', 'Bus');
INSERT INTO shc_portal.shc_transportation_type_lk (id, code, lang, label) VALUES (5, 'TRAIN', 'ar', N'قطار');
INSERT INTO shc_portal.shc_transportation_type_lk (id, code, lang, label) VALUES (6, 'TRAIN', 'en', 'Train');
INSERT INTO shc_portal.shc_transportation_type_lk (id, code, lang, label) VALUES (7, 'AIRPLANE', 'ar', N'طائرة');
INSERT INTO shc_portal.shc_transportation_type_lk (id, code, lang, label) VALUES (8, 'AIRPLANE', 'en', 'AirPlane');
SET IDENTITY_INSERT shc_portal.shc_transportation_type_lk OFF;
GO

----------------      shc_company_ritual_step_lk           --------------------------------------
SET IDENTITY_INSERT shc_portal.shc_company_ritual_step_lk ON;
INSERT INTO shc_portal.shc_company_ritual_step_lk (id, code, lang, label, description, creation_date)
VALUES (1, 'ARRIVE_TO_KSA', 'ar', N'القدوم إلى المملكة', '', GETDATE());
INSERT INTO shc_portal.shc_company_ritual_step_lk (id, code, lang, label, description, creation_date)
VALUES (2, 'ARRIVE_TO_KSA', 'en', 'Arrive to KSA', '', GETDATE());
INSERT INTO shc_portal.shc_company_ritual_step_lk (id, code, lang, label, description, creation_date)
VALUES (3, 'HOTEL', 'ar', N'الذهاب إلي الفندق', '', GETDATE());
INSERT INTO shc_portal.shc_company_ritual_step_lk (id, code, lang, label, description, creation_date)
VALUES (4, 'HOTEL', 'en', 'Go to Hotel', '', GETDATE());
INSERT INTO shc_portal.shc_company_ritual_step_lk (id, code, lang, label, description, creation_date)
VALUES (5, 'FOR_UMRAH', 'ar', N'الذهاب إلى الحرم للطواف والسعي لأداء العمرة', '', GETDATE());
INSERT INTO shc_portal.shc_company_ritual_step_lk (id, code, lang, label, description, creation_date)
VALUES (6, N'FOR_UMRAH', 'en', 'Go to Haram for Umrah', '', GETDATE());
INSERT INTO shc_portal.shc_company_ritual_step_lk (id, code, lang, label, description, creation_date)
VALUES (7, 'FOR_MADINA', 'ar', N'الذهاب إلى المدينة للزيارة', '', GETDATE());
INSERT INTO shc_portal.shc_company_ritual_step_lk (id, code, lang, label, description, creation_date)
VALUES (8, 'FOR_MADINA', 'en', 'Go to visit Madina', '', GETDATE());
SET IDENTITY_INSERT shc_portal.shc_company_ritual_step_lk OFF;
GO

----------------      shc_company_staff_title_lk           --------------------------------------
SET IDENTITY_INSERT shc_portal.shc_company_staff_title_lk ON;
INSERT INTO shc_portal.shc_portal.shc_company_staff_title_lk (id, code, lang, label) VALUES (1, 'GROUP_LEADER', 'en', 'Group Leader ');
INSERT INTO shc_portal.shc_portal.shc_company_staff_title_lk (id, code, lang, label) VALUES (2, 'GROUP_LEADER', 'ar', N'قائد مجموعة');
INSERT INTO shc_portal.shc_portal.shc_company_staff_title_lk (id, code, lang, label) VALUES (3, 'GROUP_DOCTOR', 'en', 'Group Doctor ');
INSERT INTO shc_portal.shc_portal.shc_company_staff_title_lk (id, code, lang, label) VALUES (4, 'GROUP_DOCTOR', 'ar', N'طبيب مجموعة');
INSERT INTO shc_portal.shc_portal.shc_company_staff_title_lk (id, code, lang, label) VALUES (5, 'GROUP_IMAM', 'en', 'Group Imam');
INSERT INTO shc_portal.shc_portal.shc_company_staff_title_lk (id, code, lang, label) VALUES (6, 'GROUP_IMAM', 'ar', N'امام مجموعة');
SET IDENTITY_INSERT shc_portal.shc_company_staff_title_lk OFF;
GO

----------------      shc_company           --------------------------------------
SET IDENTITY_INSERT shc_portal.shc_company ON;
INSERT INTO shc_portal.shc_company (id, code, label_ar, label_en, mission_id, contact_number, website,
                                    accreditation_organization, accreditation_number, accreditation_date,
                                    accreditation_expiry, email)
VALUES (1, N'4343', N'علم', 'Elm', 113, 45567788, null, 'test', '3445667', GETDATE() - 10, GETDATE() + 10, null);
SET IDENTITY_INSERT shc_portal.shc_company OFF;
GO

----------------      shc_ritual_season           --------------------------------------
SET IDENTITY_INSERT shc_portal.shc_ritual_season ON;
INSERT INTO shc_portal.shc_ritual_season (id, season_year, ritual_type_code, active, season_start, season_end)
VALUES (2, 1443, N'INTERNAL_HAJJ', 1, 14430210, 14430710);
SET IDENTITY_INSERT shc_portal.shc_ritual_season OFF;
GO

----------------      shc_company_ritual_season           --------------------------------------
SET IDENTITY_INSERT shc_portal.shc_company_ritual_season ON;
INSERT INTO shc_portal.shc_company_ritual_season (id, company_id, ritual_season_id, active, total_quota, air_quota,
                                                  sea_quota, land_quota, season_start, season_end)
VALUES (1, 1, 2, 1, 1, 1, 1, 1, 14430210, 14430710);
SET IDENTITY_INSERT shc_portal.shc_company_ritual_season OFF;
GO


----------------      shc_company_staff           --------------------------------------
SET IDENTITY_INSERT shc_portal.shc_company_staff ON;
INSERT INTO shc_portal.shc_portal.shc_company_staff (id, full_name_ar, full_name_en, title_code, mobile_number, email, creation_date,
                                                     update_date, id_number_original, gender, date_of_birth_hijri, date_of_birth_gregorian, passport_number, nationality_code, full_name_origin, photo, mobile_number_intl,
                                                     data_request_record_id, id_number, registered, country_code)
VALUES (1, N'احمد عبدالغني السيد', N'Ahmed Abdelghany Elsayed', N'GROUP_LEADER', N'54321678', N'aelsayed@elm.sa',GETDATE(), null, N'28502111998', N'M', 14101010, N'1990-01-01', N'A123456789', N'EG',
        N'احمد عبدالغني السيد', N'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',
        N'00201098885533', null, N'17019889', 0, N'EG');
SET IDENTITY_INSERT shc_portal.shc_company_staff OFF;
GO


----------------      shc_applicant_group           --------------------------------------
SET IDENTITY_INSERT shc_portal.shc_applicant_group ON;
INSERT INTO shc_portal.shc_portal.shc_applicant_group (id, local_office_id, company_ritual_season_id,
                                                       reference_number, arrival_date, departure_date, group_leader_id, group_type_code, entry_transportation_type_code, creation_date)
VALUES (1, 1, 1, N'4343', null, null, 1, null, null, GETDATE());
SET IDENTITY_INSERT shc_portal.shc_applicant_group OFF;
GO

----------------      shc_company_ritual_step           --------------------------------------
SET IDENTITY_INSERT shc_portal.shc_company_ritual_step ON;
INSERT shc_portal.shc_company_ritual_step (id, applicant_group_id, transportation_type_code, step_index, step_code,
                                           time, location_lat, location_lng, location_name_ar, location_name_en)
VALUES (1, 1, 'AIRPLANE', 1, 'ARRIVE_TO_KSA', GETDATE(), 21.38942707, 39.85771465, N'جدة', 'Jeddah')
INSERT shc_portal.shc_company_ritual_step (id, applicant_group_id, transportation_type_code, step_index, step_code,
                                           time, location_lat, location_lng, location_name_ar, location_name_en)
VALUES (2, 1, 'TRAIN', 2, 'HOTEL', GETDATE() + 1, 21.41528241, 39.82818239, N'مكة', 'Makkah')
INSERT shc_portal.shc_company_ritual_step (id, applicant_group_id, transportation_type_code, step_index, step_code,
                                           time, location_lat, location_lng, location_name_ar, location_name_en)
VALUES (3, 1, 'BUS', 3, 'FOR_UMRAH', GETDATE() + 2, 21.41528241, 39.82818239, N'مكة', 'Makkah')
INSERT shc_portal.shc_company_ritual_step (id, applicant_group_id, transportation_type_code, step_index, step_code,
                                           time, location_lat, location_lng, location_name_ar, location_name_en)
VALUES (4, 1, 'BUS', 4, 'FOR_MADINA', GETDATE() + 3, 21.43080634, 39.81360704, N'مدينة', 'Madina')
SET IDENTITY_INSERT shc_portal.shc_company_ritual_step OFF;
GO

----------------      shc_ritual_package           --------------------------------------
SET IDENTITY_INSERT shc_portal.shc_ritual_package ON;
INSERT INTO shc_portal.shc_ritual_package (id, type_code, price, departure_city, country_id,
                                           company_ritual_season_id, reference_number, start_date, end_date)
VALUES (1, 'VIP', 100, null, 1, 1, 'PKG1443', GETDATE() - 1, GETDATE() + 13);
SET IDENTITY_INSERT shc_portal.shc_ritual_package OFF;
GO

----------------      shc_package_housing           --------------------------------------
SET IDENTITY_INSERT shc_portal.shc_package_housing ON;
INSERT shc_portal.shc_package_housing (id, package_id, zone_id, reference_number, category_code, type_code, site_code,
                                       location_name_ar, location_name_en, validity_start, validity_end, address_ar,
                                       address_en, is_default, lat, lng)
VALUES (1, 1, 1, '43431', 'A', 'HOTEL', 'MAKKAH', N'فندق دار التوحيد', 'Dar al Tawhid Hotel', GETDATE(), GETDATE() + 3,
        N'شارع ابراهيم الخليل ،مكة المكرمة', 'Ibrahim El Khalil Street, Makkah', 1, 21.41691157, 39.89633679);

INSERT shc_portal.shc_package_housing (id, package_id, zone_id, reference_number, category_code, type_code, site_code,
                                       location_name_ar, location_name_en, validity_start, validity_end, address_ar,
                                       address_en, is_default, lat, lng)
VALUES (2, 1, 1, N'43432', 'A', 'APARTMENT', 'MENA', 'شقق دار التوحيد', 'Dar al Tawhid Apartment', GETDATE() + 4, GETDATE() + 7,
        N'مخيمات منى ،مكة المكرمة', 'Ibrahim El Khalil Street, Makkah', 1, 21.41691157, 39.89633679);

INSERT shc_portal.shc_package_housing (id, package_id, zone_id, reference_number, category_code, type_code, site_code,
                                       location_name_ar, location_name_en, validity_start, validity_end, address_ar,
                                       address_en, is_default, lat, lng)
VALUES (3, 1, 1, '43433', 'A', 'CAMP', 'ARAFAT', N'مخيم دار التوحيد', 'Dar al Tawhid Camps', GETDATE() + 8, GETDATE() + 11,
        N'مخيمات عرفات ،مكة المكرمة', 'Ibrahim El Khalil Street, Makkah', 1, 21.41691157, 39.89633679);
SET IDENTITY_INSERT shc_portal.shc_package_housing OFF;
GO

----------------      shc_package_transportation           --------------------------------------
SET IDENTITY_INSERT shc_portal.shc_package_transportation ON;
INSERT shc_portal.shc_package_transportation (id, package_id, type_code, validity_start, validity_end,
                                              location_from_name_ar, location_from_name_en, location_to_name_ar,
                                              location_to_name_en, ritual_step_code, creation_date)
VALUES (1, 1, 'AIRPLANE', GETDATE(), GETDATE(), N'الرياض', 'Riyadh', N'جدة', 'Jeddah', 'ARRIVE_TO_KSA', GETDATE());
INSERT INTO shc_portal.shc_package_transportation (id, package_id, type_code, validity_start, validity_end,
                                                   location_from_name_ar, location_from_name_en, location_to_name_ar,
                                                   location_to_name_en, ritual_step_code, creation_date)
VALUES (2, 1, 'TRAIN', GETDATE() + 1, GETDATE() + 4, N'جدة', 'Jeddah', N'مكة', 'Makkah', 'HOTEL', GETDATE());
INSERT shc_portal.shc_package_transportation (id, package_id, type_code, validity_start, validity_end,
                                              location_from_name_ar, location_from_name_en, location_to_name_ar,
                                              location_to_name_en, ritual_step_code, creation_date)
VALUES (3, 1, 'BUS', GETDATE() + 4, GETDATE() + 7, N'جدة', 'Jeddah', N'مكه', 'Macka', 'FOR_UMRAH', GETDATE());
INSERT shc_portal.shc_package_transportation (id, package_id, type_code, validity_start, validity_end,
                                              location_from_name_ar, location_from_name_en, location_to_name_ar,
                                              location_to_name_en, ritual_step_code, creation_date)
VALUES (4, 1, 'BUS', GETDATE() + 7, GETDATE() + 11, N'مكه', 'Macka', N'المدينة', 'Madina', 'FOR_MADINA', GETDATE());
SET IDENTITY_INSERT shc_portal.shc_package_transportation OFF;
GO

----------------      shc_package_catering           --------------------------------------
SET IDENTITY_INSERT shc_portal.shc_package_catering ON;
INSERT INTO shc_portal.shc_portal.shc_package_catering (id, package_housing_id, meal_code, meal_time, meal_description,
                                                        type, description_ar, description_en, creation_date, is_default)
VALUES (1, 1, '123', '06:35:00', 'BREAKFAST', 'BREAKFAST', N'بوفيه مفتوح', 'Open buffet', GETDATE(), 1);
INSERT INTO shc_portal.shc_portal.shc_package_catering (id, package_housing_id, meal_code, meal_time, meal_description,
                                                        type, description_ar, description_en, creation_date, is_default)
VALUES (2, 1, '122', N'14:00:00', N'LUNCH', 'LUNCH', N'غداء', N'Open Lunch', GETDATE(), 1);
INSERT INTO shc_portal.shc_portal.shc_package_catering (id, package_housing_id, meal_code, meal_time, meal_description,
                                                        type, description_ar, description_en, creation_date, is_default)
VALUES (3, 1, '133', '23:00:00', 'DINNER', 'DINNER', N'عشاء', 'Open Dinner', GETDATE(), 1);
SET IDENTITY_INSERT shc_portal.shc_package_catering OFF;
GO

-- UPDATE shc_portal.shc_applicant_package SET start_date = GETDATE() - 1 ,end_date = GETDATE() + 13 WHERE id = 1
-- GO


INSERT INTO shc_portal.shc_religious_occasions_day_lk (code,lang,label)
VALUES ('04_10','en','First days of Tashriq');
INSERT INTO shc_portal.shc_religious_occasions_day_lk (code,lang,label)
VALUES ('04_10','ar',N'اول ايام التشريق');
INSERT INTO shc_portal.shc_religious_occasions_day_lk (code ,lang,label)
VALUES  ('04_11'  ,'en'  ,'Second days of Tashriq' )
INSERT INTO shc_portal.shc_religious_occasions_day_lk (code ,lang,label)
VALUES  ('04_11'  ,'ar'  , N'ثاني ايام التشريق' )
INSERT INTO shc_portal.shc_religious_occasions_day_lk (code ,lang,label)
VALUES  ('04_12'  ,'en'  ,'Third days of Tashriq' )
INSERT INTO shc_portal.shc_religious_occasions_day_lk (code ,lang,label)
VALUES  ('04_12'  ,'ar'  ,N'ثالث ايام التشريق' )
GO

