USE shc_portal
GO
ALTER TABLE shc_portal.shc_applicant_package_transportation add vehicle_info nvarchar(255) null
GO
ALTER TABLE shc_portal.shc_applicant_package_housing add camp_info nvarchar(255) null
GO
ALTER TABLE shc_portal.shc_company_staff ALTER column custom_job_title nvarchar(30)
GO

ALTER TABLE shc_portal.shc_company_ritual_step_lk ADD step_index int NOT NULL;
ALTER TABLE shc_portal.shc_company_ritual_step_lk ADD location_lat decimal(10,8);
ALTER TABLE shc_portal.shc_company_ritual_step_lk ADD location_lng decimal(11,8);
GO

