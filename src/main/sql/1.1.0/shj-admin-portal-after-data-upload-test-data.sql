-- This script must be executed after the data upload
USE shc_portal
GO
INSERT INTO shc_portal.shc_group_applicant_list (group_id, applicant_uin)
SELECT 1, UIN
FROM shc_portal.shc_applicant_digital_id;
GO

update shc_portal.shc_applicant_package_catering set option_en= 'continental', option_ar=N'كونتيننتال'
GO

