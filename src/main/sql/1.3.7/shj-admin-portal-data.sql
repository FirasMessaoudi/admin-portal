USE shc_portal
GO

INSERT INTO shc_portal.shc_config (conf_key, conf_value) VALUES ('scheduler.notification.template.processing.active.nodes', '127.0.0.1,localhost');
INSERT INTO shc_portal.shc_config (conf_key, conf_value) VALUES ('scheduler.notification.processing.active.nodes', '127.0.0.1,localhost');
GO

SET IDENTITY_INSERT shc_portal.shc_chatbot_item ON;

INSERT INTO shc_portal.shc_chatbot_item (id, code, lang, label) VALUES (1, 'HAJJ_RITUAL', 'ar',N'عن الحج');
INSERT INTO shc_portal.shc_chatbot_item (id, code, lang, label) VALUES (2, 'LOCATIONS', 'ar',N'مواقع مهمة');
INSERT INTO shc_portal.shc_chatbot_item (id, code, lang, label) VALUES (3, 'CONTACTS', 'ar',N'أرقام تواصل مهمة');
INSERT INTO shc_portal.shc_chatbot_item (id, code, lang, label) VALUES (4, 'SMART_CARD', 'ar',N'البطاقة الذكية');
INSERT INTO shc_portal.shc_chatbot_item (id, code, lang, label) VALUES (5, 'SPECIAL_NEEDS', 'ar',N'إحتياجات خاصة');

INSERT INTO shc_portal.shc_chatbot_item (id, code, lang, label, parent_code) VALUES (6, 'SLAUGHTER_PROCESS','ar',N'متى يمكن التأكد من أن عملية الذبح قد تمت؟ كما في حالة كون طالب النسك من الحجاج بحيث يرتبط أداء نسكه بتحلله في الحج أو المضحي غير الحاج ؟', 'HAJJ_RITUAL');
INSERT INTO shc_portal.shc_chatbot_item (id, code, lang, label, parent_code) VALUES (7, 'SLAUGHTER_PROCESS_RESPONSE',N'ar', N'بعد الشراء و بمجرد أن يتم خصم المبلغ من حساب العميل وتأكيد استلامه في حساب المشروع أو حصول العميل على الكوبون أو سند النسك، يعتبر العميل بذلك قد وكّل المشروع لأداء النسك نيابةً عنه وبذلك تبرأ ذمته من أداء النسك مع ملاحظة أنه في حال كون الشراء والدفع تم عبر الموقع الالكتروني سيتم إرسال رسالة تعلمه بتنفيذ طلبه (إتمام الذبح).','SLAUGHTER_PROCESS');

INSERT INTO shc_portal.shc_chatbot_item (id, code, lang, label, parent_code) VALUES (8, 'ELECTRONIC_PATH', 'ar', N'هل يوفر المسار الإلكتروني شراء الهدي والأضاحي ؟', 'HAJJ_RITUAL');
INSERT INTO shc_portal.shc_chatbot_item (id, code, lang, label, parent_code) VALUES (9, 'ELECTRONIC_PATH_RESPONSE', 'ar',N'نعم، هذه الخدمة متوفرة.','ELECTRONIC_PATH');

INSERT INTO shc_portal.shc_chatbot_item (id, code, lang, label, parent_code) VALUES (10, 'HAJJ_APPLICATION', 'ar',N'متى يبدأ التقديم في الحج لموسم 1443هـ', 'HAJJ_RITUAL');
INSERT INTO shc_portal.shc_chatbot_item (id, code, lang, label, parent_code) VALUES (11, 'HAJJ_APPLICATION_RESPONSE', 'ar',N'بدء الإعلان عن موعد فتح التقديم للحج 1443هـ  عن طريق موقع وزارة الحج والعمرة والقنوات الرسمية للوزارة  يوم الجمعة  الموافق 4/11/1443 هـ', 'HAJJ_APPLICATION');

INSERT INTO shc_portal.shc_chatbot_item (id, code, lang, label, parent_code) VALUES (12, 'HAJJ_AGE', 'ar',N'مالعمر المسموح لأداء الحج؟', 'HAJJ_RITUAL');
INSERT INTO shc_portal.shc_chatbot_item (id, code, lang, label, parent_code) VALUES (13, 'HAJJ_AGE_RESPONSE', 'ar',N'لا مانع من اصطحاب الأطفال في حج  1443هـ مع شرط كونهم محصنين.', 'HAJJ_AGE');

INSERT INTO shc_portal.shc_chatbot_item (id, code, lang, label, parent_code) VALUES (14, 'INTERNAL_HAJJ', 'ar',N'متى يبدأ التقديم في حملات الحج الداخلية ومتى ينتهي ؟', 'HAJJ_RITUAL');
INSERT INTO shc_portal.shc_chatbot_item (id, code, lang, label, parent_code) VALUES (15, 'INTERNAL_HAJJ_RESPONSE', 'ar',N'يبدأ التقديم4 ذو القعدة وينتهي بتاريخ 13 ذو القعدة', 'INTERNAL_HAJJ');

INSERT INTO shc_portal.shc_chatbot_item (id, code, lang, label, parent_code) VALUES (16, 'HAJJ_INQUIRY', 'ar',N'ما آلية الإستعلام عن الأحقية بالحج ؟', 'HAJJ_RITUAL');
INSERT INTO shc_portal.shc_chatbot_item (id, code, lang, label, parent_code) VALUES (17, 'HAJJ_INQUIRY_RESPONSE', 'ar',N'للإستعلام عن أحقية الحج, الدخول لموقع أبشر واختيار الاستعلامات الالكترونية ثم الاستعلام عن أحقية الحج ثم ادخال رقم الهوية والرمز المرئي وستظهر الأهلية.', 'HAJJ_INQUIRY');

INSERT INTO shc_portal.shc_chatbot_item (id, code, lang, label, parent_code) VALUES (18, 'GULF_NATIONALS', 'ar',N'الخليجيين المتواجدين داخل المملكة وليسو مقيمين ويرغبون بأداء فريضة الحج من الداخل', 'HAJJ_RITUAL');
INSERT INTO shc_portal.shc_chatbot_item (id, code, lang, label, parent_code) VALUES (19, 'GULF_NATIONALS_RESPONSE', 'ar',N'الخليجي المقيم في المملكة يمكنه التسجيل في المسار من خلال رقم الدخول ويمكن الحصول عليه من الجوازات ""* الخليجي الغير مقيم في السعودية لا يمكنه التسجيل في المسار الإلكتروني لحجاج الداخل، ويلزمه التسجيل في دولته حسب انظمتها.', 'GULF_NATIONALS');

INSERT INTO shc_portal.shc_chatbot_item (id, code, lang, label, parent_code) VALUES (20, 'MADINAH_CARE_CENTERS', 'ar',N'مراكز عناية المدينة','LOCATIONS');
INSERT INTO shc_portal.shc_chatbot_item (id, code, lang, label, parent_code) VALUES (21, 'MADINAH_CARE_CENTERS_RESPONSE', 'ar',N'مركز شرق المسجد النبوي الشريف العنوان: طريق الملك فيصل، الدائري الأول، الحرم أوقات العمل: 7 صباحاً - 9 مساءً مطار الامير محمد بن عبدالعزيز الدولي العنوان: مطار الأمير محمد بن عبد العزيز الدولي أوقات العمل: 8 صباحاً - 10 مساءً','MADINAH_CARE_CENTERS');

INSERT INTO shc_portal.shc_chatbot_item (id, code, lang, label, parent_code) VALUES (22, 'JEDDAH_CARE_CENTERS', 'ar',N'مراكز عناية جدة','LOCATIONS');
INSERT INTO shc_portal.shc_chatbot_item (id, code, lang, label, parent_code) VALUES (23, 'JEDDAH_CARE_CENTERS_RESPONSE', 'ar',N'مطار الملك عبدالعزيز الدولي بجدة​ العنوان: مطار الملك عبد العزيز الدولي صالة رقم1','JEDDAH_CARE_CENTERS');

INSERT INTO shc_portal.shc_chatbot_item (id, code, lang, label, parent_code) VALUES (24, 'MAKKAH_CARE_CENTERS', 'ar',N' مراكز عناية مكة المكرمة','LOCATIONS');
INSERT INTO shc_portal.shc_chatbot_item (id, code, lang, label, parent_code) VALUES (25, 'MAKKAH_CARE_CENTERS_RESPONSE', 'ar',N'فرع الحجون: طريق المدينة المنورة، الحجون، مكة. أوقات العمل: 8 صباحاً - 10 مساءً رابط الموقع على خرائط جوجل: https://maps.app.goo.gl/NE1G4t6S6WvNkDVb8 فرع المسفلة: لعنوان: شارع إبراهيم الخليل، المسفلة، مكة المكرمة. أوقات العمل: 8 صباحاً - 10 مساءً رابط الموقع على خرائط جوجل: https://maps.app.goo.gl/NGCnEg6hCp9JTT238 فرع فندق دار التوحيد/ مكة المكرمة رابط الموقع على خرائط جوجل: https://maps.app.goo.gl/3c9iDmVRL2Zqk3n57','MAKKAH_CARE_CENTERS');

INSERT INTO shc_portal.shc_chatbot_item (id, code, lang, label, parent_code) VALUES (26, 'HARAM', 'ar',N'الحرم','LOCATIONS');
INSERT INTO shc_portal.shc_chatbot_item (id, code, lang, label, parent_code) VALUES (27, 'HARAM_RESPONSE', 'ar', 'https://goo.gl/maps/P9L6JX9VCNgcuxR3A','HARAM');

INSERT INTO shc_portal.shc_chatbot_item (id, code, lang, label, parent_code) VALUES (28, 'MERCY_MOUNT', 'ar',N'جبل الرحمة','LOCATIONS');
INSERT INTO shc_portal.shc_chatbot_item (id, code, lang, label, parent_code) VALUES (29, 'MERCY_MOUNT_RESPONSE', 'ar', 'https://goo.gl/maps/TjMBJqj754QzJFg88','MERCY_MOUNT');

INSERT INTO shc_portal.shc_chatbot_item (id, code, lang, label, parent_code) VALUES (30, 'ARAFAT_MOUNT', 'ar',N'جبل عرفات','LOCATIONS');
INSERT INTO shc_portal.shc_chatbot_item (id, code, lang, label, parent_code) VALUES (31, 'ARAFAT_MOUNT_RESPONSE', 'ar', 'https://goo.gl/maps/hGcK5D7uY2jcr6gP7','ARAFAT_MOUNT');

INSERT INTO shc_portal.shc_chatbot_item (id, code, lang, label, parent_code) VALUES (32, 'MASJID_NABAWI', 'ar',N'المسجد النبوي','LOCATIONS');
INSERT INTO shc_portal.shc_chatbot_item (id, code, lang, label, parent_code) VALUES (33, 'MASJID_NABAWI_RESPONSE', 'ar', 'https://goo.gl/maps/SseRYc5NoZXXHxsh8','MASJID_NABAWI');

INSERT INTO shc_portal.shc_chatbot_item (id, code, lang, label, parent_code) VALUES (34, 'FATWA_NUMBERS', 'ar',N'أرقام الفتاوى','CONTACTS');
INSERT INTO shc_portal.shc_chatbot_item (id, code, lang, label, parent_code) VALUES (35, 'FATWA_NUMBERS_RESPONSE', 'ar',N'رقم فتاوى 24 ساعة السعودية أو رقم الفتاوى المجاني: رقم الفتاوى المجاني 24 ساعة: 8002451000 رقم الفتاوى الموحد مكة المكرمة: 8001222100 رقم فتاوى مكة المكرمة: 8001222400 رقم فتاوى إذاعة القرآن الكريم: 0114052999 رقم دار الإفتاء السعودية: 0114595555 رقم فتاوى الحج والعمرة: 8002451000 رقم فتاوى واتس (1): 0501785536','FATWA_NUMBERS');

INSERT INTO shc_portal.shc_chatbot_item (id, code, lang, label, parent_code) VALUES (36, 'PRESIDENCY_MOSQUES_NUMBERS', 'ar',N'أرقام رئاسة شؤون الحرمين','CONTACTS');
INSERT INTO shc_portal.shc_chatbot_item (id, code, lang, label, parent_code) VALUES (37, 'PRESIDENCY_MOSQUES_NUMBERS_RESPONSE', 'ar',N'التواصل على الرقم المجاني 1966','PRESIDENCY_MOSQUES_NUMBERS');

INSERT INTO shc_portal.shc_chatbot_item (id, code, lang, label, parent_code) VALUES (38, 'HAJJ_MINISTRY', 'ar',N'وزارة الحج و العمرة','CONTACTS');
INSERT INTO shc_portal.shc_chatbot_item (id, code, lang, label, parent_code) VALUES (39, 'HAJJ_MINISTRY_RESPONSE', 'ar',N'للتواصل مع وزارة الحج و العمرة يمكنكم الاتصال على مركز خدمات المستفيدين بوزارة الحج والعمرة على الرقم 920002814 أو من خلال البريد الإلكتروني care@haj.gov.sa او من خلال الحساب الرسمي عبر تويتر @MOHU_Care','HAJJ_MINISTRY');

INSERT INTO shc_portal.shc_chatbot_item (id, code, lang, label, parent_code) VALUES (40, 'EMERGENCY_NUMBER', 'ar',N'للحالات الطارئة والخدمات الأمنية','CONTACTS');
INSERT INTO shc_portal.shc_chatbot_item (id, code, lang, label, parent_code) VALUES (41, 'EMERGENCY_NUMBER_RESPONSE', 'ar', '911','EMERGENCY_NUMBER');

INSERT INTO shc_portal.shc_chatbot_item (id, code, lang, label, parent_code) VALUES (42, 'MEDICAL_ADVICES', 'ar',N'للإستشارات والإستفسارات الطبية','CONTACTS');
INSERT INTO shc_portal.shc_chatbot_item (id, code, lang, label, parent_code) VALUES (43, 'MEDICAL_ADVICES_RESPONSE', 'ar', '937','MEDICAL_ADVICES');

INSERT INTO shc_portal.shc_chatbot_item (id, code, lang, label, parent_code) VALUES (44, 'EVALUATION', 'ar', N'التقييم', 'SMART_CARD');
INSERT INTO shc_portal.shc_chatbot_item (id, code, lang, label, parent_code) VALUES (45, 'EVALUATION_RESPONSE', 'ar',N'يوجد التقييم اليومي والتقييم في نهاية رحلة الحاج من قائمة الخدمات ويجب الحرص على تعبئته للعمل على تحسين الخدمات','EVALUATION');

INSERT INTO shc_portal.shc_chatbot_item (id, code, lang, label, parent_code) VALUES (46, 'COMPLAINTS', 'ar', N'الشكاوى', 'SMART_CARD');
INSERT INTO shc_portal.shc_chatbot_item (id, code, lang, label, parent_code) VALUES (47, 'COMPLAINTS_RESPONSE', 'ar',N'يتم تسجيل الشكوى على المنظم التابع له وبعد مرور فترة من عدم الحل يتم رفع الشكوى كبلاغ','COMPLAINTS');

INSERT INTO shc_portal.shc_chatbot_item (id, code, lang, label, parent_code) VALUES (48, 'INCIDENTS', 'ar', N'البلاغات', 'SMART_CARD');
INSERT INTO shc_portal.shc_chatbot_item (id, code, lang, label, parent_code) VALUES (49, 'INCIDENTS_RESPONSE', 'ar',N'يتم تسجيل البلاغ على المنظم التابع له ويرفع كبلاغ إلى الحهات الرسمية لإتخاذ اللازم','INCIDENTS');

INSERT INTO shc_portal.shc_chatbot_item (id, code, lang, label, parent_code) VALUES (50, 'CARD_COLORS', 'ar', N'ألوان البطاقات', 'SMART_CARD');
INSERT INTO shc_portal.shc_chatbot_item (id, code, lang, label, parent_code) VALUES (51, 'CARD_COLORS_RESPONSE', 'ar',N'تعتمد أولان البطاقة على شركة الطوافة التابع لها الحاج كمان أنه يوجد لون مميز لحجاج الداخل','CARD_COLORS');

INSERT INTO shc_portal.shc_chatbot_item (id, code, lang, label, parent_code) VALUES (52, 'WHEELCHAIRS_GATES', 'ar', N'ماهي بوابات الحرم  المستخدمة للكراسي المتحركة؟', 'SPECIAL_NEEDS');
INSERT INTO shc_portal.shc_chatbot_item (id, code, lang, label, parent_code) VALUES (53, 'WHEELCHAIRS_GATES_RESPONSE', 'ar',N'يمكن التواصل مع الرئاسة العامة لشؤون الحرمين بهذا الخصوص هاتف ١٩٦٦','WHEELCHAIRS_GATES');

INSERT INTO shc_portal.shc_chatbot_item (id, code, lang, label, parent_code) VALUES (54, 'HAJJ_CARTS', 'ar', N'هل تتوفر العربات داخل الحرم؟', 'SPECIAL_NEEDS');
INSERT INTO shc_portal.shc_chatbot_item (id, code, lang, label, parent_code) VALUES (55, 'HAJJ_CARTS_RESPONSE', 'ar',N'توفر شؤون الحرمين عربات كهربائية للطواف أو السعي في أدوار محددة بالحرم يتم استئجارها عن طريق تطبيق تنقل يرجى التواصل مع رئاسة شؤون الحرمين لمتابعة أي مستجدات.','HAJJ_CARTS');

INSERT INTO shc_portal.shc_chatbot_item (id, code, lang, label, parent_code) VALUES (56, 'SEPCIAL_NEEDS_SERVICES', 'ar', N'ماهي الخدمات المتاحة لذوي الاحتياجات الخاصة؟', 'SPECIAL_NEEDS');
INSERT INTO shc_portal.shc_chatbot_item (id, code, lang, label, parent_code) VALUES (57, 'SEPCIAL_NEEDS_SERVICES_RESPONSE', 'ar',N'يمكن التواصل مع الرئاسة العامة لشؤون الحرمين بهذا الخصوص','SEPCIAL_NEEDS_SERVICES');

SET IDENTITY_INSERT shc_portal.shc_chatbot_item OFF;
GO


