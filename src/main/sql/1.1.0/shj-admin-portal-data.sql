USE shc_portal
GO

-- insert integration web service user
SET IDENTITY_INSERT shc_portal.shc_user ON;
insert into shc_portal.shc_user (id, nin, gender, mobile_number, date_of_birth_gregorian, password_hash, first_name,
                                 family_name, number_of_tries, activated, deleted, creation_date)
values (2, 9366014457, 'M', 512345678, convert(date, '01/01/1970', 103),
        '$2a$10$A81/FuMFJWcxaJhUcL8isuVeKKa.hk7GVzTVTyf7xe/XoMVWuKckK', 'web', 'service', 0, 'true', 'false',
        current_timestamp);
SET
IDENTITY_INSERT shc_portal.shc_user OFF;
GO
-- insert integration web service user role and authority
SET IDENTITY_INSERT shc_portal.shc_role ON;
INSERT INTO shc_portal.shc_role(id, label_ar, label_en, deleted, activated)
VALUES (13, N'مستخدم خارجي', 'WS User', 0, 1);
SET
IDENTITY_INSERT shc_portal.shc_role OFF;
GO
INSERT INTO shc_portal.shc_user_role(user_id, role_id, is_main_role) VALUES (2, 13, 1);
GO
SET IDENTITY_INSERT shc_portal.shc_authority_lk ON;
INSERT INTO shc_portal.shc_authority_lk(id, label_ar, label_en, code, parent_id)
VALUES (34, N'طلب خدمة ربط', 'Integration Web Service Call', 'INTEGRATION_WEB_SERVICE_CALL', NULL);
SET
IDENTITY_INSERT shc_portal.shc_authority_lk OFF;
GO
INSERT INTO shc_portal.shc_role_authority(role_id, authority_id) VALUES (13, 34);
GO

SET IDENTITY_INSERT shc_portal.shc_authority_lk ON;
INSERT INTO shc_portal.shc_authority_lk(id, label_ar, label_en, code, parent_id)
VALUES (35, N'إدارة الإشعارات', 'Notification Management', 'NOTIFICATION_MANAGEMENT', NULL);
SET
IDENTITY_INSERT shc_portal.shc_authority_lk OFF;
GO

INSERT INTO shc_portal.shc_role_authority(role_id, authority_id) VALUES (1, 35);
GO

SET IDENTITY_INSERT shc_portal.shc_notification_category_lk ON;
INSERT INTO shc_portal.shc_notification_category_lk (id, code, lang, label)
VALUES (1, 'GENERAL', 'ar', N'عامة');
INSERT INTO shc_portal.shc_notification_category_lk (id, code, lang, label)
VALUES (2, 'GENERAL', 'en', 'General');
INSERT INTO shc_portal.shc_notification_category_lk (id, code, lang, label)
VALUES (3, 'HEALTH', 'ar', N'صحي');
INSERT INTO shc_portal.shc_notification_category_lk (id, code, lang, label)
VALUES (4, 'HEALTH', 'en', 'Health');
INSERT INTO shc_portal.shc_notification_category_lk (id, code, lang, label)
VALUES (5, 'RELIGIOUS', 'ar', N'دينية');
INSERT INTO shc_portal.shc_notification_category_lk (id, code, lang, label)
VALUES (6, 'RELIGIOUS', 'en', 'Religious');
INSERT INTO shc_portal.shc_notification_category_lk (id, code, lang, label)
VALUES (7, 'RITUAL', 'ar', N'شعيرة');
INSERT INTO shc_portal.shc_notification_category_lk (id, code, lang, label)
VALUES (8, 'RITUAL', 'en', 'Ritual');
INSERT INTO shc_portal.shc_notification_category_lk (id, code, lang, label)
VALUES (9, 'GENERAL_AWARENESS', 'ar', N'توعية عامة');
INSERT INTO shc_portal.shc_notification_category_lk (id, code, lang, label)
VALUES (10, 'GENERAL_AWARENESS', 'en', 'General Awareness');
SET
IDENTITY_INSERT shc_portal.shc_notification_category_lk OFF;
GO

SET IDENTITY_INSERT shc_portal.shc_notification_template_name_lk ON;
INSERT INTO shc_portal.shc_notification_template_name_lk (id, code, lang, label)
VALUES (1, 'PASSWORD_EXPIRATION', 'ar', N'قرب انتهاء كلمة المرور');
INSERT INTO shc_portal.shc_notification_template_name_lk (id, code, lang, label)
VALUES (2, 'PASSWORD_EXPIRATION', 'en', 'Password Will Expire');
INSERT INTO shc_portal.shc_notification_template_name_lk (id, code, lang, label)
VALUES (3, 'OUT_ARAFAT_FENCE', 'ar', N'خارج حدود عرفات');
INSERT INTO shc_portal.shc_notification_template_name_lk (id, code, lang, label)
VALUES (4, 'OUT_ARAFAT_FENCE', 'en', 'Out of Arafat Geo Fence');
INSERT INTO shc_portal.shc_notification_template_name_lk (id, code, lang, label)
VALUES (5, 'DAILY_SURVEY', 'ar', N'تقييم الخدمات اليومية المقدمة');
INSERT INTO shc_portal.shc_notification_template_name_lk (id, code, lang, label)
VALUES (6, 'DAILY_SURVEY', 'en', 'Evaluate Daily Service');
INSERT INTO shc_portal.shc_notification_template_name_lk (id, code, lang, label)
VALUES (7, 'RESOLVE_INCIDENT', 'ar', N'حل شكوى');
INSERT INTO shc_portal.shc_notification_template_name_lk (id, code, lang, label)
VALUES (8, 'RESOLVE_INCIDENT', 'en', 'Resolve Incident');
INSERT INTO shc_portal.shc_notification_template_name_lk (id, code, lang, label)
VALUES (9, 'CLOSE_INCIDENT', 'ar', N'حل شكوى');
INSERT INTO shc_portal.shc_notification_template_name_lk (id, code, lang, label)
VALUES (10, 'CLOSE_INCIDENT', 'en', 'Close Incident');
SET
IDENTITY_INSERT shc_portal.shc_notification_template_name_lk OFF;
GO

SET IDENTITY_INSERT shc_portal.shc_notification_template_status_lk ON;
INSERT INTO shc_portal.shc_notification_template_status_lk (id, code, lang, label)
VALUES (1, 'CONFIRMED', 'ar', N'معتمدة');
INSERT INTO shc_portal.shc_notification_template_status_lk (id, code, lang, label)
VALUES (2, 'CONFIRMED', 'en', 'Confirmed');
INSERT INTO shc_portal.shc_notification_template_status_lk (id, code, lang, label)
VALUES (3, 'DRAFT', 'ar', N'نسخة أولية');
INSERT INTO shc_portal.shc_notification_template_status_lk (id, code, lang, label)
VALUES (4, 'DRAFT', 'en', 'Draft');
SET
IDENTITY_INSERT shc_portal.shc_notification_template_status_lk OFF;
GO

SET IDENTITY_INSERT shc_portal.shc_notification_processing_status_lk ON;
INSERT INTO shc_portal.shc_notification_processing_status_lk (id, code, description)
VALUES (1, 'NEW', 'Newly created notification request');
INSERT INTO shc_portal.shc_notification_processing_status_lk (id, code, description)
VALUES (2, 'UNDER_PROCESSING', 'Under processing');
INSERT INTO shc_portal.shc_notification_processing_status_lk (id, code, description)
VALUES (3, 'FAILED', 'Failed to process');
SET
IDENTITY_INSERT shc_portal.shc_notification_processing_status_lk OFF;
GO

SET IDENTITY_INSERT shc_portal.shc_notification_template_type_lk ON;
INSERT INTO shc_portal.shc_notification_template_type_lk (id, code, lang, label)
VALUES (1, 'SYSTEM_DEFINED', 'ar', N'أليا');
INSERT INTO shc_portal.shc_notification_template_type_lk (id, code, lang, label)
VALUES (2, 'SYSTEM_DEFINED', 'en', 'System Defined');
INSERT INTO shc_portal.shc_notification_template_type_lk (id, code, lang, label)
VALUES (3, 'USER_DEFINED', 'ar', N'معرف من المستخدم');
INSERT INTO shc_portal.shc_notification_template_type_lk (id, code, lang, label)
VALUES (4, 'USER_DEFINED', 'en', 'User Defined');
SET
IDENTITY_INSERT shc_portal.shc_notification_template_type_lk OFF;
GO

SET IDENTITY_INSERT shc_portal.shc_user_notification_status_lk ON;
INSERT INTO shc_portal.shc_user_notification_status_lk (id, code, lang, label)
VALUES (1, 'NEW', 'ar', N'جديد');
INSERT INTO shc_portal.shc_user_notification_status_lk (id, code, lang, label)
VALUES (2, 'NEW', 'en', 'New');
INSERT INTO shc_portal.shc_user_notification_status_lk (id, code, lang, label)
VALUES (3, 'READ', 'ar', N'مقروءة');
INSERT INTO shc_portal.shc_user_notification_status_lk (id, code, lang, label)
VALUES (4, 'READ', 'en', 'Read');
INSERT INTO shc_portal.shc_user_notification_status_lk (id, code, lang, label)
VALUES (5, 'EXPIRED', 'ar', N'منتهية');
INSERT INTO shc_portal.shc_user_notification_status_lk (id, code, lang, label)
VALUES (6, 'EXPIRED', 'en', 'Expired');
SET
IDENTITY_INSERT shc_portal.shc_user_notification_status_lk OFF;
GO

SET IDENTITY_INSERT shc_portal.shc_notification_template ON;
INSERT INTO shc_portal.shc_notification_template (id, category_code, name_code, status_code, type_code, important,
                                                  action_required, enabled, user_specific, force_sending)
values (1, 'GENERAL', 'PASSWORD_EXPIRATION', 'CONFIRMED', 'SYSTEM_DEFINED', 0, 1, 1, 1, 1);
INSERT INTO shc_portal.shc_notification_template (id, category_code, name_code, status_code, type_code, important,
                                                  action_required, enabled, user_specific, force_sending)
values (2, 'RITUAL', 'OUT_ARAFAT_FENCE', 'CONFIRMED', 'SYSTEM_DEFINED', 1, 0, 1, 1, 1);
INSERT INTO shc_portal.shc_notification_template (id, category_code, name_code, status_code, type_code, important,
                                                  action_required, enabled, user_specific, force_sending)
values (3, 'RITUAL', 'DAILY_SURVEY', 'CONFIRMED', 'SYSTEM_DEFINED', 0, 1, 1, 1, 1);
INSERT INTO shc_portal.shc_notification_template (id, category_code, name_code, status_code, type_code, important,
                                                  action_required, enabled, user_specific, force_sending)
values (4, 'GENERAL', 'RESOLVE_INCIDENT', 'CONFIRMED', 'SYSTEM_DEFINED', 0, 0, 1, 1, 1);
INSERT INTO shc_portal.shc_notification_template (id, category_code, name_code, status_code, type_code, important,
                                                  action_required, enabled, user_specific, force_sending)
values (5, 'GENERAL', 'CLOSE_INCIDENT', 'CONFIRMED', 'SYSTEM_DEFINED', 0, 0, 1, 1, 1);
SET
IDENTITY_INSERT shc_portal.shc_notification_template OFF;
GO

SET IDENTITY_INSERT shc_portal.shc_notification_template_content ON;
INSERT INTO shc_portal.shc_notification_template_content (id, notification_template_id, lang, title, body, action_label)
VALUES (1, 1, 'AR', N'قرب انتهاء كلمة المرور', N'سوف تنتهي صلاحية كلمة المرور خلال <days_to_expiry> أيام من الأن',
        N'تغيير كلمة المرور');
INSERT INTO shc_portal.shc_notification_template_content (id, notification_template_id, lang, title, body, action_label)
VALUES (2, 1, 'EN', 'Password will expire soon', 'Password will expire after <days_to_expiry> days', 'Change Password');
INSERT INTO shc_portal.shc_notification_template_content (id, notification_template_id, lang, title, body)
VALUES (3, 4, 'AR', N'حل شكوى',
        N'عزيزي ضيف الرحمن نفيدكم بأن راحتكم هي أولويتنا تم دراسة الشكوى المقدمة وتم حلها وفي حالة عدم رضاكم عن الحل أو استمرار الشكوى نأمل رفع شكوى جديدة');
INSERT INTO shc_portal.shc_notification_template_content (id, notification_template_id, lang, title, body)
VALUES (4, 4, 'EN', 'Resolve incident',
        'Dear applicant your satisfaction is our top priority, your complaint has been investigated and resolved. If the provided resolution is not as per your expectations or if the complaint still not resolved we would request you to raise another complaint');
INSERT INTO shc_portal.shc_notification_template_content (id, notification_template_id, lang, title, body)
VALUES (5, 5, 'AR', N'حل شكوى',
        N'عزيزي ضيف الرحمن نفيدكم بأن راحتكم هي أولويتنا تم دراسة الشكوى المقدمة وتم إغلاقها وفي حالة استمرار الشكوى نأمل رفع شكوى جديدة بتفاصيل أكثر');
INSERT INTO shc_portal.shc_notification_template_content (id, notification_template_id, lang, title, body)
VALUES (6, 5, 'EN', 'Close incident',
        'Dear applicant your satisfaction is our top priority, your complaint has been investigated and closed. If the complaint is still not resolved we would request you to raise another complaint with more details');
SET
IDENTITY_INSERT shc_portal.shc_notification_template_content OFF;
GO

INSERT INTO shc_portal.shc_config (conf_key, conf_value) VALUES ('notification.processing.batch.size', '1000');
INSERT INTO shc_portal.shc_config (conf_key, conf_value)
VALUES ('scheduler.notification.processing.cron', '0 0/5 * * * *');
INSERT INTO shc_portal.shc_config (conf_key, conf_value)
VALUES ('google.maps.api.key', 'AIzaSyAC78ugAlOF9B2YK8-ukki2IQTyNAgUSO0');
GO

INSERT INTO shc_portal.shc_portal.shc_config (conf_key, conf_value)
VALUES ('scheduler.user.notification.expiration.cron', '0 0/1 * * * *');
GO

INSERT INTO shc_portal.shc_portal.shc_config (conf_key, conf_value)
VALUES ('user.notification.expiration.batch.size', 1000);
GO

update shc_portal.shc_notification_template
set expiration_period_in_minutes= 150
where id = 1;
GO

update shc_portal.shc_notification_template
set expiration_period_in_minutes= 150
where id = 2;
GO

update shc_portal.shc_notification_template
set expiration_period_in_minutes= 150
where id = 3;
GO

update shc_portal.shc_notification_template
set expiration_period_in_minutes= 0
where id in (4, 5);
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
VALUES (36, N'عرض تفاصيل تنبيهات النظام', 'System Defined Notification Details', 'SYSTEM_DEFINED_NOTIFICATION_DETAILS',
        35);
SET
IDENTITY_INSERT shc_portal.shc_authority_lk OFF;
GO

INSERT INTO shc_portal.shc_role_authority(role_id, authority_id) VALUES (1, 36);
GO

SET IDENTITY_INSERT shc_portal.shc_authority_lk ON;
INSERT INTO shc_portal.shc_authority_lk(id, label_ar, label_en, code, parent_id)
VALUES (37, N'تنبيهات المستخدم', 'User Defined Notification Management', 'USER_DEFINED_NOTIFICATION_MANAGEMENT', 35);
SET
IDENTITY_INSERT shc_portal.shc_authority_lk OFF;
GO

INSERT INTO shc_portal.shc_role_authority(role_id, authority_id)
VALUES (1, 37);
GO

--Activate Card (System Admin ,Enrollment officer and enrollment officer admin , Service center agent and service center regent admin ,Hamalah admin  )
INSERT INTO shc_portal.shc_role_authority ( role_id, authority_id) values (1,17);

--Suspend Card (System Admin ,Enrollment officer and enrollment officer admin , Service center agent and service center regent admin )
INSERT INTO shc_portal.shc_role_authority (role_id, authority_id)
values (1, 19);

--Cancel Card (System Admin ,Enrollment officer and enrollment officer admin , Service center agent and service center regent admin )
INSERT INTO shc_portal.shc_role_authority (role_id, authority_id)
values (1, 18);

--Re-issue Card(System Admin  , Service center agent and service center regent admin )
INSERT INTO shc_portal.shc_role_authority (role_id, authority_id)
values (1, 20);
GO

UPDATE shc_portal.shc_portal.shc_card_status_lk
SET label = N'تمت إعادة الإصدار'
WHERE code = 'REISSUED'
  and lang = 'ar';
UPDATE shc_portal.shc_portal.shc_card_status_lk
SET label = 'Reissued'
WHERE code = 'REISSUED'
  and lang = 'en';
GO

INSERT INTO shc_portal.shc_meal_type_lk (code,lang,label)
VALUES ('BREAKFAST','en','Breakfast');
INSERT INTO shc_portal.shc_meal_type_lk (code, lang, label)
VALUES ('BREAKFAST', 'ar', N'افطار');
INSERT INTO shc_portal.shc_meal_type_lk (code, lang, label)
VALUES ('LUNCH', 'en', 'lunch');
INSERT INTO shc_portal.shc_meal_type_lk (code, lang, label)
VALUES ('LUNCH', 'ar', N'غداء');
INSERT INTO shc_portal.shc_meal_type_lk (code, lang, label)
VALUES ('DINNER', 'en', 'Dinner');
INSERT INTO shc_portal.shc_meal_type_lk (code, lang, label)
VALUES ('DINNER', 'ar', N'عشاء');
GO

INSERT INTO shc_portal.shc_config(conf_key ,conf_value )
VALUES ('scheduler.update.applicant.card.status.cron','0 0/5 * * * *')
GO

UPDATE shc_portal.shc_portal.shc_notification_category_lk
SET sample = N'احرص على حمل بطاقة الحج الخاصة بك عند اداء الشعائر'
WHERE code = 'GENERAL'
  and lang = 'ar';
UPDATE shc_portal.shc_portal.shc_notification_category_lk
SET sample = N'في حالة ارتفاع درجة حرارتك فوق 38 درجة توجه الى اقرب نقطة صحية مباشرة'
WHERE code = 'HEALTH'
  and lang = 'ar';
UPDATE shc_portal.shc_portal.shc_notification_category_lk
SET sample = N'ربنا تقبل منا انك انت السميع العليم'
WHERE code = 'RELIGIOUS'
  and lang = 'ar';
UPDATE shc_portal.shc_portal.shc_notification_category_lk
SET sample = N'طواف الافاضة هو ركن من اركان الحج لا يتم الا بالاتيان به؛ و دليل ذلك قوله تعالى: (و ليطوفوا بالبيت العتيق)'
WHERE code = 'RITUAL'
  and lang = 'ar';
UPDATE shc_portal.shc_portal.shc_notification_category_lk
SET sample = N'تجنب صعود الجبال والاماكن المرتفعة وتجنب المزاحمة و الالتحام و الافتراش في الطرقات'
WHERE code = 'GENERAL_AWARENESS'
  and lang = 'ar';

UPDATE shc_portal.shc_portal.shc_notification_category_lk
SET sample = ''
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
GO

SET IDENTITY_INSERT shc_portal.shc_data_segment ON;
insert into shc_portal.shc_data_segment (id, template_file_name, label_ar, label_en)
values (7, 'applicant-emergency-data.xlsx', N'البيانات الضرورية لهوية ضيف الرحمن (في حالة الطوارئ)',
        'Applicant Emergency Data');
SET
IDENTITY_INSERT shc_portal.shc_data_segment OFF;
GO

SET IDENTITY_INSERT shc_portal.shc_incident_type_lk ON;
INSERT INTO shc_portal.shc_incident_type_lk (id, code, lang, label)
VALUES (1, 'FOOD', 'ar', N'شكوى خدمة طعام');
INSERT INTO shc_portal.shc_incident_type_lk (id, code, lang, label)
VALUES (2, 'FOOD', 'en', 'Food Complaint');
INSERT INTO shc_portal.shc_incident_type_lk (id, code, lang, label)
VALUES (3, 'TRANSPORTATION', 'ar', N'شكوى خدمة نقل');
INSERT INTO shc_portal.shc_incident_type_lk (id, code, lang, label)
VALUES (4, 'TRANSPORTATION', 'en', 'Transportation Complaint');
INSERT INTO shc_portal.shc_incident_type_lk (id, code, lang, label)
VALUES (5, 'HOUSING', 'ar', N'شكوى خدمة سكن');
INSERT INTO shc_portal.shc_incident_type_lk (id, code, lang, label)
VALUES (6, 'HOUSING', 'en', 'Housing Complaint');
INSERT INTO shc_portal.shc_incident_type_lk (id, code, lang, label)
VALUES (7, 'GENERAL', 'ar', N'شكوى عامة');
INSERT INTO shc_portal.shc_incident_type_lk (id, code, lang, label)
VALUES (8, 'GENERAL', 'en', 'General Complaint');
SET
IDENTITY_INSERT shc_portal.shc_incident_type_lk OFF;
GO

SET IDENTITY_INSERT shc_portal.shc_incident_status_lk ON;
INSERT INTO shc_portal.shc_incident_status_lk (id, code, lang, label)
VALUES (1, 'UNDER_PROCESSING', 'ar', N'جاري دراسة الشكوى');
INSERT INTO shc_portal.shc_incident_status_lk (id, code, lang, label)
VALUES (2, 'UNDER_PROCESSING', 'en', 'Under Processing');
INSERT INTO shc_portal.shc_incident_status_lk (id, code, lang, label)
VALUES (3, 'RESOLVED', 'ar', N'تم حل الشكوى');
INSERT INTO shc_portal.shc_incident_status_lk (id, code, lang, label)
VALUES (4, 'RESOLVED', 'en', 'Resolved');
INSERT INTO shc_portal.shc_incident_status_lk (id, code, lang, label)
VALUES (5, 'CLOSED', 'ar', N'تم إغلاق الشكوى');
INSERT INTO shc_portal.shc_incident_status_lk (id, code, lang, label)
VALUES (6, 'CLOSED', 'en', 'Closed');
SET
IDENTITY_INSERT shc_portal.shc_incident_status_lk OFF;
GO

SET IDENTITY_INSERT shc_portal.shc_data_segment ON;
insert into shc_portal.shc_data_segment (id, template_file_name, label_ar, label_en)
values (9, 'staff-ritual-data.xlsx', '', 'Staff Ritual Data');
SET
IDENTITY_INSERT shc_portal.shc_data_segment OFF;
GO


SET IDENTITY_INSERT shc_portal.shc_data_segment ON;
insert into shc_portal.shc_data_segment (id, template_file_name, label_ar, label_en)
values (8, 'staff-main-data.xlsx', N'بيانات العاملين',
        'Staff Main Data');
SET
IDENTITY_INSERT shc_portal.shc_data_segment OFF;
GO

INSERT INTO shc_portal.shc_config (conf_key, conf_value)
VALUES ('scheduler.generate.staff.digital.ids.cron', '0 0/15 * * * *');
GO

INSERT INTO shc_portal.shc_config (conf_key, conf_value)
VALUES ('data.request.company.staff.override', 'false');
GO

update shc_portal.shc_data_segment
set label_ar =N'بيانات الشعيرة للعاملين'
where id = 9
    GO

delete
from shc_portal.shc_config
where conf_key = 'scheduler.generate.staff.digital.ids.cron';
Go

update shc_portal.shc_notification_category_lk
set shc_notification_category_lk.mandatory = 1
where code in ('HEALTH', 'RITUAL');
Go

update shc_portal.shc_notification_category_lk
set shc_notification_category_lk.mandatory = 0
where code not in ('HEALTH', 'RITUAL');
GO

INSERT INTO shc_portal.shc_portal.shc_config (conf_key, conf_value)
VALUES ('sftp.incident.client.protocol', 'sftp');
INSERT INTO shc_portal.shc_portal.shc_config (conf_key, conf_value)
VALUES ('sftp.incident.client.host', '127.0.0.1');
INSERT INTO shc_portal.shc_portal.shc_config (conf_key, conf_value)
VALUES ('sftp.incident.client.port', '22');
INSERT INTO shc_portal.shc_portal.shc_config (conf_key, conf_value)
VALUES ('sftp.incident.client.username', 'sftp-user');
INSERT INTO shc_portal.shc_portal.shc_config (conf_key, conf_value)
VALUES ('sftp.incident.client.password', 'Aa123456');
INSERT INTO shc_portal.shc_portal.shc_config (conf_key, conf_value)
VALUES ('sftp.incident.client.root-folder', '/data/smart-hajj/applicant-incidents/');
INSERT INTO shc_portal.shc_portal.shc_config (conf_key, conf_value)
VALUES ('sftp.incident.client.session-strict-host-key-checking', 'no');
INSERT INTO shc_portal.shc_portal.shc_config (conf_key, conf_value)
VALUES ('sftp.incident.client.session-connect-timeout', '15000');
INSERT INTO shc_portal.shc_portal.shc_config (conf_key, conf_value)
VALUES ('sftp.incident.client.channel-connected-timeout', '15000');
GO

SET IDENTITY_INSERT shc_portal.shc_data_segment ON;
insert into shc_portal.shc_data_segment (id, template_file_name, label_ar, label_en)
values (10, 'staff-applicant-group-data.xlsx', N'بيانات المجموعات',
        'Staff Applicant Group Data');
SET
IDENTITY_INSERT shc_portal.shc_data_segment OFF;
GO

SET IDENTITY_INSERT shc_portal.shc_contact_type_lk ON;
INSERT INTO shc_portal.shc_contact_type_lk (id, code)
VALUES (1, 'STAFF');
INSERT INTO shc_portal.shc_contact_type_lk (id, code)
VALUES (2, 'APPLICANT');
SET
IDENTITY_INSERT shc_portal.shc_contact_type_lk OFF;
GO

update shc_portal.shc_authority_lk
set label_ar =N'إدارة طلبات الطباعة لضيوف الرحمن',
    label_en ='Applicant Printing Request Management',
    code='APPLICANT_PRINTING_REQUEST_MANAGEMENT'
where id = 24;
GO

SET IDENTITY_INSERT shc_portal.shc_authority_lk ON;
INSERT INTO shc_portal.shc_authority_lk(id, label_ar, label_en, code, parent_id)
VALUES (38, N'إدارة طلبات الطباعة لموظفى الشركات', 'Staff Printing Request Management',
        'STAFF_PRINTING_REQUEST_MANAGEMENT', NULL);
SET
IDENTITY_INSERT shc_portal.shc_authority_lk OFF;
GO

insert into shc_portal.shc_role_authority (role_id, authority_id)
values (1, 38);
GO

UPDATE shc_portal.shc_data_segment
SET label_ar = REPLACE(label_ar, N'الرحمان', N'الرحمن')
WHERE id > 0;
GO

INSERT INTO shc_portal.shc_config (conf_key, conf_value)
VALUES ('incident.file.allowed.extensions',
        'apng,avif,gif,jpeg,jpg,png,svg,webp,bmp,tiff,mp4,mov,wmv,avi,flv,avchd,mkv');
INSERT INTO shc_portal.shc_config (conf_key, conf_value)
VALUES ('incident.file.allowed.max.size', '15');
GO

update shc_portal.shc_print_batch_type_lk
set shc_print_batch_type_lk.target ='APPLICANT'
where id <= 5;
Go

SET IDENTITY_INSERT shc_portal.shc_print_batch_type_lk ON;
insert into shc_portal.shc_print_batch_type_lk (id, label_ar, label_en, code, target)
values (6, N'الجنسية', 'Nationality', 'STAFF_NATIONALITY', 'STAFF');
insert into shc_portal.shc_print_batch_type_lk (id, label_ar, label_en, code, target)
values (7, N'الشركة', 'Company', 'COMPANY', 'STAFF');
GO

SET IDENTITY_INSERT shc_portal.shc_print_batch_type_lk OFF;
GO

INSERT INTO shc_portal.shc_config (conf_key, conf_value) VALUES ('scheduler.staff.digitalId.invalidate.cron', '0 0 0 * * ?');
GO

SET IDENTITY_INSERT shc_portal.shc_applicant_digital_id_status_lk ON;
INSERT INTO shc_portal.shc_portal.shc_applicant_digital_id_status_lk (id, code, lang, label)
VALUES (1, 'VALID', 'ar', N'نشط');
INSERT INTO shc_portal.shc_portal.shc_applicant_digital_id_status_lk (id, code, lang, label)
VALUES (2, 'VALID', 'en', 'Active');
INSERT INTO shc_portal.shc_portal.shc_applicant_digital_id_status_lk (id, code, lang, label)
VALUES (3, 'INVALID', 'ar', N'متوقف');
INSERT INTO shc_portal.shc_portal.shc_applicant_digital_id_status_lk (id, code, lang, label)
VALUES (4, 'INVALID', 'en', 'Invalid');
SET
IDENTITY_INSERT shc_portal.shc_applicant_digital_id_status_lk OFF;
GO

INSERT INTO shc_portal.shc_config (conf_key, conf_value) VALUES ('applicants.counter.ages.range', '0-12,12-18,18-30,30-50,50-70,70-200');
GO

SET IDENTITY_INSERT shc_portal.shc_housing_site_lk ON;
INSERT INTO shc_portal.shc_housing_site_lk (id, code, lang, label, creation_date)
VALUES (1, 'MAKKAH', 'en', 'Makkah', GETDATE());
INSERT INTO shc_portal.shc_housing_site_lk (id, code, lang, label, creation_date)
VALUES (2, 'MAKKAH', 'ar', N'مكة المكرمة', GETDATE());
INSERT INTO shc_portal.shc_housing_site_lk (id, code, lang, label, creation_date)
VALUES (3, 'MADINA', 'en', 'Madina', GETDATE());
INSERT INTO shc_portal.shc_housing_site_lk (id, code, lang, label, creation_date)
VALUES (4, 'MADINA', 'ar', N'المدينة المنورة', GETDATE());
INSERT INTO shc_portal.shc_housing_site_lk (id, code, lang, label, creation_date)
VALUES (5, 'MAKKAH_HOLY_MOSQUE', 'en', 'Holy Mosque in Mecca', GETDATE());
INSERT INTO shc_portal.shc_housing_site_lk (id, code, lang, label, creation_date)
VALUES (6, 'MAKKAH_HOLY_MOSQUE', 'ar', N'المسجد الحرام في مكة', GETDATE());
INSERT INTO shc_portal.shc_housing_site_lk (id, code, lang, label, creation_date)
VALUES (7, 'MADINA_HOLY_MOSQUE', 'en', 'Holy Mosque in Madina', GETDATE());
INSERT INTO shc_portal.shc_housing_site_lk (id, code, lang, label, creation_date)
VALUES (8, 'MADINA_HOLY_MOSQUE', 'ar', N'المسجد الحرام في المدينة', GETDATE());
INSERT INTO shc_portal.shc_housing_site_lk (id, code, lang, label, creation_date)
VALUES (9, 'MENA', 'en', 'Mena', GETDATE());
INSERT INTO shc_portal.shc_housing_site_lk (id, code, lang, label, creation_date)
VALUES (10, 'MENA', 'ar', N'منى', GETDATE());
INSERT INTO shc_portal.shc_housing_site_lk (id, code, lang, label, creation_date)
VALUES (11, 'GAMARAT', 'en', 'Gamarat', GETDATE());
INSERT INTO shc_portal.shc_housing_site_lk (id, code, lang, label, creation_date)
VALUES (12, 'GAMARAT', 'ar', N'الجمرات', GETDATE());
INSERT INTO shc_portal.shc_housing_site_lk (id, code, lang, label, creation_date)
VALUES (13, 'MUZDALIFA', 'en', 'Muzdalifah', GETDATE());
INSERT INTO shc_portal.shc_housing_site_lk (id, code, lang, label, creation_date)
VALUES (14, 'MUZDALIFA', 'ar', N'مزدلفة', GETDATE());
INSERT INTO shc_portal.shc_housing_site_lk (id, code, lang, label, creation_date)
VALUES (15, 'ARAFAT', 'en', 'Arafat', GETDATE());
INSERT INTO shc_portal.shc_housing_site_lk (id, code, lang, label, creation_date)
VALUES (16, 'ARAFAT', 'ar', N'عرفات', GETDATE());
INSERT INTO shc_portal.shc_housing_site_lk (id, code, lang, label, creation_date)
VALUES (17, 'JABAL_ALRAHMA', 'en', 'Alrahma Mountain', GETDATE());
INSERT INTO shc_portal.shc_housing_site_lk (id, code, lang, label, creation_date)
VALUES (18, 'JABAL_ALRAHMA', 'ar', N'جبل الرحمة', GETDATE());
SET
IDENTITY_INSERT shc_portal.shc_housing_site_lk OFF;

SET
IDENTITY_INSERT shc_portal.shc_authority_lk ON;
GO
INSERT INTO shc_portal.shc_authority_lk (id, label_ar, label_en, code, parent_id)
VALUES (39, N'احصائيات الجوال', N'Mobile Tracking Dashboard', N'MOBILE_TRACKING_DASHBOARD', 1);
INSERT INTO shc_portal.shc_authority_lk (id, label_ar, label_en, code, parent_id)
VALUES (40, N'احصائيات العمليات', N'Statistical Dashboard', N'STATISTICAL_DASHBOARD', 1);

GO
SET IDENTITY_INSERT shc_portal.shc_authority_lk OFF;

INSERT INTO shc_portal.shc_role_authority(role_id, authority_id)
VALUES (1, 39);
INSERT INTO shc_portal.shc_role_authority(role_id, authority_id)
VALUES (1, 40);
GO

INSERT INTO shc_portal.shc_config (conf_key, conf_value)
VALUES ('dashboard.incident.company.chart.size', 15);
GO

INSERT INTO shc_portal.shc_config (conf_key, conf_value)
VALUES ('dashboard.refresh-interval', 10*60*1000); -- 10 minutes
GO

INSERT INTO shc_portal.shc_config (conf_key, conf_value)
VALUES ('dashboard.mobile.registered.applicant.by.company.size', 5);
GO

SET IDENTITY_INSERT shc_portal.shc_authority_lk ON;
GO
INSERT INTO shc_portal.shc_authority_lk (id, label_ar, label_en, code, parent_id)
VALUES (41, N'لوحة معلومات البلاغات', N'Incident Dashboards', N'INCIDENT_DASHBOARD', 1);

SET
IDENTITY_INSERT shc_portal.shc_authority_lk OFF;

INSERT INTO shc_portal.shc_role_authority(role_id, authority_id)
VALUES (1, 41);
GO

INSERT INTO shc_portal.shc_config (conf_key, conf_value)
VALUES ('dashboard.mobile.age.range', '0-18,18-30,30-40,40-60,60-200');

GO

SET IDENTITY_INSERT shc_portal.shc_area_layers_lk ON;

INSERT INTO shc_portal.shc_area_layers_lk (id, code, lang, label, creation_date)
VALUES (1, 'MAKKAH', 'en', 'Makkah', GETDATE());

INSERT INTO shc_portal.shc_area_layers_lk (id, code, lang, label, creation_date)
VALUES (2, 'MAKKAH', 'ar', N'مكة المكرمة', GETDATE());

INSERT INTO shc_portal.shc_area_layers_lk (id, code, lang, label, creation_date)
VALUES (3, 'MADINA', 'en', 'Madina', GETDATE());

INSERT INTO shc_portal.shc_area_layers_lk (id, code, lang, label, creation_date)
VALUES (4, 'MADINA', 'ar', N'المدينة المنورة', GETDATE());

INSERT INTO shc_portal.shc_area_layers_lk (id, code, lang, label, creation_date)
VALUES (5, 'MAKKAH_HOLY_MOSQUE', 'en', 'Holy Mosque in Mecca', GETDATE());

INSERT INTO shc_portal.shc_area_layers_lk (id, code, lang, label, creation_date)
VALUES (6, 'MAKKAH_HOLY_MOSQUE', 'ar', N'المسجد الحرام في مكة', GETDATE());

INSERT INTO shc_portal.shc_area_layers_lk (id, code, lang, label, creation_date)
VALUES (7, 'MADINA_HOLY_MOSQUE', 'en', 'Holy Mosque in Madina', GETDATE());

INSERT INTO shc_portal.shc_area_layers_lk (id, code, lang, label, creation_date)
VALUES (8, 'MADINA_HOLY_MOSQUE', 'ar', N'المسجد الحرام في المدينة', GETDATE());

INSERT INTO shc_portal.shc_area_layers_lk (id, code, lang, label, creation_date)
VALUES (9, 'MENA', 'ar', N'منى', GETDATE());

INSERT INTO shc_portal.shc_area_layers_lk (id, code, lang, label, creation_date)
VALUES (10, 'MENA', 'en', N'Mena', GETDATE());

INSERT INTO shc_portal.shc_area_layers_lk (id, code, lang, label, creation_date)
VALUES (11, 'GAMARAT', 'ar', N'الجمرات', GETDATE());

INSERT INTO shc_portal.shc_area_layers_lk (id, code, lang, label, creation_date)
VALUES (12, 'GAMARAT', 'en', N'Gamarat', GETDATE());

INSERT INTO shc_portal.shc_area_layers_lk (id, code, lang, label, creation_date)
VALUES (13, 'MUZDALIFA', 'ar', N'مزدلفة', GETDATE());

INSERT INTO shc_portal.shc_area_layers_lk (id, code, lang, label, creation_date)
VALUES (14, 'MUZDALIFA', 'en', N'Muzdalifah', GETDATE());

INSERT INTO shc_portal.shc_area_layers_lk (id, code, lang, label, creation_date)
VALUES (15, 'ARAFAT', 'ar', N'عرفات', GETDATE());

INSERT INTO shc_portal.shc_area_layers_lk (id, code, lang, label, creation_date)
VALUES (16, 'ARAFAT', 'en', N'Arafat', GETDATE());

INSERT INTO shc_portal.shc_area_layers_lk (id, code, lang, label, creation_date)
VALUES (17, 'JABAL_ALRAHMA', 'ar', N'جبل الرحمة', GETDATE());

INSERT INTO shc_portal.shc_area_layers_lk (id, code, lang, label, creation_date)
VALUES (18, 'JABAL_ALRAHMA', 'en', N'Alrahma Mountain', GETDATE());

SET
IDENTITY_INSERT shc_portal.shc_area_layers_lk OFF;

GO

INSERT INTO shc_portal.shc_chat_message_type_lk (code) VALUES ('TEXT');
INSERT INTO shc_portal.shc_chat_message_type_lk (code)
VALUES ('PHOTO');
INSERT INTO shc_portal.shc_chat_message_type_lk (code)
VALUES ('VIDEO');
INSERT INTO shc_portal.shc_chat_message_type_lk (code)
VALUES ('AUDIO');
INSERT INTO shc_portal.shc_chat_message_type_lk (code)
VALUES ('LOCATION');
GO

Use shc_portal
SET IDENTITY_INSERT shc_portal.shc_survey_type_lk ON;
INSERT INTO shc_portal.shc_survey_type_lk(id, code)
VALUES (1, 'DAILY');
INSERT INTO shc_portal.shc_survey_type_lk(id, code)
VALUES (2, 'END_OF_RITUAL');
SET
IDENTITY_INSERT shc_portal.shc_survey_type_lk OFF;
Use
shc_portal
SET IDENTITY_INSERT shc_portal.shc_survey_question_lk ON;
INSERT INTO shc_portal.shc_survey_question_lk(id, code, survey_type_code, question_index, lang, label)
VALUES (1, 'FOOD_SATISFACTION_Q_DAILY', 'DAILY', 1, 'en', 'How satisfied are you with the food?');
INSERT INTO shc_portal.shc_survey_question_lk(id, code, survey_type_code, question_index, lang, label)
VALUES (2, 'FOOD_SATISFACTION_Q_DAILY', 'DAILY', 1, 'ar', N'مدى رضاك عن الطعام؟');
INSERT INTO shc_portal.shc_survey_question_lk(id, code, survey_type_code, question_index, lang, label)
VALUES (3, 'HOUSING_SATISFACTION_Q_DAILY', 'DAILY', 2, 'en', 'How satisfied are you with the housing?');
INSERT INTO shc_portal.shc_survey_question_lk(id, code, survey_type_code, question_index, lang, label)
VALUES (4, 'HOUSING_SATISFACTION_Q_DAILY', 'DAILY', 2, 'ar', N'مدى رضاك عن مستوى السكن؟');
INSERT INTO shc_portal.shc_survey_question_lk(id, code, survey_type_code, question_index, lang, label)
VALUES (5, 'TRANSPORTATION_SATISFACTION_Q_DAILY', 'DAILY', 3, 'en', 'How satisfied are you with the transportation?');
INSERT INTO shc_portal.shc_survey_question_lk(id, code, survey_type_code, question_index, lang, label)
VALUES (6, 'TRANSPORTATION_SATISFACTION_Q_DAILY', 'DAILY', 3, 'ar', N'مدى رضاك عن مستوى المواصلات؟');
INSERT INTO shc_portal.shc_survey_question_lk(id, code, survey_type_code, question_index, lang, label)
VALUES (7, 'CLEANLINESS_SATISFACTION_Q_DAILY', 'DAILY', 4, 'en', 'How satisfied are you with the cleanliness?');
INSERT INTO shc_portal.shc_survey_question_lk(id, code, survey_type_code, question_index, lang, label)
VALUES (8, 'CLEANLINESS_SATISFACTION_Q_DAILY', 'DAILY', 4, 'ar', N'مدى رضاك عن مستوى النظافة؟');
INSERT INTO shc_portal.shc_survey_question_lk(id, code, survey_type_code, question_index, lang, label)
VALUES (9, 'ORGANIZATION_SATISFACTION_Q_DAILY', 'DAILY', 5, 'en',
        'How satisfied are you with the ritual organization?');
INSERT INTO shc_portal.shc_survey_question_lk(id, code, survey_type_code, question_index, lang, label)
VALUES (10, 'ORGANIZATION_SATISFACTION_Q_DAILY', 'DAILY', 5, 'ar', N'مدى رضاك عن مستوى تنظيم الشعيرة؟');

INSERT INTO shc_portal.shc_survey_question_lk(id, code, survey_type_code, question_index, lang, label)
VALUES (11, 'FOOD_SATISFACTION_Q_END_OF_RITUAL', 'END_OF_RITUAL', 1, 'en', 'How satisfied are you with the food?');
INSERT INTO shc_portal.shc_survey_question_lk(id, code, survey_type_code, question_index, lang, label)
VALUES (12, 'FOOD_SATISFACTION_Q_END_OF_RITUAL', 'END_OF_RITUAL', 1, 'ar', N'مدى رضاك عن الطعام؟');
INSERT INTO shc_portal.shc_survey_question_lk(id, code, survey_type_code, question_index, lang, label)
VALUES (13, 'HOUSING_SATISFACTION_Q_END_OF_RITUAL', 'END_OF_RITUAL', 2, 'en',
        'How satisfied are you with the housing?');
INSERT INTO shc_portal.shc_survey_question_lk(id, code, survey_type_code, question_index, lang, label)
VALUES (14, 'HOUSING_SATISFACTION_Q_END_OF_RITUAL', 'END_OF_RITUAL', 2, 'ar', N'مدى رضاك عن مستوى السكن؟');
INSERT INTO shc_portal.shc_survey_question_lk(id, code, survey_type_code, question_index, lang, label)
VALUES (15, 'TRANSPORTATION_SATISFACTION_Q_END_OF_RITUAL', 'END_OF_RITUAL', 3, 'en',
        'How satisfied are you with the transportation?');
INSERT INTO shc_portal.shc_survey_question_lk(id, code, survey_type_code, question_index, lang, label)
VALUES (16, 'TRANSPORTATION_SATISFACTION_Q_END_OF_RITUAL', 'END_OF_RITUAL', 3, 'ar', N'مدى رضاك عن مستوى المواصلات؟');
INSERT INTO shc_portal.shc_survey_question_lk(id, code, survey_type_code, question_index, lang, label)
VALUES (17, 'CLEANLINESS_SATISFACTION_Q_END_OF_RITUAL', 'END_OF_RITUAL', 4, 'en',
        'How satisfied are you with the cleanliness?');
INSERT INTO shc_portal.shc_survey_question_lk(id, code, survey_type_code, question_index, lang, label)
VALUES (18, 'CLEANLINESS_SATISFACTION_Q_END_OF_RITUAL', 'END_OF_RITUAL', 4, 'ar', N'مدى رضاك عن مستوى النظافة؟');
INSERT INTO shc_portal.shc_survey_question_lk(id, code, survey_type_code, question_index, lang, label)
VALUES (19, 'ORGANIZATION_SATISFACTION_Q_END_OF_RITUAL', 'END_OF_RITUAL', 5, 'en',
        'How satisfied are you with the ritual organization?');
INSERT INTO shc_portal.shc_survey_question_lk(id, code, survey_type_code, question_index, lang, label)
VALUES (20, 'ORGANIZATION_SATISFACTION_Q_END_OF_RITUAL', 'END_OF_RITUAL', 5, 'ar', N'مدى رضاك عن مستوى تنظيم الشعيرة؟');

SET
IDENTITY_INSERT shc_portal.shc_survey_question_lk OFF;

GO

SET IDENTITY_INSERT shc_portal.shc_area_layers ON;
INSERT INTO shc_portal.shc_area_layers (id, area_code, layer, creation_date)
VALUES (1, 'MAKKAH',
        '21.50546,39.8281-21.59392,39.78535-21.60956,39.77767-21.6035,39.74939-21.59296,39.74377-21.58179,39.74227-21.47644,39.78222-21.41481,39.69034-21.4038,39.69083-21.39279,39.69132-21.35031,39.69368-21.31249,39.81205-21.29599,39.83717-21.28206,39.86505-21.28938,39.95787-21.38891,40.02534-21.46949,40.04259-21.51136,40.00521-21.52047,39.95357-21.5196,39.90886-21.4625,39.87102',
        GETDATE());

INSERT INTO shc_portal.shc_area_layers (id, area_code, layer, creation_date)
VALUES (2, 'MADINA',
        '24.65638,39.60837-24.6542,39.50417-24.65575,39.43499-24.60383,39.36628-24.44879,39.40126-24.38041,39.41491-24.34565,39.45212-24.31277,39.49276-24.2855,39.59314-24.31644,39.69695-24.33822,39.76707-24.38064,39.81316-24.47758,39.86543-24.55413,39.81261-24.61815,39.77008-24.6535,39.70879',
        GETDATE());

INSERT INTO shc_portal.shc_area_layers (id, area_code, layer, creation_date, parent_layer_id)
VALUES (3, 'MAKKAH_HOLY_MOSQUE',
        '21.42675,39.82925-21.42609,39.82956-21.42523,39.83007-21.42407,39.82972-21.42056,39.82759-21.4206,39.82512-21.42112,39.82274-21.42295,39.82098-21.42409,39.8205-21.42606,39.81962-21.42773,39.82098-21.42901,39.82312-21.429,39.82655-21.42843,39.8287',
        GETDATE(), 1);

INSERT INTO shc_portal.shc_area_layers (id, area_code, layer, creation_date, parent_layer_id)
VALUES (4, 'MADINA_HOLY_MOSQUE',
        '24.46411,39.61125-24.46364,39.60567-24.46422,39.60315-24.46809,39.60191-24.47297,39.60382-24.47567,39.61069-24.47243,39.61833-24.46645,39.62004-24.46536,39.61485',
        GETDATE(), 1);

INSERT INTO shc_portal.shc_area_layers (id, area_code, layer, creation_date, parent_layer_id)
VALUES (5, 'MENA',
        '21.42242,39.8673-21.42619,39.86975-21.42472,39.87515-21.42128,39.8831-21.42009,39.88702-21.41629,39.89009-21.42043,39.89287-21.4256,39.89142-21.42845,39.89511-21.42846,39.89836-21.42795,39.90077-21.42496,39.9064-21.42419,39.90608-21.41982,39.9042-21.41059,39.9095-21.40814,39.9063-21.40521,39.9033-21.40336,39.90081-21.40014,39.89742-21.40279,39.89511-21.40544,39.88825-21.41018,39.88374-21.41253,39.87923-21.41636,39.87493-21.41723,39.8745',
        GETDATE(), 1);

INSERT INTO shc_portal.shc_area_layers (id, area_code, layer, creation_date, parent_layer_id)
VALUES (6, 'GAMARAT',
        '21.422382316339643,39.86802589710366-21.423625296397248,39.86919873623091-21.423482522198753,39.87020016040881-21.422323526480284,39.87220300876459-21.42157605334794,39.87361943755675-21.42101334633092,39.87468401461073-21.420568218348727,39.87520728129827-21.419703154427634,39.8752794560138-21.41947638925121,39.875080975546105-21.419207630067678,39.8743231410331-21.420433839823335,39.8726721444155-21.421382885511864,39.87053396846811-21.421777619513183,39.869252867267555',
        GETDATE(), 5);

INSERT INTO shc_portal.shc_area_layers (id, area_code, layer, creation_date, parent_layer_id)
VALUES (7, 'MUZDALIFA',
        '21.40033252004347,39.893674663505756-21.40472766778344,39.9008844410646-21.40792405586115,39.90809421862342-21.420069692913145,39.90457516052923-21.42486374533796,39.90706425040073-21.414316622280634,39.910325816439254-21.404008470832448,39.91178493808806-21.392820507112802,39.91890888496167-21.39114223872465,39.92980938198514-21.3890643559455,39.92946605924424-21.38666676221655,39.92320041922287-21.377875249069056,39.92199878962974-21.374838058155746,39.919595530443466-21.379553669642224,39.91238575288463-21.379553669642224,39.90878086410522-21.38203130292791,39.906892589030285-21.381791533798044,39.90139942517594-21.391621745943407,39.89796619776697',
        GETDATE(), 1);

INSERT INTO shc_portal.shc_area_layers (id, area_code, layer, creation_date, parent_layer_id)
VALUES (8, 'ARAFAT',
        '21.37984,39.98245-21.38072,39.98691-21.3804,39.99095-21.36809,39.98931-21.36369,39.98914-21.35858,39.99584-21.3525,39.99979-21.34427,40.0009-21.33324,39.99155-21.33184,39.98524-21.33188,39.97807-21.33372,39.97185-21.33715,39.96623-21.34571,39.95249-21.35027,39.95867-21.35302,39.96447-21.3565,39.9688-21.35938,39.97228-21.36274,39.97489-21.36825,39.97695-21.37688,39.98065',
        GETDATE(), 1);

INSERT INTO shc_portal.shc_area_layers (id, area_code, layer, creation_date, parent_layer_id)
VALUES (9, 'JABAL_ALRAHMA',
        '21.35569,39.98249-21.35612,39.98286-21.35637,39.9838-21.3562,39.98465-21.3559,39.98516-21.35543,39.98551-21.35493,39.98567-21.3543,39.98569-21.35373,39.98534-21.35334,39.98481-21.3532,39.98439-21.35308,39.98397-21.35318,39.98311-21.35352,39.98265-21.35392,39.98222-21.35482,39.98202-21.35536,39.9822',
        GETDATE(), 8);


SET
IDENTITY_INSERT shc_portal.shc_area_layers OFF;

Use
shc_portal
Go
SET
IDENTITY_INSERT shc_portal.shc_user ON;
insert into shc_portal.shc_user (id, nin, gender, mobile_number, date_of_birth_gregorian, password_hash, first_name,
                                 family_name, number_of_tries, activated, deleted, creation_date)
values (3, 1234567891, 'M', 512345678, convert(date, '01/01/1970', 103),
        '$2a$10$A81/FuMFJWcxaJhUcL8isuVeKKa.hk7GVzTVTyf7xe/XoMVWuKckK', 'HUIC', 'User', 0, 'true', 'false',
        current_timestamp);
SET
IDENTITY_INSERT shc_portal.shc_user OFF;
GO

SET IDENTITY_INSERT shc_portal.shc_role ON;
INSERT INTO shc_portal.shc_role(id, label_ar, label_en, deleted, activated)
VALUES (14, N'مستخدم خارجي', 'HUIC User', 0, 1);
SET
IDENTITY_INSERT shc_portal.shc_role OFF;
GO

INSERT INTO shc_portal.shc_user_role(user_id, role_id, is_main_role) VALUES (3, 14, 1);
GO

SET IDENTITY_INSERT shc_portal.shc_authority_lk ON;
INSERT INTO shc_portal.shc_authority_lk(id, label_ar, label_en, code, parent_id)
VALUES (42, N'طلب خدمة ربط', 'HUIC Integration Web Service Call', 'HUIC_INTEGRATION_WEB_SERVICE_CALL', NULL);
SET
IDENTITY_INSERT shc_portal.shc_authority_lk OFF;
GO
INSERT INTO shc_portal.shc_role_authority(role_id, authority_id) VALUES (14, 42);
GO

SET IDENTITY_INSERT shc_portal.shc_data_request_status_lk ON;
INSERT INTO shc_portal.shc_data_request_status_lk(id, label_ar, label_en)
VALUES (7, N'خطأ', 'Failed');
SET
IDENTITY_INSERT shc_portal.shc_data_request_status_lk OFF;
GO

UPDATE shc_portal.shc_portal.shc_notification_category_lk
SET sample = 'Make sure to carry your Hajj card when performing the rituals'
WHERE code = 'GENERAL'
  and lang = 'en';
UPDATE shc_portal.shc_portal.shc_notification_category_lk
SET sample = 'If your temperature rises above 38 degrees, go to the nearest health point directly'
WHERE code = 'HEALTH'
  and lang = 'en';
UPDATE shc_portal.shc_portal.shc_notification_category_lk
SET sample = 'Our Lord, accept from us, You are the Hearer, the Knower'
WHERE code = 'RELIGIOUS'
  and lang = 'en';
UPDATE shc_portal.shc_portal.shc_notification_category_lk
SET sample = 'Tawaf al-Ifadah is one of the pillars of Hajj that can only be done by performing it. And the evidence for that is the Almighty’s saying: (And let them circumambulate the Ancient House)'
WHERE code = 'RITUAL'
  and lang = 'en';
UPDATE shc_portal.shc_portal.shc_notification_category_lk
SET sample ='Avoid climbing mountains and high places and avoid crowding, docking and sleeping on the roads'
WHERE code = 'GENERAL_AWARENESS'
  and lang = 'en';
GO

update shc_portal.shc_config
set conf_value = '0 0 23 * * *'
where conf_key = 'scheduler.update.applicant.card.status.cron';
GO


INSERT INTO shc_portal.shc_portal.shc_config (conf_key, conf_value)
VALUES ('sftp.card.client.protocol', 'sftp');
INSERT INTO shc_portal.shc_portal.shc_config (conf_key, conf_value)
VALUES ('sftp.card.client.host', '127.0.0.1');
INSERT INTO shc_portal.shc_portal.shc_config (conf_key, conf_value)
VALUES ('sftp.card.client.port', '22');
INSERT INTO shc_portal.shc_portal.shc_config (conf_key, conf_value)
VALUES ('sftp.card.client.username', 'sftp-user');
INSERT INTO shc_portal.shc_portal.shc_config (conf_key, conf_value)
VALUES ('sftp.card.client.password', 'Aa123456');
INSERT INTO shc_portal.shc_portal.shc_config (conf_key, conf_value)
VALUES ('sftp.card.client.root-folder', '/data/smart-hajj/applicant-cards/');
INSERT INTO shc_portal.shc_portal.shc_config (conf_key, conf_value)
VALUES ('sftp.card.client.session-strict-host-key-checking', 'no');
INSERT INTO shc_portal.shc_portal.shc_config (conf_key, conf_value)
VALUES ('sftp.card.client.session-connect-timeout', '15000');
INSERT INTO shc_portal.shc_portal.shc_config (conf_key, conf_value)
VALUES ('sftp.card.client.channel-connected-timeout', '15000');
GO

SET IDENTITY_INSERT shc_portal.shc_collection_status_lk ON;
INSERT INTO shc_portal.shc_collection_status_lk (id, code, lang, label)
VALUES (1, 'NEW', 'ar', N'جديد');
INSERT INTO shc_portal.shc_collection_status_lk (id, code, lang, label)
VALUES (2, 'NEW', 'en', 'NEW');
INSERT INTO shc_portal.shc_collection_status_lk (id, code, lang, label)
VALUES (3, 'GENERATING', 'ar', N'جاري إعداد البطاقات');
INSERT INTO shc_portal.shc_collection_status_lk (id, code, lang, label)
VALUES (4, 'GENERATING', 'en', 'Generating Cards ');
INSERT INTO shc_portal.shc_collection_status_lk (id, code, lang, label)
VALUES (5, 'FAILED', 'ar', N'فشل في إعداد البطاقات');
INSERT INTO shc_portal.shc_collection_status_lk (id, code, lang, label)
VALUES (6, 'FAILED', 'en', 'Fail to generate');
INSERT INTO shc_portal.shc_collection_status_lk (id, code, lang, label)
VALUES (7, 'READY', 'ar', N'جاهزة للطباعة');
INSERT INTO shc_portal.shc_collection_status_lk (id, code, lang, label)
VALUES (8, 'READY', 'en', 'Ready');
SET
IDENTITY_INSERT shc_portal.shc_collection_status_lk OFF;
GO

SET IDENTITY_INSERT shc_portal.shc_supplication_lk ON;
INSERT INTO shc_portal.shc_supplication_lk(id, code, lang, label, type, counter)
VALUES (1, 'MORNING_SUPPLICATION_1', 'en',
        'Allah! There is no ilah (deity worthy of worship) but He The Living, The Eternal One. Neither slumber, nor sleep overtakes Him. To Him belongs whatever is in the Heavens and on the earth. Who is he that can intercede with Him but by His permission? He knows what is before and what is behind them. They encompass nothing of His knowledge which He will. His Throne extends over the Heavens and the Earth, and the preservation of both does not weary Him. He is the Exalted The Immense One.',
        'Morning Supplications', 3);
INSERT INTO shc_portal.shc_supplication_lk(id, code, lang, label, type, counter)
VALUES (2, 'MORNING_SUPPLICATION_1', 'ar',
        N' اللّهُ لاَ إِلَـهَ إِلاَّ هُوَ الْحَيُّ الْقَيُّومُ لاَ تَأْخُذُهُ سِنَةٌ وَلاَ نَوْمٌ لَّهُ مَا فِي السَّمَاوَاتِ وَمَا فِي الأَرْضِ مَن ذَا الَّذِي يَشْفَعُ عِنْدَهُ إِلاَّ بِإِذْنِهِ يَعْلَمُ مَا بَيْنَ أَيْدِيهِمْ وَمَا خَلْفَهُمْ وَلاَ يُحِيطُونَ بِشَيْءٍ مِّنْ عِلْمِهِ إِلاَّ بِمَا شَاء وَسِعَ كُرْسِيُّهُ السَّمَاوَاتِ وَالأَرْضَ وَلاَ يَؤُودُهُ حِفْظُهُمَا وَهُوَ الْعَلِيُّ الْعَظِيمُ.',
        N'اذكار الصباح', 3);
INSERT INTO shc_portal.shc_supplication_lk(id, code, lang, label, type, counter)
VALUES (3, 'MORNING_SUPPLICATION_2', 'en', 'O Allah, I seek Your forgiveness and Your protection in this world and the next?O Allah, I seek Your forgiveness and Your protection in my religion, in my worldly affairs, in my family and in my wealth.
O Allah, conceal my secrets and preserve me from anguish,O Allah, guard me from what is in front of me and behind me, from my left, and from my right, and from above me. I seek refuge in Your Greatness from being struck down from beneath me.',
        'Morning Supplications', 1);
INSERT INTO shc_portal.shc_supplication_lk(id, code, lang, label, type, counter)
VALUES (4, 'MORNING_SUPPLICATION_2', 'ar', N'
اللَّهُمَّ إِنِّي أَسْأَلُكَ الْعَفْوَ وَالْعَافِيَةَ فِي الدُّنْيَا اللَّهُمَّ إِنِّي أَسْأَلُكَ الْعَفْوَ وَالْعَافِيَةَ فِي دِينِي وَدُنْيَايَ وَأَهْلِي، وَمَالِي، وَالْآخِرَةِ اللَّهُمَّ اسْتُرْ عَوْرَاتِي، وَآمِنْ رَوْعَاتِي اللَّهُمَّ احْفَظْنِي مِنْ بَيْنِ يَدَيَّ، وَمِنْ خَلْفِي، وَعَنْ يَمِينِي، وَعَنْ شِمَالِي، وَمِنْ فَوْقِي، وَأَعُوذُ بِعَظَمَتِكَ أَنْ أُغْتَالَ مِنْ تَحْتِي',
        N'اذكار الصباح', 1);
INSERT INTO shc_portal.shc_supplication_lk(id, code, lang, label, type, counter)
VALUES (5, 'MORNING_SUPPLICATION_3', 'en',
        'We have reached the morning and at this very time unto Allah belongs all sovereignty, and all praise is for Allah. None has the right to be worshipped except Allah, alone, without partner, to Him belongs all sovereignty and praise and He is over all things omnipotent. My Lord, I ask You for the good of this day and the good of what follows it and I take refuge in You from the evil of this day and the evil of what follows it. My Lord, I take refuge in You from laziness and senility. My Lord, I take refuge in You from torment in the Fire and punishment in the grave.',
        'Morning Supplications', 1);
INSERT INTO shc_portal.shc_supplication_lk(id, code, lang, label, type, counter)
VALUES (6, 'MORNING_SUPPLICATION_3', 'ar',
        N'أَصْـبَحْنا وَأَصْـبَحَ المُـلْكُ لله وَالحَمدُ لله ، لا إلهَ إلاّ اللّهُ وَحدَهُ لا شَريكَ لهُ، لهُ المُـلكُ ولهُ الحَمْـد، وهُوَ على كلّ شَيءٍ قدير ، رَبِّ أسْـأَلُـكَ خَـيرَ ما في هـذا اليوم وَخَـيرَ ما بَعْـدَه ، وَأَعـوذُ بِكَ مِنْ شَـرِّ ما في هـذا اليوم وَشَرِّ ما بَعْـدَه، رَبِّ أَعـوذُبِكَ مِنَ الْكَسَـلِ وَسـوءِ الْكِـبَر ، رَبِّ أَعـوذُ بِكَ مِنْ عَـذابٍ في النّـارِ وَعَـذابٍ في القَـبْر',
        N'اذكار الصباح', 1);

INSERT INTO shc_portal.shc_supplication_lk(id, code, lang, label, type, counter)
VALUES (7, 'EVENING_SUPPLICATION_1', 'en',
        'We have reached the evening and at this very time unto Allah belongs all sovereignty, and all praise is for Allah. None has the right to be worshipped except Allah, alone, without partner, to Him belongs all sovereignty and praise and He is over all things omnipotent. My Lord, I ask You for the good of this night and the good of what follows it and I take refuge in You from the evil of this night and the evil of what follows it. My Lord, I take refuge in You from laziness and senility. My Lord, I take refuge in You from torment in the Fire and punishment in the grave.',
        'Evening Supplications', 3);
INSERT INTO shc_portal.shc_supplication_lk(id, code, lang, label, type, counter)
VALUES (8, 'EVENING_SUPPLICATION_1', 'ar',
        N'أَمْسَيْـنا وَأَمْسـى المـلكُ لله وَالحَمدُ لله ، لا إلهَ إلاّ اللّهُ وَحدَهُ لا شَريكَ لهُ، لهُ المُـلكُ ولهُ الحَمْـد، وهُوَ على كلّ شَيءٍ قدير ، رَبِّ أسْـأَلُـكَ خَـيرَ ما في هـذهِ اللَّـيْلَةِ وَخَـيرَ ما بَعْـدَهـا ، وَأَعـوذُ بِكَ مِنْ شَـرِّ هـذهِ اللَّـيْلةِ وَشَرِّ ما بَعْـدَهـا ، رَبِّ أَعـوذُبِكَ مِنَ الْكَسَـلِ وَسـوءِ الْكِـبَر ، رَبِّ أَعـوذُبِكَ مِنْ عَـذابٍ في النّـارِ وَعَـذابٍ في القَـبْر',
        N'اذكار المساء', 3);
INSERT INTO shc_portal.shc_supplication_lk(id, code, lang, label, type, counter)
VALUES (9, 'EVENING_SUPPLICATION_2', 'en',
        'Allah! There is no ilah (deity worthy of worship) but He The Living, The Eternal One. Neither slumber, nor sleep overtakes Him. To Him belonges whatever is in the Heavens and on the earth. Who is he that can intercede with Him but by His permission? He knows what is before and what is behind them. They encompass nothing of His knowledge which He will. His Throne extends over the Heavens and the Earth, and the preservation of both does not weary Him. He is the Exalted The Immense One.',
        'Evening Supplications', 3);
INSERT INTO shc_portal.shc_supplication_lk(id, code, lang, label, type, counter)
VALUES (10, 'EVENING_SUPPLICATION_2', 'ar',
        N'االلَّهُ لاَ إِلَـهَ إِلاَّ هُوَ الْحَيُّ الْقَيُّومُ لاَ تَأْخُذُهُ سِنَةٌ وَلاَ نَوْمٌ لَّهُ مَا فِي السَّمَاوَاتِ وَمَا فِي الْأَرْضِ مَن ذَا الَّذِي يَشْفَعُ عِندَهُ إِلاَّ بِإِذْنِهِ يَعْلَمُ مَا بَيْنَ أَيْدِيهِمْ وَمَا خَلْفَهُمْ وَلاَ يُحِيطُونَ بِشَيْءٍ مِّنْ عِلْمِهِ إِلاَّ بِمَا شَاءَ وَسِعَ كُرْسِيُّهُ السَّمَاوَاتِ وَالْأَرْضَ وَلاَ يَئُودُهُ حِفْظُهُمَا وَهُوَ الْعَلِيُّ الْعَظِيمُ',
        N'اذكار المساء', 3);
INSERT INTO shc_portal.shc_supplication_lk(id, code, lang, label, type, counter)
VALUES (11, 'EVENING_SUPPLICATION_3', 'en',
        'He is Allah, who is One. Allah, the Perfect Self-Sufficient Master. He neither begets nor was He begotten, nor is there to Him any equivalent.',
        'Evening Supplications', 3);
INSERT INTO shc_portal.shc_supplication_lk(id, code, lang, label, type, counter)
VALUES (12, 'EVENING_SUPPLICATION_3', 'ar',
        N'قُلْ هُوَ اللهُ أَحَدٌ اللهُ الصَّمَدُ  لَمْ يَلِدْ وَلَمْ يُولَدْ وَلَمْ يَكُن لَّهُ كُفُوًا أَحَد',
        N'اذكار المساء', 3);

SET
IDENTITY_INSERT shc_portal.shc_supplication_lk OFF;
GO
SET IDENTITY_INSERT shc_portal.shc_suggested_supplication_lk ON;
INSERT INTO shc_portal.shc_suggested_supplication_lk (id, code, lang, label)
VALUES (1, 'SUGGESTEDSUPPLICATION1', 'ar', N'سبحان الله وبحمده سبحان الله العظيم');
INSERT INTO shc_portal.shc_suggested_supplication_lk (id, code, lang, label)
VALUES (2, 'SUGGESTEDSUPPLICATION1', 'en', 'Glory be to Allah');
INSERT INTO shc_portal.shc_suggested_supplication_lk(id, code, lang, label)
VALUES (3, 'SUGGESTEDSUPPLICATION2', 'ar', N'الحمدلله');
INSERT INTO shc_portal.shc_suggested_supplication_lk (id, code, lang, label)
VALUES (4, 'SUGGESTEDSUPPLICATION2', 'en', 'Praise be to Allah');
INSERT INTO shc_portal.shc_suggested_supplication_lk (id, code, lang, label)
VALUES (5, 'SUGGESTEDSUPPLICATION3', 'ar', N'استغفر الله');
INSERT INTO shc_portal.shc_suggested_supplication_lk(id, code, lang, label)
VALUES (6, 'SUGGESTEDSUPPLICATION3', 'en', 'I seek forgiveness from Allah');
INSERT INTO shc_portal.shc_suggested_supplication_lk (id, code, lang, label)
VALUES (7, 'SUGGESTEDSUPPLICATION4', 'ar', N'لا حول ولا قوة الا بالله');
INSERT INTO shc_portal.shc_suggested_supplication_lk(id, code, lang, label)
VALUES (8, 'SUGGESTEDSUPPLICATION4', 'en', 'There is no power or strength except with Allah');
SET
IDENTITY_INSERT shc_portal.shc_suggested_supplication_lk OFF
GO

INSERT INTO shc_portal.shc_portal.shc_config (conf_key, conf_value)
VALUES ('data.generation.max-retries', '3');

INSERT INTO shc_portal.shc_portal.shc_config (conf_key, conf_value)
VALUES ('data.generation.retry.period', '2000');

update shc_portal.shc_portal.shc_config
set conf_key ='card.generation.max-retries'
where conf_key = 'data.generation.max-retries';

update shc_portal.shc_portal.shc_config
set conf_key ='card.generation.retry.period'
where conf_key = 'data.generation.retry.period';


INSERT INTO shc_portal.shc_print_request_status_lk (code, lang, label) VALUES ('PRINTED', 'en', 'Printed')
INSERT INTO shc_portal.shc_print_request_status_lk (code, lang, label) VALUES ('PRINTED', 'ar', N'تم الطباعة')
INSERT INTO shc_portal.shc_print_request_status_lk (code, lang, label) VALUES ('DISTRIBUTED', 'en', 'Distributed')
INSERT INTO shc_portal.shc_print_request_status_lk (code, lang, label) VALUES ('DISTRIBUTED', 'ar', N'تم التوزيع')
delete from shc_portal.shc_portal.shc_print_request_status_lk where code = 'UNDER_PROCESSING' or code = 'PROCESSED'
GO
INSERT
INTO shc_portal.shc_config (conf_key, conf_value)
VALUES ('daily.survey.activation.hour', '17');
GO

INSERT INTO shc_portal.shc_config (conf_key, conf_value) VALUES ('scheduler.notification.template.processing.cron', '0 0/4 * * * *');
GO

INSERT INTO shc_portal.shc_portal.shc_config (conf_key, conf_value)
VALUES ('activate.printed.card', 'true');
GO
  SET IDENTITY_INSERT shc_portal.shc_data_segment ON;
insert into shc_portal.shc_data_segment (id, template_file_name, label_ar, label_en)
values (11, 'staff-data.xlsx', N'بيانات العاملين ',
        'Staff Data');
insert into shc_portal.shc_data_segment (id, template_file_name, label_ar, label_en)
values (12, 'main-group-data.xlsx', N'بيانات المجموعات الرئيسية',
        'Main Group Data');

insert into shc_portal.shc_data_segment (id, template_file_name, label_ar, label_en)
values (13, 'group-data.xlsx', N'بيانات المجموعات ',
        'Group Data');


SET
IDENTITY_INSERT shc_portal.shc_data_segment OFF;
GO
update shc_portal.shc_portal.shc_incident_status_lk
set label = N'جاري دراسة البلاغ'
where id = 1
update shc_portal.shc_portal.shc_incident_status_lk
set label = N'تم حل البلاغ'
where id = 3
update shc_portal.shc_portal.shc_incident_status_lk
set label = N'تم إغلاق البلاغ'
where id = 5
    GO

SET IDENTITY_INSERT shc_portal.shc_company_type_lk
ON;
INSERT INTO shc_portal.shc_company_type_lk (id, code, lang, label)
VALUES (1, 'ESTABLISHMENT', 'ar', N'مؤسسة طوافة');
INSERT INTO shc_portal.shc_company_type_lk(id, code, lang, label)
VALUES (2, 'ESTABLISHMENT', 'en', 'Establishment');
INSERT INTO shc_portal.shc_company_type_lk (id, code, lang, label)
VALUES (3, 'MISSION', 'ar', N'بعثة');
INSERT INTO shc_portal.shc_company_type_lk (id, code, lang, label)
VALUES (4, 'MISSION', 'en', 'Mission');
INSERT INTO shc_portal.shc_company_type_lk (id, code, lang, label)
VALUES (5, 'INTERNAL_HAJ_COMPANY', 'ar', N'شركة  حجاج داخل ');
INSERT INTO shc_portal.shc_company_type_lk (id, code, lang, label)
VALUES (6, 'INTERNAL_HAJ_COMPANY', 'en', 'Internal Haj Company  ');
INSERT INTO shc_portal.shc_company_type_lk (id, code, lang, label)
VALUES (7, 'EXTERNAL_HAJ_COMPANY', 'ar', N'شركة حجاج خارج ');
INSERT INTO shc_portal.shc_company_type_lk (id, code, lang, label)
VALUES (8, 'EXTERNAL_HAJ_COMPANY', 'en', 'External  Haj  Company ');
INSERT INTO shc_portal.shc_company_type_lk (id, code, lang, label)
VALUES (9, 'SERVICE_GROUP', 'ar', N'مكتب خدمة ميدانية');
INSERT INTO shc_portal.shc_company_type_lk (id, code, lang, label)
VALUES (10, 'SERVICE_GROUP', 'en', 'Service Group');
SET
IDENTITY_INSERT shc_portal.shc_company_type_lk OFF;
GO

delete
from shc_portal.shc_meal_type_lk
where id > 0;
go
SET IDENTITY_INSERT shc_portal.shc_meal_type_lk ON;
INSERT INTO shc_portal.shc_meal_type_lk (id, code, lang, label)
VALUES (1, 'FULL_BOARD', 'ar', N'وجبة الإفطار والغداء والعشاء');
INSERT INTO shc_portal.shc_meal_type_lk (id, code, lang, label)
VALUES (2, 'FULL_BOARD', 'en', 'Breakfast, lunch, and dinner buffets');
INSERT INTO shc_portal.shc_meal_type_lk (id, code, lang, label)
VALUES (3, 'BREAKFAST_DINNER', 'ar', N'وجبة الإفطار والعشاء');
INSERT INTO shc_portal.shc_meal_type_lk (id, code, lang, label)
VALUES (4, 'BREAKFAST_DINNER', 'en', 'Breakfast, and dinner buffets');
INSERT INTO shc_portal.shc_meal_type_lk (id, code, lang, label)
VALUES (5, 'BREAKFAST_LUNCH', 'ar', N'وجبة الإفطار والغداء');
INSERT INTO shc_portal.shc_meal_type_lk (id, code, lang, label)
VALUES (6, 'BREAKFAST_LUNCH', 'en', 'Breakfast, and lunch');
INSERT INTO shc_portal.shc_meal_type_lk (id, code, lang, label)
VALUES (7, 'ONLY_BREAKFAST', 'ar', N'وجبة الإفطار فقط');
INSERT INTO shc_portal.shc_meal_type_lk (id, code, lang, label)
VALUES (8, 'ONLY_BREAKFAST', 'en', 'Only Breakfast');
SET
IDENTITY_INSERT shc_portal.shc_meal_type_lk OFF;
GO

SET IDENTITY_INSERT shc_portal.shc_meal_time_type_lk ON;
INSERT INTO shc_portal.shc_meal_time_type_lk (id, code, lang, label)
VALUES (1, 'BREAKFAST', 'ar', N'الإفطار ');
INSERT INTO shc_portal.shc_meal_time_type_lk (id, code, lang, label)
VALUES (2, 'BREAKFAST', 'en', 'Breakfast');
INSERT INTO shc_portal.shc_meal_time_type_lk (id, code, lang, label)
VALUES (3, 'LUNCH', 'ar', N'الغداء');
INSERT INTO shc_portal.shc_meal_time_type_lk (id, code, lang, label)
VALUES (4, 'LUNCH', 'en', 'Lunch');
INSERT INTO shc_portal.shc_meal_time_type_lk (id, code, lang, label)
VALUES (5, 'DINNER', 'ar', N'العشاء');
INSERT INTO shc_portal.shc_meal_time_type_lk (id, code, lang, label)
VALUES (6, 'DINNER', 'en', 'Dinner');
SET
IDENTITY_INSERT shc_portal.shc_meal_time_type_lk OFF;
GO