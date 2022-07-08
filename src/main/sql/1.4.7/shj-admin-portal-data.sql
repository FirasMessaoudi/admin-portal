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

INSERT INTO shc_portal.shc_supplication_lk(id,code,lang,label,type,counter) VALUES(13,N'MORNING_SUPPLICATION_1',N'fr',N'Allah! There is no ilah (deity worthy of worship) but He The Living, The Eternal One. Neither slumber, nor sleep overtakes Him. To Him belongs whatever is in the Heavens and on the earth. Who is he that can intercede with Him but by His permission? He knows what is before and what is behind them. They encompass nothing of His knowledge which He will. His Throne extends over the Heavens and the Earth, and the preservation of both does not weary Him. He is the Exalted The Immense One.',N'Morning Supplications',3);
INSERT INTO shc_portal.shc_supplication_lk(id,code,lang,label,type,counter) VALUES(14,N'MORNING_SUPPLICATION_1',N'tr',N'Allah! There is no ilah (deity worthy of worship) but He The Living, The Eternal One. Neither slumber, nor sleep overtakes Him. To Him belongs whatever is in the Heavens and on the earth. Who is he that can intercede with Him but by His permission? He knows what is before and what is behind them. They encompass nothing of His knowledge which He will. His Throne extends over the Heavens and the Earth, and the preservation of both does not weary Him. He is the Exalted The Immense One.',N'Morning Supplications',3);
INSERT INTO shc_portal.shc_supplication_lk(id,code,lang,label,type,counter) VALUES(15,N'MORNING_SUPPLICATION_1',N'ms',N'Allah! There is no ilah (deity worthy of worship) but He The Living, The Eternal One. Neither slumber, nor sleep overtakes Him. To Him belongs whatever is in the Heavens and on the earth. Who is he that can intercede with Him but by His permission? He knows what is before and what is behind them. They encompass nothing of His knowledge which He will. His Throne extends over the Heavens and the Earth, and the preservation of both does not weary Him. He is the Exalted The Immense One.',N'Morning Supplications',3);
INSERT INTO shc_portal.shc_supplication_lk(id,code,lang,label,type,counter) VALUES(16,N'MORNING_SUPPLICATION_1',N'fa',N'Allah! There is no ilah (deity worthy of worship) but He The Living, The Eternal One. Neither slumber, nor sleep overtakes Him. To Him belongs whatever is in the Heavens and on the earth. Who is he that can intercede with Him but by His permission? He knows what is before and what is behind them. They encompass nothing of His knowledge which He will. His Throne extends over the Heavens and the Earth, and the preservation of both does not weary Him. He is the Exalted The Immense One.',N'Morning Supplications',3);

INSERT INTO shc_portal.shc_supplication_lk(id,code,lang,label,type,counter) VALUES(17,N'MORNING_SUPPLICATION_2',N'fr',N'O Allah, I seek Your forgiveness and Your protection in this world and the next?O Allah, I seek Your forgiveness and Your protection in my religion, in my worldly affairs, in my family and in my wealth.
O Allah, conceal my secrets and preserve me from anguish,O Allah, guard me from what is in front of me and behind me, from my left, and from my right, and from above me. I seek refuge in Your Greatness from being struck down from beneath me.',N'Morning Supplications',1);
INSERT INTO shc_portal.shc_supplication_lk(id,code,lang,label,type,counter) VALUES(18,N'MORNING_SUPPLICATION_2',N'tr',N'O Allah, I seek Your forgiveness and Your protection in this world and the next?O Allah, I seek Your forgiveness and Your protection in my religion, in my worldly affairs, in my family and in my wealth.
O Allah, conceal my secrets and preserve me from anguish,O Allah, guard me from what is in front of me and behind me, from my left, and from my right, and from above me. I seek refuge in Your Greatness from being struck down from beneath me.',N'Morning Supplications',1);
INSERT INTO shc_portal.shc_supplication_lk(id,code,lang,label,type,counter) VALUES(19,N'MORNING_SUPPLICATION_2',N'ms',N'O Allah, I seek Your forgiveness and Your protection in this world and the next?O Allah, I seek Your forgiveness and Your protection in my religion, in my worldly affairs, in my family and in my wealth.
O Allah, conceal my secrets and preserve me from anguish,O Allah, guard me from what is in front of me and behind me, from my left, and from my right, and from above me. I seek refuge in Your Greatness from being struck down from beneath me.',N'Morning Supplications',1);
INSERT INTO shc_portal.shc_supplication_lk(id,code,lang,label,type,counter) VALUES(20,N'MORNING_SUPPLICATION_2',N'fa',N'O Allah, I seek Your forgiveness and Your protection in this world and the next?O Allah, I seek Your forgiveness and Your protection in my religion, in my worldly affairs, in my family and in my wealth.
O Allah, conceal my secrets and preserve me from anguish,O Allah, guard me from what is in front of me and behind me, from my left, and from my right, and from above me. I seek refuge in Your Greatness from being struck down from beneath me.',N'Morning Supplications',1);

INSERT INTO shc_portal.shc_supplication_lk(id,code,lang,label,type,counter) VALUES(21,N'MORNING_SUPPLICATION_3',N'fr',N'We have reached the morning and at this very time unto Allah belongs all sovereignty, and all praise is for Allah. None has the right to be worshipped except Allah, alone, without partner, to Him belongs all sovereignty and praise and He is over all things omnipotent. My Lord, I ask You for the good of this day and the good of what follows it and I take refuge in You from the evil of this day and the evil of what follows it. My Lord, I take refuge in You from laziness and senility. My Lord, I take refuge in You from torment in the Fire and punishment in the grave.',N'Morning Supplications',1);
INSERT INTO shc_portal.shc_supplication_lk(id,code,lang,label,type,counter) VALUES(22,N'MORNING_SUPPLICATION_3',N'tr',N'We have reached the morning and at this very time unto Allah belongs all sovereignty, and all praise is for Allah. None has the right to be worshipped except Allah, alone, without partner, to Him belongs all sovereignty and praise and He is over all things omnipotent. My Lord, I ask You for the good of this day and the good of what follows it and I take refuge in You from the evil of this day and the evil of what follows it. My Lord, I take refuge in You from laziness and senility. My Lord, I take refuge in You from torment in the Fire and punishment in the grave.',N'Morning Supplications',1);
INSERT INTO shc_portal.shc_supplication_lk(id,code,lang,label,type,counter) VALUES(23,N'MORNING_SUPPLICATION_3',N'ms',N'We have reached the morning and at this very time unto Allah belongs all sovereignty, and all praise is for Allah. None has the right to be worshipped except Allah, alone, without partner, to Him belongs all sovereignty and praise and He is over all things omnipotent. My Lord, I ask You for the good of this day and the good of what follows it and I take refuge in You from the evil of this day and the evil of what follows it. My Lord, I take refuge in You from laziness and senility. My Lord, I take refuge in You from torment in the Fire and punishment in the grave.',N'Morning Supplications',1);
INSERT INTO shc_portal.shc_supplication_lk(id,code,lang,label,type,counter) VALUES(24,N'MORNING_SUPPLICATION_3',N'fa',N'We have reached the morning and at this very time unto Allah belongs all sovereignty, and all praise is for Allah. None has the right to be worshipped except Allah, alone, without partner, to Him belongs all sovereignty and praise and He is over all things omnipotent. My Lord, I ask You for the good of this day and the good of what follows it and I take refuge in You from the evil of this day and the evil of what follows it. My Lord, I take refuge in You from laziness and senility. My Lord, I take refuge in You from torment in the Fire and punishment in the grave.',N'Morning Supplications',1);

INSERT INTO shc_portal.shc_supplication_lk(id,code,lang,label,type,counter) VALUES(25,N'EVENING_SUPPLICATION_1',N'fr',N'We have reached the evening and at this very time unto Allah belongs all sovereignty, and all praise is for Allah. None has the right to be worshipped except Allah, alone, without partner, to Him belongs all sovereignty and praise and He is over all things omnipotent. My Lord, I ask You for the good of this night and the good of what follows it and I take refuge in You from the evil of this night and the evil of what follows it. My Lord, I take refuge in You from laziness and senility. My Lord, I take refuge in You from torment in the Fire and punishment in the grave.',N'Evening Supplications',3);
INSERT INTO shc_portal.shc_supplication_lk(id,code,lang,label,type,counter) VALUES(26,N'EVENING_SUPPLICATION_1',N'tr',N'We have reached the evening and at this very time unto Allah belongs all sovereignty, and all praise is for Allah. None has the right to be worshipped except Allah, alone, without partner, to Him belongs all sovereignty and praise and He is over all things omnipotent. My Lord, I ask You for the good of this night and the good of what follows it and I take refuge in You from the evil of this night and the evil of what follows it. My Lord, I take refuge in You from laziness and senility. My Lord, I take refuge in You from torment in the Fire and punishment in the grave.',N'Evening Supplications',3);
INSERT INTO shc_portal.shc_supplication_lk(id,code,lang,label,type,counter) VALUES(27,N'EVENING_SUPPLICATION_1',N'ms',N'We have reached the evening and at this very time unto Allah belongs all sovereignty, and all praise is for Allah. None has the right to be worshipped except Allah, alone, without partner, to Him belongs all sovereignty and praise and He is over all things omnipotent. My Lord, I ask You for the good of this night and the good of what follows it and I take refuge in You from the evil of this night and the evil of what follows it. My Lord, I take refuge in You from laziness and senility. My Lord, I take refuge in You from torment in the Fire and punishment in the grave.',N'Evening Supplications',3);
INSERT INTO shc_portal.shc_supplication_lk(id,code,lang,label,type,counter) VALUES(28,N'EVENING_SUPPLICATION_1',N'fa',N'We have reached the evening and at this very time unto Allah belongs all sovereignty, and all praise is for Allah. None has the right to be worshipped except Allah, alone, without partner, to Him belongs all sovereignty and praise and He is over all things omnipotent. My Lord, I ask You for the good of this night and the good of what follows it and I take refuge in You from the evil of this night and the evil of what follows it. My Lord, I take refuge in You from laziness and senility. My Lord, I take refuge in You from torment in the Fire and punishment in the grave.',N'Evening Supplications',3);

INSERT INTO shc_portal.shc_supplication_lk(id,code,lang,label,type,counter) VALUES(29,N'EVENING_SUPPLICATION_2',N'fr',N'Allah! There is no ilah (deity worthy of worship) but He The Living, The Eternal One. Neither slumber, nor sleep overtakes Him. To Him belonges whatever is in the Heavens and on the earth. Who is he that can intercede with Him but by His permission? He knows what is before and what is behind them. They encompass nothing of His knowledge which He will. His Throne extends over the Heavens and the Earth, and the preservation of both does not weary Him. He is the Exalted The Immense One.',N'Evening Supplications',3);
INSERT INTO shc_portal.shc_supplication_lk(id,code,lang,label,type,counter) VALUES(30,N'EVENING_SUPPLICATION_2',N'tr',N'Allah! There is no ilah (deity worthy of worship) but He The Living, The Eternal One. Neither slumber, nor sleep overtakes Him. To Him belonges whatever is in the Heavens and on the earth. Who is he that can intercede with Him but by His permission? He knows what is before and what is behind them. They encompass nothing of His knowledge which He will. His Throne extends over the Heavens and the Earth, and the preservation of both does not weary Him. He is the Exalted The Immense One.',N'Evening Supplications',3);
INSERT INTO shc_portal.shc_supplication_lk(id,code,lang,label,type,counter) VALUES(31,N'EVENING_SUPPLICATION_2',N'ms',N'Allah! There is no ilah (deity worthy of worship) but He The Living, The Eternal One. Neither slumber, nor sleep overtakes Him. To Him belonges whatever is in the Heavens and on the earth. Who is he that can intercede with Him but by His permission? He knows what is before and what is behind them. They encompass nothing of His knowledge which He will. His Throne extends over the Heavens and the Earth, and the preservation of both does not weary Him. He is the Exalted The Immense One.',N'Evening Supplications',3);
INSERT INTO shc_portal.shc_supplication_lk(id,code,lang,label,type,counter) VALUES(32,N'EVENING_SUPPLICATION_2',N'fa',N'Allah! There is no ilah (deity worthy of worship) but He The Living, The Eternal One. Neither slumber, nor sleep overtakes Him. To Him belonges whatever is in the Heavens and on the earth. Who is he that can intercede with Him but by His permission? He knows what is before and what is behind them. They encompass nothing of His knowledge which He will. His Throne extends over the Heavens and the Earth, and the preservation of both does not weary Him. He is the Exalted The Immense One.',N'Evening Supplications',3);

INSERT INTO shc_portal.shc_supplication_lk(id,code,lang,label,type,counter) VALUES(33,N'EVENING_SUPPLICATION_3',N'fr',N'He is Allah, who is One. Allah, the Perfect Self-Sufficient Master. He neither begets nor was He begotten, nor is there to Him any equivalent.',N'Evening Supplications',3);
INSERT INTO shc_portal.shc_supplication_lk(id,code,lang,label,type,counter) VALUES(34,N'EVENING_SUPPLICATION_3',N'tr',N'He is Allah, who is One. Allah, the Perfect Self-Sufficient Master. He neither begets nor was He begotten, nor is there to Him any equivalent.',N'Evening Supplications',3);
INSERT INTO shc_portal.shc_supplication_lk(id,code,lang,label,type,counter) VALUES(35,N'EVENING_SUPPLICATION_3',N'ms',N'He is Allah, who is One. Allah, the Perfect Self-Sufficient Master. He neither begets nor was He begotten, nor is there to Him any equivalent.',N'Evening Supplications',3);
INSERT INTO shc_portal.shc_supplication_lk(id,code,lang,label,type,counter) VALUES(36,N'EVENING_SUPPLICATION_3',N'fa',N'He is Allah, who is One. Allah, the Perfect Self-Sufficient Master. He neither begets nor was He begotten, nor is there to Him any equivalent.',N'Evening Supplications',3);

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


SET IDENTITY_INSERT shc_portal.shc_applicant_digital_id_status_lk ON;

INSERT INTO shc_portal.shc_portal.shc_applicant_digital_id_status_lk (id, code, lang, label) VALUES (5, 'VALID', 'fr', 'Active');
INSERT INTO shc_portal.shc_portal.shc_applicant_digital_id_status_lk (id, code, lang, label) VALUES (6, 'INVALID', 'fr', 'Invalid');

INSERT INTO shc_portal.shc_portal.shc_applicant_digital_id_status_lk (id, code, lang, label) VALUES (7, 'VALID', 'tr', 'Active');
INSERT INTO shc_portal.shc_portal.shc_applicant_digital_id_status_lk (id, code, lang, label) VALUES (8, 'INVALID', 'tr', 'Invalid');

INSERT INTO shc_portal.shc_portal.shc_applicant_digital_id_status_lk (id, code, lang, label) VALUES (9, 'VALID', 'fa', 'Active');
INSERT INTO shc_portal.shc_portal.shc_applicant_digital_id_status_lk (id, code, lang, label) VALUES (10, 'INVALID', 'fa', 'Invalid');

INSERT INTO shc_portal.shc_portal.shc_applicant_digital_id_status_lk (id, code, lang, label) VALUES (11, 'VALID', 'ms', 'Active');
INSERT INTO shc_portal.shc_portal.shc_applicant_digital_id_status_lk (id, code, lang, label) VALUES (12, 'INVALID', 'ms', 'Invalid');
SET IDENTITY_INSERT shc_portal.shc_applicant_digital_id_status_lk OFF;

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

SET IDENTITY_INSERT shc_portal.shc_card_status_lk ON;

INSERT INTO shc_portal.shc_card_status_lk (id, code, lang, label) VALUES (21, 'READY_TO_PRINT', 'fr', 'Ready to Print');
INSERT INTO shc_portal.shc_card_status_lk (id, code, lang, label) VALUES (22, 'SENT_FOR_PRINT', 'fr', 'Sent for Print');
INSERT INTO shc_portal.shc_card_status_lk (id, code, lang, label) VALUES (23, 'PRINTED', 'fr', 'Printed');
INSERT INTO shc_portal.shc_card_status_lk (id, code, lang, label) VALUES (24, 'DISTRIBUTED', 'fr', 'Distributed');
INSERT INTO shc_portal.shc_card_status_lk (id, code, lang, label) VALUES (25, 'ACTIVE', 'fr', 'Active');
INSERT INTO shc_portal.shc_card_status_lk (id, code, lang, label) VALUES (26, 'SUSPENDED', 'fr', 'Suspended');
INSERT INTO shc_portal.shc_card_status_lk (id, code, lang, label) VALUES (27, 'CANCELLED', 'fr', 'Cancelled');
INSERT INTO shc_portal.shc_card_status_lk (id, code, lang, label) VALUES (28, 'WAITING_TO_SEND', 'fr', 'Waiting to Send');
INSERT INTO shc_portal.shc_portal.shc_card_status_lk (id, code, lang, label) VALUES (29, 'EXPIRED', 'fr', 'Expired');
INSERT INTO shc_portal.shc_portal.shc_card_status_lk (id, code, lang, label) VALUES (30, 'REISSUED', 'fr', 'Reissued');

INSERT INTO shc_portal.shc_card_status_lk (id, code, lang, label) VALUES (31, 'READY_TO_PRINT', 'tr', 'Ready to Print');
INSERT INTO shc_portal.shc_card_status_lk (id, code, lang, label) VALUES (32, 'SENT_FOR_PRINT', 'tr', 'Sent for Print');
INSERT INTO shc_portal.shc_card_status_lk (id, code, lang, label) VALUES (33, 'PRINTED', 'tr', 'Printed');
INSERT INTO shc_portal.shc_card_status_lk (id, code, lang, label) VALUES (34, 'DISTRIBUTED', 'tr', 'Distributed');
INSERT INTO shc_portal.shc_card_status_lk (id, code, lang, label) VALUES (35, 'ACTIVE', 'tr', 'Active');
INSERT INTO shc_portal.shc_card_status_lk (id, code, lang, label) VALUES (36, 'SUSPENDED', 'tr', 'Suspended');
INSERT INTO shc_portal.shc_card_status_lk (id, code, lang, label) VALUES (37, 'CANCELLED', 'tr', 'Cancelled');
INSERT INTO shc_portal.shc_card_status_lk (id, code, lang, label) VALUES (38, 'WAITING_TO_SEND', 'tr', 'Waiting to Send');
INSERT INTO shc_portal.shc_portal.shc_card_status_lk (id, code, lang, label) VALUES (39, 'EXPIRED', 'tr', 'Expired');
INSERT INTO shc_portal.shc_portal.shc_card_status_lk (id, code, lang, label) VALUES (40, 'REISSUED', 'tr', 'Reissued');

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
