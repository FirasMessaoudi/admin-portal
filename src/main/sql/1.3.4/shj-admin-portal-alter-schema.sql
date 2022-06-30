USE shc_portal
GO
ALTER TABLE shc_portal.shc_applicant_health_disease ALTER COLUMN label_en nvarchar(650) NULL;

ALTER TABLE [shc_portal].[shc_portal].[shc_company] ADD company_uid uniqueidentifier NOT NULL DEFAULT(NEWID())

GO
