USE shc_portal
GO
ALTER TABLE shc_portal.shc_company_ritual_step_lk Add edit_mode VARCHAR(45) NULL;
GO

ALTER TABLE shc_portal.shc_housing_master ALTER COLUMN location_name_ar nvarchar(600) NOT NULL;
ALTER TABLE shc_portal.shc_housing_master ALTER COLUMN location_name_en nvarchar(600) NOT NULL;
GO

ALTER TABLE shc_portal.shc_applicant_package_housing
    add site_camp_ref_code nvarchar(30) null
GO
ALTER TABLE shc_portal.shc_applicant_package_housing
    add site_tent nvarchar(30) null
GO

ALTER TABLE shc_portal.shc_applicant_package_housing
    add site_floor nvarchar(30) null
GO

ALTER TABLE shc_portal.shc_applicant_package_housing
    add site_corridor nvarchar(30) null
GO

ALTER TABLE shc_portal.shc_applicant_package_housing
    add site_room nvarchar(30) null
GO

ALTER TABLE shc_portal.shc_applicant_package_housing
    add site_bed_number nvarchar(30) null
GO


ALTER TABLE shc_portal.shc_package_transportation ALTER COLUMN location_from_name_ar NVARCHAR(650);
ALTER TABLE shc_portal.shc_package_transportation ALTER COLUMN location_from_name_en NVARCHAR(650);
ALTER TABLE shc_portal.shc_package_transportation ALTER COLUMN location_to_name_ar NVARCHAR(650);
ALTER TABLE shc_portal.shc_package_transportation ALTER COLUMN location_to_name_en NVARCHAR(650);
ALTER TABLE shc_portal.shc_package_transportation ALTER COLUMN route_details NVARCHAR(650);
go