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
INSERT INTO shc_portal.shc_notification_template_name_lk (id, code, lang, label) VALUES (7, 'RESOLVE_INCIDENT', 'ar', N'حل شكوى');
INSERT INTO shc_portal.shc_notification_template_name_lk (id, code, lang, label) VALUES (8, 'RESOLVE_INCIDENT', 'en', 'Resolve Incident');
INSERT INTO shc_portal.shc_notification_template_name_lk (id, code, lang, label) VALUES (9, 'CLOSE_INCIDENT', 'ar', N'حل شكوى');
INSERT INTO shc_portal.shc_notification_template_name_lk (id, code, lang, label) VALUES (10, 'CLOSE_INCIDENT', 'en', 'Close Incident');
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
INSERT INTO shc_portal.shc_notification_template (id, category_code, name_code, status_code, type_code, important, action_required, enabled, user_specific, force_sending)
values (1, 'GENERAL', 'PASSWORD_EXPIRATION', 'CONFIRMED', 'SYSTEM_DEFINED', 0, 1, 1, 1, 1);
INSERT INTO shc_portal.shc_notification_template (id, category_code, name_code, status_code, type_code, important, action_required, enabled, user_specific, force_sending)
values (2, 'RITUAL', 'OUT_ARAFAT_FENCE', 'CONFIRMED', 'SYSTEM_DEFINED', 1, 0, 1, 1, 1);
INSERT INTO shc_portal.shc_notification_template (id, category_code, name_code, status_code, type_code, important, action_required, enabled, user_specific, force_sending)
values (3, 'RITUAL', 'DAILY_SURVEY', 'CONFIRMED', 'SYSTEM_DEFINED', 0, 1, 1, 1, 1);
INSERT INTO shc_portal.shc_notification_template (id, category_code, name_code, status_code, type_code, important, action_required, enabled, user_specific, force_sending)
values (4, 'GENERAL', 'RESOLVE_INCIDENT', 'CONFIRMED', 'SYSTEM_DEFINED', 0, 0, 1, 1, 1);
INSERT INTO shc_portal.shc_notification_template (id, category_code, name_code, status_code, type_code, important, action_required, enabled, user_specific, force_sending)
values (5, 'GENERAL', 'CLOSE_INCIDENT', 'CONFIRMED', 'SYSTEM_DEFINED', 0, 0, 1, 1, 1);
SET IDENTITY_INSERT shc_portal.shc_notification_template OFF;
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
SET IDENTITY_INSERT shc_portal.shc_notification_template_content OFF;
GO

INSERT INTO shc_portal.shc_config (conf_key, conf_value) VALUES ('notification.processing.batch.size', '1000');
INSERT INTO shc_portal.shc_config (conf_key, conf_value) VALUES ('scheduler.notification.processing.cron', '0 0/5 * * * *');
INSERT INTO shc_portal.shc_config (conf_key, conf_value) VALUES ('google.maps.api.key', 'AIzaSyAC78ugAlOF9B2YK8-ukki2IQTyNAgUSO0');
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
INSERT INTO shc_portal.shc_role_authority ( role_id, authority_id) values (1,17);

--Suspend Card (System Admin ,Enrollment officer and enrollment officer admin , Service center agent and service center regent admin )
INSERT INTO shc_portal.shc_role_authority (role_id, authority_id) values (1, 19);

--Cancel Card (System Admin ,Enrollment officer and enrollment officer admin , Service center agent and service center regent admin )
INSERT INTO shc_portal.shc_role_authority (role_id, authority_id) values (1, 18);

--Re-issue Card(System Admin  , Service center agent and service center regent admin )
INSERT INTO shc_portal.shc_role_authority (role_id, authority_id) values (1, 20);
GO

UPDATE shc_portal.shc_portal.shc_card_status_lk SET label = N'تمت إعادة الإصدار' WHERE code = 'REISSUED' and lang = 'ar';
UPDATE shc_portal.shc_portal.shc_card_status_lk SET label = 'Reissued' WHERE code = 'REISSUED' and lang = 'en';
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
GO

SET IDENTITY_INSERT shc_portal.shc_data_segment ON;
insert into shc_portal.shc_data_segment (id, template_file_name, label_ar, label_en)
values (7, 'applicant-emergency-data.xlsx', N'البيانات الضرورية لهوية ضيف الرحمن (في حالة الطوارئ)', 'Applicant Emergency Data');
SET IDENTITY_INSERT shc_portal.shc_data_segment OFF;
GO

SET IDENTITY_INSERT shc_portal.shc_incident_type_lk ON;
INSERT INTO shc_portal.shc_incident_type_lk (id, code, lang, label) VALUES (1, 'FOOD', 'ar', N'شكوى خدمة طعام');
INSERT INTO shc_portal.shc_incident_type_lk (id, code, lang, label) VALUES (2, 'FOOD', 'en', 'Food Complaint');
INSERT INTO shc_portal.shc_incident_type_lk (id, code, lang, label) VALUES (3, 'TRANSPORTATION', 'ar', N'شكوى خدمة نقل');
INSERT INTO shc_portal.shc_incident_type_lk (id, code, lang, label) VALUES (4, 'TRANSPORTATION', 'en', 'Transportation Complaint');
INSERT INTO shc_portal.shc_incident_type_lk (id, code, lang, label) VALUES (5, 'HOUSING', 'ar', N'شكوى خدمة سكن');
INSERT INTO shc_portal.shc_incident_type_lk (id, code, lang, label) VALUES (6, 'HOUSING', 'en', 'Housing Complaint');
INSERT INTO shc_portal.shc_incident_type_lk (id, code, lang, label) VALUES (7, 'GENERAL', 'ar', N'شكوى عامة');
INSERT INTO shc_portal.shc_incident_type_lk (id, code, lang, label) VALUES (8, 'GENERAL', 'en', 'General Complaint');
SET IDENTITY_INSERT shc_portal.shc_incident_type_lk OFF;
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
SET IDENTITY_INSERT shc_portal.shc_data_segment OFF;
GO

SET IDENTITY_INSERT shc_portal.shc_contact_type_lk ON;
INSERT INTO shc_portal.shc_contact_type_lk (id, code)
VALUES (1, 'STAFF');
INSERT INTO shc_portal.shc_contact_type_lk (id, code)
VALUES (2, 'APPLICANT');
SET IDENTITY_INSERT shc_portal.shc_contact_type_lk OFF;
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
SET IDENTITY_INSERT shc_portal.shc_authority_lk OFF;
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
SET IDENTITY_INSERT shc_portal.shc_applicant_digital_id_status_lk OFF;
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

SET IDENTITY_INSERT shc_portal.shc_authority_lk OFF;

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

INSERT INTO shc_portal.shc_housing_site_lk (id, code, lang, label, creation_date)
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
VALUES (9, 'MENA', 'ar',N'منى', GETDATE());

INSERT INTO shc_portal.shc_area_layers_lk (id, code, lang, label, creation_date)
VALUES (10, 'MENA', 'en', N'Mena', GETDATE());

INSERT INTO shc_portal.shc_area_layers_lk (id, code, lang, label, creation_date)
VALUES (11, 'GAMARAT', 'ar',N'الجمرات', GETDATE());

INSERT INTO shc_portal.shc_area_layers_lk (id, code, lang, label, creation_date)
VALUES (12, 'GAMARAT', 'en',N'Gamarat', GETDATE());

INSERT INTO shc_portal.shc_area_layers_lk (id, code, lang, label, creation_date)
VALUES (13, 'MUZDALIFA', 'ar', N'مزدلفة', GETDATE());

INSERT INTO shc_portal.shc_area_layers_lk (id, code, lang, label, creation_date)
VALUES (14, 'MUZDALIFA', 'en', N'Muzdalifah', GETDATE());

INSERT INTO shc_portal.shc_area_layers_lk (id, code, lang, label, creation_date)
VALUES (15, 'ARAFAT', 'ar', N'عرفات', GETDATE());

INSERT INTO shc_portal.shc_area_layers_lk (id, code, lang, label, creation_date)
VALUES (16, 'ARAFAT', 'en', N'Arafat', GETDATE());

INSERT INTO shc_portal.shc_area_layers_lk (id, code, lang, label, creation_date)
VALUES (17, 'JABAL_ALRAHMA', 'ar',N'جبل الرحمة', GETDATE());

INSERT INTO shc_portal.shc_area_layers_lk (id, code, lang, label, creation_date)
VALUES (18, 'JABAL_ALRAHMA', 'en',N'Alrahma Mountain', GETDATE());

SET IDENTITY_INSERT shc_portal.shc_area_layers_lk OFF;

GO

INSERT INTO shc_portal.shc_chat_message_type_lk (code) VALUES ('TEXT');
INSERT INTO shc_portal.shc_chat_message_type_lk (code) VALUES ('PHOTO');
INSERT INTO shc_portal.shc_chat_message_type_lk (code) VALUES ('VIDEO');
INSERT INTO shc_portal.shc_chat_message_type_lk (code) VALUES ('AUDIO');
INSERT INTO shc_portal.shc_chat_message_type_lk (code) VALUES ('LOCATION');
GO

Use shc_portal
SET IDENTITY_INSERT shc_portal.shc_survey_type_lk ON;
INSERT INTO shc_portal.shc_survey_type_lk(id, code) VALUES (1, 'DAILY');
INSERT INTO shc_portal.shc_survey_type_lk(id, code) VALUES (2, 'END_OF_RITUAL');
SET IDENTITY_INSERT shc_portal.shc_survey_type_lk OFF;
Use shc_portal
SET IDENTITY_INSERT shc_portal.shc_survey_question_lk ON;
INSERT INTO shc_portal.shc_survey_question_lk(id, code, survey_type_code ,question_index, lang , label) VALUES (1,	 'FOOD_SATISFACTION_Q_DAILY', 			'DAILY',1,'en', 'How satisfied are you with the food?');
INSERT INTO shc_portal.shc_survey_question_lk(id, code, survey_type_code ,question_index, lang , label) VALUES (2,	 'FOOD_SATISFACTION_Q_DAILY', 			'DAILY',1,'ar', N'مدى رضاك عن الطعام؟');
INSERT INTO shc_portal.shc_survey_question_lk(id, code, survey_type_code ,question_index, lang , label) VALUES (3,	 'HOUSING_SATISFACTION_Q_DAILY', 		'DAILY',2,'en', 'How satisfied are you with the housing?');
INSERT INTO shc_portal.shc_survey_question_lk(id, code, survey_type_code ,question_index, lang , label) VALUES (4,	 'HOUSING_SATISFACTION_Q_DAILY', 		'DAILY',2,'ar', N'مدى رضاك عن مستوى السكن؟');
INSERT INTO shc_portal.shc_survey_question_lk(id, code, survey_type_code ,question_index, lang , label) VALUES (5,	 'TRANSPORTATION_SATISFACTION_Q_DAILY', 'DAILY',3,'en', 'How satisfied are you with the transportation?');
INSERT INTO shc_portal.shc_survey_question_lk(id, code, survey_type_code ,question_index, lang , label) VALUES (6,	 'TRANSPORTATION_SATISFACTION_Q_DAILY', 'DAILY',3,'ar', N'مدى رضاك عن مستوى المواصلات؟');
INSERT INTO shc_portal.shc_survey_question_lk(id, code, survey_type_code ,question_index, lang , label) VALUES (7,	 'CLEANLINESS_SATISFACTION_Q_DAILY', 	'DAILY',4,'en', 'How satisfied are you with the cleanliness?');
INSERT INTO shc_portal.shc_survey_question_lk(id, code, survey_type_code ,question_index, lang , label) VALUES (8,	 'CLEANLINESS_SATISFACTION_Q_DAILY', 	'DAILY',4,'ar', N'مدى رضاك عن مستوى النظافة؟');
INSERT INTO shc_portal.shc_survey_question_lk(id, code, survey_type_code ,question_index, lang , label) VALUES (9,	 'ORGANIZATION_SATISFACTION_Q_DAILY', 	'DAILY',5,'en', 'How satisfied are you with the ritual organization?');
INSERT INTO shc_portal.shc_survey_question_lk(id, code, survey_type_code ,question_index, lang , label) VALUES (10,	 'ORGANIZATION_SATISFACTION_Q_DAILY', 	'DAILY',5,'ar', N'مدى رضاك عن مستوى تنظيم الشعيرة؟');

INSERT INTO shc_portal.shc_survey_question_lk(id, code, survey_type_code ,question_index, lang , label) VALUES (11, 'FOOD_SATISFACTION_Q_END_OF_RITUAL',        	'END_OF_RITUAL',1,'en', 'How satisfied are you with the food?');
INSERT INTO shc_portal.shc_survey_question_lk(id, code, survey_type_code ,question_index, lang , label) VALUES (12, 'FOOD_SATISFACTION_Q_END_OF_RITUAL', 			'END_OF_RITUAL',1,'ar', N'مدى رضاك عن الطعام؟');
INSERT INTO shc_portal.shc_survey_question_lk(id, code, survey_type_code ,question_index, lang , label) VALUES (13, 'HOUSING_SATISFACTION_Q_END_OF_RITUAL', 		'END_OF_RITUAL',2,'en', 'How satisfied are you with the housing?');
INSERT INTO shc_portal.shc_survey_question_lk(id, code, survey_type_code ,question_index, lang , label) VALUES (14, 'HOUSING_SATISFACTION_Q_END_OF_RITUAL', 		'END_OF_RITUAL',2,'ar', N'مدى رضاك عن مستوى السكن؟');
INSERT INTO shc_portal.shc_survey_question_lk(id, code, survey_type_code ,question_index, lang , label) VALUES (15, 'TRANSPORTATION_SATISFACTION_Q_END_OF_RITUAL', 'END_OF_RITUAL',3,'en', 'How satisfied are you with the transportation?');
INSERT INTO shc_portal.shc_survey_question_lk(id, code, survey_type_code ,question_index, lang , label) VALUES (16, 'TRANSPORTATION_SATISFACTION_Q_END_OF_RITUAL', 'END_OF_RITUAL',3,'ar', N'مدى رضاك عن مستوى المواصلات؟');
INSERT INTO shc_portal.shc_survey_question_lk(id, code, survey_type_code ,question_index, lang , label) VALUES (17, 'CLEANLINESS_SATISFACTION_Q_END_OF_RITUAL', 	'END_OF_RITUAL',4,'en', 'How satisfied are you with the cleanliness?');
INSERT INTO shc_portal.shc_survey_question_lk(id, code, survey_type_code ,question_index, lang , label) VALUES (18, 'CLEANLINESS_SATISFACTION_Q_END_OF_RITUAL', 	'END_OF_RITUAL',4,'ar', N'مدى رضاك عن مستوى النظافة؟');
INSERT INTO shc_portal.shc_survey_question_lk(id, code, survey_type_code ,question_index, lang , label) VALUES (19, 'ORGANIZATION_SATISFACTION_Q_END_OF_RITUAL', 	'END_OF_RITUAL',5,'en', 'How satisfied are you with the ritual organization?');
INSERT INTO shc_portal.shc_survey_question_lk(id, code, survey_type_code ,question_index, lang , label) VALUES (20, 'ORGANIZATION_SATISFACTION_Q_END_OF_RITUAL', 	'END_OF_RITUAL',5,'ar', N'مدى رضاك عن مستوى تنظيم الشعيرة؟');

SET IDENTITY_INSERT shc_portal.shc_survey_question_lk OFF;

GO

SET IDENTITY_INSERT shc_portal.shc_area_layers ON;
INSERT INTO shc_portal.shc_area_layers (id, area_code, layer, creation_date)
VALUES (1, 'MAKKAH', '21.51249,39.83359-21.55241,39.80355-21.55657,39.76527-21.4196,39.68416-21.35095,39.69437-21.30225,39.799-21.2881,39.95169-21.35822,40.03976-21.47081,39.90535', GETDATE());

INSERT INTO shc_portal.shc_area_layers (id, area_code, layer, creation_date)
VALUES (2, 'MADINA', '24.65638,39.60837-24.6542,39.50417-24.63079,39.43911-24.59197,39.39169-24.45442,39.43079-24.38729,39.441-24.33967,39.54563-24.32583,39.69832-24.3944,39.78638-24.47758,39.86543-24.55413,39.81261-24.61815,39.77008-24.6535,39.70879', GETDATE());

INSERT INTO shc_portal.shc_area_layers (id, area_code, layer, creation_date, parent_layer_id)
VALUES (3, 'MAKKAH_HOLY_MOSQUE', '21.42623,39.82861-21.42519,39.8281-21.42403,39.82921-21.42116,39.82733-21.42084,39.82295-21.42267,39.82119-21.42381,39.82071-21.42514,39.82035-21.42609,39.82316-21.42711,39.8272', GETDATE(), 1);

INSERT INTO shc_portal.shc_area_layers (id, area_code, layer, creation_date, parent_layer_id)
VALUES (4, 'MADINA_HOLY_MOSQUE', '24.46536,39.61052-24.46598,39.6076-24.47309,39.60468-24.47567,39.61069-24.47243,39.61833-24.46645,39.62004-24.46536,39.61485', GETDATE(),1);

INSERT INTO shc_portal.shc_area_layers (id, area_code, layer, creation_date, parent_layer_id)
VALUES (5, 'MENA','21.40585,39.87573-21.42263,39.86458-21.42527,39.88621-21.43494,39.89685-21.43418,39.90174-21.43398,39.90655-21.40489,39.91183-21.39642,39.89455', GETDATE(), 1);

INSERT INTO shc_portal.shc_area_layers (id, area_code, layer, creation_date, parent_layer_id)
VALUES (6, 'GAMARAT', '21.42623,39.85265-21.40865,39.86406-21.40274,39.86891-21.40034,39.87822-21.41808,39.88904-21.43741,39.87385-21.43686,39.87095-21.43646,39.86822-21.43606,39.86687', GETDATE(),5);

INSERT INTO shc_portal.shc_area_layers (id, area_code, layer, creation_date, parent_layer_id)
VALUES (7, 'MUZDALIFA',
        '21.4013,39.89213-21.38372,39.90354-21.3754,39.91771-21.38915,39.9311-21.41249,39.91333-21.43014,39.90717-21.42927,39.90238-21.41113,39.90635',
        GETDATE(), 1);

INSERT INTO shc_portal.shc_area_layers (id, area_code, layer, creation_date, parent_layer_id)
VALUES (8, 'ARAFAT',
        '21.32488,39.9232-21.31465,39.96414-21.32248,39.97998-21.33112,39.98929-21.35047,40.00663-21.38388,39.988-21.38336,39.97797-21.37724,39.96845-21.37477,39.96504-21.37181,39.96111',
        GETDATE(), 1);

INSERT INTO shc_portal.shc_area_layers (id, area_code, layer, creation_date, parent_layer_id)
VALUES (9, 'JABAL_ALRAHMA',
        '21.35402,39.98216-21.3553,39.98195-21.35658,39.98319-21.35678,39.98422-21.35686,39.98461-21.35638,39.98555-21.35394,39.98616-21.35254,39.9842-21.3532,39.9831',
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

update shc_portal.shc_config set conf_value = '0 0 23 * * *' where conf_key = 'scheduler.update.applicant.card.status.cron';
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