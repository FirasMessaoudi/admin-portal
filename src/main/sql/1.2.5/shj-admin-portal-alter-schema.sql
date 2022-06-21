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

ALTER TABLE shc_portal.shc_applicant ALTER column full_name_ar nvarchar(650)
ALTER TABLE shc_portal.shc_applicant ALTER column full_name_en nvarchar(650)
ALTER TABLE shc_portal.shc_applicant_health_disease ALTER column label_en nvarchar(650) not null
ALTER TABLE shc_portal.shc_applicant_health_disease ALTER column label_ar nvarchar(650)
ALTER TABLE shc_portal.shc_company ALTER column label_ar nvarchar(650)
ALTER TABLE shc_portal.shc_company ALTER column label_en nvarchar(650)
ALTER TABLE shc_portal.shc_company_ritual_step ALTER column location_name_ar nvarchar(650) not null
ALTER TABLE shc_portal.shc_company_ritual_step ALTER column location_name_en nvarchar(650) not null
ALTER TABLE shc_portal.shc_company_staff ALTER column full_name_ar nvarchar(650)
ALTER TABLE shc_portal.shc_company_staff ALTER column full_name_en nvarchar(650)
ALTER TABLE shc_portal.shc_data_segment ALTER column label_ar nvarchar(650) not null
ALTER TABLE shc_portal.shc_data_segment ALTER column label_en nvarchar(650) not null
ALTER TABLE shc_portal.shc_decision_rule ALTER column label_ar nvarchar(650) not null
ALTER TABLE shc_portal.shc_decision_rule ALTER column label_en nvarchar(650) not null
ALTER TABLE shc_portal.shc_housing_master ALTER column location_name_ar nvarchar(650) not null
ALTER TABLE shc_portal.shc_housing_master ALTER column location_name_en nvarchar(650) not null
ALTER TABLE shc_portal.shc_housing_master ALTER column address_ar nvarchar(650)
ALTER TABLE shc_portal.shc_housing_master ALTER column address_en nvarchar(650)
ALTER TABLE shc_portal.shc_housing_zone ALTER column label_ar nvarchar(650) not null
ALTER TABLE shc_portal.shc_housing_zone ALTER column label_en nvarchar(650) not null
ALTER TABLE shc_portal.shc_package_catering ALTER column description_ar nvarchar(650)
ALTER TABLE shc_portal.shc_package_catering ALTER column description_en nvarchar(650)
ALTER TABLE shc_portal.shc_ritual_package ALTER column package_name_ar nvarchar(650)
ALTER TABLE shc_portal.shc_ritual_package ALTER column package_name_en nvarchar(650)
ALTER TABLE shc_portal.shc_role ALTER column label_ar nvarchar(650) not null
ALTER TABLE shc_portal.shc_role ALTER column label_en nvarchar(650) not null
GO

ALTER TABLE shc_portal.shc_package_housing ALTER COLUMN location_name_ar nvarchar(650) not null
ALTER TABLE shc_portal.shc_package_housing ALTER COLUMN location_name_en nvarchar(650) not null
ALTER TABLE shc_portal.shc_package_housing ALTER COLUMN address_ar nvarchar(650)
ALTER TABLE shc_portal.shc_package_housing ALTER COLUMN address_en nvarchar(650)
ALTER TABLE shc_portal.shc_applicant_package_catering ALTER COLUMN option_ar nvarchar(650)
ALTER TABLE shc_portal.shc_applicant_package_catering ALTER COLUMN option_en nvarchar(650)
ALTER TABLE shc_portal.shc_applicant ALTER COLUMN full_name_origin nvarchar(650)
ALTER TABLE shc_portal.shc_company_staff ALTER COLUMN full_name_origin nvarchar(650)
GO