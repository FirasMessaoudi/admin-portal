USE shc_portal
GO
ALTER TABLE shc_portal.shc_portal.shc_chatbot_item ALTER COLUMN label nvarchar(2000)

GO
ALTER TABLE shc_portal.shc_applicant_package_housing ALTER column site_camp_ref_code nvarchar(650) null
GO

ALTER TABLE shc_portal.shc_applicant_package_housing ALTER column site_tent nvarchar(650) null
GO

ALTER TABLE shc_portal.shc_applicant_package_housing ALTER column site_floor nvarchar(650) null
GO

ALTER TABLE shc_portal.shc_applicant_package_housing ALTER column site_corridor nvarchar(650) null
GO

ALTER TABLE shc_portal.shc_applicant_package_housing ALTER column site_room nvarchar(650) null
GO

ALTER TABLE shc_portal.shc_applicant_package_housing ALTER column site_bed_number nvarchar(650) null
GO


ALTER TABLE shc_portal.shc_applicant_package_transportation ALTER column vehicle_number nvarchar(650) null
GO

ALTER TABLE shc_portal.shc_package_housing ALTER column location_name_ar nvarchar(650) null
GO

ALTER TABLE shc_portal.shc_package_housing ALTER column location_name_en nvarchar(650) null
GO


