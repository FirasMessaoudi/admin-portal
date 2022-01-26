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

----------------      shc_applicant_group           --------------------------------------
SET IDENTITY_INSERT shc_portal.shc_applicant_group ON;
INSERT INTO shc_portal.shc_applicant_group (id, local_office_id, reference_number, arrival_date, departure_date,
                                            company_ritual_season_id, group_type_code, entry_transportation_type_code)
VALUES (1, 1, '4343', null, null, 1, null, null);
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
                                           company_ritual_season_id, reference_number)
VALUES (1, 'VIP', 100, null, 1, 1, 'PKG1443');
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

