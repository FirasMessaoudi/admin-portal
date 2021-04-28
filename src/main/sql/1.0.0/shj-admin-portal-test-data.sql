USE shj_portal
GO
-- insert applicant
SET IDENTITY_INSERT shj_portal.sha_applicant ON;
INSERT INTO shj_portal.sha_applicant (id, gender, nationality_code, id_number, id_number_original, passport_number,
                                      date_of_birth_gregorian, date_of_birth_hijri, full_name_ar, full_name_en,
                                      full_name_origin, marital_status, status)
VALUES (1, 'M', 'SAUDI', 2541235487, '9184542873', '7O8512', convert(date, '05/11/1981', 103), 14400505,
        N'عبد الله محمد عبد العزيز القحطاني', 'AbdAllah Moh AbdAlaziz AlQahtani', NULL, 1, 1);
SET IDENTITY_INSERT shj_portal.sha_applicant OFF;
GO

-- insert applicant contact
INSERT INTO shj_portal.sha_applicant_contact (applicant_id, language_list, email, local_mobile_number,
                                              intl_mobile_number, country_code, street_name, district_name, city_name,
                                              building_number, postal_code)
VALUES (1, N'العربية، English', 'app@elm.sa', 558572465, 962796322285, 'MOROCCO', N'شارع الأمير بندر', N'الغدير',
        N'مدغشقر', 325, 32145);
GO

-- insert applicant relatives
INSERT INTO shj_portal.sha_applicant_relative (relationship_code, applicant_id, applicant_relative_id)
VALUES ('MOTHER', 1, 2);
INSERT INTO shj_portal.sha_applicant_relative (relationship_code, applicant_id, applicant_relative_id)
VALUES ('WIFE', 1, 3);
GO

-- insert applicant digital id
INSERT INTO shj_portal.sha_applicant_digital_id (uin, applicant_id)
VALUES ('521468547', 1);
GO

-- insert applicant ritual
SET IDENTITY_INSERT shj_portal.sha_applicant_ritual ON;
INSERT INTO shj_portal.sha_applicant_ritual (id, applicant_id, hamlah_package_code, hijri_season, date_start_gregorian,
                                             date_end_gregorian, date_start_hijri, date_end_hijri, type_code,
                                             visa_number, permit_number, insurance_number, border_number)
VALUES (1, 1, NULL, 1442, convert(date, '14/07/2021', 103), convert(date, '03/09/2021', 103), 01101442, 15121442,
        'INTERNAL_HAJJ', NULL, '32458', '5214587', '687445');
SET IDENTITY_INSERT shj_portal.sha_applicant_ritual OFF;
GO

-- insert applicant health
SET IDENTITY_INSERT shj_portal.sha_applicant_health ON;
INSERT INTO shj_portal.sha_applicant_health (id, applicant_id, blood_type)
VALUES (1, 1, 'AB+');
SET IDENTITY_INSERT shj_portal.sha_applicant_health OFF;
GO

-- insert applicant health immunization
INSERT INTO shj_portal.sha_applicant_health_immunization (applicant_health_id, immunization_code, immunization_date, mandatory)
VALUES (1, 'COVID-19', convert(date, '03/06/2021', 103), 1);
INSERT INTO shj_portal.sha_applicant_health_immunization (applicant_health_id, immunization_code, immunization_date, mandatory)
VALUES (1, 'MENINGITIS', convert(date, '27/05/2021', 103), 0);
GO

-- insert applicant health disease
INSERT INTO shj_portal.sha_applicant_health_disease (applicant_health_id, disease_name_ar, disease_name_en)
VALUES (1, N'ضغط الدم', 'Hypertension');
GO

-- insert applicant health special needs
INSERT INTO shj_portal.sha_applicant_health_special_needs (applicant_health_id, special_need_type_code)
VALUES (1, 'WHEELCHAIR');
GO

-- insert applicant card batch
SET IDENTITY_INSERT shj_portal.sha_card_batch ON;
INSERT INTO shj_portal.sha_card_batch (id, number) VALUES (1, '317');
SET IDENTITY_INSERT shj_portal.sha_card_batch OFF;
GO

-- insert applicant card
INSERT INTO shj_portal.sha_applicant_card (applicant_ritual_id, reference_number, batch_id, status_code)
VALUES (1, '362145', 1, 'SENT_FOR_PRINT');
GO
