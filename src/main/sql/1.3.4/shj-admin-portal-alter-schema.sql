USE shc_portal
GO
ALTER TABLE shc_portal.shc_applicant_health_disease ALTER COLUMN label_en nvarchar(650) NULL;

ALTER TABLE [shc_portal].[shc_portal].[shc_company] ADD company_uid uniqueidentifier NOT NULL DEFAULT(NEWID())

GO
ALTER TABLE shc_portal.shc_applicant_complaint ADD crm_status_updated bit NULL;
ALTER TABLE shc_portal.shc_applicant_incident ADD crm_status_updated bit NULL;
GO

ALTER TABLE shc_portal.shc_applicant_card ADD deleted bit default 0;
GO