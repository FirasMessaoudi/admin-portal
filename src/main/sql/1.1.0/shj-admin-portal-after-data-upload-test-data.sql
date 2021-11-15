-- This script must be executed after the data upload
USE shc_portal
GO
INSERT INTO shc_portal.shc_group_applicant_list (group_id, applicant_uin)
SELECT 1, UIN
FROM shc_portal.shc_applicant_digital_id;
GO

-------------------------For mobile demo ------------------------------------
UPDATE [shc_portal].[shc_company_ritual_step] SET [time] = '2021-11-15 15:00:00' WHERE step_index = 1
UPDATE [shc_portal].[shc_company_ritual_step] SET [time] = '2021-11-16 10:00:00' WHERE step_index = 2
UPDATE [shc_portal].[shc_company_ritual_step] SET [time] = '2021-11-16 19:00:00' WHERE step_index = 3
UPDATE [shc_portal].[shc_company_ritual_step] SET [time] = '2021-11-17 15:00:00' WHERE step_index = 4
GO

UPDATE shc_portal.shc_applicant_package SET start_date = '2021-10-15' ,end_date = '2021-11-20'
GO

UPDATE shc_portal.shc_package_housing SET  validity_start ='2021-11-12 10:00:00'  ,validity_end ='2021-11-18 19:00:00' WHERE package_id = 1
GO

UPDATE shc_portal.shc_religious_occasions_day_lk SET code = '04_10' WHERE id in (1,2)


INSERT INTO shc_portal.shc_religious_occasions_day_lk
    (code ,lang,label) VALUES  ('04_11'  ,'en'  ,'Second days of Tashriq' )
INSERT INTO shc_portal.shc_religious_occasions_day_lk
    (code ,lang,label) VALUES  ('04_11'  ,'ar'  , N'ثاني ايام التشريق' )
INSERT INTO shc_portal.shc_religious_occasions_day_lk
    (code ,lang,label) VALUES  ('04_12'  ,'en'  ,'Third days of Tashriq' )
INSERT INTO shc_portal.shc_religious_occasions_day_lk
    (code ,lang,label) VALUES  ('04_12'  ,'ar'  ,N'ثالث ايام التشريق' )
GO

UPDATE shc_portal.shc_package_catering
SET  type = 'DINNER' ,description_ar = N'بوفيه مفتوح' ,description_en = 'Open buffet' WHERE id =2
GO


