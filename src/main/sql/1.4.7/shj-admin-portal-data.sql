USE shc_portal
GO
-- insert shc_notification_template_name_lk
SET IDENTITY_INSERT shc_portal.shc_notification_template_name_lk ON;

INSERT INTO shc_portal.shc_notification_template_name_lk (id, code, lang, label) VALUES (15, 'PASSWORD_EXPIRATION', 'fr', 'Password Will Expire');
INSERT INTO shc_portal.shc_notification_template_name_lk (id, code, lang, label) VALUES (16, 'PASSWORD_EXPIRATION', 'tr', 'Password Will Expire');
INSERT INTO shc_portal.shc_notification_template_name_lk (id, code, lang, label) VALUES (17, 'PASSWORD_EXPIRATION', 'ms', 'Password Will Expire');
INSERT INTO shc_portal.shc_notification_template_name_lk (id, code, lang, label) VALUES (18, 'PASSWORD_EXPIRATION', 'fa', 'Password Will Expire');

INSERT INTO shc_portal.shc_notification_template_name_lk (id, code, lang, label) VALUES (19, 'OUT_ARAFAT_FENCE', 'fr', 'Out of Arafat Geo Fence');
INSERT INTO shc_portal.shc_notification_template_name_lk (id, code, lang, label) VALUES (20, 'OUT_ARAFAT_FENCE', 'tr', 'Out of Arafat Geo Fence');
INSERT INTO shc_portal.shc_notification_template_name_lk (id, code, lang, label) VALUES (21, 'OUT_ARAFAT_FENCE', 'ms', 'Out of Arafat Geo Fence');
INSERT INTO shc_portal.shc_notification_template_name_lk (id, code, lang, label) VALUES (22, 'OUT_ARAFAT_FENCE', 'fa', 'Out of Arafat Geo Fence');

INSERT INTO shc_portal.shc_notification_template_name_lk (id, code, lang, label) VALUES (23, 'DAILY_SURVEY', 'fr', 'Evaluate Daily Service');
INSERT INTO shc_portal.shc_notification_template_name_lk (id, code, lang, label) VALUES (24, 'DAILY_SURVEY', 'tr', 'Evaluate Daily Service');
INSERT INTO shc_portal.shc_notification_template_name_lk (id, code, lang, label) VALUES (25, 'DAILY_SURVEY', 'ms', 'Evaluate Daily Service');
INSERT INTO shc_portal.shc_notification_template_name_lk (id, code, lang, label) VALUES (26, 'DAILY_SURVEY', 'fa', 'Evaluate Daily Service');

INSERT INTO shc_portal.shc_notification_template_name_lk (id, code, lang, label) VALUES (27, 'RESOLVE_INCIDENT', 'fr', 'Resolve Incident');
INSERT INTO shc_portal.shc_notification_template_name_lk (id, code, lang, label) VALUES (28, 'RESOLVE_INCIDENT', 'tr', 'Resolve Incident');
INSERT INTO shc_portal.shc_notification_template_name_lk (id, code, lang, label) VALUES (29, 'RESOLVE_INCIDENT', 'ms', 'Resolve Incident');
INSERT INTO shc_portal.shc_notification_template_name_lk (id, code, lang, label) VALUES (30, 'RESOLVE_INCIDENT', 'fa', 'Resolve Incident');

INSERT INTO shc_portal.shc_notification_template_name_lk (id, code, lang, label) VALUES (31, 'CLOSE_INCIDENT', 'fr', 'Close Incident');
INSERT INTO shc_portal.shc_notification_template_name_lk (id, code, lang, label) VALUES (32, 'CLOSE_INCIDENT', 'tr', 'Close Incident');
INSERT INTO shc_portal.shc_notification_template_name_lk (id, code, lang, label) VALUES (33, 'CLOSE_INCIDENT', 'ms', 'Close Incident');
INSERT INTO shc_portal.shc_notification_template_name_lk (id, code, lang, label) VALUES (34, 'CLOSE_INCIDENT', 'fa', 'Close Incident');

INSERT INTO shc_portal.shc_notification_template_name_lk (id, code, lang, label) VALUES (35, 'RESOLVE_COMPLAINT', 'fr', 'Resolve Complaint');
INSERT INTO shc_portal.shc_notification_template_name_lk (id, code, lang, label) VALUES (36, 'RESOLVE_COMPLAINT', 'tr', 'Resolve Complaint');
INSERT INTO shc_portal.shc_notification_template_name_lk (id, code, lang, label) VALUES (37, 'RESOLVE_COMPLAINT', 'ms', 'Resolve Complaint');
INSERT INTO shc_portal.shc_notification_template_name_lk (id, code, lang, label) VALUES (38, 'RESOLVE_COMPLAINT', 'fa', 'Resolve Complaint');

INSERT INTO shc_portal.shc_notification_template_name_lk (id, code, lang, label) VALUES (39, 'CLOSE_COMPLAINT', 'fr', 'Close Complaint');
INSERT INTO shc_portal.shc_notification_template_name_lk (id, code, lang, label) VALUES (40, 'CLOSE_COMPLAINT', 'tr', 'Close Complaint');
INSERT INTO shc_portal.shc_notification_template_name_lk (id, code, lang, label) VALUES (41, 'CLOSE_COMPLAINT', 'ms', 'Close Complaint');
INSERT INTO shc_portal.shc_notification_template_name_lk (id, code, lang, label) VALUES (42, 'CLOSE_COMPLAINT', 'fa', 'Close Complaint');

SET IDENTITY_INSERT shc_portal.shc_notification_template_name_lk OFF;
GO
-- insert shc_notification_template_status_lk

SET IDENTITY_INSERT shc_portal.shc_notification_template_status_lk ON;

INSERT INTO shc_portal.shc_notification_template_status_lk (id, code, lang, label) VALUES (5, 'CONFIRMED', 'fr', 'Confirmed');
INSERT INTO shc_portal.shc_notification_template_status_lk (id, code, lang, label) VALUES (6, 'CONFIRMED', 'tr', 'Confirmed');
INSERT INTO shc_portal.shc_notification_template_status_lk (id, code, lang, label) VALUES (7, 'CONFIRMED', 'ms', 'Confirmed');
INSERT INTO shc_portal.shc_notification_template_status_lk (id, code, lang, label) VALUES (8, 'CONFIRMED', 'fa', 'Confirmed');

INSERT INTO shc_portal.shc_notification_template_status_lk (id, code, lang, label) VALUES (9, 'DRAFT', 'fr', 'Draft');
INSERT INTO shc_portal.shc_notification_template_status_lk (id, code, lang, label) VALUES (10, 'DRAFT', 'tr', 'Draft');
INSERT INTO shc_portal.shc_notification_template_status_lk (id, code, lang, label) VALUES (11, 'DRAFT', 'ms', 'Draft');
INSERT INTO shc_portal.shc_notification_template_status_lk (id, code, lang, label) VALUES (12, 'DRAFT', 'fa', 'Draft');

SET IDENTITY_INSERT shc_portal.shc_notification_template_status_lk OFF;
GO
-- insert shc_notification_template_type_lk

SET IDENTITY_INSERT shc_portal.shc_notification_template_type_lk ON;

INSERT INTO shc_portal.shc_notification_template_type_lk (id, code, lang, label) VALUES (5, 'SYSTEM_DEFINED', 'fr', 'System Defined');
INSERT INTO shc_portal.shc_notification_template_type_lk (id, code, lang, label) VALUES (6, 'SYSTEM_DEFINED', 'tr', 'System Defined');
INSERT INTO shc_portal.shc_notification_template_type_lk (id, code, lang, label) VALUES (7, 'SYSTEM_DEFINED', 'ms', 'System Defined');
INSERT INTO shc_portal.shc_notification_template_type_lk (id, code, lang, label) VALUES (8, 'SYSTEM_DEFINED', 'fa', 'System Defined');

INSERT INTO shc_portal.shc_notification_template_type_lk (id, code, lang, label) VALUES (9, 'USER_DEFINED', 'fr', 'User Defined');
INSERT INTO shc_portal.shc_notification_template_type_lk (id, code, lang, label) VALUES (10, 'USER_DEFINED', 'tr', 'User Defined');
INSERT INTO shc_portal.shc_notification_template_type_lk (id, code, lang, label) VALUES (11, 'USER_DEFINED', 'ms', 'User Defined');
INSERT INTO shc_portal.shc_notification_template_type_lk (id, code, lang, label) VALUES (12, 'USER_DEFINED', 'fa', 'User Defined');

SET IDENTITY_INSERT shc_portal.shc_notification_template_type_lk OFF;
GO
-- insert shc_package_type_lk

SET IDENTITY_INSERT shc_portal.shc_package_type_lk ON;

INSERT INTO shc_portal.shc_package_type_lk (id, code, lang, label) VALUES (7, 'ECONOMIC', 'fr', 'Economic');
INSERT INTO shc_portal.shc_package_type_lk (id, code, lang, label) VALUES (8, 'ECONOMIC', 'tr', 'Economic');
INSERT INTO shc_portal.shc_package_type_lk (id, code, lang, label) VALUES (9, 'ECONOMIC', 'ms', 'Economic');
INSERT INTO shc_portal.shc_package_type_lk (id, code, lang, label) VALUES (10, 'ECONOMIC', 'fa', 'Economic');

INSERT INTO shc_portal.shc_package_type_lk (id, code, lang, label) VALUES (11, 'NORMAL', 'fr', 'Normal');
INSERT INTO shc_portal.shc_package_type_lk (id, code, lang, label) VALUES (12, 'NORMAL', 'tr', 'Normal');
INSERT INTO shc_portal.shc_package_type_lk (id, code, lang, label) VALUES (13, 'NORMAL', 'ms', 'Normal');
INSERT INTO shc_portal.shc_package_type_lk (id, code, lang, label) VALUES (14, 'NORMAL', 'fa', 'Normal');

INSERT INTO shc_portal.shc_package_type_lk (id, code, lang, label) VALUES (15, 'VIP', 'fr', 'VIP');
INSERT INTO shc_portal.shc_package_type_lk (id, code, lang, label) VALUES (16, 'VIP', 'tr', 'VIP');
INSERT INTO shc_portal.shc_package_type_lk (id, code, lang, label) VALUES (17, 'VIP', 'ms', 'VIP');
INSERT INTO shc_portal.shc_package_type_lk (id, code, lang, label) VALUES (18, 'VIP', 'fa', 'VIP');

SET IDENTITY_INSERT shc_portal.shc_package_type_lk OFF;
GO
-- insert shc_print_request_status_lk
-- Check verify the last id of  print request request status lk, as per the QA last id is 16 but sequence is not correct
SET IDENTITY_INSERT shc_portal.shc_print_request_status_lk ON;

INSERT INTO shc_portal.shc_print_request_status_lk (id, code, lang, label) VALUES (17, 'NEW', 'fr', 'New');
INSERT INTO shc_portal.shc_print_request_status_lk (id, code, lang, label) VALUES (18, 'NEW', 'tr', 'New');
INSERT INTO shc_portal.shc_print_request_status_lk (id, code, lang, label) VALUES (19, 'NEW', 'ms', 'New');
INSERT INTO shc_portal.shc_print_request_status_lk (id, code, lang, label) VALUES (20, 'NEW', 'fa', 'New');

INSERT INTO shc_portal.shc_print_request_status_lk (id, code, lang, label) VALUES (21, 'CONFIRMED', 'fr', 'Confirmed');
INSERT INTO shc_portal.shc_print_request_status_lk (id, code, lang, label) VALUES (22, 'CONFIRMED', 'tr', 'Confirmed');
INSERT INTO shc_portal.shc_print_request_status_lk (id, code, lang, label) VALUES (23, 'CONFIRMED', 'ms', 'Confirmed');
INSERT INTO shc_portal.shc_print_request_status_lk (id, code, lang, label) VALUES (24, 'CONFIRMED', 'fa', 'Confirmed');

INSERT INTO shc_portal.shc_print_request_status_lk (id, code, lang, label) VALUES (25, 'SENT_TO_PRINTING', 'fr', 'Sent to printing');
INSERT INTO shc_portal.shc_print_request_status_lk (id, code, lang, label) VALUES (26, 'SENT_TO_PRINTING', 'tr', 'Sent to printing');
INSERT INTO shc_portal.shc_print_request_status_lk (id, code, lang, label) VALUES (27, 'SENT_TO_PRINTING', 'ms', 'Sent to printing');
INSERT INTO shc_portal.shc_print_request_status_lk (id, code, lang, label) VALUES (28, 'SENT_TO_PRINTING', 'fa', 'Sent to printing');

INSERT INTO shc_portal.shc_print_request_status_lk (id, code, lang, label) VALUES (29, 'CANCELLED', 'fr', 'Cancelled');
INSERT INTO shc_portal.shc_print_request_status_lk (id, code, lang, label) VALUES (30, 'CANCELLED', 'tr', 'Cancelled');
INSERT INTO shc_portal.shc_print_request_status_lk (id, code, lang, label) VALUES (31, 'CANCELLED', 'ms', 'Cancelled');
INSERT INTO shc_portal.shc_print_request_status_lk (id, code, lang, label) VALUES (32, 'CANCELLED', 'fa', 'Cancelled');

INSERT INTO shc_portal.shc_print_request_status_lk (id, code, lang, label) VALUES (33, 'PRINTED', 'fr', 'Printed');
INSERT INTO shc_portal.shc_print_request_status_lk (id, code, lang, label) VALUES (34, 'PRINTED', 'tr', 'Printed');
INSERT INTO shc_portal.shc_print_request_status_lk (id, code, lang, label) VALUES (35, 'PRINTED', 'ms', 'Printed');
INSERT INTO shc_portal.shc_print_request_status_lk (id, code, lang, label) VALUES (36, 'PRINTED', 'fa', 'Printed');

INSERT INTO shc_portal.shc_print_request_status_lk (id, code, lang, label) VALUES (37, 'DISTRIBUTED', 'fr', 'Distributed');
INSERT INTO shc_portal.shc_print_request_status_lk (id, code, lang, label) VALUES (38, 'DISTRIBUTED', 'tr', 'Distributed');
INSERT INTO shc_portal.shc_print_request_status_lk (id, code, lang, label) VALUES (39, 'DISTRIBUTED', 'ms', 'Distributed');
INSERT INTO shc_portal.shc_print_request_status_lk (id, code, lang, label) VALUES (40, 'DISTRIBUTED', 'fa', 'Distributed');

SET IDENTITY_INSERT shc_portal.shc_print_request_status_lk OFF;

GO
-- insert shc_relative_relationship_lk

SET IDENTITY_INSERT shc_portal.shc_relative_relationship_lk ON;

INSERT INTO shc_portal.shc_relative_relationship_lk (id, code, lang, label) VALUES (21, 'MOTHER', 'fr', 'Mother');
INSERT INTO shc_portal.shc_relative_relationship_lk (id, code, lang, label) VALUES (22, 'MOTHER', 'tr', 'Mother');
INSERT INTO shc_portal.shc_relative_relationship_lk (id, code, lang, label) VALUES (23, 'MOTHER', 'ms', 'Mother');
INSERT INTO shc_portal.shc_relative_relationship_lk (id, code, lang, label) VALUES (24, 'MOTHER', 'fa', 'Mother');

INSERT INTO shc_portal.shc_relative_relationship_lk (id, code, lang, label) VALUES (25, 'FATHER', 'fr', 'Father');
INSERT INTO shc_portal.shc_relative_relationship_lk (id, code, lang, label) VALUES (26, 'FATHER', 'tr', 'Father');
INSERT INTO shc_portal.shc_relative_relationship_lk (id, code, lang, label) VALUES (27, 'FATHER', 'ms', 'Father');
INSERT INTO shc_portal.shc_relative_relationship_lk (id, code, lang, label) VALUES (28, 'FATHER', 'fa', 'Father');

INSERT INTO shc_portal.shc_relative_relationship_lk (id, code, lang, label) VALUES (29, 'WIFE', 'fr', 'Wife');
INSERT INTO shc_portal.shc_relative_relationship_lk (id, code, lang, label) VALUES (30, 'WIFE', 'tr', 'Wife');
INSERT INTO shc_portal.shc_relative_relationship_lk (id, code, lang, label) VALUES (31, 'WIFE', 'ms', 'Wife');
INSERT INTO shc_portal.shc_relative_relationship_lk (id, code, lang, label) VALUES (32, 'WIFE', 'fa', 'Wife');

INSERT INTO shc_portal.shc_relative_relationship_lk (id, code, lang, label) VALUES (33, 'HUSBAND', 'fr', 'Husband');
INSERT INTO shc_portal.shc_relative_relationship_lk (id, code, lang, label) VALUES (34, 'HUSBAND', 'tr', 'Husband');
INSERT INTO shc_portal.shc_relative_relationship_lk (id, code, lang, label) VALUES (35, 'HUSBAND', 'ms', 'Husband');
INSERT INTO shc_portal.shc_relative_relationship_lk (id, code, lang, label) VALUES (36, 'HUSBAND', 'fa', 'Husband');

INSERT INTO shc_portal.shc_relative_relationship_lk (id, code, lang, label) VALUES (37, 'SON', 'fr', 'Son');
INSERT INTO shc_portal.shc_relative_relationship_lk (id, code, lang, label) VALUES (38, 'SON', 'tr', 'Son');
INSERT INTO shc_portal.shc_relative_relationship_lk (id, code, lang, label) VALUES (39, 'SON', 'ms', 'Son');
INSERT INTO shc_portal.shc_relative_relationship_lk (id, code, lang, label) VALUES (40, 'SON', 'fa', 'Son');

INSERT INTO shc_portal.shc_relative_relationship_lk (id, code, lang, label) VALUES (41, 'DAUGHTER', 'fr', 'Daughter');
INSERT INTO shc_portal.shc_relative_relationship_lk (id, code, lang, label) VALUES (42, 'DAUGHTER', 'tr', 'Daughter');
INSERT INTO shc_portal.shc_relative_relationship_lk (id, code, lang, label) VALUES (43, 'DAUGHTER', 'ms', 'Daughter');
INSERT INTO shc_portal.shc_relative_relationship_lk (id, code, lang, label) VALUES (44, 'DAUGHTER', 'fa', 'Daughter');

INSERT INTO shc_portal.shc_relative_relationship_lk (id, code, lang, label) VALUES (45, 'BROTHER', 'fr', 'Brother');
INSERT INTO shc_portal.shc_relative_relationship_lk (id, code, lang, label) VALUES (46, 'BROTHER', 'tr', 'Brother');
INSERT INTO shc_portal.shc_relative_relationship_lk (id, code, lang, label) VALUES (47, 'BROTHER', 'ms', 'Brother');
INSERT INTO shc_portal.shc_relative_relationship_lk (id, code, lang, label) VALUES (48, 'BROTHER', 'fa', 'Brother');

INSERT INTO shc_portal.shc_relative_relationship_lk (id, code, lang, label) VALUES (49, 'SISTER', 'fr', 'Sister');
INSERT INTO shc_portal.shc_relative_relationship_lk (id, code, lang, label) VALUES (50, 'SISTER', 'tr', 'Sister');
INSERT INTO shc_portal.shc_relative_relationship_lk (id, code, lang, label) VALUES (51, 'SISTER', 'ms', 'Sister');
INSERT INTO shc_portal.shc_relative_relationship_lk (id, code, lang, label) VALUES (52, 'SISTER', 'fa', 'Sister');

INSERT INTO shc_portal.shc_relative_relationship_lk (id, code, lang, label) VALUES (53, 'RELATIVE', 'fr', 'Relative');
INSERT INTO shc_portal.shc_relative_relationship_lk (id, code, lang, label) VALUES (54, 'RELATIVE', 'tr', 'Relative');
INSERT INTO shc_portal.shc_relative_relationship_lk (id, code, lang, label) VALUES (55, 'RELATIVE', 'ms', 'Relative');
INSERT INTO shc_portal.shc_relative_relationship_lk (id, code, lang, label) VALUES (56, 'RELATIVE', 'fa', 'Relative');

INSERT INTO shc_portal.shc_relative_relationship_lk (id, code, lang, label) VALUES (57, 'COMPANION', 'fr', 'Companion');
INSERT INTO shc_portal.shc_relative_relationship_lk (id, code, lang, label) VALUES (58, 'COMPANION', 'tr', 'Companion');
INSERT INTO shc_portal.shc_relative_relationship_lk (id, code, lang, label) VALUES (59, 'COMPANION', 'ms', 'Companion');
INSERT INTO shc_portal.shc_relative_relationship_lk (id, code, lang, label) VALUES (60, 'COMPANION', 'fa', 'Companion');

SET IDENTITY_INSERT shc_portal.shc_relative_relationship_lk OFF;
GO
-- insert shc_religious_occasions_day_lk

SET IDENTITY_INSERT shc_portal.shc_religious_occasions_day_lk ON;

INSERT INTO shc_portal.shc_religious_occasions_day_lk (id, code, lang, label) VALUES (7, '11_20', 'fr', 'First days of Tashriq');
INSERT INTO shc_portal.shc_religious_occasions_day_lk (id, code, lang, label) VALUES (8, '11_20', 'tr', 'First days of Tashriq');
INSERT INTO shc_portal.shc_religious_occasions_day_lk (id, code, lang, label) VALUES (9, '11_20', 'ms', 'First days of Tashriq');
INSERT INTO shc_portal.shc_religious_occasions_day_lk (id, code, lang, label) VALUES (10, '11_20', 'fa', 'First days of Tashriq');

INSERT INTO shc_portal.shc_religious_occasions_day_lk (id, code, lang, label) VALUES (11, '12_20', 'fr', 'Second days of Tashriq');
INSERT INTO shc_portal.shc_religious_occasions_day_lk (id, code, lang, label) VALUES (12, '12_20', 'tr', 'Second days of Tashriq');
INSERT INTO shc_portal.shc_religious_occasions_day_lk (id, code, lang, label) VALUES (13, '12_20', 'ms', 'Second days of Tashriq');
INSERT INTO shc_portal.shc_religious_occasions_day_lk (id, code, lang, label) VALUES (14, '12_20', 'fa', 'Second days of Tashriq');

INSERT INTO shc_portal.shc_religious_occasions_day_lk (id, code, lang, label) VALUES (15, '13_20', 'fr', 'Third days of Tashriq');
INSERT INTO shc_portal.shc_religious_occasions_day_lk (id, code, lang, label) VALUES (16, '13_20', 'tr', 'Third days of Tashriq');
INSERT INTO shc_portal.shc_religious_occasions_day_lk (id, code, lang, label) VALUES (17, '13_20', 'ms', 'Third days of Tashriq');
INSERT INTO shc_portal.shc_religious_occasions_day_lk (id, code, lang, label) VALUES (18, '13_20', 'fa', 'Third days of Tashriq');

SET IDENTITY_INSERT shc_portal.shc_religious_occasions_day_lk OFF;
GO
-- insert shc_ritual_type_lk
SET IDENTITY_INSERT shc_portal.shc_ritual_type_lk ON;

INSERT INTO shc_portal.shc_ritual_type_lk (id, code, lang, label) VALUES (11, 'EXTERNAL_HAJJ', 'fr', 'External Hajj');
INSERT INTO shc_portal.shc_ritual_type_lk (id, code, lang, label) VALUES (12, 'EXTERNAL_HAJJ', 'tr', 'External Hajj');
INSERT INTO shc_portal.shc_ritual_type_lk (id, code, lang, label) VALUES (13, 'EXTERNAL_HAJJ', 'ms', 'External Hajj');
INSERT INTO shc_portal.shc_ritual_type_lk (id, code, lang, label) VALUES (14, 'EXTERNAL_HAJJ', 'fa', 'External Hajj');

INSERT INTO shc_portal.shc_ritual_type_lk (id, code, lang, label) VALUES (15, 'INTERNAL_HAJJ', 'fr', 'Internal Hajj');
INSERT INTO shc_portal.shc_ritual_type_lk (id, code, lang, label) VALUES (16, 'INTERNAL_HAJJ', 'tr', 'Internal Hajj');
INSERT INTO shc_portal.shc_ritual_type_lk (id, code, lang, label) VALUES (17, 'INTERNAL_HAJJ', 'ms', 'Internal Hajj');
INSERT INTO shc_portal.shc_ritual_type_lk (id, code, lang, label) VALUES (18, 'INTERNAL_HAJJ', 'fa', 'Internal Hajj');

INSERT INTO shc_portal.shc_ritual_type_lk (id, code, lang, label) VALUES (19, 'COURTESY_HAJJ', 'fr', 'Courtesy Hajj');
INSERT INTO shc_portal.shc_ritual_type_lk (id, code, lang, label) VALUES (20, 'COURTESY_HAJJ', 'tr', 'Courtesy Hajj');
INSERT INTO shc_portal.shc_ritual_type_lk (id, code, lang, label) VALUES (21, 'COURTESY_HAJJ', 'ms', 'Courtesy Hajj');
INSERT INTO shc_portal.shc_ritual_type_lk (id, code, lang, label) VALUES (22, 'COURTESY_HAJJ', 'fa', 'Courtesy Hajj');

INSERT INTO shc_portal.shc_ritual_type_lk (id, code, lang, label) VALUES (23, 'EXTERNAL_UMRAH', 'fr', 'External Umrah');
INSERT INTO shc_portal.shc_ritual_type_lk (id, code, lang, label) VALUES (24, 'EXTERNAL_UMRAH', 'tr', 'External Umrah');
INSERT INTO shc_portal.shc_ritual_type_lk (id, code, lang, label) VALUES (25, 'EXTERNAL_UMRAH', 'ms', 'External Umrah');
INSERT INTO shc_portal.shc_ritual_type_lk (id, code, lang, label) VALUES (26, 'EXTERNAL_UMRAH', 'fa', 'External Umrah');

INSERT INTO shc_portal.shc_ritual_type_lk (id, code, lang, label) VALUES (27, 'INTERNAL_UMRAH', 'fr', 'Internal Umrah');
INSERT INTO shc_portal.shc_ritual_type_lk (id, code, lang, label) VALUES (28, 'INTERNAL_UMRAH', 'tr', 'Internal Umrah');
INSERT INTO shc_portal.shc_ritual_type_lk (id, code, lang, label) VALUES (29, 'INTERNAL_UMRAH', 'ms', 'Internal Umrah');
INSERT INTO shc_portal.shc_ritual_type_lk (id, code, lang, label) VALUES (30, 'INTERNAL_UMRAH', 'fa', 'Internal Umrah');

SET IDENTITY_INSERT shc_portal.shc_ritual_type_lk OFF;
GO
-- insert shc_suggested_supplication_lk
SET IDENTITY_INSERT shc_portal.shc_suggested_supplication_lk ON;

INSERT INTO shc_portal.shc_suggested_supplication_lk (id, code, lang, label) VALUES (9, 'SUGGESTEDSUPPLICATION1', 'fr', 'Glory be to Allah');
INSERT INTO shc_portal.shc_suggested_supplication_lk (id, code, lang, label) VALUES (10, 'SUGGESTEDSUPPLICATION1', 'tr', 'Glory be to Allah');
INSERT INTO shc_portal.shc_suggested_supplication_lk (id, code, lang, label) VALUES (11, 'SUGGESTEDSUPPLICATION1', 'ms', 'Glory be to Allah');
INSERT INTO shc_portal.shc_suggested_supplication_lk (id, code, lang, label) VALUES (12, 'SUGGESTEDSUPPLICATION1', 'fa', 'Glory be to Allah');

INSERT INTO shc_portal.shc_suggested_supplication_lk (id, code, lang, label) VALUES (13, 'SUGGESTEDSUPPLICATION2', 'fr', 'Praise be to Allah');
INSERT INTO shc_portal.shc_suggested_supplication_lk (id, code, lang, label) VALUES (14, 'SUGGESTEDSUPPLICATION2', 'tr', 'Praise be to Allah');
INSERT INTO shc_portal.shc_suggested_supplication_lk (id, code, lang, label) VALUES (15, 'SUGGESTEDSUPPLICATION2', 'ms', 'Praise be to Allah');
INSERT INTO shc_portal.shc_suggested_supplication_lk (id, code, lang, label) VALUES (16, 'SUGGESTEDSUPPLICATION2', 'fa', 'Praise be to Allah');

INSERT INTO shc_portal.shc_suggested_supplication_lk (id, code, lang, label) VALUES (17, 'SUGGESTEDSUPPLICATION3', 'fr', 'I seek forgiveness from Allah');
INSERT INTO shc_portal.shc_suggested_supplication_lk (id, code, lang, label) VALUES (18, 'SUGGESTEDSUPPLICATION3', 'tr', 'I seek forgiveness from Allah');
INSERT INTO shc_portal.shc_suggested_supplication_lk (id, code, lang, label) VALUES (19, 'SUGGESTEDSUPPLICATION3', 'ms', 'I seek forgiveness from Allah');
INSERT INTO shc_portal.shc_suggested_supplication_lk (id, code, lang, label) VALUES (20, 'SUGGESTEDSUPPLICATION3', 'fa', 'I seek forgiveness from Allah');

INSERT INTO shc_portal.shc_suggested_supplication_lk (id, code, lang, label) VALUES (21, 'SUGGESTEDSUPPLICATION4', 'fr', 'There is no power or strength except with Allah');
INSERT INTO shc_portal.shc_suggested_supplication_lk (id, code, lang, label) VALUES (22, 'SUGGESTEDSUPPLICATION4', 'tr', 'There is no power or strength except with Allah');
INSERT INTO shc_portal.shc_suggested_supplication_lk (id, code, lang, label) VALUES (23, 'SUGGESTEDSUPPLICATION4', 'ms', 'There is no power or strength except with Allah');
INSERT INTO shc_portal.shc_suggested_supplication_lk (id, code, lang, label) VALUES (24, 'SUGGESTEDSUPPLICATION4', 'fa', 'There is no power or strength except with Allah');

SET IDENTITY_INSERT shc_portal.shc_suggested_supplication_lk OFF;
GO
-- insert shc_suggested_supplication_lk
SET IDENTITY_INSERT shc_portal.shc_supplication_lk ON;

INSERT INTO shc_portal.shc_supplication_lk (id, code, lang, label, type, counter)
VALUES (13, 'MORNING_SUPPLICATION_1', 'fr', 'Allah! There is no ilah (deity worthy of worship) but He The Living, The Eternal One. Neither slumber, nor sleep overtakes Him. To Him belongs whatever is in the Heavens and on the earth. Who is he that can intercede with Him but by His permission? He knows what is before and what is behind them. They encompass nothing of His knowledge which He will. His Throne extends over the Heavens and the Earth, and the preservation of both does not weary Him. He is the Exalted The Immense One.', 'Morning Supplications', 3);
INSERT INTO shc_portal.shc_supplication_lk (id, code, lang, label, type, counter)
VALUES (14, 'MORNING_SUPPLICATION_1', 'tr', 'Allah! There is no ilah (deity worthy of worship) but He The Living, The Eternal One. Neither slumber, nor sleep overtakes Him. To Him belongs whatever is in the Heavens and on the earth. Who is he that can intercede with Him but by His permission? He knows what is before and what is behind them. They encompass nothing of His knowledge which He will. His Throne extends over the Heavens and the Earth, and the preservation of both does not weary Him. He is the Exalted The Immense One.', 'Morning Supplications', 3);
INSERT INTO shc_portal.shc_supplication_lk (id, code, lang, label, type, counter)
VALUES (15, 'MORNING_SUPPLICATION_1', 'ms', 'Allah! There is no ilah (deity worthy of worship) but He The Living, The Eternal One. Neither slumber, nor sleep overtakes Him. To Him belongs whatever is in the Heavens and on the earth. Who is he that can intercede with Him but by His permission? He knows what is before and what is behind them. They encompass nothing of His knowledge which He will. His Throne extends over the Heavens and the Earth, and the preservation of both does not weary Him. He is the Exalted The Immense One.', 'Morning Supplications', 3);
INSERT INTO shc_portal.shc_supplication_lk (id, code, lang, label, type, counter)
VALUES (16, 'MORNING_SUPPLICATION_1', 'fa', 'Allah! There is no ilah (deity worthy of worship) but He The Living, The Eternal One. Neither slumber, nor sleep overtakes Him. To Him belongs whatever is in the Heavens and on the earth. Who is he that can intercede with Him but by His permission? He knows what is before and what is behind them. They encompass nothing of His knowledge which He will. His Throne extends over the Heavens and the Earth, and the preservation of both does not weary Him. He is the Exalted The Immense One.', 'Morning Supplications', 3);

INSERT INTO shc_portal.shc_supplication_lk (id, code, lang, label, type, counter)
VALUES (17, 'MORNING_SUPPLICATION_2', 'fr', 'O Allah, I seek Your forgiveness and Your protection in this world and the next?O Allah, I seek Your forgiveness and Your protection in my religion, in my worldly affairs, in my family and in my wealth.
O Allah, conceal my secrets and preserve me from anguish,O Allah, guard me from what is in front of me and behind me, from my left, and from my right, and from above me. I seek refuge in Your Greatness from being struck down from beneath me.', 'Morning Supplications', 3);
INSERT INTO shc_portal.shc_supplication_lk (id, code, lang, label, type, counter)
VALUES (18, 'MORNING_SUPPLICATION_2', 'tr', 'O Allah, I seek Your forgiveness and Your protection in this world and the next?O Allah, I seek Your forgiveness and Your protection in my religion, in my worldly affairs, in my family and in my wealth.
O Allah, conceal my secrets and preserve me from anguish,O Allah, guard me from what is in front of me and behind me, from my left, and from my right, and from above me. I seek refuge in Your Greatness from being struck down from beneath me.', 'Morning Supplications', 3);
INSERT INTO shc_portal.shc_supplication_lk (id, code, lang, label, type, counter)
VALUES (19, 'MORNING_SUPPLICATION_2', 'ms', 'O Allah, I seek Your forgiveness and Your protection in this world and the next?O Allah, I seek Your forgiveness and Your protection in my religion, in my worldly affairs, in my family and in my wealth.
O Allah, conceal my secrets and preserve me from anguish,O Allah, guard me from what is in front of me and behind me, from my left, and from my right, and from above me. I seek refuge in Your Greatness from being struck down from beneath me.', 'Morning Supplications', 3);
INSERT INTO shc_portal.shc_supplication_lk (id, code, lang, label, type, counter)
VALUES (20, 'MORNING_SUPPLICATION_2', 'fa', 'O Allah, I seek Your forgiveness and Your protection in this world and the next?O Allah, I seek Your forgiveness and Your protection in my religion, in my worldly affairs, in my family and in my wealth.
O Allah, conceal my secrets and preserve me from anguish,O Allah, guard me from what is in front of me and behind me, from my left, and from my right, and from above me. I seek refuge in Your Greatness from being struck down from beneath me.', 'Morning Supplications', 3);

INSERT INTO shc_portal.shc_supplication_lk (id, code, lang, label, type, counter)
VALUES (21, 'MORNING_SUPPLICATION_3', 'fr', 'We have reached the morning and at this very time unto Allah belongs all sovereignty, and all praise is for Allah. None has the right to be worshipped except Allah, alone, without partner, to Him belongs all sovereignty and praise and He is over all things omnipotent. My Lord, I ask You for the good of this day and the good of what follows it and I take refuge in You from the evil of this day and the evil of what follows it. My Lord, I take refuge in You from laziness and senility. My Lord, I take refuge in You from torment in the Fire and punishment in the grave.', 'Morning Supplications', 3);
INSERT INTO shc_portal.shc_supplication_lk (id, code, lang, label, type, counter)
VALUES (22, 'MORNING_SUPPLICATION_3', 'tr', 'We have reached the morning and at this very time unto Allah belongs all sovereignty, and all praise is for Allah. None has the right to be worshipped except Allah, alone, without partner, to Him belongs all sovereignty and praise and He is over all things omnipotent. My Lord, I ask You for the good of this day and the good of what follows it and I take refuge in You from the evil of this day and the evil of what follows it. My Lord, I take refuge in You from laziness and senility. My Lord, I take refuge in You from torment in the Fire and punishment in the grave.', 'Morning Supplications', 3);
INSERT INTO shc_portal.shc_supplication_lk (id, code, lang, label, type, counter)
VALUES (23, 'MORNING_SUPPLICATION_3', 'ms', 'We have reached the morning and at this very time unto Allah belongs all sovereignty, and all praise is for Allah. None has the right to be worshipped except Allah, alone, without partner, to Him belongs all sovereignty and praise and He is over all things omnipotent. My Lord, I ask You for the good of this day and the good of what follows it and I take refuge in You from the evil of this day and the evil of what follows it. My Lord, I take refuge in You from laziness and senility. My Lord, I take refuge in You from torment in the Fire and punishment in the grave.', 'Morning Supplications', 3);
INSERT INTO shc_portal.shc_supplication_lk (id, code, lang, label, type, counter)
VALUES (24, 'MORNING_SUPPLICATION_3', 'fa', 'We have reached the morning and at this very time unto Allah belongs all sovereignty, and all praise is for Allah. None has the right to be worshipped except Allah, alone, without partner, to Him belongs all sovereignty and praise and He is over all things omnipotent. My Lord, I ask You for the good of this day and the good of what follows it and I take refuge in You from the evil of this day and the evil of what follows it. My Lord, I take refuge in You from laziness and senility. My Lord, I take refuge in You from torment in the Fire and punishment in the grave.', 'Morning Supplications', 3);

INSERT INTO shc_portal.shc_supplication_lk (id, code, lang, label, type, counter)
VALUES (25, 'EVENING_SUPPLICATION_1', 'fr', 'We have reached the evening and at this very time unto Allah belongs all sovereignty, and all praise is for Allah. None has the right to be worshipped except Allah, alone, without partner, to Him belongs all sovereignty and praise and He is over all things omnipotent. My Lord, I ask You for the good of this night and the good of what follows it and I take refuge in You from the evil of this night and the evil of what follows it. My Lord, I take refuge in You from laziness and senility. My Lord, I take refuge in You from torment in the Fire and punishment in the grave.', 'Evening Supplications', 3);
INSERT INTO shc_portal.shc_supplication_lk (id, code, lang, label, type, counter)
VALUES (26, 'EVENING_SUPPLICATION_1', 'tr', 'We have reached the evening and at this very time unto Allah belongs all sovereignty, and all praise is for Allah. None has the right to be worshipped except Allah, alone, without partner, to Him belongs all sovereignty and praise and He is over all things omnipotent. My Lord, I ask You for the good of this night and the good of what follows it and I take refuge in You from the evil of this night and the evil of what follows it. My Lord, I take refuge in You from laziness and senility. My Lord, I take refuge in You from torment in the Fire and punishment in the grave.', 'Evening Supplications', 3);
INSERT INTO shc_portal.shc_supplication_lk (id, code, lang, label, type, counter)
VALUES (27, 'EVENING_SUPPLICATION_1', 'ms', 'We have reached the evening and at this very time unto Allah belongs all sovereignty, and all praise is for Allah. None has the right to be worshipped except Allah, alone, without partner, to Him belongs all sovereignty and praise and He is over all things omnipotent. My Lord, I ask You for the good of this night and the good of what follows it and I take refuge in You from the evil of this night and the evil of what follows it. My Lord, I take refuge in You from laziness and senility. My Lord, I take refuge in You from torment in the Fire and punishment in the grave.', 'Evening Supplications', 3);
INSERT INTO shc_portal.shc_supplication_lk (id, code, lang, label, type, counter)
VALUES (28, 'EVENING_SUPPLICATION_1', 'fa', 'We have reached the evening and at this very time unto Allah belongs all sovereignty, and all praise is for Allah. None has the right to be worshipped except Allah, alone, without partner, to Him belongs all sovereignty and praise and He is over all things omnipotent. My Lord, I ask You for the good of this night and the good of what follows it and I take refuge in You from the evil of this night and the evil of what follows it. My Lord, I take refuge in You from laziness and senility. My Lord, I take refuge in You from torment in the Fire and punishment in the grave.', 'Evening Supplications', 3);

INSERT INTO shc_portal.shc_supplication_lk (id, code, lang, label, type, counter)
VALUES (29, 'EVENING_SUPPLICATION_2', 'fr', 'Allah! There is no ilah (deity worthy of worship) but He The Living, The Eternal One. Neither slumber, nor sleep overtakes Him. To Him belonges whatever is in the Heavens and on the earth. Who is he that can intercede with Him but by His permission? He knows what is before and what is behind them. They encompass nothing of His knowledge which He will. His Throne extends over the Heavens and the Earth, and the preservation of both does not weary Him. He is the Exalted The Immense One.', 'Evening Supplications', 3);
INSERT INTO shc_portal.shc_supplication_lk (id, code, lang, label, type, counter)
VALUES (30, 'EVENING_SUPPLICATION_2', 'tr', 'Allah! There is no ilah (deity worthy of worship) but He The Living, The Eternal One. Neither slumber, nor sleep overtakes Him. To Him belonges whatever is in the Heavens and on the earth. Who is he that can intercede with Him but by His permission? He knows what is before and what is behind them. They encompass nothing of His knowledge which He will. His Throne extends over the Heavens and the Earth, and the preservation of both does not weary Him. He is the Exalted The Immense One.', 'Evening Supplications', 3);
INSERT INTO shc_portal.shc_supplication_lk (id, code, lang, label, type, counter)
VALUES (31, 'EVENING_SUPPLICATION_2', 'ms', 'Allah! There is no ilah (deity worthy of worship) but He The Living, The Eternal One. Neither slumber, nor sleep overtakes Him. To Him belonges whatever is in the Heavens and on the earth. Who is he that can intercede with Him but by His permission? He knows what is before and what is behind them. They encompass nothing of His knowledge which He will. His Throne extends over the Heavens and the Earth, and the preservation of both does not weary Him. He is the Exalted The Immense One.', 'Evening Supplications', 3);
INSERT INTO shc_portal.shc_supplication_lk (id, code, lang, label, type, counter)
VALUES (32, 'EVENING_SUPPLICATION_2', 'fa', 'Allah! There is no ilah (deity worthy of worship) but He The Living, The Eternal One. Neither slumber, nor sleep overtakes Him. To Him belonges whatever is in the Heavens and on the earth. Who is he that can intercede with Him but by His permission? He knows what is before and what is behind them. They encompass nothing of His knowledge which He will. His Throne extends over the Heavens and the Earth, and the preservation of both does not weary Him. He is the Exalted The Immense One.', 'Evening Supplications', 3);

INSERT INTO shc_portal.shc_supplication_lk (id, code, lang, label, type, counter)
VALUES (33, 'EVENING_SUPPLICATION_3', 'fr', 'He is Allah, who is One. Allah, the Perfect Self-Sufficient Master. He neither begets nor was He begotten, nor is there to Him any equivalent.', 'Evening Supplications', 3);
INSERT INTO shc_portal.shc_supplication_lk (id, code, lang, label, type, counter)
VALUES (34, 'EVENING_SUPPLICATION_3', 'tr', 'He is Allah, who is One. Allah, the Perfect Self-Sufficient Master. He neither begets nor was He begotten, nor is there to Him any equivalent.', 'Evening Supplications', 3);
INSERT INTO shc_portal.shc_supplication_lk (id, code, lang, label, type, counter)
VALUES (35, 'EVENING_SUPPLICATION_3', 'ms', 'He is Allah, who is One. Allah, the Perfect Self-Sufficient Master. He neither begets nor was He begotten, nor is there to Him any equivalent.', 'Evening Supplications', 3);
INSERT INTO shc_portal.shc_supplication_lk (id, code, lang, label, type, counter)
VALUES (36, 'EVENING_SUPPLICATION_3', 'fa', 'He is Allah, who is One. Allah, the Perfect Self-Sufficient Master. He neither begets nor was He begotten, nor is there to Him any equivalent.', 'Evening Supplications', 3);

SET IDENTITY_INSERT shc_portal.shc_supplication_lk OFF;
GO
-- insert shc_survey_question_lk
SET IDENTITY_INSERT shc_portal.shc_survey_question_lk ON;

INSERT INTO shc_portal.shc_survey_question_lk (id, code, survey_type_code, lang, label, question_index) VALUES (61, 'WELCOME_SATISFACTION_Q_DAILY', 'DAILY', 'fr', 'How satisfied are you with the welcoming and greeting?', 1);
INSERT INTO shc_portal.shc_survey_question_lk (id, code, survey_type_code, lang, label, question_index) VALUES (62, 'TRANS_SATISFACTION_Q_DAILY', 'DAILY', 'fr', 'How satisfied are you with the buses?', 2);
INSERT INTO shc_portal.shc_survey_question_lk (id, code, survey_type_code, lang, label, question_index) VALUES (63, 'TRANS_TIME_SATISFACTION_Q_DAILY', 'DAILY', 'fr', 'How satisfied are you with the bus timings?', 3);
INSERT INTO shc_portal.shc_survey_question_lk (id, code, survey_type_code, lang, label, question_index) VALUES (64, 'FOOD_SATISFACTION_Q_DAILY', 'DAILY', 'fr', 'How satisfied are you with the quality of the meals served?', 4);
INSERT INTO shc_portal.shc_survey_question_lk (id, code, survey_type_code, lang, label, question_index) VALUES (65, 'FOOD_TIME_SATISFACTION_Q_DAILY', 'DAILY', 'fr', 'How satisfied are you with serving meals on time?', 5);
INSERT INTO shc_portal.shc_survey_question_lk (id, code, survey_type_code, lang, label, question_index) VALUES (66, 'HOUSING_SATISFACTION_Q_DAILY', 'DAILY', 'fr', 'How satisfied are you with housing?', 6);
INSERT INTO shc_portal.shc_survey_question_lk (id, code, survey_type_code, lang, label, question_index) VALUES (67, 'UTIL_SATISFACTION_Q_DAILY', 'DAILY', 'fr', 'How satisfied are you with public utilities?', 7);
INSERT INTO shc_portal.shc_survey_question_lk (id, code, survey_type_code, lang, label, question_index) VALUES (68, 'GUIDE_SATISFACTION_Q_DAILY', 'DAILY', 'fr', 'How satisfied are you with the counseling and guidance services?', 8);
INSERT INTO shc_portal.shc_survey_question_lk (id, code, survey_type_code, lang, label, question_index) VALUES (69, 'WORKERS_SATISFACTION_Q_DAILY', 'DAILY', 'fr', 'How satisfied are you with the quality of workers services?', 9);
INSERT INTO shc_portal.shc_survey_question_lk (id, code, survey_type_code, lang, label, question_index) VALUES (70, 'CLEANLINESS_SATISFACTION_Q_DAILY', 'DAILY', 'fr', 'How satisfied are you with the level of cleanliness?', 10);


INSERT INTO shc_portal.shc_survey_question_lk (id, code, survey_type_code, lang, label, question_index) VALUES (71, 'WELCOME_SATISFACTION_Q_DAILY', 'END_OF_RITUAL', 'fr', 'How satisfied are you with the welcoming and greeting?', 1);
INSERT INTO shc_portal.shc_survey_question_lk (id, code, survey_type_code, lang, label, question_index) VALUES (72, 'TRANS_SATISFACTION_Q_DAILY', 'END_OF_RITUAL', 'fr', 'How satisfied are you with the buses?', 2);
INSERT INTO shc_portal.shc_survey_question_lk (id, code, survey_type_code, lang, label, question_index) VALUES (73, 'TRANS_TIME_SATISFACTION_Q_DAILY', 'END_OF_RITUAL', 'fr', 'How satisfied are you with the bus timings?', 3);
INSERT INTO shc_portal.shc_survey_question_lk (id, code, survey_type_code, lang, label, question_index) VALUES (74, 'FOOD_SATISFACTION_Q_DAILY', 'END_OF_RITUAL', 'fr', 'How satisfied are you with the quality of the meals served?', 4);
INSERT INTO shc_portal.shc_survey_question_lk (id, code, survey_type_code, lang, label, question_index) VALUES (75, 'FOOD_TIME_SATISFACTION_Q_DAILY', 'END_OF_RITUAL', 'fr', 'How satisfied are you with serving meals on time?', 5);
INSERT INTO shc_portal.shc_survey_question_lk (id, code, survey_type_code, lang, label, question_index) VALUES (76, 'HOUSING_SATISFACTION_Q_DAILY', 'END_OF_RITUAL', 'fr', 'How satisfied are you with housing?', 6);
INSERT INTO shc_portal.shc_survey_question_lk (id, code, survey_type_code, lang, label, question_index) VALUES (77, 'UTIL_SATISFACTION_Q_DAILY', 'END_OF_RITUAL', 'fr', 'How satisfied are you with public utilities?', 7);
INSERT INTO shc_portal.shc_survey_question_lk (id, code, survey_type_code, lang, label, question_index) VALUES (78, 'GUIDE_SATISFACTION_Q_DAILY', 'END_OF_RITUAL', 'fr', 'How satisfied are you with the counseling and guidance services?', 8);
INSERT INTO shc_portal.shc_survey_question_lk (id, code, survey_type_code, lang, label, question_index) VALUES (79, 'WORKERS_SATISFACTION_Q_DAILY', 'END_OF_RITUAL', 'fr', 'How satisfied are you with the quality of workers services?', 9);
INSERT INTO shc_portal.shc_survey_question_lk (id, code, survey_type_code, lang, label, question_index) VALUES (80, 'CLEANLINESS_SATISFACTION_Q_DAILY', 'END_OF_RITUAL', 'fr', 'How satisfied are you with the level of cleanliness?', 10);


INSERT INTO shc_portal.shc_survey_question_lk (id, code, survey_type_code, lang, label, question_index) VALUES (81, 'WELCOME_SATISFACTION_Q_DAILY', 'DAILY', 'tr', 'How satisfied are you with the welcoming and greeting?', 1);
INSERT INTO shc_portal.shc_survey_question_lk (id, code, survey_type_code, lang, label, question_index) VALUES (82, 'TRANS_SATISFACTION_Q_DAILY', 'DAILY', 'tr', 'How satisfied are you with the buses?', 2);
INSERT INTO shc_portal.shc_survey_question_lk (id, code, survey_type_code, lang, label, question_index) VALUES (83, 'TRANS_TIME_SATISFACTION_Q_DAILY', 'DAILY', 'tr', 'How satisfied are you with the bus timings?', 3);
INSERT INTO shc_portal.shc_survey_question_lk (id, code, survey_type_code, lang, label, question_index) VALUES (84, 'FOOD_SATISFACTION_Q_DAILY', 'DAILY', 'tr', 'How satisfied are you with the quality of the meals served?', 4);
INSERT INTO shc_portal.shc_survey_question_lk (id, code, survey_type_code, lang, label, question_index) VALUES (85, 'FOOD_TIME_SATISFACTION_Q_DAILY', 'DAILY', 'tr', 'How satisfied are you with serving meals on time?', 5);
INSERT INTO shc_portal.shc_survey_question_lk (id, code, survey_type_code, lang, label, question_index) VALUES (86, 'HOUSING_SATISFACTION_Q_DAILY', 'DAILY', 'tr', 'How satisfied are you with housing?', 6);
INSERT INTO shc_portal.shc_survey_question_lk (id, code, survey_type_code, lang, label, question_index) VALUES (87, 'UTIL_SATISFACTION_Q_DAILY', 'DAILY', 'tr', 'How satisfied are you with public utilities?', 7);
INSERT INTO shc_portal.shc_survey_question_lk (id, code, survey_type_code, lang, label, question_index) VALUES (88, 'GUIDE_SATISFACTION_Q_DAILY', 'DAILY', 'tr', 'How satisfied are you with the counseling and guidance services?', 8);
INSERT INTO shc_portal.shc_survey_question_lk (id, code, survey_type_code, lang, label, question_index) VALUES (89, 'WORKERS_SATISFACTION_Q_DAILY', 'DAILY', 'tr', 'How satisfied are you with the quality of workers services?', 9);
INSERT INTO shc_portal.shc_survey_question_lk (id, code, survey_type_code, lang, label, question_index) VALUES (90, 'CLEANLINESS_SATISFACTION_Q_DAILY', 'DAILY', 'tr', 'How satisfied are you with the level of cleanliness?', 10);


INSERT INTO shc_portal.shc_survey_question_lk (id, code, survey_type_code, lang, label, question_index) VALUES (91, 'WELCOME_SATISFACTION_Q_DAILY', 'END_OF_RITUAL', 'tr', 'How satisfied are you with the welcoming and greeting?', 1);
INSERT INTO shc_portal.shc_survey_question_lk (id, code, survey_type_code, lang, label, question_index) VALUES (92, 'TRANS_SATISFACTION_Q_DAILY', 'END_OF_RITUAL', 'tr', 'How satisfied are you with the buses?', 2);
INSERT INTO shc_portal.shc_survey_question_lk (id, code, survey_type_code, lang, label, question_index) VALUES (93, 'TRANS_TIME_SATISFACTION_Q_DAILY', 'END_OF_RITUAL', 'tr', 'How satisfied are you with the bus timings?', 3);
INSERT INTO shc_portal.shc_survey_question_lk (id, code, survey_type_code, lang, label, question_index) VALUES (94, 'FOOD_SATISFACTION_Q_DAILY', 'END_OF_RITUAL', 'tr', 'How satisfied are you with the quality of the meals served?', 4);
INSERT INTO shc_portal.shc_survey_question_lk (id, code, survey_type_code, lang, label, question_index) VALUES (95, 'FOOD_TIME_SATISFACTION_Q_DAILY', 'END_OF_RITUAL', 'tr', 'How satisfied are you with serving meals on time?', 5);
INSERT INTO shc_portal.shc_survey_question_lk (id, code, survey_type_code, lang, label, question_index) VALUES (96, 'HOUSING_SATISFACTION_Q_DAILY', 'END_OF_RITUAL', 'tr', 'How satisfied are you with housing?', 6);
INSERT INTO shc_portal.shc_survey_question_lk (id, code, survey_type_code, lang, label, question_index) VALUES (97, 'UTIL_SATISFACTION_Q_DAILY', 'END_OF_RITUAL', 'tr', 'How satisfied are you with public utilities?', 7);
INSERT INTO shc_portal.shc_survey_question_lk (id, code, survey_type_code, lang, label, question_index) VALUES (98, 'GUIDE_SATISFACTION_Q_DAILY', 'END_OF_RITUAL', 'tr', 'How satisfied are you with the counseling and guidance services?', 8);
INSERT INTO shc_portal.shc_survey_question_lk (id, code, survey_type_code, lang, label, question_index) VALUES (99, 'WORKERS_SATISFACTION_Q_DAILY', 'END_OF_RITUAL', 'tr', 'How satisfied are you with the quality of workers services?', 9);
INSERT INTO shc_portal.shc_survey_question_lk (id, code, survey_type_code, lang, label, question_index) VALUES (100, 'CLEANLINESS_SATISFACTION_Q_DAILY', 'END_OF_RITUAL', 'tr', 'How satisfied are you with the level of cleanliness?', 10);



INSERT INTO shc_portal.shc_survey_question_lk (id, code, survey_type_code, lang, label, question_index) VALUES (101, 'WELCOME_SATISFACTION_Q_DAILY', 'DAILY', 'fa', 'How satisfied are you with the welcoming and greeting?', 1);
INSERT INTO shc_portal.shc_survey_question_lk (id, code, survey_type_code, lang, label, question_index) VALUES (102, 'TRANS_SATISFACTION_Q_DAILY', 'DAILY', 'fa', 'How satisfied are you with the buses?', 2);
INSERT INTO shc_portal.shc_survey_question_lk (id, code, survey_type_code, lang, label, question_index) VALUES (103, 'TRANS_TIME_SATISFACTION_Q_DAILY', 'DAILY', 'fa', 'How satisfied are you with the bus timings?', 3);
INSERT INTO shc_portal.shc_survey_question_lk (id, code, survey_type_code, lang, label, question_index) VALUES (104, 'FOOD_SATISFACTION_Q_DAILY', 'DAILY', 'fa', 'How satisfied are you with the quality of the meals served?', 4);
INSERT INTO shc_portal.shc_survey_question_lk (id, code, survey_type_code, lang, label, question_index) VALUES (105, 'FOOD_TIME_SATISFACTION_Q_DAILY', 'DAILY', 'fa', 'How satisfied are you with serving meals on time?', 5);
INSERT INTO shc_portal.shc_survey_question_lk (id, code, survey_type_code, lang, label, question_index) VALUES (106, 'HOUSING_SATISFACTION_Q_DAILY', 'DAILY', 'fa', 'How satisfied are you with housing?', 6);
INSERT INTO shc_portal.shc_survey_question_lk (id, code, survey_type_code, lang, label, question_index) VALUES (107, 'UTIL_SATISFACTION_Q_DAILY', 'DAILY', 'fa', 'How satisfied are you with public utilities?', 7);
INSERT INTO shc_portal.shc_survey_question_lk (id, code, survey_type_code, lang, label, question_index) VALUES (108, 'GUIDE_SATISFACTION_Q_DAILY', 'DAILY', 'fa', 'How satisfied are you with the counseling and guidance services?', 8);
INSERT INTO shc_portal.shc_survey_question_lk (id, code, survey_type_code, lang, label, question_index) VALUES (109, 'WORKERS_SATISFACTION_Q_DAILY', 'DAILY', 'fa', 'How satisfied are you with the quality of workers services?', 9);
INSERT INTO shc_portal.shc_survey_question_lk (id, code, survey_type_code, lang, label, question_index) VALUES (110, 'CLEANLINESS_SATISFACTION_Q_DAILY', 'DAILY', 'fa', 'How satisfied are you with the level of cleanliness?', 10);


INSERT INTO shc_portal.shc_survey_question_lk (id, code, survey_type_code, lang, label, question_index) VALUES (111, 'WELCOME_SATISFACTION_Q_DAILY', 'END_OF_RITUAL', 'fa', 'How satisfied are you with the welcoming and greeting?', 1);
INSERT INTO shc_portal.shc_survey_question_lk (id, code, survey_type_code, lang, label, question_index) VALUES (112, 'TRANS_SATISFACTION_Q_DAILY', 'END_OF_RITUAL', 'fa', 'How satisfied are you with the buses?', 2);
INSERT INTO shc_portal.shc_survey_question_lk (id, code, survey_type_code, lang, label, question_index) VALUES (113, 'TRANS_TIME_SATISFACTION_Q_DAILY', 'END_OF_RITUAL', 'fa', 'How satisfied are you with the bus timings?', 3);
INSERT INTO shc_portal.shc_survey_question_lk (id, code, survey_type_code, lang, label, question_index) VALUES (114, 'FOOD_SATISFACTION_Q_DAILY', 'END_OF_RITUAL', 'fa', 'How satisfied are you with the quality of the meals served?', 4);
INSERT INTO shc_portal.shc_survey_question_lk (id, code, survey_type_code, lang, label, question_index) VALUES (115, 'FOOD_TIME_SATISFACTION_Q_DAILY', 'END_OF_RITUAL', 'fa', 'How satisfied are you with serving meals on time?', 5);
INSERT INTO shc_portal.shc_survey_question_lk (id, code, survey_type_code, lang, label, question_index) VALUES (116, 'HOUSING_SATISFACTION_Q_DAILY', 'END_OF_RITUAL', 'fa', 'How satisfied are you with housing?', 6);
INSERT INTO shc_portal.shc_survey_question_lk (id, code, survey_type_code, lang, label, question_index) VALUES (117, 'UTIL_SATISFACTION_Q_DAILY', 'END_OF_RITUAL', 'fa', 'How satisfied are you with public utilities?', 7);
INSERT INTO shc_portal.shc_survey_question_lk (id, code, survey_type_code, lang, label, question_index) VALUES (118, 'GUIDE_SATISFACTION_Q_DAILY', 'END_OF_RITUAL', 'fa', 'How satisfied are you with the counseling and guidance services?', 8);
INSERT INTO shc_portal.shc_survey_question_lk (id, code, survey_type_code, lang, label, question_index) VALUES (119, 'WORKERS_SATISFACTION_Q_DAILY', 'END_OF_RITUAL', 'fa', 'How satisfied are you with the quality of workers services?', 9);
INSERT INTO shc_portal.shc_survey_question_lk (id, code, survey_type_code, lang, label, question_index) VALUES (200, 'CLEANLINESS_SATISFACTION_Q_DAILY', 'END_OF_RITUAL', 'fa', 'How satisfied are you with the level of cleanliness?', 10);

SET IDENTITY_INSERT shc_portal.shc_survey_question_lk OFF;
GO
-- insert shc_transportation_type_lk
SET IDENTITY_INSERT shc_portal.shc_transportation_type_lk ON;

INSERT INTO shc_portal.shc_transportation_type_lk (id, code, lang, label) VALUES (9, 'TRAIN', 'fr', 'Train');
INSERT INTO shc_portal.shc_transportation_type_lk (id, code, lang, label) VALUES (10, 'TRAIN', 'tr', 'Train');
INSERT INTO shc_portal.shc_transportation_type_lk (id, code, lang, label) VALUES (11, 'TRAIN', 'ms', 'Train');
INSERT INTO shc_portal.shc_transportation_type_lk (id, code, lang, label) VALUES (12, 'TRAIN', 'fa', 'Train');

INSERT INTO shc_portal.shc_transportation_type_lk (id, code, lang, label) VALUES (13, 'BUS', 'fr', 'Bus');
INSERT INTO shc_portal.shc_transportation_type_lk (id, code, lang, label) VALUES (14, 'BUS', 'tr', 'Bus');
INSERT INTO shc_portal.shc_transportation_type_lk (id, code, lang, label) VALUES (15, 'BUS', 'ms', 'Bus');
INSERT INTO shc_portal.shc_transportation_type_lk (id, code, lang, label) VALUES (16, 'BUS', 'fa', 'Bus');

INSERT INTO shc_portal.shc_transportation_type_lk (id, code, lang, label) VALUES (17, 'CAR', 'fr', 'Car');
INSERT INTO shc_portal.shc_transportation_type_lk (id, code, lang, label) VALUES (18, 'CAR', 'tr', 'Car');
INSERT INTO shc_portal.shc_transportation_type_lk (id, code, lang, label) VALUES (19, 'CAR', 'ms', 'Car');
INSERT INTO shc_portal.shc_transportation_type_lk (id, code, lang, label) VALUES (20, 'CAR', 'fa', 'Car');

INSERT INTO shc_portal.shc_transportation_type_lk (id, code, lang, label) VALUES (21, 'AIRPLANE', 'fr', 'AirPlane');
INSERT INTO shc_portal.shc_transportation_type_lk (id, code, lang, label) VALUES (22, 'AIRPLANE', 'tr', 'AirPlane');
INSERT INTO shc_portal.shc_transportation_type_lk (id, code, lang, label) VALUES (23, 'AIRPLANE', 'ms', 'AirPlane');
INSERT INTO shc_portal.shc_transportation_type_lk (id, code, lang, label) VALUES (24, 'AIRPLANE', 'fa', 'AirPlane');

SET IDENTITY_INSERT shc_portal.shc_transportation_type_lk OFF;
GO
-- insert shc_user_notification_status_lk
SET IDENTITY_INSERT shc_portal.shc_user_notification_status_lk ON;

INSERT INTO shc_portal.shc_user_notification_status_lk (id, code, lang, label) VALUES (7, 'NEW', 'fr', 'New');
INSERT INTO shc_portal.shc_user_notification_status_lk (id, code, lang, label) VALUES (8, 'NEW', 'tr', 'New');
INSERT INTO shc_portal.shc_user_notification_status_lk (id, code, lang, label) VALUES (9, 'NEW', 'ms', 'New');
INSERT INTO shc_portal.shc_user_notification_status_lk (id, code, lang, label) VALUES (10, 'NEW', 'fa', 'New');

INSERT INTO shc_portal.shc_user_notification_status_lk (id, code, lang, label) VALUES (11, 'READ', 'fr', 'Read');
INSERT INTO shc_portal.shc_user_notification_status_lk (id, code, lang, label) VALUES (12, 'READ', 'tr', 'Read');
INSERT INTO shc_portal.shc_user_notification_status_lk (id, code, lang, label) VALUES (13, 'READ', 'ms', 'Read');
INSERT INTO shc_portal.shc_user_notification_status_lk (id, code, lang, label) VALUES (14, 'READ', 'fa', 'Read');

INSERT INTO shc_portal.shc_user_notification_status_lk (id, code, lang, label) VALUES (15, 'EXPIRED', 'fr', 'Expired');
INSERT INTO shc_portal.shc_user_notification_status_lk (id, code, lang, label) VALUES (16, 'EXPIRED', 'tr', 'Expired');
INSERT INTO shc_portal.shc_user_notification_status_lk (id, code, lang, label) VALUES (17, 'EXPIRED', 'ms', 'Expired');
INSERT INTO shc_portal.shc_user_notification_status_lk (id, code, lang, label) VALUES (18, 'EXPIRED', 'fa', 'Expired');

SET IDENTITY_INSERT shc_portal.shc_user_notification_status_lk OFF;
GO

-- /////////////////////////////////////////////////// shc_applicant_digital_id_status_lk //////////////////////////////////////////////////////////////////

SET IDENTITY_INSERT shc_portal.shc_applicant_digital_id_status_lk ON;

INSERT INTO shc_portal.shc_applicant_digital_id_status_lk (id, code, lang, label) VALUES (5, 'VALID', 'fr', 'Active');
INSERT INTO shc_portal.shc_applicant_digital_id_status_lk (id, code, lang, label) VALUES (6, 'INVALID', 'fr', 'Invalid');

INSERT INTO shc_portal.shc_applicant_digital_id_status_lk (id, code, lang, label) VALUES (7, 'VALID', 'tr', 'Active');
INSERT INTO shc_portal.shc_applicant_digital_id_status_lk (id, code, lang, label) VALUES (8, 'INVALID', 'tr', 'Invalid');

INSERT INTO shc_portal.shc_applicant_digital_id_status_lk (id, code, lang, label) VALUES (9, 'VALID', 'fa', 'Active');
INSERT INTO shc_portal.shc_applicant_digital_id_status_lk (id, code, lang, label) VALUES (10, 'INVALID', 'fa', 'Invalid');

INSERT INTO shc_portal.shc_applicant_digital_id_status_lk (id, code, lang, label) VALUES (11, 'VALID', 'ms', 'Active');
INSERT INTO shc_portal.shc_applicant_digital_id_status_lk (id, code, lang, label) VALUES (12, 'INVALID', 'ms', 'Invalid');
SET IDENTITY_INSERT shc_portal.shc_applicant_digital_id_status_lk OFF;
GO

-- /////////////////////////////////////////////////// shc_area_layers_lk //////////////////////////////////////////////////////////////////
SET IDENTITY_INSERT shc_portal.shc_area_layers_lk ON;

INSERT INTO shc_portal.shc_area_layers_lk (id, code, lang, label) VALUES (19, 'MAKKAH', 'fr', 'Makkah');
INSERT INTO shc_portal.shc_area_layers_lk (id, code, lang, label) VALUES (20, 'MADINA', 'fr', 'Madina');
INSERT INTO shc_portal.shc_area_layers_lk (id, code, lang, label) VALUES (21, 'MAKKAH_HOLY_MOSQUE', 'fr', 'Holy Mosque in Mecca');
INSERT INTO shc_portal.shc_area_layers_lk (id, code, lang, label) VALUES (22, 'MADINA_HOLY_MOSQUE', 'fr', 'Holy Mosque in Madina');
INSERT INTO shc_portal.shc_area_layers_lk (id, code, lang, label) VALUES (23, 'MENA', 'fr', N'Mena');
INSERT INTO shc_portal.shc_area_layers_lk (id, code, lang, label) VALUES (24, 'GAMARAT', 'fr', N'Gamarat');
INSERT INTO shc_portal.shc_area_layers_lk (id, code, lang, label) VALUES (25, 'MUZDALIFA', 'fr', N'Muzdalifah');
INSERT INTO shc_portal.shc_area_layers_lk (id, code, lang, label) VALUES (26, 'ARAFAT', 'fr', N'Arafat');
INSERT INTO shc_portal.shc_area_layers_lk (id, code, lang, label) VALUES (27, 'JABAL_ALRAHMA', 'fr', N'Alrahma Mountain');

INSERT INTO shc_portal.shc_area_layers_lk (id, code, lang, label) VALUES (28, 'MAKKAH', 'tr', 'Makkah');
INSERT INTO shc_portal.shc_area_layers_lk (id, code, lang, label) VALUES (29, 'MADINA', 'tr', 'Madina');
INSERT INTO shc_portal.shc_area_layers_lk (id, code, lang, label) VALUES (30, 'MAKKAH_HOLY_MOSQUE', 'tr', 'Holy Mosque in Mecca');
INSERT INTO shc_portal.shc_area_layers_lk (id, code, lang, label) VALUES (31, 'MADINA_HOLY_MOSQUE', 'tr', 'Holy Mosque in Madina');
INSERT INTO shc_portal.shc_area_layers_lk (id, code, lang, label) VALUES (32, 'MENA', 'tr', N'Mena');
INSERT INTO shc_portal.shc_area_layers_lk (id, code, lang, label) VALUES (33, 'GAMARAT', 'tr', N'Gamarat');
INSERT INTO shc_portal.shc_area_layers_lk (id, code, lang, label) VALUES (34, 'MUZDALIFA', 'tr', N'Muzdalifah');
INSERT INTO shc_portal.shc_area_layers_lk (id, code, lang, label) VALUES (35, 'ARAFAT', 'tr', N'Arafat');
INSERT INTO shc_portal.shc_area_layers_lk (id, code, lang, label) VALUES (36, 'JABAL_ALRAHMA', 'tr', N'Alrahma Mountain');

INSERT INTO shc_portal.shc_area_layers_lk (id, code, lang, label) VALUES (37, 'MAKKAH', 'fa', 'Makkah');
INSERT INTO shc_portal.shc_area_layers_lk (id, code, lang, label) VALUES (38, 'MADINA', 'fa', 'Madina');
INSERT INTO shc_portal.shc_area_layers_lk (id, code, lang, label) VALUES (39, 'MAKKAH_HOLY_MOSQUE', 'fa', 'Holy Mosque in Mecca');
INSERT INTO shc_portal.shc_area_layers_lk (id, code, lang, label) VALUES (40, 'MADINA_HOLY_MOSQUE', 'fa', 'Holy Mosque in Madina');
INSERT INTO shc_portal.shc_area_layers_lk (id, code, lang, label) VALUES (41, 'MENA', 'fa', N'Mena');
INSERT INTO shc_portal.shc_area_layers_lk (id, code, lang, label) VALUES (42, 'GAMARAT', 'fa', N'Gamarat');
INSERT INTO shc_portal.shc_area_layers_lk (id, code, lang, label) VALUES (43, 'MUZDALIFA', 'fa', N'Muzdalifah');
INSERT INTO shc_portal.shc_area_layers_lk (id, code, lang, label) VALUES (44, 'ARAFAT', 'fa', N'Arafat');
INSERT INTO shc_portal.shc_area_layers_lk (id, code, lang, label) VALUES (45, 'JABAL_ALRAHMA', 'fa', N'Alrahma Mountain');

INSERT INTO shc_portal.shc_area_layers_lk (id, code, lang, label) VALUES (46, 'MAKKAH', 'ms', 'Makkah');
INSERT INTO shc_portal.shc_area_layers_lk (id, code, lang, label) VALUES (47, 'MADINA', 'ms', 'Madina');
INSERT INTO shc_portal.shc_area_layers_lk (id, code, lang, label) VALUES (48, 'MAKKAH_HOLY_MOSQUE', 'ms', 'Holy Mosque in Mecca');
INSERT INTO shc_portal.shc_area_layers_lk (id, code, lang, label) VALUES (49, 'MADINA_HOLY_MOSQUE', 'ms', 'Holy Mosque in Madina');
INSERT INTO shc_portal.shc_area_layers_lk (id, code, lang, label) VALUES (50, 'MENA', 'ms', N'Mena');
INSERT INTO shc_portal.shc_area_layers_lk (id, code, lang, label) VALUES (51, 'GAMARAT', 'ms', N'Gamarat');
INSERT INTO shc_portal.shc_area_layers_lk (id, code, lang, label) VALUES (52, 'MUZDALIFA', 'ms', N'Muzdalifah');
INSERT INTO shc_portal.shc_area_layers_lk (id, code, lang, label) VALUES (53, 'ARAFAT', 'ms', N'Arafat');
INSERT INTO shc_portal.shc_area_layers_lk (id, code, lang, label) VALUES (54, 'JABAL_ALRAHMA', 'ms', N'Alrahma Mountain');
SET IDENTITY_INSERT shc_portal.shc_area_layers_lk OFF;
GO

-- /////////////////////////////////////////////////// shc_card_status_lk //////////////////////////////////////////////////////////////////
SET IDENTITY_INSERT shc_portal.shc_card_status_lk ON;

INSERT INTO shc_portal.shc_card_status_lk (id, code, lang, label) VALUES (21, 'READY_TO_PRINT', 'fr', 'Ready to Print');
INSERT INTO shc_portal.shc_card_status_lk (id, code, lang, label) VALUES (22, 'SENT_FOR_PRINT', 'fr', 'Sent for Print');
INSERT INTO shc_portal.shc_card_status_lk (id, code, lang, label) VALUES (23, 'PRINTED', 'fr', 'Printed');
INSERT INTO shc_portal.shc_card_status_lk (id, code, lang, label) VALUES (24, 'DISTRIBUTED', 'fr', 'Distributed');
INSERT INTO shc_portal.shc_card_status_lk (id, code, lang, label) VALUES (25, 'ACTIVE', 'fr', 'Active');
INSERT INTO shc_portal.shc_card_status_lk (id, code, lang, label) VALUES (26, 'SUSPENDED', 'fr', 'Suspended');
INSERT INTO shc_portal.shc_card_status_lk (id, code, lang, label) VALUES (27, 'CANCELLED', 'fr', 'Cancelled');
INSERT INTO shc_portal.shc_card_status_lk (id, code, lang, label) VALUES (28, 'WAITING_TO_SEND', 'fr', 'Waiting to Send');
INSERT INTO shc_portal.shc_card_status_lk (id, code, lang, label) VALUES (29, 'EXPIRED', 'fr', 'Expired');
INSERT INTO shc_portal.shc_card_status_lk (id, code, lang, label) VALUES (30, 'REISSUED', 'fr', 'Reissued');

INSERT INTO shc_portal.shc_card_status_lk (id, code, lang, label) VALUES (31, 'READY_TO_PRINT', 'tr', 'Ready to Print');
INSERT INTO shc_portal.shc_card_status_lk (id, code, lang, label) VALUES (32, 'SENT_FOR_PRINT', 'tr', 'Sent for Print');
INSERT INTO shc_portal.shc_card_status_lk (id, code, lang, label) VALUES (33, 'PRINTED', 'tr', 'Printed');
INSERT INTO shc_portal.shc_card_status_lk (id, code, lang, label) VALUES (34, 'DISTRIBUTED', 'tr', 'Distributed');
INSERT INTO shc_portal.shc_card_status_lk (id, code, lang, label) VALUES (35, 'ACTIVE', 'tr', 'Active');
INSERT INTO shc_portal.shc_card_status_lk (id, code, lang, label) VALUES (36, 'SUSPENDED', 'tr', 'Suspended');
INSERT INTO shc_portal.shc_card_status_lk (id, code, lang, label) VALUES (37, 'CANCELLED', 'tr', 'Cancelled');
INSERT INTO shc_portal.shc_card_status_lk (id, code, lang, label) VALUES (38, 'WAITING_TO_SEND', 'tr', 'Waiting to Send');
INSERT INTO shc_portal.shc_card_status_lk (id, code, lang, label) VALUES (39, 'EXPIRED', 'tr', 'Expired');
INSERT INTO shc_portal.shc_card_status_lk (id, code, lang, label) VALUES (40, 'REISSUED', 'tr', 'Reissued');

INSERT INTO shc_portal.shc_card_status_lk (id, code, lang, label) VALUES (41, 'READY_TO_PRINT', 'fa', 'Ready to Print');
INSERT INTO shc_portal.shc_card_status_lk (id, code, lang, label) VALUES (42, 'SENT_FOR_PRINT', 'fa', 'Sent for Print');
INSERT INTO shc_portal.shc_card_status_lk (id, code, lang, label) VALUES (43, 'PRINTED', 'fa', 'Printed');
INSERT INTO shc_portal.shc_card_status_lk (id, code, lang, label) VALUES (44, 'DISTRIBUTED', 'fa', 'Distributed');
INSERT INTO shc_portal.shc_card_status_lk (id, code, lang, label) VALUES (45, 'ACTIVE', 'fa', 'Active');
INSERT INTO shc_portal.shc_card_status_lk (id, code, lang, label) VALUES (46, 'SUSPENDED', 'fa', 'Suspended');
INSERT INTO shc_portal.shc_card_status_lk (id, code, lang, label) VALUES (47, 'CANCELLED', 'fa', 'Cancelled');
INSERT INTO shc_portal.shc_card_status_lk (id, code, lang, label) VALUES (48, 'WAITING_TO_SEND', 'fa', 'Waiting to Send');
INSERT INTO shc_portal.shc_card_status_lk (id, code, lang, label) VALUES (49, 'EXPIRED', 'fa', 'Expired');
INSERT INTO shc_portal.shc_card_status_lk (id, code, lang, label) VALUES (50, 'REISSUED', 'fa', 'Reissued');

INSERT INTO shc_portal.shc_card_status_lk (id, code, lang, label) VALUES (51, 'READY_TO_PRINT', 'ms', 'Ready to Print');
INSERT INTO shc_portal.shc_card_status_lk (id, code, lang, label) VALUES (52, 'SENT_FOR_PRINT', 'ms', 'Sent for Print');
INSERT INTO shc_portal.shc_card_status_lk (id, code, lang, label) VALUES (53, 'PRINTED', 'ms', 'Printed');
INSERT INTO shc_portal.shc_card_status_lk (id, code, lang, label) VALUES (54, 'DISTRIBUTED', 'ms', 'Distributed');
INSERT INTO shc_portal.shc_card_status_lk (id, code, lang, label) VALUES (55, 'ACTIVE', 'ms', 'Active');
INSERT INTO shc_portal.shc_card_status_lk (id, code, lang, label) VALUES (56, 'SUSPENDED', 'ms', 'Suspended');
INSERT INTO shc_portal.shc_card_status_lk (id, code, lang, label) VALUES (57, 'CANCELLED', 'ms', 'Cancelled');
INSERT INTO shc_portal.shc_card_status_lk (id, code, lang, label) VALUES (58, 'WAITING_TO_SEND', 'ms', 'Waiting to Send');
INSERT INTO shc_portal.shc_card_status_lk (id, code, lang, label) VALUES (59, 'EXPIRED', 'ms', 'Expired');
INSERT INTO shc_portal.shc_card_status_lk (id, code, lang, label) VALUES (60, 'REISSUED', 'ms', 'Reissued');
SET IDENTITY_INSERT shc_portal.shc_card_status_lk OFF;
GO

-- /////////////////////////////////////////////////// shc_collection_status_lk //////////////////////////////////////////////////////////////////
SET IDENTITY_INSERT shc_portal.shc_collection_status_lk ON;

INSERT INTO shc_portal.shc_collection_status_lk (id, code, lang, label) VALUES (9, 'NEW', 'fr', 'NEW');
INSERT INTO shc_portal.shc_collection_status_lk (id, code, lang, label) VALUES (10, 'GENERATING', 'fr', 'Generating Cards ');
INSERT INTO shc_portal.shc_collection_status_lk (id, code, lang, label) VALUES (11, 'FAILED', 'fr', 'Fail to generate');
INSERT INTO shc_portal.shc_collection_status_lk (id, code, lang, label) VALUES (12, 'READY', 'fr', 'Ready');

INSERT INTO shc_portal.shc_collection_status_lk (id, code, lang, label) VALUES (13, 'NEW', 'tr', 'NEW');
INSERT INTO shc_portal.shc_collection_status_lk (id, code, lang, label) VALUES (14, 'GENERATING', 'tr', 'Generating Cards ');
INSERT INTO shc_portal.shc_collection_status_lk (id, code, lang, label) VALUES (15, 'FAILED', 'tr', 'Fail to generate');
INSERT INTO shc_portal.shc_collection_status_lk (id, code, lang, label) VALUES (16, 'READY', 'tr', 'Ready');

INSERT INTO shc_portal.shc_collection_status_lk (id, code, lang, label) VALUES (17, 'NEW', 'fa', 'NEW');
INSERT INTO shc_portal.shc_collection_status_lk (id, code, lang, label) VALUES (18, 'GENERATING', 'fa', 'Generating Cards ');
INSERT INTO shc_portal.shc_collection_status_lk (id, code, lang, label) VALUES (19, 'FAILED', 'fa', 'Fail to generate');
INSERT INTO shc_portal.shc_collection_status_lk (id, code, lang, label) VALUES (20, 'READY', 'fa', 'Ready');

INSERT INTO shc_portal.shc_collection_status_lk (id, code, lang, label) VALUES (21, 'NEW', 'ms', 'NEW');
INSERT INTO shc_portal.shc_collection_status_lk (id, code, lang, label) VALUES (22, 'GENERATING', 'ms', 'Generating Cards ');
INSERT INTO shc_portal.shc_collection_status_lk (id, code, lang, label) VALUES (23, 'FAILED', 'ms', 'Fail to generate');
INSERT INTO shc_portal.shc_collection_status_lk (id, code, lang, label) VALUES (24, 'READY', 'ms', 'Ready');
SET IDENTITY_INSERT shc_portal.shc_collection_status_lk OFF;
GO

-- /////////////////////////////////////////////////// shc_company_ritual_step_lk //////////////////////////////////////////////////////////////////
SET IDENTITY_INSERT shc_portal.shc_company_ritual_step_lk ON;

INSERT INTO shc_portal.shc_company_ritual_step_lk (id, code, lang, label, step_index, location_lat, location_lng) VALUES (21, 'TAWAF_AL_QUDOM', 'fr', 'Tawaf AlQudom', 1, 21.423617600219412, 39.82591208333528);
INSERT INTO shc_portal.shc_company_ritual_step_lk (id, code, lang, label, step_index, location_lat, location_lng) VALUES (22, 'STAY_IN_MINA', 'fr', 'Stay In Mina', 2, 21.414274989677875, 39.88756806782604);
INSERT INTO shc_portal.shc_company_ritual_step_lk (id, code, lang, label, step_index, location_lat, location_lng) VALUES (23, 'ARAFAT', 'fr', 'Arafat', 3, 21.35497495980284, 39.983982909305766);
INSERT INTO shc_portal.shc_company_ritual_step_lk (id, code, lang, label, step_index, location_lat, location_lng) VALUES (24, 'STAY_IN_MUZDALIFA', 'fr', 'Stay in Muzdalifa', 4, 21.38988147228351, 39.90458872373766);
INSERT INTO shc_portal.shc_company_ritual_step_lk (id, code, lang, label, step_index, location_lat, location_lng) VALUES (25, 'THROUGH_GAMARAT_STONES_10', 'fr', 'Through Gamarat Stones 10', 5, 21.42208621108833, 39.87105874249593);
INSERT INTO shc_portal.shc_company_ritual_step_lk (id, code, lang, label, step_index, location_lat, location_lng) VALUES (26, 'TAWAF_ELFADAH', 'fr', 'Tawaf Elfadah', 6, 21.423617600219412, 39.82591208333528);
INSERT INTO shc_portal.shc_company_ritual_step_lk (id, code, lang, label, step_index, location_lat, location_lng) VALUES (27, 'THROUGH_GAMARAT_STONES_11', 'fr', 'Through Gamarat Stones 11', 7, 21.42208621108833, 39.87105874249593);
INSERT INTO shc_portal.shc_company_ritual_step_lk (id, code, lang, label, step_index, location_lat, location_lng) VALUES (28, 'THROUGH_GAMARAT_STONES_12', 'fr', 'Through Gamarat Stones 12', 8, 21.42208621108833, 39.87105874249593);
INSERT INTO shc_portal.shc_company_ritual_step_lk (id, code, lang, label, step_index, location_lat, location_lng) VALUES (29, 'THROUGH_GAMARAT_STONES_13', 'fr', 'Through Gamarat Stones 13', 9, 21.42208621108833, 39.87105874249593);
INSERT INTO shc_portal.shc_company_ritual_step_lk (id, code, lang, label, step_index, location_lat, location_lng) VALUES (30, 'TAWAF_AL_WADAA', 'fr', 'Tawaf Alwadaa', 10, 21.423617600219412, 39.82591208333528);

INSERT INTO shc_portal.shc_company_ritual_step_lk (id, code, lang, label, step_index, location_lat, location_lng) VALUES (31, 'TAWAF_AL_QUDOM', 'tr', 'Tawaf AlQudom', 1, 21.423617600219412, 39.82591208333528);
INSERT INTO shc_portal.shc_company_ritual_step_lk (id, code, lang, label, step_index, location_lat, location_lng) VALUES (32, 'STAY_IN_MINA', 'tr', 'Stay In Mina', 2, 21.414274989677875, 39.88756806782604);
INSERT INTO shc_portal.shc_company_ritual_step_lk (id, code, lang, label, step_index, location_lat, location_lng) VALUES (33, 'ARAFAT', 'tr', 'Arafat', 3, 21.35497495980284, 39.983982909305766);
INSERT INTO shc_portal.shc_company_ritual_step_lk (id, code, lang, label, step_index, location_lat, location_lng) VALUES (34, 'STAY_IN_MUZDALIFA', 'tr', 'Stay in Muzdalifa', 4, 21.38988147228351, 39.90458872373766);
INSERT INTO shc_portal.shc_company_ritual_step_lk (id, code, lang, label, step_index, location_lat, location_lng) VALUES (35, 'THROUGH_GAMARAT_STONES_10', 'tr', 'Through Gamarat Stones 10', 5, 21.42208621108833, 39.87105874249593);
INSERT INTO shc_portal.shc_company_ritual_step_lk (id, code, lang, label, step_index, location_lat, location_lng) VALUES (36, 'TAWAF_ELFADAH', 'tr', 'Tawaf Elfadah', 6, 21.423617600219412, 39.82591208333528);
INSERT INTO shc_portal.shc_company_ritual_step_lk (id, code, lang, label, step_index, location_lat, location_lng) VALUES (37, 'THROUGH_GAMARAT_STONES_11', 'tr', 'Through Gamarat Stones 11', 7, 21.42208621108833, 39.87105874249593);
INSERT INTO shc_portal.shc_company_ritual_step_lk (id, code, lang, label, step_index, location_lat, location_lng) VALUES (38, 'THROUGH_GAMARAT_STONES_12', 'tr', 'Through Gamarat Stones 12', 8, 21.42208621108833, 39.87105874249593);
INSERT INTO shc_portal.shc_company_ritual_step_lk (id, code, lang, label, step_index, location_lat, location_lng) VALUES (39, 'THROUGH_GAMARAT_STONES_13', 'tr', 'Through Gamarat Stones 13', 9, 21.42208621108833, 39.87105874249593);
INSERT INTO shc_portal.shc_company_ritual_step_lk (id, code, lang, label, step_index, location_lat, location_lng) VALUES (40, 'TAWAF_AL_WADAA', 'tr', 'Tawaf Alwadaa', 10, 21.423617600219412, 39.82591208333528);

INSERT INTO shc_portal.shc_company_ritual_step_lk (id, code, lang, label, step_index, location_lat, location_lng) VALUES (41, 'TAWAF_AL_QUDOM', 'fa', 'Tawaf AlQudom', 1, 21.423617600219412, 39.82591208333528);
INSERT INTO shc_portal.shc_company_ritual_step_lk (id, code, lang, label, step_index, location_lat, location_lng) VALUES (42, 'STAY_IN_MINA', 'fa', 'Stay In Mina', 2, 21.414274989677875, 39.88756806782604);
INSERT INTO shc_portal.shc_company_ritual_step_lk (id, code, lang, label, step_index, location_lat, location_lng) VALUES (43, 'ARAFAT', 'fa', 'Arafat', 3, 21.35497495980284, 39.983982909305766);
INSERT INTO shc_portal.shc_company_ritual_step_lk (id, code, lang, label, step_index, location_lat, location_lng) VALUES (44, 'STAY_IN_MUZDALIFA', 'fa', 'Stay in Muzdalifa', 4, 21.38988147228351, 39.90458872373766);
INSERT INTO shc_portal.shc_company_ritual_step_lk (id, code, lang, label, step_index, location_lat, location_lng) VALUES (45, 'THROUGH_GAMARAT_STONES_10', 'fa', 'Through Gamarat Stones 10', 5, 21.42208621108833, 39.87105874249593);
INSERT INTO shc_portal.shc_company_ritual_step_lk (id, code, lang, label, step_index, location_lat, location_lng) VALUES (46, 'TAWAF_ELFADAH', 'fa', 'Tawaf Elfadah', 6, 21.423617600219412, 39.82591208333528);
INSERT INTO shc_portal.shc_company_ritual_step_lk (id, code, lang, label, step_index, location_lat, location_lng) VALUES (47, 'THROUGH_GAMARAT_STONES_11', 'fa', 'Through Gamarat Stones 11', 7, 21.42208621108833, 39.87105874249593);
INSERT INTO shc_portal.shc_company_ritual_step_lk (id, code, lang, label, step_index, location_lat, location_lng) VALUES (48, 'THROUGH_GAMARAT_STONES_12', 'fa', 'Through Gamarat Stones 12', 8, 21.42208621108833, 39.87105874249593);
INSERT INTO shc_portal.shc_company_ritual_step_lk (id, code, lang, label, step_index, location_lat, location_lng) VALUES (49, 'THROUGH_GAMARAT_STONES_13', 'fa', 'Through Gamarat Stones 13', 9, 21.42208621108833, 39.87105874249593);
INSERT INTO shc_portal.shc_company_ritual_step_lk (id, code, lang, label, step_index, location_lat, location_lng) VALUES (50, 'TAWAF_AL_WADAA', 'fa', 'Tawaf Alwadaa', 10, 21.423617600219412, 39.82591208333528);
GO

INSERT INTO shc_portal.shc_company_ritual_step_lk (id, code, lang, label, step_index, location_lat, location_lng) VALUES (51, 'TAWAF_AL_QUDOM', 'ms', 'Tawaf AlQudom', 1, 21.423617600219412, 39.82591208333528);
INSERT INTO shc_portal.shc_company_ritual_step_lk (id, code, lang, label, step_index, location_lat, location_lng) VALUES (52, 'STAY_IN_MINA', 'ms', 'Stay In Mina', 2, 21.414274989677875, 39.88756806782604);
INSERT INTO shc_portal.shc_company_ritual_step_lk (id, code, lang, label, step_index, location_lat, location_lng) VALUES (53, 'ARAFAT', 'ms', 'Arafat', 3, 21.35497495980284, 39.983982909305766);
INSERT INTO shc_portal.shc_company_ritual_step_lk (id, code, lang, label, step_index, location_lat, location_lng) VALUES (54, 'STAY_IN_MUZDALIFA', 'ms', 'Stay in Muzdalifa', 4, 21.38988147228351, 39.90458872373766);
INSERT INTO shc_portal.shc_company_ritual_step_lk (id, code, lang, label, step_index, location_lat, location_lng) VALUES (55, 'THROUGH_GAMARAT_STONES_10', 'ms', 'Through Gamarat Stones 10', 5, 21.42208621108833, 39.87105874249593);
INSERT INTO shc_portal.shc_company_ritual_step_lk (id, code, lang, label, step_index, location_lat, location_lng) VALUES (56, 'TAWAF_ELFADAH', 'ms', 'Tawaf Elfadah', 6, 21.423617600219412, 39.82591208333528);
INSERT INTO shc_portal.shc_company_ritual_step_lk (id, code, lang, label, step_index, location_lat, location_lng) VALUES (57, 'THROUGH_GAMARAT_STONES_11', 'ms', 'Through Gamarat Stones 11', 7, 21.42208621108833, 39.87105874249593);
INSERT INTO shc_portal.shc_company_ritual_step_lk (id, code, lang, label, step_index, location_lat, location_lng) VALUES (58, 'THROUGH_GAMARAT_STONES_12', 'ms', 'Through Gamarat Stones 12', 8, 21.42208621108833, 39.87105874249593);
INSERT INTO shc_portal.shc_company_ritual_step_lk (id, code, lang, label, step_index, location_lat, location_lng) VALUES (59, 'THROUGH_GAMARAT_STONES_13', 'ms', 'Through Gamarat Stones 13', 9, 21.42208621108833, 39.87105874249593);
INSERT INTO shc_portal.shc_company_ritual_step_lk (id, code, lang, label, step_index, location_lat, location_lng) VALUES (60, 'TAWAF_AL_WADAA', 'ms', 'Tawaf Alwadaa', 10, 21.423617600219412, 39.82591208333528);
SET IDENTITY_INSERT shc_portal.shc_company_ritual_step_lk OFF;
GO
-- /////////////////////////////////////////////////// shc_company_staff_title_lk //////////////////////////////////////////////////////////////////

SET IDENTITY_INSERT shc_portal.shc_company_staff_title_lk ON;

INSERT INTO shc_portal.shc_company_staff_title_lk (id, code, lang, label) VALUES (7, 'GROUP_LEADER', 'fr', 'Group Leader ');
INSERT INTO shc_portal.shc_company_staff_title_lk (id, code, lang, label) VALUES (8, 'INSPECTOR', 'fr', 'Inspector');
INSERT INTO shc_portal.shc_company_staff_title_lk (id, code, lang, label) VALUES (9, 'OTHERS', 'fr', 'Others');

INSERT INTO shc_portal.shc_company_staff_title_lk (id, code, lang, label) VALUES (10, 'GROUP_LEADER', 'tr', 'Group Leader ');
INSERT INTO shc_portal.shc_company_staff_title_lk (id, code, lang, label) VALUES (11, 'INSPECTOR', 'tr', 'Inspector');
INSERT INTO shc_portal.shc_company_staff_title_lk (id, code, lang, label) VALUES (12, 'OTHERS', 'tr', 'Others');

INSERT INTO shc_portal.shc_company_staff_title_lk (id, code, lang, label) VALUES (13, 'GROUP_LEADER', 'fa', 'Group Leader ');
INSERT INTO shc_portal.shc_company_staff_title_lk (id, code, lang, label) VALUES (14, 'INSPECTOR', 'fa', 'Inspector');
INSERT INTO shc_portal.shc_company_staff_title_lk (id, code, lang, label) VALUES (15, 'OTHERS', 'fa', 'Others');

INSERT INTO shc_portal.shc_company_staff_title_lk (id, code, lang, label) VALUES (16, 'GROUP_LEADER', 'ms', 'Group Leader ');
INSERT INTO shc_portal.shc_company_staff_title_lk (id, code, lang, label) VALUES (17, 'INSPECTOR', 'ms', 'Inspector');
INSERT INTO shc_portal.shc_company_staff_title_lk (id, code, lang, label) VALUES (18, 'OTHERS', 'ms', 'Others');

SET IDENTITY_INSERT shc_portal.shc_company_staff_title_lk OFF;
GO

-- /////////////////////////////////////////////////// shc_company_type_lk //////////////////////////////////////////////////////////////////

SET IDENTITY_INSERT shc_portal.shc_company_type_lk ON;

INSERT INTO shc_portal.shc_company_type_lk(id, code, lang, label) VALUES (13, 'ESTABLISHMENT', 'fr', 'Establishment');
INSERT INTO shc_portal.shc_company_type_lk (id, code, lang, label)VALUES (14, 'MISSION', 'fr', 'Mission');
INSERT INTO shc_portal.shc_company_type_lk (id, code, lang, label) VALUES (15, 'INTERNAL_HAJ_COMPANY', 'fr', 'Internal Haj Company  ');
INSERT INTO shc_portal.shc_company_type_lk (id, code, lang, label) VALUES (16, 'EXTERNAL_HAJ_COMPANY', 'fr', 'External  Haj  Company ');
INSERT INTO shc_portal.shc_company_type_lk (id, code, lang, label) VALUES (17, 'SERVICE_GROUP', 'fr', 'Service Group');
INSERT INTO shc_portal.shc_company_type_lk (id, code, lang, label) VALUES (18, 'GOVERNMENT_AGENCY', 'fr', 'Emirate or ministry or other government agency');

INSERT INTO shc_portal.shc_company_type_lk(id, code, lang, label) VALUES (19, 'ESTABLISHMENT', 'tr', 'Establishment');
INSERT INTO shc_portal.shc_company_type_lk (id, code, lang, label)VALUES (20, 'MISSION', 'tr', 'Mission');
INSERT INTO shc_portal.shc_company_type_lk (id, code, lang, label) VALUES (21, 'INTERNAL_HAJ_COMPANY', 'tr', 'Internal Haj Company  ');
INSERT INTO shc_portal.shc_company_type_lk (id, code, lang, label) VALUES (22, 'EXTERNAL_HAJ_COMPANY', 'tr', 'External  Haj  Company ');
INSERT INTO shc_portal.shc_company_type_lk (id, code, lang, label) VALUES (23, 'SERVICE_GROUP', 'tr', 'Service Group');
INSERT INTO shc_portal.shc_company_type_lk (id, code, lang, label) VALUES (24, 'GOVERNMENT_AGENCY', 'tr', 'Emirate or ministry or other government agency');

INSERT INTO shc_portal.shc_company_type_lk(id, code, lang, label) VALUES (25, 'ESTABLISHMENT', 'fa', 'Establishment');
INSERT INTO shc_portal.shc_company_type_lk (id, code, lang, label)VALUES (26, 'MISSION', 'fa', 'Mission');
INSERT INTO shc_portal.shc_company_type_lk (id, code, lang, label) VALUES (27, 'INTERNAL_HAJ_COMPANY', 'fa', 'Internal Haj Company  ');
INSERT INTO shc_portal.shc_company_type_lk (id, code, lang, label) VALUES (28, 'EXTERNAL_HAJ_COMPANY', 'fa', 'External  Haj  Company ');
INSERT INTO shc_portal.shc_company_type_lk (id, code, lang, label) VALUES (29, 'SERVICE_GROUP', 'fa', 'Service Group');
INSERT INTO shc_portal.shc_company_type_lk (id, code, lang, label) VALUES (30, 'GOVERNMENT_AGENCY', 'fa', 'Emirate or ministry or other government agency');

INSERT INTO shc_portal.shc_company_type_lk(id, code, lang, label) VALUES (31, 'ESTABLISHMENT', 'ms', 'Establishment');
INSERT INTO shc_portal.shc_company_type_lk (id, code, lang, label)VALUES (32, 'MISSION', 'ms', 'Mission');
INSERT INTO shc_portal.shc_company_type_lk (id, code, lang, label) VALUES (33, 'INTERNAL_HAJ_COMPANY', 'ms', 'Internal Haj Company  ');
INSERT INTO shc_portal.shc_company_type_lk (id, code, lang, label) VALUES (34, 'EXTERNAL_HAJ_COMPANY', 'ms', 'External  Haj  Company ');
INSERT INTO shc_portal.shc_company_type_lk (id, code, lang, label) VALUES (35, 'SERVICE_GROUP', 'ms', 'Service Group');
INSERT INTO shc_portal.shc_company_type_lk (id, code, lang, label) VALUES (36, 'GOVERNMENT_AGENCY', 'ms', 'Emirate or ministry or other government agency');

SET IDENTITY_INSERT shc_portal.shc_company_type_lk OFF;
GO

-- /////////////////////////////////////////////////// shc_city_lk //////////////////////////////////////////////////////////////////

SET IDENTITY_INSERT shc_portal.shc_city_lk ON;

INSERT INTO shc_portal.shc_city_lk (id, code, lang, label) VALUES (11, 'MAKKAH', 'fr', 'Makkah');
INSERT INTO shc_portal.shc_city_lk (id, code, lang, label) VALUES (12, 'HOLY_SITES', 'fr', 'Holy Sites(Mena- Muzdalifa – Arafat )');
INSERT INTO shc_portal.shc_city_lk (id, code, lang, label) VALUES (13, 'MADINAH', 'fr', 'Madinah');
INSERT INTO shc_portal.shc_city_lk (id, code, lang, label) VALUES (14, 'JEDDAH', 'fr', 'Jeddah');
INSERT INTO shc_portal.shc_city_lk (id, code, lang, label) VALUES (15, 'OTHERS', 'fr', 'Others');

INSERT INTO shc_portal.shc_city_lk (id, code, lang, label) VALUES (16, 'MAKKAH', 'tr', 'Makkah');
INSERT INTO shc_portal.shc_city_lk (id, code, lang, label) VALUES (17, 'HOLY_SITES', 'tr', 'Holy Sites(Mena- Muzdalifa – Arafat )');
INSERT INTO shc_portal.shc_city_lk (id, code, lang, label) VALUES (18, 'MADINAH', 'tr', 'Madinah');
INSERT INTO shc_portal.shc_city_lk (id, code, lang, label) VALUES (19, 'JEDDAH', 'tr', 'Jeddah');
INSERT INTO shc_portal.shc_city_lk (id, code, lang, label) VALUES (20, 'OTHERS', 'tr', 'Others');

INSERT INTO shc_portal.shc_city_lk (id, code, lang, label) VALUES (21, 'MAKKAH', 'fa', 'Makkah');
INSERT INTO shc_portal.shc_city_lk (id, code, lang, label) VALUES (22, 'HOLY_SITES', 'fa', 'Holy Sites(Mena- Muzdalifa – Arafat )');
INSERT INTO shc_portal.shc_city_lk (id, code, lang, label) VALUES (23, 'MADINAH', 'fa', 'Madinah');
INSERT INTO shc_portal.shc_city_lk (id, code, lang, label) VALUES (24, 'JEDDAH', 'fa', 'Jeddah');
INSERT INTO shc_portal.shc_city_lk (id, code, lang, label) VALUES (25, 'OTHERS', 'fa', 'Others');

INSERT INTO shc_portal.shc_city_lk (id, code, lang, label) VALUES (26, 'MAKKAH', 'ms', 'Makkah');
INSERT INTO shc_portal.shc_city_lk (id, code, lang, label) VALUES (27, 'HOLY_SITES', 'ms', 'Holy Sites(Mena- Muzdalifa – Arafat )');
INSERT INTO shc_portal.shc_city_lk (id, code, lang, label) VALUES (28, 'MADINAH', 'ms', 'Madinah');
INSERT INTO shc_portal.shc_city_lk (id, code, lang, label) VALUES (29, 'JEDDAH', 'ms', 'Jeddah');
INSERT INTO shc_portal.shc_city_lk (id, code, lang, label) VALUES (30, 'OTHERS', 'ms', 'Others');
GO

SET IDENTITY_INSERT shc_portal.shc_city_lk OFF;
GO
-- /////////////////////////////////////////////////// shc_complaint_status_lk //////////////////////////////////////////////////////////////////
SET IDENTITY_INSERT shc_portal.shc_complaint_status_lk ON;

INSERT INTO shc_portal.shc_complaint_status_lk (id, code, lang, label) VALUES (7, 'UNDER_PROCESSING', 'fr', 'Under Processing');
INSERT INTO shc_portal.shc_complaint_status_lk (id, code, lang, label) VALUES (8, 'RESOLVED', 'fr', 'Resolved');
INSERT INTO shc_portal.shc_complaint_status_lk (id, code, lang, label) VALUES (9, 'CLOSED', 'fr', 'Closed');

INSERT INTO shc_portal.shc_complaint_status_lk (id, code, lang, label) VALUES (10, 'UNDER_PROCESSING', 'tr', 'Under Processing');
INSERT INTO shc_portal.shc_complaint_status_lk (id, code, lang, label) VALUES (11, 'RESOLVED', 'tr', 'Resolved');
INSERT INTO shc_portal.shc_complaint_status_lk (id, code, lang, label) VALUES (12, 'CLOSED', 'tr', 'Closed');

INSERT INTO shc_portal.shc_complaint_status_lk (id, code, lang, label) VALUES (13, 'UNDER_PROCESSING', 'fa', 'Under Processing');
INSERT INTO shc_portal.shc_complaint_status_lk (id, code, lang, label) VALUES (14, 'RESOLVED', 'fa', 'Resolved');
INSERT INTO shc_portal.shc_complaint_status_lk (id, code, lang, label) VALUES (15, 'CLOSED', 'fa', 'Closed');

INSERT INTO shc_portal.shc_complaint_status_lk (id, code, lang, label) VALUES (16, 'UNDER_PROCESSING', 'ms', 'Under Processing');
INSERT INTO shc_portal.shc_complaint_status_lk (id, code, lang, label) VALUES (17, 'RESOLVED', 'ms', 'Resolved');
INSERT INTO shc_portal.shc_complaint_status_lk (id, code, lang, label) VALUES (18, 'CLOSED', 'ms', 'Closed');

SET IDENTITY_INSERT shc_portal.shc_complaint_status_lk OFF;
GO

-- /////////////////////////////////////////////////// shc_complaint_type_lk //////////////////////////////////////////////////////////////////

SET IDENTITY_INSERT shc_portal.shc_complaint_type_lk ON;

INSERT INTO shc_portal.shc_complaint_type_lk (id, code, lang, label) VALUES (9, 'FOOD', 'fr', 'Food Complaint');
INSERT INTO shc_portal.shc_complaint_type_lk (id, code, lang, label) VALUES (10, 'TRANSPORTATION', 'fr', 'Transportation Complaint');
INSERT INTO shc_portal.shc_complaint_type_lk (id, code, lang, label) VALUES (11, 'HOUSING', 'fr', 'Housing Complaint');
INSERT INTO shc_portal.shc_complaint_type_lk (id, code, lang, label) VALUES (12, 'GENERAL', 'fr', 'General Complaint');

INSERT INTO shc_portal.shc_complaint_type_lk (id, code, lang, label) VALUES (13, 'FOOD', 'tr', 'Food Complaint');
INSERT INTO shc_portal.shc_complaint_type_lk (id, code, lang, label) VALUES (14, 'TRANSPORTATION', 'tr', 'Transportation Complaint');
INSERT INTO shc_portal.shc_complaint_type_lk (id, code, lang, label) VALUES (15, 'HOUSING', 'tr', 'Housing Complaint');
INSERT INTO shc_portal.shc_complaint_type_lk (id, code, lang, label) VALUES (16, 'GENERAL', 'tr', 'General Complaint');

INSERT INTO shc_portal.shc_complaint_type_lk (id, code, lang, label) VALUES (17, 'FOOD', 'fa', 'Food Complaint');
INSERT INTO shc_portal.shc_complaint_type_lk (id, code, lang, label) VALUES (18, 'TRANSPORTATION', 'fa', 'Transportation Complaint');
INSERT INTO shc_portal.shc_complaint_type_lk (id, code, lang, label) VALUES (19, 'HOUSING', 'fa', 'Housing Complaint');
INSERT INTO shc_portal.shc_complaint_type_lk (id, code, lang, label) VALUES (20, 'GENERAL', 'fa', 'General Complaint');

INSERT INTO shc_portal.shc_complaint_type_lk (id, code, lang, label) VALUES (21, 'FOOD', 'ms', 'Food Complaint');
INSERT INTO shc_portal.shc_complaint_type_lk (id, code, lang, label) VALUES (22, 'TRANSPORTATION', 'ms', 'Transportation Complaint');
INSERT INTO shc_portal.shc_complaint_type_lk (id, code, lang, label) VALUES (23, 'HOUSING', 'ms', 'Housing Complaint');
INSERT INTO shc_portal.shc_complaint_type_lk (id, code, lang, label) VALUES (24, 'GENERAL', 'ms', 'General Complaint');

SET IDENTITY_INSERT shc_portal.shc_complaint_type_lk OFF;
GO

-- /////////////////////////////////////////////////// shc_camp_site_lk //////////////////////////////////////////////////////////////////
SET IDENTITY_INSERT shc_portal.shc_camp_site_lk ON;

INSERT INTO [shc_portal].[shc_camp_site_lk](id, code, lang, label) VALUES(4, 'Mena', 'en', 'Mena');
INSERT INTO [shc_portal].[shc_camp_site_lk](id, code, lang, label) VALUES(5, 'Arafat', 'en', 'Arafat');
INSERT INTO [shc_portal].[shc_camp_site_lk](id, code, lang, label) VALUES(6, 'Mouzdalifa', 'en', 'Muzdalifa');

INSERT INTO [shc_portal].[shc_camp_site_lk](id, code, lang, label) VALUES(7, 'Mena', 'fr', 'Mena');
INSERT INTO [shc_portal].[shc_camp_site_lk](id, code, lang, label) VALUES(8, 'Arafat', 'fr', 'Arafat');
INSERT INTO [shc_portal].[shc_camp_site_lk](id, code, lang, label) VALUES(9, 'Mouzdalifa', 'fr', 'Muzdalifa');

INSERT INTO [shc_portal].[shc_camp_site_lk](id, code, lang, label) VALUES(10, 'Mena', 'tr', 'Mena');
INSERT INTO [shc_portal].[shc_camp_site_lk](id, code, lang, label) VALUES(11, 'Arafat', 'tr', 'Arafat');
INSERT INTO [shc_portal].[shc_camp_site_lk](id, code, lang, label) VALUES(12, 'Mouzdalifa', 'tr', 'Muzdalifa');

INSERT INTO [shc_portal].[shc_camp_site_lk](id, code, lang, label) VALUES(13, 'Mena', 'fa', 'Mena');
INSERT INTO [shc_portal].[shc_camp_site_lk](id, code, lang, label) VALUES(14, 'Arafat', 'fa', 'Arafat');
INSERT INTO [shc_portal].[shc_camp_site_lk](id, code, lang, label) VALUES(15, 'Mouzdalifa', 'fa', 'Muzdalifa');

INSERT INTO [shc_portal].[shc_camp_site_lk](id, code, lang, label) VALUES(16, 'Mena', 'ms', 'Mena');
INSERT INTO [shc_portal].[shc_camp_site_lk](id, code, lang, label) VALUES(17, 'Arafat', 'ms', 'Arafat');
INSERT INTO [shc_portal].[shc_camp_site_lk](id, code, lang, label) VALUES(18, 'Mouzdalifa', 'ms', 'Muzdalifa');

SET IDENTITY_INSERT shc_portal.shc_camp_site_lk OFF;
GO


SET IDENTITY_INSERT shc_portal.shc_country_lk ON;

INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(495,N'503',N'fr',N'Salvador');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(496,N'503',N'tr',N'Salvador');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(497,N'503',N'ms',N'Salvador');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(498,N'503',N'fa',N'Salvador');

INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(499,N'504',N'fr',N'Honduras');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(500,N'504',N'tr',N'Honduras');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(501,N'504',N'ms',N'Honduras');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(502,N'504',N'fa',N'Honduras');

INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(503,N'675',N'fr',N'Papua New Guinea');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(504,N'675',N'tr',N'Papua New Guinea');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(505,N'675',N'ms',N'Papua New Guinea');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(506,N'675',N'fa',N'Papua New Guinea');

INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(507,N'1112',N'fr',N'Canada');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(508,N'1112',N'tr',N'Canada');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(509,N'1112',N'ms',N'Canada');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(510,N'1112',N'fa',N'Canada');

INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(511,N'1246',N'fr',N'Barbados');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(512,N'1246',N'tr',N'Barbados');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(513,N'1246',N'ms',N'Barbados');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(514,N'1246',N'fa',N'Barbados');

INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(515,N'5962',N'fr',N'Martinique');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(516,N'5962',N'tr',N'Martinique');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(517,N'5962',N'ms',N'Martinique');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(518,N'5962',N'fa',N'Martinique');

INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(519,N'2681',N'fr',N'Antigua');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(520,N'2681',N'tr',N'Antigua');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(521,N'2681',N'ms',N'Antigua');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(522,N'2681',N'fa',N'Antigua');

INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(523,N'246',N'fr',N'Diego Garcia');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(524,N'246',N'tr',N'Diego Garcia');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(525,N'246',N'ms',N'Diego Garcia');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(526,N'246',N'fa',N'Diego Garcia');

INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(527,N'247',N'fr',N'Ascension Island');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(528,N'247',N'tr',N'Ascension Island');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(529,N'247',N'ms',N'Ascension Island');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(530,N'247',N'fa',N'Ascension Island');

INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(531,N'5961',N'fr',N'French Antilles');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(532,N'5961',N'tr',N'French Antilles');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(533,N'5961',N'ms',N'French Antilles');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(534,N'5961',N'fa',N'French Antilles');

INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(535,N'8691',N'fr',N'Nevis');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(536,N'8691',N'tr',N'Nevis');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(537,N'8691',N'ms',N'Nevis');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(538,N'8691',N'fa',N'Nevis');

INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(539,N'664',N'fr',N'Montserrat');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(540,N'664',N'tr',N'Montserrat');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(541,N'664',N'ms',N'Montserrat');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(542,N'664',N'fa',N'Montserrat');

INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(543,N'242',N'fr',N'Republic of the Congo ( Brazzaville )');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(544,N'242',N'tr',N'Republic of the Congo ( Brazzaville )');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(545,N'242',N'ms',N'Republic of the Congo ( Brazzaville )');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(546,N'242',N'fa',N'Republic of the Congo ( Brazzaville )');

INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(547,N'8082',N'fr',N'Wake Island');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(548,N'8082',N'tr',N'Wake Island');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(549,N'8082',N'ms',N'Wake Island');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(550,N'8082',N'fa',N'Wake Island');

INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(551,N'8081',N'fr',N'Midway Island');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(552,N'8081',N'tr',N'Midway Island');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(553,N'8081',N'ms',N'Midway Island');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(554,N'8081',N'fa',N'Midway Island');

INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(555,N'3811',N'fr',N'Siberia');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(556,N'3811',N'tr',N'Siberia');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(557,N'3811',N'ms',N'Siberia');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(558,N'3811',N'fa',N'Siberia');

INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(559,N'5991',N'fr',N'Curacao');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(560,N'5991',N'tr',N'Curacao');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(561,N'5991',N'ms',N'Curacao');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(562,N'5991',N'fa',N'Curacao');

INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(563,N'2552',N'fr',N'Zanzibar');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(564,N'2552',N'tr',N'Zanzibar');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(565,N'2552',N'ms',N'Zanzibar');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(566,N'2552',N'fa',N'Zanzibar');

INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(567,N'263',N'fr',N'Zimbabwe');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(568,N'263',N'tr',N'Zimbabwe');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(569,N'263',N'ms',N'Zimbabwe');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(570,N'263',N'fa',N'Zimbabwe');

INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(571,N'254',N'fr',N'Kenya');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(572,N'254',N'tr',N'Kenya');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(573,N'254',N'ms',N'Kenya');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(574,N'254',N'fa',N'Kenya');

INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(575,N'236',N'fr',N'Central African Republic');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(576,N'236',N'tr',N'Central African Republic');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(577,N'236',N'ms',N'Central African Republic');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(578,N'236',N'fa',N'Central African Republic');

INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(579,N'94',N'fr',N'Sri Lanka');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(580,N'94',N'tr',N'Sri Lanka');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(581,N'94',N'ms',N'Sri Lanka');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(582,N'94',N'fa',N'Sri Lanka');

INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(583,N'86',N'fr',N'China (PRC)');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(584,N'86',N'tr',N'China (PRC)');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(585,N'86',N'ms',N'China (PRC)');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(586,N'86',N'fa',N'China (PRC)');

INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(587,N'234',N'fr',N'Nigeria');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(588,N'234',N'tr',N'Nigeria');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(589,N'234',N'ms',N'Nigeria');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(590,N'234',N'fa',N'Nigeria');

INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(591,N'998',N'fr',N'Uzbekistan');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(592,N'998',N'tr',N'Uzbekistan');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(593,N'998',N'ms',N'Uzbekistan');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(594,N'998',N'fa',N'Uzbekistan');

INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(595,N'376',N'fr',N'Andorra');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(596,N'376',N'tr',N'Andorra');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(597,N'376',N'ms',N'Andorra');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(598,N'376',N'fa',N'Andorra');

INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(599,N'57',N'fr',N'Colombia');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(600,N'57',N'tr',N'Colombia');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(601,N'57',N'ms',N'Colombia');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(602,N'57',N'fa',N'Colombia');

INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(603,N'372',N'fr',N'Estonia');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(604,N'372',N'tr',N'Estonia');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(605,N'372',N'ms',N'Estonia');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(606,N'372',N'fa',N'Estonia');

INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(607,N'27',N'fr',N'South Africa');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(608,N'27',N'tr',N'South Africa');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(609,N'27',N'ms',N'South Africa');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(610,N'27',N'fa',N'South Africa');

INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(611,N'20',N'fr',N'Egypt');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(612,N'20',N'tr',N'Egypt');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(613,N'20',N'ms',N'Egypt');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(614,N'20',N'fa',N'Egypt');

INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(615,N'1',N'fr',N'United States of America');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(616,N'1',N'tr',N'United States of America');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(617,N'1',N'ms',N'United States of America');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(618,N'1',N'fa',N'United States of America');

INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(619,N'502',N'fr',N'Guatemala');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(620,N'502',N'tr',N'Guatemala');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(621,N'502',N'ms',N'Guatemala');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(622,N'502',N'fa',N'Guatemala');

INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(623,N'501',N'fr',N'Belize');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(624,N'501',N'tr',N'Belize');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(625,N'501',N'ms',N'Belize');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(626,N'501',N'fa',N'Belize');

INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(627,N'500',N'fr',N'Falkland Islands Dem. Rep. of (former Zaire)');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(628,N'500',N'tr',N'Falkland Islands Dem. Rep. of (former Zaire)');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(629,N'500',N'ms',N'Falkland Islands Dem. Rep. of (former Zaire)');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(630,N'500',N'fa',N'Falkland Islands Dem. Rep. of (former Zaire)');

INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(631,N'473',N'fr',N'Grenada');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(632,N'473',N'tr',N'Grenada');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(633,N'473',N'ms',N'Grenada');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(634,N'473',N'fa',N'Grenada');

INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(635,N'441',N'fr',N'Bermuda');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(636,N'441',N'tr',N'Bermuda');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(637,N'441',N'ms',N'Bermuda');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(638,N'441',N'fa',N'Bermuda');

INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(639,N'423',N'fr',N'Liechtenstein');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(640,N'423',N'tr',N'Liechtenstein');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(641,N'423',N'ms',N'Liechtenstein');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(642,N'423',N'fa',N'Liechtenstein');

INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(643,N'51',N'fr',N'Peru');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(644,N'51',N'tr',N'Peru');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(645,N'51',N'ms',N'Peru');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(646,N'51',N'fa',N'Peru');

INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(647,N'31',N'fr',N'Netherlands');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(648,N'31',N'tr',N'Netherlands');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(649,N'31',N'ms',N'Netherlands');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(650,N'31',N'fa',N'Netherlands');

INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(651,N'48',N'fr',N'Poland');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(652,N'48',N'tr',N'Poland');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(653,N'48',N'ms',N'Poland');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(654,N'48',N'fa',N'Poland');

INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(655,N'47',N'fr',N'Norway');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(656,N'47',N'tr',N'Norway');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(657,N'47',N'ms',N'Norway');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(658,N'47',N'fa',N'Norway');

INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(659,N'46',N'fr',N'Sweden');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(660,N'46',N'tr',N'Sweden');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(661,N'46',N'ms',N'Sweden');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(662,N'46',N'fa',N'Sweden');

INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(663,N'45',N'fr',N'Denmark');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(664,N'45',N'tr',N'Denmark');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(665,N'45',N'ms',N'Denmark');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(666,N'45',N'fa',N'Denmark');

INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(667,N'290',N'fr',N'St. Helena');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(668,N'290',N'tr',N'St. Helena');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(669,N'290',N'ms',N'St. Helena');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(670,N'290',N'fa',N'St. Helena');

INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(671,N'284',N'fr',N'British Virgin Islands');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(672,N'284',N'tr',N'British Virgin Islands');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(673,N'284',N'ms',N'British Virgin Islands');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(674,N'284',N'fa',N'British Virgin Islands');

INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(675,N'267',N'fr',N'Botswana');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(676,N'267',N'tr',N'Botswana');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(677,N'267',N'ms',N'Botswana');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(678,N'267',N'fa',N'Botswana');

INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(679,N'252',N'fr',N'Somalia');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(680,N'252',N'tr',N'Somalia');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(681,N'252',N'ms',N'Somalia');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(682,N'252',N'fa',N'Somalia');

INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(683,N'421',N'fr',N'Slovakia');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(684,N'421',N'tr',N'Slovakia');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(685,N'421',N'ms',N'Slovakia');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(686,N'421',N'fa',N'Slovakia');

INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(687,N'389',N'fr',N'Macedonia');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(688,N'389',N'tr',N'Macedonia');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(689,N'389',N'ms',N'Macedonia');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(690,N'389',N'fa',N'Macedonia');

INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(691,N'56',N'fr',N'Chile');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(692,N'56',N'tr',N'Chile');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(693,N'56',N'ms',N'Chile');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(694,N'56',N'fa',N'Chile');

INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(695,N'98',N'fr',N'Iran');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(696,N'98',N'tr',N'Iran');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(697,N'98',N'ms',N'Iran');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(698,N'98',N'fa',N'Iran');

INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(699,N'229',N'fr',N'Benin');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(700,N'229',N'tr',N'Benin');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(701,N'229',N'ms',N'Benin');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(702,N'229',N'fa',N'Benin');

INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(703,N'32',N'fr',N'Belgium');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(704,N'32',N'tr',N'Belgium');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(705,N'32',N'ms',N'Belgium');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(706,N'32',N'fa',N'Belgium');

INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(707,N'227',N'fr',N'Niger');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(708,N'227',N'tr',N'Niger');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(709,N'227',N'ms',N'Niger');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(710,N'227',N'fa',N'Niger');

INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(711,N'226',N'fr',N'Burkina Faso');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(712,N'226',N'tr',N'Burkina Faso');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(713,N'226',N'ms',N'Burkina Faso');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(714,N'226',N'fa',N'Burkina Faso');

INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(715,N'225',N'fr',N'Ivory Coast');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(716,N'225',N'tr',N'Ivory Coast');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(717,N'225',N'ms',N'Ivory Coast');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(718,N'225',N'fa',N'Ivory Coast');

INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(719,N'224',N'fr',N'Guinea Conakry (PRP)');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(720,N'224',N'tr',N'Guinea Conakry (PRP)');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(721,N'224',N'ms',N'Guinea Conakry (PRP)');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(722,N'224',N'fa',N'Guinea Conakry (PRP)');

INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(723,N'223',N'fr',N'Mali');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(724,N'223',N'tr',N'Mali');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(725,N'223',N'ms',N'Mali');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(726,N'223',N'fa',N'Mali');

INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(727,N'222',N'fr',N'Mauritania');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(728,N'222',N'tr',N'Mauritania');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(729,N'222',N'ms',N'Mauritania');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(730,N'222',N'fa',N'Mauritania');

INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(731,N'221',N'fr',N'Senegal');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(732,N'221',N'tr',N'Senegal');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(733,N'221',N'ms',N'Senegal');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(734,N'221',N'fa',N'Senegal');

INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(735,N'220',N'fr',N'Gambia');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(736,N'220',N'tr',N'Gambia');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(737,N'220',N'ms',N'Gambia');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(738,N'220',N'fa',N'Gambia');

INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(739,N'218',N'fr',N'Libya');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(740,N'218',N'tr',N'Libya');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(741,N'218',N'ms',N'Libya');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(742,N'218',N'fa',N'Libya');

INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(743,N'216',N'fr',N'Tunisia');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(744,N'216',N'tr',N'Tunisia');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(745,N'216',N'ms',N'Tunisia');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(746,N'216',N'fa',N'Tunisia');

INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(747,N'213',N'fr',N'Algeria');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(748,N'213',N'tr',N'Algeria');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(749,N'213',N'ms',N'Algeria');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(750,N'213',N'fa',N'Algeria');

INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(751,N'248',N'fr',N'Seychelles');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(752,N'248',N'tr',N'Seychelles');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(753,N'248',N'ms',N'Seychelles');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(754,N'248',N'fa',N'Seychelles');

INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(755,N'55',N'fr',N'Brazil');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(756,N'55',N'tr',N'Brazil');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(757,N'55',N'ms',N'Brazil');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(758,N'55',N'fa',N'Brazil');

INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(759,N'49',N'fr',N'Germany');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(760,N'49',N'tr',N'Germany');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(761,N'49',N'ms',N'Germany');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(762,N'49',N'fa',N'Germany');

INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(763,N'876',N'fr',N'Jamaica');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(764,N'876',N'tr',N'Jamaica');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(765,N'876',N'ms',N'Jamaica');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(766,N'876',N'fa',N'Jamaica');

INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(767,N'266',N'fr',N'Lesotho');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(768,N'266',N'tr',N'Lesotho');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(769,N'266',N'ms',N'Lesotho');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(770,N'266',N'fa',N'Lesotho');

INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(771,N'358',N'fr',N'Finland');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(772,N'358',N'tr',N'Finland');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(773,N'358',N'ms',N'Finland');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(774,N'358',N'fa',N'Finland');

INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(775,N'357',N'fr',N'Cyprus');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(776,N'357',N'tr',N'Cyprus');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(777,N'357',N'ms',N'Cyprus');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(778,N'357',N'fa',N'Cyprus');

INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(779,N'356',N'fr',N'Malta');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(780,N'356',N'tr',N'Malta');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(781,N'356',N'ms',N'Malta');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(782,N'356',N'fa',N'Malta');

INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(783,N'355',N'fr',N'Albania');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(784,N'355',N'tr',N'Albania');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(785,N'355',N'ms',N'Albania');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(786,N'355',N'fa',N'Albania');

INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(787,N'354',N'fr',N'Iceland');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(788,N'354',N'tr',N'Iceland');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(789,N'354',N'ms',N'Iceland');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(790,N'354',N'fa',N'Iceland');

INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(791,N'353',N'fr',N'Ireland');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(792,N'353',N'tr',N'Ireland');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(793,N'353',N'ms',N'Ireland');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(794,N'353',N'fa',N'Ireland');

INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(795,N'228',N'fr',N'Togo');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(796,N'228',N'tr',N'Togo');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(797,N'228',N'ms',N'Togo');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(798,N'228',N'fa',N'Togo');

INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(799,N'352',N'fr',N'Luxembourg');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(800,N'352',N'tr',N'Luxembourg');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(801,N'352',N'ms',N'Luxembourg');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(802,N'352',N'fa',N'Luxembourg');

INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(803,N'351',N'fr',N'Portugal');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(804,N'351',N'tr',N'Portugal');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(805,N'351',N'ms',N'Portugal');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(806,N'351',N'fa',N'Portugal');

INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(807,N'54',N'fr',N'Argentina');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(808,N'54',N'tr',N'Argentina');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(809,N'54',N'ms',N'Argentina');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(810,N'54',N'fa',N'Argentina');

INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(811,N'53',N'fr',N'Cuba');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(812,N'53',N'tr',N'Cuba');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(813,N'53',N'ms',N'Cuba');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(814,N'53',N'fa',N'Cuba');

INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(815,N'30',N'fr',N'Greece');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(816,N'30',N'tr',N'Greece');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(817,N'30',N'ms',N'Greece');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(818,N'30',N'fa',N'Greece');

INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(819,N'61',N'fr',N'Australia');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(820,N'61',N'tr',N'Australia');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(821,N'61',N'ms',N'Australia');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(822,N'61',N'fa',N'Australia');

INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(823,N'93',N'fr',N'Afghanistan');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(824,N'93',N'tr',N'Afghanistan');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(825,N'93',N'ms',N'Afghanistan');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(826,N'93',N'fa',N'Afghanistan');

INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(827,N'92',N'fr',N'Pakistan');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(828,N'92',N'tr',N'Pakistan');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(829,N'92',N'ms',N'Pakistan');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(830,N'92',N'fa',N'Pakistan');

INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(831,N'91',N'fr',N'India');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(832,N'91',N'tr',N'India');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(833,N'91',N'ms',N'India');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(834,N'91',N'fa',N'India');

INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(835,N'90',N'fr',N'Turkey');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(836,N'90',N'tr',N'Turkey');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(837,N'90',N'ms',N'Turkey');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(838,N'90',N'fa',N'Turkey');

INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(839,N'84',N'fr',N'Vietnam');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(840,N'84',N'tr',N'Vietnam');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(841,N'84',N'ms',N'Vietnam');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(842,N'84',N'fa',N'Vietnam');

INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(843,N'82',N'fr',N'South Korea');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(844,N'82',N'tr',N'South Korea');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(845,N'82',N'ms',N'South Korea');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(846,N'82',N'fa',N'South Korea');

INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(847,N'81',N'fr',N'Japan');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(848,N'81',N'tr',N'Japan');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(849,N'81',N'ms',N'Japan');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(850,N'81',N'fa',N'Japan');

INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(851,N'66',N'fr',N'Thailand');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(852,N'66',N'tr',N'Thailand');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(853,N'66',N'ms',N'Thailand');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(854,N'66',N'fa',N'Thailand');

INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(855,N'65',N'fr',N'Singapore');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(856,N'65',N'tr',N'Singapore');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(857,N'65',N'ms',N'Singapore');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(858,N'65',N'fa',N'Singapore');

INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(859,N'64',N'fr',N'New Zealand');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(860,N'64',N'tr',N'New Zealand');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(861,N'64',N'ms',N'New Zealand');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(862,N'64',N'fa',N'New Zealand');

INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(863,N'62',N'fr',N'Indonesia');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(864,N'62',N'tr',N'Indonesia');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(865,N'62',N'ms',N'Indonesia');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(866,N'62',N'fa',N'Indonesia');

INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(867,N'235',N'fr',N'Chad');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(868,N'235',N'tr',N'Chad');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(869,N'235',N'ms',N'Chad');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(870,N'235',N'fa',N'Chad');

INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(871,N'60',N'fr',N'Malaysia');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(872,N'60',N'tr',N'Malaysia');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(873,N'60',N'ms',N'Malaysia');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(874,N'60',N'fa',N'Malaysia');

INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(875,N'58',N'fr',N'Venezuela');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(876,N'58',N'tr',N'Venezuela');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(877,N'58',N'ms',N'Venezuela');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(878,N'58',N'fa',N'Venezuela');

INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(879,N'230',N'fr',N'Mauritius');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(880,N'230',N'tr',N'Mauritius');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(881,N'230',N'ms',N'Mauritius');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(882,N'230',N'fa',N'Mauritius');

INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(883,N'245',N'fr',N'Guinea-Bissau');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(884,N'245',N'tr',N'Guinea-Bissau');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(885,N'245',N'ms',N'Guinea-Bissau');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(886,N'245',N'fa',N'Guinea-Bissau');

INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(887,N'244',N'fr',N'Angola');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(888,N'244',N'tr',N'Angola');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(889,N'244',N'ms',N'Angola');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(890,N'244',N'fa',N'Angola');

INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(891,N'243',N'fr',N'Democratic Republic of the Congo');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(892,N'243',N'tr',N'Democratic Republic of the Congo');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(893,N'243',N'ms',N'Democratic Republic of the Congo');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(894,N'243',N'fa',N'Democratic Republic of the Congo');

INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(895,N'241',N'fr',N'Gabon');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(896,N'241',N'tr',N'Gabon');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(897,N'241',N'ms',N'Gabon');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(898,N'241',N'fa',N'Gabon');

INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(899,N'240',N'fr',N'Equatorial Guinea');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(900,N'240',N'tr',N'Equatorial Guinea');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(901,N'240',N'ms',N'Equatorial Guinea');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(902,N'240',N'fa',N'Equatorial Guinea');

INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(903,N'239',N'fr',N'S.o Tom? and Principe');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(904,N'239',N'tr',N'S.o Tom? and Principe');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(905,N'239',N'ms',N'S.o Tom? and Principe');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(906,N'239',N'fa',N'S.o Tom? and Principe');

INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(907,N'238',N'fr',N'Cape Verde Islands');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(908,N'238',N'tr',N'Cape Verde Islands');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(909,N'238',N'ms',N'Cape Verde Islands');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(910,N'238',N'fa',N'Cape Verde Islands');

INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(911,N'237',N'fr',N'Cameroon');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(912,N'237',N'tr',N'Cameroon');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(913,N'237',N'ms',N'Cameroon');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(914,N'237',N'fa',N'Cameroon');

INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(915,N'886',N'fr',N'Taiwan');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(916,N'886',N'tr',N'Taiwan');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(917,N'886',N'ms',N'Taiwan');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(918,N'886',N'fa',N'Taiwan');

INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(919,N'63',N'fr',N'Philippines');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(920,N'63',N'tr',N'Philippines');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(921,N'63',N'ms',N'Philippines');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(922,N'63',N'fa',N'Philippines');

INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(923,N'6702',N'fr',N'Northern Marianas Islands');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(924,N'6702',N'tr',N'Northern Marianas Islands');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(925,N'6702',N'ms',N'Northern Marianas Islands');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(926,N'6702',N'fa',N'Northern Marianas Islands');

INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(927,N'961',N'fr',N'Lebanon');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(928,N'961',N'tr',N'Lebanon');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(929,N'961',N'ms',N'Lebanon');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(930,N'961',N'fa',N'Lebanon');

INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(931,N'960',N'fr',N'Maldives');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(932,N'960',N'tr',N'Maldives');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(933,N'960',N'ms',N'Maldives');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(934,N'960',N'fa',N'Maldives');

INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(935,N'880',N'fr',N'Bangladesh');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(936,N'880',N'tr',N'Bangladesh');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(937,N'880',N'ms',N'Bangladesh');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(938,N'880',N'fa',N'Bangladesh');

INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(939,N'868',N'fr',N'Trinidad , Tobago');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(940,N'868',N'tr',N'Trinidad , Tobago');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(941,N'868',N'ms',N'Trinidad , Tobago');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(942,N'868',N'fa',N'Trinidad , Tobago');

INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(943,N'856',N'fr',N'Laos');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(944,N'856',N'tr',N'Laos');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(945,N'856',N'ms',N'Laos');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(946,N'856',N'fa',N'Laos');

INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(947,N'855',N'fr',N'Cambodia');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(948,N'855',N'tr',N'Cambodia');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(949,N'855',N'ms',N'Cambodia');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(950,N'855',N'fa',N'Cambodia');

INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(951,N'853',N'fr',N'Macau');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(952,N'853',N'tr',N'Macau');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(953,N'853',N'ms',N'Macau');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(954,N'853',N'fa',N'Macau');

INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(955,N'809',N'fr',N'Dominican Republic');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(956,N'809',N'tr',N'Dominican Republic');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(957,N'809',N'ms',N'Dominican Republic');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(958,N'809',N'fa',N'Dominican Republic');

INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(959,N'784',N'fr',N'St. Vincent , Grenadines');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(960,N'784',N'tr',N'St. Vincent , Grenadines');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(961,N'784',N'ms',N'St. Vincent , Grenadines');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(962,N'784',N'fa',N'St. Vincent , Grenadines');

INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(963,N'2683',N'fr',N'Swaziland');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(964,N'2683',N'tr',N'Swaziland');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(965,N'2683',N'ms',N'Swaziland');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(966,N'2683',N'fa',N'Swaziland');

INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(967,N'8692',N'fr',N'St. Kitts/Nevis');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(968,N'8692',N'tr',N'St. Kitts/Nevis');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(969,N'8692',N'ms',N'St. Kitts/Nevis');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(970,N'8692',N'fa',N'St. Kitts/Nevis');

INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(971,N'594',N'fr',N'French Guiana');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(972,N'594',N'tr',N'French Guiana');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(973,N'594',N'ms',N'French Guiana');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(974,N'594',N'fa',N'French Guiana');

INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(975,N'7001',N'fr',N'Kazakhstan');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(976,N'7001',N'tr',N'Kazakhstan');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(977,N'7001',N'ms',N'Kazakhstan');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(978,N'7001',N'fa',N'Kazakhstan');

INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(979,N'965',N'fr',N'Kuwait');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(980,N'965',N'tr',N'Kuwait');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(981,N'965',N'ms',N'Kuwait');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(982,N'965',N'fa',N'Kuwait');

INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(983,N'5992',N'fr',N'Netherlands Antilles');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(984,N'5992',N'tr',N'Netherlands Antilles');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(985,N'5992',N'ms',N'Netherlands Antilles');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(986,N'5992',N'fa',N'Netherlands Antilles');

INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(987,N'3812',N'fr',N'Yugoslavia');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(988,N'3812',N'tr',N'Yugoslavia');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(989,N'3812',N'ms',N'Yugoslavia');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(990,N'3812',N'fa',N'Yugoslavia');

INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(991,N'2692',N'fr',N'Mayotte Island');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(992,N'2692',N'tr',N'Mayotte Island');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(993,N'2692',N'ms',N'Mayotte Island');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(994,N'2692',N'fa',N'Mayotte Island');

INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(995,N'2691',N'fr',N'Comoros Islands');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(996,N'2691',N'tr',N'Comoros Islands');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(997,N'2691',N'ms',N'Comoros Islands');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(998,N'2691',N'fa',N'Comoros Islands');

INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(999,N'674',N'fr',N'Nauru');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1000,N'674',N'tr',N'Nauru');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1001,N'674',N'ms',N'Nauru');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1002,N'674',N'fa',N'Nauru');

INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1003,N'673',N'fr',N'Brunei');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1004,N'673',N'tr',N'Brunei');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1005,N'673',N'ms',N'Brunei');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1006,N'673',N'fa',N'Brunei');

INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1007,N'671',N'fr',N'Guam');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1008,N'671',N'tr',N'Guam');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1009,N'671',N'ms',N'Guam');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1010,N'671',N'fa',N'Guam');

INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1011,N'359',N'fr',N'Bulgaria');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1012,N'359',N'tr',N'Bulgaria');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1013,N'359',N'ms',N'Bulgaria');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1014,N'359',N'fa',N'Bulgaria');

INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1015,N'598',N'fr',N'Uruguay');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1016,N'598',N'tr',N'Uruguay');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1017,N'598',N'ms',N'Uruguay');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1018,N'598',N'fa',N'Uruguay');

INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1019,N'962',N'fr',N'Jordan');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1020,N'962',N'tr',N'Jordan');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1021,N'962',N'ms',N'Jordan');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1022,N'962',N'fa',N'Jordan');

INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1023,N'595',N'fr',N'Paraguay');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1024,N'595',N'tr',N'Paraguay');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1025,N'595',N'ms',N'Paraguay');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1026,N'595',N'fa',N'Paraguay');

INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1027,N'7002',N'fr',N'Russia');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1028,N'7002',N'tr',N'Russia');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1029,N'7002',N'ms',N'Russia');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1030,N'7002',N'fa',N'Russia');

INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1031,N'1242',N'fr',N'Bahamas');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1032,N'1242',N'tr',N'Bahamas');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1033,N'1242',N'ms',N'Bahamas');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1034,N'1242',N'fa',N'Bahamas');

INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1035,N'370',N'fr',N'Lithuania');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1036,N'370',N'tr',N'Lithuania');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1037,N'370',N'ms',N'Lithuania');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1038,N'370',N'fa',N'Lithuania');

INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1039,N'850',N'fr',N'North Korea');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1040,N'850',N'tr',N'North Korea');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1041,N'850',N'ms',N'North Korea');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1042,N'850',N'fa',N'North Korea');

INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1043,N'758',N'fr',N'St. Lucia');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1044,N'758',N'tr',N'St. Lucia');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1045,N'758',N'ms',N'St. Lucia');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1046,N'758',N'fa',N'St. Lucia');

INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1047,N'691',N'fr',N'Micronesia (Federal States of)');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1048,N'691',N'tr',N'Micronesia (Federal States of)');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1049,N'691',N'ms',N'Micronesia (Federal States of)');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1050,N'691',N'fa',N'Micronesia (Federal States of)');

INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1051,N'690',N'fr',N'Tokelau');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1052,N'690',N'tr',N'Tokelau');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1053,N'690',N'ms',N'Tokelau');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1054,N'690',N'fa',N'Tokelau');

INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1055,N'689',N'fr',N'French Polynesia');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1056,N'689',N'tr',N'French Polynesia');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1057,N'689',N'ms',N'French Polynesia');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1058,N'689',N'fa',N'French Polynesia');

INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1059,N'688',N'fr',N'Tuvalu');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1060,N'688',N'tr',N'Tuvalu');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1061,N'688',N'ms',N'Tuvalu');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1062,N'688',N'fa',N'Tuvalu');

INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1063,N'687',N'fr',N'New Caledonia');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1064,N'687',N'tr',N'New Caledonia');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1065,N'687',N'ms',N'New Caledonia');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1066,N'687',N'fa',N'New Caledonia');

INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1067,N'767',N'fr',N'Dominican');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1068,N'767',N'tr',N'Dominican');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1069,N'767',N'ms',N'Dominican');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1070,N'767',N'fa',N'Dominican');

INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1071,N'2682',N'fr',N'Barbuda');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1072,N'2682',N'tr',N'Barbuda');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1073,N'2682',N'ms',N'Barbuda');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1074,N'2682',N'fa',N'Barbuda');

INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1075,N'2642',N'fr',N'Namibia');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1076,N'2642',N'tr',N'Namibia');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1077,N'2642',N'ms',N'Namibia');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1078,N'2642',N'fa',N'Namibia');

INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1079,N'2641',N'fr',N'Anguilla');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1080,N'2641',N'tr',N'Anguilla');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1081,N'2641',N'ms',N'Anguilla');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1082,N'2641',N'fa',N'Anguilla');

INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1083,N'963',N'fr',N'Syria');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1084,N'963',N'tr',N'Syria');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1085,N'963',N'ms',N'Syria');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1086,N'963',N'fa',N'Syria');

INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1087,N'1787',N'fr',N'Puerto Rico');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1088,N'1787',N'tr',N'Puerto Rico');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1089,N'1787',N'ms',N'Puerto Rico');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1090,N'1787',N'fa',N'Puerto Rico');

INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1091,N'964',N'fr',N'Iraq');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1092,N'964',N'tr',N'Iraq');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1093,N'964',N'ms',N'Iraq');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1094,N'964',N'fa',N'Iraq');

INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1095,N'1061',N'fr',N'Christmas Island');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1096,N'1061',N'tr',N'Christmas Island');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1097,N'1061',N'ms',N'Christmas Island');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1098,N'1061',N'fa',N'Christmas Island');

INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1099,N'1039',N'fr',N'Vatican City');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1100,N'1039',N'tr',N'Vatican City');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1101,N'1039',N'ms',N'Vatican City');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1102,N'1039',N'fa',N'Vatican City');

INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1103,N'996',N'fr',N'Kyrgyzstan');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1104,N'996',N'tr',N'Kyrgyzstan');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1105,N'996',N'ms',N'Kyrgyzstan');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1106,N'996',N'fa',N'Kyrgyzstan');

INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1107,N'995',N'fr',N'Georgia');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1108,N'995',N'tr',N'Georgia');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1109,N'995',N'ms',N'Georgia');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1110,N'995',N'fa',N'Georgia');

INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1111,N'993',N'fr',N'Turkmenistan');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1112,N'993',N'tr',N'Turkmenistan');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1113,N'993',N'ms',N'Turkmenistan');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1114,N'993',N'fa',N'Turkmenistan');

INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1115,N'992',N'fr',N'Tajikistan');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1116,N'992',N'tr',N'Tajikistan');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1117,N'992',N'ms',N'Tajikistan');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1118,N'992',N'fa',N'Tajikistan');

INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1119,N'977',N'fr',N'Nepal');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1120,N'977',N'tr',N'Nepal');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1121,N'977',N'ms',N'Nepal');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1122,N'977',N'fa',N'Nepal');

INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1123,N'975',N'fr',N'Bhutan');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1124,N'975',N'tr',N'Bhutan');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1125,N'975',N'ms',N'Bhutan');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1126,N'975',N'fa',N'Bhutan');

INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1127,N'972',N'fr',N'Philistine (Arab 48)');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1128,N'972',N'tr',N'Philistine (Arab 48)');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1129,N'972',N'ms',N'Philistine (Arab 48)');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1130,N'972',N'fa',N'Philistine (Arab 48)');

INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1131,N'971',N'fr',N'United Arab Emirates');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1132,N'971',N'tr',N'United Arab Emirates');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1133,N'971',N'ms',N'United Arab Emirates');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1134,N'971',N'fa',N'United Arab Emirates');

INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1135,N'374',N'fr',N'Armenia');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1136,N'374',N'tr',N'Armenia');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1137,N'374',N'ms',N'Armenia');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1138,N'374',N'fa',N'Armenia');

INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1139,N'649',N'fr',N'Turks and Caicos Islands');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1140,N'649',N'tr',N'Turks and Caicos Islands');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1141,N'649',N'ms',N'Turks and Caicos Islands');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1142,N'649',N'fa',N'Turks and Caicos Islands');

INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1143,N'681',N'fr',N'Wallis and Futuna Islands');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1144,N'681',N'tr',N'Wallis and Futuna Islands');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1145,N'681',N'ms',N'Wallis and Futuna Islands');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1146,N'681',N'fa',N'Wallis and Futuna Islands');

INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1147,N'680',N'fr',N'Palau');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1148,N'680',N'tr',N'Palau');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1149,N'680',N'ms',N'Palau');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1150,N'680',N'fa',N'Palau');

INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1151,N'679',N'fr',N'Fiji');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1152,N'679',N'tr',N'Fiji');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1153,N'679',N'ms',N'Fiji');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1154,N'679',N'fa',N'Fiji');

INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1155,N'678',N'fr',N'Vanuatu');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1156,N'678',N'tr',N'Vanuatu');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1157,N'678',N'ms',N'Vanuatu');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1158,N'678',N'fa',N'Vanuatu');

INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1159,N'677',N'fr',N'Solomon Islands');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1160,N'677',N'tr',N'Solomon Islands');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1161,N'677',N'ms',N'Solomon Islands');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1162,N'677',N'fa',N'Solomon Islands');

INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1163,N'676',N'fr',N'Tonga Islands');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1164,N'676',N'tr',N'Tonga Islands');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1165,N'676',N'ms',N'Tonga Islands');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1166,N'676',N'fa',N'Tonga Islands');

INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1167,N'387',N'fr',N'Bosnia and Herzegovina');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1168,N'387',N'tr',N'Bosnia and Herzegovina');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1169,N'387',N'ms',N'Bosnia and Herzegovina');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1170,N'387',N'fa',N'Bosnia and Herzegovina');

INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1171,N'386',N'fr',N'Slovenia');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1172,N'386',N'tr',N'Slovenia');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1173,N'386',N'ms',N'Slovenia');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1174,N'386',N'fa',N'Slovenia');

INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1175,N'385',N'fr',N'Croatia');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1176,N'385',N'tr',N'Croatia');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1177,N'385',N'ms',N'Croatia');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1178,N'385',N'fa',N'Croatia');

INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1179,N'380',N'fr',N'Ukraine');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1180,N'380',N'tr',N'Ukraine');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1181,N'380',N'ms',N'Ukraine');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1182,N'380',N'fa',N'Ukraine');

INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1183,N'378',N'fr',N'San Marino');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1184,N'378',N'tr',N'San Marino');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1185,N'378',N'ms',N'San Marino');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1186,N'378',N'fa',N'San Marino');

INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1187,N'377',N'fr',N'Monaco');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1188,N'377',N'tr',N'Monaco');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1189,N'377',N'ms',N'Monaco');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1190,N'377',N'fa',N'Monaco');

INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1191,N'683',N'fr',N'Niue');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1192,N'683',N'tr',N'Niue');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1193,N'683',N'ms',N'Niue');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1194,N'683',N'fa',N'Niue');

INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1195,N'967',N'fr',N'Yemen');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1196,N'967',N'tr',N'Yemen');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1197,N'967',N'ms',N'Yemen');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1198,N'967',N'fa',N'Yemen');

INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1199,N'968',N'fr',N'Oman');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1200,N'968',N'tr',N'Oman');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1201,N'968',N'ms',N'Oman');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1202,N'968',N'fa',N'Oman');

INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1203,N'970',N'fr',N'Philistine');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1204,N'970',N'tr',N'Philistine');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1205,N'970',N'ms',N'Philistine');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1206,N'970',N'fa',N'Philistine');

INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1207,N'973',N'fr',N'Bahrain');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1208,N'973',N'tr',N'Bahrain');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1209,N'973',N'ms',N'Bahrain');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1210,N'973',N'fa',N'Bahrain');

INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1211,N'974',N'fr',N'Qatar');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1212,N'974',N'tr',N'Qatar');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1213,N'974',N'ms',N'Qatar');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1214,N'974',N'fa',N'Qatar');

INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1215,N'994',N'fr',N'Azerbaijan');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1216,N'994',N'tr',N'Azerbaijan');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1217,N'994',N'ms',N'Azerbaijan');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1218,N'994',N'fa',N'Azerbaijan');

INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1219,N'375',N'fr',N'Belarus');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1220,N'375',N'tr',N'Belarus');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1221,N'375',N'ms',N'Belarus');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1222,N'375',N'fa',N'Belarus');

INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1223,N'420',N'fr',N'Czech Republic');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1224,N'420',N'tr',N'Czech Republic');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1225,N'420',N'ms',N'Czech Republic');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1226,N'420',N'fa',N'Czech Republic');

INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1227,N'852',N'fr',N'Hong Kong');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1228,N'852',N'tr',N'Hong Kong');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1229,N'852',N'ms',N'Hong Kong');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1230,N'852',N'fa',N'Hong Kong');

INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1231,N'371',N'fr',N'Latvia');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1232,N'371',N'tr',N'Latvia');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1233,N'371',N'ms',N'Latvia');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1234,N'371',N'fa',N'Latvia');

INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1235,N'373',N'fr',N'Moldavia');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1236,N'373',N'tr',N'Moldavia');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1237,N'373',N'ms',N'Moldavia');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1238,N'373',N'fa',N'Moldavia');

INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1239,N'684',N'fr',N'American Samoa');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1240,N'684',N'tr',N'American Samoa');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1241,N'684',N'ms',N'American Samoa');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1242,N'684',N'fa',N'American Samoa');

INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1243,N'976',N'fr',N'Mongolia');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1244,N'976',N'tr',N'Mongolia');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1245,N'976',N'ms',N'Mongolia');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1246,N'976',N'fa',N'Mongolia');

INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1247,N'6721',N'fr',N'Antarctica');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1248,N'6721',N'tr',N'Antarctica');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1249,N'6721',N'ms',N'Antarctica');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1250,N'6721',N'fa',N'Antarctica');

INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1251,N'592',N'fr',N'Guyana');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1252,N'592',N'tr',N'Guyana');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1253,N'592',N'ms',N'Guyana');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1254,N'592',N'fa',N'Guyana');

INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1255,N'591',N'fr',N'Bolivia');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1256,N'591',N'tr',N'Bolivia');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1257,N'591',N'ms',N'Bolivia');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1258,N'591',N'fa',N'Bolivia');

INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1259,N'590',N'fr',N'Guadeloupe');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1260,N'590',N'tr',N'Guadeloupe');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1261,N'590',N'ms',N'Guadeloupe');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1262,N'590',N'fa',N'Guadeloupe');

INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1263,N'509',N'fr',N'Haiti');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1264,N'509',N'tr',N'Haiti');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1265,N'509',N'ms',N'Haiti');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1266,N'509',N'fa',N'Haiti');

INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1267,N'508',N'fr',N'St. Pierre ,Miquelon');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1268,N'508',N'tr',N'St. Pierre ,Miquelon');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1269,N'508',N'ms',N'St. Pierre ,Miquelon');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1270,N'508',N'fa',N'St. Pierre ,Miquelon');

INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1271,N'507',N'fr',N'Panama');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1272,N'507',N'tr',N'Panama');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1273,N'507',N'ms',N'Panama');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1274,N'507',N'fa',N'Panama');

INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1275,N'506',N'fr',N'Costa Rica');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1276,N'506',N'tr',N'Costa Rica');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1277,N'506',N'ms',N'Costa Rica');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1278,N'506',N'fa',N'Costa Rica');

INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1279,N'505',N'fr',N'Nicaragua');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1280,N'505',N'tr',N'Nicaragua');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1281,N'505',N'ms',N'Nicaragua');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1282,N'505',N'fa',N'Nicaragua');

INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1283,N'966',N'fr',N'Saudi Arabia');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1284,N'966',N'tr',N'Saudi Arabia');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1285,N'966',N'ms',N'Saudi Arabia');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1286,N'966',N'fa',N'Saudi Arabia');

INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1287,N'597',N'fr',N'Suriname');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1288,N'597',N'tr',N'Suriname');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1289,N'597',N'ms',N'Suriname');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1290,N'597',N'fa',N'Suriname');

INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1291,N'2061',N'fr',N'Cocos-Keeling Islands');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1292,N'2061',N'tr',N'Cocos-Keeling Islands');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1293,N'2061',N'ms',N'Cocos-Keeling Islands');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1294,N'2061',N'fa',N'Cocos-Keeling Islands');

INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1295,N'686',N'fr',N'Kiribati');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1296,N'686',N'tr',N'Kiribati');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1297,N'686',N'ms',N'Kiribati');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1298,N'686',N'fa',N'Kiribati');

INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1299,N'692',N'fr',N'Marshall Islands');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1300,N'692',N'tr',N'Marshall Islands');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1301,N'692',N'ms',N'Marshall Islands');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1302,N'692',N'fa',N'Marshall Islands');

INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1303,N'682',N'fr',N'Cook Islands');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1304,N'682',N'tr',N'Cook Islands');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1305,N'682',N'ms',N'Cook Islands');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1306,N'682',N'fa',N'Cook Islands');

INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1307,N'6722',N'fr',N'Norfolk Island');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1308,N'6722',N'tr',N'Norfolk Island');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1309,N'6722',N'ms',N'Norfolk Island');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1310,N'6722',N'fa',N'Norfolk Island');

INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1311,N'593',N'fr',N'Ecuador');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1312,N'593',N'tr',N'Ecuador');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1313,N'593',N'ms',N'Ecuador');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1314,N'593',N'fa',N'Ecuador');

INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1315,N'2551',N'fr',N'Tanzania');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1316,N'2551',N'tr',N'Tanzania');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1317,N'2551',N'ms',N'Tanzania');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1318,N'2551',N'fa',N'Tanzania');

INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1319,N'685',N'fr',N'Western Samoa');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1320,N'685',N'tr',N'Western Samoa');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1321,N'685',N'ms',N'Western Samoa');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1322,N'685',N'fa',N'Western Samoa');

INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1323,N'6701',N'fr',N'East Timor');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1324,N'6701',N'tr',N'East Timor');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1325,N'6701',N'ms',N'East Timor');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1326,N'6701',N'fa',N'East Timor');

INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1327,N'44',N'fr',N'United Kingdom');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1328,N'44',N'tr',N'United Kingdom');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1329,N'44',N'ms',N'United Kingdom');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1330,N'44',N'fa',N'United Kingdom');

INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1331,N'43',N'fr',N'Austria');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1332,N'43',N'tr',N'Austria');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1333,N'43',N'ms',N'Austria');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1334,N'43',N'fa',N'Austria');

INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1335,N'41',N'fr',N'Switzerland');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1336,N'41',N'tr',N'Switzerland');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1337,N'41',N'ms',N'Switzerland');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1338,N'41',N'fa',N'Switzerland');

INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1339,N'40',N'fr',N'Romania');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1340,N'40',N'tr',N'Romania');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1341,N'40',N'ms',N'Romania');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1342,N'40',N'fa',N'Romania');

INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1343,N'39',N'fr',N'Italy');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1344,N'39',N'tr',N'Italy');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1345,N'39',N'ms',N'Italy');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1346,N'39',N'fa',N'Italy');

INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1347,N'36',N'fr',N'Hungary');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1348,N'36',N'tr',N'Hungary');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1349,N'36',N'ms',N'Hungary');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1350,N'36',N'fa',N'Hungary');

INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1351,N'34',N'fr',N'Spain');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1352,N'34',N'tr',N'Spain');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1353,N'34',N'ms',N'Spain');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1354,N'34',N'fa',N'Spain');

INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1355,N'33',N'fr',N'France');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1356,N'33',N'tr',N'France');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1357,N'33',N'ms',N'France');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1358,N'33',N'fa',N'France');

INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1359,N'95',N'fr',N'Myanmar');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1360,N'95',N'tr',N'Myanmar');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1361,N'95',N'ms',N'Myanmar');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1362,N'95',N'fa',N'Myanmar');

INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1363,N'52',N'fr',N'Mexico');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1364,N'52',N'tr',N'Mexico');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1365,N'52',N'ms',N'Mexico');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1366,N'52',N'fa',N'Mexico');

INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1367,N'250',N'fr',N'Rwanda');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1368,N'250',N'tr',N'Rwanda');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1369,N'250',N'ms',N'Rwanda');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1370,N'250',N'fa',N'Rwanda');

INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1371,N'233',N'fr',N'Ghana');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1372,N'233',N'tr',N'Ghana');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1373,N'233',N'ms',N'Ghana');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1374,N'233',N'fa',N'Ghana');

INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1375,N'232',N'fr',N'Sierra Leone');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1376,N'232',N'tr',N'Sierra Leone');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1377,N'232',N'ms',N'Sierra Leone');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1378,N'232',N'fa',N'Sierra Leone');

INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1379,N'231',N'fr',N'Liberia');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1380,N'231',N'tr',N'Liberia');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1381,N'231',N'ms',N'Liberia');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1382,N'231',N'fa',N'Liberia');

INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1383,N'262',N'fr',N'Réunion Island');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1384,N'262',N'tr',N'Réunion Island');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1385,N'262',N'ms',N'Réunion Island');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1386,N'262',N'fa',N'Réunion Island');

INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1387,N'261',N'fr',N'Madagascar');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1388,N'261',N'tr',N'Madagascar');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1389,N'261',N'ms',N'Madagascar');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1390,N'261',N'fa',N'Madagascar');

INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1391,N'260',N'fr',N'Zambia');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1392,N'260',N'tr',N'Zambia');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1393,N'260',N'ms',N'Zambia');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1394,N'260',N'fa',N'Zambia');

INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1395,N'258',N'fr',N'Mozambique');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1396,N'258',N'tr',N'Mozambique');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1397,N'258',N'ms',N'Mozambique');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1398,N'258',N'fa',N'Mozambique');

INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1399,N'257',N'fr',N'Burundi');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1400,N'257',N'tr',N'Burundi');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1401,N'257',N'ms',N'Burundi');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1402,N'257',N'fa',N'Burundi');

INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1403,N'256',N'fr',N'Uganda');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1404,N'256',N'tr',N'Uganda');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1405,N'256',N'ms',N'Uganda');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1406,N'256',N'fa',N'Uganda');

INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1407,N'253',N'fr',N'Djibouti');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1408,N'253',N'tr',N'Djibouti');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1409,N'253',N'ms',N'Djibouti');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1410,N'253',N'fa',N'Djibouti');

INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1411,N'212',N'fr',N'Morocco');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1412,N'212',N'tr',N'Morocco');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1413,N'212',N'ms',N'Morocco');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1414,N'212',N'fa',N'Morocco');

INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1415,N'251',N'fr',N'Ethiopia');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1416,N'251',N'tr',N'Ethiopia');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1417,N'251',N'ms',N'Ethiopia');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1418,N'251',N'fa',N'Ethiopia');

INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1419,N'249',N'fr',N'Sudan');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1420,N'249',N'tr',N'Sudan');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1421,N'249',N'ms',N'Sudan');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1422,N'249',N'fa',N'Sudan');

INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1423,N'265',N'fr',N'Malawi');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1424,N'265',N'tr',N'Malawi');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1425,N'265',N'ms',N'Malawi');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1426,N'265',N'fa',N'Malawi');

INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1427,N'350',N'fr',N'Gibraltar');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1428,N'350',N'tr',N'Gibraltar');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1429,N'350',N'ms',N'Gibraltar');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1430,N'350',N'fa',N'Gibraltar');

INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1431,N'345',N'fr',N'Cayman Islands');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1432,N'345',N'tr',N'Cayman Islands');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1433,N'345',N'ms',N'Cayman Islands');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1434,N'345',N'fa',N'Cayman Islands');

INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1435,N'340',N'fr',N'US Virgin Islands');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1436,N'340',N'tr',N'US Virgin Islands');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1437,N'340',N'ms',N'US Virgin Islands');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1438,N'340',N'fa',N'US Virgin Islands');

INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1439,N'299',N'fr',N'Greenland');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1440,N'299',N'tr',N'Greenland');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1441,N'299',N'ms',N'Greenland');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1442,N'299',N'fa',N'Greenland');

INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1443,N'298',N'fr',N'Faeroe Islands');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1444,N'298',N'tr',N'Faeroe Islands');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1445,N'298',N'ms',N'Faeroe Islands');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1446,N'298',N'fa',N'Faeroe Islands');

INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1447,N'297',N'fr',N'Aruba');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1448,N'297',N'tr',N'Aruba');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1449,N'297',N'ms',N'Aruba');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1450,N'297',N'fa',N'Aruba');

INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1451,N'291',N'fr',N'Eritrea');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1452,N'291',N'tr',N'Eritrea');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1453,N'291',N'ms',N'Eritrea');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1454,N'291',N'fa',N'Eritrea');

INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1455,N'550',N'fr',N'KOSOVO');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1456,N'550',N'tr',N'KOSOVO');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1457,N'550',N'ms',N'KOSOVO');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1458,N'550',N'fa',N'KOSOVO');

INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1459,N'7872',N'fr',N'Dagestan');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1460,N'7872',N'tr',N'Dagestan');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1461,N'7872',N'ms',N'Dagestan');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1462,N'7872',N'fa',N'Dagestan');

INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1463,N'381',N'fr',N'Serbia');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1464,N'381',N'tr',N'Serbia');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1465,N'381',N'ms',N'Serbia');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1466,N'381',N'fa',N'Serbia');

INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1467,N'382',N'fr',N'MONTENEGRO');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1468,N'382',N'tr',N'MONTENEGRO');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1469,N'382',N'ms',N'MONTENEGRO');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1470,N'382',N'fa',N'MONTENEGRO');

INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1471,N'9999',N'fr',N'Bohrah');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1472,N'9999',N'tr',N'Bohrah');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1473,N'9999',N'ms',N'Bohrah');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1474,N'9999',N'fa',N'Bohrah');

INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1475,N'211',N'fr',N'South Sudan');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1476,N'211',N'tr',N'South Sudan');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1477,N'211',N'ms',N'South Sudan');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1478,N'211',N'fa',N'South Sudan');

INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1479,N'9998',N'fr',N'unspecific Nationality');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1480,N'9998',N'tr',N'unspecific Nationality');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1481,N'9998',N'ms',N'unspecific Nationality');
INSERT INTO shc_portal.shc_country_lk(id,code,lang,label) VALUES(1482,N'9998',N'fa',N'unspecific Nationality');

SET IDENTITY_INSERT shc_portal.shc_country_lk OFF;
GO

SET IDENTITY_INSERT shc_portal.shc_health_immunization_lk ON;

INSERT INTO shc_portal.shc_health_immunization_lk(id,code,lang,label) VALUES(11,N'COVID-19',N'fr',N'Covid-19');
INSERT INTO shc_portal.shc_health_immunization_lk(id,code,lang,label) VALUES(12,N'COVID-19',N'tr',N'Covid-19');
INSERT INTO shc_portal.shc_health_immunization_lk(id,code,lang,label) VALUES(13,N'COVID-19',N'ms',N'Covid-19');
INSERT INTO shc_portal.shc_health_immunization_lk(id,code,lang,label) VALUES(14,N'COVID-19',N'fa',N'Covid-19');

INSERT INTO shc_portal.shc_health_immunization_lk(id,code,lang,label) VALUES(15,N'YELLOW_FEVER',N'fr',N'Yellow Fever');
INSERT INTO shc_portal.shc_health_immunization_lk(id,code,lang,label) VALUES(16,N'YELLOW_FEVER',N'tr',N'Yellow Fever');
INSERT INTO shc_portal.shc_health_immunization_lk(id,code,lang,label) VALUES(17,N'YELLOW_FEVER',N'ms',N'Yellow Fever');
INSERT INTO shc_portal.shc_health_immunization_lk(id,code,lang,label) VALUES(18,N'YELLOW_FEVER',N'fa',N'Yellow Fever');

INSERT INTO shc_portal.shc_health_immunization_lk(id,code,lang,label) VALUES(19,N'NEISSERIA_MENINGITIS',N'fr',N'Neisseria Meningitis');
INSERT INTO shc_portal.shc_health_immunization_lk(id,code,lang,label) VALUES(20,N'NEISSERIA_MENINGITIS',N'tr',N'Neisseria Meningitis');
INSERT INTO shc_portal.shc_health_immunization_lk(id,code,lang,label) VALUES(21,N'NEISSERIA_MENINGITIS',N'ms',N'Neisseria Meningitis');
INSERT INTO shc_portal.shc_health_immunization_lk(id,code,lang,label) VALUES(22,N'NEISSERIA_MENINGITIS',N'fa',N'Neisseria Meningitis');

INSERT INTO shc_portal.shc_health_immunization_lk(id,code,lang,label) VALUES(23,N'POLIOMYELITIS',N'fr',N'Poliomyelitis');
INSERT INTO shc_portal.shc_health_immunization_lk(id,code,lang,label) VALUES(24,N'POLIOMYELITIS',N'tr',N'Poliomyelitis');
INSERT INTO shc_portal.shc_health_immunization_lk(id,code,lang,label) VALUES(25,N'POLIOMYELITIS',N'ms',N'Poliomyelitis');
INSERT INTO shc_portal.shc_health_immunization_lk(id,code,lang,label) VALUES(26,N'POLIOMYELITIS',N'fa',N'Poliomyelitis');

INSERT INTO shc_portal.shc_health_immunization_lk(id,code,lang,label) VALUES(27,N'SEASONAL_FLU',N'fr',N'Seasonal Flu');
INSERT INTO shc_portal.shc_health_immunization_lk(id,code,lang,label) VALUES(28,N'SEASONAL_FLU',N'tr',N'Seasonal Flu');
INSERT INTO shc_portal.shc_health_immunization_lk(id,code,lang,label) VALUES(29,N'SEASONAL_FLU',N'ms',N'Seasonal Flu');
INSERT INTO shc_portal.shc_health_immunization_lk(id,code,lang,label) VALUES(30,N'SEASONAL_FLU',N'fa',N'Seasonal Flu');

SET IDENTITY_INSERT shc_portal.shc_health_immunization_lk OFF;
GO

SET IDENTITY_INSERT shc_portal.shc_health_special_needs_type_lk ON;

INSERT INTO shc_portal.shc_health_special_needs_type_lk(id,code,lang,label) VALUES(7,N'WHEELCHAIR',N'fr',N'Wheelchair');
INSERT INTO shc_portal.shc_health_special_needs_type_lk(id,code,lang,label) VALUES(8,N'WHEELCHAIR',N'tr',N'Wheelchair');
INSERT INTO shc_portal.shc_health_special_needs_type_lk(id,code,lang,label) VALUES(9,N'WHEELCHAIR',N'ms',N'Wheelchair');
INSERT INTO shc_portal.shc_health_special_needs_type_lk(id,code,lang,label) VALUES(10,N'WHEELCHAIR',N'fa',N'Wheelchair');

INSERT INTO shc_portal.shc_health_special_needs_type_lk(id,code,lang,label) VALUES(11,N'GOLF_CAR',N'fr',N'Golf Car');
INSERT INTO shc_portal.shc_health_special_needs_type_lk(id,code,lang,label) VALUES(12,N'GOLF_CAR',N'tr',N'Golf Car');
INSERT INTO shc_portal.shc_health_special_needs_type_lk(id,code,lang,label) VALUES(13,N'GOLF_CAR',N'ms',N'Golf Car');
INSERT INTO shc_portal.shc_health_special_needs_type_lk(id,code,lang,label) VALUES(14,N'GOLF_CAR',N'fa',N'Golf Car');

INSERT INTO shc_portal.shc_health_special_needs_type_lk(id,code,lang,label) VALUES(15,N'DEAF_DUMB',N'fr',N'Deaf and Dumb');
INSERT INTO shc_portal.shc_health_special_needs_type_lk(id,code,lang,label) VALUES(16,N'DEAF_DUMB',N'tr',N'Deaf and Dumb');
INSERT INTO shc_portal.shc_health_special_needs_type_lk(id,code,lang,label) VALUES(17,N'DEAF_DUMB',N'ms',N'Deaf and Dumb');
INSERT INTO shc_portal.shc_health_special_needs_type_lk(id,code,lang,label) VALUES(18,N'DEAF_DUMB',N'fa',N'Deaf and Dumb');

SET IDENTITY_INSERT shc_portal.shc_health_special_needs_type_lk OFF;
GO

SET IDENTITY_INSERT shc_portal.shc_housing_category_lk ON;

INSERT INTO shc_portal.shc_housing_category_lk(id,code,lang,label) VALUES(9,N'A',N'fr',N'A');
INSERT INTO shc_portal.shc_housing_category_lk(id,code,lang,label) VALUES(10,N'A',N'tr',N'A');
INSERT INTO shc_portal.shc_housing_category_lk(id,code,lang,label) VALUES(11,N'A',N'ms',N'A');
INSERT INTO shc_portal.shc_housing_category_lk(id,code,lang,label) VALUES(12,N'A',N'fa',N'A');

INSERT INTO shc_portal.shc_housing_category_lk(id,code,lang,label) VALUES(13,N'B',N'fr',N'B');
INSERT INTO shc_portal.shc_housing_category_lk(id,code,lang,label) VALUES(14,N'B',N'tr',N'B');
INSERT INTO shc_portal.shc_housing_category_lk(id,code,lang,label) VALUES(15,N'B',N'ms',N'B');
INSERT INTO shc_portal.shc_housing_category_lk(id,code,lang,label) VALUES(16,N'B',N'fa',N'B');

INSERT INTO shc_portal.shc_housing_category_lk(id,code,lang,label) VALUES(17,N'C',N'fr',N'C');
INSERT INTO shc_portal.shc_housing_category_lk(id,code,lang,label) VALUES(18,N'C',N'tr',N'C');
INSERT INTO shc_portal.shc_housing_category_lk(id,code,lang,label) VALUES(19,N'C',N'ms',N'C');
INSERT INTO shc_portal.shc_housing_category_lk(id,code,lang,label) VALUES(20,N'C',N'fa',N'C');

INSERT INTO shc_portal.shc_housing_category_lk(id,code,lang,label) VALUES(21,N'D',N'fr',N'D');
INSERT INTO shc_portal.shc_housing_category_lk(id,code,lang,label) VALUES(22,N'D',N'tr',N'D');
INSERT INTO shc_portal.shc_housing_category_lk(id,code,lang,label) VALUES(23,N'D',N'ms',N'D');
INSERT INTO shc_portal.shc_housing_category_lk(id,code,lang,label) VALUES(24,N'D',N'fa',N'D');

SET IDENTITY_INSERT shc_portal.shc_housing_category_lk OFF;
GO

SET IDENTITY_INSERT shc_portal.shc_housing_site_lk ON;

INSERT INTO shc_portal.shc_housing_site_lk(id,code,lang,label) VALUES(19,N'MAKKAH',N'fr',N'Makkah');
INSERT INTO shc_portal.shc_housing_site_lk(id,code,lang,label) VALUES(20,N'MAKKAH',N'tr',N'Makkah');
INSERT INTO shc_portal.shc_housing_site_lk(id,code,lang,label) VALUES(21,N'MAKKAH',N'ms',N'Makkah');
INSERT INTO shc_portal.shc_housing_site_lk(id,code,lang,label) VALUES(22,N'MAKKAH',N'fa',N'Makkah');

INSERT INTO shc_portal.shc_housing_site_lk(id,code,lang,label) VALUES(23,N'MADINA',N'fr',N'Madina');
INSERT INTO shc_portal.shc_housing_site_lk(id,code,lang,label) VALUES(24,N'MADINA',N'tr',N'Madina');
INSERT INTO shc_portal.shc_housing_site_lk(id,code,lang,label) VALUES(25,N'MADINA',N'ms',N'Madina');
INSERT INTO shc_portal.shc_housing_site_lk(id,code,lang,label) VALUES(26,N'MADINA',N'fa',N'Madina');

INSERT INTO shc_portal.shc_housing_site_lk(id,code,lang,label) VALUES(27,N'MAKKAH_HOLY_MOSQUE',N'fr',N'Holy Mosque in Mecca');
INSERT INTO shc_portal.shc_housing_site_lk(id,code,lang,label) VALUES(28,N'MAKKAH_HOLY_MOSQUE',N'tr',N'Holy Mosque in Mecca');
INSERT INTO shc_portal.shc_housing_site_lk(id,code,lang,label) VALUES(29,N'MAKKAH_HOLY_MOSQUE',N'ms',N'Holy Mosque in Mecca');
INSERT INTO shc_portal.shc_housing_site_lk(id,code,lang,label) VALUES(30,N'MAKKAH_HOLY_MOSQUE',N'fa',N'Holy Mosque in Mecca');

INSERT INTO shc_portal.shc_housing_site_lk(id,code,lang,label) VALUES(31,N'MADINA_HOLY_MOSQUE',N'fr',N'Holy Mosque in Madina');
INSERT INTO shc_portal.shc_housing_site_lk(id,code,lang,label) VALUES(32,N'MADINA_HOLY_MOSQUE',N'tr',N'Holy Mosque in Madina');
INSERT INTO shc_portal.shc_housing_site_lk(id,code,lang,label) VALUES(33,N'MADINA_HOLY_MOSQUE',N'ms',N'Holy Mosque in Madina');
INSERT INTO shc_portal.shc_housing_site_lk(id,code,lang,label) VALUES(34,N'MADINA_HOLY_MOSQUE',N'fa',N'Holy Mosque in Madina');

INSERT INTO shc_portal.shc_housing_site_lk(id,code,lang,label) VALUES(35,N'MENA',N'fr',N'Mena');
INSERT INTO shc_portal.shc_housing_site_lk(id,code,lang,label) VALUES(36,N'MENA',N'tr',N'Mena');
INSERT INTO shc_portal.shc_housing_site_lk(id,code,lang,label) VALUES(37,N'MENA',N'ms',N'Mena');
INSERT INTO shc_portal.shc_housing_site_lk(id,code,lang,label) VALUES(38,N'MENA',N'fa',N'Mena');

INSERT INTO shc_portal.shc_housing_site_lk(id,code,lang,label) VALUES(39,N'GAMARAT',N'fr',N'Gamarat');
INSERT INTO shc_portal.shc_housing_site_lk(id,code,lang,label) VALUES(40,N'GAMARAT',N'tr',N'Gamarat');
INSERT INTO shc_portal.shc_housing_site_lk(id,code,lang,label) VALUES(41,N'GAMARAT',N'ms',N'Gamarat');
INSERT INTO shc_portal.shc_housing_site_lk(id,code,lang,label) VALUES(42,N'GAMARAT',N'fa',N'Gamarat');

INSERT INTO shc_portal.shc_housing_site_lk(id,code,lang,label) VALUES(43,N'MUZDALIFA',N'fr',N'Muzdalifah');
INSERT INTO shc_portal.shc_housing_site_lk(id,code,lang,label) VALUES(44,N'MUZDALIFA',N'tr',N'Muzdalifah');
INSERT INTO shc_portal.shc_housing_site_lk(id,code,lang,label) VALUES(45,N'MUZDALIFA',N'ms',N'Muzdalifah');
INSERT INTO shc_portal.shc_housing_site_lk(id,code,lang,label) VALUES(46,N'MUZDALIFA',N'fa',N'Muzdalifah');

INSERT INTO shc_portal.shc_housing_site_lk(id,code,lang,label) VALUES(47,N'ARAFAT',N'fr',N'Arafat');
INSERT INTO shc_portal.shc_housing_site_lk(id,code,lang,label) VALUES(48,N'ARAFAT',N'tr',N'Arafat');
INSERT INTO shc_portal.shc_housing_site_lk(id,code,lang,label) VALUES(49,N'ARAFAT',N'ms',N'Arafat');
INSERT INTO shc_portal.shc_housing_site_lk(id,code,lang,label) VALUES(50,N'ARAFAT',N'fa',N'Arafat');

INSERT INTO shc_portal.shc_housing_site_lk(id,code,lang,label) VALUES(51,N'JABAL_ALRAHMA',N'fr',N'Alrahma Mountain');
INSERT INTO shc_portal.shc_housing_site_lk(id,code,lang,label) VALUES(52,N'JABAL_ALRAHMA',N'tr',N'Alrahma Mountain');
INSERT INTO shc_portal.shc_housing_site_lk(id,code,lang,label) VALUES(53,N'JABAL_ALRAHMA',N'ms',N'Alrahma Mountain');
INSERT INTO shc_portal.shc_housing_site_lk(id,code,lang,label) VALUES(54,N'JABAL_ALRAHMA',N'fa',N'Alrahma Mountain');

SET IDENTITY_INSERT shc_portal.shc_housing_site_lk OFF;
GO

SET IDENTITY_INSERT shc_portal.shc_housing_type_lk ON;

INSERT INTO shc_portal.shc_housing_type_lk(id,code,lang,label) VALUES(9,N'CAMP',N'fr',N'Camp');
INSERT INTO shc_portal.shc_housing_type_lk(id,code,lang,label) VALUES(10,N'CAMP',N'tr',N'Camp');
INSERT INTO shc_portal.shc_housing_type_lk(id,code,lang,label) VALUES(11,N'CAMP',N'ms',N'Camp');
INSERT INTO shc_portal.shc_housing_type_lk(id,code,lang,label) VALUES(12,N'CAMP',N'fa',N'Camp');

INSERT INTO shc_portal.shc_housing_type_lk(id,code,lang,label) VALUES(13,N'HOTEL',N'fr',N'Hotel');
INSERT INTO shc_portal.shc_housing_type_lk(id,code,lang,label) VALUES(14,N'HOTEL',N'tr',N'Hotel');
INSERT INTO shc_portal.shc_housing_type_lk(id,code,lang,label) VALUES(15,N'HOTEL',N'ms',N'Hotel');
INSERT INTO shc_portal.shc_housing_type_lk(id,code,lang,label) VALUES(16,N'HOTEL',N'fa',N'Hotel');

INSERT INTO shc_portal.shc_housing_type_lk(id,code,lang,label) VALUES(17,N'BUILDING',N'fr',N'Building');
INSERT INTO shc_portal.shc_housing_type_lk(id,code,lang,label) VALUES(18,N'BUILDING',N'tr',N'Building');
INSERT INTO shc_portal.shc_housing_type_lk(id,code,lang,label) VALUES(19,N'BUILDING',N'ms',N'Building');
INSERT INTO shc_portal.shc_housing_type_lk(id,code,lang,label) VALUES(20,N'BUILDING',N'fa',N'Building');

INSERT INTO shc_portal.shc_housing_type_lk(id,code,lang,label) VALUES(21,N'APARTMENT',N'fr',N'Apartment');
INSERT INTO shc_portal.shc_housing_type_lk(id,code,lang,label) VALUES(22,N'APARTMENT',N'tr',N'Apartment');
INSERT INTO shc_portal.shc_housing_type_lk(id,code,lang,label) VALUES(23,N'APARTMENT',N'ms',N'Apartment');
INSERT INTO shc_portal.shc_housing_type_lk(id,code,lang,label) VALUES(24,N'APARTMENT',N'fa',N'Apartment');

SET IDENTITY_INSERT shc_portal.shc_housing_type_lk OFF;
GO

SET IDENTITY_INSERT shc_portal.shc_incident_status_lk ON;

INSERT INTO shc_portal.shc_incident_status_lk(id,code,lang,label) VALUES(7,N'UNDER_PROCESSING',N'fr',N'Under Processing');
INSERT INTO shc_portal.shc_incident_status_lk(id,code,lang,label) VALUES(8,N'UNDER_PROCESSING',N'tr',N'Under Processing');
INSERT INTO shc_portal.shc_incident_status_lk(id,code,lang,label) VALUES(9,N'UNDER_PROCESSING',N'ms',N'Under Processing');
INSERT INTO shc_portal.shc_incident_status_lk(id,code,lang,label) VALUES(10,N'UNDER_PROCESSING',N'fa',N'Under Processing');

INSERT INTO shc_portal.shc_incident_status_lk(id,code,lang,label) VALUES(11,N'RESOLVED',N'fr',N'Resolved');
INSERT INTO shc_portal.shc_incident_status_lk(id,code,lang,label) VALUES(12,N'RESOLVED',N'tr',N'Resolved');
INSERT INTO shc_portal.shc_incident_status_lk(id,code,lang,label) VALUES(13,N'RESOLVED',N'ms',N'Resolved');
INSERT INTO shc_portal.shc_incident_status_lk(id,code,lang,label) VALUES(14,N'RESOLVED',N'fa',N'Resolved');

INSERT INTO shc_portal.shc_incident_status_lk(id,code,lang,label) VALUES(15,N'CLOSED',N'fr',N'Closed');
INSERT INTO shc_portal.shc_incident_status_lk(id,code,lang,label) VALUES(16,N'CLOSED',N'tr',N'Closed');
INSERT INTO shc_portal.shc_incident_status_lk(id,code,lang,label) VALUES(17,N'CLOSED',N'ms',N'Closed');
INSERT INTO shc_portal.shc_incident_status_lk(id,code,lang,label) VALUES(18,N'CLOSED',N'fa',N'Closed');

SET IDENTITY_INSERT shc_portal.shc_incident_status_lk OFF;
GO

SET IDENTITY_INSERT shc_portal.shc_incident_type_lk ON;

INSERT INTO shc_portal.shc_incident_type_lk(id,code,lang,label) VALUES(9,N'FOOD',N'fr',N'Food Complaint');
INSERT INTO shc_portal.shc_incident_type_lk(id,code,lang,label) VALUES(10,N'FOOD',N'tr',N'Food Complaint');
INSERT INTO shc_portal.shc_incident_type_lk(id,code,lang,label) VALUES(11,N'FOOD',N'ms',N'Food Complaint');
INSERT INTO shc_portal.shc_incident_type_lk(id,code,lang,label) VALUES(12,N'FOOD',N'fa',N'Food Complaint');

INSERT INTO shc_portal.shc_incident_type_lk(id,code,lang,label) VALUES(13,N'TRANSPORTATION',N'fr',N'Transportation Complaint');
INSERT INTO shc_portal.shc_incident_type_lk(id,code,lang,label) VALUES(14,N'TRANSPORTATION',N'tr',N'Transportation Complaint');
INSERT INTO shc_portal.shc_incident_type_lk(id,code,lang,label) VALUES(15,N'TRANSPORTATION',N'ms',N'Transportation Complaint');
INSERT INTO shc_portal.shc_incident_type_lk(id,code,lang,label) VALUES(16,N'TRANSPORTATION',N'fa',N'Transportation Complaint');

INSERT INTO shc_portal.shc_incident_type_lk(id,code,lang,label) VALUES(17,N'HOUSING',N'fr',N'Housing Complaint');
INSERT INTO shc_portal.shc_incident_type_lk(id,code,lang,label) VALUES(18,N'HOUSING',N'tr',N'Housing Complaint');
INSERT INTO shc_portal.shc_incident_type_lk(id,code,lang,label) VALUES(19,N'HOUSING',N'ms',N'Housing Complaint');
INSERT INTO shc_portal.shc_incident_type_lk(id,code,lang,label) VALUES(20,N'HOUSING',N'fa',N'Housing Complaint');

INSERT INTO shc_portal.shc_incident_type_lk(id,code,lang,label) VALUES(21,N'GENERAL',N'fr',N'General Complaint');
INSERT INTO shc_portal.shc_incident_type_lk(id,code,lang,label) VALUES(22,N'GENERAL',N'tr',N'General Complaint');
INSERT INTO shc_portal.shc_incident_type_lk(id,code,lang,label) VALUES(23,N'GENERAL',N'ms',N'General Complaint');
INSERT INTO shc_portal.shc_incident_type_lk(id,code,lang,label) VALUES(24,N'GENERAL',N'fa',N'General Complaint');

SET IDENTITY_INSERT shc_portal.shc_incident_type_lk OFF;
GO



SET IDENTITY_INSERT shc_portal.shc_marital_status_lk ON;

INSERT INTO shc_portal.shc_marital_status_lk(id,code,lang,label) VALUES(9,N'SINGLE',N'fr',N'Single');
INSERT INTO shc_portal.shc_marital_status_lk(id,code,lang,label) VALUES(10,N'SINGLE',N'tr',N'Single');
INSERT INTO shc_portal.shc_marital_status_lk(id,code,lang,label) VALUES(11,N'SINGLE',N'ms',N'Single');
INSERT INTO shc_portal.shc_marital_status_lk(id,code,lang,label) VALUES(12,N'SINGLE',N'fa',N'Single');

INSERT INTO shc_portal.shc_marital_status_lk(id,code,lang,label) VALUES(13,N'MARRIED',N'fr',N'Married');
INSERT INTO shc_portal.shc_marital_status_lk(id,code,lang,label) VALUES(14,N'MARRIED',N'tr',N'Married');
INSERT INTO shc_portal.shc_marital_status_lk(id,code,lang,label) VALUES(15,N'MARRIED',N'ms',N'Married');
INSERT INTO shc_portal.shc_marital_status_lk(id,code,lang,label) VALUES(16,N'MARRIED',N'fa',N'Married');

INSERT INTO shc_portal.shc_marital_status_lk(id,code,lang,label) VALUES(17,N'WIDOWED',N'fr',N'Widowed');
INSERT INTO shc_portal.shc_marital_status_lk(id,code,lang,label) VALUES(18,N'WIDOWED',N'tr',N'Widowed');
INSERT INTO shc_portal.shc_marital_status_lk(id,code,lang,label) VALUES(19,N'WIDOWED',N'ms',N'Widowed');
INSERT INTO shc_portal.shc_marital_status_lk(id,code,lang,label) VALUES(20,N'WIDOWED',N'fa',N'Widowed');

INSERT INTO shc_portal.shc_marital_status_lk(id,code,lang,label) VALUES(21,N'DIVORCED',N'fr',N'Divorced');
INSERT INTO shc_portal.shc_marital_status_lk(id,code,lang,label) VALUES(22,N'DIVORCED',N'tr',N'Divorced');
INSERT INTO shc_portal.shc_marital_status_lk(id,code,lang,label) VALUES(23,N'DIVORCED',N'ms',N'Divorced');
INSERT INTO shc_portal.shc_marital_status_lk(id,code,lang,label) VALUES(24,N'DIVORCED',N'fa',N'Divorced');

SET IDENTITY_INSERT shc_portal.shc_marital_status_lk OFF;
GO

SET IDENTITY_INSERT shc_portal.shc_meal_time_type_lk ON;

INSERT INTO shc_portal.shc_meal_time_type_lk(id,code,lang,label) VALUES(9,N'BREAKFAST',N'fr',N'Breakfast');
INSERT INTO shc_portal.shc_meal_time_type_lk(id,code,lang,label) VALUES(10,N'BREAKFAST',N'tr',N'Breakfast');
INSERT INTO shc_portal.shc_meal_time_type_lk(id,code,lang,label) VALUES(11,N'BREAKFAST',N'ms',N'Breakfast');
INSERT INTO shc_portal.shc_meal_time_type_lk(id,code,lang,label) VALUES(12,N'BREAKFAST',N'fa',N'Breakfast');

INSERT INTO shc_portal.shc_meal_time_type_lk(id,code,lang,label) VALUES(13,N'LUNCH',N'fr',N'Lunch');
INSERT INTO shc_portal.shc_meal_time_type_lk(id,code,lang,label) VALUES(14,N'LUNCH',N'tr',N'Lunch');
INSERT INTO shc_portal.shc_meal_time_type_lk(id,code,lang,label) VALUES(15,N'LUNCH',N'ms',N'Lunch');
INSERT INTO shc_portal.shc_meal_time_type_lk(id,code,lang,label) VALUES(16,N'LUNCH',N'fa',N'Lunch');

INSERT INTO shc_portal.shc_meal_time_type_lk(id,code,lang,label) VALUES(17,N'DINNER',N'fr',N'Dinner');
INSERT INTO shc_portal.shc_meal_time_type_lk(id,code,lang,label) VALUES(18,N'DINNER',N'tr',N'Dinner');
INSERT INTO shc_portal.shc_meal_time_type_lk(id,code,lang,label) VALUES(19,N'DINNER',N'ms',N'Dinner');
INSERT INTO shc_portal.shc_meal_time_type_lk(id,code,lang,label) VALUES(20,N'DINNER',N'fa',N'Dinner');

INSERT INTO shc_portal.shc_meal_time_type_lk(id,code,lang,label) VALUES(21,N'OTHERS',N'fr',N'Others');
INSERT INTO shc_portal.shc_meal_time_type_lk(id,code,lang,label) VALUES(22,N'OTHERS',N'tr',N'Others');
INSERT INTO shc_portal.shc_meal_time_type_lk(id,code,lang,label) VALUES(23,N'OTHERS',N'ms',N'Others');
INSERT INTO shc_portal.shc_meal_time_type_lk(id,code,lang,label) VALUES(24,N'OTHERS',N'fa',N'Others');

SET IDENTITY_INSERT shc_portal.shc_meal_time_type_lk OFF;
GO

SET IDENTITY_INSERT shc_portal.shc_meal_type_lk ON;

INSERT INTO shc_portal.shc_meal_type_lk(id,code,lang,label) VALUES(11,N'FULL_BOARD',N'fr',N'Breakfast, lunch, and dinner buffets');
INSERT INTO shc_portal.shc_meal_type_lk(id,code,lang,label) VALUES(12,N'FULL_BOARD',N'tr',N'Breakfast, lunch, and dinner buffets');
INSERT INTO shc_portal.shc_meal_type_lk(id,code,lang,label) VALUES(13,N'FULL_BOARD',N'ms',N'Breakfast, lunch, and dinner buffets');
INSERT INTO shc_portal.shc_meal_type_lk(id,code,lang,label) VALUES(14,N'FULL_BOARD',N'fa',N'Breakfast, lunch, and dinner buffets');

INSERT INTO shc_portal.shc_meal_type_lk(id,code,lang,label) VALUES(15,N'BREAKFAST_DINNER',N'fr',N'Breakfast, and dinner buffets');
INSERT INTO shc_portal.shc_meal_type_lk(id,code,lang,label) VALUES(16,N'BREAKFAST_DINNER',N'tr',N'Breakfast, and dinner buffets');
INSERT INTO shc_portal.shc_meal_type_lk(id,code,lang,label) VALUES(17,N'BREAKFAST_DINNER',N'ms',N'Breakfast, and dinner buffets');
INSERT INTO shc_portal.shc_meal_type_lk(id,code,lang,label) VALUES(18,N'BREAKFAST_DINNER',N'fa',N'Breakfast, and dinner buffets');

INSERT INTO shc_portal.shc_meal_type_lk(id,code,lang,label) VALUES(19,N'BREAKFAST_LUNCH',N'fr',N'Breakfast, and lunch');
INSERT INTO shc_portal.shc_meal_type_lk(id,code,lang,label) VALUES(20,N'BREAKFAST_LUNCH',N'tr',N'Breakfast, and lunch');
INSERT INTO shc_portal.shc_meal_type_lk(id,code,lang,label) VALUES(21,N'BREAKFAST_LUNCH',N'ms',N'Breakfast, and lunch');
INSERT INTO shc_portal.shc_meal_type_lk(id,code,lang,label) VALUES(22,N'BREAKFAST_LUNCH',N'fa',N'Breakfast, and lunch');

INSERT INTO shc_portal.shc_meal_type_lk(id,code,lang,label) VALUES(23,N'ONLY_BREAKFAST',N'fr',N'Only Breakfast');
INSERT INTO shc_portal.shc_meal_type_lk(id,code,lang,label) VALUES(24,N'ONLY_BREAKFAST',N'tr',N'Only Breakfast');
INSERT INTO shc_portal.shc_meal_type_lk(id,code,lang,label) VALUES(25,N'ONLY_BREAKFAST',N'ms',N'Only Breakfast');
INSERT INTO shc_portal.shc_meal_type_lk(id,code,lang,label) VALUES(26,N'ONLY_BREAKFAST',N'fa',N'Only Breakfast');

INSERT INTO shc_portal.shc_meal_type_lk(id,code,lang,label) VALUES(27,N'OTHERS',N'fr',N'Others');
INSERT INTO shc_portal.shc_meal_type_lk(id,code,lang,label) VALUES(28,N'OTHERS',N'tr',N'Others');
INSERT INTO shc_portal.shc_meal_type_lk(id,code,lang,label) VALUES(29,N'OTHERS',N'ms',N'Others');
INSERT INTO shc_portal.shc_meal_type_lk(id,code,lang,label) VALUES(30,N'OTHERS',N'fa',N'Others');

SET IDENTITY_INSERT shc_portal.shc_meal_type_lk OFF;
GO



SET IDENTITY_INSERT shc_portal.shc_nationality_lk ON;

INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(653,N'101',N'fr',N'Emirati',N'971',N'AE');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(654,N'101',N'tr',N'Emirati',N'971',N'AE');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(655,N'101',N'ms',N'Emirati',N'971',N'Emirati');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(656,N'101',N'fa',N'Emirati',N'971',N'AE');

INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(657,N'102',N'fr',N'Jordanian',N'962',N'JO');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(658,N'102',N'tr',N'Jordanian',N'962',N'JO');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(659,N'102',N'ms',N'Jordanian',N'962',N'Jordanian');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(660,N'102',N'fa',N'Jordanian',N'962',N'JO');

INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(661,N'103',N'fr',N'Bahraini',N'973',N'BH');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(662,N'103',N'tr',N'Bahraini',N'973',N'BH');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(663,N'103',N'ms',N'Bahraini',N'973',N'Bahraini');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(664,N'103',N'fa',N'Bahraini',N'973',N'BH');

INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(665,N'104',N'fr',N'Syrian',N'963',N'SY');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(666,N'104',N'tr',N'Syrian',N'963',N'SY');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(667,N'104',N'ms',N'Syrian',N'963',N'Syrian');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(668,N'104',N'fa',N'Syrian',N'963',N'SY');

INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(669,N'105',N'fr',N'Iraqi',N'964',N'IQ');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(670,N'105',N'tr',N'Iraqi',N'964',N'IQ');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(671,N'105',N'ms',N'Iraqi',N'964',N'Iraqi');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(672,N'105',N'fa',N'Iraqi',N'964',N'IQ');

INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(673,N'106',N'fr',N'Omani',N'968',N'OM');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(674,N'106',N'tr',N'Omani',N'968',N'OM');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(675,N'106',N'ms',N'Omani',N'968',N'Omani');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(676,N'106',N'fa',N'Omani',N'968',N'OM');

INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(677,N'107',N'fr',N'Palestinian',N'970',N'PS');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(678,N'107',N'tr',N'Palestinian',N'970',N'PS');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(679,N'107',N'ms',N'Palestinian',N'970',N'Palestinian');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(680,N'107',N'fa',N'Palestinian',N'970',N'PS');

INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(681,N'108',N'fr',N'Qatari',N'974',N'QA');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(682,N'108',N'tr',N'Qatari',N'974',N'QA');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(683,N'108',N'ms',N'Qatari',N'974',N'Qatari');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(684,N'108',N'fa',N'Qatari',N'974',N'QA');

INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(685,N'109',N'fr',N'Kuwaiti',N'965',N'KW');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(686,N'109',N'tr',N'Kuwaiti',N'965',N'KW');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(687,N'109',N'ms',N'Kuwaiti',N'965',N'Kuwaiti');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(688,N'109',N'fa',N'Kuwaiti',N'965',N'KW');

INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(689,N'110',N'fr',N'Lebanese',N'961',N'LB');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(690,N'110',N'tr',N'Lebanese',N'961',N'LB');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(691,N'110',N'ms',N'Lebanese',N'961',N'Lebanese');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(692,N'110',N'fa',N'Lebanese',N'961',N'LB');

INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(693,N'111',N'fr',N'Yemen',N'967',N'YE');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(694,N'111',N'tr',N'Yemen',N'967',N'YE');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(695,N'111',N'ms',N'Yemen',N'967',N'Yemen');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(696,N'111',N'fa',N'Yemen',N'967',N'YE');

INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(697,N'113',N'fr',N'Saudi',N'966',N'SA');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(698,N'113',N'tr',N'Saudi',N'966',N'SA');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(699,N'113',N'ms',N'Saudi',N'966',N'Saudi');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(700,N'113',N'fa',N'Saudi',N'966',N'SA');

INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(701,N'201',N'fr',N'Tunisian',N'216',N'TN');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(702,N'201',N'tr',N'Tunisian',N'216',N'TN');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(703,N'201',N'ms',N'Tunisian',N'216',N'Tunisian');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(704,N'201',N'fa',N'Tunisian',N'216',N'TN');

INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(705,N'202',N'fr',N'Algerian',N'213',N'DZ');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(706,N'202',N'tr',N'Algerian',N'213',N'DZ');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(707,N'202',N'ms',N'Algerian',N'213',N'Algerian');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(708,N'202',N'fa',N'Algerian',N'213',N'DZ');

INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(709,N'203',N'fr',N'Djiboutian',N'253',N'DJ');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(710,N'203',N'tr',N'Djiboutian',N'253',N'DJ');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(711,N'203',N'ms',N'Djiboutian',N'253',N'Djiboutian');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(712,N'203',N'fa',N'Djiboutian',N'253',N'DJ');

INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(713,N'204',N'fr',N'Sudanese',N'249',N'SD');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(714,N'204',N'tr',N'Sudanese',N'249',N'SD');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(715,N'204',N'ms',N'Sudanese',N'249',N'Sudanese');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(716,N'204',N'fa',N'Sudanese',N'249',N'SD');

INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(717,N'205',N'fr',N'Somalian ',N'252',N'SO');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(718,N'205',N'tr',N'Somalian ',N'252',N'SO');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(719,N'205',N'ms',N'Somalian ',N'252',N'Somalian ');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(720,N'205',N'fa',N'Somalian ',N'252',N'SO');

INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(721,N'206',N'fr',N'Libyan',N'218',N'LY');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(722,N'206',N'tr',N'Libyan',N'218',N'LY');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(723,N'206',N'ms',N'Libyan',N'218',N'Libyan');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(724,N'206',N'fa',N'Libyan',N'218',N'LY');

INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(725,N'207',N'fr',N'Egyptian',N'20',N'EG');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(726,N'207',N'tr',N'Egyptian',N'20',N'EG');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(727,N'207',N'ms',N'Egyptian',N'20',N'Egyptian');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(728,N'207',N'fa',N'Egyptian',N'20',N'EG');

INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(729,N'208',N'fr',N'Moroccan',N'212',N'MA');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(730,N'208',N'tr',N'Moroccan',N'212',N'MA');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(731,N'208',N'ms',N'Moroccan',N'212',N'Moroccan');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(732,N'208',N'fa',N'Moroccan',N'212',N'MA');

INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(733,N'209',N'fr',N'Mauritanian',N'222',N'MR');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(734,N'209',N'tr',N'Mauritanian',N'222',N'MR');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(735,N'209',N'ms',N'Mauritanian',N'222',N'Mauritanian');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(736,N'209',N'fa',N'Mauritanian',N'222',N'MR');

INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(737,N'301',N'fr',N'Afghan',N'93',N'AF');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(738,N'301',N'tr',N'Afghan',N'93',N'AF');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(739,N'301',N'ms',N'Afghan',N'93',N'Afghan');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(740,N'301',N'fa',N'Afghan',N'93',N'AF');

INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(741,N'302',N'fr',N'Indonesian',N'62',N'ID');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(742,N'302',N'tr',N'Indonesian',N'62',N'ID');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(743,N'302',N'ms',N'Indonesian',N'62',N'Indonesian');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(744,N'302',N'fa',N'Indonesian',N'62',N'ID');

INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(745,N'303',N'fr',N'Iranian',N'98',N'IR');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(746,N'303',N'tr',N'Iranian',N'98',N'IR');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(747,N'303',N'ms',N'Iranian',N'98',N'Iranian');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(748,N'303',N'fa',N'Iranian',N'98',N'IR');

INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(749,N'304',N'fr',N'Pakistani',N'92',N'PK');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(750,N'304',N'tr',N'Pakistani',N'92',N'PK');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(751,N'304',N'ms',N'Pakistani',N'92',N'Pakistani');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(752,N'304',N'fa',N'Pakistani',N'92',N'PK');

INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(753,N'305',N'fr',N'Bangladeshi',N'880',N'BD');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(754,N'305',N'tr',N'Bangladeshi',N'880',N'BD');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(755,N'305',N'ms',N'Bangladeshi',N'880',N'Bangladeshi');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(756,N'305',N'fa',N'Bangladeshi',N'880',N'BD');

INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(757,N'306',N'fr',N'Brunein',N'673',N'BN');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(758,N'306',N'tr',N'Brunein',N'673',N'BN');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(759,N'306',N'ms',N'Brunein',N'673',N'Brunein');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(760,N'306',N'fa',N'Brunein',N'673',N'BN');

INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(761,N'307',N'fr',N'Myanmarian',N'95',N'MM');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(762,N'307',N'tr',N'Myanmarian',N'95',N'MM');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(763,N'307',N'ms',N'Myanmarian',N'95',N'Myanmarian');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(764,N'307',N'fa',N'Myanmarian',N'95',N'MM');

INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(765,N'308',N'fr',N'Thai',N'66',N'TH');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(766,N'308',N'tr',N'Thai',N'66',N'TH');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(767,N'308',N'ms',N'Thai',N'66',N'Thai');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(768,N'308',N'fa',N'Thai',N'66',N'TH');

INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(769,N'309',N'fr',N'Turkish',N'90',N'TR');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(770,N'309',N'tr',N'Turkish',N'90',N'TR');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(771,N'309',N'ms',N'Turkish',N'90',N'Turkish');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(772,N'309',N'fa',N'Turkish',N'90',N'TR');

INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(773,N'310',N'fr',N'Maldivian',N'960',N'MV');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(774,N'310',N'tr',N'Maldivian',N'960',N'MV');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(775,N'310',N'ms',N'Maldivian',N'960',N'Maldivian');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(776,N'310',N'fa',N'Maldivian',N'960',N'MV');

INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(777,N'311',N'fr',N'Russian',N'7',N'RU');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(778,N'311',N'tr',N'Russian',N'7',N'RU');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(779,N'311',N'ms',N'Russian',N'7',N'Russian');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(780,N'311',N'fa',N'Russian',N'7',N'RU');

INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(781,N'312',N'fr',N'Singaporean',N'65',N'SG');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(782,N'312',N'tr',N'Singaporean',N'65',N'SG');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(783,N'312',N'ms',N'Singaporean',N'65',N'Singaporean');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(784,N'312',N'fa',N'Singaporean',N'65',N'SG');

INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(785,N'313',N'fr',N'Srilankan',N'94',N'LK');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(786,N'313',N'tr',N'Srilankan',N'94',N'LK');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(787,N'313',N'ms',N'Srilankan',N'94',N'Srilankan');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(788,N'313',N'fa',N'Srilankan',N'94',N'LK');

INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(789,N'315',N'fr',N'Philippino',N'63',N'PH');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(790,N'315',N'tr',N'Philippino',N'63',N'PH');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(791,N'315',N'ms',N'Philippino',N'63',N'Philippino');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(792,N'315',N'fa',N'Philippino',N'63',N'PH');

INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(793,N'316',N'fr',N'Vietnamese',N'84',N'VN');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(794,N'316',N'tr',N'Vietnamese',N'84',N'VN');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(795,N'316',N'ms',N'Vietnamese',N'84',N'Vietnamese');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(796,N'316',N'fa',N'Vietnamese',N'84',N'VN');

INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(797,N'317',N'fr',N'Cambodian',N'855',N'KH');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(798,N'317',N'tr',N'Cambodian',N'855',N'KH');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(799,N'317',N'ms',N'Cambodian',N'855',N'Cambodian');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(800,N'317',N'fa',N'Cambodian',N'855',N'KH');

INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(801,N'318',N'fr',N'Korean',N'82',N'KR');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(802,N'318',N'tr',N'Korean',N'82',N'KR');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(803,N'318',N'ms',N'Korean',N'82',N'Korean');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(804,N'318',N'fa',N'Korean',N'82',N'KR');

INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(805,N'319',N'fr',N'Malaysian',N'60',N'MY');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(806,N'319',N'tr',N'Malaysian',N'60',N'MY');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(807,N'319',N'ms',N'Malaysian',N'60',N'Malaysian');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(808,N'319',N'fa',N'Malaysian',N'60',N'MY');

INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(809,N'320',N'fr',N'Nepalese',N'977',N'NP');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(810,N'320',N'tr',N'Nepalese',N'977',N'NP');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(811,N'320',N'ms',N'Nepalese',N'977',N'Nepalese');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(812,N'320',N'fa',N'Nepalese',N'977',N'NP');

INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(813,N'321',N'fr',N'Indian',N'91',N'IN');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(814,N'321',N'tr',N'Indian',N'91',N'IN');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(815,N'321',N'ms',N'Indian',N'91',N'Indian');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(816,N'321',N'fa',N'Indian',N'91',N'IN');

INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(817,N'322',N'fr',N'Hong Konger',N'852',N'HK');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(818,N'322',N'tr',N'Hong Konger',N'852',N'HK');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(819,N'322',N'ms',N'Hong Konger',N'852',N'Hong Konger');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(820,N'322',N'fa',N'Hong Konger',N'852',N'HK');

INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(821,N'323',N'fr',N'Japanese',N'81',N'JP');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(822,N'323',N'tr',N'Japanese',N'81',N'JP');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(823,N'323',N'ms',N'Japanese',N'81',N'Japanese');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(824,N'323',N'fa',N'Japanese',N'81',N'JP');

INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(825,N'324',N'fr',N'Bhutanese',N'975',N'BT');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(826,N'324',N'tr',N'Bhutanese',N'975',N'BT');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(827,N'324',N'ms',N'Bhutanese',N'975',N'Bhutanese');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(828,N'324',N'fa',N'Bhutanese',N'975',N'BT');

INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(829,N'325',N'fr',N'Chinese',N'86',N'CN');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(830,N'325',N'tr',N'Chinese',N'86',N'CN');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(831,N'325',N'ms',N'Chinese',N'86',N'Chinese');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(832,N'325',N'fa',N'Chinese',N'86',N'CN');

INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(833,N'326',N'fr',N'Cypriote',N'357',N'CY');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(834,N'326',N'tr',N'Cypriote',N'357',N'CY');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(835,N'326',N'ms',N'Cypriote',N'357',N'Cypriote');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(836,N'326',N'fa',N'Cypriote',N'357',N'CY');

INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(837,N'328',N'fr',N'Korean N.',N'850',N'KP');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(838,N'328',N'tr',N'Korean N.',N'850',N'KP');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(839,N'328',N'ms',N'Korean N.',N'850',N'Korean N.');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(840,N'328',N'fa',N'Korean N.',N'850',N'KP');

INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(841,N'329',N'fr',N'Laosian',N'856',N'LA');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(842,N'329',N'tr',N'Laosian',N'856',N'LA');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(843,N'329',N'ms',N'Laosian',N'856',N'Laosian');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(844,N'329',N'fa',N'Laosian',N'856',N'LA');

INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(845,N'330',N'fr',N'Mongolian',N'976',N'MN');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(846,N'330',N'tr',N'Mongolian',N'976',N'MN');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(847,N'330',N'ms',N'Mongolian',N'976',N'Mongolian');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(848,N'330',N'fa',N'Mongolian',N'976',N'MN');

INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(849,N'331',N'fr',N'Macauese',N'853',N'MO');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(850,N'331',N'tr',N'Macauese',N'853',N'MO');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(851,N'331',N'ms',N'Macauese',N'853',N'Macauese');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(852,N'331',N'fa',N'Macauese',N'853',N'MO');

INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(853,N'332',N'fr',N'Turkistani',N'72533',N'KZ-YUZ');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(854,N'332',N'tr',N'Turkistani',N'72533',N'KZ-YUZ');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(855,N'332',N'ms',N'Turkistani',N'72533',N'Turkistani');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(856,N'332',N'fa',N'Turkistani',N'72533',N'KZ-YUZ');

INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(857,N'336',N'fr',N'Kazakh',N'7',N'KZ');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(858,N'336',N'tr',N'Kazakh',N'7',N'KZ');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(859,N'336',N'ms',N'Kazakh',N'7',N'Kazakh');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(860,N'336',N'fa',N'Kazakh',N'7',N'KZ');

INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(861,N'337',N'fr',N'Uzbek',N'998',N'UZ');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(862,N'337',N'tr',N'Uzbek',N'998',N'UZ');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(863,N'337',N'ms',N'Uzbek',N'998',N'Uzbek');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(864,N'337',N'fa',N'Uzbek',N'998',N'UZ');

INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(865,N'338',N'fr',N'Turkmenenian',N'993',N'TM');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(866,N'338',N'tr',N'Turkmenenian',N'993',N'TM');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(867,N'338',N'ms',N'Turkmenenian',N'993',N'Turkmenenian');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(868,N'338',N'fa',N'Turkmenenian',N'993',N'TM');

INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(869,N'339',N'fr',N'Tajik',N'992',N'TJ');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(870,N'339',N'tr',N'Tajik',N'992',N'TJ');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(871,N'339',N'ms',N'Tajik',N'992',N'Tajik');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(872,N'339',N'fa',N'Tajik',N'992',N'TJ');

INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(873,N'340',N'fr',N'Kyrgyzian',N'996',N'KG');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(874,N'340',N'tr',N'Kyrgyzian',N'996',N'KG');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(875,N'340',N'ms',N'Kyrgyzian',N'996',N'Kyrgyzian');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(876,N'340',N'fa',N'Kyrgyzian',N'996',N'KG');

INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(877,N'343',N'fr',N'Azerbaijani',N'994',N'AZ');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(878,N'343',N'tr',N'Azerbaijani',N'994',N'AZ');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(879,N'343',N'ms',N'Azerbaijani',N'994',N'Azerbaijani');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(880,N'343',N'fa',N'Azerbaijani',N'994',N'AZ');

INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(881,N'344',N'fr',N'Chechenian',N'7871',N'RU-CE');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(882,N'344',N'tr',N'Chechenian',N'7871',N'RU-CE');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(883,N'344',N'ms',N'Chechenian',N'7871',N'Chechenian');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(884,N'344',N'fa',N'Chechenian',N'7871',N'RU-CE');

INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(885,N'345',N'fr',N'Daghestani',N'7872',N'RU-DA');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(886,N'345',N'tr',N'Daghestani',N'7872',N'RU-DA');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(887,N'345',N'ms',N'Daghestani',N'7872',N'Daghestani');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(888,N'345',N'fa',N'Daghestani',N'7872',N'RU-DA');

INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(889,N'212',N'fr',N'Tataristani',N'843',N'RU-TA');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(890,N'212',N'tr',N'Tataristani',N'843',N'RU-TA');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(891,N'212',N'ms',N'Tataristani',N'843',N'Tataristani');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(892,N'212',N'fa',N'Tataristani',N'843',N'RU-TA');

INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(893,N'349',N'fr',N'East Timor',N'670',N'TL');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(894,N'349',N'tr',N'East Timor',N'670',N'TL');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(895,N'349',N'ms',N'East Timor',N'670',N'East Timor');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(896,N'349',N'fa',N'East Timor',N'670',N'TL');

INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(897,N'401',N'fr',N'Ethiopian',N'251',N'ET');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(898,N'401',N'tr',N'Ethiopian',N'251',N'ET');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(899,N'401',N'ms',N'Ethiopian',N'251',N'Ethiopian');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(900,N'401',N'fa',N'Ethiopian',N'251',N'ET');

INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(901,N'402',N'fr',N'Ugandan',N'256',N'UG');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(902,N'402',N'tr',N'Ugandan',N'256',N'UG');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(903,N'402',N'ms',N'Ugandan',N'256',N'Ugandan');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(904,N'402',N'fa',N'Ugandan',N'256',N'UG');

INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(905,N'403',N'fr',N'Botswanian',N'267',N'BW');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(906,N'403',N'tr',N'Botswanian',N'267',N'BW');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(907,N'403',N'ms',N'Botswanian',N'267',N'Botswanian');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(908,N'403',N'fa',N'Botswanian',N'267',N'BW');

INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(909,N'404',N'fr',N'Burundian',N'257',N'BI');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(910,N'404',N'tr',N'Burundian',N'257',N'BI');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(911,N'404',N'ms',N'Burundian',N'257',N'Burundian');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(912,N'404',N'fa',N'Burundian',N'257',N'BI');

INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(913,N'405',N'fr',N'Chadian',N'235',N'TD');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(914,N'405',N'tr',N'Chadian',N'235',N'TD');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(915,N'405',N'ms',N'Chadian',N'235',N'Chadian');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(916,N'405',N'fa',N'Chadian',N'235',N'TD');

INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(917,N'406',N'fr',N'Tanzanian',N'255',N'TZ');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(918,N'406',N'tr',N'Tanzanian',N'255',N'TZ');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(919,N'406',N'ms',N'Tanzanian',N'255',N'Tanzanian');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(920,N'406',N'fa',N'Tanzanian',N'255',N'TZ');

INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(921,N'407',N'fr',N'Togolese',N'228',N'TG');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(922,N'407',N'tr',N'Togolese',N'228',N'TG');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(923,N'407',N'ms',N'Togolese',N'228',N'Togolese');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(924,N'407',N'fa',N'Togolese',N'228',N'TG');

INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(925,N'408',N'fr',N'Gabonese',N'241',N'GA');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(926,N'408',N'tr',N'Gabonese',N'241',N'GA');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(927,N'408',N'ms',N'Gabonese',N'241',N'Gabonese');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(928,N'408',N'fa',N'Gabonese',N'241',N'GA');

INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(929,N'409',N'fr',N'Gambian',N'220',N'GM');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(930,N'409',N'tr',N'Gambian',N'220',N'GM');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(931,N'409',N'ms',N'Gambian',N'220',N'Gambian');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(932,N'409',N'fa',N'Gambian',N'220',N'GM');

INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(933,N'410',N'fr',N'Comorosian',N'269',N'KM');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(934,N'410',N'tr',N'Comorosian',N'269',N'KM');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(935,N'410',N'ms',N'Comorosian',N'269',N'Comorosian');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(936,N'410',N'fa',N'Comorosian',N'269',N'KM');

INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(937,N'411',N'fr',N'South African',N'27',N'ZA');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(938,N'411',N'tr',N'South African',N'27',N'ZA');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(939,N'411',N'ms',N'South African',N'27',N'South African');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(940,N'411',N'fa',N'South African',N'27',N'ZA');

INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(941,N'412',N'fr',N'Namibian',N'264',N'NA');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(942,N'412',N'tr',N'Namibian',N'264',N'NA');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(943,N'412',N'ms',N'Namibian',N'264',N'Namibian');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(944,N'412',N'fa',N'Namibian',N'264',N'NA');

INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(945,N'413',N'fr',N'Beninese',N'229',N'BJ');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(946,N'413',N'tr',N'Beninese',N'229',N'BJ');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(947,N'413',N'ms',N'Beninese',N'229',N'Beninese');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(948,N'413',N'fa',N'Beninese',N'229',N'BJ');

INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(949,N'414',N'fr',N'Rwandan',N'250',N'RW');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(950,N'414',N'tr',N'Rwandan',N'250',N'RW');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(951,N'414',N'ms',N'Rwandan',N'250',N'Rwandan');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(952,N'414',N'fa',N'Rwandan',N'250',N'RW');

INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(953,N'415',N'fr',N'Zimbabwean',N'263',N'ZW');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(954,N'415',N'tr',N'Zimbabwean',N'263',N'ZW');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(955,N'415',N'ms',N'Zimbabwean',N'263',N'Zimbabwean');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(956,N'415',N'fa',N'Zimbabwean',N'263',N'ZW');

INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(957,N'416',N'fr',N'Zairean',N'243',N'CD');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(958,N'416',N'tr',N'Zairean',N'243',N'CD');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(959,N'416',N'ms',N'Zairean',N'243',N'Zairean');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(960,N'416',N'fa',N'Zairean',N'243',N'CD');

INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(961,N'417',N'fr',N'Zambian',N'260',N'ZM');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(962,N'417',N'tr',N'Zambian',N'260',N'ZM');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(963,N'417',N'ms',N'Zambian',N'260',N'Zambian');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(964,N'417',N'fa',N'Zambian',N'260',N'ZM');

INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(965,N'418',N'fr',N'Ivorian',N'225',N'CI');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(966,N'418',N'tr',N'Ivorian',N'225',N'CI');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(967,N'418',N'ms',N'Ivorian',N'225',N'Ivorian');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(968,N'418',N'fa',N'Ivorian',N'225',N'CI');

INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(969,N'419',N'fr',N'Senegalese',N'221',N'SN');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(970,N'419',N'tr',N'Senegalese',N'221',N'SN');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(971,N'419',N'ms',N'Senegalese',N'221',N'Senegalese');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(972,N'419',N'fa',N'Senegalese',N'221',N'SN');

INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(973,N'420',N'fr',N'Sierra Leonean',N'232',N'SL');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(974,N'420',N'tr',N'Sierra Leonean',N'232',N'SL');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(975,N'420',N'ms',N'Sierra Leonean',N'232',N'Sierra Leonean');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(976,N'420',N'fa',N'Sierra Leonean',N'232',N'SL');

INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(977,N'421',N'fr',N'Ghanian',N'233',N'GH');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(978,N'421',N'tr',N'Ghanian',N'233',N'GH');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(979,N'421',N'ms',N'Ghanian',N'233',N'Ghanian');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(980,N'421',N'fa',N'Ghanian',N'233',N'GH');

INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(981,N'422',N'fr',N'Guinean',N'224',N'GN');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(982,N'422',N'tr',N'Guinean',N'224',N'GN');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(983,N'422',N'ms',N'Guinean',N'224',N'Guinean');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(984,N'422',N'fa',N'Guinean',N'224',N'GN');

INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(985,N'423',N'fr',N'Guinean Bissau',N'245',N'GW');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(986,N'423',N'tr',N'Guinean Bissau',N'245',N'GW');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(987,N'423',N'ms',N'Guinean Bissau',N'245',N'Guinean Bissau');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(988,N'423',N'fa',N'Guinean Bissau',N'245',N'GW');

INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(989,N'424',N'fr',N'Burkinian',N'226',N'BF');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(990,N'424',N'tr',N'Burkinian',N'226',N'BF');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(991,N'424',N'ms',N'Burkinian',N'226',N'Burkinian');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(992,N'424',N'fa',N'Burkinian',N'226',N'BF');

INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(993,N'425',N'fr',N'Cameroonian',N'237',N'CM');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(994,N'425',N'tr',N'Cameroonian',N'237',N'CM');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(995,N'425',N'ms',N'Cameroonian',N'237',N'Cameroonian');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(996,N'425',N'fa',N'Cameroonian',N'237',N'CM');

INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(997,N'426',N'fr',N'Congolese',N'242',N'CG');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(998,N'426',N'tr',N'Congolese',N'242',N'CG');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(999,N'426',N'ms',N'Congolese',N'242',N'Congolese');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1000,N'426',N'fa',N'Congolese',N'242',N'CG');

INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1001,N'427',N'fr',N'Kenyan',N'254',N'KE');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1002,N'427',N'tr',N'Kenyan',N'254',N'KE');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1003,N'427',N'ms',N'Kenyan',N'254',N'Kenyan');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1004,N'427',N'fa',N'Kenyan',N'254',N'KE');

INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1005,N'428',N'fr',N'Lesothian',N'266',N'LS');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1006,N'428',N'tr',N'Lesothian',N'266',N'LS');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1007,N'428',N'ms',N'Lesothian',N'266',N'Lesothian');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1008,N'428',N'fa',N'Lesothian',N'266',N'LS');

INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1009,N'429',N'fr',N'Liberian',N'231',N'LR');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1010,N'429',N'tr',N'Liberian',N'231',N'LR');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1011,N'429',N'ms',N'Liberian',N'231',N'Liberian');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1012,N'429',N'fa',N'Liberian',N'231',N'LR');

INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1013,N'430',N'fr',N'Malian',N'223',N'ML');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1014,N'430',N'tr',N'Malian',N'223',N'ML');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1015,N'430',N'ms',N'Malian',N'223',N'Malian');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1016,N'430',N'fa',N'Malian',N'223',N'ML');

INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1017,N'432',N'fr',N'Malawian',N'265',N'MW');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1018,N'432',N'tr',N'Malawian',N'265',N'MW');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1019,N'432',N'ms',N'Malawian',N'265',N'Malawian');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1020,N'432',N'fa',N'Malawian',N'265',N'MW');

INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1021,N'433',N'fr',N'Mauritian',N'230',N'MU');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1022,N'433',N'tr',N'Mauritian',N'230',N'MU');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1023,N'433',N'ms',N'Mauritian',N'230',N'Mauritian');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1024,N'433',N'fa',N'Mauritian',N'230',N'MU');

INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1025,N'434',N'fr',N'Mozambican',N'258',N'MZ');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1026,N'434',N'tr',N'Mozambican',N'258',N'MZ');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1027,N'434',N'ms',N'Mozambican',N'258',N'Mozambican');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1028,N'434',N'fa',N'Mozambican',N'258',N'MZ');

INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1029,N'435',N'fr',N'Nigeria',N'234',N'NG');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1030,N'435',N'tr',N'Nigeria',N'234',N'NG');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1031,N'435',N'ms',N'Nigeria',N'234',N'Nigeria');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1032,N'435',N'fa',N'Nigeria',N'234',N'NG');

INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1033,N'436',N'fr',N'Nigerois',N'227',N'NE');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1034,N'436',N'tr',N'Nigerois',N'227',N'NE');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1035,N'436',N'ms',N'Nigerois',N'227',N'Nigerois');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1036,N'436',N'fa',N'Nigerois',N'227',N'NE');

INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1037,N'437',N'fr',N'Central African',N'236',N'CF');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1038,N'437',N'tr',N'Central African',N'236',N'CF');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1039,N'437',N'ms',N'Central African',N'236',N'Central African');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1040,N'437',N'fa',N'Central African',N'236',N'CF');

INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1041,N'438',N'fr',N'Angolan',N'244',N'AO');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1042,N'438',N'tr',N'Angolan',N'244',N'AO');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1043,N'438',N'ms',N'Angolan',N'244',N'Angolan');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1044,N'438',N'fa',N'Angolan',N'244',N'AO');

INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1045,N'439',N'fr',N'Cape Verdean',N'238',N'CV');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1046,N'439',N'tr',N'Cape Verdean',N'238',N'CV');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1047,N'439',N'ms',N'Cape Verdean',N'238',N'Cape Verdean');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1048,N'439',N'fa',N'Cape Verdean',N'238',N'CV');

INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1049,N'440',N'fr',N'Equatorial Guinea',N'240',N'GQ');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1050,N'440',N'tr',N'Equatorial Guinea',N'240',N'GQ');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1051,N'440',N'ms',N'Equatorial Guinea',N'240',N'Equatorial Guinea');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1052,N'440',N'fa',N'Equatorial Guinea',N'240',N'GQ');

INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1053,N'441',N'fr',N'Malagasyan',N'261',N'MG');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1054,N'441',N'tr',N'Malagasyan',N'261',N'MG');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1055,N'441',N'ms',N'Malagasyan',N'261',N'Malagasyan');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1056,N'441',N'fa',N'Malagasyan',N'261',N'MG');

INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1057,N'442',N'fr',N'São Tomé and Príncipe',N'239',N'ST');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1058,N'442',N'tr',N'São Tomé and Príncipe',N'239',N'ST');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1059,N'442',N'ms',N'São Tomé and Príncipe',N'239',N'São Tomé and Príncipe');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1060,N'442',N'fa',N'São Tomé and Príncipe',N'239',N'ST');

INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1061,N'443',N'fr',N'Seychelian',N'248',N'SC');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1062,N'443',N'tr',N'Seychelian',N'248',N'SC');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1063,N'443',N'ms',N'Seychelian',N'248',N'Seychelian');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1064,N'443',N'fa',N'Seychelian',N'248',N'SC');

INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1065,N'444',N'fr',N'Swazilander',N'268',N'SZ');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1066,N'444',N'tr',N'Swazilander',N'268',N'SZ');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1067,N'444',N'ms',N'Swazilander',N'268',N'Swazilander');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1068,N'444',N'fa',N'Swazilander',N'268',N'SZ');

INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1069,N'449',N'fr',N'Eritrean',N'291',N'ER');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1070,N'449',N'tr',N'Eritrean',N'291',N'ER');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1071,N'449',N'ms',N'Eritrean',N'291',N'Eritrean');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1072,N'449',N'fa',N'Eritrean',N'291',N'ER');

INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1073,N'453',N'fr',N'جمهورية جنوب السودان',N'211',N'SS');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1074,N'453',N'tr',N'جمهورية جنوب السودان',N'211',N'SS');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1075,N'453',N'ms',N'جمهورية جنوب السودان',N'211',N'جمهورية جنوب السودان');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1076,N'453',N'fa',N'جمهورية جنوب السودان',N'211',N'SS');

INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1077,N'501',N'fr',N'Spanish',N'34',N'ES');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1078,N'501',N'tr',N'Spanish',N'34',N'ES');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1079,N'501',N'ms',N'Spanish',N'34',N'Spanish');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1080,N'501',N'fa',N'Spanish',N'34',N'ES');

INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1081,N'502',N'fr',N'Albanian',N'355',N'AL');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1082,N'502',N'tr',N'Albanian',N'355',N'AL');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1083,N'502',N'ms',N'Albanian',N'355',N'Albanian');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1084,N'502',N'fa',N'Albanian',N'355',N'AL');

INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1085,N'503',N'fr',N'German',N'49',N'DE');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1086,N'503',N'tr',N'German',N'49',N'DE');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1087,N'503',N'ms',N'German',N'49',N'German');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1088,N'503',N'fa',N'German',N'49',N'DE');

INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1089,N'504',N'fr',N'Irish',N'353',N'IE');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1090,N'504',N'tr',N'Irish',N'353',N'IE');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1091,N'504',N'ms',N'Irish',N'353',N'Irish');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1092,N'504',N'fa',N'Irish',N'353',N'IE');

INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1093,N'505',N'fr',N'Italian',N'39',N'IT');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1094,N'505',N'tr',N'Italian',N'39',N'IT');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1095,N'505',N'ms',N'Italian',N'39',N'Italian');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1096,N'505',N'fa',N'Italian',N'39',N'IT');

INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1097,N'506',N'fr',N'British',N'44',N'GB');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1098,N'506',N'tr',N'British',N'44',N'GB');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1099,N'506',N'ms',N'British',N'44',N'British');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1100,N'506',N'fa',N'British',N'44',N'GB');

INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1101,N'507',N'fr',N'Portuguese',N'351',N'PT');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1102,N'507',N'tr',N'Portuguese',N'351',N'PT');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1103,N'507',N'ms',N'Portuguese',N'351',N'Portuguese');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1104,N'507',N'fa',N'Portuguese',N'351',N'PT');

INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1105,N'508',N'fr',N'Bulgarian',N'359',N'BG');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1106,N'508',N'tr',N'Bulgarian',N'359',N'BG');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1107,N'508',N'ms',N'Bulgarian',N'359',N'Bulgarian');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1108,N'508',N'fa',N'Bulgarian',N'359',N'BG');

INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1109,N'509',N'fr',N'Belgian',N'32',N'BE');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1110,N'509',N'tr',N'Belgian',N'32',N'BE');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1111,N'509',N'ms',N'Belgian',N'32',N'Belgian');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1112,N'509',N'fa',N'Belgian',N'32',N'BE');

INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1113,N'510',N'fr',N'Polish',N'48',N'PL');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1114,N'510',N'tr',N'Polish',N'48',N'PL');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1115,N'510',N'ms',N'Polish',N'48',N'Polish');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1116,N'510',N'fa',N'Polish',N'48',N'PL');

INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1117,N'512',N'fr',N'Danish',N'45',N'DK');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1118,N'512',N'tr',N'Danish',N'45',N'DK');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1119,N'512',N'ms',N'Danish',N'45',N'Danish');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1120,N'512',N'fa',N'Danish',N'45',N'DK');

INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1121,N'513',N'fr',N'Romanian',N'40',N'RO');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1122,N'513',N'tr',N'Romanian',N'40',N'RO');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1123,N'513',N'ms',N'Romanian',N'40',N'Romanian');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1124,N'513',N'fa',N'Romanian',N'40',N'RO');

INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1125,N'514',N'fr',N'Swedish',N'46',N'SE');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1126,N'514',N'tr',N'Swedish',N'46',N'SE');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1127,N'514',N'ms',N'Swedish',N'46',N'Swedish');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1128,N'514',N'fa',N'Swedish',N'46',N'SE');

INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1129,N'515',N'fr',N'Swiss',N'41',N'CH');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1130,N'515',N'tr',N'Swiss',N'41',N'CH');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1131,N'515',N'ms',N'Swiss',N'41',N'Swiss');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1132,N'515',N'fa',N'Swiss',N'41',N'CH');

INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1133,N'516',N'fr',N'Franch',N'33',N'FR');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1134,N'516',N'tr',N'Franch',N'33',N'FR');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1135,N'516',N'ms',N'Franch',N'33',N'Franch');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1136,N'516',N'fa',N'Franch',N'33',N'FR');

INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1137,N'517',N'fr',N'Finlander',N'358',N'AX');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1138,N'517',N'tr',N'Finlander',N'358',N'AX');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1139,N'517',N'ms',N'Finlander',N'358',N'Finlander');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1140,N'517',N'fa',N'Finlander',N'358',N'AX');

INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1141,N'518',N'fr',N'Serbia',N'381',N'RS');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1142,N'518',N'tr',N'Serbia',N'381',N'RS');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1143,N'518',N'ms',N'Serbia',N'381',N'Serbia');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1144,N'518',N'fa',N'Serbia',N'381',N'RS');

INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1145,N'519',N'fr',N'Dutch',N'31',N'NL');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1146,N'519',N'tr',N'Dutch',N'31',N'NL');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1147,N'519',N'ms',N'Dutch',N'31',N'Dutch');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1148,N'519',N'fa',N'Dutch',N'31',N'NL');

INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1149,N'521',N'fr',N'GreeK',N'30',N'GR');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1150,N'521',N'tr',N'GreeK',N'30',N'GR');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1151,N'521',N'ms',N'GreeK',N'30',N'GreeK');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1152,N'521',N'fa',N'GreeK',N'30',N'GR');

INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1153,N'522',N'fr',N'Andorian',N'376',N'AD');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1154,N'522',N'tr',N'Andorian',N'376',N'AD');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1155,N'522',N'ms',N'Andorian',N'376',N'Andorian');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1156,N'522',N'fa',N'Andorian',N'376',N'AD');

INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1157,N'523',N'fr',N'Austrian',N'43',N'AT');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1158,N'523',N'tr',N'Austrian',N'43',N'AT');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1159,N'523',N'ms',N'Austrian',N'43',N'Austrian');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1160,N'523',N'fa',N'Austrian',N'43',N'AT');

INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1161,N'524',N'fr',N'Montenegro',N'382',N'ME');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1162,N'524',N'tr',N'Montenegro',N'382',N'ME');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1163,N'524',N'ms',N'Montenegro',N'382',N'Montenegro');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1164,N'524',N'fa',N'Montenegro',N'382',N'ME');

INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1165,N'525',N'fr',N'Hungarian',N'36',N'HU');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1166,N'525',N'tr',N'Hungarian',N'36',N'HU');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1167,N'525',N'ms',N'Hungarian',N'36',N'Hungarian');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1168,N'525',N'fa',N'Hungarian',N'36',N'HU');

INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1169,N'526',N'fr',N'Icelander',N'354',N'IS');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1170,N'526',N'tr',N'Icelander',N'354',N'IS');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1171,N'526',N'ms',N'Icelander',N'354',N'Icelander');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1172,N'526',N'fa',N'Icelander',N'354',N'IS');

INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1173,N'527',N'fr',N'Liechtenstein',N'423',N'LI');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1174,N'527',N'tr',N'Liechtenstein',N'423',N'LI');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1175,N'527',N'ms',N'Liechtenstein',N'423',N'Liechtenstein');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1176,N'527',N'fa',N'Liechtenstein',N'423',N'LI');

INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1177,N'528',N'fr',N'Luxembourgian',N'352',N'LU');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1178,N'528',N'tr',N'Luxembourgian',N'352',N'LU');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1179,N'528',N'ms',N'Luxembourgian',N'352',N'Luxembourgian');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1180,N'528',N'fa',N'Luxembourgian',N'352',N'LU');

INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1181,N'529',N'fr',N'Maltese',N'356',N'MT');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1182,N'529',N'tr',N'Maltese',N'356',N'MT');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1183,N'529',N'ms',N'Maltese',N'356',N'Maltese');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1184,N'529',N'fa',N'Maltese',N'356',N'MT');

INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1185,N'530',N'fr',N'Monacan',N'377',N'MC');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1186,N'530',N'tr',N'Monacan',N'377',N'MC');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1187,N'530',N'ms',N'Monacan',N'377',N'Monacan');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1188,N'530',N'fa',N'Monacan',N'377',N'MC');

INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1189,N'531',N'fr',N'Norwegian',N'47',N'SJ');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1190,N'531',N'tr',N'Norwegian',N'47',N'SJ');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1191,N'531',N'ms',N'Norwegian',N'47',N'Norwegian');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1192,N'531',N'fa',N'Norwegian',N'47',N'SJ');

INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1193,N'532',N'fr',N'San Marino',N'378',N'SM');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1194,N'532',N'tr',N'San Marino',N'378',N'SM');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1195,N'532',N'ms',N'San Marino',N'378',N'San Marino');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1196,N'532',N'fa',N'San Marino',N'378',N'SM');

INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1197,N'533',N'fr',N'Vatican',N'1039',N'VA');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1198,N'533',N'tr',N'Vatican',N'1039',N'VA');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1199,N'533',N'ms',N'Vatican',N'1039',N'Vatican');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1200,N'533',N'fa',N'Vatican',N'1039',N'VA');

INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1201,N'534',N'fr',N'Gibraltarian',N'350',N'GI');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1202,N'534',N'tr',N'Gibraltarian',N'350',N'GI');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1203,N'534',N'ms',N'Gibraltarian',N'350',N'Gibraltarian');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1204,N'534',N'fa',N'Gibraltarian',N'350',N'GI');

INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1205,N'536',N'fr',N'Ucranian',N'380',N'UA');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1206,N'536',N'tr',N'Ucranian',N'380',N'UA');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1207,N'536',N'ms',N'Ucranian',N'380',N'Ucranian');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1208,N'536',N'fa',N'Ucranian',N'380',N'UA');

INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1209,N'537',N'fr',N'Belarussian',N'375',N'BY');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1210,N'537',N'tr',N'Belarussian',N'375',N'BY');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1211,N'537',N'ms',N'Belarussian',N'375',N'Belarussian');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1212,N'537',N'fa',N'Belarussian',N'375',N'BY');

INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1213,N'539',N'fr',N'Armenian',N'374',N'AM');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1214,N'539',N'tr',N'Armenian',N'374',N'AM');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1215,N'539',N'ms',N'Armenian',N'374',N'Armenian');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1216,N'539',N'fa',N'Armenian',N'374',N'AM');

INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1217,N'540',N'fr',N'Moldovian',N'373',N'MD');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1218,N'540',N'tr',N'Moldovian',N'373',N'MD');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1219,N'540',N'ms',N'Moldovian',N'373',N'Moldovian');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1220,N'540',N'fa',N'Moldovian',N'373',N'MD');

INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1221,N'541',N'fr',N'Georgian',N'995',N'GE');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1222,N'541',N'tr',N'Georgian',N'995',N'GE');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1223,N'541',N'ms',N'Georgian',N'995',N'Georgian');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1224,N'541',N'fa',N'Georgian',N'995',N'GE');

INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1225,N'542',N'fr',N'Lithuanian',N'370',N'LT');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1226,N'542',N'tr',N'Lithuanian',N'370',N'LT');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1227,N'542',N'ms',N'Lithuanian',N'370',N'Lithuanian');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1228,N'542',N'fa',N'Lithuanian',N'370',N'LT');

INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1229,N'543',N'fr',N'Estonian',N'372',N'EE');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1230,N'543',N'tr',N'Estonian',N'372',N'EE');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1231,N'543',N'ms',N'Estonian',N'372',N'Estonian');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1232,N'543',N'fa',N'Estonian',N'372',N'EE');

INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1233,N'544',N'fr',N'Latvian',N'371',N'LV');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1234,N'544',N'tr',N'Latvian',N'371',N'LV');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1235,N'544',N'ms',N'Latvian',N'371',N'Latvian');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1236,N'544',N'fa',N'Latvian',N'371',N'LV');

INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1237,N'545',N'fr',N'Bosnian',N'387',N'BA');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1238,N'545',N'tr',N'Bosnian',N'387',N'BA');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1239,N'545',N'ms',N'Bosnian',N'387',N'Bosnian');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1240,N'545',N'fa',N'Bosnian',N'387',N'BA');

INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1241,N'546',N'fr',N'Croatian',N'385',N'HR');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1242,N'546',N'tr',N'Croatian',N'385',N'HR');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1243,N'546',N'ms',N'Croatian',N'385',N'Croatian');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1244,N'546',N'fa',N'Croatian',N'385',N'HR');

INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1245,N'547',N'fr',N'Slovenian',N'386',N'SI');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1246,N'547',N'tr',N'Slovenian',N'386',N'SI');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1247,N'547',N'ms',N'Slovenian',N'386',N'Slovenian');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1248,N'547',N'fa',N'Slovenian',N'386',N'SI');

INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1249,N'549',N'fr',N'Macedonian',N'389',N'MK');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1250,N'549',N'tr',N'Macedonian',N'389',N'MK');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1251,N'549',N'ms',N'Macedonian',N'389',N'Macedonian');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1252,N'549',N'fa',N'Macedonian',N'389',N'MK');

INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1253,N'552',N'fr',N'Czechish',N'420',N'CZ');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1254,N'552',N'tr',N'Czechish',N'420',N'CZ');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1255,N'552',N'ms',N'Czechish',N'420',N'Czechish');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1256,N'552',N'fa',N'Czechish',N'420',N'CZ');

INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1257,N'553',N'fr',N'Slovakian',N'421',N'SK');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1258,N'553',N'tr',N'Slovakian',N'421',N'SK');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1259,N'553',N'ms',N'Slovakian',N'421',N'Slovakian');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1260,N'553',N'fa',N'Slovakian',N'421',N'SK');

INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1261,N'554',N'fr',N'Faroe Islands',N'298',N'FO');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1262,N'554',N'tr',N'Faroe Islands',N'298',N'FO');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1263,N'554',N'ms',N'Faroe Islands',N'298',N'Faroe Islands');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1264,N'554',N'fa',N'Faroe Islands',N'298',N'FO');

INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1265,N'601',N'fr',N'American',N'1',N'US');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1266,N'601',N'tr',N'American',N'1',N'US');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1267,N'601',N'ms',N'American',N'1',N'American');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1268,N'601',N'fa',N'American',N'1',N'US');

INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1269,N'602',N'fr',N'Argentinian',N'54',N'AR');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1270,N'602',N'tr',N'Argentinian',N'54',N'AR');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1271,N'602',N'ms',N'Argentinian',N'54',N'Argentinian');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1272,N'602',N'fa',N'Argentinian',N'54',N'AR');

INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1273,N'603',N'fr',N'Barbadian',N'1246',N'BB');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1274,N'603',N'tr',N'Barbadian',N'1246',N'BB');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1275,N'603',N'ms',N'Barbadian',N'1246',N'Barbadian');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1276,N'603',N'fa',N'Barbadian',N'1246',N'BB');

INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1277,N'604',N'fr',N'Brazilian',N'55',N'BR');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1278,N'604',N'tr',N'Brazilian',N'55',N'BR');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1279,N'604',N'ms',N'Brazilian',N'55',N'Brazilian');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1280,N'604',N'fa',N'Brazilian',N'55',N'BR');

INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1281,N'605',N'fr',N'Panamanian',N'507',N'PA');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1282,N'605',N'tr',N'Panamanian',N'507',N'PA');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1283,N'605',N'ms',N'Panamanian',N'507',N'Panamanian');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1284,N'605',N'fa',N'Panamanian',N'507',N'PA');

INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1285,N'606',N'fr',N'Trinindad',N'868',N'TT');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1286,N'606',N'tr',N'Trinindad',N'868',N'TT');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1287,N'606',N'ms',N'Trinindad',N'868',N'Trinindad');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1288,N'606',N'fa',N'Trinindad',N'868',N'TT');

INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1289,N'607',N'fr',N'Jamaican',N'876',N'JM');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1290,N'607',N'tr',N'Jamaican',N'876',N'JM');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1291,N'607',N'ms',N'Jamaican',N'876',N'Jamaican');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1292,N'607',N'fa',N'Jamaican',N'876',N'JM');

INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1293,N'608',N'fr',N'Guyanese',N'592',N'GY');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1294,N'608',N'tr',N'Guyanese',N'592',N'GY');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1295,N'608',N'ms',N'Guyanese',N'592',N'Guyanese');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1296,N'608',N'fa',N'Guyanese',N'592',N'GY');

INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1297,N'609',N'fr',N'Venezuelan',N'58',N'VE');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1298,N'609',N'tr',N'Venezuelan',N'58',N'VE');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1299,N'609',N'ms',N'Venezuelan',N'58',N'Venezuelan');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1300,N'609',N'fa',N'Venezuelan',N'58',N'VE');

INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1301,N'610',N'fr',N'Canadian',N'1',N'CA');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1302,N'610',N'tr',N'Canadian',N'1',N'CA');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1303,N'610',N'ms',N'Canadian',N'1',N'Canadian');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1304,N'610',N'fa',N'Canadian',N'1',N'CA');

INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1305,N'611',N'fr',N'Colombian',N'57',N'CO');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1306,N'611',N'tr',N'Colombian',N'57',N'CO');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1307,N'611',N'ms',N'Colombian',N'57',N'Colombian');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1308,N'611',N'fa',N'Colombian',N'57',N'CO');

INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1309,N'612',N'fr',N'Bahamian',N'1242',N'BS');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1310,N'612',N'tr',N'Bahamian',N'1242',N'BS');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1311,N'612',N'ms',N'Bahamian',N'1242',N'Bahamian');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1312,N'612',N'fa',N'Bahamian',N'1242',N'BS');

INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1313,N'613',N'fr',N'Costa Rican',N'506',N'CR');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1314,N'613',N'tr',N'Costa Rican',N'506',N'CR');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1315,N'613',N'ms',N'Costa Rican',N'506',N'Costa Rican');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1316,N'613',N'fa',N'Costa Rican',N'506',N'CR');

INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1317,N'614',N'fr',N'Cuban',N'53',N'CU');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1318,N'614',N'tr',N'Cuban',N'53',N'CU');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1319,N'614',N'ms',N'Cuban',N'53',N'Cuban');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1320,N'614',N'fa',N'Cuban',N'53',N'CU');

INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1321,N'615',N'fr',N'Dominican',N'767',N'DM');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1322,N'615',N'tr',N'Dominican',N'767',N'DM');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1323,N'615',N'ms',N'Dominican',N'767',N'Dominican');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1324,N'615',N'fa',N'Dominican',N'767',N'DM');

INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1325,N'616',N'fr',N'Republic Dominica',N'809',N'DO');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1326,N'616',N'tr',N'Republic Dominica',N'809',N'DO');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1327,N'616',N'ms',N'Republic Dominica',N'809',N'Republic Dominica');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1328,N'616',N'fa',N'Republic Dominica',N'809',N'DO');

INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1329,N'617',N'fr',N'Salvadorian',N'503',N'SV');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1330,N'617',N'tr',N'Salvadorian',N'503',N'SV');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1331,N'617',N'ms',N'Salvadorian',N'503',N'Salvadorian');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1332,N'617',N'fa',N'Salvadorian',N'503',N'SV');

INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1333,N'618',N'fr',N'Grenadian',N'473',N'GD');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1334,N'618',N'tr',N'Grenadian',N'473',N'GD');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1335,N'618',N'ms',N'Grenadian',N'473',N'Grenadian');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1336,N'618',N'fa',N'Grenadian',N'473',N'GD');

INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1337,N'619',N'fr',N'Guatemalan',N'502',N'GT');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1338,N'619',N'tr',N'Guatemalan',N'502',N'GT');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1339,N'619',N'ms',N'Guatemalan',N'502',N'Guatemalan');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1340,N'619',N'fa',N'Guatemalan',N'502',N'GT');

INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1341,N'620',N'fr',N'Haitian',N'509',N'HT');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1342,N'620',N'tr',N'Haitian',N'509',N'HT');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1343,N'620',N'ms',N'Haitian',N'509',N'Haitian');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1344,N'620',N'fa',N'Haitian',N'509',N'HT');

INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1345,N'621',N'fr',N'Honduranian',N'504',N'HN');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1346,N'621',N'tr',N'Honduranian',N'504',N'HN');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1347,N'621',N'ms',N'Honduranian',N'504',N'Honduranian');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1348,N'621',N'fa',N'Honduranian',N'504',N'HN');

INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1349,N'622',N'fr',N'Mexican',N'52',N'MX');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1350,N'622',N'tr',N'Mexican',N'52',N'MX');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1351,N'622',N'ms',N'Mexican',N'52',N'Mexican');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1352,N'622',N'fa',N'Mexican',N'52',N'MX');

INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1353,N'623',N'fr',N'Nicaraguan',N'505',N'NI');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1354,N'623',N'tr',N'Nicaraguan',N'505',N'NI');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1355,N'623',N'ms',N'Nicaraguan',N'505',N'Nicaraguan');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1356,N'623',N'fa',N'Nicaraguan',N'505',N'NI');

INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1357,N'624',N'fr',N'Saint Lucia',N'758',N'LC');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1358,N'624',N'tr',N'Saint Lucia',N'758',N'LC');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1359,N'624',N'ms',N'Saint Lucia',N'758',N'Saint Lucia');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1360,N'624',N'fa',N'Saint Lucia',N'758',N'LC');

INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1361,N'625',N'fr',N'Saint Vincent',N'784',N'VC');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1362,N'625',N'tr',N'Saint Vincent',N'784',N'VC');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1363,N'625',N'ms',N'Saint Vincent',N'784',N'Saint Vincent');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1364,N'625',N'fa',N'Saint Vincent',N'784',N'VC');

INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1365,N'626',N'fr',N'Bolivian',N'591',N'BO');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1366,N'626',N'tr',N'Bolivian',N'591',N'BO');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1367,N'626',N'ms',N'Bolivian',N'591',N'Bolivian');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1368,N'626',N'fa',N'Bolivian',N'591',N'BO');

INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1369,N'627',N'fr',N'Chilean',N'56',N'CL');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1370,N'627',N'tr',N'Chilean',N'56',N'CL');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1371,N'627',N'ms',N'Chilean',N'56',N'Chilean');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1372,N'627',N'fa',N'Chilean',N'56',N'CL');

INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1373,N'628',N'fr',N'Ecuadorean',N'593',N'EC');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1374,N'628',N'tr',N'Ecuadorean',N'593',N'EC');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1375,N'628',N'ms',N'Ecuadorean',N'593',N'Ecuadorean');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1376,N'628',N'fa',N'Ecuadorean',N'593',N'EC');

INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1377,N'629',N'fr',N'Paraguayan',N'595',N'PY');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1378,N'629',N'tr',N'Paraguayan',N'595',N'PY');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1379,N'629',N'ms',N'Paraguayan',N'595',N'Paraguayan');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1380,N'629',N'fa',N'Paraguayan',N'595',N'PY');

INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1381,N'630',N'fr',N'Peruvian',N'51',N'PE');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1382,N'630',N'tr',N'Peruvian',N'51',N'PE');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1383,N'630',N'ms',N'Peruvian',N'51',N'Peruvian');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1384,N'630',N'fa',N'Peruvian',N'51',N'PE');

INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1385,N'701',N'fr',N'Australian',N'61',N'CC');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1386,N'701',N'tr',N'Australian',N'61',N'CC');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1387,N'701',N'ms',N'Australian',N'61',N'Australian');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1388,N'701',N'fa',N'Australian',N'61',N'CC');

INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1389,N'702',N'fr',N'N.zelander',N'64',N'NZ');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1390,N'702',N'tr',N'N.zelander',N'64',N'NZ');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1391,N'702',N'ms',N'N.zelander',N'64',N'N.zelander');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1392,N'702',N'fa',N'N.zelander',N'64',N'NZ');

INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1393,N'112',N'fr',N'يمني جنوبي ',N'967',N'YE');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1394,N'112',N'tr',N'يمني جنوبي ',N'967',N'YE');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1395,N'112',N'ms',N'يمني جنوبي ',N'967',N'يمني جنوبي ');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1396,N'112',N'fa',N'يمني جنوبي ',N'967',N'YE');

INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1397,N'114',N'fr',N'يمني جنوبيالسلاطين',N'967',N'YE');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1398,N'114',N'tr',N'يمني جنوبيالسلاطين',N'967',N'YE');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1399,N'114',N'ms',N'يمني جنوبيالسلاطين',N'967',N'يمني جنوبيالسلاطين');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1400,N'114',N'fa',N'يمني جنوبيالسلاطين',N'967',N'YE');

INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1401,N'115',N'fr',N'بني حارث',N'967',N'YE');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1402,N'115',N'tr',N'بني حارث',N'967',N'YE');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1403,N'115',N'ms',N'بني حارث',N'967',N'بني حارث');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1404,N'115',N'fa',N'بني حارث',N'967',N'YE');

INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1405,N'118',N'fr',N'Bahrain Res.',N'967',N'BH');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1406,N'118',N'tr',N'Bahrain Res.',N'967',N'BH');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1407,N'118',N'ms',N'Bahrain Res.',N'967',N'Bahrain Res.');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1408,N'118',N'fa',N'Bahrain Res.',N'967',N'BH');

INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1409,N'314',N'fr',N'Taiwanese',N'886',N'TWN');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1410,N'314',N'tr',N'Taiwanese',N'886',N'TWN');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1411,N'314',N'ms',N'Taiwanese',N'886',N'Taiwanese');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1412,N'314',N'fa',N'Taiwanese',N'886',N'TWN');

INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1413,N'333',N'fr',N'Blushistani',N'81',N'PK');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1414,N'333',N'tr',N'Blushistani',N'81',N'PK');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1415,N'333',N'ms',N'Blushistani',N'81',N'Blushistani');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1416,N'333',N'fa',N'Blushistani',N'81',N'PK');

INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1417,N'334',N'fr',N'Bukharan',N'',N'');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1418,N'334',N'tr',N'Bukharan',N'',N'');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1419,N'334',N'ms',N'Bukharan',N'',N'Bukharan');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1420,N'334',N'fa',N'Bukharan',N'',N'');

INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1421,N'335',N'fr',N'القبائل النازحة',N'',N'');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1422,N'335',N'tr',N'القبائل النازحة',N'',N'');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1423,N'335',N'ms',N'القبائل النازحة',N'',N'القبائل النازحة');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1424,N'335',N'fa',N'القبائل النازحة',N'',N'');

INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1425,N'341',N'fr',N'Sukatra',N'',N'');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1426,N'341',N'tr',N'Sukatra',N'',N'');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1427,N'341',N'ms',N'Sukatra',N'',N'Sukatra');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1428,N'341',N'fa',N'Sukatra',N'',N'');

INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1429,N'342',N'fr',N'Mahara',N'',N'');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1430,N'342',N'tr',N'Mahara',N'',N'');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1431,N'342',N'ms',N'Mahara',N'',N'Mahara');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1432,N'342',N'fa',N'Mahara',N'',N'');

INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1433,N'346',N'fr',N'Ingush',N'',N'');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1434,N'346',N'tr',N'Ingush',N'',N'');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1435,N'346',N'ms',N'Ingush',N'',N'Ingush');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1436,N'346',N'fa',N'Ingush',N'',N'');

INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1437,N'446',N'fr',N'Reunese',N'262',N'REU');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1438,N'446',N'tr',N'Reunese',N'262',N'REU');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1439,N'446',N'ms',N'Reunese',N'262',N'Reunese');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1440,N'446',N'fa',N'Reunese',N'262',N'REU');

INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1441,N'447',N'fr',N'ترانسكي',N'',N'');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1442,N'447',N'tr',N'ترانسكي',N'',N'');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1443,N'447',N'ms',N'ترانسكي',N'',N'ترانسكي');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1444,N'447',N'fa',N'ترانسكي',N'',N'');

INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1445,N'448',N'fr',N'فيندي',N'',N'');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1446,N'448',N'tr',N'فيندي',N'',N'');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1447,N'448',N'ms',N'فيندي',N'',N'فيندي');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1448,N'448',N'fa',N'فيندي',N'',N'');

INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1449,N'511',N'fr',N'Czechlovakian',N'42',N'CSK');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1450,N'511',N'tr',N'Czechlovakian',N'42',N'CSK');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1451,N'511',N'ms',N'Czechlovakian',N'42',N'Czechlovakian');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1452,N'511',N'fa',N'Czechlovakian',N'42',N'CSK');

INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1453,N'520',N'fr',N'Yugoslavian',N'38',N'SER');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1454,N'520',N'tr',N'Yugoslavian',N'38',N'SER');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1455,N'520',N'ms',N'Yugoslavian',N'38',N'Yugoslavian');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1456,N'520',N'fa',N'Yugoslavian',N'38',N'SER');

INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1457,N'631',N'fr',N'Suriname',N'597',N'SUR');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1458,N'631',N'tr',N'Suriname',N'597',N'SUR');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1459,N'631',N'ms',N'Suriname',N'597',N'Suriname');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1460,N'631',N'fa',N'Suriname',N'597',N'SUR');

INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1461,N'632',N'fr',N'Uruguayan',N'598',N'URY');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1462,N'632',N'tr',N'Uruguayan',N'598',N'URY');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1463,N'632',N'ms',N'Uruguayan',N'598',N'Uruguayan');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1464,N'632',N'fa',N'Uruguayan',N'598',N'URY');

INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1465,N'633',N'fr',N'س بييري وميكويلن',N'',N'');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1466,N'633',N'tr',N'س بييري وميكويلن',N'',N'');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1467,N'633',N'ms',N'س بييري وميكويلن',N'',N'س بييري وميكويلن');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1468,N'633',N'fa',N'س بييري وميكويلن',N'',N'');

INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1469,N'634',N'fr',N'GreenLander',N'299',N'GRL');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1470,N'634',N'tr',N'GreenLander',N'299',N'GRL');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1471,N'634',N'ms',N'GreenLander',N'299',N'GreenLander');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1472,N'634',N'fa',N'GreenLander',N'299',N'GRL');

INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1473,N'635',N'fr',N'Belizian',N'501',N'BLZ');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1474,N'635',N'tr',N'Belizian',N'501',N'BLZ');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1475,N'635',N'ms',N'Belizian',N'501',N'Belizian');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1476,N'635',N'fa',N'Belizian',N'501',N'BLZ');

INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1477,N'636',N'fr',N'بيرمودي',N'',N'');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1478,N'636',N'tr',N'بيرمودي',N'',N'');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1479,N'636',N'ms',N'بيرمودي',N'',N'بيرمودي');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1480,N'636',N'fa',N'بيرمودي',N'',N'');

INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1481,N'637',N'fr',N'ج الترك والقوقاز ',N'',N'');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1482,N'637',N'tr',N'ج الترك والقوقاز ',N'',N'');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1483,N'637',N'ms',N'ج الترك والقوقاز ',N'',N'ج الترك والقوقاز ');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1484,N'637',N'fa',N'ج الترك والقوقاز ',N'',N'');

INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1485,N'638',N'fr',N'سان كريستوفرنيفز',N'',N'');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1486,N'638',N'tr',N'سان كريستوفرنيفز',N'',N'');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1487,N'638',N'ms',N'سان كريستوفرنيفز',N'',N'سان كريستوفرنيفز');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1488,N'638',N'fa',N'سان كريستوفرنيفز',N'',N'');

INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1489,N'641',N'fr',N'Virgin Isaland British',N'1',N'VGB');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1490,N'641',N'tr',N'Virgin Isaland British',N'1',N'VGB');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1491,N'641',N'ms',N'Virgin Isaland British',N'1',N'Virgin Isaland British');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1492,N'641',N'fa',N'Virgin Isaland British',N'1',N'VGB');

INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1493,N'642',N'fr',N'Caymanian',N'1',N'CYM');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1494,N'642',N'tr',N'Caymanian',N'1',N'CYM');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1495,N'642',N'ms',N'Caymanian',N'1',N'Caymanian');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1496,N'642',N'fa',N'Caymanian',N'1',N'CYM');

INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1497,N'643',N'fr',N'مونت سيرات',N'1',N'MSR');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1498,N'643',N'tr',N'مونت سيرات',N'1',N'MSR');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1499,N'643',N'ms',N'مونت سيرات',N'1',N'مونت سيرات');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1500,N'643',N'fa',N'مونت سيرات',N'1',N'MSR');

INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1501,N'644',N'fr',N'Guadeloupean',N'590',N'GLP');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1502,N'644',N'tr',N'Guadeloupean',N'590',N'GLP');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1503,N'644',N'ms',N'Guadeloupean',N'590',N'Guadeloupean');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1504,N'644',N'fa',N'Guadeloupean',N'590',N'GLP');

INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1505,N'645',N'fr',N'مارتينيكوي',N'596',N'MTQ');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1506,N'645',N'tr',N'مارتينيكوي',N'596',N'MTQ');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1507,N'645',N'ms',N'مارتينيكوي',N'596',N'مارتينيكوي');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1508,N'645',N'fa',N'مارتينيكوي',N'596',N'MTQ');

INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1509,N'646',N'fr',N'عروبي',N'',N'');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1510,N'646',N'tr',N'عروبي',N'',N'');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1511,N'646',N'ms',N'عروبي',N'',N'عروبي');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1512,N'646',N'fa',N'عروبي',N'',N'');

INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1513,N'647',N'fr',N'بونيري',N'',N'');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1514,N'647',N'tr',N'بونيري',N'',N'');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1515,N'647',N'ms',N'بونيري',N'',N'بونيري');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1516,N'647',N'fa',N'بونيري',N'',N'');

INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1517,N'648',N'fr',N'كيوراكو',N'',N'');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1518,N'648',N'tr',N'كيوراكو',N'',N'');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1519,N'648',N'ms',N'كيوراكو',N'',N'كيوراكو');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1520,N'648',N'fa',N'كيوراكو',N'',N'');

INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1521,N'650',N'fr',N'سابا',N'60-8',N'SBH');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1522,N'650',N'tr',N'سابا',N'60-8',N'SBH');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1523,N'650',N'ms',N'سابا',N'60-8',N'سابا');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1524,N'650',N'fa',N'سابا',N'60-8',N'SBH');

INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1525,N'652',N'fr',N'Puerto Rican',N'1',N'PRT');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1526,N'652',N'tr',N'Puerto Rican',N'1',N'PRT');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1527,N'652',N'ms',N'Puerto Rican',N'1',N'Puerto Rican');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1528,N'652',N'fa',N'Puerto Rican',N'1',N'PRT');

INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1529,N'653',N'fr',N'Virgin Isaland US',N'1',N'VIR');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1530,N'653',N'tr',N'Virgin Isaland US',N'1',N'VIR');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1531,N'653',N'ms',N'Virgin Isaland US',N'1',N'Virgin Isaland US');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1532,N'653',N'fa',N'Virgin Isaland US',N'1',N'VIR');

INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1533,N'654',N'fr',N'Falklander',N'500',N'FLK');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1534,N'654',N'tr',N'Falklander',N'500',N'FLK');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1535,N'654',N'ms',N'Falklander',N'500',N'Falklander');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1536,N'654',N'fa',N'Falklander',N'500',N'FLK');

INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1537,N'655',N'fr',N'Guinean',N'',N'');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1538,N'655',N'tr',N'Guinean',N'',N'');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1539,N'655',N'ms',N'Guinean',N'',N'Guinean');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1540,N'655',N'fa',N'Guinean',N'',N'');

INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1541,N'656',N'fr',N'United Nations',N'',N'');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1542,N'656',N'tr',N'United Nations',N'',N'');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1543,N'656',N'ms',N'United Nations',N'',N'United Nations');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1544,N'656',N'fa',N'United Nations',N'',N'');

INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1545,N'703',N'fr',N'بابوا نيوغينا',N'',N'');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1546,N'703',N'tr',N'بابوا نيوغينا',N'',N'');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1547,N'703',N'ms',N'بابوا نيوغينا',N'',N'بابوا نيوغينا');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1548,N'703',N'fa',N'بابوا نيوغينا',N'',N'');

INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1549,N'801',N'fr',N'Fijian',N'',N'');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1550,N'801',N'tr',N'Fijian',N'',N'');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1551,N'801',N'ms',N'Fijian',N'',N'Fijian');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1552,N'801',N'fa',N'Fijian',N'',N'');

INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1553,N'803',N'fr',N'Nauru',N'',N'NRU');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1554,N'803',N'tr',N'Nauru',N'',N'NRU');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1555,N'803',N'ms',N'Nauru',N'',N'Nauru');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1556,N'803',N'fa',N'Nauru',N'',N'NRU');

INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1557,N'804',N'fr',N'Solomon Island',N'',N'SLB');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1558,N'804',N'tr',N'Solomon Island',N'',N'SLB');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1559,N'804',N'ms',N'Solomon Island',N'',N'Solomon Island');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1560,N'804',N'fa',N'Solomon Island',N'',N'SLB');

INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1561,N'805',N'fr',N'Tongan',N'',N'TON');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1562,N'805',N'tr',N'Tongan',N'',N'TON');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1563,N'805',N'ms',N'Tongan',N'',N'Tongan');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1564,N'805',N'fa',N'Tongan',N'',N'TON');

INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1565,N'806',N'fr',N'Tuvalu',N'',N'TUV');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1566,N'806',N'tr',N'Tuvalu',N'',N'TUV');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1567,N'806',N'ms',N'Tuvalu',N'',N'Tuvalu');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1568,N'806',N'fa',N'Tuvalu',N'',N'TUV');

INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1569,N'807',N'fr',N'فانيوتو',N'',N'');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1570,N'807',N'tr',N'فانيوتو',N'',N'');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1571,N'807',N'ms',N'فانيوتو',N'',N'فانيوتو');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1572,N'807',N'fa',N'فانيوتو',N'',N'');

INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1573,N'808',N'fr',N'Samoan W.',N'',N'');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1574,N'808',N'tr',N'Samoan W.',N'',N'');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1575,N'808',N'ms',N'Samoan W.',N'',N'Samoan W.');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1576,N'808',N'fa',N'Samoan W.',N'',N'');

INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1577,N'810',N'fr',N'جوام',N'',N'');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1578,N'810',N'tr',N'جوام',N'',N'');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1579,N'810',N'ms',N'جوام',N'',N'جوام');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1580,N'810',N'fa',N'جوام',N'',N'');

INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1581,N'811',N'fr',N'جزر ماريانا',N'',N'');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1582,N'811',N'tr',N'جزر ماريانا',N'',N'');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1583,N'811',N'ms',N'جزر ماريانا',N'',N'جزر ماريانا');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1584,N'811',N'fa',N'جزر ماريانا',N'',N'');

INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1585,N'812',N'fr',N'Micronesia',N'691',N'FSM');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1586,N'812',N'tr',N'Micronesia',N'691',N'FSM');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1587,N'812',N'ms',N'Micronesia',N'691',N'Micronesia');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1588,N'812',N'fa',N'Micronesia',N'691',N'FSM');

INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1589,N'813',N'fr',N'Marshall Island',N'692',N'MHL');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1590,N'813',N'tr',N'Marshall Island',N'692',N'MHL');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1591,N'813',N'ms',N'Marshall Island',N'692',N'Marshall Island');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1592,N'813',N'fa',N'Marshall Island',N'692',N'MHL');

INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1593,N'814',N'fr',N'بيلو',N'',N'');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1594,N'814',N'tr',N'بيلو',N'',N'');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1595,N'814',N'ms',N'بيلو',N'',N'بيلو');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1596,N'814',N'fa',N'بيلو',N'',N'');

INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1597,N'548',N'fr',N'Serbian',N'381',N'SRB');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1598,N'548',N'tr',N'Serbian',N'381',N'SRB');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1599,N'548',N'ms',N'Serbian',N'381',N'Serbian');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1600,N'548',N'fa',N'Serbian',N'381',N'SRB');

INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1601,N'550',N'fr',N'Kosovar',N'',N'');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1602,N'550',N'tr',N'Kosovar',N'',N'');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1603,N'550',N'ms',N'Kosovar',N'',N'Kosovar');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1604,N'550',N'fa',N'Kosovar',N'',N'');

INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1605,N'815',N'fr',N'بولينيسياالفرنسية',N'',N'');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1606,N'815',N'tr',N'بولينيسياالفرنسية',N'',N'');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1607,N'815',N'ms',N'بولينيسياالفرنسية',N'',N'بولينيسياالفرنسية');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1608,N'815',N'fa',N'بولينيسياالفرنسية',N'',N'');

INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1609,N'816',N'fr',N'Wallis and Futura Isl.',N'681',N'WLF');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1610,N'816',N'tr',N'Wallis and Futura Isl.',N'681',N'WLF');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1611,N'816',N'ms',N'Wallis and Futura Isl.',N'681',N'Wallis and Futura Isl.');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1612,N'816',N'fa',N'Wallis and Futura Isl.',N'681',N'WLF');

INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1613,N'817',N'fr',N'كاليدونيا الجديد',N'687',N'NCL');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1614,N'817',N'tr',N'كاليدونيا الجديد',N'687',N'NCL');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1615,N'817',N'ms',N'كاليدونيا الجديد',N'687',N'كاليدونيا الجديد');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1616,N'817',N'fa',N'كاليدونيا الجديد',N'687',N'NCL');

INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1617,N'900',N'fr',N'Unknown',N'',N'');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1618,N'900',N'tr',N'Unknown',N'',N'');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1619,N'900',N'ms',N'Unknown',N'',N'Unknown');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1620,N'900',N'fa',N'Unknown',N'',N'');

INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1621,N'818',N'fr',N'Madagascan',N'261',N'MDG');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1622,N'818',N'tr',N'Madagascan',N'261',N'MDG');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1623,N'818',N'ms',N'Madagascan',N'261',N'Madagascan');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1624,N'818',N'fa',N'Madagascan',N'261',N'MDG');

INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1625,N'126',N'fr',N'وثيقة قطرية',N'',N'');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1626,N'126',N'tr',N'وثيقة قطرية',N'',N'');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1627,N'126',N'ms',N'وثيقة قطرية',N'',N'وثيقة قطرية');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1628,N'126',N'fa',N'وثيقة قطرية',N'',N'');

INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1629,N'128',N'fr',N'Emirati Doct',N'',N'');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1630,N'128',N'tr',N'Emirati Doct',N'',N'');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1631,N'128',N'ms',N'Emirati Doct',N'',N'Emirati Doct');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1632,N'128',N'fa',N'Emirati Doct',N'',N'');

INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1633,N'116',N'fr',N'Kuwaiti Without',N'',N'');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1634,N'116',N'tr',N'Kuwaiti Without',N'',N'');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1635,N'116',N'ms',N'Kuwaiti Without',N'',N'Kuwaiti Without');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1636,N'116',N'fa',N'Kuwaiti Without',N'',N'');

INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1637,N'121',N'fr',N'Palestinian (Egyptian T.Doct.)',N'20',N'PS');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1638,N'121',N'tr',N'Palestinian (Egyptian T.Doct.)',N'20',N'PS');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1639,N'121',N'ms',N'Palestinian (Egyptian T.Doct.)',N'20',N'Palestinian (Egyptian T.Doct.)');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1640,N'121',N'fa',N'Palestinian (Egyptian T.Doct.)',N'20',N'PS');

INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1641,N'125',N'fr',N'Palestinian (Syrian T.Doct.)',N'963',N'PS');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1642,N'125',N'tr',N'Palestinian (Syrian T.Doct.)',N'963',N'PS');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1643,N'125',N'ms',N'Palestinian (Syrian T.Doct.)',N'963',N'Palestinian (Syrian T.Doct.)');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1644,N'125',N'fa',N'Palestinian (Syrian T.Doct.)',N'963',N'PS');

INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1645,N'352',N'fr',N'Myanmar / Passport of Pakistan',N'',N'');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1646,N'352',N'tr',N'Myanmar / Passport of Pakistan',N'',N'');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1647,N'352',N'ms',N'Myanmar / Passport of Pakistan',N'',N'Myanmar / Passport of Pakistan');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1648,N'352',N'fa',N'Myanmar / Passport of Pakistan',N'',N'');

INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1649,N'350',N'fr',N'without',N'',N'');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1650,N'350',N'tr',N'without',N'',N'');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1651,N'350',N'ms',N'without',N'',N'without');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1652,N'350',N'fa',N'without',N'',N'');

INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1653,N'353',N'fr',N'ميانمار/جوازبنجلا دش',N'95',N'MMR');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1654,N'353',N'tr',N'ميانمار/جوازبنجلا دش',N'95',N'MMR');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1655,N'353',N'ms',N'ميانمار/جوازبنجلا دش',N'95',N'ميانمار/جوازبنجلا دش');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1656,N'353',N'fa',N'ميانمار/جوازبنجلا دش',N'95',N'MMR');

INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1657,N'663',N'fr',N'سانت كيتس  ونافيس',N'1',N'KN');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1658,N'663',N'tr',N'سانت كيتس  ونافيس',N'1',N'KN');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1659,N'663',N'ms',N'سانت كيتس  ونافيس',N'1',N'سانت كيتس  ونافيس');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1660,N'663',N'fa',N'سانت كيتس  ونافيس',N'1',N'KN');

INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1661,N'351',N'fr',N'ميانمار /مقيم',N'95',N'MMR');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1662,N'351',N'tr',N'ميانمار /مقيم',N'95',N'MMR');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1663,N'351',N'ms',N'ميانمار /مقيم',N'95',N'ميانمار /مقيم');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1664,N'351',N'fa',N'ميانمار /مقيم',N'95',N'MMR');

INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1665,N'131',N'fr',N'قبائل نازحة/الحليفه',N'',N'');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1666,N'131',N'tr',N'قبائل نازحة/الحليفه',N'',N'');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1667,N'131',N'ms',N'قبائل نازحة/الحليفه',N'',N'قبائل نازحة/الحليفه');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1668,N'131',N'fa',N'قبائل نازحة/الحليفه',N'',N'');

INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1669,N'136',N'fr',N'غير قطري',N'',N'');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1670,N'136',N'tr',N'غير قطري',N'',N'');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1671,N'136',N'ms',N'غير قطري',N'',N'غير قطري');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1672,N'136',N'fa',N'غير قطري',N'',N'');

INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1673,N'142',N'fr',N'مقيم/أفراد القبائل',N'',N'');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1674,N'142',N'tr',N'مقيم/أفراد القبائل',N'',N'');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1675,N'142',N'ms',N'مقيم/أفراد القبائل',N'',N'مقيم/أفراد القبائل');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1676,N'142',N'fa',N'مقيم/أفراد القبائل',N'',N'');

INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1677,N'143',N'fr',N'مقيم/غير معروف',N'',N'');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1678,N'143',N'tr',N'مقيم/غير معروف',N'',N'');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1679,N'143',N'ms',N'مقيم/غير معروف',N'',N'مقيم/غير معروف');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1680,N'143',N'fa',N'مقيم/غير معروف',N'',N'');

INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1681,N'137',N'fr',N'غير اماراتي',N'',N'');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1682,N'137',N'tr',N'غير اماراتي',N'',N'');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1683,N'137',N'ms',N'غير اماراتي',N'',N'غير اماراتي');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1684,N'137',N'fa',N'غير اماراتي',N'',N'');

INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1685,N'129',N'fr',N'وثيقة بحرينيه',N'',N'');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1686,N'129',N'tr',N'وثيقة بحرينيه',N'',N'');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1687,N'129',N'ms',N'وثيقة بحرينيه',N'',N'وثيقة بحرينيه');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1688,N'129',N'fa',N'وثيقة بحرينيه',N'',N'');

INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1689,N'823',N'fr',N'قبيلة نهد',N'',N'');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1690,N'823',N'tr',N'قبيلة نهد',N'',N'');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1691,N'823',N'ms',N'قبيلة نهد',N'',N'قبيلة نهد');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1692,N'823',N'fa',N'قبيلة نهد',N'',N'');

INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1693,N'144',N'fr',N'مقيم/لا يحمل وثيقة',N'',N'');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1694,N'144',N'tr',N'مقيم/لا يحمل وثيقة',N'',N'');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1695,N'144',N'ms',N'مقيم/لا يحمل وثيقة',N'',N'مقيم/لا يحمل وثيقة');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1696,N'144',N'fa',N'مقيم/لا يحمل وثيقة',N'',N'');

INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1697,N'135',N'fr',N'غير بحريني',N'',N'');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1698,N'135',N'tr',N'غير بحريني',N'',N'');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1699,N'135',N'ms',N'غير بحريني',N'',N'غير بحريني');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1700,N'135',N'fa',N'غير بحريني',N'',N'');

INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1701,N'707',N'fr',N'Tokelau',N'',N'TKL');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1702,N'707',N'tr',N'Tokelau',N'',N'TKL');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1703,N'707',N'ms',N'Tokelau',N'',N'Tokelau');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1704,N'707',N'fa',N'Tokelau',N'',N'TKL');

INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1705,N'710',N'fr',N'فرنسا الجنوب القطبية',N'',N'');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1706,N'710',N'tr',N'فرنسا الجنوب القطبية',N'',N'');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1707,N'710',N'ms',N'فرنسا الجنوب القطبية',N'',N'فرنسا الجنوب القطبية');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1708,N'710',N'fa',N'فرنسا الجنوب القطبية',N'',N'');

INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1709,N'706',N'fr',N'جزر نورفولك',N'',N'NFK');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1710,N'706',N'tr',N'جزر نورفولك',N'',N'NFK');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1711,N'706',N'ms',N'جزر نورفولك',N'',N'جزر نورفولك');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1712,N'706',N'fa',N'جزر نورفولك',N'',N'NFK');

INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1713,N'824',N'fr',N'جزر مينور',N'',N'');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1714,N'824',N'tr',N'جزر مينور',N'',N'');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1715,N'824',N'ms',N'جزر مينور',N'',N'جزر مينور');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1716,N'824',N'fa',N'جزر مينور',N'',N'');

INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1717,N'134',N'fr',N'غير كويتي',N'',N'');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1718,N'134',N'tr',N'غير كويتي',N'',N'');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1719,N'134',N'ms',N'غير كويتي',N'',N'غير كويتي');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1720,N'134',N'fa',N'غير كويتي',N'',N'');

INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1721,N'705',N'fr',N'انتاركتيكا',N'',N'');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1722,N'705',N'tr',N'انتاركتيكا',N'',N'');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1723,N'705',N'ms',N'انتاركتيكا',N'',N'انتاركتيكا');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1724,N'705',N'fa',N'انتاركتيكا',N'',N'');

INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1725,N'826',N'fr',N'مقيم اجنبي - العدوان',N'',N'');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1726,N'826',N'tr',N'مقيم اجنبي - العدوان',N'',N'');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1727,N'826',N'ms',N'مقيم اجنبي - العدوان',N'',N'مقيم اجنبي - العدوان');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1728,N'826',N'fa',N'مقيم اجنبي - العدوان',N'',N'');

INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1729,N'825',N'fr',N'مقيم اجنبي - الاشاجعة',N'',N'');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1730,N'825',N'tr',N'مقيم اجنبي - الاشاجعة',N'',N'');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1731,N'825',N'ms',N'مقيم اجنبي - الاشاجعة',N'',N'مقيم اجنبي - الاشاجعة');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1732,N'825',N'fa',N'مقيم اجنبي - الاشاجعة',N'',N'');

INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1733,N'709',N'fr',N'Cocos (Keeling) Islands',N'61',N'CCK');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1734,N'709',N'tr',N'Cocos (Keeling) Islands',N'61',N'CCK');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1735,N'709',N'ms',N'Cocos (Keeling) Islands',N'61',N'Cocos (Keeling) Islands');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1736,N'709',N'fa',N'Cocos (Keeling) Islands',N'61',N'CCK');

INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1737,N'708',N'fr',N'جزيرةكريسماس',N'61',N'CXR');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1738,N'708',N'tr',N'جزيرةكريسماس',N'61',N'CXR');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1739,N'708',N'ms',N'جزيرةكريسماس',N'61',N'جزيرةكريسماس');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1740,N'708',N'fa',N'جزيرةكريسماس',N'61',N'CXR');

INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1741,N'821',N'fr',N'قبائل مجاورة للعبر',N'',N'');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1742,N'821',N'tr',N'قبائل مجاورة للعبر',N'',N'');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1743,N'821',N'ms',N'قبائل مجاورة للعبر',N'',N'قبائل مجاورة للعبر');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1744,N'821',N'fa',N'قبائل مجاورة للعبر',N'',N'');

INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1745,N'662',N'fr',N'البريطانية في المحيط',N'',N'');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1746,N'662',N'tr',N'البريطانية في المحيط',N'',N'');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1747,N'662',N'ms',N'البريطانية في المحيط',N'',N'البريطانية في المحيط');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1748,N'662',N'fa',N'البريطانية في المحيط',N'',N'');

INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1749,N'657',N'fr',N'جزر كوك',N'',N'COK');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1750,N'657',N'tr',N'جزر كوك',N'',N'COK');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1751,N'657',N'ms',N'جزر كوك',N'',N'جزر كوك');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1752,N'657',N'fa',N'جزر كوك',N'',N'COK');

INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1753,N'664',N'fr',N'جنوب جورجيا',N'',N'GEO');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1754,N'664',N'tr',N'جنوب جورجيا',N'',N'GEO');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1755,N'664',N'ms',N'جنوب جورجيا',N'',N'جنوب جورجيا');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1756,N'664',N'fa',N'جنوب جورجيا',N'',N'GEO');

INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1757,N'132',N'fr',N'اليمن - لحج',N'',N'');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1758,N'132',N'tr',N'اليمن - لحج',N'',N'');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1759,N'132',N'ms',N'اليمن - لحج',N'',N'اليمن - لحج');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1760,N'132',N'fa',N'اليمن - لحج',N'',N'');

INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1761,N'130',N'fr',N'عرب ثمانية وأربعون',N'',N'');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1762,N'130',N'tr',N'عرب ثمانية وأربعون',N'',N'');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1763,N'130',N'ms',N'عرب ثمانية وأربعون',N'',N'عرب ثمانية وأربعون');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1764,N'130',N'fa',N'عرب ثمانية وأربعون',N'',N'');

INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1765,N'555',N'fr',N'ميتروبوليتان فرنسية',N'',N'');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1766,N'555',N'tr',N'ميتروبوليتان فرنسية',N'',N'');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1767,N'555',N'ms',N'ميتروبوليتان فرنسية',N'',N'ميتروبوليتان فرنسية');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1768,N'555',N'fa',N'ميتروبوليتان فرنسية',N'',N'');

INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1769,N'711',N'fr',N'جزيرة هيرد وماكدونلد',N'',N'');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1770,N'711',N'tr',N'جزيرة هيرد وماكدونلد',N'',N'');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1771,N'711',N'ms',N'جزيرة هيرد وماكدونلد',N'',N'جزيرة هيرد وماكدونلد');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1772,N'711',N'fa',N'جزيرة هيرد وماكدونلد',N'',N'');

INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1773,N'133',N'fr',N'قبائل نازحة/الكويت',N'',N'');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1774,N'133',N'tr',N'قبائل نازحة/الكويت',N'',N'');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1775,N'133',N'ms',N'قبائل نازحة/الكويت',N'',N'قبائل نازحة/الكويت');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1776,N'133',N'fa',N'قبائل نازحة/الكويت',N'',N'');

INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1777,N'146',N'fr',N'المناهيل والمهرة',N'',N'');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1778,N'146',N'tr',N'المناهيل والمهرة',N'',N'');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1779,N'146',N'ms',N'المناهيل والمهرة',N'',N'المناهيل والمهرة');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1780,N'146',N'fa',N'المناهيل والمهرة',N'',N'');

INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1781,N'451',N'fr',N'سانت هيلانة',N'290',N'SHN');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1782,N'451',N'tr',N'سانت هيلانة',N'290',N'SHN');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1783,N'451',N'ms',N'سانت هيلانة',N'290',N'سانت هيلانة');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1784,N'451',N'fa',N'سانت هيلانة',N'290',N'SHN');

INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1785,N'659',N'fr',N'باربودا',N'1',N'BRB');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1786,N'659',N'tr',N'باربودا',N'1',N'BRB');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1787,N'659',N'ms',N'باربودا',N'1',N'باربودا');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1788,N'659',N'fa',N'باربودا',N'1',N'BRB');

INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1789,N'139',N'fr',N'مقيم/نازح',N'',N'');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1790,N'139',N'tr',N'مقيم/نازح',N'',N'');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1791,N'139',N'ms',N'مقيم/نازح',N'',N'مقيم/نازح');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1792,N'139',N'fa',N'مقيم/نازح',N'',N'');

INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1793,N'901',N'fr',N'اخرى',N'',N'');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1794,N'901',N'tr',N'اخرى',N'',N'');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1795,N'901',N'ms',N'اخرى',N'',N'اخرى');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1796,N'901',N'fa',N'اخرى',N'',N'');

INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1797,N'827',N'fr',N'جنسية غير معرفه',N'',N'');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1798,N'827',N'tr',N'جنسية غير معرفه',N'',N'');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1799,N'827',N'ms',N'جنسية غير معرفه',N'',N'جنسية غير معرفه');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1800,N'827',N'fa',N'جنسية غير معرفه',N'',N'');

INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1801,N'820',N'fr',N'قبيلة النسي',N'',N'');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1802,N'820',N'tr',N'قبيلة النسي',N'',N'');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1803,N'820',N'ms',N'قبيلة النسي',N'',N'قبيلة النسي');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1804,N'820',N'fa',N'قبيلة النسي',N'',N'');

INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1805,N'822',N'fr',N'قبيلة الحرث',N'',N'');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1806,N'822',N'tr',N'قبيلة الحرث',N'',N'');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1807,N'822',N'ms',N'قبيلة الحرث',N'',N'قبيلة الحرث');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1808,N'822',N'fa',N'قبيلة الحرث',N'',N'');

INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1809,N'704',N'fr',N'نيو',N'',N'');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1810,N'704',N'tr',N'نيو',N'',N'');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1811,N'704',N'ms',N'نيو',N'',N'نيو');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1812,N'704',N'fa',N'نيو',N'',N'');

INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1813,N'140',N'fr',N'مقيم/مولود',N'',N'');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1814,N'140',N'tr',N'مقيم/مولود',N'',N'');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1815,N'140',N'ms',N'مقيم/مولود',N'',N'مقيم/مولود');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1816,N'140',N'fa',N'مقيم/مولود',N'',N'');

INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1817,N'141',N'fr',N'مقيم/طالب جنسية',N'',N'');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1818,N'141',N'tr',N'مقيم/طالب جنسية',N'',N'');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1819,N'141',N'ms',N'مقيم/طالب جنسية',N'',N'مقيم/طالب جنسية');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1820,N'141',N'fa',N'مقيم/طالب جنسية',N'',N'');

INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1821,N'138',N'fr',N'غير عماني',N'',N'');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1822,N'138',N'tr',N'غير عماني',N'',N'');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1823,N'138',N'ms',N'غير عماني',N'',N'غير عماني');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1824,N'138',N'fa',N'غير عماني',N'',N'');

INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1825,N'660',N'fr',N'انتيل الهولندية',N'599',N'ANT');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1826,N'660',N'tr',N'انتيل الهولندية',N'599',N'ANT');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1827,N'660',N'ms',N'انتيل الهولندية',N'599',N'انتيل الهولندية');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1828,N'660',N'fa',N'انتيل الهولندية',N'599',N'ANT');

INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1829,N'454',N'fr',N'كاب فيرد',N'238',N'CPV');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1830,N'454',N'tr',N'كاب فيرد',N'238',N'CPV');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1831,N'454',N'ms',N'كاب فيرد',N'238',N'كاب فيرد');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1832,N'454',N'fa',N'كاب فيرد',N'238',N'CPV');

INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1833,N'712',N'fr',N'Pitcairn',N'64',N'PCN');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1834,N'712',N'tr',N'Pitcairn',N'64',N'PCN');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1835,N'712',N'ms',N'Pitcairn',N'64',N'Pitcairn');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1836,N'712',N'fa',N'Pitcairn',N'64',N'PCN');

INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1837,N'819',N'fr',N'قبيلة بالعبيد',N'',N'');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1838,N'819',N'tr',N'قبيلة بالعبيد',N'',N'');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1839,N'819',N'ms',N'قبيلة بالعبيد',N'',N'قبيلة بالعبيد');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1840,N'819',N'fa',N'قبيلة بالعبيد',N'',N'');

INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1841,N'145',N'fr',N'قبيلة الصيعر',N'',N'');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1842,N'145',N'tr',N'قبيلة الصيعر',N'',N'');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1843,N'145',N'ms',N'قبيلة الصيعر',N'',N'قبيلة الصيعر');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1844,N'145',N'fa',N'قبيلة الصيعر',N'',N'');

INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1845,N'127',N'fr',N'وثيقة عمانيه',N'',N'');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1846,N'127',N'tr',N'وثيقة عمانيه',N'',N'');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1847,N'127',N'ms',N'وثيقة عمانيه',N'',N'وثيقة عمانيه');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1848,N'127',N'fa',N'وثيقة عمانيه',N'',N'');

INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1849,N'117',N'fr',N'افراد القبائل',N'',N'');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1850,N'117',N'tr',N'افراد القبائل',N'',N'');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1851,N'117',N'ms',N'افراد القبائل',N'',N'افراد القبائل');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1852,N'117',N'fa',N'افراد القبائل',N'',N'');

INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1853,N'639',N'fr',N'Anguillian',N'1',N'AIA');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1854,N'639',N'tr',N'Anguillian',N'1',N'AIA');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1855,N'639',N'ms',N'Anguillian',N'1',N'Anguillian');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1856,N'639',N'fa',N'Anguillian',N'1',N'AIA');

INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1857,N'649',N'fr',N'سان استاتيوس',N'',N'');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1858,N'649',N'tr',N'سان استاتيوس',N'',N'');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1859,N'649',N'ms',N'سان استاتيوس',N'',N'سان استاتيوس');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1860,N'649',N'fa',N'سان استاتيوس',N'',N'');

INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1861,N'809',N'fr',N'Samoana',N'685',N'ASM');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1862,N'809',N'tr',N'Samoana',N'685',N'ASM');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1863,N'809',N'ms',N'Samoana',N'685',N'Samoana');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1864,N'809',N'fa',N'Samoana',N'685',N'ASM');

INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1865,N'2222',N'fr',N'جنسية غير معرفه',N'',N'');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1866,N'2222',N'tr',N'جنسية غير معرفه',N'',N'');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1867,N'2222',N'ms',N'جنسية غير معرفه',N'',N'جنسية غير معرفه');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1868,N'2222',N'fa',N'جنسية غير معرفه',N'',N'');

INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1869,N'997',N'fr',N'جميع الجنسيات',N'',N'');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1870,N'997',N'tr',N'جميع الجنسيات',N'',N'');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1871,N'997',N'ms',N'جميع الجنسيات',N'',N'جميع الجنسيات');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1872,N'997',N'fa',N'جميع الجنسيات',N'',N'');

INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1873,N'119',N'fr',N'قبائل مجاورة للعطفين',N'',N'');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1874,N'119',N'tr',N'قبائل مجاورة للعطفين',N'',N'');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1875,N'119',N'ms',N'قبائل مجاورة للعطفين',N'',N'قبائل مجاورة للعطفين');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1876,N'119',N'fa',N'قبائل مجاورة للعطفين',N'',N'');

INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1877,N'640',N'fr',N'Antiguan',N'1',N'ATG');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1878,N'640',N'tr',N'Antiguan',N'1',N'ATG');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1879,N'640',N'ms',N'Antiguan',N'1',N'Antiguan');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1880,N'640',N'fa',N'Antiguan',N'1',N'ATG');

INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1881,N'651',N'fr',N'سان مارتين',N'',N'');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1882,N'651',N'tr',N'سان مارتين',N'',N'');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1883,N'651',N'ms',N'سان مارتين',N'',N'سان مارتين');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1884,N'651',N'fa',N'سان مارتين',N'',N'');

INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1885,N'551',N'fr',N'Montenegrin',N'',N'');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1886,N'551',N'tr',N'Montenegrin',N'',N'');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1887,N'551',N'ms',N'Montenegrin',N'',N'Montenegrin');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1888,N'551',N'fa',N'Montenegrin',N'',N'');

INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1889,N'348',N'fr',N'Kyrgyzian',N'',N'');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1890,N'348',N'tr',N'Kyrgyzian',N'',N'');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1891,N'348',N'ms',N'Kyrgyzian',N'',N'Kyrgyzian');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1892,N'348',N'fa',N'Kyrgyzian',N'',N'');

INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1893,N'120',N'fr',N'Foreigner Saudi Passport',N'',N'');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1894,N'120',N'tr',N'Foreigner Saudi Passport',N'',N'');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1895,N'120',N'ms',N'Foreigner Saudi Passport',N'',N'Foreigner Saudi Passport');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1896,N'120',N'fa',N'Foreigner Saudi Passport',N'',N'');

INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1897,N'445',N'fr',N'Botswanian',N'267',N'BWA');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1898,N'445',N'tr',N'Botswanian',N'267',N'BWA');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1899,N'445',N'ms',N'Botswanian',N'267',N'Botswanian');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1900,N'445',N'fa',N'Botswanian',N'267',N'BWA');

INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1901,N'661',N'fr',N'جزر كوكوس',N'',N'');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1902,N'661',N'tr',N'جزر كوكوس',N'',N'');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1903,N'661',N'ms',N'جزر كوكوس',N'',N'جزر كوكوس');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1904,N'661',N'fa',N'جزر كوكوس',N'',N'');

INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1905,N'452',N'fr',N'جزيرة مايوت',N'',N'');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1906,N'452',N'tr',N'جزيرة مايوت',N'',N'');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1907,N'452',N'ms',N'جزيرة مايوت',N'',N'جزيرة مايوت');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1908,N'452',N'fa',N'جزيرة مايوت',N'',N'');

INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1909,N'124',N'fr',N'Palestinian (Iraqi T.Doct.)',N'964',N'PS');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1910,N'124',N'tr',N'Palestinian (Iraqi T.Doct.)',N'964',N'PS');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1911,N'124',N'ms',N'Palestinian (Iraqi T.Doct.)',N'964',N'Palestinian (Iraqi T.Doct.)');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1912,N'124',N'fa',N'Palestinian (Iraqi T.Doct.)',N'964',N'PS');

INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1913,N'122',N'fr',N'Palestinian (lebanese T.Doct.)',N'961',N'PS');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1914,N'122',N'tr',N'Palestinian (lebanese T.Doct.)',N'961',N'PS');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1915,N'122',N'ms',N'Palestinian (lebanese T.Doct.)',N'961',N'Palestinian (lebanese T.Doct.)');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1916,N'122',N'fa',N'Palestinian (lebanese T.Doct.)',N'961',N'PS');

INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1917,N'123',N'fr',N'Palestinian (Jordanian T.Doct.)',N'962',N'PS');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1918,N'123',N'tr',N'Palestinian (Jordanian T.Doct.)',N'962',N'PS');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1919,N'123',N'ms',N'Palestinian (Jordanian T.Doct.)',N'962',N'Palestinian (Jordanian T.Doct.)');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1920,N'123',N'fa',N'Palestinian (Jordanian T.Doct.)',N'962',N'PS');

INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1921,N'450',N'fr',N'Other Africans',N'',N'');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1922,N'450',N'tr',N'Other Africans',N'',N'');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1923,N'450',N'ms',N'Other Africans',N'',N'Other Africans');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1924,N'450',N'fa',N'Other Africans',N'',N'');

INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1925,N'998',N'fr',N'البهره',N'',N'');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1926,N'998',N'tr',N'البهره',N'',N'');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1927,N'998',N'ms',N'البهره',N'',N'البهره');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1928,N'998',N'fa',N'البهره',N'',N'');

INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1929,N'999',N'fr',N'جنسية غير معرفه',N'',N'');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1930,N'999',N'tr',N'جنسية غير معرفه',N'',N'');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1931,N'999',N'ms',N'جنسية غير معرفه',N'',N'جنسية غير معرفه');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1932,N'999',N'fa',N'جنسية غير معرفه',N'',N'');

INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1933,N'828',N'fr',N'جنسية غير معرفه',N'',N'');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1934,N'828',N'tr',N'جنسية غير معرفه',N'',N'');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1935,N'828',N'ms',N'جنسية غير معرفه',N'',N'جنسية غير معرفه');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1936,N'828',N'fa',N'جنسية غير معرفه',N'',N'');

INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1937,N'829',N'fr',N'جنسية غير معرفه',N'',N'');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1938,N'829',N'tr',N'جنسية غير معرفه',N'',N'');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1939,N'829',N'ms',N'جنسية غير معرفه',N'',N'جنسية غير معرفه');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1940,N'829',N'fa',N'جنسية غير معرفه',N'',N'');

INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1941,N'833',N'fr',N'جنسية غير معرفه',N'',N'');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1942,N'833',N'tr',N'جنسية غير معرفه',N'',N'');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1943,N'833',N'ms',N'جنسية غير معرفه',N'',N'جنسية غير معرفه');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1944,N'833',N'fa',N'جنسية غير معرفه',N'',N'');

INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1945,N'835',N'fr',N'جنسية غير معرفه',N'',N'');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1946,N'835',N'tr',N'جنسية غير معرفه',N'',N'');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1947,N'835',N'ms',N'جنسية غير معرفه',N'',N'جنسية غير معرفه');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1948,N'835',N'fa',N'جنسية غير معرفه',N'',N'');

INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1949,N'836',N'fr',N'جنسية غير معرفه',N'',N'');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1950,N'836',N'tr',N'جنسية غير معرفه',N'',N'');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1951,N'836',N'ms',N'جنسية غير معرفه',N'',N'جنسية غير معرفه');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1952,N'836',N'fa',N'جنسية غير معرفه',N'',N'');

INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1953,N'802',N'fr',N'كيريباتي',N'',N'');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1954,N'802',N'tr',N'كيريباتي',N'',N'');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1955,N'802',N'ms',N'كيريباتي',N'',N'كيريباتي');
INSERT INTO shc_portal.shc_nationality_lk(id,code,lang,label,country_phone_prefix,country_name_prefix) VALUES(1956,N'802',N'fa',N'كيريباتي',N'',N'');

SET IDENTITY_INSERT shc_portal.shc_nationality_lk OFF;
GO


SET IDENTITY_INSERT shc_portal.shc_notification_category_lk ON;

INSERT INTO shc_portal.shc_notification_category_lk(id,code,lang,label,sample,mandatory) VALUES(11,N'GENERAL',N'fr',N'General',N'Make sure to carry your Hajj card when performing the rituals',0);
INSERT INTO shc_portal.shc_notification_category_lk(id,code,lang,label,sample,mandatory) VALUES(12,N'GENERAL',N'tr',N'General',N'Make sure to carry your Hajj card when performing the rituals',0);
INSERT INTO shc_portal.shc_notification_category_lk(id,code,lang,label,sample,mandatory) VALUES(13,N'GENERAL',N'ms',N'General',N'Make sure to carry your Hajj card when performing the rituals',0);
INSERT INTO shc_portal.shc_notification_category_lk(id,code,lang,label,sample,mandatory) VALUES(14,N'GENERAL',N'fa',N'General',N'Make sure to carry your Hajj card when performing the rituals',0);

INSERT INTO shc_portal.shc_notification_category_lk(id,code,lang,label,sample,mandatory) VALUES(15,N'HEALTH',N'fr',N'Health',N'If your temperature rises above 38 degrees, go to the nearest health point directly',1);
INSERT INTO shc_portal.shc_notification_category_lk(id,code,lang,label,sample,mandatory) VALUES(16,N'HEALTH',N'tr',N'Health',N'If your temperature rises above 38 degrees, go to the nearest health point directly',1);
INSERT INTO shc_portal.shc_notification_category_lk(id,code,lang,label,sample,mandatory) VALUES(17,N'HEALTH',N'ms',N'Health',N'If your temperature rises above 38 degrees, go to the nearest health point directly',1);
INSERT INTO shc_portal.shc_notification_category_lk(id,code,lang,label,sample,mandatory) VALUES(18,N'HEALTH',N'fa',N'Health',N'If your temperature rises above 38 degrees, go to the nearest health point directly',1);

INSERT INTO shc_portal.shc_notification_category_lk(id,code,lang,label,sample,mandatory) VALUES(19,N'RELIGIOUS',N'fr',N'Religious',N'Our Lord, accept from us, You are the Hearer, the Knower',0);
INSERT INTO shc_portal.shc_notification_category_lk(id,code,lang,label,sample,mandatory) VALUES(20,N'RELIGIOUS',N'tr',N'Religious',N'Our Lord, accept from us, You are the Hearer, the Knower',0);
INSERT INTO shc_portal.shc_notification_category_lk(id,code,lang,label,sample,mandatory) VALUES(21,N'RELIGIOUS',N'ms',N'Religious',N'Our Lord, accept from us, You are the Hearer, the Knower',0);
INSERT INTO shc_portal.shc_notification_category_lk(id,code,lang,label,sample,mandatory) VALUES(22,N'RELIGIOUS',N'fa',N'Religious',N'Our Lord, accept from us, You are the Hearer, the Knower',0);

INSERT INTO shc_portal.shc_notification_category_lk(id,code,lang,label,sample,mandatory) VALUES(23,N'RITUAL',N'fr',N'Ritual',N'Tawaf al-Ifadah is one of the pillars of Hajj that can only be done by performing it. And the evidence for that is the Almighty’s saying: (And let them circumambulate the Ancient House)',1);
INSERT INTO shc_portal.shc_notification_category_lk(id,code,lang,label,sample,mandatory) VALUES(24,N'RITUAL',N'tr',N'Ritual',N'Tawaf al-Ifadah is one of the pillars of Hajj that can only be done by performing it. And the evidence for that is the Almighty’s saying: (And let them circumambulate the Ancient House)',1);
INSERT INTO shc_portal.shc_notification_category_lk(id,code,lang,label,sample,mandatory) VALUES(25,N'RITUAL',N'ms',N'Ritual',N'Tawaf al-Ifadah is one of the pillars of Hajj that can only be done by performing it. And the evidence for that is the Almighty’s saying: (And let them circumambulate the Ancient House)',1);
INSERT INTO shc_portal.shc_notification_category_lk(id,code,lang,label,sample,mandatory) VALUES(26,N'RITUAL',N'fa',N'Ritual',N'Tawaf al-Ifadah is one of the pillars of Hajj that can only be done by performing it. And the evidence for that is the Almighty’s saying: (And let them circumambulate the Ancient House)',1);

INSERT INTO shc_portal.shc_notification_category_lk(id,code,lang,label,sample,mandatory) VALUES(27,N'GENERAL_AWARENESS',N'fr',N'General Awareness',N'Avoid climbing mountains and high places and avoid crowding, docking and sleeping on the roads',0);
INSERT INTO shc_portal.shc_notification_category_lk(id,code,lang,label,sample,mandatory) VALUES(28,N'GENERAL_AWARENESS',N'tr',N'General Awareness',N'Avoid climbing mountains and high places and avoid crowding, docking and sleeping on the roads',0);
INSERT INTO shc_portal.shc_notification_category_lk(id,code,lang,label,sample,mandatory) VALUES(29,N'GENERAL_AWARENESS',N'ms',N'General Awareness',N'Avoid climbing mountains and high places and avoid crowding, docking and sleeping on the roads',0);
INSERT INTO shc_portal.shc_notification_category_lk(id,code,lang,label,sample,mandatory) VALUES(30,N'GENERAL_AWARENESS',N'fa',N'General Awareness',N'Avoid climbing mountains and high places and avoid crowding, docking and sleeping on the roads',0);

SET IDENTITY_INSERT shc_portal.shc_notification_category_lk OFF;
GO
