USE shc_portal
-- insert integration web service user
SET IDENTITY_INSERT shc_portal.shc_user ON;
insert into shc_portal.shc_user (id, nin, gender, mobile_number, date_of_birth_gregorian, password_hash, first_name, family_name, number_of_tries, activated, deleted, creation_date)
values (2, 9366014457, 'M', 512345678, convert(date, '01/01/1970', 103), '$2a$10$A81/FuMFJWcxaJhUcL8isuVeKKa.hk7GVzTVTyf7xe/XoMVWuKckK', 'web', 'service', 0, 'true', 'false', current_timestamp);
SET IDENTITY_INSERT shc_portal.shc_user OFF;
GO
-- insert integration web service user role and authority
SET IDENTITY_INSERT shc_portal.shc_role ON;
INSERT INTO shc_portal.shc_role(id, label_ar, label_en, deleted, activated) VALUES (13, N'مستخدم خارجي', 'WS User', 0, 1);
SET IDENTITY_INSERT shc_portal.shc_role OFF;
GO
INSERT INTO shc_portal.shc_user_role(user_id, role_id, is_main_role) VALUES (2, 13, 1);
GO
SET IDENTITY_INSERT shc_portal.shc_authority_lk ON;
INSERT INTO shc_portal.shc_authority_lk(id, label_ar, label_en, code, parent_id)
VALUES (34, N'طلب خدمة ربط', 'Integration Web Service Call', 'INTEGRATION_WEB_SERVICE_CALL', NULL);
SET IDENTITY_INSERT shc_portal.shc_authority_lk OFF;
GO
INSERT INTO shc_portal.shc_role_authority(role_id, authority_id) VALUES (13, 34);
GO

SET IDENTITY_INSERT shc_portal.shc_authority_lk ON;
INSERT INTO shc_portal.shc_authority_lk(id, label_ar, label_en, code, parent_id) VALUES (35, N'إدارة الإشعارات', 'Notification Management', 'NOTIFICATION_MANAGEMENT', NULL);
SET IDENTITY_INSERT shc_portal.shc_authority_lk OFF;
GO

INSERT INTO shc_portal.shc_role_authority(role_id, authority_id) VALUES (1, 35);
GO

SET IDENTITY_INSERT shc_portal.shc_notification_category_lk ON;
INSERT INTO shc_portal.shc_notification_category_lk (id, code, lang, label) VALUES (1, 'GENERAL', 'ar', N'عامة');
INSERT INTO shc_portal.shc_notification_category_lk (id, code, lang, label) VALUES (2, 'GENERAL', 'en', 'General');
INSERT INTO shc_portal.shc_notification_category_lk (id, code, lang, label) VALUES (3, 'HEALTH', 'ar', N'صحي');
INSERT INTO shc_portal.shc_notification_category_lk (id, code, lang, label) VALUES (4, 'HEALTH', 'en', 'Health');
INSERT INTO shc_portal.shc_notification_category_lk (id, code, lang, label) VALUES (5, 'RELIGIOUS', 'ar', N'دينية');
INSERT INTO shc_portal.shc_notification_category_lk (id, code, lang, label) VALUES (6, 'RELIGIOUS', 'en', 'Religious');
INSERT INTO shc_portal.shc_notification_category_lk (id, code, lang, label) VALUES (7, 'RITUAL', 'ar', N'شعيرة');
INSERT INTO shc_portal.shc_notification_category_lk (id, code, lang, label) VALUES (8, 'RITUAL', 'en', 'Ritual');
INSERT INTO shc_portal.shc_notification_category_lk (id, code, lang, label) VALUES (9, 'GENERAL_AWARENESS', 'ar', N'توعية عامة');
INSERT INTO shc_portal.shc_notification_category_lk (id, code, lang, label) VALUES (10, 'GENERAL_AWARENESS', 'en', 'General Awareness');
SET IDENTITY_INSERT shc_portal.shc_notification_category_lk OFF;
GO

SET IDENTITY_INSERT shc_portal.shc_notification_template_name_lk ON;
INSERT INTO shc_portal.shc_notification_template_name_lk (id, code, lang, label) VALUES (1, 'PASSWORD_EXPIRATION', 'ar', N'قرب انتهاء كلمة المرور');
INSERT INTO shc_portal.shc_notification_template_name_lk (id, code, lang, label) VALUES (2, 'PASSWORD_EXPIRATION', 'en', 'Password Will Expire');
INSERT INTO shc_portal.shc_notification_template_name_lk (id, code, lang, label) VALUES (3, 'OUT_ARAFAT_FENCE', 'ar', N'خارج حدود عرفات');
INSERT INTO shc_portal.shc_notification_template_name_lk (id, code, lang, label) VALUES (4, 'OUT_ARAFAT_FENCE', 'en', 'Out of Arafat Geo Fence');
INSERT INTO shc_portal.shc_notification_template_name_lk (id, code, lang, label) VALUES (5, 'DAILY_SURVEY', 'ar', N'تقييم الخدمات اليومية المقدمة');
INSERT INTO shc_portal.shc_notification_template_name_lk (id, code, lang, label) VALUES (6, 'DAILY_SURVEY', 'en', 'Evaluate Daily Service');
SET IDENTITY_INSERT shc_portal.shc_notification_template_name_lk OFF;
GO

SET IDENTITY_INSERT shc_portal.shc_notification_template_status_lk ON;
INSERT INTO shc_portal.shc_notification_template_status_lk (id, code, lang, label) VALUES (1, 'CONFIRMED', 'ar', N'معتمدة');
INSERT INTO shc_portal.shc_notification_template_status_lk (id, code, lang, label) VALUES (2, 'CONFIRMED', 'en', 'Confirmed');
INSERT INTO shc_portal.shc_notification_template_status_lk (id, code, lang, label) VALUES (3, 'DRAFT', 'ar', N'نسخة أولية');
INSERT INTO shc_portal.shc_notification_template_status_lk (id, code, lang, label) VALUES (4, 'DRAFT', 'en', 'Draft');
SET IDENTITY_INSERT shc_portal.shc_notification_template_status_lk OFF;
GO

SET IDENTITY_INSERT shc_portal.shc_notification_processing_status_lk ON;
INSERT INTO shc_portal.shc_notification_processing_status_lk (id, code, description) VALUES (1, 'NEW', 'Newly created notification request');
INSERT INTO shc_portal.shc_notification_processing_status_lk (id, code, description) VALUES (2, 'UNDER_PROCESSING', 'Under processing');
INSERT INTO shc_portal.shc_notification_processing_status_lk (id, code, description) VALUES (3, 'FAILED', 'Failed to process');
SET IDENTITY_INSERT shc_portal.shc_notification_processing_status_lk OFF;
GO

SET IDENTITY_INSERT shc_portal.shc_notification_template_type_lk ON;
INSERT INTO shc_portal.shc_notification_template_type_lk (id, code, lang, label) VALUES (1, 'SYSTEM_DEFINED', 'ar', N'أليا');
INSERT INTO shc_portal.shc_notification_template_type_lk (id, code, lang, label) VALUES (2, 'SYSTEM_DEFINED', 'en', 'System Defined');
INSERT INTO shc_portal.shc_notification_template_type_lk (id, code, lang, label) VALUES (3, 'USER_DEFINED', 'ar', N'معرف من المستخدم');
INSERT INTO shc_portal.shc_notification_template_type_lk (id, code, lang, label) VALUES (4, 'USER_DEFINED', 'en', 'User Defined');
SET IDENTITY_INSERT shc_portal.shc_notification_template_type_lk OFF;
GO

SET IDENTITY_INSERT shc_portal.shc_user_notification_status_lk ON;
INSERT INTO shc_portal.shc_user_notification_status_lk (id, code, lang, label) VALUES (1, 'NEW', 'ar', N'جديد');
INSERT INTO shc_portal.shc_user_notification_status_lk (id, code, lang, label) VALUES (2, 'NEW', 'en', 'New');
INSERT INTO shc_portal.shc_user_notification_status_lk (id, code, lang, label) VALUES (3, 'READ', 'ar', N'مقروءة');
INSERT INTO shc_portal.shc_user_notification_status_lk (id, code, lang, label) VALUES (4, 'READ', 'en', 'Read');
INSERT INTO shc_portal.shc_user_notification_status_lk (id, code, lang, label) VALUES (5, 'EXPIRED', 'ar', N'منتهية');
INSERT INTO shc_portal.shc_user_notification_status_lk (id, code, lang, label) VALUES (6, 'EXPIRED', 'en', 'Expired');
SET IDENTITY_INSERT shc_portal.shc_user_notification_status_lk OFF;
GO

SET IDENTITY_INSERT shc_portal.shc_notification_template ON;
INSERT INTO shc_portal.shc_notification_template (id, category_code, name_code, status_code, type_code, important, action_required, enabled, user_specific)
values (1, 'GENERAL', 'PASSWORD_EXPIRATION', 'CONFIRMED', 'SYSTEM_DEFINED', 0, 1, 1, 1);
INSERT INTO shc_portal.shc_notification_template (id, category_code, name_code, status_code, type_code, important, action_required, enabled, user_specific)
values (2, 'RITUAL', 'OUT_ARAFAT_FENCE', 'CONFIRMED', 'SYSTEM_DEFINED', 1, 0, 1, 1);
INSERT INTO shc_portal.shc_notification_template (id, category_code, name_code, status_code, type_code, important, action_required, enabled, user_specific)
values (3, 'RITUAL', 'DAILY_SURVEY', 'CONFIRMED', 'SYSTEM_DEFINED', 0, 1, 1, 1);
SET IDENTITY_INSERT shc_portal.shc_notification_template OFF;
GO

SET IDENTITY_INSERT shc_portal.shc_notification_template_content ON;
INSERT INTO shc_portal.shc_notification_template_content (id, notification_template_id, lang, title, body, action_label)
values (1, 1, 'AR', N'قرب انتهاء كلمة المرور', N'سوف تنتهي صلاحية كلمة المرور خلال <days> أيام من الأن', N'تغيير كلمة المرور');
INSERT INTO shc_portal.shc_notification_template_content (id, notification_template_id, lang, title, body, action_label)
values (2, 1, 'EN', 'Password will expire soon', 'Password will expire after <days> days', 'Change Password');
SET IDENTITY_INSERT shc_portal.shc_notification_template_content OFF;
GO

SET IDENTITY_INSERT shc_portal.shc_notification_template_parameter ON;
INSERT INTO shc_portal.shc_notification_template_parameter (id, notification_template_id, parameter_name) values (1, 1, 'days_to_expiry');
SET IDENTITY_INSERT shc_portal.shc_notification_template_parameter OFF;
GO

INSERT INTO shc_portal.shc_portal.shc_config ( conf_key, conf_value  ) VALUES (  'notification.processing.batch.size',  '1000' );
INSERT INTO shc_portal.shc_portal.shc_config (conf_key, conf_value)
VALUES ('scheduler.notification.processing.cron', '* * * ? * *');
GO
GO
INSERT INTO shc_portal.shc_portal.shc_config (conf_key, conf_value)
VALUES ('scheduler.user.notification.expiration.cron', '0 0/1 * * * *');
GO

GO
INSERT INTO shc_portal.shc_portal.shc_config (conf_key, conf_value)
VALUES ('user.notification.expiration.batch.size', 1000);
GO

GO
update shc_portal.shc_notification_template
set expiration_period_in_minutes= 150
where id = 1;

update shc_portal.shc_notification_template
set expiration_period_in_minutes= 150
where id = 2;

update shc_portal.shc_notification_template
set expiration_period_in_minutes= 150
where id = 3;

GO

GO
INSERT INTO shc_portal.shc_portal.shc_notification_template_parameter (  notification_template_id, parameter_name  ) VALUES (  1, N'days_to_expiry' );
GO

SET IDENTITY_INSERT shc_portal.shc_card_status_lk ON;
INSERT INTO shc_portal.shc_portal.shc_card_status_lk (id, code, lang, label)
VALUES (17, 'EXPIRED', 'ar', N'منتهية');
INSERT INTO shc_portal.shc_portal.shc_card_status_lk (id, code, lang, label)
VALUES (18, 'EXPIRED', 'en', 'Expired');
INSERT INTO shc_portal.shc_portal.shc_card_status_lk (id, code, lang, label)
VALUES (19, 'REISSUED', 'ar', N'إعادة الإصدار');
INSERT INTO shc_portal.shc_portal.shc_card_status_lk (id, code, lang, label)
VALUES (20, 'REISSUED', 'en', 'Re-Issue');
SET
IDENTITY_INSERT shc_portal.shc_card_status_lk OFF;
GO

SET IDENTITY_INSERT shc_portal.shc_authority_lk ON;
INSERT INTO shc_portal.shc_authority_lk(id, label_ar, label_en, code, parent_id)
VALUES (36, N'عرض تفاصيل تنبيهات النظام', 'System Defined Notification Details', 'SYSTEM_DEFINED_NOTIFICATION_DETAILS', 35);
SET IDENTITY_INSERT shc_portal.shc_authority_lk OFF;
GO

INSERT INTO shc_portal.shc_role_authority(role_id, authority_id) VALUES (1, 36);
GO

SET IDENTITY_INSERT shc_portal.shc_authority_lk ON;
INSERT INTO shc_portal.shc_authority_lk(id, label_ar, label_en, code, parent_id)
VALUES (37, N'تنبيهات المستخدم', 'User Defined Notification Management', 'USER_DEFINED_NOTIFICATION_MANAGEMENT', 35);
SET IDENTITY_INSERT shc_portal.shc_authority_lk OFF;
GO

INSERT INTO shc_portal.shc_role_authority(role_id, authority_id)
VALUES (1, 37);
GO

--Activate Card (System Admin ,Enrollment officer and enrollment officer admin , Service center agent and service center regent admin ,Hamalah admin  )
insert into shc_portal.shc_role_authority ( role_id, authority_id) values (1,17);

--Suspend Card (System Admin ,Enrollment officer and enrollment officer admin , Service center agent and service center regent admin )
insert into shc_portal.shc_role_authority (role_id, authority_id) values (1, 19);

--Cancel Card (System Admin ,Enrollment officer and enrollment officer admin , Service center agent and service center regent admin )
insert into shc_portal.shc_role_authority (role_id, authority_id) values (1, 18);

--Re-issue Card(System Admin  , Service center agent and service center regent admin )
insert into shc_portal.shc_role_authority (role_id, authority_id) values (1, 20);
GO


UPDATE shc_portal.shc_portal.shc_card_status_lk SET label = N'تمت إعادة الإصدار' WHERE code = 'REISSUED' and lang = 'ar';
UPDATE shc_portal.shc_portal.shc_card_status_lk SET label = 'Reissued' WHERE code = 'REISSUED' and lang = 'en';
GO

INSERT INTO shc_portal.shc_religious_occasions_day_lk (code,lang,label)
VALUES ('12_15','en','First days of Tashriq');
INSERT INTO shc_portal.shc_religious_occasions_day_lk (code,lang,label)
VALUES ('12_15','ar',N'اول ايام التشريق');
GO

INSERT INTO shc_portal.shc_meal_type_lk (code,lang,label)
VALUES ('BREAKFAST','en','Breakfast');
INSERT INTO shc_portal.shc_meal_type_lk (code,lang,label)
VALUES ('BREAKFAST','ar',N'افطار');
INSERT INTO shc_portal.shc_meal_type_lk (code,lang,label)
VALUES ('LUNCH','en','lunch');
INSERT INTO shc_portal.shc_meal_type_lk (code,lang,label)
VALUES ('LUNCH','ar',N'غداء');
INSERT INTO shc_portal.shc_meal_type_lk (code,lang,label)
VALUES ('DINNER','en','Dinner');
INSERT INTO shc_portal.shc_meal_type_lk (code,lang,label)
VALUES ('DINNER','ar',N'عشاء');
GO

INSERT INTO shc_portal.shc_config(conf_key ,conf_value )
VALUES ('scheduler.update.applicant.card.status.cron','0 0/5 * * * *')
GO

UPDATE shc_portal.shc_portal.shc_notification_category_lk SET sample = N'احرص على حمل بطاقة الحج الخاصة بك عند اداء الشعائر'
WHERE code = 'GENERAL' and lang = 'ar';
UPDATE shc_portal.shc_portal.shc_notification_category_lk SET sample = N'في حالة ارتفاع درجة حرارتك فوق 38 درجة توجه الى اقرب نقطة صحية مباشرة'
WHERE code = 'HEALTH' and lang = 'ar';
UPDATE shc_portal.shc_portal.shc_notification_category_lk SET sample = N'ربنا تقبل منا انك انت السميع العليم'
WHERE code = 'RELIGIOUS' and lang = 'ar';
UPDATE shc_portal.shc_portal.shc_notification_category_lk SET sample = N'طواف الافاضة هو ركن من اركان الحج لا يتم الا بالاتيان به؛ و دليل ذلك قوله تعالى: (و ليطوفوا بالبيت العتيق)'
WHERE code = 'RITUAL' and lang = 'ar';
UPDATE shc_portal.shc_portal.shc_notification_category_lk SET sample = N'تجنب صعود الجبال والاماكن المرتفعة وتجنب المزاحمة و الالتحام و الافتراش في الطرقات'
WHERE code = 'GENERAL_AWARENESS' and lang = 'ar';

UPDATE shc_portal.shc_portal.shc_notification_category_lk SET sample = ''
WHERE code = 'GENERAL'
  and lang = 'en';
UPDATE shc_portal.shc_portal.shc_notification_category_lk
SET sample = ''
WHERE code = 'HEALTH'
  and lang = 'en';
UPDATE shc_portal.shc_portal.shc_notification_category_lk
SET sample = ''
WHERE code = 'RELIGIOUS'
  and lang = 'en';
UPDATE shc_portal.shc_portal.shc_notification_category_lk
SET sample = ''
WHERE code = 'RITUAL'
  and lang = 'en';
UPDATE shc_portal.shc_portal.shc_notification_category_lk
SET sample =''
WHERE code = 'GENERAL_AWARENESS'
  and lang = 'en';
Go

SET IDENTITY_INSERT shc_portal.shc_data_segment ON;
insert into shc_portal.shc_data_segment (id, template_file_name, label_ar, label_en)
values (7, 'applicant-emergency-data.xlsx', N'البيانات الضرورية لهوية ضيف الرحمن (في حالة الطوارئ)',
        'Applicant Emergency Data');
SET IDENTITY_INSERT shc_portal.shc_data_segment OFF;
GO
