USE shc_portal
GO
ALTER TABLE shc_portal.shc_applicant_incident ADD city varchar(20) NOT NULL DEFAULT ('OTHERS');
ALTER TABLE shc_portal.shc_applicant_incident ADD camp_number nvarchar(50) NULL;
ALTER TABLE shc_portal.shc_applicant_incident ADD crm_ticket_number nvarchar(50) NULL;



GO