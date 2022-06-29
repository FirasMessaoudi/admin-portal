USE shc_portal
GO
ALTER TABLE shc_portal.shc_notification_template ADD company_code varchar(100);
GO

ALTER TABLE shc_portal.shc_applicant_group ALTER COLUMN reference_number nvarchar(100);
GO

ALTER TABLE shc_portal.shc_applicant_incident
    ADD mobile_number VARCHAR(20) NOT NULL DEFAULT('')

ALTER TABLE shc_portal.shc_applicant_complaint
    ADD mobile_number VARCHAR(20) NOT NULL DEFAULT('')

    GO