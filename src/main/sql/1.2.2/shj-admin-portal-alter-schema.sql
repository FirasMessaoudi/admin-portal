USE shc_portal
GO
ALTER TABLE shc_portal.shc_company_ritual_step_lk ALTER COLUMN code VARCHAR(45) NOT NULL;
GO

ALTER TABLE shc_portal.shc_company_ritual_step ALTER COLUMN step_code VARCHAR(45) NOT NULL;
GO

ALTER TABLE shc_portal.shc_data_request ADD company_code varchar(100)
GO

