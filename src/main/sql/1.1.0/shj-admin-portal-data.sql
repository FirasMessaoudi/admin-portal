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

SET IDENTITY_INSERT shc_portal.shc_survey_type_lk ON;
INSERT INTO shc_portal.shc_survey_type_lk(id, code)
VALUES (1, 'DAILY');
INSERT INTO shc_portal.shc_survey_type_lk(id, code)
VALUES (2, 'END_OF_RITUAL');
SET IDENTITY_INSERT shc_portal.shc_survey_type_lk OFF;
GO

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
INSERT INTO shc_portal.shc_company_type_lk (id, code, lang, label)
VALUES (11, 'GOVERNMENT_AGENCY', 'en', 'Emirate or ministry or other government agency');
INSERT INTO shc_portal.shc_company_type_lk (id, code, lang, label)
VALUES (12, 'GOVERNMENT_AGENCY', 'ar', N'إمارة أو وزارة أو هيئة أو جهة حكومية أخرى');
SET
IDENTITY_INSERT shc_portal.shc_company_type_lk OFF;
GO

delete from shc_portal.shc_meal_type_lk where id > 0;
GO

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
SET IDENTITY_INSERT shc_portal.shc_meal_type_lk OFF;
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

delete
from shc_portal.shc_company
where code in ('1', '2', '3', '4', '5', '6', '7')
    go

INSERT
INTO shc_portal.shc_company (code, label_ar, label_en, establishment_ref_code)
VALUES (1, N'شركة مطوفي حجاج دول جنوب شرق آسيا', 'Company of Pilgrims of South Est Asia countries', 1);
INSERT INTO shc_portal.shc_company (code, label_ar, label_en, establishment_ref_code)
VALUES (2, N'ششركة مطوفي حجاج دول جنوب آسيا', 'Company of Pilgrims of South Asia countries', 1);
INSERT INTO shc_portal.shc_company (code, label_ar, label_en, establishment_ref_code)
VALUES (3, N'شركة مطوفي حجاج الدول الأفريقية غير العربية', 'Company of Pilgrims of Africa non Arabic countries', 1);
INSERT INTO shc_portal.shc_company (code, label_ar, label_en, establishment_ref_code)
VALUES (4, N'شركة مطوفي حجاج الدولة العربية', 'Company of Pilgrims of Arabic countries', 1);
INSERT INTO shc_portal.shc_company (code, label_ar, label_en, establishment_ref_code)
VALUES (5, N'شركة مطوفي حجاج تركيا، وحجاج أوروبا وأمريكا وأستراليا',
        'Company of Pilgrims of Turkey Muslims of Europe America and Australia', 1);
INSERT INTO shc_portal.shc_company (code, label_ar, label_en, establishment_ref_code)
VALUES (6, N'شركة مطوفي حجاج إيران', 'Company of Pilgrims of Iran', 1);
INSERT INTO shc_portal.shc_company (code, label_ar, label_en, establishment_ref_code)
VALUES (7, N'شركة الأدلاء', 'Company Adela', 1);
go

delete
from shc_portal.shc_housing_type_lk
where id > 0;
go
SET IDENTITY_INSERT shc_portal.shc_housing_type_lk ON;
INSERT INTO shc_portal.shc_housing_type_lk (id, code, lang, label)
VALUES (1, 'CAMP', 'ar', N'مخيم');
INSERT INTO shc_portal.shc_housing_type_lk (id, code, lang, label)
VALUES (2, 'CAMP', 'en', 'Camp');
INSERT INTO shc_portal.shc_housing_type_lk (id, code, lang, label)
VALUES (3, 'HOTEL', 'ar', N'فندق');
INSERT INTO shc_portal.shc_housing_type_lk (id, code, lang, label)
VALUES (4, 'HOTEL', 'en', 'Hotel');
INSERT INTO shc_portal.shc_housing_type_lk (id, code, lang, label)
VALUES (5, 'BUILDING', 'ar', N'عمارة ');
INSERT INTO shc_portal.shc_housing_type_lk (id, code, lang, label)
VALUES (6, 'BUILDING', 'en', 'Building');
SET
IDENTITY_INSERT shc_portal.shc_housing_type_lk OFF;
GO

delete
from shc_portal.shc_housing_category_lk
where id > 0;
go
SET IDENTITY_INSERT shc_portal.shc_housing_category_lk ON;
INSERT INTO shc_portal.shc_housing_category_lk (id, code, lang, label)
VALUES (1, 'A', 'ar', N'أ');
INSERT INTO shc_portal.shc_housing_category_lk (id, code, lang, label)
VALUES (2, 'A', 'en', 'A');
INSERT INTO shc_portal.shc_housing_category_lk (id, code, lang, label)
VALUES (3, 'B', 'ar', N'ب');
INSERT INTO shc_portal.shc_housing_category_lk (id, code, lang, label)
VALUES (4, 'B', 'en', 'B');
INSERT INTO shc_portal.shc_housing_category_lk (id, code, lang, label)
VALUES (5, 'C', 'ar', N'ج ');
INSERT INTO shc_portal.shc_housing_category_lk (id, code, lang, label)
VALUES (6, 'C', 'en', 'C');
INSERT INTO shc_portal.shc_housing_category_lk (id, code, lang, label)
VALUES (7, 'D', 'ar', N'د ');
INSERT INTO shc_portal.shc_housing_category_lk (id, code, lang, label)
VALUES (8, 'D', 'en', 'D');
SET
IDENTITY_INSERT shc_portal.shc_housing_category_lk OFF;
GO

Delete from shc_portal.shc_company where code in ('1', '2', '3', '4', '5', '6', '7', '8', '9');
GO
INSERT INTO shc_portal.shc_company (code, label_ar, label_en, contact_number)
VALUES ('1_ESTABLISHMENT', N'شركة مطوفي حجاج دول جنوب شرق آسيا', 'South East Asia countries Tawafa Co.','0125459922');
INSERT INTO shc_portal.shc_company (code, label_ar, label_en, contact_number)
VALUES ('2_ESTABLISHMENT', N'شركة مطوفي حجاج دول جنوب آسيا', 'South Asia countries Tawafa Co.','0125344444');
INSERT INTO shc_portal.shc_company (code, label_ar, label_en, contact_number)
VALUES ('3_ESTABLISHMENT', N'شركة مطوفي حجاج الدول الأفريقية غير العربية', 'Africa non Arab countries Tawafa Co.','0125435160');
INSERT INTO shc_portal.shc_company (code, label_ar, label_en, contact_number)
VALUES ('4_ESTABLISHMENT', N'شركة مطوفي حجاج الدولة العربية', 'Arab countries Tawafa Co.','920003503');
INSERT INTO shc_portal.shc_company (code, label_ar, label_en, contact_number)
VALUES ('5_ESTABLISHMENT', N'شركة مطوفي حجاج تركيا وحجاج أوروبا وأمريكا وأستراليا', 'Turkey, Europe, America and Australia Tawafa Co.','920012013');
INSERT INTO shc_portal.shc_company (code, label_ar, label_en, contact_number)
VALUES ('6_ESTABLISHMENT', N'شركة مطوفي حجاج إيران', 'Iran Tawafa Co.','0125488466');
INSERT INTO shc_portal.shc_company (code, label_ar, label_en, contact_number)
VALUES ('7_ESTABLISHMENT', N'شركة الأدلاء', 'Company Adela','0148260088');
INSERT INTO shc_portal.shc_company (code, label_ar, label_en, contact_number)
VALUES ('8_ESTABLISHMENT', N'المجلس التنسيقي لحجاج الداخل', 'Coordination Council For Internal Pilgrims','0125582424');
INSERT INTO shc_portal.shc_company (code, label_ar, label_en, contact_number)
VALUES ('9_ESTABLISHMENT', N'شركة فلاي ناس', 'Flynas Company','920033540');
GO


SET IDENTITY_INSERT shc_portal.shc_country_lk ON;
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (1, 101, N'إماراتي', 'ar', '971', 'AE');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (2, 101, 'Emirati', 'en', '971', 'AE');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (3, 102, N'أردني', 'ar', '962', 'JO');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (4, 102, 'Jordanian', 'en', '962', 'JO');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (5, 103, N'بحريني', 'ar', '973', 'BH');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (6, 103, 'Bahraini', 'en', '973', 'BH');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (7, 104, N'سوري', 'ar', '963', 'SY');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (8, 104, 'Syrian', 'en', '963', 'SY');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (9, 105, N'عراقي', 'ar', '964', 'IQ');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (10, 105, 'Iraqi', 'en', '964', 'IQ');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (11, 106, N'عماني', 'ar', '968', 'OM');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (12, 106, 'Omani', 'en', '968', 'OM');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (13, 107, N'فلسطيني', 'ar', '970', 'PS');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (14, 107, 'Palestinian', 'en', '970', 'PS');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (15, 108, N'قطري', 'ar', '974', 'QA');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (16, 108, 'Qatari', 'en', '974', 'QA');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (17, 109, N'كويتي', 'ar', '965', 'KW');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (18, 109, 'Kuwaiti', 'en', '965', 'KW');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (19, 110, N'لبناني', 'ar', '961', 'LB');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (20, 110, 'Lebanese', 'en', '961', 'LB');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (21, 111, N'يمني', 'ar', '967', 'YE');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (22, 111, 'Yemen', 'en', '967', 'YE');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (23, 113, N'سعودي ', 'ar', '966', 'SA');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (24, 113, 'Saudi', 'en', '966', 'SA');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (25, 201, N'تونسي', 'ar', '216', 'TN');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (26, 201, 'Tunisian', 'en', '216', 'TN');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (27, 202, N'جزائري', 'ar', '213', 'DZ');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (28, 202, 'Algerian', 'en', '213', 'DZ');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (29, 203, N'جيبوتي', 'ar', '253', 'DJ');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (30, 203, 'Djiboutian', 'en', '253', 'DJ');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (31, 204, N'سوداني', 'ar', '249', 'SD');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (32, 204, 'Sudanese', 'en', '249', 'SD');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (33, 205, N'صومالي', 'ar', '252', 'SO');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (34, 205, 'Somalian ', 'en', '252', 'SO');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (35, 206, N'ليبي', 'ar', '218', 'LY');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (36, 206, 'Libyan', 'en', '218', 'LY');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (37, 207, N'مصري', 'ar', '20', 'EG');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (38, 207, 'Egyptian', 'en', '20', 'EG');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (39, 208, N'مغربي', 'ar', '212', 'MA');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (40, 208, 'Moroccan', 'en', '212', 'MA');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (41, 209, N'موريتاني', 'ar', '222', 'MR');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (42, 209, 'Mauritanian', 'en', '222', 'MR');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (43, 301, N'أفغانستاني', 'ar', '93', 'AF');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (44, 301, 'Afghan', 'en', '93', 'AF');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (45, 302, N'اندونيسي', 'ar', '62', 'ID');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (46, 302, 'Indonesian', 'en', '62', 'ID');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (47, 303, N'إيراني', 'ar', '98', 'IR');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (48, 303, 'Iranian', 'en', '98', 'IR');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (49, 304, N'باكستاني', 'ar', '92', 'PK');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (50, 304, 'Pakistani', 'en', '92', 'PK');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (51, 305, N'بنغلاديشي', 'ar', '880', 'BD');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (52, 305, 'Bangladeshi', 'en', '880', 'BD');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (53, 306, N'بروني', 'ar', '673', 'BN');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (54, 306, 'Brunein', 'en', '673', 'BN');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (55, 307, N'ميانماري', 'ar', '95', 'MM');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (56, 307, 'Myanmarian', 'en', '95', 'MM');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (57, 308, N'تايلندي', 'ar', '66', 'TH');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (58, 308, 'Thai', 'en', '66', 'TH');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (59, 309, N'تركي', 'ar', '90', 'TR');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (60, 309, 'Turkish', 'en', '90', 'TR');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (61, 310, N'جزر مالديف', 'ar', '960', 'MV');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (62, 310, 'Maldivian', 'en', '960', 'MV');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (63, 311, N'روسي إتحادي', 'ar', '7', 'RU');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (64, 311, 'Russian', 'en', '7', 'RU');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (65, 312, N'سنغافوري', 'ar', '65', 'SG');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (66, 312, 'Singaporean', 'en', '65', 'SG');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (67, 313, N'سريلانكي', 'ar', '94', 'LK');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (68, 313, 'Srilankan', 'en', '94', 'LK');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (69, 315, N'فلبيني', 'ar', '63', 'PH');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (70, 315, 'Philippino', 'en', '63', 'PH');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (71, 316, N'فيتنامي', 'ar', '84', 'VN');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (72, 316, 'Vietnamese', 'en', '84', 'VN');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (73, 317, N'كمبودي ', 'ar', '855', 'KH');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (74, 317, 'Cambodian', 'en', '855', 'KH');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (75, 318, N'كوري جنوبي', 'ar', '82', 'KR');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (76, 318, 'Korean', 'en', '82', 'KR');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (77, 319, N'ماليزي', 'ar', '60', 'MY');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (78, 319, 'Malaysian', 'en', '60', 'MY');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (79, 320, N'نيبالي ', 'ar', '977', 'NP');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (80, 320, 'Nepalese', 'en', '977', 'NP');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (81, 321, N'هندي', 'ar', '91', 'IN');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (82, 321, 'Indian', 'en', '91', 'IN');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (83, 322, N'هونج كونج', 'ar', '852', 'HK');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (84, 322, 'Hong Konger', 'en', '852', 'HK');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (85, 323, N'ياباني', 'ar', '81', 'JP');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (86, 323, 'Japanese', 'en', '81', 'JP');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (87, 324, N'بهوتاني', 'ar', '975', 'BT');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (88, 324, 'Bhutanese', 'en', '975', 'BT');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (89, 325, N'صيني', 'ar', '86', 'CN');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (90, 325, 'Chinese', 'en', '86', 'CN');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (91, 326, N'قبرصي', 'ar', '357', 'CY');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (92, 326, 'Cypriote', 'en', '357', 'CY');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (93, 328, N'كوري شمالي', 'ar', '850', 'KP');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (94, 328, 'Korean N.', 'en', '850', 'KP');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (95, 329, N'لاوسي', 'ar', '856', 'LA');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (96, 329, 'Laosian', 'en', '856', 'LA');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (97, 330, N'منغولي', 'ar', '976', 'MN');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (98, 330, 'Mongolian', 'en', '976', 'MN');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (99, 331, N'ماكاوي', 'ar', '853', 'MO');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (100, 331, 'Macauese', 'en', '853', 'MO');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (101, 332, N'تركستاني', 'ar', '7-72533', 'KZ-YUZ');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (102, 332, 'Turkistani', 'en', '7-72533', 'KZ-YUZ');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (103, 336, N'كازاخستاني', 'ar', '7', 'KZ');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (104, 336, 'Kazakh', 'en', '7', 'KZ');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (105, 337, N'أوزبكستاني', 'ar', '998', 'UZ');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (106, 337, 'Uzbek', 'en', '998', 'UZ');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (107, 338, N'تركمانستاني', 'ar', '993', 'TM');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (108, 338, 'Turkmenenian', 'en', '993', 'TM');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (109, 339, N'طاجكستاني', 'ar', '992', 'TJ');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (110, 339, 'Tajik', 'en', '992', 'TJ');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (111, 340, N'قيرغيزي', 'ar', '996', 'KG');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (112, 340, 'Kyrgyzian', 'en', '996', 'KG');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (113, 343, N'أذربيجاني', 'ar', '994', 'AZ');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (114, 343, 'Azerbaijani', 'en', '994', 'AZ');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (115, 344, N'شيشاني', 'ar', '7-871', 'RU-CE');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (116, 344, 'Chechenian', 'en', '7-871', 'RU-CE');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (117, 345, N'داغستاني', 'ar', '7-872', 'RU-DA');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (118, 345, 'Daghestani', 'en', '7-872', 'RU-DA');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (119, 347, N'تتارستاني', 'ar', '7-843', 'RU-TA');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (120, 347, 'Tataristani', 'en', '7-843', 'RU-TA');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (121, 349, N'تيمور الشرقية', 'ar', '670', 'TL');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (122, 349, 'East Timor', 'en', '670', 'TL');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (123, 401, N'اثيوبي', 'ar', '251', 'ET');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (124, 401, 'Ethiopian', 'en', '251', 'ET');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (125, 402, N'اوغندي', 'ar', '256', 'UG');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (126, 402, 'Ugandan', 'en', '256', 'UG');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (127, 403, N'بوتسواني', 'ar', '267', 'BW');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (128, 403, 'Botswanian', 'en', '267', 'BW');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (129, 404, N'بورندي', 'ar', '257', 'BI');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (130, 404, 'Burundian', 'en', '257', 'BI');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (131, 405, N'تشادي', 'ar', '235', 'TD');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (132, 405, 'Chadian', 'en', '235', 'TD');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (133, 406, N'تنزاني', 'ar', '255', 'TZ');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (134, 406, 'Tanzanian', 'en', '255', 'TZ');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (135, 407, N'توجوي', 'ar', '228', 'TG');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (136, 407, 'Togolese', 'en', '228', 'TG');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (137, 408, N'جابوني', 'ar', '241', 'GA');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (138, 408, 'Gabonese', 'en', '241', 'GA');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (139, 409, N'غامبي', 'ar', '220', 'GM');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (140, 409, 'Gambian', 'en', '220', 'GM');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (141, 410, N'جزر القمر', 'ar', '269', 'KM');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (142, 410, 'Comorosian', 'en', '269', 'KM');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (143, 411, N'جنوب أفريقي', 'ar', '27', 'ZA');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (144, 411, 'South African', 'en', '27', 'ZA');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (145, 412, N'ناميبي', 'ar', '264', 'NA');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (146, 412, 'Namibian', 'en', '264', 'NA');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (147, 413, N'بنيني', 'ar', '229', 'BJ');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (148, 413, 'Beninese', 'en', '229', 'BJ');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (149, 414, N'رواندي', 'ar', '250', 'RW');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (150, 414, 'Rwandan', 'en', '250', 'RW');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (151, 415, N'زمبابوي', 'ar', '263', 'ZW');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (152, 415, 'Zimbabwean', 'en', '263', 'ZW');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (153, 416, N'زائيري', 'ar', '243', 'CD');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (154, 416, 'Zairean', 'en', '243', 'CD');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (155, 417, N'زامبي', 'ar', '260', 'ZM');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (156, 417, 'Zambian', 'en', '260', 'ZM');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (157, 418, N'ساحل العاج', 'ar', '225', 'CI');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (158, 418, 'Ivorian', 'en', '225', 'CI');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (159, 419, N'سنغالي', 'ar', '221', 'SN');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (160, 419, 'Senegalese', 'en', '221', 'SN');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (161, 420, N'سيراليوني', 'ar', '232', 'SL');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (162, 420, 'Sierra Leonean', 'en', '232', 'SL');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (163, 421, N'غاني', 'ar', '233', 'GH');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (164, 421, 'Ghanian', 'en', '233', 'GH');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (165, 422, N'غيني', 'ar', '224', 'GN');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (166, 422, 'Guinean', 'en', '224', 'GN');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (167, 423, N'غيني بيساوي', 'ar', '245', 'GW');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (168, 423, 'Guinean Bissau', 'en', '245', 'GW');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (169, 424, N'بوركينافاسوي', 'ar', '226', 'BF');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (170, 424, 'Burkinian', 'en', '226', 'BF');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (171, 425, N'كاميروني', 'ar', '237', 'CM');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (172, 425, 'Cameroonian', 'en', '237', 'CM');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (173, 426, N'كونغوي', 'ar', '242', 'CG');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (174, 426, 'Congolese', 'en', '242', 'CG');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (175, 427, N'كيني', 'ar', '254', 'KE');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (176, 427, 'Kenyan', 'en', '254', 'KE');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (177, 428, N'ليسوتوي', 'ar', '266', 'LS');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (178, 428, 'Lesothian', 'en', '266', 'LS');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (179, 429, N'ليبيري', 'ar', '231', 'LR');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (180, 429, 'Liberian', 'en', '231', 'LR');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (181, 430, N'مالي', 'ar', '223', 'ML');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (182, 430, 'Malian', 'en', '223', 'ML');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (183, 432, N'ملاوي', 'ar', '265', 'MW');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (184, 432, 'Malawian', 'en', '265', 'MW');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (185, 433, N'موريشيوسي', 'ar', '230', 'MU');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (186, 433, 'Mauritian', 'en', '230', 'MU');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (187, 434, N'موزمبيقي', 'ar', '258', 'MZ');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (188, 434, 'Mozambican', 'en', '258', 'MZ');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (189, 435, N'نيجيري', 'ar', '234', 'NG');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (190, 435, 'Nigeria', 'en', '234', 'NG');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (191, 436, N'نيجري', 'ar', '227', 'NE');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (192, 436, 'Nigerois', 'en', '227', 'NE');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (193, 437, N'افريقيا الوسطى', 'ar', '236', 'CF');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (194, 437, 'Central African', 'en', '236', 'CF');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (195, 438, N'أنغولي', 'ar', '244', 'AO');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (196, 438, 'Angolan', 'en', '244', 'AO');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (197, 439, N'الراس الاخضر', 'ar', '238', 'CV');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (198, 439, 'Cape Verdean', 'en', '238', 'CV');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (199, 440, N'غيني استوائي', 'ar', '240', 'GQ');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (200, 440, 'Equatorial Guinea', 'en', '240', 'GQ');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (201, 441, N'ملاجاسي/مدغشقر', 'ar', '261', 'MG');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (202, 441, 'Malagasyan', 'en', '261', 'MG');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (203, 442, N'ساوتوميبرنسبى', 'ar', '239', 'ST');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (204, 442, 'São Tomé and Príncipe', 'en', '239', 'ST');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (205, 443, N'جزر سيشل', 'ar', '248', 'SC');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (206, 443, 'Seychelian', 'en', '248', 'SC');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (207, 444, N'سوزيلاندي', 'ar', '268', 'SZ');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (208, 444, 'Swazilander', 'en', '268', 'SZ');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (209, 449, N'إرتيري', 'ar', '291', 'ER');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (210, 449, 'Eritrean', 'en', '291', 'ER');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (211, 453, N'جمهورية جنوب السودان', 'ar', '211', 'SS');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (212, 453, 'جمهورية جنوب السودان', 'en', '211', 'SS');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (213, 501, N'أسباني', 'ar', '34', 'ES');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (214, 501, 'Spanish', 'en', '34', 'ES');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (215, 502, N'الباني', 'ar', '355', 'AL');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (216, 502, 'Albanian', 'en', '355', 'AL');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (217, 503, N'ألماني', 'ar', '49', 'DE');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (218, 503, 'German', 'en', '49', 'DE');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (219, 504, N'ايرلندي', 'ar', '353', 'IE');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (220, 504, 'Irish', 'en', '353', 'IE');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (221, 505, N'إيطالي', 'ar', '39', 'IT');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (222, 505, 'Italian', 'en', '39', 'IT');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (223, 506, N'بريطاني', 'ar', '44', 'GB');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (224, 506, 'British', 'en', '44', 'GB');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (225, 507, N'برتغالي', 'ar', '351', 'PT');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (226, 507, 'Portuguese', 'en', '351', 'PT');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (227, 508, N'بلغاري', 'ar', '359', 'BG');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (228, 508, 'Bulgarian', 'en', '359', 'BG');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (229, 509, N'بلجيكي', 'ar', '32', 'BE');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (230, 509, 'Belgian', 'en', '32', 'BE');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (231, 510, N'بولندي', 'ar', '48', 'PL');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (232, 510, 'Polish', 'en', '48', 'PL');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (233, 512, N'دانماركي', 'ar', '45', 'DK');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (234, 512, 'Danish', 'en', '45', 'DK');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (235, 513, N'روماني', 'ar', '40', 'RO');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (236, 513, 'Romanian', 'en', '40', 'RO');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (237, 514, N'سويدي', 'ar', '46', 'SE');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (238, 514, 'Swedish', 'en', '46', 'SE');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (239, 515, N'سويسري', 'ar', '41', 'CH');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (240, 515, 'Swiss', 'en', '41', 'CH');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (241, 516, N'فرنسي', 'ar', '33', 'FR');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (242, 516, 'Franch', 'en', '33', 'FR');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (243, 517, N'فنلندي', 'ar', '358', 'AX');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (244, 517, 'Finlander', 'en', '358', 'AX');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (245, 518, N'صربيا', 'ar', '381', 'RS');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (246, 518, 'Serbia', 'en', '381', 'RS');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (247, 519, N'هولندي', 'ar', '31', 'NL');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (248, 519, 'Dutch', 'en', '31', 'NL');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (249, 521, N'يوناني', 'ar', '30', 'GR');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (250, 521, 'GreeK', 'en', '30', 'GR');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (251, 522, N'اندوري', 'ar', '376', 'AD');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (252, 522, 'Andorian', 'en', '376', 'AD');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (253, 523, N'نمساوي', 'ar', '43', 'AT');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (254, 523, 'Austrian', 'en', '43', 'AT');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (255, 524, N'الجبل الأ سود', 'ar', '382', 'ME');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (256, 524, 'Montenegro', 'en', '382', 'ME');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (257, 525, N'هنغاري', 'ar', '36', 'HU');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (258, 525, 'Hungarian', 'en', '36', 'HU');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (259, 526, N'ايسلندي', 'ar', '354', 'IS');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (260, 526, 'Icelander', 'en', '354', 'IS');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (261, 527, N'ليختنشتيني', 'ar', '423', 'LI');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (262, 527, 'Liechtenstein', 'en', '423', 'LI');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (263, 528, N'لوكسمبورغي', 'ar', '352', 'LU');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (264, 528, 'Luxembourgian', 'en', '352', 'LU');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (265, 529, N'مالطي', 'ar', '356', 'MT');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (266, 529, 'Maltese', 'en', '356', 'MT');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (267, 530, N'موناكوي', 'ar', '377', 'MC');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (268, 530, 'Monacan', 'en', '377', 'MC');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (269, 531, N'نرويجي', 'ar', '47', 'SJ');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (270, 531, 'Norwegian', 'en', '47', 'SJ');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (271, 532, N'سان مورينو', 'ar', '378', 'SM');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (272, 532, 'San Marino', 'en', '378', 'SM');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (273, 533, N'مدينة الفاتيكان', 'ar', '39-06', 'VA');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (274, 533, 'Vatican', 'en', '39-06', 'VA');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (275, 534, N'جبل طارق', 'ar', '350', 'GI');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (276, 534, 'Gibraltarian', 'en', '350', 'GI');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (277, 536, N'اوكراني', 'ar', '380', 'UA');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (278, 536, 'Ucranian', 'en', '380', 'UA');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (279, 537, N'روسيا البيضاء', 'ar', '375', 'BY');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (280, 537, 'Belarussian', 'en', '375', 'BY');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (281, 539, N'ارميني', 'ar', '374', 'AM');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (282, 539, 'Armenian', 'en', '374', 'AM');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (283, 540, N'مولدافي', 'ar', '373', 'MD');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (284, 540, 'Moldovian', 'en', '373', 'MD');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (285, 541, N'جورجي', 'ar', '995', 'GE');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (286, 541, 'Georgian', 'en', '995', 'GE');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (287, 542, N'ليتواني', 'ar', '370', 'LT');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (288, 542, 'Lithuanian', 'en', '370', 'LT');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (289, 543, N'أستوني', 'ar', '372', 'EE');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (290, 543, 'Estonian', 'en', '372', 'EE');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (291, 544, N'لاتفي', 'ar', '371', 'LV');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (292, 544, 'Latvian', 'en', '371', 'LV');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (293, 545, N'بوسني', 'ar', '387', 'BA');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (294, 545, 'Bosnian', 'en', '387', 'BA');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (295, 546, N'كرواتي', 'ar', '385', 'HR');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (296, 546, 'Croatian', 'en', '385', 'HR');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (297, 547, N'سلوفيني', 'ar', '386', 'SI');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (298, 547, 'Slovenian', 'en', '386', 'SI');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (299, 549, N'مقدوني', 'ar', '389', 'MK');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (300, 549, 'Macedonian', 'en', '389', 'MK');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (301, 552, N'تشيكي', 'ar', '420', 'CZ');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (302, 552, 'Czechish', 'en', '420', 'CZ');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (303, 553, N'سلوفاكي', 'ar', '421', 'SK');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (304, 553, 'Slovakian', 'en', '421', 'SK');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (305, 554, N'جزر فيرو', 'ar', '298', 'FO');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (306, 554, 'Faroe Islands', 'en', '298', 'FO');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (307, 601, N'أمريكي', 'ar', '1', 'US');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (308, 601, 'American', 'en', '1', 'US');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (309, 602, N'أرجنتيني', 'ar', '54', 'AR');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (310, 602, 'Argentinian', 'en', '54', 'AR');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (311, 603, N'بربادوسي', 'ar', '1-246', 'BB');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (312, 603, 'Barbadian', 'en', '1-246', 'BB');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (313, 604, N'برازيلي', 'ar', '55', 'BR');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (314, 604, 'Brazilian', 'en', '55', 'BR');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (315, 605, N'بنمي', 'ar', '507', 'PA');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (316, 605, 'Panamanian', 'en', '507', 'PA');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (317, 606, N'ترينداد وتوباجو', 'ar', '1-868', 'TT');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (318, 606, 'Trinindad', 'en', '1-868', 'TT');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (319, 607, N'جامايكي', 'ar', '1-876', 'JM');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (320, 607, 'Jamaican', 'en', '1-876', 'JM');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (321, 608, N'جواني', 'ar', '592', 'GY');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (322, 608, 'Guyanese', 'en', '592', 'GY');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (323, 609, N'فنزويلي', 'ar', '58', 'VE');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (324, 609, 'Venezuelan', 'en', '58', 'VE');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (325, 610, N'كندي', 'ar', '1', 'CA');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (326, 610, 'Canadian', 'en', '1', 'CA');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (327, 611, N'كولمبي', 'ar', '57', 'CO');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (328, 611, 'Colombian', 'en', '57', 'CO');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (329, 612, N'جزر البهاما', 'ar', '1-242', 'BS');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (330, 612, 'Bahamian', 'en', '1-242', 'BS');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (331, 613, N'كوستاريكي', 'ar', '506', 'CR');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (332, 613, 'Costa Rican', 'en', '506', 'CR');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (333, 614, N'كوبي', 'ar', '53', 'CU');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (334, 614, 'Cuban', 'en', '53', 'CU');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (335, 615, N'دومينيكي', 'ar', '1-767', 'DM');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (336, 615, 'Dominican', 'en', '1-767', 'DM');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (337, 616, N'جمهورية دمينكان', 'ar', '1-809,1-829,1-849', 'DO');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (338, 616, 'Republic Dominica', 'en', '1-809,1-829,1-849', 'DO');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (339, 617, N'سلفادوري', 'ar', '503', 'SV');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (340, 617, 'Salvadorian', 'en', '503', 'SV');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (341, 618, N'جرانادي', 'ar', '1-473', 'GD');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (342, 618, 'Grenadian', 'en', '1-473', 'GD');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (343, 619, N'جواتيمالي', 'ar', '502', 'GT');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (344, 619, 'Guatemalan', 'en', '502', 'GT');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (345, 620, N'هايتي', 'ar', '509', 'HT');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (346, 620, 'Haitian', 'en', '509', 'HT');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (347, 621, N'هوندوراس', 'ar', '504', 'HN');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (348, 621, 'Honduranian', 'en', '504', 'HN');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (349, 622, N'مكسيكي', 'ar', '52', 'MX');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (350, 622, 'Mexican', 'en', '52', 'MX');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (351, 623, N'نيكاراجوي', 'ar', '505', 'NI');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (352, 623, 'Nicaraguan', 'en', '505', 'NI');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (353, 624, N'سانت لوسيا', 'ar', '1-758', 'LC');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (354, 624, 'Saint Lucia', 'en', '1-758', 'LC');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (355, 625, N'سان فينسنت', 'ar', '1-784', 'VC');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (356, 625, 'Saint Vincent', 'en', '1-784', 'VC');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (357, 626, N'بوليفي', 'ar', '591', 'BO');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (358, 626, 'Bolivian', 'en', '591', 'BO');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (359, 627, N'شيلي', 'ar', '56', 'CL');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (360, 627, 'Chilean', 'en', '56', 'CL');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (361, 628, N'اكوادوري', 'ar', '593', 'EC');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (362, 628, 'Ecuadorean', 'en', '593', 'EC');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (363, 629, N'باراجواي', 'ar', '595', 'PY');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (364, 629, 'Paraguayan', 'en', '595', 'PY');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (365, 630, N'بيروي', 'ar', '51', 'PE');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (366, 630, 'Peruvian', 'en', '51', 'PE');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (367, 701, N'استرالي', 'ar', '61', 'CC');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (368, 701, 'Australian', 'en', '61', 'CC');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (369, 702, N'نيوزيلندي', 'ar', '64', 'NZ');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (370, 702, 'N.zelander', 'en', '64', 'NZ');


INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (371, 112, N'يمني جنوبي ', 'ar', '967', 'YE');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (372, 112, N'يمني جنوبي ', 'en', '967', 'YE');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (373, 114, N'يمني جنوبيالسلاطين', 'ar', '967', 'YE');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (374, 114, N'يمني جنوبيالسلاطين', 'en', '967', 'YE');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (375, 115, N'بني حارث', 'ar', '967', 'YE');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (376, 115, N'بني حارث', 'en', '967', 'YE');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (377, 118, N'من سكان البحرين', 'ar', '967', 'BH');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (378, 118, 'Bahrain Res.', 'en', '967', 'BH');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (379, 314, N'تايواني', 'ar', '886', 'TWN');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (380, 314, 'Taiwanese', 'en', '886', 'TWN');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (381, 333, N'بلوشستاني', 'ar', '81', 'PK');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (382, 333, 'Blushistani', 'en', '81', 'PK');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (383, 334, N'بخارستاني', 'ar', '', '');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (384, 334, 'Bukharan', 'en', '', '');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (385, 335, N'القبائل النازحة', 'ar', '', '');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (386, 335, N'القبائل النازحة', 'en', '', '');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (387, 341, N'سقطري', 'ar', '', '');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (388, 341, 'Sukatra', 'en', '', '');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (389, 342, N'مهري', 'ar', '', '');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (390, 342, 'Mahara', 'en', '', '');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (391, 346, N'انقوشي ', 'ar', '', '');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (393, 346, 'Ingush', 'en', '', '');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (394, 446, N'رينيوني', 'ar', '262', 'REU');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (395, 446, N'Reunese', 'en', '262', 'REU');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (396, 447, N'ترانسكي', 'ar', '', '');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (397, 447, N'ترانسكي', 'en', '', '');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (398, 448, N'فيندي', 'ar', '', '');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (399, 448, N'فيندي', 'en', '', '');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (400, 511, N'تشيكوسلوفاكي', 'ar', '', 'CSK');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (401, 511, 'Czechlovakian', 'en', '42', 'CSK');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (402, 520, N'يوغسلافي', 'ar', '42', 'SER');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (403, 520, 'Yugoslavian', 'en', '38', 'SER');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (404, 631, N'سورينامي', 'ar', '597', 'SUR');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (405, 631, 'Suriname', 'en', '597', 'SUR');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (406, 632, N'اوراجواي', 'ar', '598', 'URY');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (407, 632, 'Uruguayan', 'en', '598', 'URY');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (408, 633, N'س بييري وميكويلن', 'ar', '', '');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (409, 633, N'س بييري وميكويلن', 'en', '', '');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (410, 634, N'جرينلاند', 'ar', '299', 'GRL');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (411, 634, 'GreenLander', 'en', '299', 'GRL');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (412, 635, N'بيليز', 'ar', '501', 'BLZ');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (413, 635, 'Belizian', 'en', '501', 'BLZ');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (414, 636, N'بيرمودي', 'ar', '', '');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (415, 636, N'بيرمودي', 'en', '', '');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (416, 637, N'ج الترك والقوقاز', 'ar', '', '');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (417, 637, N'ج الترك والقوقاز ', 'en', '', '');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (418, 638, N'سان كريستوفرنيفز', 'ar', '', '');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (419, 638, N'سان كريستوفرنيفز', 'en', '', '');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (420, 641, N'ج فيرجن البريطانية', 'ar', '1', 'VGB');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (421, 641, 'Virgin Isaland British', 'en', '1', 'VGB');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (422, 642, N'جزر كايمون', 'ar', '1', 'CYM');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (423, 642, 'Caymanian', 'en', '1', 'CYM');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (424, 643, N'مونت سيرات', 'ar', '1', 'MSR');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (425, 643, N'مونت سيرات', 'en', '1', 'MSR');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (426, 644, N'جيودي لوب', 'ar', '590', 'GLP');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (428, 644, 'Guadeloupean', 'en', '590', 'GLP');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (429, 645, N'مارتينيكوي', 'ar', '596', 'MTQ');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (430, 645, N'مارتينيكوي', 'en', '596', 'MTQ');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (431, 646, N'عروبي', 'ar', '', '');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (432, 646, N'عروبي', 'en', '', '');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (433, 647, N'بونيري', 'ar', '', '');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (434, 647, N'بونيري', 'en', '', '');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (435, 648, N'كيوراكو', 'ar', '', '');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (436, 648, N'كيوراكو', 'en', '', '');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (437, 650, N'سابا', 'ar', '60-8', 'SBH');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (438, 650, N'سابا', 'en', '60-8', 'SBH');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (439, 652, N'بورتوريكو', 'ar', '1', 'PRT');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (440, 652, 'Puerto Rican', 'en', '1', 'PRT');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (441, 653, N'ج فيرجن الامريكية', 'ar', '1', 'VIR');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (442, 653, 'Virgin Isaland US', 'en', '1', 'VIR');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (443, 654, N'جزر فاكلاند', 'ar', '500', 'FLK');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (444, 654, 'Falklander', 'en', '500', 'FLK');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (445, 655, N'جيانا الفرنسية', 'ar', '', '');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (446, 655, 'Guinean', 'en', '', '');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (447, 656, N'الامم المتحدة', 'ar', '', '');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (448, 656, 'United Nations', 'en', '', '');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (449, 703, N'بابوا نيوغينا', 'ar', '', '');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (450, 703, N'بابوا نيوغينا', 'en', '', '');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (451, 801, N'جزر فيجي', 'ar', '', '');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (452, 801, 'Fijian', 'en', '', '');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (453, 803, N'نوروي', 'ar', '', 'NRU');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (454, 803, 'Nauru', 'en', '', 'NRU');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (455, 804, N'جزر سليمان', 'ar', '', 'SLB');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (456, 804, 'Solomon Island', 'en', '', 'SLB');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (457, 805, N'تونجي', 'ar', '', 'TON');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (458, 805, 'Tongan', 'en', '', 'TON');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (459, 806, N'توفالوي', 'ar', '', 'TUV');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (460, 806, 'Tuvalu', 'en', '', 'TUV');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (461, 807, N'فانيوتو', 'ar', '', '');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (462, 807, N'فانيوتو', 'en', '', '');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (463, 808, N'ساموا الغربية', 'ar', '', '');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (464, 808, 'Samoan W.', 'en', '', '');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (465, 810, N'جوام', 'ar', '', '');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (466, 810, N'جوام', 'en', '', '');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (467, 811, N'جزر ماريانا', 'ar', '', '');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (468, 811, N'جزر ماريانا', 'en', '', '');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (469, 812, N'ميكرونيسيا', 'ar', '691', 'FSM');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (470, 812, 'Micronesia', 'en', '691', 'FSM');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (471, 813, N'جزر ماريشال', 'ar', '692', 'MHL');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (472, 813, 'Marshall Island', 'en', '692', 'MHL');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (473, 814, N'بيلو', 'ar', '', '');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (474, 814, N'بيلو', 'en', '', '');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (475, 548, N'صربي', 'ar', '381', 'SRB');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (476, 548, 'Serbian', 'en', '381', 'SRB');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (477, 550, N'كوسوفوا', 'ar', '', '');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (478, 550, 'Kosovar', 'en', '', '');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (479, 815, N'بولينيسياالفرنسية', 'ar', '', '');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (480, 815, N'بولينيسياالفرنسية', 'en', '', '');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (481, 816, N'Wallis and Futura Isl.', 'ar', '681', 'WLF');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (482, 816, N'Wallis and Futura Isl.', 'en', '681', 'WLF');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (483, 817, N'كاليدونيا الجديد', 'ar', '687', 'NCL');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (484, 817, N'كاليدونيا الجديد', 'en', '687', 'NCL');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (485, 900, N'غير معروف', 'ar', '', '');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (486, 900, 'Unknown', 'en', '', '');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (487, 818, N'مدغشقر', 'ar', '', '');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (488, 818, 'Madagascan', 'en', '261', 'MDG');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (489, 126, N'وثيقة قطرية ', 'ar', '261', 'MDG');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (490, 126, N'وثيقة قطرية', 'en', '', '');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (491, 128, N'وثيقة اماراتية', 'ar', '', '');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (492, 128, 'Emirati Doct', 'en', '', '');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (493, 116, N'كويتي بدون', 'ar', '', '');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (494, 116, 'Kuwaiti Without', 'en', '', '');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (495, 121, N'فلسطيني بوثيقة مصرية', 'ar', '', '');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (496, 121, 'Palestinian (Egyptian T.Doct.)', 'en', '', '');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (497, 125, N'فلسطيني بوثيقة سورية', 'ar', '', '');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (498, 125, 'Palestinian (Syrian T.Doct.)', 'en', '', '');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (499, 352, N'ميانمار/جواز باكستان', 'ar', '', '');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (500, 352, 'Myanmar / Passport of Pakistan', 'en', '', '');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (501, 350, N'بدون', 'ar', '', '');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (502, 350, 'without', 'en', '', '');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (503, 353, N'ميانمار/جوازبنجلا دش', 'ar', '95', 'MMR');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (504, 353, N'ميانمار/جوازبنجلا دش', 'en', '95', 'MMR');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (505, 663, N'سانت كيتس  ونافيس', 'ar', '1', 'KN');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (506, 663, N'سانت كيتس  ونافيس', 'en', '1', 'KN');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (507, 351, N'ميانمار /مقيم', 'ar', '95', 'MMR');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (508, 351, N'ميانمار /مقيم', 'en', '95', 'MMR');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (509, 131, N'قبائل نازحة/الحليفه', 'ar', '', '');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (510, 131, N'قبائل نازحة/الحليفه', 'en', '', '');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (511, 136, N'غير قطري', 'ar', '', '');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (512, 136, N'غير قطري', 'en', '', '');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (513, 142, N'مقيم/أفراد القبائل', 'ar', '', '');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (514, 142, N'مقيم/أفراد القبائل', 'en', '', '');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (515, 142, N'مقيم/غير معروف', 'ar', '', '');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (516, 143, N'مقيم/غير معروف', 'en', '', '');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (517, 137, N'غير اماراتي', 'ar', '', '');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (518, 137, N'غير اماراتي', 'en', '', '');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (519, 129, N'وثيقة بحرينيه', 'ar', '', '');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (520, 129, N'وثيقة بحرينيه', 'en', '', '');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (521, 823, N'قبيلة نهد', 'ar', '', '');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (522, 823, N'قبيلة نهد', 'en', '', '');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (523, 144, N'مقيم/لا يحمل وثيقة', 'ar', '', '');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (524, 144, N'مقيم/لا يحمل وثيقة', 'en', '', '');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (525, 135, N'غير بحريني', 'ar', '', '');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (526, 135, N'غير بحريني', 'en', '', '');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (527, 707, N'توكيلاو', 'ar', '', 'TKL');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (528, 707, N'Tokelau', 'en', '', 'TKL');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (529, 710, N'فرنسا الجنوب القطبية', 'ar', '', '');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (530, 710, N'فرنسا الجنوب القطبية', 'en', '', '');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (531, 706, N'جزر نورفولك', 'ar', '', 'NFK');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (532, 706, N'جزر نورفولك', 'en', '', 'NFK');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (533, 824, N'جزر مينور', 'ar', '', '');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (534, 824, N'جزر مينور', 'en', '', '');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (535, 134, N'غير كويتي', 'ar', '', '');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (536, 134, N'غير كويتي', 'en', '', '');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (537, 705, N'انتاركتيكا', 'ar', '', '');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (538, 705, N'انتاركتيكا', 'en', '', '');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (539, 826, N'مقيم اجنبي - العدوان', 'ar', '', '');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (540, 826, N'مقيم اجنبي - العدوان', 'en', '', '');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (541, 825, N'مقيم اجنبي - الاشاجعة', 'ar', '', '');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (542, 825, N'مقيم اجنبي - الاشاجعة', 'en', '', '');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (543, 709, N'جزيرةكوكو-كيلنج', 'ar', '61', 'CCK');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (544, 709, N'Cocos (Keeling) Islands', 'en', '61', 'CCK');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (545, 708, N'جزيرةكريسماس', 'ar', '61', 'CXR');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (546, 708, N'جزيرةكريسماس', 'en', '61', 'CXR');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (547, 821, N'قبائل مجاورة للعبر', 'ar', '', '');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (548, 821, N'قبائل مجاورة للعبر', 'en', '', '');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (549, 662, N'البريطانية في المحيط', 'ar', '', '');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (550, 662, N'البريطانية في المحيط', 'en', '', '');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (551, 657, N'جزر كوك', 'ar', '', 'COK');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (552, 657, N'جزر كوك', 'en', '', 'COK');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (553, 664, N'جنوب جورجيا', 'ar', '', 'GEO');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (554, 664, N'جنوب جورجيا', 'en', '', 'GEO');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (555, 132, N'اليمن - لحج', 'ar', '', '');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (556, 132, N'اليمن - لحج', 'en', '', '');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (557, 130, N'عرب ثمانية وأربعون', 'ar', '', '');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (558, 130, N'عرب ثمانية وأربعون', 'en', '', '');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (559, 555, N'ميتروبوليتان فرنسية', 'ar', '', '');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (560, 555, N'ميتروبوليتان فرنسية', 'en', '', '');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (561, 711, N'جزيرة هيرد وماكدونلد', 'ar', '', '');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (562, 711, N'جزيرة هيرد وماكدونلد', 'en', '', '');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (563, 133, N'قبائل نازحة/الكويت', 'ar', '', '');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (564, 133, N'قبائل نازحة/الكويت', 'en', '', '');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (565, 146, N'المناهيل والمهرة', 'ar', '', '');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (566, 146, N'المناهيل والمهرة', 'en', '', '');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (567, 451, N'سانت هيلانة', 'ar', '290', 'SHN');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (568, 451, N'سانت هيلانة', 'en', '290', 'SHN');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (569, 659, N'باربودا', 'ar', '1', 'BRB');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (570, 659, N'باربودا', 'en', '1', 'BRB');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (571, 139, N'مقيم/نازح', 'ar', '', '');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (572, 139, N'مقيم/نازح', 'en', '', '');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (573, 901, N'اخرى', 'ar', '', '');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (574, 901, N'اخرى', 'en', '', '');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (575, 554, N'جزر فيرو', 'ar', '', '');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (576, 554, N'جزر فيرو', 'en', '', '');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (577, 820, N'قبيلة النسي', 'ar', '', '');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (578, 820, N'قبيلة النسي', 'en', '', '');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (579, 822, N'قبيلة الحرث', 'ar', '', '');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (580, 822, N'قبيلة الحرث', 'en', '', '');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (581, 704, N'نيو', 'ar', '', '');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (582, 704, N'نيو', 'en', '', '');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (583, 140, N'مقيم/مولود', 'ar', '', '');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (584, 140, N'مقيم/مولود', 'en', '', '');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (585, 141, N'مقيم/طالب جنسية', 'ar', '', '');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (586, 141, N'مقيم/طالب جنسية', 'en', '', '');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (587, 138, N'غير عماني', 'ar', '', '');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (588, 138, N'غير عماني', 'en', '', '');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (589, 660, N'انتيل الهولندية', 'ar', '599', 'ANT');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (590, 660, N'انتيل الهولندية', 'en', '599', 'ANT');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (591, 454, N'كاب فيرد', 'ar', '238', 'CPV');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (592, 454, N'كاب فيرد', 'en', '238', 'CPV');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (593, 712, N'جزر بيتكايرن', 'ar', '64', 'PCN');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (594, 712, N'Pitcairn', 'en', '64', 'PCN');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (595, 819, N'قبيلة بالعبيد', 'ar', '', '');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (596, 819, N'قبيلة بالعبيد', 'en', '', '');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (597, 145, N'قبيلة الصيعر', 'ar', '', '');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (598, 145, N'قبيلة الصيعر', 'en', '', '');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (599, 127, N'وثيقة عمانيه', 'ar', '', '');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (600, 127, N'وثيقة عمانيه', 'en', '', '');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (601, 117, N'افراد القبائل', 'ar', '', '');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (602, 117, N'افراد القبائل', 'en', '', '');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (603, 639, N'انجويلي', 'ar', '1', 'AIA');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (604, 639, 'Anguillian', 'en', '1', 'AIA');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (605, 649, N'سان استاتيوس', 'ar', '', '');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (606, 649, N'سان استاتيوس', 'en', '', '');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (607, 809, N'ساموا الامريكية', 'ar', '685', 'ASM');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (608, 809, 'Samoana', 'en', '685', 'ASM');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (609, 2222, N'جنسية غير معرفه', 'ar', '', '');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (610, 2222, N'جنسية غير معرفه', 'en', '', '');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (611, 997, N'جميع الجنسيات', 'ar', '', '');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (612, 997, N'جميع الجنسيات', 'en', '', '');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (613, 119, N'قبائل مجاورة للعطفين', 'ar', '', '');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (614, 119, N'قبائل مجاورة للعطفين', 'en', '', '');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (615, 640, N'انتيكوي', 'ar', '1', 'ATG');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (616, 640, 'Antiguan', 'en', '1', 'ATG');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (617, 651, N'سان مارتين', 'ar', '', '');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (618, 651, N'سان مارتين', 'en', '', '');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (619, 551, N'الجبل الاسود', 'ar', '', '');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (620, 551, 'Montenegrin', 'en', '', '');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (621, 348, N'كرغيزستاني', 'ar', '', '');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (622, 348, 'Kyrgyzian', 'en', '', '');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (623, 120, N'اجنبي بجواز سعودي', 'ar', '', '');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (624, 120, 'Foreigner Saudi Passport', 'en', '', '');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (625, 445, N'بوفثاتسواني', 'ar', '267', 'BWA');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (626, 445, 'Botswanian', 'en', '267', 'BWA');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (627, 661, N'جزر كوكوس', 'ar', '', '');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (628, 661, N'جزر كوكوس', 'en', '', '');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (629, 452, N'جزيرةمايوت', 'ar', '', '');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (630, 452, N'جزيرةمايوت', 'en', '', '');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (631, 122, N'فلسطيني بوثيقةعراقية', 'ar', '967', 'PS');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (632, 122, 'Palestinian (Iraqi T.Doct.)', 'en', '967', 'PS');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (633, 122, N'فلسطيني بوثيقةلبناني', 'ar', '967', 'PS');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (634, 122, 'Palestinian (lebanese T.Doct.)', 'en', '967', 'PS');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (635, 122, N'فلسطيني بوثيقةاردنية', 'ar', '967', 'PS');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (636, 122, 'Palestinian (Jordanian T.Doct.)', 'en', '967', 'PS');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (637, 450, N'دول افريقية اخري', 'ar', '', '');
INSERT INTO shc_portal.shc_country_lk(id, code, label, lang, country_phone_prefix, country_name_prefix)
VALUES (638, 450, 'Other Africans', 'en', '', '');

SET IDENTITY_INSERT shc_portal.shc_country_lk OFF;
GO