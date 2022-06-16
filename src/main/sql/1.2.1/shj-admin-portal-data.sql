USE shc_portal
GO
UPDATE shc_portal.shc_company SET code = '37_GOVERNMENT_AGENCY' WHERE code = '99999_GOVERNMENT_AGENCY';
GO
-- add government agency companies
insert into shc_portal.shc_company (label_ar,label_en,code) values (N'وزارة الداخلية','Ministry of Interior Affairs','1_GOVERNMENT_AGENCY')
insert into shc_portal.shc_company (label_ar,label_en,code) values (N'وزارة الشؤون الإسلامية والدعوة والإرشاد','Ministry of Islamic Affairs','2_GOVERNMENT_AGENCY')
insert into shc_portal.shc_company (label_ar,label_en,code) values (N'وزارة الصحة','Ministry of Health','3_GOVERNMENT_AGENCY')
insert into shc_portal.shc_company (label_ar,label_en,code) values (N'وزارة التجارة','Ministry of Commerce','4_GOVERNMENT_AGENCY')
insert into shc_portal.shc_company (label_ar,label_en,code) values (N'وزارة الشؤون البلدية والقروية والإسكان','Ministry of Municipal and Rural Affairs and Housing','5_GOVERNMENT_AGENCY')
insert into shc_portal.shc_company (label_ar,label_en,code) values (N'وزارة الحج والعمرة','Ministry of Hajj and Umrah','6_GOVERNMENT_AGENCY')
insert into shc_portal.shc_company (label_ar,label_en,code) values (N'وزارة الموارد البشرية والتنمية الاجتماعية','Ministry of Human Resources and Social Development','7_GOVERNMENT_AGENCY')
insert into shc_portal.shc_company (label_ar,label_en,code) values (N'وزارة البيئة والمياه والزراعة','Ministry of Environment, Water and Agriculture','8_GOVERNMENT_AGENCY')
insert into shc_portal.shc_company (label_ar,label_en,code) values (N'وزارة الاتصالات وتقنية المعلومات','Ministry of Communications and Information Technology','9_GOVERNMENT_AGENCY')
insert into shc_portal.shc_company (label_ar,label_en,code) values (N'وزارة الإعلام','Ministry of Media','10_GOVERNMENT_AGENCY')
insert into shc_portal.shc_company (label_ar,label_en,code) values (N'وزارة النقل والخدمات اللوجستية','Ministry of Transport and Logistics','11_GOVERNMENT_AGENCY')
insert into shc_portal.shc_company (label_ar,label_en,code) values (N'إمارة منطقة مكة المكرمة','Emirate of Makkah Province','12_GOVERNMENT_AGENCY')
insert into shc_portal.shc_company (label_ar,label_en,code) values (N'هيئة وكالة الأنباء السعودية','Saudi Press Agency Authority','13_GOVERNMENT_AGENCY')
insert into shc_portal.shc_company (label_ar,label_en,code) values (N'الهيئة العامة للنقل','Public Transport Authority','14_GOVERNMENT_AGENCY')
insert into shc_portal.shc_company (label_ar,label_en,code) values (N'الهيئة السعودية للبيانات والذكاء الاصطناعي','Saudi Data and Artificial Intelligence Authority','15_GOVERNMENT_AGENCY')
insert into shc_portal.shc_company (label_ar,label_en,code) values (N'هيئة الرقابة ومكافحة الفساد','Control and Anti-Corruption Authority','16_GOVERNMENT_AGENCY')
insert into shc_portal.shc_company (label_ar,label_en,code) values (N'الهيئة الملكية لمدينة مكة المكرمة والمشاعر المقدسة','The Royal Commission for the Holy City of Makkah and the Holy Sites','17_GOVERNMENT_AGENCY')
insert into shc_portal.shc_company (label_ar,label_en,code) values (N'هيئة الصحة العامة','Public Health Authority','18_GOVERNMENT_AGENCY')
insert into shc_portal.shc_company (label_ar,label_en,code) values (N'هيئة الهلال الأحمر السعودي','Saudi Red Crescent Authority','19_GOVERNMENT_AGENCY')
insert into shc_portal.shc_company (label_ar,label_en,code) values (N'الهيئة التنسيقية لمؤسسات أرباب الطوائف','Coordinating body for the foundations of the communities','20_GOVERNMENT_AGENCY')
insert into shc_portal.shc_company (label_ar,label_en,code) values (N'هيئة تطوير منطقة مكة المكرمة','Makkah Region Development Authority','21_GOVERNMENT_AGENCY')
insert into shc_portal.shc_company (label_ar,label_en,code) values (N'هيئة الإذاعة والتلفزيون','Radio and Television Commission','22_GOVERNMENT_AGENCY')
insert into shc_portal.shc_company (label_ar,label_en,code) values (N'الهيئة العامة للإعلام المرئي والمسموع','General Authority for Audiovisual Media','23_GOVERNMENT_AGENCY')
insert into shc_portal.shc_company (label_ar,label_en,code) values (N'المركز الوطني لقياس أداء الأجهزة العامة','The National Center for Measuring the Performance of Public Institutions','24_GOVERNMENT_AGENCY')
insert into shc_portal.shc_company (label_ar,label_en,code) values (N'المركز الوطني للتخصيص','National Center for Personalization','25_GOVERNMENT_AGENCY')
insert into shc_portal.shc_company (label_ar,label_en,code) values (N'المؤسسة العامة لتحلية المياه المالحة','Saline Water Conversion Corporation','26_GOVERNMENT_AGENCY')
insert into shc_portal.shc_company (label_ar,label_en,code) values (N'جامعة أم القرى','Umm Al Qura University','27_GOVERNMENT_AGENCY')
insert into shc_portal.shc_company (label_ar,label_en,code) values (N'المديرية العامة للدفاع المدني','General Directorate of Civil Defense','28_GOVERNMENT_AGENCY')
insert into shc_portal.shc_company (label_ar,label_en,code) values (N'مديرية الأمن العام','Directorate of general security','29_GOVERNMENT_AGENCY')
insert into shc_portal.shc_company (label_ar,label_en,code) values (N'أمانة العاصمة المقدسة','The Holy Capital Secretariat','30_GOVERNMENT_AGENCY')
insert into shc_portal.shc_company (label_ar,label_en,code) values (N'الرئاسة العامة لشؤون المسجد الحرام والمسجد النبوي','General Presidency for the affairs of the Grand Mosque and the Prophets Mosque','31_GOVERNMENT_AGENCY')
insert into shc_portal.shc_company (label_ar,label_en,code) values (N'رئاسة الاستخبارات العامة','General Intelligence Presidency','32_GOVERNMENT_AGENCY')
insert into shc_portal.shc_company (label_ar,label_en,code) values (N'برنامج خدمة ضيوف الرحمن','Rahmans service program','33_GOVERNMENT_AGENCY')
insert into shc_portal.shc_company (label_ar,label_en,code) values (N'الغرفة التجارية الصناعية بمكة المكرمة','Chamber of Commerce and Industry in Makkah','34_GOVERNMENT_AGENCY')
insert into shc_portal.shc_company (label_ar,label_en,code) values (N'المجلس التنسيقي لحجاج الداخل ','Coordination Council For Internal Pilgrims','35_GOVERNMENT_AGENCY')
insert into shc_portal.shc_company (label_ar,label_en,code) values (N'النقابة العامة للسيارات ','Naqaba','36_GOVERNMENT_AGENCY')
insert into shc_portal.shc_company (label_ar,label_en,code) values (N'علم','ELM','37_GOVERNMENT_AGENCY');
GO
INSERT INTO shc_portal.shc_company_ritual_season (company_id,ritual_season_id,active,season_start,season_end,total_quota,air_quota,sea_quota,land_quota)
SELECT id,1,1,14431101,14431230,0,0,0,0 FROM shc_portal.shc_company C where code like '%govern%'
UNION ALL SELECT id,2,1,14431101,14431230,0,0,0,0 FROM shc_portal.shc_company C where code like '%govern%'
UNION ALL SELECT id,3,1,14431101,14431230,0,0,0,0 FROM shc_portal.shc_company C where code like '%govern%'
GO

-- INSERT INTO shc_portal.shc_company_ritual_step_lk

UPDATE shc_portal.shc_config
SET conf_key = 'huic.sms.api.token'
WHERE conf_key = 'sms.api.token';

UPDATE shc_portal.shc_config
SET conf_key = 'huic.sms.api.url'
WHERE conf_key = 'sms.api.url';

INSERT INTO shc_portal.shc_config(conf_key, conf_value)
VALUES('huic.sms.api.mock.enabled','true');
GO
