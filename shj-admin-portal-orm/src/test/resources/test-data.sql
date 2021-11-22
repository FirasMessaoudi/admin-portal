INSERT INTO shc_authority_lk (id, label_ar, label_en, code, parent_id, creation_date)
VALUES (1, N'لوحة المعلومات', N'Admin Dashboard', N'ADMIN_DASHBOARD', null, N'2020-07-27 11:44:00');
INSERT INTO shc_authority_lk (id, label_ar, label_en, code, parent_id, creation_date)
VALUES (2, N'ادارة المستخدمين', N'User Management', N'USER_MANAGEMENT', null, N'2020-07-27 11:44:00');
INSERT INTO shc_authority_lk (id, label_ar, label_en, code, parent_id, creation_date)
VALUES (3, N'اضا�?ة مستخدم', N'Add User', N'ADD_USER', 2, N'2020-07-27 11:44:00');
INSERT INTO shc_authority_lk (id, label_ar, label_en, code, parent_id, creation_date)
VALUES (4, N'تعديل مستخدم', N'Edit User', N'EDIT_USER', 2, N'2020-07-27 11:44:00');
INSERT INTO shc_authority_lk (id, label_ar, label_en, code, parent_id, creation_date)
VALUES (5, N'تغيير حالة مستخدم', N'Change User Status', N'CHANGE_USER_STATUS', 2, N'2020-07-27 11:44:00');
INSERT INTO shc_authority_lk (id, label_ar, label_en, code, parent_id, creation_date)
VALUES (6, N'إعادة تعيين كلمة المرور', N'Reset Password', N'RESET_PASSWORD', 2, N'2020-07-27 11:44:00');
INSERT INTO shc_authority_lk (id, label_ar, label_en, code, parent_id, creation_date)
VALUES (7, N'حذ�? مستخدم', N'Delete User', N'DELETE_USER', 2, N'2020-07-27 11:44:00');
INSERT INTO shc_authority_lk (id, label_ar, label_en, code, parent_id, creation_date)
VALUES (8, N'ادارة الأدوار', N'Role Management', N'ROLE_MANAGEMENT', null, N'2020-07-27 11:44:00');
INSERT INTO shc_authority_lk (id, label_ar, label_en, code, parent_id, creation_date)
VALUES (9, N'اضا�?ة دور', N'Add Role', N'ADD_ROLE', 8, N'2020-07-27 11:44:00');
INSERT INTO shc_authority_lk (id, label_ar, label_en, code, parent_id, creation_date)
VALUES (10, N'تعديل دور', N'Edit Role', N'EDIT_ROLE', 8, N'2020-07-27 11:44:00');
INSERT INTO shc_authority_lk (id, label_ar, label_en, code, parent_id, creation_date)
VALUES (11, N'حذ�? دور', N'Delete Role', N'DELETE_ROLE', 8, N'2020-07-27 11:44:00');
INSERT INTO shc_authority_lk (id, label_ar, label_en, code, parent_id, creation_date)
VALUES (12, N'تغيير حالة دور', N'Change Role Status', N'CHANGE_ROLE_STATUS', 8, N'2020-08-12 12:59:00');
INSERT INTO shc_authority_lk (id, label_ar, label_en, code, parent_id, creation_date)
VALUES (13, N'إعادة تعيين كلمة مرور المستخدم', N'Reset User Password', N'RESET_USER_PASSWORD', 2,
        N'2020-09-07 11:04:00');

INSERT INTO  shc_notification_template_name_lk (id, code, lang,  label ,creation_date ) VALUES (1, 'PASSWORD_EXPIRATION', 'ar', N'قرب انتهاء كلمة المرور',N'2020-08-12 12:59:00');
 INSERT INTO  shc_notification_template_name_lk (id, code, lang, label,creation_date) VALUES (3, 'OUT_ARAFAT_FENCE', 'ar', N'خارج حدود عرفات',N'2020-08-12 12:59:00');
INSERT INTO  shc_notification_template_name_lk (id, code, lang, label,creation_date) VALUES (4, 'OUT_ARAFAT_FENCE', 'en', 'Out of Arafat Geo Fence',N'2020-08-12 12:59:00');
INSERT INTO  shc_notification_template_name_lk (id, code, lang, label,creation_date) VALUES (5, 'DAILY_SURVEY', 'ar', N'تقييم الخدمات اليومية المقدمة',N'2020-08-12 12:59:00');
INSERT INTO  shc_notification_template_name_lk (id, code, lang, label,creation_date) VALUES (6, 'DAILY_SURVEY', 'en', 'Evaluate Daily Service',N'2020-08-12 12:59:00');


INSERT INTO  shc_notification_template_status_lk (id, code, lang,  label ,creation_date) VALUES (1, 'CONFIRMED', 'ar', N'معتمدة', N'2020-09-28 16:19:00');
INSERT INTO  shc_notification_template_status_lk (id, code, lang,  label ,creation_date) VALUES (2, 'CONFIRMED', 'en', 'Confirmed', N'2020-09-28 16:19:00');
INSERT INTO  shc_notification_template_status_lk (id, code, lang,  label ,creation_date) VALUES (3, 'DRAFT', 'ar', N'نسخة أولية', N'2020-09-28 16:19:00');
INSERT INTO  shc_notification_template_status_lk (id, code, lang,  label ,creation_date) VALUES (4, 'DRAFT', 'en', 'Draft', N'2020-09-28 16:19:00');


INSERT INTO  shc_notification_processing_status_lk (id, code, description ,creation_date) VALUES (1, 'NEW', 'Newly created notification request', N'2020-09-28 16:19:00');
INSERT INTO  shc_notification_processing_status_lk (id, code, description ,creation_date) VALUES (2, 'UNDER_PROCESSING', 'Under processing', N'2020-09-28 16:19:00');
INSERT INTO  shc_notification_processing_status_lk (id, code, description ,creation_date) VALUES (3, 'FAILED', 'Failed to process', N'2020-09-28 16:19:00');


INSERT INTO  shc_notification_category_lk (id, code, lang,  label ,creation_date) VALUES (1, 'GENERAL', 'ar', N'عامة', N'2020-09-28 16:19:00');
INSERT INTO  shc_notification_category_lk (id, code, lang,  label ,creation_date) VALUES (2, 'GENERAL', 'en', 'General', N'2020-09-28 16:19:00');
INSERT INTO  shc_notification_category_lk (id, code, lang,  label ,creation_date) VALUES (3, 'HEALTH', 'ar', N'صحي', N'2020-09-28 16:19:00');
INSERT INTO  shc_notification_category_lk (id, code, lang,  label ,creation_date) VALUES (4, 'HEALTH', 'en', 'Health', N'2020-09-28 16:19:00');
INSERT INTO  shc_notification_category_lk (id, code, lang,  label ,creation_date) VALUES (5, 'RELIGIOUS', 'ar', N'دينية', N'2020-09-28 16:19:00');
INSERT INTO  shc_notification_category_lk (id, code, lang,  label ,creation_date) VALUES (6, 'RELIGIOUS', 'en', 'Religious', N'2020-09-28 16:19:00');
INSERT INTO  shc_notification_category_lk (id, code, lang,  label ,creation_date) VALUES (7, 'RITUAL', 'ar', N'شعيرة', N'2020-09-28 16:19:00');
INSERT INTO  shc_notification_category_lk (id, code, lang,  label ,creation_date) VALUES (8, 'RITUAL', 'en', 'Ritual', N'2020-09-28 16:19:00');
INSERT INTO  shc_notification_category_lk (id, code, lang,  label ,creation_date) VALUES (9, 'GENERAL_AWARENESS', 'ar', N'توعية عامة', N'2020-09-28 16:19:00');
INSERT INTO  shc_notification_category_lk (id, code, lang,  label ,creation_date) VALUES (10, 'GENERAL_AWARENESS', 'en', 'General Awareness', N'2020-09-28 16:19:00');


INSERT INTO  shc_notification_template_type_lk (id, code, lang,  label ,creation_date) VALUES (1, 'SYSTEM_DEFINED', 'ar', N'أليا', N'2020-09-28 16:19:00');
INSERT INTO  shc_notification_template_type_lk (id, code, lang,  label ,creation_date) VALUES (2, 'SYSTEM_DEFINED', 'en', 'System Defined', N'2020-09-28 16:19:00');
INSERT INTO  shc_notification_template_type_lk (id, code, lang,  label ,creation_date) VALUES (3, 'USER_DEFINED', 'ar', N'معرف من المستخدم', N'2020-09-28 16:19:00');
INSERT INTO  shc_notification_template_type_lk (id, code, lang,  label ,creation_date) VALUES (4, 'USER_DEFINED', 'en', 'User Defined', N'2020-09-28 16:19:00');


INSERT INTO shc_user_notification_status_lk (id, code, lang,  label ,creation_date) VALUES (1, 'NEW', 'ar', N'جديد', N'2020-09-28 16:19:00');
INSERT INTO shc_user_notification_status_lk (id, code, lang,  label ,creation_date) VALUES (2, 'NEW', 'en', 'New', N'2020-09-28 16:19:00');
INSERT INTO shc_user_notification_status_lk (id, code, lang,  label ,creation_date) VALUES (3, 'READ', 'ar', N'مقروءة', N'2020-09-28 16:19:00');
INSERT INTO shc_user_notification_status_lk (id, code, lang,  label ,creation_date) VALUES (4, 'READ', 'en', 'Read', N'2020-09-28 16:19:00');
INSERT INTO shc_user_notification_status_lk (id, code, lang,  label ,creation_date) VALUES (5, 'EXPIRED', 'ar', N'منتهية', N'2020-09-28 16:19:00');
INSERT INTO shc_user_notification_status_lk (id, code, lang,  label ,creation_date) VALUES (6, 'EXPIRED', 'en', 'Expired', N'2020-09-28 16:19:00');


INSERT INTO shc_role (id, label_ar, label_en, deleted, activated, creation_date, update_date)
VALUES (1, N'مشر�? النظام', N'System Admin', 0, 1, N'2020-07-27 11:44:00', null);
INSERT INTO shc_role (id, label_ar, label_en, deleted, activated, creation_date, update_date)
VALUES (2, N'مستخدم النظام', N'System User', 0, 1, N'2020-09-07 11:04:00', null);



INSERT INTO shc_user (id, nin, gender, mobile_number, date_of_birth_gregorian, date_of_birth_hijri, password_hash,
                      email, first_name, father_name, grand_father_name, family_name, activated, deleted, blocked,
                      block_date, number_of_tries, preferred_language, change_password_required, last_login_date,
                      creation_date, update_date, avatar, token_expiry_date, action_date)
VALUES (1, 1234567897, N'M', 512345678, N'1972-02-14', 14400505,
        N'$2a$10$A81/FuMFJWcxaJhUcL8isuVeKKa.hk7GVzTVTyf7xe/XoMVWuKckK', N'sgh@elm.sa', N'سعد', N'عبد الرحمن', N'',
        N'الغامدي', 1, 0, 0, null, 0, N'en', 0, N'2020-09-28 16:19:00', N'2020-07-21 16:01:00', N'2020-09-28 16:19:00',
        null, N'2020-09-28 16:34:00', null);
INSERT INTO shc_user (id, nin, gender, mobile_number, date_of_birth_gregorian, date_of_birth_hijri, password_hash,
                      email, first_name, father_name, grand_father_name, family_name, activated, deleted, blocked,
                      block_date, number_of_tries, preferred_language, change_password_required, last_login_date,
                      creation_date, update_date, avatar, token_expiry_date, action_date)
VALUES (2, 1234567892, N'M', 512345678, N'2004-08-06', 14250620,
        N'$2a$10$A81/FuMFJWcxaJhUcL8isuVeKKa.hk7GVzTVTyf7xe/XoMVWuKckK', N'mail@company.com', N'ايمن', N'مسعود',
        N'محمد', N'ضاوي', 1, 0, 0, null, 0, null, 1, N'2020-09-20 16:34:00', N'2020-08-25 13:38:00',
        N'2020-09-20 16:34:00', null, N'2020-09-20 16:49:00', N'2020-09-09 13:04:00');
INSERT INTO shc_user (id, nin, gender, mobile_number, date_of_birth_gregorian, date_of_birth_hijri, password_hash,
                      email, first_name, father_name, grand_father_name, family_name, activated, deleted, blocked,
                      block_date, number_of_tries, preferred_language, change_password_required, last_login_date,
                      creation_date, update_date, avatar, token_expiry_date, action_date)
VALUES (3, 1000000164, N'F', 512345678, N'2004-08-12', 14250625,
        N'$2a$10$A81/FuMFJWcxaJhUcL8isuVeKKa.hk7GVzTVTyf7xe/XoMVWuKckK', N'maram@company.com', N'مرام', N'ايمن',
        N'مسعود', N'ضاوي', 1, 1, 0, null, 0, null, 1, null, N'2020-08-25 16:41:00', null, null, null, null);

INSERT INTO shc_user_role(id, user_id, role_id, is_main_role, creation_date) VALUES (1, 1, 1, 1, N'2004-08-12');
INSERT INTO shc_user_role(id, user_id, role_id, is_main_role, creation_date) VALUES (2, 2, 2, 1, N'2004-08-12');
INSERT INTO shc_user_role(id, user_id, role_id, is_main_role, creation_date) VALUES (3, 3, 2, 1, N'2004-08-12');

insert into shc_applicant (id,id_number,date_of_birth_gregorian, full_name_ar, full_name_en, marital_status_code,creation_date)
values (1,1010101040,'1987-02-18','عبدالغنى عبدالعزيز عبدالعزيز السيد' , 'Abdelghany Abdelaziz Abdelaziz Elsayed',1,N'2004-08-12' );

insert into shc_applicant_digital_id (id, uin, applicant_id, creation_date)
values (1, 59737700000059, 1, N'2004-08-12');



INSERT INTO shc_company ( id, code, label_ar, label_en, mission_id, contact_number, website
                        , accreditation_organization, accreditation_number, accreditation_date, accreditation_expiry
                        , email, creation_date, update_date)
VALUES (1, N'111111', N'علم', N'elm', 113, 45567788, null, N'test', N'3445667', N'2021-09-14 12:12:00',
        N'2027-09-14 12:12:00', null, N'2021-09-14 12:12:00', null);


INSERT INTO shc_ritual_season (id, season_year, ritual_type_code, ACTIVE, season_start, season_end)
VALUES (2, 1443, N'INTERNAL_HAJJ', true, 14430210, 14430410);

INSERT INTO shc_company_ritual_season (id, company_id, ritual_season_id, active, total_quota, air_quota, sea_quota,
                                       land_quota, creation_date, update_date, season_start, season_end)
VALUES (1, 1, 2, 1, 1, 1, 1, 1, N'2021-09-14 13:48:00', null, 14430210, 14430410);



INSERT INTO shc_company_staff (id, full_name_ar, full_name_en, id_number, company_id, title_code, mobile_number, email, creation_date, update_date)
 VALUES (1, N'احمد السيد', N'Ahmed Elsayed', 1234567897, 1, null, N'54321678', null, N'2021-09-14 14:27:00', null);

INSERT INTO shc_applicant_group (id, local_office_id, reference_number, arrival_date, departure_date, group_leader_id, company_ritual_season_id, group_type_code, entry_transportation_type_code, creation_date, update_date) VALUES
(1, 1, N'123', null, null, 1, 1, null, null, N'2021-09-14 14:22:00', null);


INSERT INTO shc_group_applicant_list (id, group_id, applicant_uin, creation_date) VALUES
      (1, 1, N'59737700000059', N'2021-09-14 14:30:00');

INSERT INTO shc_company_ritual_step (id, applicant_group_id, transportation_type_code, step_index, step_code, time,
                                     location_lat, location_lng, location_name_ar, location_name_en, creation_date,
                                     update_date)
VALUES (1, 1, 'code', 1, 'step 1', N'2021-09-14 14:27:00', 18.0, 19.0, 'arafet', 'arafet', N'2021-09-14 14:27:00',
        N'2021-09-14 14:27:00');


INSERT INTO shc_ritual_package (id, type_code, price, departure_city, country_id, creation_date, update_date,
                                company_ritual_season_id)
VALUES (1, N'A', '100', null, 1, N'2021-09-15 09:54:00', null, 1);


INSERT INTO shc_applicant_package (id, ritual_package_id, applicant_uin, creation_date, update_date)
VALUES (1, 1, 59737700000059, N'2021-09-15 09:56:00', null);

insert into shc_applicant_ritual (id, applicant_id, visa_number,
                                  permit_number, insurance_number, creation_date, UPDATE_DATE, applicant_package_id)
values (1, 1, '1234567891225678912345678912345678912345678912365',
        '1234567891225678912345678912345678912345678912365', '1234567891225678912345678912345678912345678912365',
        '2021-08-03 17:36:00', '2021-08-03 17:36:00', 1);

insert into shc_applicant_ritual (id, applicant_id, visa_number,
                                  permit_number, insurance_number, creation_date, UPDATE_DATE, applicant_package_id)
values (2, 1, '1234567891225678912345678912345678912345678912365',
        '1234567891225678912345678912345678912345678912365', '1234567891225678912345678912345678912345678912365',
        '2021-08-03 17:36:00', '2021-08-03 17:36:00', 1);

INSERT INTO  shc_applicant_incident (id, reference_number, applicant_ritual_id, status_code, type_code, description, location_lat, location_lng, resolution_comment, creation_date, update_date) VALUES (5, N'12345', 1, N'UNDER_PROCESSING', N'GENERAL', N'hello', 1.00000000, 1.00000000, null, N'2021-11-22 11:18:00', null);
insert into shc_applicant_contact(id, applicant_id, language_list, email, local_mobile_number, intl_mobile_number,
                                  country_code, street_name, district_name, city_name, building_number, postal_code,
                                  creation_date, update_date, applicant_ritual_id)
values (1, 1, 'AR,EN', 'app@elm1', 00966558572482, 00355558572477, 'AL', 545454, 545454, 545454, 545454, 545454,
        '2021-06-30 16:49:00', '2021-08-18 14:40:00', 1);
INSERT INTO shc_package_housing (id, package_id, zone_id, reference_number, category_code, type_code, location_name_ar,
                                 location_name_en, validity_start, validity_end, address_ar, address_en, is_default,
                                 lat, lng, creation_date, update_date)
VALUES (1, 1, null, null, N'VIP', null, N'مكة', N'Macka', null, null, N'مكة', N'Macka', 1, null, null,
        N'2021-09-15 10:10:00', null);

INSERT INTO shc_applicant_package_housing (id, applicant_package_id, package_housing_id, room_number, bed_number,
                                           creation_date, update_date)
VALUES (1, 1, 1, N'22', N'3', N'2021-09-15 10:13:00', null);


INSERT INTO shc_package_catering (id, package_housing_id, meal_code, meal_time, meal_description, type, description_ar,
                                  description_en, creation_date, update_date)
VALUES (1, 1, N'123', '10:00:00', N'mail', N'type', null, null, N'2021-09-15 20:28:00', N'2021-09-15 20:28:00');


INSERT INTO shc_applicant_package_catering (id, applicant_package_id, ritual_package_catering_id, option_ar, option_en,
                                            creation_date, update_date)
VALUES (1, 1, 1, null, null, N'2021-09-15 20:30:00', null);

INSERT INTO shc_package_transportation (id, package_id, type_code, validity_start, validity_end, location_from_name_ar,
                                        location_from_name_en, location_to_name_ar, location_to_name_en,
                                        ritual_step_code, creation_date, update_date)
VALUES (1, 1, N'A', N'2021-09-15 20:43:00', N'2021-09-15 20:43:00', N'مكه', N'Macka', N'الطائف', N'el Taaff', null,
        N'2021-09-15 20:44:00', null);

INSERT INTO shc_applicant_package_transportation (id, applicant_package_id, ritual_package_transportation_id,
                                                  seat_number, wagon_number, vehicle_number, creation_date, update_date)
VALUES (1, 1, 1, N'12', N'1', N'122', N'2021-09-15 20:47:00', null);




 INSERT INTO  shc_notification_template (id, category_code, name_code, status_code, type_code, important,
                                                  action_required, enabled, user_specific,creation_date)
values (3, 'GENERAL', 'PASSWORD_EXPIRATION', 'CONFIRMED', 'SYSTEM_DEFINED', 0, 1, 1, 1,N'2021-10-17 16:35:00');
INSERT INTO  shc_notification_template (id, category_code, name_code, status_code, type_code, important,
                                                  action_required, enabled, user_specific,creation_date)
values (1, 'RITUAL', 'OUT_ARAFAT_FENCE', 'CONFIRMED', 'SYSTEM_DEFINED', 1, 0, 1, 1,N'2021-10-17 16:35:00');
INSERT INTO  shc_notification_template (id, category_code, name_code, status_code, type_code, important,
                                                  action_required, enabled, user_specific,creation_date)
values (2, 'RITUAL', 'DAILY_SURVEY', 'CONFIRMED', 'SYSTEM_DEFINED', 0, 1, 1, 1,N'2021-10-17 16:35:00');



INSERT INTO  shc_notification_request (id, notification_template_id, user_id, processing_status_id, sending_date, creation_date, update_date, user_lang) VALUES (1, 3, 1, 1, N'2021-10-17 16:34:00', N'2021-10-17 16:35:00', null, N'EN');

INSERT INTO  shc_notification_request_parameter_value (id, notification_request_id, notification_template_parameter_id, notification_template_parameter_value, creation_date) VALUES (1, 1, 1, N'5', N'2021-10-17 16:36:00');
INSERT INTO  shc_notification_request_parameter_value (id, notification_request_id, notification_template_parameter_id, notification_template_parameter_value, creation_date) VALUES (2, 1, 6, N'1', N'2021-10-17 16:36:00');
INSERT INTO   shc_notification_request_parameter_value (id, notification_request_id, notification_template_parameter_id, notification_template_parameter_value, creation_date) VALUES (3, 1, 7, N'EN', N'2021-10-17 16:36:00');

INSERT INTO shc_user_notification (id, notification_template_id, user_id, resolved_body, status_code, creation_date, update_date, user_lang) VALUES (982, 3, 1, N'Password will expire after 1 days', N'NEW', N'2021-10-16 16:39:00', null, N'en');
INSERT INTO shc_user_notification (id, notification_template_id, user_id, resolved_body, status_code, creation_date, update_date, user_lang) VALUES (983, 3, 1, N'Password will expire after 1 days', N'NEW', N'2021-10-16 16:40:00', null, N'en');
INSERT INTO shc_user_notification (id, notification_template_id, user_id, resolved_body, status_code, creation_date, update_date, user_lang) VALUES (984, 3, 1, N'Password will expire after 1 days', N'NEW', N'2021-10-16 16:41:00', null, N'en');
INSERT INTO shc_user_notification (id, notification_template_id, user_id, resolved_body, status_code, creation_date, update_date, user_lang) VALUES (985, 3, 1, N'Password will expire after 1 days', N'NEW', N'2021-10-16 16:42:00', null, N'en');
INSERT INTO shc_user_notification (id, notification_template_id, user_id, resolved_body, status_code, creation_date, update_date, user_lang) VALUES (986, 3, 1, N'Password will expire after 1 days', N'NEW', N'2021-10-16 16:43:00', null, N'en');
INSERT INTO shc_user_notification (id, notification_template_id, user_id, resolved_body, status_code, creation_date, update_date, user_lang) VALUES (987, 3, 1, N'Password will expire after 1 days', N'NEW', N'2021-10-16 16:44:00', null, N'en');


