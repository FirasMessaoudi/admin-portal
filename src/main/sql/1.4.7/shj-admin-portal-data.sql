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

INSERT INTO shc_portal.shc_supplication_lk (id, code, lang, label)
VALUES (13, 'MORNING_SUPPLICATION_1', 'fr', 'Allah! There is no ilah (deity worthy of worship) but He The Living, The Eternal One. Neither slumber, nor sleep overtakes Him. To Him belongs whatever is in the Heavens and on the earth. Who is he that can intercede with Him but by His permission? He knows what is before and what is behind them. They encompass nothing of His knowledge which He will. His Throne extends over the Heavens and the Earth, and the preservation of both does not weary Him. He is the Exalted The Immense One.');
INSERT INTO shc_portal.shc_supplication_lk (id, code, lang, label)
VALUES (14, 'MORNING_SUPPLICATION_1', 'tr', 'Allah! There is no ilah (deity worthy of worship) but He The Living, The Eternal One. Neither slumber, nor sleep overtakes Him. To Him belongs whatever is in the Heavens and on the earth. Who is he that can intercede with Him but by His permission? He knows what is before and what is behind them. They encompass nothing of His knowledge which He will. His Throne extends over the Heavens and the Earth, and the preservation of both does not weary Him. He is the Exalted The Immense One.');
INSERT INTO shc_portal.shc_supplication_lk (id, code, lang, label)
VALUES (15, 'MORNING_SUPPLICATION_1', 'ms', 'Allah! There is no ilah (deity worthy of worship) but He The Living, The Eternal One. Neither slumber, nor sleep overtakes Him. To Him belongs whatever is in the Heavens and on the earth. Who is he that can intercede with Him but by His permission? He knows what is before and what is behind them. They encompass nothing of His knowledge which He will. His Throne extends over the Heavens and the Earth, and the preservation of both does not weary Him. He is the Exalted The Immense One.');
INSERT INTO shc_portal.shc_supplication_lk (id, code, lang, label)
VALUES (16, 'MORNING_SUPPLICATION_1', 'fa', 'Allah! There is no ilah (deity worthy of worship) but He The Living, The Eternal One. Neither slumber, nor sleep overtakes Him. To Him belongs whatever is in the Heavens and on the earth. Who is he that can intercede with Him but by His permission? He knows what is before and what is behind them. They encompass nothing of His knowledge which He will. His Throne extends over the Heavens and the Earth, and the preservation of both does not weary Him. He is the Exalted The Immense One.');

INSERT INTO shc_portal.shc_supplication_lk (id, code, lang, label)
VALUES (17, 'MORNING_SUPPLICATION_2', 'fr', 'O Allah, I seek Your forgiveness and Your protection in this world and the next?O Allah, I seek Your forgiveness and Your protection in my religion, in my worldly affairs, in my family and in my wealth.
O Allah, conceal my secrets and preserve me from anguish,O Allah, guard me from what is in front of me and behind me, from my left, and from my right, and from above me. I seek refuge in Your Greatness from being struck down from beneath me.');
INSERT INTO shc_portal.shc_supplication_lk (id, code, lang, label)
VALUES (18, 'MORNING_SUPPLICATION_2', 'tr', 'O Allah, I seek Your forgiveness and Your protection in this world and the next?O Allah, I seek Your forgiveness and Your protection in my religion, in my worldly affairs, in my family and in my wealth.
O Allah, conceal my secrets and preserve me from anguish,O Allah, guard me from what is in front of me and behind me, from my left, and from my right, and from above me. I seek refuge in Your Greatness from being struck down from beneath me.');
INSERT INTO shc_portal.shc_supplication_lk (id, code, lang, label)
VALUES (19, 'MORNING_SUPPLICATION_2', 'ms', 'O Allah, I seek Your forgiveness and Your protection in this world and the next?O Allah, I seek Your forgiveness and Your protection in my religion, in my worldly affairs, in my family and in my wealth.
O Allah, conceal my secrets and preserve me from anguish,O Allah, guard me from what is in front of me and behind me, from my left, and from my right, and from above me. I seek refuge in Your Greatness from being struck down from beneath me.');
INSERT INTO shc_portal.shc_supplication_lk (id, code, lang, label)
VALUES (20, 'MORNING_SUPPLICATION_2', 'fa', 'O Allah, I seek Your forgiveness and Your protection in this world and the next?O Allah, I seek Your forgiveness and Your protection in my religion, in my worldly affairs, in my family and in my wealth.
O Allah, conceal my secrets and preserve me from anguish,O Allah, guard me from what is in front of me and behind me, from my left, and from my right, and from above me. I seek refuge in Your Greatness from being struck down from beneath me.');

INSERT INTO shc_portal.shc_supplication_lk (id, code, lang, label)
VALUES (21, 'MORNING_SUPPLICATION_3', 'fr', 'We have reached the morning and at this very time unto Allah belongs all sovereignty, and all praise is for Allah. None has the right to be worshipped except Allah, alone, without partner, to Him belongs all sovereignty and praise and He is over all things omnipotent. My Lord, I ask You for the good of this day and the good of what follows it and I take refuge in You from the evil of this day and the evil of what follows it. My Lord, I take refuge in You from laziness and senility. My Lord, I take refuge in You from torment in the Fire and punishment in the grave.');
INSERT INTO shc_portal.shc_supplication_lk (id, code, lang, label)
VALUES (22, 'MORNING_SUPPLICATION_3', 'tr', 'We have reached the morning and at this very time unto Allah belongs all sovereignty, and all praise is for Allah. None has the right to be worshipped except Allah, alone, without partner, to Him belongs all sovereignty and praise and He is over all things omnipotent. My Lord, I ask You for the good of this day and the good of what follows it and I take refuge in You from the evil of this day and the evil of what follows it. My Lord, I take refuge in You from laziness and senility. My Lord, I take refuge in You from torment in the Fire and punishment in the grave.');
INSERT INTO shc_portal.shc_supplication_lk (id, code, lang, label)
VALUES (23, 'MORNING_SUPPLICATION_3', 'ms', 'We have reached the morning and at this very time unto Allah belongs all sovereignty, and all praise is for Allah. None has the right to be worshipped except Allah, alone, without partner, to Him belongs all sovereignty and praise and He is over all things omnipotent. My Lord, I ask You for the good of this day and the good of what follows it and I take refuge in You from the evil of this day and the evil of what follows it. My Lord, I take refuge in You from laziness and senility. My Lord, I take refuge in You from torment in the Fire and punishment in the grave.');
INSERT INTO shc_portal.shc_supplication_lk (id, code, lang, label)
VALUES (24, 'MORNING_SUPPLICATION_3', 'fa', 'We have reached the morning and at this very time unto Allah belongs all sovereignty, and all praise is for Allah. None has the right to be worshipped except Allah, alone, without partner, to Him belongs all sovereignty and praise and He is over all things omnipotent. My Lord, I ask You for the good of this day and the good of what follows it and I take refuge in You from the evil of this day and the evil of what follows it. My Lord, I take refuge in You from laziness and senility. My Lord, I take refuge in You from torment in the Fire and punishment in the grave.');

INSERT INTO shc_portal.shc_supplication_lk (id, code, lang, label)
VALUES (25, 'EVENING_SUPPLICATION_1', 'fr', 'We have reached the evening and at this very time unto Allah belongs all sovereignty, and all praise is for Allah. None has the right to be worshipped except Allah, alone, without partner, to Him belongs all sovereignty and praise and He is over all things omnipotent. My Lord, I ask You for the good of this night and the good of what follows it and I take refuge in You from the evil of this night and the evil of what follows it. My Lord, I take refuge in You from laziness and senility. My Lord, I take refuge in You from torment in the Fire and punishment in the grave.');
INSERT INTO shc_portal.shc_supplication_lk (id, code, lang, label)
VALUES (26, 'EVENING_SUPPLICATION_1', 'tr', 'We have reached the evening and at this very time unto Allah belongs all sovereignty, and all praise is for Allah. None has the right to be worshipped except Allah, alone, without partner, to Him belongs all sovereignty and praise and He is over all things omnipotent. My Lord, I ask You for the good of this night and the good of what follows it and I take refuge in You from the evil of this night and the evil of what follows it. My Lord, I take refuge in You from laziness and senility. My Lord, I take refuge in You from torment in the Fire and punishment in the grave.');
INSERT INTO shc_portal.shc_supplication_lk (id, code, lang, label)
VALUES (27, 'EVENING_SUPPLICATION_1', 'ms', 'We have reached the evening and at this very time unto Allah belongs all sovereignty, and all praise is for Allah. None has the right to be worshipped except Allah, alone, without partner, to Him belongs all sovereignty and praise and He is over all things omnipotent. My Lord, I ask You for the good of this night and the good of what follows it and I take refuge in You from the evil of this night and the evil of what follows it. My Lord, I take refuge in You from laziness and senility. My Lord, I take refuge in You from torment in the Fire and punishment in the grave.');
INSERT INTO shc_portal.shc_supplication_lk (id, code, lang, label)
VALUES (28, 'EVENING_SUPPLICATION_1', 'fa', 'We have reached the evening and at this very time unto Allah belongs all sovereignty, and all praise is for Allah. None has the right to be worshipped except Allah, alone, without partner, to Him belongs all sovereignty and praise and He is over all things omnipotent. My Lord, I ask You for the good of this night and the good of what follows it and I take refuge in You from the evil of this night and the evil of what follows it. My Lord, I take refuge in You from laziness and senility. My Lord, I take refuge in You from torment in the Fire and punishment in the grave.');

INSERT INTO shc_portal.shc_supplication_lk (id, code, lang, label)
VALUES (29, 'EVENING_SUPPLICATION_2', 'fr', 'Allah! There is no ilah (deity worthy of worship) but He The Living, The Eternal One. Neither slumber, nor sleep overtakes Him. To Him belonges whatever is in the Heavens and on the earth. Who is he that can intercede with Him but by His permission? He knows what is before and what is behind them. They encompass nothing of His knowledge which He will. His Throne extends over the Heavens and the Earth, and the preservation of both does not weary Him. He is the Exalted The Immense One.');
INSERT INTO shc_portal.shc_supplication_lk (id, code, lang, label)
VALUES (30, 'EVENING_SUPPLICATION_2', 'tr', 'Allah! There is no ilah (deity worthy of worship) but He The Living, The Eternal One. Neither slumber, nor sleep overtakes Him. To Him belonges whatever is in the Heavens and on the earth. Who is he that can intercede with Him but by His permission? He knows what is before and what is behind them. They encompass nothing of His knowledge which He will. His Throne extends over the Heavens and the Earth, and the preservation of both does not weary Him. He is the Exalted The Immense One.');
INSERT INTO shc_portal.shc_supplication_lk (id, code, lang, label)
VALUES (31, 'EVENING_SUPPLICATION_2', 'ms', 'Allah! There is no ilah (deity worthy of worship) but He The Living, The Eternal One. Neither slumber, nor sleep overtakes Him. To Him belonges whatever is in the Heavens and on the earth. Who is he that can intercede with Him but by His permission? He knows what is before and what is behind them. They encompass nothing of His knowledge which He will. His Throne extends over the Heavens and the Earth, and the preservation of both does not weary Him. He is the Exalted The Immense One.');
INSERT INTO shc_portal.shc_supplication_lk (id, code, lang, label)
VALUES (32, 'EVENING_SUPPLICATION_2', 'fa', 'Allah! There is no ilah (deity worthy of worship) but He The Living, The Eternal One. Neither slumber, nor sleep overtakes Him. To Him belonges whatever is in the Heavens and on the earth. Who is he that can intercede with Him but by His permission? He knows what is before and what is behind them. They encompass nothing of His knowledge which He will. His Throne extends over the Heavens and the Earth, and the preservation of both does not weary Him. He is the Exalted The Immense One.');

INSERT INTO shc_portal.shc_supplication_lk (id, code, lang, label)
VALUES (33, 'EVENING_SUPPLICATION_3', 'fr', 'He is Allah, who is One. Allah, the Perfect Self-Sufficient Master. He neither begets nor was He begotten, nor is there to Him any equivalent.');
INSERT INTO shc_portal.shc_supplication_lk (id, code, lang, label)
VALUES (34, 'EVENING_SUPPLICATION_3', 'tr', 'He is Allah, who is One. Allah, the Perfect Self-Sufficient Master. He neither begets nor was He begotten, nor is there to Him any equivalent.');
INSERT INTO shc_portal.shc_supplication_lk (id, code, lang, label)
VALUES (35, 'EVENING_SUPPLICATION_3', 'ms', 'He is Allah, who is One. Allah, the Perfect Self-Sufficient Master. He neither begets nor was He begotten, nor is there to Him any equivalent.');
INSERT INTO shc_portal.shc_supplication_lk (id, code, lang, label)
VALUES (36, 'EVENING_SUPPLICATION_3', 'fa', 'He is Allah, who is One. Allah, the Perfect Self-Sufficient Master. He neither begets nor was He begotten, nor is there to Him any equivalent.');

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

