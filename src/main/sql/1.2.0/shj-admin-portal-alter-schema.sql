USE shc_portal
GO
ALTER TABLE shc_portal.shc_company_staff ALTER column id_number varchar(16) null
GO

ALTER TABLE shc_portal.shc_applicant_package_transportation  add  vehicle_info nvarchar(255) null
GO

ALTER TABLE shc_portal.shc_applicant_package_housing  add  camp_info nvarchar(255) null
GO