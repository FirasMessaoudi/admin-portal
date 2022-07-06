USE shc_portal
GO

SET IDENTITY_INSERT shc_portal.shc_chatbot_item ON;

INSERT INTO shc_portal.shc_chatbot_item (id, code, lang, label) VALUES (58, 'HAJJ_RITUAL', 'en','About Hajj');
INSERT INTO shc_portal.shc_chatbot_item (id, code, lang, label) VALUES (59, 'LOCATIONS', 'en','Important locations');
INSERT INTO shc_portal.shc_chatbot_item (id, code, lang, label) VALUES (60, 'CONTACTS', 'en','Important contact numbers');
INSERT INTO shc_portal.shc_chatbot_item (id, code, lang, label) VALUES (61, 'SMART_CARD', 'en','Smart Card');
INSERT INTO shc_portal.shc_chatbot_item (id, code, lang, label) VALUES (62, 'SPECIAL_NEEDS', 'en','Special Needs');

INSERT INTO shc_portal.shc_chatbot_item (id, code, lang, label, parent_code) VALUES (63, 'SLAUGHTER_PROCESS','en','When can the slaughtering be confirmed? As when the person requesting the ritual is one of the pilgrims, so that the performance of his ritual is related to his leaving the state of Ihraam in Hajj or the sacrificer who is not a pilgrim?', 'HAJJ_RITUAL');
INSERT INTO shc_portal.shc_chatbot_item (id, code, lang, label, parent_code) VALUES (64, 'SLAUGHTER_PROCESS_RESPONSE','en', 'After the purchase and as soon as the amount is deducted from the customer’s account and its receipt in the Project account is confirmed, or the customer obtains the coupon or the ritual bond, the customer is thus considered to have entrusted the Project to perform the ritual on his behalf; and thus, he/she is discharged from performing the ritual, noting that in the event the purchase and payment were made through the website, a message will be sent informing the customer of the implementation of his/her request (the slaughtering completion).','SLAUGHTER_PROCESS');

INSERT INTO shc_portal.shc_chatbot_item (id, code, lang, label, parent_code) VALUES (65, 'ELECTRONIC_PATH', 'en', 'Does the E-track provide the purchase of Hady and Adahi (sacrificial animals)?', 'HAJJ_RITUAL');
INSERT INTO shc_portal.shc_chatbot_item (id, code, lang, label, parent_code) VALUES (66, 'ELECTRONIC_PATH_RESPONSE', 'en','Yes, this service is available.','ELECTRONIC_PATH');

INSERT INTO shc_portal.shc_chatbot_item (id, code, lang, label, parent_code) VALUES (67, 'HAJJ_APPLICATION', 'en','When does the application for Hajj Season 1443 AH start? ', 'HAJJ_RITUAL');
INSERT INTO shc_portal.shc_chatbot_item (id, code, lang, label, parent_code) VALUES (68, 'HAJJ_APPLICATION_RESPONSE', 'en','The announcement about the opening date of applying for 1443 Hajj has started through the website of the Ministry of Hajj and Umrah and its official channels on Friday 4/11/1443 AH.  ', 'HAJJ_APPLICATION');

INSERT INTO shc_portal.shc_chatbot_item (id, code, lang, label, parent_code) VALUES (69, 'HAJJ_AGE', 'en','What is the allowed age for performing Hajj?', 'HAJJ_RITUAL');
INSERT INTO shc_portal.shc_chatbot_item (id, code, lang, label, parent_code) VALUES (70, 'HAJJ_AGE_RESPONSE', 'en','There is no objection to accompanying children during 1443 Hajj, provided that they are vaccinated.', 'HAJJ_AGE');

INSERT INTO shc_portal.shc_chatbot_item (id, code, lang, label, parent_code) VALUES (71, 'INTERNAL_HAJJ', 'en','When does the application for internal Hajj campaigns begin and end?', 'HAJJ_RITUAL');
INSERT INTO shc_portal.shc_chatbot_item (id, code, lang, label, parent_code) VALUES (72, 'INTERNAL_HAJJ_RESPONSE', 'en','Applying starts on the 4th of Dhu Al-Qaedah and ends on 13th of Dhu Al-Qaedah.', 'INTERNAL_HAJJ');

INSERT INTO shc_portal.shc_chatbot_item (id, code, lang, label, parent_code) VALUES (73, 'HAJJ_INQUIRY', 'en','What is the mechanism of inquiring about Hajj eligibility?', 'HAJJ_RITUAL');
INSERT INTO shc_portal.shc_chatbot_item (id, code, lang, label, parent_code) VALUES (74, 'HAJJ_INQUIRY_RESPONSE', 'en','To inquire about Hajj eligibility, enter the Absher Platform, select e-inquiries, inquire about Hajj eligibility, then enter the ID Number and the visual code, and the eligibility will appear.', 'HAJJ_INQUIRY');

INSERT INTO shc_portal.shc_chatbot_item (id, code, lang, label, parent_code) VALUES (75, 'GULF_NATIONALS', 'en','GCC citizens who are in the Kingdom but are not residents and wish to perform domestic Hajj', 'HAJJ_RITUAL');
INSERT INTO shc_portal.shc_chatbot_item (id, code, lang, label, parent_code) VALUES (76, 'GULF_NATIONALS_RESPONSE', 'en','A GCC citizen who is a resident in the Kingdom can register in the E-track using the entry number that can be obtained from the Passports Department GCC ""*. A GCC citizen who is not a resident in the Kingdom cannot register in the E-track for domestic pilgrims, and he/she must register in his country according to its regulations.', 'GULF_NATIONALS');

INSERT INTO shc_portal.shc_chatbot_item (id, code, lang, label, parent_code) VALUES (77, 'MADINAH_CARE_CENTERS', 'en','Madinah Care Centers','LOCATIONS');
INSERT INTO shc_portal.shc_chatbot_item (id, code, lang, label, parent_code) VALUES (78, 'MADINAH_CARE_CENTERS_RESPONSE', 'en','East of the Prophet''s Mosque Center Address: King Faisal Road, 1st Ring Road, Al-Haram Working hours: 7 am - 9 pm Prince Mohammad bin Abdulaziz International Airport Address: Prince Mohammad bin Abdulaziz International Airport Working hours: 8 am - 10 pm','MADINAH_CARE_CENTERS');

INSERT INTO shc_portal.shc_chatbot_item (id, code, lang, label, parent_code) VALUES (79, 'JEDDAH_CARE_CENTERS', 'en','Jeddah Care Centers','LOCATIONS');
INSERT INTO shc_portal.shc_chatbot_item (id, code, lang, label, parent_code) VALUES (80, 'JEDDAH_CARE_CENTERS_RESPONSE', 'en','King Abdulaziz International Airport in Jeddah Address: King Abdulaziz International Airport Terminal No. 1','JEDDAH_CARE_CENTERS');

INSERT INTO shc_portal.shc_chatbot_item (id, code, lang, label, parent_code) VALUES (81, 'MAKKAH_CARE_CENTERS', 'en','Makkah Care Centers','LOCATIONS');
INSERT INTO shc_portal.shc_chatbot_item (id, code, lang, label, parent_code) VALUES (82, 'MAKKAH_CARE_CENTERS_RESPONSE', 'en','Al-Hujun Branch: Madinah Road, Al Hujun, Makkah. Working hours: 8 am - 10 pm Location link on Google Maps: https://maps.app.goo.gl/NE1G4t6S6WvNkDVb8 Misfalah Branch: Address: Ibrahim Al Khalil Street, Misfalah, Makkah. Working hours: 8 am - 10 pm Location link on Google Maps: https://maps.app.goo.gl/NGCnEg6hCp9JTT238 Dar Al-Tawhid Hotel Branch / Makkah Location link on Google Maps: https://maps.app.goo.gl/3c9iDmVRL2Zqk3n57','MAKKAH_CARE_CENTERS');

INSERT INTO shc_portal.shc_chatbot_item (id, code, lang, label, parent_code) VALUES (83, 'HARAM', 'en','Al-Haram','LOCATIONS');
INSERT INTO shc_portal.shc_chatbot_item (id, code, lang, label, parent_code) VALUES (84, 'HARAM_RESPONSE', 'en', 'https://goo.gl/maps/P9L6JX9VCNgcuxR3A','HARAM');

INSERT INTO shc_portal.shc_chatbot_item (id, code, lang, label, parent_code) VALUES (85, 'MERCY_MOUNT', 'en','Rahma Mountain','LOCATIONS');
INSERT INTO shc_portal.shc_chatbot_item (id, code, lang, label, parent_code) VALUES (86, 'MERCY_MOUNT_RESPONSE', 'en', 'https://goo.gl/maps/TjMBJqj754QzJFg88','MERCY_MOUNT');

INSERT INTO shc_portal.shc_chatbot_item (id, code, lang, label, parent_code) VALUES (87, 'ARAFAT_MOUNT', 'en','Arafat Mountain','LOCATIONS');
INSERT INTO shc_portal.shc_chatbot_item (id, code, lang, label, parent_code) VALUES (88, 'ARAFAT_MOUNT_RESPONSE', 'en', 'https://goo.gl/maps/hGcK5D7uY2jcr6gP7','ARAFAT_MOUNT');

INSERT INTO shc_portal.shc_chatbot_item (id, code, lang, label, parent_code) VALUES (89, 'MASJID_NABAWI', 'en','The Prophet''s Mosque','LOCATIONS');
INSERT INTO shc_portal.shc_chatbot_item (id, code, lang, label, parent_code) VALUES (90, 'MASJID_NABAWI_RESPONSE', 'en', 'https://goo.gl/maps/SseRYc5NoZXXHxsh8','MASJID_NABAWI');

INSERT INTO shc_portal.shc_chatbot_item (id, code, lang, label, parent_code) VALUES (91, 'FATWA_NUMBERS', 'en','Fatwa Numbers','CONTACTS');
INSERT INTO shc_portal.shc_chatbot_item (id, code, lang, label, parent_code) VALUES (92, 'FATWA_NUMBERS_RESPONSE', 'en','Saudi Fatwa 24 hour number or Fatwa free Number Fatwa free 24 hour number: 8002451000 Unified Fatwa Number, Makkah: 8001222100 Makkah Fatwa Number: 8001222400 Holy Quran Radio Fatwa Number: 0114052999 Saudi Dar Al-Iftaa Number: 0114595555 MOHU Fatwa Number: 8002451000 WhatsApp Fatwa Number (1): 0501785536','FATWA_NUMBERS');

INSERT INTO shc_portal.shc_chatbot_item (id, code, lang, label, parent_code) VALUES (93, 'PRESIDENCY_MOSQUES_NUMBERS', 'en','General Presidency of Haramain','CONTACTS');
INSERT INTO shc_portal.shc_chatbot_item (id, code, lang, label, parent_code) VALUES (94, 'PRESIDENCY_MOSQUES_NUMBERS_RESPONSE', 'en','Contact free number 1966','PRESIDENCY_MOSQUES_NUMBERS');

INSERT INTO shc_portal.shc_chatbot_item (id, code, lang, label, parent_code) VALUES (95, 'HAJJ_MINISTRY', 'en',' Ministry of Hajj and Umrah','CONTACTS');
INSERT INTO shc_portal.shc_chatbot_item (id, code, lang, label, parent_code) VALUES (96, 'HAJJ_MINISTRY_RESPONSE', 'en','For contacting the Ministry of Hajj and Umrah, you can contact MOHU Customer Service Center at 920002814 or care@haj.gov.sa, or through Twitter official account @MOHU_Care','HAJJ_MINISTRY');

INSERT INTO shc_portal.shc_chatbot_item (id, code, lang, label, parent_code) VALUES (97, 'EMERGENCY_NUMBER', 'en','For emergencies and security services','CONTACTS');
INSERT INTO shc_portal.shc_chatbot_item (id, code, lang, label, parent_code) VALUES (98, 'EMERGENCY_NUMBER_RESPONSE', 'en', '911','EMERGENCY_NUMBER');

INSERT INTO shc_portal.shc_chatbot_item (id, code, lang, label, parent_code) VALUES (99, 'MEDICAL_ADVICES', 'en','For medical consulting and inquiries','CONTACTS');
INSERT INTO shc_portal.shc_chatbot_item (id, code, lang, label, parent_code) VALUES (100, 'MEDICAL_ADVICES_RESPONSE', 'en', '937','MEDICAL_ADVICES');

INSERT INTO shc_portal.shc_chatbot_item (id, code, lang, label, parent_code) VALUES (101, 'EVALUATION', 'en', 'Evaluation', 'SMART_CARD');
INSERT INTO shc_portal.shc_chatbot_item (id, code, lang, label, parent_code) VALUES (102, 'EVALUATION_RESPONSE', 'en','A daily review and evaluation at the end of the pilgrim’s journey can be selected from Services list, so please pay attention to fill it out to help us improve services','EVALUATION');

INSERT INTO shc_portal.shc_chatbot_item (id, code, lang, label, parent_code) VALUES (103, 'COMPLAINTS', 'en', 'Complaints', 'SMART_CARD');
INSERT INTO shc_portal.shc_chatbot_item (id, code, lang, label, parent_code) VALUES (104, 'COMPLAINTS_RESPONSE', 'en','The complaint is registered for the organizer, and after a lapse of a period without a resolution, the complaint is raised as a report. ','COMPLAINTS');

INSERT INTO shc_portal.shc_chatbot_item (id, code, lang, label, parent_code) VALUES (105, 'INCIDENTS', 'en', 'Reports', 'SMART_CARD');
INSERT INTO shc_portal.shc_chatbot_item (id, code, lang, label, parent_code) VALUES (106, 'INCIDENTS_RESPONSE', 'en','The report is registered for the organizer and submitted as a report to the official authorities for necessary action. ','INCIDENTS');

INSERT INTO shc_portal.shc_chatbot_item (id, code, lang, label, parent_code) VALUES (107, 'CARD_COLORS', 'en', 'Card colors', 'SMART_CARD');
INSERT INTO shc_portal.shc_chatbot_item (id, code, lang, label, parent_code) VALUES (108, 'CARD_COLORS_RESPONSE', 'en','Colors of the card is based on the Tawafa Establishment to which the Hajj is affiliated, and there is a distinctive color for domestic pilgrims.','CARD_COLORS');

INSERT INTO shc_portal.shc_chatbot_item (id, code, lang, label, parent_code) VALUES (109, 'WHEELCHAIRS_GATES', 'en', 'What are the gates specified for wheelchair users in Al-Haram?', 'SPECIAL_NEEDS');
INSERT INTO shc_portal.shc_chatbot_item (id, code, lang, label, parent_code) VALUES (110, 'WHEELCHAIRS_GATES_RESPONSE', 'en','You can contact the General Presidency of Haramain at 1966 in this regard.','WHEELCHAIRS_GATES');

INSERT INTO shc_portal.shc_chatbot_item (id, code, lang, label, parent_code) VALUES (111, 'HAJJ_CARTS', 'en', 'Are vehicles available inside Al-Haram?', 'SPECIAL_NEEDS');
INSERT INTO shc_portal.shc_chatbot_item (id, code, lang, label, parent_code) VALUES (112, 'HAJJ_CARTS_RESPONSE', 'en','The General Presidency of Haramain provides electric vehicles, which are rented through Tanaqol App, for performing Tawaaf or Sa’i in specific floors in Al-Haram . Please contact the Presidency of Haramain for any developments.','HAJJ_CARTS');

INSERT INTO shc_portal.shc_chatbot_item (id, code, lang, label, parent_code) VALUES (113, 'SEPCIAL_NEEDS_SERVICES', 'en', 'What services are available for persons with special needs?', 'SPECIAL_NEEDS');
INSERT INTO shc_portal.shc_chatbot_item (id, code, lang, label, parent_code) VALUES (114, 'SEPCIAL_NEEDS_SERVICES_RESPONSE', 'en','You can contact the General Presidency of Haramain in this regard.','SEPCIAL_NEEDS_SERVICES');

SET IDENTITY_INSERT shc_portal.shc_chatbot_item OFF;
GO
