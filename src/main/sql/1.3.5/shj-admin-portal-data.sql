USE shc_portal
GO

SET IDENTITY_INSERT shc_portal.shc_notification_template_name_lk ON;
INSERT INTO shc_portal.shc_notification_template_name_lk (id, code, lang, label)
VALUES (11, 'RESOLVE_COMPLAINT', 'ar', N'حل شكوى');
INSERT INTO shc_portal.shc_notification_template_name_lk (id, code, lang, label)
VALUES (12, 'RESOLVE_COMPLAINT', 'en', 'Resolve Complaint');
INSERT INTO shc_portal.shc_notification_template_name_lk (id, code, lang, label)
VALUES (13, 'CLOSE_COMPLAINT', 'ar', N'حل شكوى');
INSERT INTO shc_portal.shc_notification_template_name_lk (id, code, lang, label)
VALUES (14, 'CLOSE_COMPLAINT', 'en', 'Close Complaint');
SET IDENTITY_INSERT shc_portal.shc_notification_template_name_lk OFF;
GO

INSERT INTO shc_portal.shc_notification_template (category_code, name_code, status_code, type_code, important,
                                                  action_required, enabled, user_specific, force_sending)
values ('GENERAL', 'RESOLVE_COMPLAINT', 'CONFIRMED', 'SYSTEM_DEFINED', 0, 0, 1, 1, 1);

SELECT @notificationTemplateId =   (SELECT SCOPE_IDENTITY())

INSERT INTO shc_portal.shc_notification_template_content (notification_template_id, lang, title, body)
VALUES (notificationTemplateId, 'AR', N'حل شكوى',
    N'عزيزي ضيف الرحمن نفيدكم بأن راحتكم هي أولويتنا تم دراسة الشكوى المقدمة وتم حلها وفي حالة عدم رضاكم عن الحل أو استمرار الشكوى نأمل رفع شكوى جديدة');
INSERT INTO shc_portal.shc_notification_template_content ( notification_template_id, lang, title, body)
VALUES (notificationTemplateId, 'EN', 'Resolve complaint',
        'Dear applicant your satisfaction is our top priority, your complaint has been investigated and resolved. If the provided resolution is not as per your expectations or if the complaint still not resolved we would request you to raise another complaint');

INSERT INTO shc_portal.shc_notification_template (category_code, name_code, status_code, type_code, important,
                                                  action_required, enabled, user_specific, force_sending)
values ('GENERAL', 'CLOSE_COMPLAINT', 'CONFIRMED', 'SYSTEM_DEFINED', 0, 0, 1, 1, 1);

SELECT @notificationTemplateId =   (SELECT SCOPE_IDENTITY())

INSERT INTO shc_portal.shc_notification_template_content ( notification_template_id, lang, title, body)
VALUES (notificationTemplateId, 'AR', N'حل شكوى',
    N'عزيزي ضيف الرحمن نفيدكم بأن راحتكم هي أولويتنا تم دراسة الشكوى المقدمة وتم إغلاقها وفي حالة استمرار الشكوى نأمل رفع شكوى جديدة بتفاصيل أكثر');
INSERT INTO shc_portal.shc_notification_template_content (notification_template_id, lang, title, body)
VALUES (notificationTemplateId, 'EN', 'Close complaint',
        'Dear applicant your satisfaction is our top priority, your complaint has been investigated and closed. If the complaint is still not resolved we would request you to raise another complaint with more details');

Update shc_portal.shc_notification_template_content SET title = N'حل بلاغ', body = N'عزيزي ضيف الرحمن نفيدكم بأن راحتكم هي أولويتنا تم دراسة البلاغ المقدمة وتم حلها وفي حالة عدم رضاكم عن الحل أو استمرار البلاغ نأمل رفع بلاغ جديدة'
WHERE id = 3

Update shc_portal.shc_notification_template_content SET title = 'Resolve incident', body = 'Dear applicant your satisfaction is our top priority, your incident has been investigated and resolved. If the provided resolution is not as per your expectations or if the incident still not resolved we would request you to raise another incident'
WHERE id = 4

Update shc_portal.shc_notification_template_content SET title = N'حل بلاغ', body = N'عزيزي ضيف الرحمن نفيدكم بأن راحتكم هي أولويتنا تم دراسة البلاغ المقدمة وتم إغلاقها وفي حالة استمرار البلاغ نأمل رفع بلاغ جديدة بتفاصيل أكثر'
WHERE id = 5

Update shc_portal.shc_notification_template_content SET title = 'Close incident', body = 'Dear applicant your satisfaction is our top priority, your incident has been investigated and closed. If the incident is still not resolved we would request you to raise another incident with more details'
WHERE id = 6

GO
