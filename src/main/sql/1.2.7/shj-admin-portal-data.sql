USE shc_portal
GO

DELETE FROM shc_portal.shc_survey_question_lk WHERE id > 0;
GO
SET IDENTITY_INSERT shc_portal.shc_survey_question_lk ON;
INSERT INTO shc_portal.shc_portal.shc_survey_question_lk (id, code, survey_type_code, lang, label, question_index) VALUES (1, 'WELCOME_SATISFACTION_Q_DAILY', 'DAILY', 'ar', N'مدى رضاك عن الترحيب والاستقبال', 1);
INSERT INTO shc_portal.shc_portal.shc_survey_question_lk (id, code, survey_type_code, lang, label, question_index) VALUES (2, 'WELCOME_SATISFACTION_Q_DAILY', 'DAILY', 'en', 'How satisfied are you with the welcoming and greeting?', 1);

INSERT INTO shc_portal.shc_portal.shc_survey_question_lk (id, code, survey_type_code, lang, label, question_index) VALUES (3, 'TRANS_SATISFACTION_Q_DAILY', 'DAILY', 'ar', N'مدى رضاك عن الحافلات', 2);
INSERT INTO shc_portal.shc_portal.shc_survey_question_lk (id, code, survey_type_code, lang, label, question_index) VALUES (4, 'TRANS_SATISFACTION_Q_DAILY', 'DAILY', 'en', 'How satisfied are you with the buses?', 2);

INSERT INTO shc_portal.shc_portal.shc_survey_question_lk (id, code, survey_type_code, lang, label, question_index) VALUES (5, 'TRANS_TIME_SATISFACTION_Q_DAILY', 'DAILY', 'ar', N'مدى رضاك عن الالتزام بمواعيد الحافلات', 3);
INSERT INTO shc_portal.shc_portal.shc_survey_question_lk (id, code, survey_type_code, lang, label, question_index) VALUES (6, 'TRANS_TIME_SATISFACTION_Q_DAILY', 'DAILY', 'en', 'How satisfied are you with the bus timings?', 3);

INSERT INTO shc_portal.shc_portal.shc_survey_question_lk (id, code, survey_type_code, lang, label, question_index) VALUES (7, 'FOOD_SATISFACTION_Q_DAILY', 'DAILY', 'ar', N'مدى رضاك عن جودة الوجبات المقدمة', 4);
INSERT INTO shc_portal.shc_portal.shc_survey_question_lk (id, code, survey_type_code, lang, label, question_index) VALUES (8, 'FOOD_SATISFACTION_Q_DAILY', 'DAILY', 'en', 'How satisfied are you with the quality of the meals served?', 4);

INSERT INTO shc_portal.shc_portal.shc_survey_question_lk (id, code, survey_type_code, lang, label, question_index) VALUES (9, 'FOOD_TIME_SATISFACTION_Q_DAILY', 'DAILY', 'ar', N'مدى رضاك عن الالتزام بمواعيد تقديم الوجبات', 5);
INSERT INTO shc_portal.shc_portal.shc_survey_question_lk (id, code, survey_type_code, lang, label, question_index) VALUES (10, 'FOOD_TIME_SATISFACTION_Q_DAILY', 'DAILY', 'en', N'How satisfied are you with serving meals on time?', 5);

INSERT INTO shc_portal.shc_portal.shc_survey_question_lk (id, code, survey_type_code, lang, label, question_index) VALUES (11, 'HOUSING_SATISFACTION_Q_DAILY', 'DAILY', 'ar', N'مدى رضاك عن السكن', 6);
INSERT INTO shc_portal.shc_portal.shc_survey_question_lk (id, code, survey_type_code, lang, label, question_index) VALUES (12, 'HOUSING_SATISFACTION_Q_DAILY', 'DAILY', 'en', 'How satisfied are you with housing?', 6);

INSERT INTO shc_portal.shc_portal.shc_survey_question_lk (id, code, survey_type_code, lang, label, question_index) VALUES (13, 'UTIL_SATISFACTION_Q_DAILY', 'DAILY', 'ar', N'مدى رضاك عن المرافق العامة', 7);
INSERT INTO shc_portal.shc_portal.shc_survey_question_lk (id, code, survey_type_code, lang, label, question_index) VALUES (14, 'UTIL_SATISFACTION_Q_DAILY', 'DAILY', 'en', 'How satisfied are you with public utilities?', 7);

INSERT INTO shc_portal.shc_portal.shc_survey_question_lk (id, code, survey_type_code, lang, label, question_index) VALUES (15, 'GUIDE_SATISFACTION_Q_DAILY', 'DAILY', 'ar', N'مدى رضاك عن خدمات الارشاد والتوجيه', 8);
INSERT INTO shc_portal.shc_portal.shc_survey_question_lk (id, code, survey_type_code, lang, label, question_index) VALUES (16, 'GUIDE_SATISFACTION_Q_DAILY', 'DAILY', 'en', 'How satisfied are you with the counseling and guidance services?', 8);

INSERT INTO shc_portal.shc_portal.shc_survey_question_lk (id, code, survey_type_code, lang, label, question_index) VALUES (17, 'WORKERS_SATISFACTION_Q_DAILY', 'DAILY', 'ar', N'رضاك عن تعامل العاملين في تقديم الخدمات', 9);
INSERT INTO shc_portal.shc_portal.shc_survey_question_lk (id, code, survey_type_code, lang, label, question_index) VALUES (18, 'WORKERS_SATISFACTION_Q_DAILY', 'DAILY', 'en', 'How satisfied are you with the quality of workers services?', 9);

INSERT INTO shc_portal.shc_portal.shc_survey_question_lk (id, code, survey_type_code, lang, label, question_index) VALUES (19, 'CLEANLINESS_SATISFACTION_Q_DAILY', 'DAILY', 'ar', N'مدى رضاك عن مستوى النظافة', 10);
INSERT INTO shc_portal.shc_portal.shc_survey_question_lk (id, code, survey_type_code, lang, label, question_index) VALUES (20, 'CLEANLINESS_SATISFACTION_Q_DAILY', 'DAILY', 'en', 'How satisfied are you with the level of cleanliness?', 10);
SET IDENTITY_INSERT shc_portal.shc_survey_question_lk OFF;
GO