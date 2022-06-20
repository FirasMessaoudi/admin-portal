USE
shc_portal
GO
ALTER TABLE shc_portal.shc_company_ritual_step_lk
    Add edit_mode VARCHAR(45) NULL;
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

ALTER TABLE shc_portal.shc_applicant ALTER COLUMN date_of_birth_gregorian date NULL;
GO